package com.example.videotalk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                    finish();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                finally {
                    startActivity(new Intent(SplashScreenActivity.this, SignIn_Activity.class));
                }
            }
        };
        thread.start();

//        Below code also usable for Splash Screen but it has an Exception the activity did not finish  after intent

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(MainActivity.this, SignIn_Activity.class));
//
//            }
//        },1000);
//        finish();
    }
}