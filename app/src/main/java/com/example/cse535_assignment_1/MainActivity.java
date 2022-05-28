package com.example.cse535_assignment_1;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

public class MainActivity extends AppCompatActivity {


    private static final int VIDEO_CAPTURE = 101;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected HashMap<String, String> signUrls = new HashMap<String, String>() {{
        put("ACPower.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAC2q_9gv9Uai__KsL6bFH8qa/ACPower.mp4?raw=1");
        put("Algorithm.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAB5KcvXYJO7jK33i9BGZsT2a/Algorithm.mp4?raw=1");
        put("Antenna.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACDRZKqnY5ZuiwyhTzXwXOva/Antenna.mp4?raw=1");
        put("Authentication.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACDKXA5SSD75Rf3LeRMG1tFa/Authentication.mp4?raw=1");
        put("Authorization.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AADfCPTWrrQvRtN7NQZsgxBLa/Authorization.mp4?raw=1");
        put("Bandwidth.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AADFSKg2ljCa51Zex1HDXOeza/Bandwidth.mp4?raw=1");
        put("Bluetooth.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAAa-8WCxIx-CMypnPkv2cVja/Bluetooth.mp4?raw=1");
        put("Browser.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAAFrjJSdKC1e256M_fHG-Iia/Browser.mp4?raw=1");
        put("Cloudcomputing.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABx01dDg9ppoljPZJTid9UJa/Cloudcomputing.mp4?raw=1");
        put("DataCompression.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AADUJztFtZbN9wrfKB8-xrYca/DataCompression.mp4?raw=1");
        put("DataLinkLayer.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACvsLy-jTVIpbI7AzLHoDUHa/DataLinkLayer.mp4?raw=1");
        put("DataMining.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAD4lWxzTnH7pqqGQA8Q_C2pa/DataMining.mp4?raw=1");
        put("Decryption.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AADN7YrJ1rcl4G5rTcdfl3ska/Decryption.mp4?raw=1");
        put("Domain.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAAfMdcXuzAAGInrbl6wbHwba/Domain.mp4?raw=1");
        put("Email.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AADymdlRov3X_OB8npSPEMXoa/Email.mp4?raw=1");
        put("Exposure.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABAy9O1K0o7RxeUmOpkDUfBa/Exposure.mp4?raw=1");
        put("Filter.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACrjIECJljMHkWSKXOmrzxda/Filter.mp4?raw=1");
        put("Firewall.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACNl3SddtXcITGCoj-K1lz4a/Firewall.mp4?raw=1");
        put("Flooding.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACd-2IAuH7cAZDnslGixiL-a/Flooding.mp4?raw=1");
        put("Gateway.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAAHB0OEd3qkGnkFzCDiRBD3a/Gateway.mp4?raw=1");
        put("Hacker.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABeodApQRsGY9-gAmKHjQAda/Hacker.mp4?raw=1");
        put("Header.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACzaXVCUp-iRwYDoswLKofxa/Header.mp4?raw=1");
        put("HotSwap.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAAzp8csGFMQ0xfiqF9Anoraa/HotSwap.mp4?raw=1");
        put("Hyperlink.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABgJD-_J48TPjrMs2SjyLApa/Hyperlink.mp4?raw=1");
        put("Infrastructure.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAA5E1INRbAmH2p8JaAD7Vkua/Infrastructure.mp4?raw=1");
        put("Integrity.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACfQNcOaUvl2TJqJfz-1-KWa/Integrity.mp4?raw=1");
        put("Internet.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABr9lJJTMkhSuEPXtNwBlspa/Internet.mp4?raw=1");
        put("Intranet.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABpUDE-tTGP7lVjNfOdHsOla/Intranet.mp4?raw=1");
        put("Latency.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABkjjFNuosP895YHxZF4TBpa/Latency.mp4?raw=1");
        put("Loopback.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAAXKyt0hfrPVLW7tguX6FTWa/Loopback.mp4?raw=1");
        put("Motherboard.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAAnPw7B9M1AeHbVrvHQFL4La/Motherboard.mp4?raw=1");
        put("Network.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACDqJkkY-5wdQW4Ywm1Rmi0a/Network.mp4?raw=1");
        put("Networking.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABGvKDmaGiVL9APCAi8_Wk-a/Networking.mp4?raw=1");
        put("Networklayer.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAASipMMxZnySm8hpKzSLp4Na/Networklayer.mp4?raw=1");
        put("Node.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACDyX0uCOQukL8FsUSHD-zba/Node.mp4?raw=1");
        put("Packet.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABw8dQvkKNwZRL95D1M3l5oa/Packet.mp4?raw=1");
        put("Partition.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABspfIXa0S6TlGN5X1T3ljXa/Partition.mp4?raw=1");
        put("PasswordSniffing.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAD70I-4qpe0ZUbUy34AQcwaa/PasswordSniffing.mp4?raw=1");
        put("Patch.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABfjbLy7HiNB9Mr3uIOH2Vha/Patch.mp4?raw=1");
        put("Phishing.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAB-16-y5G3Xu08nzY4K9n7Ca/Phishing.mp4?raw=1");
        put("PhysicalLayer.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AADMO_FV6e9LY_pc5ykSS43La/PhysicalLayer.mp4?raw=1");
        put("Ping.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAA86a02ciUb6wp6KHRuiRcRa/Ping.mp4?raw=1");
        put("Portscan.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACWPwl6wWrBNPZ8dgqW1Fwna/Portscan.mp4?raw=1");
        put("PresentationLayer.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACHNwv1rbnlzYddn23iTU19a/PresentationLayer.mp4?raw=1");
        put("Protocol.mp4", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACdBpSkYp0dneMmKUGeUcuga/Protocol.mp4?raw=1");
    }};
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//
//        locationManager.requestLocationUpdates(NETWORK_PROVIDER, 0, 0, this);
//        Button bt5 = findViewById(R.id.button5);
//        bt5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                        && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//                Location location = locationManager.getLastKnownLocation(GPS_PROVIDER);
//                Toast.makeText(getApplicationContext(), "Longitude: " + location.getLongitude() + "Latitude: " + location.getLatitude(), Toast.LENGTH_LONG).show();
//            }
//        });


        Button bt1 = findViewById(R.id.button);
        Log.println(1, "Test tag", "Record button declared");
        if (!hasCamera()) {
            bt1.setEnabled(false);
        }

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecording();
            }
        });


        Button bt3 = findViewById(R.id.button3);

        bt3.setEnabled(false);

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoView vv2 = findViewById(R.id.videoView);
                vv2.start();
            }
        });

        Button bt4 = findViewById(R.id.button4);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadTask up1 = new UploadTask();
                Toast.makeText(getApplicationContext(), "Stating to Upload", Toast.LENGTH_LONG).show();
                up1.execute();
            }
        });


        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items = signUrls.keySet().toArray(new String[0]);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        Button downloadBtn = findViewById(R.id.download1);

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadTask dw1 = new DownloadTask();
                Toast.makeText(getApplicationContext(), "Running Background Task", Toast.LENGTH_LONG).show();
                dw1.execute();

            }
        });
    }


