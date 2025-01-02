package com.example.musicstreammelodify;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity {

    private ImageView playlistImage;
    private TextView playlistName;
    private RecyclerView songRecyclerView;

    // MediaPlayer chung cho toàn bộ Activity
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist_detail);

        ImageView backButton = findViewById(R.id.backButton);
        playlistImage = findViewById(R.id.playlistImage);
        playlistName = findViewById(R.id.playlistName);
        songRecyclerView = findViewById(R.id.songRecyclerView);

        backButton.setOnClickListener(v -> onBackPressed());

        // Khi vào PlaylistActivity, dừng nhạc cũ (nếu có)
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("playlist_name");
            int imageRes = intent.getIntExtra("playlist_image", -1);
            ArrayList<Song> songs = intent.getParcelableArrayListExtra("songs");

            playlistName.setText(name);
            if (imageRes != -1) {
                playlistImage.setImageResource(imageRes);
            }
            setupRecyclerView(songs);
        }
    }

    private void setupRecyclerView(ArrayList<Song> songs) {
        songRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        songRecyclerView.setAdapter(new SongAdapter(this, songs));
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

}
