package com.example.musicstreammelodify;

public class Song {
    private int id;
    private String title;
    private String artist;
    private String album;
    private String coverImagePath;
    private String filePath;

    public Song(int id, String title, String artist, String album, String coverImagePath, String filePath) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.coverImagePath = coverImagePath;
        this.filePath = filePath;
    }

    // Getters và Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getAlbum() { return album; }
    public String getCoverImagePath() { return coverImagePath; }
    public String getFilePath() { return filePath; }
}
