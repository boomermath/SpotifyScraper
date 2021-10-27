import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.time.LocalDate;

public class SpotifyTrack {
        private String name;
        private String type;
        private SpotifyURI uri;
        private SpotifyArtist artist;
        private Image[] thumbnails;
        private boolean explicit;
        private int durationMS;
        private String audioPreviewURL;
        private String releaseDate;

        public SpotifyTrack(String name, String type, SpotifyURI uri, SpotifyArtist artist, Image[] thumbnails, boolean explicit, int durationMS, String audioPreviewURL, String releaseDate) {
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

        public int durationMS() {
                return durationMS;
        }

        public String duration() {
                String pattern = durationMS > 3600000 ? "HH:mm:ss" : "mm:ss";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                format.setTimeZone(Timezone.getTimeZone("UTC"));

                return format.format(durationMS);
        }

        public String audioPreviewURL() {
                return audioPreviewURL;
        }

        public LocalDate releaseDate() {
                return LocalDate.parse(releaseDate);
        }
}