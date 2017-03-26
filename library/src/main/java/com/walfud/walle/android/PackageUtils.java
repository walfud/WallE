package com.walfud.walle.android;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.walfud.walle.WallE;

import java.io.File;

/**
 * Created by walfud on 2016/7/8.
 */
public class PackageUtils {
    public static final String TAG = "PackageUtils";

    public static final String PKG_NAME_WECHAT = "com.tencent.mm";
    public static final String PKG_NAME_QQ = "com.tencent.mobileqq";
    public static final String PKG_NAME_SINA = "com.sina.weibo";

    // Version code
    public static int getVersionCode() {
        return getVersionCode(WallE.getContext().getPackageName());
    }
    public static int getVersionCode(String packageName) {
        return getPackageInfo(packageName, new IPackageInfoGetter<Integer>() {
            @Override
            public Integer get(PackageInfo packageInfo) {
                return packageInfo.versionCode;
            }
        });
    }

    // Version name
    public static String getVersionName() {
        return getVersionName(WallE.getContext().getPackageName());
    }
    public static String getVersionName(String packageName) {
        return getPackageInfo(packageName, new IPackageInfoGetter<String>() {
            @Override
            public String get(PackageInfo packageInfo) {
                return packageInfo.versionName;
            }
        });
    }

    private static <T> T getPackageInfo(String packageName, IPackageInfoGetter<T> getter) {
        PackageManager packageManager = WallE.getContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return getter.get(packageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean isPackageInstalled(String packageName) {
        try {
            WallE.getContext().getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void installPackage(File apk) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(apk), "application/vnd.android.package-archive");
        WallE.getContext().startActivity(intent);
    }

    //
    private interface IPackageInfoGetter<T> {
        T get(PackageInfo packageInfo);
    }
}
