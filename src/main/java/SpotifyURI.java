public class SpotifyURI {

    private String[] URI;

    public SpotifyURI(String url) {
        URI = url.split(":");
    }

    public String id() {
        return URI[2];
    }

    public String toURI() {
        StringBuilder uri = new StringBuilder();
        uri.append(URI[0]);
        uri.append(":");
        uri.append(URI[1]);
        uri.append(":");
        uri.append(URI[2]);
        
        return uri.toString();
    }

    public String toURL() {
        StringBuilder url = new StringBuilder("https://open.spotify.com/");
        url.append(URI[1]);
        url.append("/");
        url.append(URI[2]);

        return url.toString();
    }
}
