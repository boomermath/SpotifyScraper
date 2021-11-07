package com.boomermath.spotifyscraper.entities;

import java.util.Objects;

public class SpotifyURI {

    private final String[] URI;

    public SpotifyURI(String url) {
        URI = url.split(":");

        if (!Objects.equals(URI[0], "spotify") || URI.length != 3) {
            throw new IllegalArgumentException("Invalid Spotify URI");
        }
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

    public String toAPIURL() {
        return "https://api.spotify.com/v1/" + URI[1] + "s/" + URI[2];
    }
}
