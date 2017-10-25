package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;

import static com.example.myapplication.R.string.ClickText;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private TextView philMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        philMessage = (TextView) findViewById(R.id.philMessage);
        this.gestureDetector = new GestureDetectorCompat(this, this);
        gestureDetector.setOnDoubleTapListener(this);


        Button button = (Button) findViewById(R.id.buttonID);

        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        TextView buttonText = (TextView) findViewById(R.id.philMessage);
                        philMessage.setText(ClickText);

                    }
                }

        );
        button.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        philMessage.setText(R.string.app_name);
                        return true;
                    }
                }
        );
    }

    ///Begin Gestures
    private GestureDetectorCompat gestureDetector;


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        philMessage.setText("onSingleTapConfirmed");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        philMessage.setText("onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        philMessage.setText("onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        philMessage.setText("onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        philMessage.setText("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        philMessage.setText("onSingleTapUp");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        philMessage.setText("onScroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        philMessage.setText("onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        philMessage.setText("onFling");
        return true;
    }


/* /End Gestures */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}