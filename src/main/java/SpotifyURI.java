public class SpotifyURI {

    private String[] URI;

    public SpotifyURI(String url) {
        URI = url.split(":");
    }

    public String toURL() {
        StringBuilder url = new StringBuilder("https://open.spotify.com/");
        url.append(URI[1]);
        url.append("/");
        url.append(URI[2]);

        return url.toString();
    }
}
