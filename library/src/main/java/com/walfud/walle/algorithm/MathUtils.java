package com.walfud.walle.algorithm;

/**
 * Created by walfud on 2015/11/18.
 */
public class MathUtils {

    public static final String TAG = "MathUtils";

    public static int min(int... values) {
        int minValue = 0;
        for (int i : values) {
            if (i < minValue) {
                minValue = i;
            }
        }

        return minValue;
    }

    public static double min(double... values) {
        double minValue = 0;
        for (double d : values) {
            if (d < minValue) {
                minValue = d;
            }
        }

        return minValue;
    }
}
