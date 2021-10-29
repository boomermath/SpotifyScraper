package com.boomermath.spotifyscraper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Spotify {
    private static JSONObject parseResource(String inputUrl) {
        try {
            URL url = new URL(inputUrl);
            Element script = Jsoup.connect("https://open.spotify.com/embed" + url.getPath()).get().select("script#resource").get(0);
            String parsedJSON = URLDecoder.decode(script.data(), StandardCharsets.UTF_8);
            return new JSONObject(parsedJSON);
        } catch (Exception e) {
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

    public static SpotifyTrack getTrack(String url) {
        JSONObject json = parseResource(url);
        JSONObject albumInfo = json.getJSONObject("album");
        JSONObject artistInfo = json.getJSONArray("artists").getJSONObject(0);

        SpotifyArtist artist = new SpotifyArtist(artistInfo.getString("name"), artistInfo.getString("type"), new SpotifyURI(artistInfo.getString("uri")));        

        return new SpotifyTrack(
                json.getString("name"),
                json.getString("type"), 
                new SpotifyURI(json.getString("uri"), artist, 
                parseThumbnails(albumInfo.getJSONArray("images")),
                json.getInt("popularity"),
                json.getBoolean("explicit"),
                json.getLong("duration_ms"),
                json.getString("preview_url"),
                albumInfo.getString("release_date"),
                json.optString("added_at"),
                json.optJSONObject("added_by") ? new SpotifyURI(json.getJSONObject("added_by").getString("uri")) : null
        );
    }

    public static SpotifyPlaylist getPlaylist(String url) {
        JSONObject json = parseResource(url);
        JSONObject artistInfo = json.getJSONObject("owner");

        SpotifyArtist owner = new SpotifyArtist(artistInfo.getString("display_name"), artistInfo.getString("type"), new SpotifyURI(artistInfo.getString("uri")));

        JSONArray jsonTracks = json.getJSONObject("tracks").getJSONArray("items");
        SpotifyTrack[] tracks = new SpotifyTrack[jsonTracks.length()];

        for (int i = 0; i < jsonTracks.length(); i++) {
            JSONObject track = jsonTracks.getJSONObject(i);
            tracks[i] = parseTrack(track);
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
