package com.example.videotalk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DashboardActivity extends AppCompatActivity {
    private EditText codeBox;
    private Button btnJoin, btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        codeBox = findViewById(R.id.codeBox);
        btnJoin = findViewById(R.id.join);
        btnShare = findViewById(R.id.btn_share);
        URL serverUrl ;

        try {
            serverUrl = new URL("https://meet.jit.si");

            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverUrl)
                            .setFeatureFlag("welcomePage.enabled", false)
                            .setAudioMuted(true)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                         .setRoom(codeBox.getText().toString())
//                         .setFeatureFlag("welcomePage.enabled", false)
                         .build();

                JitsiMeetActivity.launch(DashboardActivity.this, options);

            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent shareCode = new Intent(Intent.ACTION_SEND);
//                shareCode.putExtra()
            }
        });


}
}
