package com.boomermath.spotifyscraper.entities;

public class SpotifyAlbum extends SpotifyComponent {
    private SpotifyArtist artist;

    public SpotifyAlbum(String name, String type, SpotifyURI uri, SpotifyArtist artist) {
        super(name, type, uri);
        this.artist = artist;
    }

    public SpotifyArtist artist() {
        return artist;
    }
}
