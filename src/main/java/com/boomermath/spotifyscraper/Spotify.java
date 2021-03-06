package com.boomermath.spotifyscraper;

import com.boomermath.spotifyscraper.entities.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Spotify {

    private static String getURL(String inputUrl) throws MalformedURLException {
        URL url;
        try {
            url = new URL(inputUrl);
        } catch (MalformedURLException e) {
            url = new URL(new SpotifyURI(inputUrl).toURL());
        }

        if (!url.getHost().equals("open.spotify.com")) {
            throw new MalformedURLException("URL is not a valid spotify url!");
        }

        String path = url.getPath();

        return path.startsWith("/embed") ? inputUrl : "https://open.spotify.com/embed" + path;
    }

    private static JSONObject parseResource(String inputUrl) {
        try {
            String url = getURL(inputUrl);
            Element script = Jsoup.connect(url).get().select("script#resource").get(0);
            String parsedJSON = URLDecoder.decode(script.data(), StandardCharsets.UTF_8);
            return new JSONObject(parsedJSON);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Input is not a valid spotify url!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Image[] parseThumbnails(JSONArray jsonImages) {
        Image[] images = new Image[jsonImages.length()];

        for (int i = 0; i < jsonImages.length(); i++) {
            JSONObject jsonImage = jsonImages.getJSONObject(i);
            images[i] = new Image(jsonImage.getInt("height"), jsonImage.getInt("width"), jsonImage.getString("url"));
        }

        return images;
    }

    private static SpotifyArtist[] parseArtists(JSONArray artistsJSON) {
        SpotifyArtist[] artists = new SpotifyArtist[artistsJSON.length()];

        for (int i = 0; i < artistsJSON.length(); i++) {
            JSONObject artistInfo = artistsJSON.getJSONObject(i);

            artists[i] = new SpotifyArtist(
                    artistInfo.getString("name"),
                    artistInfo.getString("type"),
                    new SpotifyURI(artistInfo.getString("uri"))
            );
        }

        return artists;
    }

    private static SpotifyTrack parseTrack(JSONObject json) {
        json = json.has("track") ? json.getJSONObject("track") : json; //if its from a playlist

        JSONObject albumInfo = json.getJSONObject("album");
        SpotifyAlbum album = new SpotifyAlbum(
                albumInfo.getString("name"),
                albumInfo.getString("album_type"),
                new SpotifyURI(albumInfo.getString("uri")),
                parseArtists(albumInfo.getJSONArray("artists"))
        );

        return new SpotifyTrack(
                json.getString("name"),
                json.getString("type"),
                new SpotifyURI(json.getString("uri")), album,
                parseArtists(json.getJSONArray("artists")),
                parseThumbnails(albumInfo.getJSONArray("images")),
                json.getInt("popularity"),
                json.getBoolean("explicit"),
                json.getLong("duration_ms"),
                json.getString("preview_url"),
                albumInfo.getString("release_date"),
                json.optString("added_at", null),
                json.has("added_by") ? new SpotifyURI(json.getJSONObject("added_by").getString("uri")) : null
        );
    }

    public static SpotifyTrack getTrack(String url) {
        return parseTrack(parseResource(url));
    }

    public static SpotifyPlaylist getPlaylist(String url) {
        JSONObject json = parseResource(url);
        JSONObject artistInfo = Objects.requireNonNull(json).getJSONObject("owner");

        SpotifyArtist owner = new SpotifyArtist(artistInfo.getString("display_name"), artistInfo.getString("type"), new SpotifyURI(artistInfo.getString("uri")));

        JSONArray jsonTracks = json.getJSONObject("tracks").getJSONArray("items");
        SpotifyTrack[] tracks = new SpotifyTrack[jsonTracks.length()];

        for (int i = 0; i < jsonTracks.length(); i++) {
            tracks[i] = parseTrack(jsonTracks.getJSONObject(i));
        }

        return new SpotifyPlaylist(
                json.getString("name"),
                json.getString("type"),
                json.getString("description"), tracks,
                new SpotifyURI(json.getString("uri")),
                json.getJSONObject("followers").getInt("total"),
                parseThumbnails(json.getJSONArray("images")),
                owner
        );
    }
}
