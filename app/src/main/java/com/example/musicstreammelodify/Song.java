package com.example.musicstreammelodify;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
    private String name;
    private String artist;
    private int imageRes;
    private int audioRes;

    public Song(String name, String artist, int imageRes, int audioRes) {
        this.name = name;
        this.artist = artist;
        this.imageRes = imageRes;
        this.audioRes = audioRes;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public int getImageRes() {
        return imageRes;
    }

    public int getAudioRes() {
        return audioRes;
    }

    // Parcelable implementation
    protected Song(Parcel in) {
        name = in.readString();
        artist = in.readString();
        imageRes = in.readInt();
        audioRes = in.readInt();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(artist);
        dest.writeInt(imageRes);
        dest.writeInt(audioRes);
    }
}
