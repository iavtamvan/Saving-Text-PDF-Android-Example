package com.example.root.saving_pdf_android;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    String cinta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO CODE SOMETING, SOMEONE, SOME etc.
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
