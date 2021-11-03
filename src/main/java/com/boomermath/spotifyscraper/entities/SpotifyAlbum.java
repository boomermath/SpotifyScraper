package com.boomermath.spotifyscraper.entities;

public class SpotifyAlbum extends SpotifyComponent {
    private SpotifyArtist[] artists;

    public SpotifyAlbum(String name, String type, SpotifyURI uri, SpotifyArtist[] artists) {
        super(name, type, uri);
        this.artists = artists;
    }

    public SpotifyArtist[] artist() {
        return artists;
    }
}
