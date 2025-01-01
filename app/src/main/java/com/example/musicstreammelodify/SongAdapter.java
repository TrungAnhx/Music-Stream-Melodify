package com.example.musicstreammelodify;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private ArrayList<Song> songs;
    private Context context;
    private MediaPlayer mediaPlayer;

    public SongAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
        this.mediaPlayer = new MediaPlayer();  // Tạo MediaPlayer mới khi tạo Adapter
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.songName.setText(song.getName());
        holder.artistName.setText(song.getArtist());
        holder.songImage.setImageResource(song.getImageRes());

        View.OnClickListener listener = v -> {
            Intent intent = new Intent(context, NowPlayingActivity.class);
            intent.putExtra("song_name", song.getName());
            intent.putExtra("artist_name", song.getArtist());
            intent.putExtra("song_image", song.getImageRes());
            intent.putExtra("song_audio", song.getAudioRes());
            context.startActivity(intent);
        };

        holder.songName.setOnClickListener(listener);
        holder.songImage.setOnClickListener(listener);
    }



    @Override
    public int getItemCount() {
        return songs.size();
    }

    private void playSong(int audioRes) {
        // Kiểm tra nếu MediaPlayer đang phát, dừng và reset
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }

        // Tạo mới MediaPlayer với bài hát mới
        mediaPlayer = MediaPlayer.create(context, audioRes);
        mediaPlayer.setOnCompletionListener(mp -> {
            // Khi bài hát kết thúc, giải phóng tài nguyên MediaPlayer
            mp.release();
        });
        mediaPlayer.start();
    }

    // Giải phóng tài nguyên khi không sử dụng
    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    static class SongViewHolder extends RecyclerView.ViewHolder {
        TextView songName, artistName;
        ImageView songImage;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.songName);
            artistName = itemView.findViewById(R.id.artistName);
            songImage = itemView.findViewById(R.id.songImage);
        }
    }
}
