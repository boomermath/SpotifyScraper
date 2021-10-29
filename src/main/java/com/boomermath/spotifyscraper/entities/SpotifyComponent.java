package com.boomermath.spotifyscraper.entities;

class SpotifyComponent {
    protected final String name;
    protected final String type;
    protected final SpotifyURI uri;

    public SpotifyComponent(String name, String type, SpotifyURI uri) {
        this.name = name;
        this.type = type;
        this.uri = uri;
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
}