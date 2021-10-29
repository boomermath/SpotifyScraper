package com.boomermath.spotifyscraper.entities;

public class SpotifyPlaylist extends SpotifyComponent {
    private final String description;
    private final SpotifyTrack[] tracks;
    private final int followers;
    private final Image[] thumbnails;
    private final SpotifyArtist owner;

    public SpotifyPlaylist(String name, String type, String description, SpotifyTrack[] tracks, SpotifyURI uri, int followers, Image[] thumbnails, SpotifyArtist owner) {
        super(name, type, uri);
        this.description = description;
        this.tracks = tracks;
        this.followers = followers;
        this.thumbnails = thumbnails;
        this.owner = owner;
    }

    public String description() {
        return description;
    }

    public SpotifyTrack[] tracks() {
        return tracks;
    }

    public int followers() {
        return followers;
    }

    public Image[] thumbnails() {
        return thumbnails;
    }

    public SpotifyArtist owner() {
        return owner;
    }
}