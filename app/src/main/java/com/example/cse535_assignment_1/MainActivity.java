package com.example.cse535_assignment_1;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    protected Map<String, String> signUrls = new LinkedHashMap<String, String>() {{
        put("Please Choose an Action", "Dummy Value");
        put("ACPower", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAC2q_9gv9Uai__KsL6bFH8qa/ACPower.mp4?raw=1");
        put("Algorithm", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAB5KcvXYJO7jK33i9BGZsT2a/Algorithm.mp4?raw=1");
        put("Antenna", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACDRZKqnY5ZuiwyhTzXwXOva/Antenna.mp4?raw=1");
        put("Authentication", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACDKXA5SSD75Rf3LeRMG1tFa/Authentication.mp4?raw=1");
        put("Authorization", "https://www.dropbox.com/sh/uw4569iupasxuyk/AADfCPTWrrQvRtN7NQZsgxBLa/Authorization.mp4?raw=1");
        put("Bandwidth", "https://www.dropbox.com/sh/uw4569iupasxuyk/AADFSKg2ljCa51Zex1HDXOeza/Bandwidth.mp4?raw=1");
        put("Bluetooth", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAAa-8WCxIx-CMypnPkv2cVja/Bluetooth.mp4?raw=1");
        put("Browser", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAAFrjJSdKC1e256M_fHG-Iia/Browser.mp4?raw=1");
        put("Cloudcomputing", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABx01dDg9ppoljPZJTid9UJa/Cloudcomputing.mp4?raw=1");
        put("DataCompression", "https://www.dropbox.com/sh/uw4569iupasxuyk/AADUJztFtZbN9wrfKB8-xrYca/DataCompression.mp4?raw=1");
        put("DataLinkLayer", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACvsLy-jTVIpbI7AzLHoDUHa/DataLinkLayer.mp4?raw=1");
        put("DataMining", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAD4lWxzTnH7pqqGQA8Q_C2pa/DataMining.mp4?raw=1");
        put("Decryption", "https://www.dropbox.com/sh/uw4569iupasxuyk/AADN7YrJ1rcl4G5rTcdfl3ska/Decryption.mp4?raw=1");
        put("Domain", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAAfMdcXuzAAGInrbl6wbHwba/Domain.mp4?raw=1");
        put("Email", "https://www.dropbox.com/sh/uw4569iupasxuyk/AADymdlRov3X_OB8npSPEMXoa/Email.mp4?raw=1");
        put("Exposure", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABAy9O1K0o7RxeUmOpkDUfBa/Exposure.mp4?raw=1");
        put("Filter", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACrjIECJljMHkWSKXOmrzxda/Filter.mp4?raw=1");
        put("Firewall", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACNl3SddtXcITGCoj-K1lz4a/Firewall.mp4?raw=1");
        put("Flooding", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACd-2IAuH7cAZDnslGixiL-a/Flooding.mp4?raw=1");
        put("Gateway", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAAHB0OEd3qkGnkFzCDiRBD3a/Gateway.mp4?raw=1");
        put("Hacker", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABeodApQRsGY9-gAmKHjQAda/Hacker.mp4?raw=1");
        put("Header", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACzaXVCUp-iRwYDoswLKofxa/Header.mp4?raw=1");
        put("HotSwap", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAAzp8csGFMQ0xfiqF9Anoraa/HotSwap.mp4?raw=1");
        put("Hyperlink", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABgJD-_J48TPjrMs2SjyLApa/Hyperlink.mp4?raw=1");
        put("Infrastructure", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAA5E1INRbAmH2p8JaAD7Vkua/Infrastructure.mp4?raw=1");
        put("Integrity", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACfQNcOaUvl2TJqJfz-1-KWa/Integrity.mp4?raw=1");
        put("Internet", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABr9lJJTMkhSuEPXtNwBlspa/Internet.mp4?raw=1");
        put("Intranet", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABpUDE-tTGP7lVjNfOdHsOla/Intranet.mp4?raw=1");
        put("Latency", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABkjjFNuosP895YHxZF4TBpa/Latency.mp4?raw=1");
        put("Loopback", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAAXKyt0hfrPVLW7tguX6FTWa/Loopback.mp4?raw=1");
        put("Motherboard", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAAnPw7B9M1AeHbVrvHQFL4La/Motherboard.mp4?raw=1");
        put("Network", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACDqJkkY-5wdQW4Ywm1Rmi0a/Network.mp4?raw=1");
        put("Networking", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABGvKDmaGiVL9APCAi8_Wk-a/Networking.mp4?raw=1");
        put("Networklayer", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAASipMMxZnySm8hpKzSLp4Na/Networklayer.mp4?raw=1");
        put("Node", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACDyX0uCOQukL8FsUSHD-zba/Node.mp4?raw=1");
        put("Packet", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABw8dQvkKNwZRL95D1M3l5oa/Packet.mp4?raw=1");
        put("Partition", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABspfIXa0S6TlGN5X1T3ljXa/Partition.mp4?raw=1");
        put("PasswordSniffing", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAD70I-4qpe0ZUbUy34AQcwaa/PasswordSniffing.mp4?raw=1");
        put("Patch", "https://www.dropbox.com/sh/uw4569iupasxuyk/AABfjbLy7HiNB9Mr3uIOH2Vha/Patch.mp4?raw=1");
        put("Phishing", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAB-16-y5G3Xu08nzY4K9n7Ca/Phishing.mp4?raw=1");
        put("PhysicalLayer", "https://www.dropbox.com/sh/uw4569iupasxuyk/AADMO_FV6e9LY_pc5ykSS43La/PhysicalLayer.mp4?raw=1");
        put("Ping", "https://www.dropbox.com/sh/uw4569iupasxuyk/AAA86a02ciUb6wp6KHRuiRcRa/Ping.mp4?raw=1");
        put("Portscan", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACWPwl6wWrBNPZ8dgqW1Fwna/Portscan.mp4?raw=1");
        put("PresentationLayer", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACHNwv1rbnlzYddn23iTU19a/PresentationLayer.mp4?raw=1");
        put("Protocol", "https://www.dropbox.com/sh/uw4569iupasxuyk/AACdBpSkYp0dneMmKUGeUcuga/Protocol.mp4?raw=1");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinner);

        //create a list of items for the spinner.
        String[] items = signUrls.keySet().toArray(new String[0]);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // To get the chosen Option
                if (parent.getItemAtPosition(position).equals("Please Choose an Action")) {
                    Log.d("Checking", "Nothing");
                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    // To get the chosen option Id
                    long Id = parent.getSelectedItemId();

                    // To get the Video URL for Chosen Option
                    String signText = parent.getSelectedItem().toString();
                    String videoUrl = signUrls.get(signText);
                    Intent intent = new Intent(MainActivity.this, VideoDisplay.class);
                    String action_url = item + "&" + videoUrl;
                    System.out.println(action_url);
                    intent.putExtra(VideoDisplay.GESTURE_URL, action_url);
                    startActivity(intent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}