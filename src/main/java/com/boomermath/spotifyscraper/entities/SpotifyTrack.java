package com.boomermath.spotifyscraper.entities;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.TimeZone;

public class SpotifyTrack extends SpotifyComponent {
    private final SpotifyArtist artist;
    private final Image[] thumbnails;
    private final int popularity;
    private final boolean explicit;
    private final long durationMS;
    private final String audioPreviewURL;
    private final String releaseDate;
    private final String addedAt;
    private final SpotifyURI addedBy;

    public SpotifyTrack(String name, String type, SpotifyURI uri, SpotifyArtist artist, Image[] thumbnails, int popularity, boolean explicit, long durationMS, String audioPreviewURL, String releaseDate, String addedAt, SpotifyURI addedBy) {
        super(name, type, uri);
        this.artist = artist;
        this.thumbnails = thumbnails;
        this.popularity = popularity;
        this.explicit = explicit;
        this.durationMS = durationMS;
        this.audioPreviewURL = audioPreviewURL;
        this.releaseDate = releaseDate;
        this.addedAt = addedAt;
        this.addedBy = addedBy;
    }

    public SpotifyArtist artist() {
        return artist;
    }

    public Image[] thumbnails() {
        return thumbnails;
    }

    public int popularity() {
        return popularity;
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

    public Instant addedAt() {
        return addedAt != null ? Instant.parse(addedAt) : null;
    }

    public SpotifyURI addedBy() {
        return addedBy;
    }
}