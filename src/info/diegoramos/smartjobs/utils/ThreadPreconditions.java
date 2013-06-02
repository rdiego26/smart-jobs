package info.diegoramos.smartjobs.utils;

import info.diegoramos.smartjobs2.BuildConfig;
import android.os.Looper;

public class ThreadPreconditions {
    public static void checkOnMainThread() {
        if (BuildConfig.DEBUG) {
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                throw new IllegalStateException("This method should be called from the Main Thread");
            }
        }
    }
}