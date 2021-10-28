public class SpotifyArtist {
    private final String name;
    private final SpotifyURI uri;

    public SpotifyArtist(String name, SpotifyURI uri) {
        this.name = name; 
        this.uri = uri;
    }

    public String id() {
        return uri.id();
    }

    public String name() {
        return name;
    }

    public String uri() {
        return uri.toURI();
    }

    public String url() {
        return uri.toURL();
    }
}