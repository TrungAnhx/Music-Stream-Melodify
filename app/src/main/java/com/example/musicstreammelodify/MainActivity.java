package com.example.musicstreammelodify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Kiểm tra nếu người dùng đã đăng nhập hay chưa
        if (!checkIfUserIsLoggedIn()) {
            // Nếu chưa đăng nhập, chuyển đến LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();  // Đóng MainActivity để không quay lại sau khi đăng nhập
        } else {
            // Nếu đã đăng nhập, hiển thị màn hình playlist
            setContentView(R.layout.activity_main);
            initializePlaylistUI();  // Phương thức khởi tạo UI cho các playlist
        }
    }

    // Hàm kiểm tra trạng thái đăng nhập
    private boolean checkIfUserIsLoggedIn() {
        SharedPreferences prefs = getSharedPreferences("user_data", MODE_PRIVATE);
        return prefs.getBoolean("is_logged_in", false);  // Trả về true nếu đã đăng nhập
    }

    private void initializePlaylistUI() {
        // Khai báo các ImageView cho các playlist
        ImageView popPlaylist1 = findViewById(R.id.popPlaylist1);
        ImageView popPlaylist2 = findViewById(R.id.popPlaylist2);
        ImageView popPlaylist3 = findViewById(R.id.popPlaylist3);
        ImageView popPlaylist4 = findViewById(R.id.popPlaylist4);

        ImageView rapPlaylist1 = findViewById(R.id.rapPlaylist1);
        ImageView rapPlaylist2 = findViewById(R.id.rapPlaylist2);
        ImageView rapPlaylist3 = findViewById(R.id.rapPlaylist3);
        ImageView rapPlaylist4 = findViewById(R.id.rapPlaylist4);

        ImageView newMusicPlaylist1 = findViewById(R.id.newMusicPlaylist1);
        ImageView newMusicPlaylist2 = findViewById(R.id.newMusicPlaylist2);
        ImageView newMusicPlaylist3 = findViewById(R.id.newMusicPlaylist3);
        ImageView newMusicPlaylist4 = findViewById(R.id.newMusicPlaylist4);

        // Set OnClickListener cho các playlist
        popPlaylist1.setOnClickListener(v -> openPlaylistActivity("Pop Playlist 1", R.drawable.billie, getPopSongs1()));
        popPlaylist2.setOnClickListener(v -> openPlaylistActivity("Pop Playlist 2", R.drawable.billie, getPopSongs2()));
        popPlaylist3.setOnClickListener(v -> openPlaylistActivity("Pop Playlist 3", R.drawable.billie, getPopSongs3()));
        popPlaylist4.setOnClickListener(v -> openPlaylistActivity("Pop Playlist 4", R.drawable.billie, getPopSongs4()));

        rapPlaylist1.setOnClickListener(v -> openPlaylistActivity("Rap Playlist 1", R.drawable.playlist_rap, getRapSongs1()));
        rapPlaylist2.setOnClickListener(v -> openPlaylistActivity("Rap Playlist 2", R.drawable.playlist_rap2, getRapSongs2()));
        rapPlaylist3.setOnClickListener(v -> openPlaylistActivity("Rap Playlist 3", R.drawable.playlist_rap3, getRapSongs3()));
        rapPlaylist4.setOnClickListener(v -> openPlaylistActivity("Rap Playlist 4", R.drawable.playlist_rap4, getRapSongs4()));

        newMusicPlaylist1.setOnClickListener(v -> openPlaylistActivity("New Music Playlist 1", R.drawable.playlist_newmusic, getNewMusicSongs1()));
        newMusicPlaylist2.setOnClickListener(v -> openPlaylistActivity("New Music Playlist 2", R.drawable.playlist_newmusic2, getNewMusicSongs2()));
        newMusicPlaylist3.setOnClickListener(v -> openPlaylistActivity("New Music Playlist 3", R.drawable.playlist_newmusic3, getNewMusicSongs3()));
        newMusicPlaylist4.setOnClickListener(v -> openPlaylistActivity("New Music Playlist 4", R.drawable.playlist_newmusic4, getNewMusicSongs4()));
    }

    // Hàm mở PlaylistActivity với các dữ liệu truyền vào
    private void openPlaylistActivity(String playlistName, int playlistImageRes, ArrayList<Song> songs) {
        Intent intent = new Intent(MainActivity.this, PlaylistActivity.class);
        intent.putExtra("playlist_name", playlistName);
        intent.putExtra("playlist_image", playlistImageRes);
        intent.putParcelableArrayListExtra("songs", songs);
        startActivity(intent);
    }

    // Các phương thức lấy danh sách bài hát cho từng playlist (thêm từng bài hát thủ công)

    // Playlist Pop
    private ArrayList<Song> getPopSongs1() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("BellyAche", "Billie Eilish", R.drawable.song1_4, R.raw.billie1));
        songs.add(new Song("Everything I've wanted", "Billie Eilish", R.drawable.billie2, R.raw.billie2));
        songs.add(new Song("Hostage", "Billie Eilish", R.drawable.billie3, R.raw.billie3));
        return songs;
    }

    private ArrayList<Song> getPopSongs2() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Phía Sau Em", "Ẩn danh", R.drawable.pop4, R.raw.pop4));
        songs.add(new Song("Em Của Quá Khứ", "Ẩn danh", R.drawable.pop5, R.raw.pop5));
        songs.add(new Song("Ngày Chưa Giông Bão", "Ẩn danh", R.drawable.pop6, R.raw.pop6));
        return songs;
    }

    private ArrayList<Song> getPopSongs3() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Em Không Quay Về", "Ẩn danh", R.drawable.pop7, R.raw.pop7));
        songs.add(new Song("Tình Yêu Màu Nắng", "Ẩn danh", R.drawable.pop8, R.raw.pop8));
        songs.add(new Song("Thu Cuối", "Ẩn danh", R.drawable.pop9, R.raw.pop9));
        return songs;
    }

    private ArrayList<Song> getPopSongs4() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Yêu Một Người Có Lẽ", "Ẩn danh", R.drawable.pop1, R.raw.pop1));
        songs.add(new Song("We're different", "Ẩn danh", R.drawable.pop2, R.raw.pop2));
        songs.add(new Song("Hẹn Ước Từ Hư Vô", "Ẩn danh", R.drawable.pop3, R.raw.pop3));
        return songs;
    }

    // Playlist Rap
    private ArrayList<Song> getRapSongs1() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Trở về", "Wrxdie", R.drawable.rap1, R.raw.rap1));
        songs.add(new Song("Tim anh ghen", "Wrxdie", R.drawable.rap2, R.raw.rap2));
        songs.add(new Song("Get money", "Wrxdie", R.drawable.rap3, R.raw.rap3));
        return songs;
    }

    private ArrayList<Song> getRapSongs2() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Mấy đứa nhóc", "Wrxdie", R.drawable.rap4, R.raw.rap4));
        songs.add(new Song("Gia tài", "Wrxdie", R.drawable.rap5, R.raw.rap5));
        songs.add(new Song("Thíc Qé", "Wrxdie", R.drawable.rap6, R.raw.rap6));
        return songs;
    }

    private ArrayList<Song> getRapSongs3() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Lonely Stonie", "Wrxdie", R.drawable.rap7, R.raw.rap7));
        songs.add(new Song("Cả 2", "Wrxdie", R.drawable.rap8, R.raw.rap8));
        songs.add(new Song("Đế Chế", "Wrxdie", R.drawable.rap9, R.raw.rap9));
        return songs;
    }

    private ArrayList<Song> getRapSongs4() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Băng Qua Cầu Giấy", "Wrxdie", R.drawable.rap10, R.raw.rap10));
        songs.add(new Song("Mistreated", "Wrxdie", R.drawable.rap9, R.raw.rap9));
        songs.add(new Song("This Song", "Wrxdie", R.drawable.rap2, R.raw.rap2));
        return songs;
    }

    // Playlist New Music
    private ArrayList<Song> getNewMusicSongs1() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Party Favor", "Billie", R.drawable.billie3, R.raw.billie4));
        songs.add(new Song("Yêu Một Người Có Lẽ", "Ẩn danh", R.drawable.pop1, R.raw.pop1));
        songs.add(new Song("we're different", "Ẩn danh", R.drawable.pop2, R.raw.pop2));
        return songs;
    }

    private ArrayList<Song> getNewMusicSongs2() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Get Money", "Wrxdie", R.drawable.rap3, R.raw.rap3));
        songs.add(new Song("Phía Sau Em", "Ẩn danh", R.drawable.pop4, R.raw.pop4));
        songs.add(new Song("Gia Tài", "Wrxdie", R.drawable.rap5, R.raw.rap5));
        return songs;
    }

    private ArrayList<Song> getNewMusicSongs3() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Em Của Quá Khứ", "Ẩn danh", R.drawable.pop5, R.raw.pop5));
        songs.add(new Song("Thíc Qé", "Wrxdie", R.drawable.rap6, R.raw.rap6));
        songs.add(new Song("Lonely Stonie", "Wrxdie", R.drawable.rap7, R.raw.rap7));
        return songs;
    }

    private ArrayList<Song> getNewMusicSongs4() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("BellyAche", "Billie Eilish", R.drawable.billie, R.raw.billie1));
        songs.add(new Song("New Music 4 - 2", "Wrxdie", R.drawable.rap8, R.raw.rap8));
        songs.add(new Song("Mistreated", "Wrxdie", R.drawable.rap2, R.raw.rap2));
        return songs;
    }
}
