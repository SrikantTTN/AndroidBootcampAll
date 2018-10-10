package com.example.srikant.day7;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service implements ThreadCompleteListener {
    private final String TAG = "MyService";
    private NotifyingThread t1;
    private NotifyingThread t2;
    private NotifyingThread t3;

    @Override
    public void onCreate() {
        t1 = new NotifyingThread(10, 2000);
        t2 = new NotifyingThread(20, 1000);
        t3 = new NotifyingThread(15, 1500);
        t1.addListener(this);
        t2.addListener(this);
        t3.addListener(this);
        t1.start();
        t2.start();
        t3.start();
        super.onCreate();
    }
    int completedThread = 0;
    @Override
    public void notifyOfThreadComplete(Thread thread) {
        Log.d(TAG, "notifyOfThreadComplete: ");
        completedThread++;
        if (completedThread == 3) {
            Intent intent1 = new Intent();
            intent1.setAction("day7.ServiceFinished");
            intent1.putExtra("IamFinished","yes");
            sendBroadcast(intent1);
        }
    }

    public int getValueOfI(int i) {
        switch (i) {
            case 1:
                return t1.getI();
            case 2:
                return t2.getI();
            case 3:
                return t3.getI();
        }
        return -1;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");

    }

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        MyService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MyService.this;
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
