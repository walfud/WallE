package com.walfud.walle;

import android.Manifest;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by walfud on 2015/10/18.
 */
public class PermissionUtils {

    public static final String TAG = "PermissionUtils";
    private static final Set<String> sDangerousPerm = new HashSet<>();
    static {
        // Dangerous permissions. See also: http://developer.android.com/guide/topics/security/permissions.html#normal-dangerous
        sDangerousPerm.add(Manifest.permission.READ_CALENDAR);
        sDangerousPerm.add(Manifest.permission.READ_CALENDAR);
        sDangerousPerm.add(Manifest.permission.WRITE_CALENDAR);
        sDangerousPerm.add(Manifest.permission.CAMERA);
        sDangerousPerm.add(Manifest.permission.READ_CONTACTS);
        sDangerousPerm.add(Manifest.permission.WRITE_CONTACTS);
        sDangerousPerm.add(Manifest.permission.GET_ACCOUNTS);
        sDangerousPerm.add(Manifest.permission.ACCESS_FINE_LOCATION);
        sDangerousPerm.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        sDangerousPerm.add(Manifest.permission.RECORD_AUDIO);
        sDangerousPerm.add(Manifest.permission.READ_PHONE_STATE);
        sDangerousPerm.add(Manifest.permission.CALL_PHONE);
        sDangerousPerm.add(Manifest.permission.READ_CALL_LOG);
        sDangerousPerm.add(Manifest.permission.WRITE_CALL_LOG);
        sDangerousPerm.add(Manifest.permission.ADD_VOICEMAIL);
        sDangerousPerm.add(Manifest.permission.USE_SIP);
        sDangerousPerm.add(Manifest.permission.PROCESS_OUTGOING_CALLS);
        sDangerousPerm.add(Manifest.permission.BODY_SENSORS);
        sDangerousPerm.add(Manifest.permission.SEND_SMS);
        sDangerousPerm.add(Manifest.permission.RECEIVE_SMS);
        sDangerousPerm.add(Manifest.permission.READ_SMS);
        sDangerousPerm.add(Manifest.permission.RECEIVE_WAP_PUSH);
        sDangerousPerm.add(Manifest.permission.RECEIVE_MMS);
        sDangerousPerm.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        sDangerousPerm.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public static boolean isDangerousPerm(String permName) {
        return sDangerousPerm.contains(permName);
    }
}
