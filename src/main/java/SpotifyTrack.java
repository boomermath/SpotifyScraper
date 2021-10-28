import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.time.LocalDate;

public class SpotifyTrack {
        private final String name;
        private final String type;
        private final SpotifyURI uri;
        private final SpotifyArtist artist;
        private final Image[] thumbnails;
        private final boolean explicit;
        private final long durationMS;
        private final String audioPreviewURL;
        private final String releaseDate;

        public SpotifyTrack(String name, String type, SpotifyURI uri, SpotifyArtist artist, Image[] thumbnails, boolean explicit, long durationMS, String audioPreviewURL, String releaseDate) {
                this.name = name;
                this.type = type;
                this.uri = uri;
                this.artist = artist;
                this.thumbnails = thumbnails;
                this.explicit = explicit;
                this.durationMS = durationMS;
                this.audioPreviewURL = audioPreviewURL;
                this.releaseDate = releaseDate;
        }
        
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

        public SpotifyArtist artist() {
                return artist;
        }

        public Image[] thumbnails() {
                return thumbnails;
        }

        public boolean explicit() {
                return explicit;
        }

        public long durationMS() {
                return durationMS;
        }

        public String duration() {
                String pattern = durationMS > 3600000 ? "HH:mm:ss" : "mm:ss";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                format.setTimeZone(TimeZone.getTimeZone("UTC"));

                return format.format(durationMS);
        }

        public String audioPreviewURL() {
                return audioPreviewURL;
        }

        public LocalDate releaseDate() {
                return LocalDate.parse(releaseDate);
        }
}