package com.walfud.walle.algorithm.hash;

import com.walfud.walle.Transformer;

import java.security.MessageDigest;

/**
 * Created by walfud on 2015/11/18.
 */
public class HashUtils {

    public static final String TAG = "HashUtils";
    public static final String MD5_DEFAULT = "00000000000000000000000000000000";

    public static String md5(String string) {
        String md5 = MD5_DEFAULT;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytesMd5 = messageDigest.digest(string.getBytes());
            md5 = Transformer.bytes2String(bytesMd5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return md5;
    }
}
