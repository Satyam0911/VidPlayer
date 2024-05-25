package com.example.vedioplayer;

import android.app.AppCompactActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;

public class MainActivity extends AppCompactActivity {
    VideoView video;
    private final int REQUEST_VIDEO_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        video = findViewById(R.id.videoView);
        Button btnVideo;

        String vpath = "android.resource://"+getPackageName()+"/raw/android_11v";
        Uri videoUri = Uri.parse(vpath);
        video.setVideoURI(videoUri);
        video.start();

        MediaController mediaController = new MediaController(this);
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);

        btnVideo = findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,REQUEST_VIDEO_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_VIDEO_CODE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            video.setVideoURI(videoUri);
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(video);
            video.setMediaController(mediaController);
            video.start();
        }
    }
}