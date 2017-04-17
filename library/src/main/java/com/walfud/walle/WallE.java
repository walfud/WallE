package com.walfud.walle;

import android.content.Context;

import com.walfud.walle.android.Etc;

/**
 * Created by walfud on 2016/7/7.
 */
public class WallE {
    public static final String TAG = "WallE";

    /**
     * Should be application context
     */
    private static Context sContext;

    /**
     *  主线程调用
     */
    public static final boolean initialize(Context context) {
        sContext = context.getApplicationContext();

        Etc.initialize(context);

        return true;
    }

    /**
     *
     * @return Application context
     */
    public static Context getContext() {
        return sContext;
    }
}
