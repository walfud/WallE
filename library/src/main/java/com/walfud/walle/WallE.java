package com.walfud.walle;

import android.content.Context;

/**
 * 'WallE' comes from science-fiction comedy film <a href="https://en.wikipedia.org/wiki/WALL-E">&lt;&lt;Wall-E&gt;&gt;</a>. He is brave and hard-working. Please
 * let him to do initialization before using.
 *
 * Created by walfud on 2015/10/18.
 */
public class WallE {

    public static final String TAG = "WallE";

    /**
     * Should be application context
     */
    private static Context sContext;

    /**
     *
     * @param context
     * @return
     */
    public static final boolean initialize(Context context) {
        sContext = context.getApplicationContext();

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
