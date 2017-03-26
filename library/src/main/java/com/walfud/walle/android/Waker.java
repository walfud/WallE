package com.walfud.walle.android;

import android.content.Context;
import android.os.PowerManager;

import com.walfud.walle.WallE;

/**
 * Created by walfud on 2015/9/8.
 */
public class Waker {

    public static final String TAG = "Waker";

    private Context mContext;
    private PowerManager.WakeLock mWakeLock;

    public Waker() {
        mContext = WallE.getContext();
    }

    public void acquire() {
        if (mWakeLock == null) {
            PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "");
            if (null != mWakeLock) {
                mWakeLock.acquire();
            }
        }
    }

    private void release() {
        if (null != mWakeLock) {
            mWakeLock.release();
            mWakeLock = null;
        }
    }
}
