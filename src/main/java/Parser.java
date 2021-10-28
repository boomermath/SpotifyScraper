import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Parser {
    public static JSONObject parseResource(String url) {
        try {
            Element script = Jsoup.connect(url).get().select("script#resource").get(0);
            String parsedJSON = URLDecoder.decode(script.data(), StandardCharsets.UTF_8);
            return new JSONObject(parsedJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String UrltoUri(String url) {
        try {
            URL spotifyUrl = new URL(url);
            String[] path = spotifyUrl.getPath().split("/");
            return "spotify:" + path[1] + ":" + path[2];
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SpotifyTrack parseTrack(String url) {
        JSONObject json = parseResource(url);
        assert json != null;
        JSONObject albumInfo = json.getJSONObject("album");
        JSONObject artistInfo = json.getJSONArray("artists").getJSONObject(0);

        SpotifyURI uri = new SpotifyURI(json.getString("uri"));
        SpotifyArtist artist = new SpotifyArtist(artistInfo.getString("name"), new SpotifyURI(artistInfo.getString("uri")));

        JSONArray JSONimages = albumInfo.getJSONArray("images");
        Image[] images = new Image[JSONimages.length()];

        for (int i = 0; i < JSONimages.length(); i++) {
            JSONObject jsonImage = JSONimages.getJSONObject(i);
            images[i] = new Image(jsonImage.getInt("height"), jsonImage.getInt("width"), jsonImage.getString("url"));
        }

        return new SpotifyTrack(
                json.getString("name"),
                json.getString("type"), uri, artist, images,
                json.getBoolean("explicit"),
                json.getLong("duration_ms"),
                json.getString("preview_url"),
                albumInfo.getString("release_date")
        );
    }
}
