package com.walfud.walle.android;

import android.content.Context;
import android.content.pm.PackageManager;

import com.walfud.walle.WallE;

/**
 * Created by walfud on 2016/7/7.
 */
public class PermissionUtils {
    public static final String TAG = "PermissionUtils";

    public static boolean hasPermission(String permName) {
        Context appContext = WallE.getContext();

        PackageManager packageManager = appContext.getPackageManager();
        return packageManager.checkPermission(permName, appContext.getPackageName()) == PackageManager.PERMISSION_GRANTED;
    }
}
