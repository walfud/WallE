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

    public interface FuncVoid {
        void call(Object... args);
    }
    public static void runOnUiThread(FuncVoid func, Object... args) {
        runOnUiThread(args2 -> {
            func.call(args2);
            return null;
        }, new StrongReference<>(null), args);
    }
    public interface Func<T> {
        T call(Object... args);
    }
    public static <T> T runOnUiThread(Func<T> func, StrongReference<T> rtn, Object... args) {
        if (Looper.getMainLooper().isCurrentThread()) {
            T t = func.call(args);
            if (rtn != null) {
                rtn.set(t);
            }
            return t;
        } else {
            sHandler.post(() -> {
                T t = func.call(args);
                if (rtn != null) {
                    rtn.set(t);
                }
            });
            return null;
        }
    }
}
