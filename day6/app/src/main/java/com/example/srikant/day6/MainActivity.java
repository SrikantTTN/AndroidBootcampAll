package com.example.srikant.day6;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.manager);
        Button btn2 = findViewById(R.id.scheduled);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleJob();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });
    }

    public void scheduleJob() {
        ComponentName componentName = new ComponentName(this, MyJobService.class);
        JobInfo info = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            info = new JobInfo.Builder(100, componentName)
                    .setPeriodic(15*60*1000)
                    .build();

            JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            int resultCode = scheduler.schedule(info);
            if (resultCode == JobScheduler.RESULT_SUCCESS) {
                Log.d("MainActivity", "Job scheduled");
            } else {
                Log.d("MainActivity", "Job scheduling failed");
            }
        }
    }

    public void cancelJob() {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                scheduler.cancel(100);
                Log.d("MainActivity", "Job cancelled");
            }
    }

    private void setAlarm(){
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //intent to open the broadcast receiver
        Intent intent = new Intent(this, AlarmReceiver.class);

        //pending intent to be passed in alarm manager
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        //logic to call the alarm to be run 5 mintues starting 1 min from event
        am.setRepeating(AlarmManager.RTC, SystemClock.elapsedRealtime() + 60*1000, 5*60*1000, pendingIntent);
        Toast.makeText(this, "Alarm set", Toast.LENGTH_SHORT).show();
    }

    }
