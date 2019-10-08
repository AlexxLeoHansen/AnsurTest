package com.example.joseba.ansurtest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Thumbnails and show them up.
        CustomAsync.execute(new Runnable() {
            @Override
            public void run() {
                new CustomAsync(MainActivity.this).execute();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


}
