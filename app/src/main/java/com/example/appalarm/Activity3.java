package com.example.appalarm;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import static com.example.appalarm.Activity2.usage_time;

public class Activity3  extends AppCompatActivity implements View.OnClickListener {
    private  TextView countdownTimerText;
    private  EditText minutes;
    private  int minutes1=1;
    private  Button startTimer, resetTimer;
    private  CountDownTimer countDownTimer;
    String hms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        countdownTimerText =  findViewById(R.id.countdownText);
        minutes = findViewById(R.id.enterMinutes);
        startTimer = findViewById(R.id.startTimer);
        resetTimer = findViewById(R.id.resetTimer);
        setListeners();
    }
    private void setListeners() {
        startTimer.setOnClickListener(this);
        resetTimer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startTimer:
                if (countDownTimer == null) {
                    String getMinutes = minutes.getText().toString();
                    if (!getMinutes.equals("") && getMinutes.length() > 0) {
                        int noOfMinutes = Integer.parseInt(getMinutes) * 60 * 1000;
                        startTimer(noOfMinutes);
                        startTimer.setText(getString(R.string.stop_timer));

                    } else
                        Toast.makeText(Activity3.this, "Please enter no. of Minutes.", Toast.LENGTH_SHORT).show();//Display toast if edittext is empty
                } else {
                    stopCountdown();
                    startTimer.setText(getString(R.string.start_timer));
                }
                break;
            case R.id.resetTimer:
                stopCountdown();
                startTimer.setText(getString(R.string.start_timer));
                countdownTimerText.setText(getString(R.string.timer));
                break;
        }
    }
     private void stopCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    private void startTimer(int noOfMinutes) {
        countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
            hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                countdownTimerText.setText(hms);//set text
            }
            public void onCheckboxClicked(View view)
            {

                boolean checked =((CheckBox)view).isChecked();
                switch (view.getId())
                {
                    case R.id.checkbox:
                        if(checked)
                        {
                            if(minutes1==usage_time)
                            {
                                Toast.makeText(Activity3.this, "Time is up", Toast.LENGTH_SHORT).show();
                            }

                        }

                }
            }

            public void onFinish() {

                countdownTimerText.setText("TIME'S UP!!");
                countDownTimer = null;
                startTimer.setText(getString(R.string.start_timer));
            }
        }.start();
    }
}
