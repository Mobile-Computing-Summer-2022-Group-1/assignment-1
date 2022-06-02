package com.example.cse535_assignment_1;

import static android.location.LocationManager.GPS_PROVIDER;
import static android.location.LocationManager.NETWORK_PROVIDER;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

//import androidx.core.app.ActivityCompat;

import android.widget.MediaController;
import android.widget.ProgressBar;
import android.media.MediaPlayer;
import android.os.Bundle;

public class VideoDisplay extends AppCompatActivity {
    public static String URL;
    public static String Action;
    String url, action;
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
        url = i.getStringExtra(URL);
        action = i.getStringExtra(Action);
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

        //TO go back to the 1st Page
        practice.setOnClickListener(v -> {
            Intent intent = new Intent(VideoDisplay.this, RecordGesture.class);
            intent.putExtra(RecordGesture.Action, action);
            startActivity(intent);
        });
    }
}