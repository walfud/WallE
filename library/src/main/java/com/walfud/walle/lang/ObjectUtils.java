package com.walfud.walle.lang;

import android.text.TextUtils;

import com.walfud.walle.algorithm.Comparator2;
import com.walfud.walle.collection.CollectionUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by walfud on 2015/12/18.
 */
public class ObjectUtils {

    public static final String TAG = "ObjectUtils";

    /**
     * sdk 19 后, 可以考虑使用 {@link java.util.Objects#equals(Object, Object)}
     * @param x
     * @param y
     * @return
     */
    public static boolean isEqual(Object x, Object y) {
        if (x != null) {
            return x.equals(y);
        }

        if (y != null) {
            return y.equals(x);
        }

        return true;
    }

    public static <X, Y> boolean isEqual(Collection<X> xs, Collection<Y> ys, Comparator2<X, Y> comparator2) {
        if (CollectionUtils.isEmpty(xs) && CollectionUtils.isEmpty(ys)) {
            return true;
        } else if (CollectionUtils.isEmpty(xs) && !CollectionUtils.isEmpty(ys)) {
            return false;
        } else if (!CollectionUtils.isEmpty(xs) && CollectionUtils.isEmpty(ys)) {
            return false;
        } else {
            Iterator<X> xIter = xs.iterator();
            Iterator<Y> yIter = ys.iterator();
            while (xIter.hasNext() && yIter.hasNext()) {
                X x = xIter.next();
                Y y = yIter.next();
                if (comparator2.compareTo(x, y) != 0) {
                    return false;
                }
            }
            return !xIter.hasNext() && !yIter.hasNext();
        }
    }

    /**
     * Safe `get` operation with default value
     * @param value
     * @param defaultValue
     * @return
     */
    public static String getOpt(String value, String defaultValue) {
        return !TextUtils.isEmpty(value) ? value : defaultValue;
    }
    public static <T extends Number> T getOpt(T value, T defaultValue) {
        return (value != null && value.intValue() != 0) ? value : defaultValue;
    }
    public static <T extends Collection> T getOpt(T value, T defaultValue) {
        return !CollectionUtils.isEmpty(value) ? value : defaultValue;
    }
    public static <T> T getOpt(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }
}
