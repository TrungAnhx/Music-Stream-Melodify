package com.example.musicstreammelodify;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class MusicDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "music.db";
    private static final int DATABASE_VERSION = 1;

    public MusicDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE songs (id INTEGER PRIMARY KEY, title TEXT, artist TEXT, album TEXT, cover_image_path TEXT, file_path TEXT)");
        db.execSQL("CREATE TABLE playlists (id INTEGER PRIMARY KEY, name TEXT)");
        db.execSQL("CREATE TABLE playlist_songs (playlist_id INTEGER, song_id INTEGER, FOREIGN KEY (playlist_id) REFERENCES playlists(id), FOREIGN KEY (song_id) REFERENCES songs(id))");
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY, email TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS songs");
        db.execSQL("DROP TABLE IF EXISTS playlists");
        db.execSQL("DROP TABLE IF EXISTS playlist_songs");
        onCreate(db);
    }

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

    // Thêm người dùng vào cơ sở dữ liệu
    public boolean addUser(String email, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);

        long result = db.insert("users", null, values); // Trả về -1 nếu thất bại
        db.close();
        return result != -1;
    }


    // Kiểm tra thông tin đăng nhập
    public boolean checkUserCredentials(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});

        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isValid;
    }

    // Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu chưa
    public boolean checkEmailExists(String email) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM users WHERE email = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }
}