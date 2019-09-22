package com.arjun.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView time;
    MediaPlayer horn;
    SeekBar timeSeekBar;


    public void startTimer(View view){

        timeSeekBar.setVisibility(View.INVISIBLE);

        horn=MediaPlayer.create(this, R.raw.airhorn);

        new CountDownTimer(timeSeekBar.getProgress()*1000, 1000) {

            public void onTick(long millisecondsUntilDone){

                //Coundown is counting down(every second)
                String mins="",secs="";
                mins=Integer.toString((int)(millisecondsUntilDone/1000)/60);
                secs=Integer.toString((int)(millisecondsUntilDone/1000)%60);
                time.setText(mins+":"+secs);


                Log.i("Seconds left", String.valueOf(millisecondsUntilDone/1000));

            }
            public void onFinish(){

                //Countdown finishes(after 10 seconds)
                horn.start();
                Log.i("Done!","Countdown timer finished");

            }

        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        time=(TextView) findViewById(R.id.time);

        timeSeekBar=(SeekBar)findViewById(R.id.timeSeekBar);
        timeSeekBar.setMax(600);
        timeSeekBar.setProgress(0);

        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                Log.i("Time",Integer.toString(i)+"secs");
                time.setText((Integer.toString(i/60))+":"+(Integer.toString(i%60)));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
