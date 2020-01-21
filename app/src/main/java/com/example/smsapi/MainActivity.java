package com.example.smsapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int timeout = 3000; // make the activity visible for 3 seconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                finish();
                Intent homepage = new Intent(MainActivity.this,Home.class);

                startActivity(homepage);
            }
        }, timeout);
    }


}
