package com.walfud.walle.collection;

import com.walfud.walle.ObjectUtils;
import com.walfud.walle.algorithm.Comparator;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by walfud on 9/22/15.
 */
public class CollectionUtils {

    public static final String TAG = "CollectionUtils";

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    //
    public static <T> T find(Collection<T> collection, T t) {
        return find(collection, t, 0);
    }
    public static <S, T> T find(Collection<T> collection, S s, Comparator<S, T> compFunc) {
        return find(collection, s, 0, compFunc);
    }
    public static <T> T find(Collection<T> collection, T t, int startOffset) {
        return find(collection, t, startOffset, null);
    }
    public static <S, T> T find(Collection<T> collection, S s, int startOffset, Comparator<S, T> compFunc) {
        if (collection == null) {
            return null;
        }

        Iterator<T> iterator = collection.iterator();
        for (int i = 0; i < collection.size(); i++) {
            T elem = iterator.next();

            if (i >= startOffset) {
                if (compFunc == null) {
                    // Use default equal comparator
                    if (ObjectUtils.isEqual(s, elem)) {
                        return elem;
                    }
                } else {
                    // Use custom comparator
                    if (compFunc.compareTo(s, elem) == 0) {
                        return elem;
                    }
                }
            }
        }

        return null;
    }

    public static <K, V> Map.Entry<K, V> find(Map<K, V> collection, V v) {
        return find(collection, v, 0);
    }
    public static <S, K, V> Map.Entry<K, V> find(Map<K, V> collection, S s, Comparator<S, K> compFunc) {
        return find(collection, s, 0, compFunc);
    }
    public static <K, V> Map.Entry<K, V> find(Map<K, V> collection, V v, int startOffset) {
        return find(collection, v, startOffset, null);
    }
    /**
     *
     * @param map
     * @param s
     * @param startOffset
     * @param compFunc `a` is `s`, `b` is element in container
     * @param <S>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <S, K, V> Map.Entry<K, V> find(Map<K, V> map, S s, int startOffset, Comparator<S, K> compFunc) {
        if (map == null) {
            return null;
        }

        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
        for (int i = 0; i < map.size(); i++) {
            Map.Entry<K, V> kv = iterator.next();
            K key = kv.getKey();
            V value = kv.getValue();

            if (i >= startOffset) {
                if (compFunc == null) {
                    // Use default equal comparator
                    if (ObjectUtils.isEqual(s, key)) {
                        return kv;
                    }
                } else {
                    // Use custom comparator
                    if (compFunc.compareTo(s, key) == 0) {
                        return kv;
                    }
                }
            }
        }

        return null;
    }
}
