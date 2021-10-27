import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
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
}
