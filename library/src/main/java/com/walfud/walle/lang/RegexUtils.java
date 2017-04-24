package com.walfud.walle.lang;

import java.util.regex.Pattern;

/**
 * Created by walfud on 2017/4/20.
 */

public class RegexUtils {

    public static boolean isMd5(String md5) {
        return isMd5_32(md5) || isMd5_16(md5);
    }
    private static final Pattern PATTERN_MD5_16 = Pattern.compile("[^[a-fA-F0-9]{16}$]");
    public static boolean isMd5_16(String md5) {
        return PATTERN_MD5_16.matcher(md5).matches();
    }
    private static final Pattern PATTERN_MD5_32 = Pattern.compile("[^[a-fA-F0-9]{32}$]");
    public static boolean isMd5_32(String md5) {
        return PATTERN_MD5_32.matcher(md5).matches();
    }

}
