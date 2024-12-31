package com.example.musicstreammelodify;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SongAdapter songAdapter;
    private MusicDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lấy danh sách bài hát từ cơ sở dữ liệu
        dbHelper = new MusicDatabaseHelper(this);
        List<Song> songList = dbHelper.getAllSongs();

        // Kết nối Adapter với RecyclerView
        songAdapter = new SongAdapter(this, songList);
        recyclerView.setAdapter(songAdapter);
    }

    public void onPlaylistClick(View view) {
        // Khi người dùng bấm vào playlist, mở ra playlist và hiển thị các bài hát
        // Bạn có thể sử dụng một Intent để chuyển sang Activity khác hoặc hiển thị một dialog.
    }

}
