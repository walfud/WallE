package com.walfud.walle.debug;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Debug;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.walfud.walle.Transformer;
import com.walfud.walle.WallE;
import com.walfud.walle.algorithm.Comparator;
import com.walfud.walle.collection.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by walfud on 2015/12/18.
 */
public class DebugUtils {

    public static final String TAG = "DebugUtils";

    public static DebugInfo dump() {
        Context context = WallE.getContext();

        //
//        PowerManager powerManager = (PowerManager) context.getSystemService(context.POWER_SERVICE);
//        WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//        AccountManager accountManager = (AccountManager) context.getSystemService(context.ACCOUNT_SERVICE);
        ActivityManager activityManager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
//        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService(context.ACCESSIBILITY_SERVICE);
//        CaptioningManager captioningManager = (CaptioningManager) context.getSystemService(context.CAPTIONING_SERVICE);
//        context.getSystemService(context.KEYGUARD_SERVICE);
//        LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
////        context.getSystemService(context.COUNTRY_DETECTOR);
//        SearchManager searchManager = (SearchManager) context.getSystemService(context.SEARCH_SERVICE);
//        SensorManager sensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
//        StorageManager storageManager = (StorageManager) context.getSystemService(context.STORAGE_SERVICE);
//        WallpaperManager wallpaperManager = (WallpaperManager) context.getSystemService(context.WALLPAPER_SERVICE);
//        Vibrator vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
////        context.getSystemService(context.STATUS_BAR_SERVICE);
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
////        context.getSystemService(context.UPDATE_LOCK_SERVICE);
////        context.getSystemService(context.NETWORKMANAGEMENT_SERVICE);
//        NetworkStatsManager networkStatsManager = (NetworkStatsManager) context.getSystemService(context.NETWORK_STATS_SERVICE);
////        context.getSystemService(context.NETWORK_POLICY_SERVICE);
//        WifiManager wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
////        context.getSystemService(context.WIFI_PASSPOINT_SERVICE);
//        WifiP2pManager wifiP2pManager = (WifiP2pManager) context.getSystemService(context.WIFI_P2P_SERVICE);
////        context.getSystemService(context.WIFI_SCANNING_SERVICE);
////        context.getSystemService(context.WIFI_RTT_SERVICE);
////        context.getSystemService(context.ETHERNET_SERVICE);
//        NsdManager nsdManager = (NsdManager) context.getSystemService(context.NSD_SERVICE);
//        AudioManager audioManager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
//        FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService(context.FINGERPRINT_SERVICE);
//        MediaRouter mediaRouter = (MediaRouter) context.getSystemService(context.MEDIA_ROUTER_SERVICE);
//        MediaSessionManager mediaSessionManager = (MediaSessionManager) context.getSystemService(context.MEDIA_SESSION_SERVICE);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
//        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(context.TELEPHONY_SUBSCRIPTION_SERVICE);
//        TelecomManager telecomManager = (TelecomManager) context.getSystemService(context.TELECOM_SERVICE);
//        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) context.getSystemService(context.CARRIER_CONFIG_SERVICE);
//        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
//        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
//        TextServicesManager textServicesManager = (TextServicesManager) context.getSystemService(context.TEXT_SERVICES_MANAGER_SERVICE);
//        AppWidgetManager appWidgetManager = (AppWidgetManager) context.getSystemService(context.APPWIDGET_SERVICE);
////        context.getSystemService(context.VOICE_INTERACTION_MANAGER_SERVICE);
////        context.getSystemService(context.BACKUP_SERVICE);
//        DropBoxManager dropBoxManager = (DropBoxManager) context.getSystemService(context.DROPBOX_SERVICE);
////        context.getSystemService(context.DEVICE_IDLE_CONTROLLER);
//        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService(context.DEVICE_POLICY_SERVICE);
//        UiModeManager uiModeManager = (UiModeManager) context.getSystemService(context.UI_MODE_SERVICE);
//        DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
//        BatteryManager batteryManager = (BatteryManager) context.getSystemService(context.BATTERY_SERVICE);
//        NfcManager nfcManager = (NfcManager) context.getSystemService(context.NFC_SERVICE);
//        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(context.BLUETOOTH_SERVICE);
////        context.getSystemService(context.SIP_SERVICE);
//        UsbManager usbManager = (UsbManager) context.getSystemService(context.USB_SERVICE);
////        context.getSystemService(context.SERIAL_SERVICE);
////        context.getSystemService(context.HDMI_CONTROL_SERVICE);
//        InputManager inputManager = (InputManager) context.getSystemService(context.INPUT_SERVICE);
//        DisplayManager displayManager = (DisplayManager) context.getSystemService(context.DISPLAY_SERVICE);
//        UserManager userManager = (UserManager) context.getSystemService(context.USER_SERVICE);
//        LauncherApps launcherApps = (LauncherApps) context.getSystemService(context.LAUNCHER_APPS_SERVICE);
//        RestrictionsManager restrictionsManager = (RestrictionsManager) context.getSystemService(context.RESTRICTIONS_SERVICE);
//        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(context.APP_OPS_SERVICE);
//        CameraManager cameraManager = (CameraManager) context.getSystemService(context.CAMERA_SERVICE);
//        PrintManager printManager = (PrintManager) context.getSystemService(context.PRINT_SERVICE);
//        ConsumerIrManager consumerIrManager = (ConsumerIrManager) context.getSystemService(context.CONSUMER_IR_SERVICE);
////        context.getSystemService(context.TRUST_SERVICE);
//        TvInputManager tvInputManager = (TvInputManager) context.getSystemService(context.TV_INPUT_SERVICE);
////        context.getSystemService(context.NETWORK_SCORE_SERVICE);
//        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(context.USAGE_STATS_SERVICE);
//        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(context.JOB_SCHEDULER_SERVICE);
////        context.getSystemService(context.PERSISTENT_DATA_BLOCK_SERVICE);
//        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) context.getSystemService(context.MEDIA_PROJECTION_SERVICE);
//        MidiManager midiManager = (MidiManager) context.getSystemService(context.MIDI_SERVICE);
////        context.getSystemService(context.RADIO_SERVICE);

        // Debug info
        DebugInfo debugInfo = new DebugInfo();
        debugInfo.brand = Build.BRAND;
        debugInfo.manufacturer = Build.MANUFACTURER;
        debugInfo.device = Build.DEVICE;
        debugInfo.model = Build.MODEL;
        debugInfo.serial = Build.SERIAL;
        debugInfo.firstBootTime = Transformer.timeInMillis2String(Build.TIME);
        debugInfo.osName = Build.DISPLAY;
        debugInfo.verCode = Build.VERSION.SDK_INT;
        
        debugInfo.isRoot = isRoot();
        debugInfo.imei = telephonyManager.getDeviceId();
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        debugInfo.totalMemInMb = memoryInfo.totalMem / 1024 / 1024;
        debugInfo.availableMemInMb = memoryInfo.availMem / 1024 / 1024;
        debugInfo.isLowMem = memoryInfo.lowMemory;

        Map<PackageInfo, List<ActivityManager.RunningAppProcessInfo>> packageInfo_processInfoList = getPkgProcessMap();
        for (Map.Entry<PackageInfo, List<ActivityManager.RunningAppProcessInfo>> kv : packageInfo_processInfoList.entrySet()) {
            PackageInfo packageInfo = kv.getKey();
            List<ActivityManager.RunningAppProcessInfo> processInfoList = kv.getValue();
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;

            // Pkg info
            DebugInfo.PkgInfo pkgInfo = new DebugInfo.PkgInfo();
            debugInfo.pkgInfoList.add(pkgInfo);
            pkgInfo.name = packageInfo.packageName;
            pkgInfo.verName = packageInfo.versionName;
            pkgInfo.verCode = packageInfo.versionCode;
            pkgInfo.targetVerCode = applicationInfo.targetSdkVersion;
            pkgInfo.firstInstallTime = Transformer.timeInMillis2String(packageInfo.firstInstallTime);
            pkgInfo.lastUpdateTime = Transformer.timeInMillis2String(packageInfo.lastUpdateTime);
            pkgInfo.requestPermissionList = Transformer.strings2StringList(packageInfo.requestedPermissions);
            pkgInfo.dataDir = applicationInfo.dataDir;

            // Process info
            for (ActivityManager.RunningAppProcessInfo appProcessInfo : processInfoList) {
                DebugInfo.ProcessInfo processInfo = new DebugInfo.ProcessInfo();
                pkgInfo.processInfoList.add(processInfo);
                processInfo.uid = appProcessInfo.uid;
                processInfo.pid = appProcessInfo.pid;
                processInfo.processName = appProcessInfo.processName;
                Debug.MemoryInfo[] memoryInfos = activityManager.getProcessMemoryInfo(new int[]{appProcessInfo.pid});
                if (memoryInfos.length > 0) {
                    processInfo.processMemInMb = memoryInfos[0].getTotalPss() / 1024;

                    pkgInfo.pkgMemInMb += memoryInfos[0].getTotalPss() / 1024;
                }

                // TODO: thread info
            }
        }

        return debugInfo;
    }

