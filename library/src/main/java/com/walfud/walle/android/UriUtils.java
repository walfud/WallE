package com.walfud.walle.android;

import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by walfud on 2015/12/22.
 */
public class UriUtils {

    public static final String TAG = "UriUtils";

    public static boolean isEmpty(Uri uri) {
        return uri == null
                || uri.equals(Uri.EMPTY)
                || TextUtils.isEmpty(uri.toString());
    }
}
