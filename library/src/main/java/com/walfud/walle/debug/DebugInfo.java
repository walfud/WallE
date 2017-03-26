package com.walfud.walle.debug;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walfud on 2015/12/18.
 */
public class DebugInfo {

    public static final String TAG = "DebugInfo";

    public String brand;                // google
    public String manufacturer;         // motorola
    public String device;               // shamu
    public String model;                // Nexus 6
    public String serial;               // ZX1G42CWLW
    public String firstBootTime;        // 1443486857000
    public String osName;               // MRA58N
    public int verCode;                 // 23

    public boolean isRoot;
    public String imei;
    public long totalMemInMb;
    public long availableMemInMb;
    public boolean isLowMem;

    public List<PkgInfo> pkgInfoList = new ArrayList<>();

    //
    public static class PkgInfo {
        public String appName;
        public String pkgName;
        public String verName;
        public int verCode;
        public int targetVerCode;
        public String firstInstallTime;
        public String lastUpdateTime;
        public List<String> requestPermissionList = new ArrayList<>();
        public String dataDir;
        public int flags;

        public long pkgMemInMb;

        public List<ProcessInfo> processInfoList = new ArrayList<>();
    }

    public static class ProcessInfo {
        public int uid;
        public int pid;
        public String processName;

        public long processMemInMb;

        public List<ThreadInfo> threadInfoList = new ArrayList<>();
    }

    public static class ThreadInfo {
        public int tid;
    }
}