package com.example.musicstreammelodify;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MusicDatabaseHelper extends SQLiteOpenHelper {

    // Tên và phiên bản của cơ sở dữ liệu
    private static final String DATABASE_NAME = "music.db";
    private static final int DATABASE_VERSION = 1;

    public MusicDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng lưu bài hát
        db.execSQL("CREATE TABLE songs (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL, " +
                "artist TEXT NOT NULL, " +
                "album TEXT, " +
                "cover_image_path TEXT, " +
                "file_path TEXT NOT NULL)");

        // Tạo bảng lưu playlist
        db.execSQL("CREATE TABLE playlists (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL)");

        // Tạo bảng liên kết bài hát và playlist
        db.execSQL("CREATE TABLE playlist_songs (" +
                "playlist_id INTEGER NOT NULL, " +
                "song_id INTEGER NOT NULL, " +
                "FOREIGN KEY (playlist_id) REFERENCES playlists(id), " +
                "FOREIGN KEY (song_id) REFERENCES songs(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu nâng cấp
        db.execSQL("DROP TABLE IF EXISTS songs");
        db.execSQL("DROP TABLE IF EXISTS playlists");
        db.execSQL("DROP TABLE IF EXISTS playlist_songs");
        onCreate(db);
    }

    // Hàm thêm bài hát
    public void addSong(String title, String artist, String album, String coverImagePath, String filePath) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("artist", artist);
        values.put("album", album);
        values.put("cover_image_path", coverImagePath);
        values.put("file_path", filePath);

        db.insert("songs", null, values);
        db.close();
    }

    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM songs", null);

        if (cursor.moveToFirst()) {
            do {
                Song song = new Song(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("title")),
                        cursor.getString(cursor.getColumnIndexOrThrow("artist")),
                        cursor.getString(cursor.getColumnIndexOrThrow("album")),
                        cursor.getString(cursor.getColumnIndexOrThrow("cover_image_path")),
                        cursor.getString(cursor.getColumnIndexOrThrow("file_path"))
                );
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return songs;
    }


}
