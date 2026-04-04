package com.example.audiovideoplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    VideoView videoView;
    Uri audioUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openFile = findViewById(R.id.openFile);
        Button openUrl = findViewById(R.id.openUrl);
        Button play = findViewById(R.id.play);
        Button pause = findViewById(R.id.pause);
        Button stop = findViewById(R.id.stop);
        Button restart = findViewById(R.id.restart);
        EditText urlInput = findViewById(R.id.urlInput);
        videoView = findViewById(R.id.videoView);

        openFile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("audio/*");
            startActivityForResult(intent, 1);
        });

        openUrl.setOnClickListener(v -> {
            String url = urlInput.getText().toString();
            videoView.setVideoURI(Uri.parse(url));
            videoView.start();
        });

        play.setOnClickListener(v -> {
            if(mediaPlayer != null) mediaPlayer.start();
        });

        pause.setOnClickListener(v -> {
            if(mediaPlayer != null) mediaPlayer.pause();
        });

        stop.setOnClickListener(v -> {
            if(mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(this, audioUri);
            }
        });

        restart.setOnClickListener(v -> {
            if(mediaPlayer != null) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK) {
            audioUri = data.getData();
            mediaPlayer = MediaPlayer.create(this, audioUri);
        }
    }
}