package com.example.android.soundapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();
    }

    private void initControls()
    {
        try
        {
            volumeSeekbar = (SeekBar)findViewById(R.id.seekBar);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager
                    .getStreamVolume(AudioManager.STREAM_MUSIC));


            volumeSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
            {
                @Override
                public void onStopTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
                {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void playMusic(View view){
        Button playButton = (Button) findViewById(R.id.button1);
        Toast.makeText(MainActivity.this,"Play", Toast.LENGTH_SHORT).show();


        mediaPlayer = MediaPlayer.create(this, R.raw.fallout);
         mediaPlayer.start();
    }

    public void pauseMusic(View view){
        Button pauseButton = (Button) findViewById(R.id.button2);
        Toast.makeText(MainActivity.this,"Pause", Toast.LENGTH_SHORT).show();

        mediaPlayer.pause();
    }

}
