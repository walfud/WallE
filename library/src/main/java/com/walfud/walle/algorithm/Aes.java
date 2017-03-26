package com.walfud.walle.algorithm;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by walfud on 2016/10/31.
 */

public final class Aes {
    // 加密
    public static String encrypt(String src, String key) throws Exception {
        if (key == null) {
            return null;
        }
        // 判断Key是否为16位
        if (key.length() != 16) {
            return null;
        }

        byte[] raw = key.getBytes("utf-8");
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec("6HbN2vadZB4mYiH1".getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
        byte[] encrypted = cipher.doFinal(src.getBytes("utf-8"));

        return Base64.encodeToString(encrypted, Base64.NO_WRAP);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String decrypt(String src, String key) throws Exception {
        try {
            // 判断Key是否正确
            if (key == null) {
                return null;
            }

            // 判断Key是否为16位
            if (key.length() != 16) {
                return null;
            }

            byte[] raw = key.getBytes("utf-8");
            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("6HbN2vadZB4mYiH1".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            byte[] encrypted1 = Base64.decode(src, Base64.NO_WRAP);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
