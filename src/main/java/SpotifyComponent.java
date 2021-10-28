public class SpotifyComponent {
    private final String name;
    private final String type; 
    private final String uri;

    public String id() {
        return uri.id();
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }  

    public String uri() {
        return uri.toURI();
    }

    public String url() {
        return uri.toURL();
    }
}