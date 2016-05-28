package com.walfud.walle;

import java.util.regex.Pattern;

/**
 * Created by walfud on 2016/1/18.
 */
public class RegexUtils {

    public static final String TAG = "RegexUtils";
    private static final Pattern PATTERN_EMAIL = Pattern.compile("[a-zA-Z0-9._-]+@[a-zA-Z0-9_-]+\\.[a-zA-Z0-9_-]+");

    public static boolean isEmail(String str) {
        return PATTERN_EMAIL.matcher(str).matches();
    }
}
