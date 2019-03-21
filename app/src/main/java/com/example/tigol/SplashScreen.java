package com.example.tigol;

import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    public static int splash_timer_out = 3000;
    private FirebaseAuth frAuth;
    private Intent splashIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //firebase
        frAuth = FirebaseAuth.getInstance();

        //autologin / login kalo g ada akun yang masuk
        if (frAuth.getCurrentUser() != null) {
            splashIntent = new Intent(SplashScreen.this, MainActivity.class);
        } else {
            splashIntent = new Intent(SplashScreen.this, LoginPage.class);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(splashIntent);
                finish();
            }
        }, splash_timer_out);
    }
}