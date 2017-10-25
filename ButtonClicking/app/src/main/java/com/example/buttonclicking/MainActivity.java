package com.example.buttonclicking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = (Button)findViewById(R.id.button1);

        button.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        TextView buttonText = (TextView)findViewById(R.id.textView1);
                        buttonText.setText("Good job Phil");

                       }
                }
        );
        button.setOnLongClickListener(
                new Button.OnLongClickListener(){
                    public int onLongClick(View v) {
                        TextView buttonText = (TextView)findViewById(R.id.textView1);
                        buttonText.setText(R.string.app_name);
                        return true;

                    }
                }
        );
    }
}