    public static Map<PackageInfo, List<ActivityManager.RunningAppProcessInfo>> getPkgProcessMap() {
        Map<PackageInfo, List<ActivityManager.RunningAppProcessInfo>> pkgProcessListMap = new HashMap<>();

        Context context = WallE.getContext();
        PackageManager packageManager = context.getPackageManager();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : activityManager.getRunningAppProcesses()) {
            for (String pkgName : appProcessInfo.pkgList) {
                try {
                    // Package info
                    PackageInfo packageInfo = packageManager.getPackageInfo(pkgName, PackageManager.GET_ACTIVITIES
                            | PackageManager.GET_GIDS
                            | PackageManager.GET_CONFIGURATIONS
                            | PackageManager.GET_INSTRUMENTATION
                            | PackageManager.GET_PERMISSIONS
                            | PackageManager.GET_PROVIDERS
                            | PackageManager.GET_RECEIVERS
                            | PackageManager.GET_SERVICES
                            | PackageManager.GET_SIGNATURES
                            | PackageManager.GET_UNINSTALLED_PACKAGES);

                    // Process info
                    Map.Entry<PackageInfo, List<ActivityManager.RunningAppProcessInfo>> packageInfo_appProcessInfoList = CollectionUtils.find(pkgProcessListMap, packageInfo, new Comparator<PackageInfo, PackageInfo>() {
                        @Override
                        public int compareTo(PackageInfo a, PackageInfo b) {
                            if (TextUtils.equals(a.packageName, b.packageName)) {
                                return 0;
                            }

                            return 1;
                        }
                    });
                    if (packageInfo_appProcessInfoList == null) {
                        // Create kv
                        List<ActivityManager.RunningAppProcessInfo> appProcessInfoList = new ArrayList<>();
                        appProcessInfoList.add(appProcessInfo);
                        pkgProcessListMap.put(packageInfo, appProcessInfoList);
                    } else {
                        // Add to v
                        packageInfo_appProcessInfoList.getValue().add(appProcessInfo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return pkgProcessListMap;
    }

    private static final String SU_PATHS[]={"/system/bin/su","/system/xbin/su","/system/sbin/su","/sbin/su","/vendor/bin/su"};
    public static boolean isRoot() {
        for (String suPath : SU_PATHS) {
            File file = new File(suPath);
            if (file.exists()) {
                return true;
            }
        }

        return false;
    }
}