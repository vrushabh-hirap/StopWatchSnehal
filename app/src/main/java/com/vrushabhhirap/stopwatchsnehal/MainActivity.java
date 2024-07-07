package com.vrushabhhirap.stopwatchsnehal;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int second = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            second = savedInstanceState.getInt("second");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    private void runTimer() {
        final TextView timeView = findViewById(R.id.time_View);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second / 3600;
                int minutes = (second % 3600) / 60;
                int sec = second % 60;

                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, sec);

                timeView.setText(time);
                if (running) {
                    Log.d("Stopwatch", "Incrementing seconds: " + second);
                    second++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    public void onclickStart(View view) {
        running = true;
        Log.d("Stopwatch", "Started");
    }

    public void onclickStop(View view) {
        running = false;
        Log.d("Stopwatch", "Stopped");
    }

    public void onclickStopReset(View view) {
        running = false;
        second = 0;
        Log.d("Stopwatch", "Reset");
    }
}
