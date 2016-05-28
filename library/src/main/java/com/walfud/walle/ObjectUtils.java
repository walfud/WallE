package com.walfud.walle;

/**
 * Created by walfud on 2015/12/18.
 */
public class ObjectUtils {

    public static final String TAG = "ObjectUtils";

    public static boolean isEqual(Object x, Object y) {
        if (x != null) {
            return x.equals(y);
        }

        if (y != null) {
            return y.equals(x);
        }

        return true;
    }

    public static <T extends Comparable<T>> int compare(T x, T y) {
        if (x != null) {
            return x.compareTo(y);
        }

        if (y != null) {
            return y.compareTo(x) * -1;
        }

        return 0;
    }
}
