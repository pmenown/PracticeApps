package com.example.android.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.myapplication.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void printToLogs(View view) {
        // Find first menu item TextView and print the text to the logs
        TextView firstMenu = (TextView) findViewById(R.id.menu_item_1);
        String firstMenuText = firstMenu.getText().toString();
        Log.i("MainActivity.java", firstMenuText);

        // Find second menu item TextView and print the text to the logs
        TextView secondMenu = (TextView) findViewById(R.id.menu_item_2);
        String secondMenuText = secondMenu.getText().toString();
        Log.i("MainActivity.java", secondMenuText);

        // Find third menu item TextView and print the text to the logs
        TextView thirdMenu = (TextView) findViewById(R.id.menu_item_3);
        String thirdMenuText = thirdMenu.getText().toString();
        Log.i("MainActivity.java", thirdMenuText);
    }
}