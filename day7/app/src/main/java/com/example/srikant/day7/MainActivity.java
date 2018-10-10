package com.example.srikant.day7;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView displayState;
    private EditText getStateOf;
    private MyService mService;
    private Boolean mBound;
    Button b1;
    IntentFilter serviceStateIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button);
        Button b2 = findViewById(R.id.button2);
        displayState = findViewById(R.id.textView);
        getStateOf = findViewById(R.id.editText);
        serviceStateIntentFilter = new IntentFilter("day7.ServiceFinished");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MyService.class);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                b1.setEnabled(false);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueOfWhichTimerS = getStateOf.getText().toString();
                if (!valueOfWhichTimerS.isEmpty()) {
                    if (mBound) {
                        int valueOfWhichTimer = Integer.parseInt(valueOfWhichTimerS);
                        int iteration = mService.getValueOfI(valueOfWhichTimer);
                        if (iteration == 0) {
                            displayState.setText(" Your thread Completed");
                        } else if (iteration == -1) {
                            displayState.setText("Invalid Input");
                        } else {
                            displayState.setText(" Your thead " + valueOfWhichTimerS + " is at " + iteration);
                        }
                    } else {
                    Toast.makeText(MainActivity.this, "Service Not Connected", Toast.LENGTH_SHORT).show();
                }
            }else
            {
                getStateOf.setError("Empty");
            }
        }
    });
}
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(serviceFinishListener, serviceStateIntentFilter);
    }

    private BroadcastReceiver serviceFinishListener = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String status = intent.getStringExtra("IamFinished");

            if (status != null && status.equals("yes")) {
                displayState.setText("All Threads are finished");
                b1.setEnabled(true);
                unbindService(mConnection);
                mBound = false;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(serviceFinishListener);
        unbindService(mConnection);
        mBound = false;
    }
}
