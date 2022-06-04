package com.example.cse535_assignment_1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoDisplay extends AppCompatActivity {
    public static String GESTURE_URL;
    String url, action, gesture_url;

    Button back, play, practice;
    VideoView videoView;

    MediaController mediaController;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_display);
        // Creating instances of components in the layout
        back = findViewById(R.id.back);
        practice = findViewById(R.id.practice);
        videoView = findViewById(R.id.videoView);
        progressBar = findViewById(R.id.progressBar);

        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // Setting the Chosen Video URL
        Intent i = getIntent();
        gesture_url = i.getStringExtra(GESTURE_URL);
        String[] arrOfStr = gesture_url.split("&");
        action = arrOfStr[0];
        url = arrOfStr[1];
        System.out.println(action);
        System.out.println(url);
        Uri uri = Uri.parse(url);
        videoView.setVideoURI(uri);
        videoView.requestFocus();

        //TO go back to the 1st Page
        back.setOnClickListener(v -> {
            Intent intent = new Intent(VideoDisplay.this, MainActivity.class);
            startActivity(intent);
        });

        videoView.setOnPreparedListener(mediaPlayer -> {
            videoView.start();
            progressBar.setVisibility(View.GONE);
        });

        //TO go to the Next Page i.e Recording and Uploading Page
        practice.setOnClickListener(v -> {
            Intent intent = new Intent(VideoDisplay.this, RecordGesture.class);
            intent.putExtra(RecordGesture.Action, action);
            intent.putExtra(RecordGesture.ORIGINAL_VIDEO_URL, url);
            startActivity(intent);
        });
    }
}