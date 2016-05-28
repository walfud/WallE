package com.walfud.walle.widget;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.walfud.walle.WallE;

/**
 * Created by walfud on 2016/2/15.
 */
public class HardwareUtils {

    public static final String TAG = "HardwareUtils";

    public static String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) WallE.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
}
