import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class HTMLParser {
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

            StringBuilder uri = new StringBuilder("spotify:");
            uri.append(path[1]);
            uri.append(":");
            uri.append(path[2]);

            return uri.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
