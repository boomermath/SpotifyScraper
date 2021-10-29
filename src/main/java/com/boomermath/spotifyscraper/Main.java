package com.boomermath.spotifyscraper;

import com.boomermath.spotifyscraper.entities.SpotifyTrack;

public class Main {
    public static void main(String[] args) {
        SpotifyTrack spotifyTrack = Spotify.getTrack("https://open.spotify.com/track/06Pvy98db25O7wlfFFFIRM?si=f1fdc3a5a9144b7e");
        log(spotifyTrack);
        System.out.println(spotifyTrack);
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
        System.out.println(spotifyTrack.popularity());
        System.out.println(spotifyTrack.addedAt());
        System.out.println(spotifyTrack.addedBy());
    }
}