//    @Override
//    public void onLocationChanged(Location location) {
//        Toast.makeText(getApplicationContext(), "Longitude: " + location.getLongitude() + " Latitude: " + location.getLatitude(), Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }

    public void startRecording() {
        File mediaFile = new
                File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/myvideo.mp4");


        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
        fileUri = Uri.fromFile(mediaFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, VIDEO_CAPTURE);
    }

    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_ANY);
    }

    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {

        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video has been saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public class UploadTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {

                String url = "http://10.218.107.121/cse535/upload_video.php";
                String charset = "UTF-8";
                String group_id = "40";
                String ASUid = "1200072576";
                String accept = "1";


                File videoFile = new File(Environment.getExternalStorageDirectory() + "/my_folder/Action1.mp4");
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
                    writer.append("--" + boundary).append(CRLF);
                    writer.append("Content-Disposition: form-data; name=\"accept\"").append(CRLF);
                    writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
                    writer.append(CRLF).append(accept).append(CRLF).flush();

                    // Send normal accept.
                    writer.append("--" + boundary).append(CRLF);
                    writer.append("Content-Disposition: form-data; name=\"id\"").append(CRLF);
                    writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
                    writer.append(CRLF).append(ASUid).append(CRLF).flush();

                    // Send normal accept.
                    writer.append("--" + boundary).append(CRLF);
                    writer.append("Content-Disposition: form-data; name=\"group_id\"").append(CRLF);
                    writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
                    writer.append(CRLF).append(group_id).append(CRLF).flush();


                    // Send video file.
                    writer.append("--" + boundary).append(CRLF);
                    writer.append("Content-Disposition: form-data; name=\"uploaded_file\"; filename=\"" + videoFile.getName() + "\"").append(CRLF);
                    writer.append("Content-Type: video/mp4; charset=" + charset).append(CRLF); // Text file itself must be saved in this charset!
                    writer.append(CRLF).flush();
                    FileInputStream vf = new FileInputStream(videoFile);
                    try {
                        byte[] buffer = new byte[1024];
                        int bytesRead = 0;
                        while ((bytesRead = vf.read(buffer, 0, buffer.length)) >= 0) {
                            output.write(buffer, 0, bytesRead);

                        }
                        //   output.close();
                        //Toast.makeText(getApplicationContext(),"Read Done", Toast.LENGTH_LONG).show();
                    } catch (Exception exception) {


                        //Toast.makeText(getApplicationContext(),"output exception in catch....."+ exception + "", Toast.LENGTH_LONG).show();
                        Log.d("Error", String.valueOf(exception));
                        publishProgress(String.valueOf(exception));
                        // output.close();

                    }

                    output.flush(); // Important before continuing with writer!
                    writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.


                    // End of multipart/form-data.
                    writer.append("--" + boundary + "--").append(CRLF).flush();
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

    public class DownloadTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "Starting to execute Background Task", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... text) {

            File SDCardRoot = Environment.getExternalStorageDirectory(); // location where you want to store
            File directory = new File(SDCardRoot, "/my_folder/"); //create directory to keep your downloaded file
            if (!directory.exists()) {
                directory.mkdir();
            }
            //publishProgress();
//            Toast.makeText(getApplicationContext(),"In Background Task", Toast.LENGTH_LONG).show();
            String fileName = "Action1" + ".mp4"; //song name that will be stored in your device in case of song
            //String fileName = "myImage" + ".jpeg"; in case of image
            try {
                InputStream input = null;
                try {
                    Spinner signSpinner = (Spinner) findViewById(R.id.spinner1);
                    String signText = signSpinner.getSelectedItem().toString();
                    String videoUrl = signUrls.get(signText);

//                    URL url = new URL("https://www.signingsavvy.com/media/mp4-ld/7/7231.mp4"); // link of the song which you want to download like (http://...)
//                    URL url = new URL("https://www.dropbox.com/sh/uw4569iupasxuyk/AAC2q_9gv9Uai__KsL6bFH8qa/ACPower.mp4?raw=1"); // link of the song which you want to download like (http://...)
                    URL url = new URL(videoUrl);
                    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
//                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setReadTimeout(95 * 1000);
                    urlConnection.setConnectTimeout(95 * 1000);
                    urlConnection.setDoInput(true);
                    urlConnection.setRequestProperty("Accept", "application/json");
                    urlConnection.setRequestProperty("X-Environment", "android");


                    urlConnection.setHostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            /** if it necessarry get url verfication */
                            //return HttpsURLConnection.getDefaultHostnameVerifier().verify("your_domain.com", session);
                            return true;
                        }
                    });
                    urlConnection.setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());


                    urlConnection.connect();
                    input = urlConnection.getInputStream();
                    //input = url.openStream();
                    OutputStream output = new FileOutputStream(new File(directory, fileName));

                    try {
                        byte[] buffer = new byte[1024];
                        int bytesRead = 0;
                        while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0) {
                            output.write(buffer, 0, bytesRead);

                        }
                        output.close();
                        //Toast.makeText(getApplicationContext(),"Read Done", Toast.LENGTH_LONG).show();
                    } catch (Exception exception) {


                        //Toast.makeText(getApplicationContext(),"output exception in catch....."+ exception + "", Toast.LENGTH_LONG).show();
                        Log.d("Error", String.valueOf(exception));
                        publishProgress(String.valueOf(exception));
                        output.close();

                    }
                } catch (Exception exception) {

                    //Toast.makeText(getApplicationContext(), "input exception in catch....."+ exception + "", Toast.LENGTH_LONG).show();
                    publishProgress(String.valueOf(exception));

                } finally {
                    input.close();
                }
            } catch (Exception exception) {
                publishProgress(String.valueOf(exception));
            }

            return "true";
        }


        @Override
        protected void onProgressUpdate(String... text) {
            Toast.makeText(getApplicationContext(), "In Background Task" + text[0], Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(String text) {
            VideoView vv = findViewById(R.id.videoView);
            vv.setVideoPath(Environment.getExternalStorageDirectory() + "/my_folder/Action1.mp4");
            vv.start();
            Button bt4 = findViewById(R.id.button3);
            bt4.setEnabled(true);
        }
    }
}