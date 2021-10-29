package com.boomermath.spotifyscraper.entities;

public class SpotifyURI {

    private final String[] URI;

    public SpotifyURI(String url) {
        URI = url.split(":");
    }

    public String id() {
        return URI[2];
    }

    public String toURI() {
        return URI[0] + ":" + URI[1] + ":" + URI[2];
    }

    public String toURL() {
        return "https://open.spotify.com/" + URI[1] + "/" + URI[2];
    }
}
