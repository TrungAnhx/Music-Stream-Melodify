package com.example.musicstreammelodify;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NowPlayingActivity extends AppCompatActivity {

    private ImageView songImage, playPauseButton, backButton;
    private TextView songName, artistName;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        // Khởi tạo các thành phần
        songImage = findViewById(R.id.songImage);
        playPauseButton = findViewById(R.id.playPauseButton);
        backButton = findViewById(R.id.backButton); // Nút Back
        songName = findViewById(R.id.songName);
        artistName = findViewById(R.id.artistName);

        // Lấy dữ liệu từ Intent
        String name = getIntent().getStringExtra("song_name");
        String artist = getIntent().getStringExtra("artist_name");
        int imageRes = getIntent().getIntExtra("song_image", -1);
        int audioRes = getIntent().getIntExtra("song_audio", -1);
        String playlistName = getIntent().getStringExtra("playlist_name"); // Thông tin playlist
        int playlistImage = getIntent().getIntExtra("playlist_image", -1); // Hình ảnh playlist

        // Kiểm tra dữ liệu hợp lệ
        if (name == null || artist == null || audioRes == -1) {
            Toast.makeText(this, "Lỗi: Không thể tải bài hát!", Toast.LENGTH_SHORT).show();
            finish(); // Thoát nếu dữ liệu không hợp lệ
            return;
        }

        // Cập nhật giao diện
        songName.setText(name);
        artistName.setText(artist);

        if (imageRes != -1) {
            try {
                songImage.setImageResource(imageRes);
            } catch (Exception e) {
                songImage.setVisibility(ImageView.GONE); // Ẩn ảnh nếu có lỗi
            }
        } else {
            songImage.setVisibility(ImageView.GONE); // Ẩn ảnh nếu không có ảnh hợp lệ
        }

        // Tạo MediaPlayer và phát nhạc
        try {
            mediaPlayer = MediaPlayer.create(this, audioRes);
            if (mediaPlayer == null) {
                throw new IllegalArgumentException("Không thể khởi tạo MediaPlayer.");
            }
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi phát nhạc: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            finish(); // Thoát nếu MediaPlayer không thể khởi tạo
            return;
        }

        // Xử lý nút Play/Pause
        playPauseButton.setOnClickListener(v -> {
            if (isPlaying) {
                mediaPlayer.pause();
                playPauseButton.setImageResource(R.drawable.ic_play);
            } else {
                mediaPlayer.start();
                playPauseButton.setImageResource(R.drawable.ic_pause);
            }
            isPlaying = !isPlaying;
        });

        // Xử lý nút Back để quay lại PlaylistActivity
        backButton.setOnClickListener(v -> {
            // Quay lại PlaylistActivity với thông tin playlist
            Intent backIntent = new Intent(NowPlayingActivity.this, PlaylistActivity.class);
            backIntent.putExtra("playlist_name", playlistName);  // Truyền tên playlist
            backIntent.putExtra("playlist_image", playlistImage);  // Truyền ảnh playlist
            backIntent.putParcelableArrayListExtra("songs", getIntent().getParcelableArrayListExtra("songs")); // Truyền danh sách bài hát
            startActivity(backIntent); // Mở PlaylistActivity
            finish(); // Đóng NowPlayingActivity
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onBackPressed() {
        // Lấy thông tin từ Intent của NowPlayingActivity
        String playlistName = getIntent().getStringExtra("playlist_name");  // Tên playlist
        int playlistImage = getIntent().getIntExtra("playlist_image", -1);  // Ảnh playlist
        ArrayList<Song> songs = getIntent().getParcelableArrayListExtra("songs"); // Danh sách bài hát

        // Tạo intent mới để quay lại PlaylistActivity
        Intent intent = new Intent(NowPlayingActivity.this, PlaylistActivity.class);
        intent.putExtra("playlist_name", playlistName);
        intent.putExtra("playlist_image", playlistImage);
        intent.putParcelableArrayListExtra("songs", songs);

        // Đảm bảo PlaylistActivity luôn là Activity phía trên cùng trong ngăn xếp Activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        // Không gọi finish() nữa để tránh thoát ứng dụng
    }

}
