package com.example.cse535_assignment_1;

import static com.example.cse535_assignment_1.Utils.practiceCount;
import static com.example.cse535_assignment_1.Utils.actions_map;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class RecordGesture extends AppCompatActivity {
    public static String Action;
    private final String LOG_TAG = "RECORD_GESTURE";
    private Uri fileUri;
    String action;

    ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent videoPath = result.getData();
                        fileUri = videoPath.getData();
                        Toast.makeText(getBaseContext(), "Video Recorded Successfully. Path- " + fileUri, Toast.LENGTH_LONG).show();
                        Log.i(LOG_TAG, "Video Recorded Successfully. Path: " + fileUri);
                    } else if (result.getResultCode() == RESULT_CANCELED) {
                        Toast.makeText(getBaseContext(), "Video recording cancelled.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getBaseContext(), "Failed to record video", Toast.LENGTH_LONG).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_gesture);

        Button record = findViewById(R.id.record);
        Button upload = findViewById(R.id.upload);
        Button home = findViewById(R.id.homeBtn);

        //Saving the chosen Gesture action
        Intent i = getIntent();
        action = i.getStringExtra(Action);
        if (!hasCamera()) {
            record.setEnabled(false);
        }

        record.setOnClickListener(view -> startRecording());

        upload.setOnClickListener(view -> {
            System.out.println("Entering Upload");
            System.out.println(actions_map);
            UploadTask up1 = new UploadTask();
            Toast.makeText(getApplicationContext(), "Starting to Upload", Toast.LENGTH_LONG).show();
            if (!actions_map.containsKey(action)) {
                practiceCount.set(1);
//                actions.add(action);
                actions_map.put(action,1);
            }
            else {
                actions_map.put(action, actions_map.get(action) + 1);
                practiceCount.set(actions_map.get(action));
            }
            up1.execute();
            Toast.makeText(RecordGesture.this, "File uploaded successfully", Toast.LENGTH_LONG).show();
            //GO Back To 1st Page
            Intent intent = new Intent(RecordGesture.this, MainActivity.class);
            System.out.println("Going out Upload");
            System.out.println(actions_map);
            startActivity(intent);
        });

        home.setOnClickListener(view -> {
            // Going back to screen-1
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }

    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_ANY);
    }


    public void startRecording() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        //Setting Specific Video Duration
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 3);
        activityResultLaunch.launch(intent);
    }


    public class UploadTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                // Set Flask web-server URL
                String url = "http://192.168.0.10:8085";
                String charset = "UTF-8";
                String group_id = "1";
                String ASUid = "1219367110";
                String accept = "1";

                InputStream videoInputStream = getContentResolver().openInputStream(fileUri);
                String videoFileName = action+ "_" + practiceCount.getAndIncrement() + "_MUKHERJEE.mp4";

                String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
                String CRLF = "\r\n"; // Line separator required by multipart/form-data.

                URLConnection connection;

                connection = new URL(url).openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

                try (
                        OutputStream output = connection.getOutputStream();
                        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true)
                ) {
                    // Send normal accept.
                    writer.append("--").append(boundary).append(CRLF);
                    writer.append("Content-Disposition: form-data; name=\"accept\"").append(CRLF);
                    writer.append("Content-Type: text/plain; charset=").append(charset).append(CRLF);
                    writer.append(CRLF).append(accept).append(CRLF).flush();

                    // Send normal accept.
                    writer.append("--").append(boundary).append(CRLF);
                    writer.append("Content-Disposition: form-data; name=\"id\"").append(CRLF);
                    writer.append("Content-Type: text/plain; charset=").append(charset).append(CRLF);
                    writer.append(CRLF).append(ASUid).append(CRLF).flush();

                    // Send normal accept.
                    writer.append("--").append(boundary).append(CRLF);
                    writer.append("Content-Disposition: form-data; name=\"group_id\"").append(CRLF);
                    writer.append("Content-Type: text/plain; charset=").append(charset).append(CRLF);
                    writer.append(CRLF).append(group_id).append(CRLF).flush();


                    // Send video file.
                    writer.append("--").append(boundary).append(CRLF);
                    writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"").append(videoFileName).append("\"").append(CRLF);
                    writer.append("Content-Type: video/mp4; charset=").append(charset).append(CRLF); // Text file itself must be saved in this charset!
                    writer.append(CRLF).flush();
                    try {
                        byte[] buffer = new byte[1024];
                        int bytesRead = 0;
                        while ((bytesRead = videoInputStream.read(buffer, 0, buffer.length)) >= 0) {
                            output.write(buffer, 0, bytesRead);

                        }
                    } catch (Exception exception) {
                        Log.e(LOG_TAG, String.valueOf(exception));
                        publishProgress(String.valueOf(exception));
                    }

                    output.flush(); // Important before continuing with writer!
                    writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

                    // End of multipart/form-data.
                    writer.append("--").append(boundary).append("--").append(CRLF).flush();
                } catch (IOException e) {
                    Log.e(LOG_TAG, String.valueOf(e));
                }

                // Request is lazily fired whenever you need to obtain information about response.
                int responseCode = ((HttpURLConnection) connection).getResponseCode();

                Log.i(LOG_TAG, "Server video upload response code: " + responseCode); // Should be 200
            } catch (IOException e) {
                Log.e(LOG_TAG, String.valueOf(e));
            }

            return null;
        }


        @Override
        protected void onProgressUpdate(String... text) {
            Toast.makeText(getApplicationContext(), "In Background Task " + text[0], Toast.LENGTH_LONG).show();
        }

    }
}