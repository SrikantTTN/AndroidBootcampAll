package com.example.srikant.day7;

import android.util.Log;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class NotifyingThread extends Thread {
    private int i;
    private int off;

    public NotifyingThread(int i, int off) {
        this.i = i;
        this.off = off;
    }

    private final Set<ThreadCompleteListener> listeners
            = new CopyOnWriteArraySet<ThreadCompleteListener>();

    public final void addListener(final ThreadCompleteListener listener) {
        listeners.add(listener);
    }

    public final void removeListener(final ThreadCompleteListener listener) {
        listeners.remove(listener);
    }

    private final void notifyListeners() {
        for (ThreadCompleteListener listener : listeners) {
            listener.notifyOfThreadComplete(this);
        }
    }

    @Override
    public void run() {
        try {
            while (i > 0) {
                try {
                    Thread.sleep(off);
                    i--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
        } finally {
            notifyListeners();
        }
    }

    public int getI() {
        return i;
    }
}

