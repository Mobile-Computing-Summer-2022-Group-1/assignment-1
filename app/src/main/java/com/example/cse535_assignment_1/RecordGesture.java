package com.example.cse535_assignment_1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import android.os.Environment;

public class RecordGesture extends AppCompatActivity {
    private Uri fileUri;
    public static String Action;
    private String filesPath, fileName;
    protected HashMap<String, Integer> COUNT = new HashMap<String, Integer>();
    private static final int VIDEO_CAPTURE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_gesture);

        Button record = (Button) findViewById(R.id.record);
        Button upload = (Button) findViewById(R.id.upload);

        if(!hasCamera()){
            record.setEnabled(false);
        }

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecording();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadTask up1 = new UploadTask();
                Toast.makeText(getApplicationContext(),"Stating to Upload",Toast.LENGTH_LONG).show();
                up1.execute();
            }
        });


    }

    private boolean hasCamera() {
        if (getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_ANY)){
            return true;
        } else {
            return false;
        }
    }

    public void startRecording() {
//        File mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GESTURE_PRACTICE_(Practice Number)_USERLASTNAME.mp4");


        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        //Setting Specific Video Duration
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,3);

        // To save file with specific name
//        fileUri = Uri.fromFile(mediaFile);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        activityResultLaunch.launch(intent);
    }

    ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent videoPath = result.getData();
                        Toast.makeText(getBaseContext(), "Path:\n" + videoPath, Toast.LENGTH_LONG).show();
                    } else if (result.getResultCode() == RESULT_CANCELED) {
                        Toast.makeText(getBaseContext(), "Video recording cancelled.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getBaseContext(), "Failed to record video", Toast.LENGTH_LONG).show();
                    }
                }
            });

    public class UploadTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
//                String url = "http://10.218.107.121/cse535/upload_video.php";
                String url = "http://192.168.0.252:8085";
                String charset = "UTF-8";
                String group_id = "1";
                String ASUid = "1219367110";
                String accept = "1";


                File videoFile = new File(Environment.getExternalStorageDirectory()+"/my_folder/Action1.mp4");
                String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
                String CRLF = "\r\n"; // Line separator required by multipart/form-data.

                URLConnection connection;

                connection = new URL(url).openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

                try (
                        OutputStream output = connection.getOutputStream();
                        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
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
                    writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"").append(videoFile.getName()).append("\"").append(CRLF);
                    writer.append("Content-Type: video/mp4; charset=").append(charset).append(CRLF); // Text file itself must be saved in this charset!
                    writer.append(CRLF).flush();
                    FileInputStream vf = new FileInputStream(videoFile);
                    try {
                        byte[] buffer = new byte[1024];
                        int bytesRead = 0;
                        while ((bytesRead = vf.read(buffer, 0, buffer.length)) >= 0)
                        {
                            output.write(buffer, 0, bytesRead);

                        }
                        //   output.close();
                        //Toast.makeText(getApplicationContext(),"Read Done", Toast.LENGTH_LONG).show();
                    }catch (Exception exception)
                    {


                        //Toast.makeText(getApplicationContext(),"output exception in catch....."+ exception + "", Toast.LENGTH_LONG).show();
                        Log.d("Error", String.valueOf(exception));
                        publishProgress(String.valueOf(exception));
                        // output.close();

                    }

                    output.flush(); // Important before continuing with writer!
                    writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.


                    // End of multipart/form-data.
                    writer.append("--").append(boundary).append("--").append(CRLF).flush();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Request is lazily fired whenever you need to obtain information about response.
                int responseCode = ((HttpURLConnection) connection).getResponseCode();
                System.out.println(responseCode); // Should be 200

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onProgressUpdate(String... text) {
            Toast.makeText(getApplicationContext(), "In Background Task " + text[0], Toast.LENGTH_LONG).show();
        }

    }
}