package com.boomermath.spotifyscraper;

public class Main {
    public static void main(String[] args) {
        SpotifyTrack spotifyTrack = Parser.parseTrack("https://open.spotify.com/embed/track/69WpV0U7OMNFGyq8I63dcC");
        log(spotifyTrack);
    }

    public static void log(SpotifyTrack spotifyTrack) {
        System.out.println(spotifyTrack.id());
        System.out.println(spotifyTrack.name());
        System.out.println(spotifyTrack.type());
        System.out.println(spotifyTrack.uri());
        System.out.println(spotifyTrack.url());
        System.out.println(spotifyTrack.artist().name());
        System.out.println(spotifyTrack.thumbnails()[0].url());
        System.out.println(spotifyTrack.explicit());
        System.out.println(spotifyTrack.durationMS());
        System.out.println(spotifyTrack.duration());
        System.out.println(spotifyTrack.audioPreviewURL());
        System.out.println(spotifyTrack.releaseDate().toString());
    }
}
