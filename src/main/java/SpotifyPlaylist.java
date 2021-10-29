public class SpotifyPlaylist extends SpotifyComponent {
    private final String description;
    private final SpotifyTrack[] tracks;
    private final int followers;
    private final Image[] thumbnails;
    private final SpotifyArtist owner;

    public SpotifyPlaylist(String name, String type, String description, SpotifyTrack[] tracks, SpotifyURI uri, int followers, Image[] thumbnails, SpotifyArtist owner) {
        super(name, type, uri);
        this.description = description;
        this.followers = followers;
        this.thumbnails = thumbnails;
        this.owner = owner;
    }

    public static String description() {
        return description;
    }

    public static SpotifyTrack[] tracks() {
        return tracks;
    }

    public static int followers() {
        return followers;
    }

    public static Image[] thumbnails() {
        return thumbnails;
    }

    public static SpotifyArtist owner() {
        return owner;
    }
}