package com.example.android.audiorecordtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start (View v){
        Intent numbersIntent = new Intent(this, AudioRecordTest.class);
        startActivity(numbersIntent);
    }
}
