package com.walfud.walle.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.walfud.walle.lang.StrongReference;

/**
 * Created by walfud on 2017/4/17.
 */

public class Etc {
    private static Handler sHandler;

    public static void initialize(Context context) {
        sHandler = new Handler();
    }

    public interface Func<T> {
        T call(Object... args);
    }
    public static <T> void runOnUiThread(Func<T> func, StrongReference<T> rtn, Object... args) {
        Runnable runnable = () -> {
            T t = func.call(args);
            if (rtn != null) {
                rtn.set(t);
            }
        };

        if (Looper.getMainLooper().isCurrentThread()) {
            runnable.run();
        } else {
            sHandler.post(runnable);
        }
    }
}
