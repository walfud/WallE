package com.walfud.walle.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.walfud.walle.WallE;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * Created by walfud on 2016/7/7.
 */
public class DeviceInfo {
    public static final String TAG = "DeviceInfo";

    /**
     * @return 返回 "192.168.1.1" 或者 "3FFE:FFFF:0:CD30:0:0:0:0". 获取不到则返回 `null`
     */
    public static String getIpAddress() {
        if (!PermissionUtils.hasPermission("android.permission.INTERNET")) {
            return null;
        }

        try {
            List<NetworkInterface> networkInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkInterface : networkInterfaces) {
                List<InetAddress> inetAddresses = Collections.list(networkInterface.getInetAddresses());
                for (InetAddress inetAddress : inetAddresses) {
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();

                        if (!hostAddress.contains(":")) {
                            // Ipv4, just use it
                        } else {
                            // Ipv6
                            // Handles such as 'fe80::7c0e:c6ff:fe8d:1e34%dummy0'
                            int pos = hostAddress.indexOf('%');
                            if (pos != -1) {
                                hostAddress = hostAddress.substring(0, pos);
                            }
                        }
                        return hostAddress;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @return 失败返回 null
     * TODO: 此方法在 6.0 上会获取不到正确信息. 目前还没有太好方法
     */
    public static String getMacAddress() {
        if (!PermissionUtils.hasPermission("android.permission.ACCESS_WIFI_STATE")) {
            return null;
        }

        WifiManager wifiManager = (WifiManager) WallE.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            WifiInfo wInfo = wifiManager.getConnectionInfo();
            if (wInfo != null) {
                return wInfo.getMacAddress();
            }
        }

        return null;
    }

    /**
     * @return 失败返回 null
     */
    public static String getImei() {
        if (!PermissionUtils.hasPermission("android.permission.READ_PHONE_STATE")) {
            return null;
        }

        TelephonyManager telephonyManager = (TelephonyManager) WallE.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            return telephonyManager.getDeviceId();
        }
        return null;
    }

    public static String getAndroidId() {
        return Settings.Secure.getString(WallE.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    // Network
    public static final int NETWORK_NONE    = 0;
    public static final int NETWORK_WIFI    = 1;
    public static final int NETWORK_2G      = 2;
    public static final int NETWORK_3G      = 3;
    public static final int NETWORK_4G      = 4;
    public static final int NETWORK_5G      = 5;

    /**
     * 返回当前联网类型
     * @return 0: 失败, 2: 2G, 3: 3G, 4: 4G
     * @see {@link #NETWORK_NONE}
     * @see {@link #NETWORK_WIFI}
     * @see {@link #NETWORK_2G}
     * @see {@link #NETWORK_3G}
     * @see {@link #NETWORK_4G}
     * @see {@link #NETWORK_5G}
     * @see <a href="http://stackoverflow.com/questions/9283765/how-to-determine-if-network-type-is-2g-3g-or-4g">How to determine if network type is 2G, 3G or 4G (Stackoverflow)</a>
     */
    public static boolean isNetworkAvailable() {
        return getNetworkType() != NETWORK_NONE;
    }
    public static boolean isWifiConnected() {
        return getNetworkType() == NETWORK_WIFI;
    }
    public static boolean isMobileConnected() {
        return getNetworkType() == NETWORK_2G
                || getNetworkType() == NETWORK_3G
                || getNetworkType() == NETWORK_4G
                || getNetworkType() == NETWORK_5G;
    }
    public static int getNetworkType() {
        if (!PermissionUtils.hasPermission("android.permission.ACCESS_NETWORK_STATE")) {
            return NETWORK_NONE;
        }

        ConnectivityManager connMgr = (ConnectivityManager) WallE.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null
                || !networkInfo.isAvailable()
                || !networkInfo.isConnected()) {
            return NETWORK_NONE;
        }

        switch (networkInfo.getType()) {
            case ConnectivityManager.TYPE_WIFI:
                return NETWORK_WIFI;

            case ConnectivityManager.TYPE_MOBILE: {
                TelephonyManager mTelephonyManager = (TelephonyManager) WallE.getContext().getSystemService(Context.TELEPHONY_SERVICE);
                int networkType = mTelephonyManager.getNetworkType();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        return NETWORK_4G;

                    default:
                        return NETWORK_NONE;
                }
            }

            default:
                return NETWORK_NONE;
        }
    }
    public static String getNetworkTypeStr() {
        String netType = "MOBILE";

        ConnectivityManager cm = (ConnectivityManager) WallE.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null) {
            netType = networkInfo.getTypeName();
        }

        return netType;
    }
}
