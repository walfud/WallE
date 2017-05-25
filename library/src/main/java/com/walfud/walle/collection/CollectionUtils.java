package com.walfud.walle.collection;

import com.walfud.walle.lang.ObjectUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
        return find(collection, t, 0, collection.size());
    }
    public static <T> T find(Collection<T> collection, Predicate<T> predicate) {
        return find(collection, predicate, 0, collection.size());
    }
    public static <T, S> T find(Collection<T> collection, S s, int startOffset, int endOffset) {
        return find(collection, e -> ObjectUtils.isEqual(s, e), startOffset, endOffset);
    }
    public static <T> T find(Collection<T> collection, Predicate<T> predicate, int startOffset, int endOffset) {
        return find(collection.iterator(), collection.size(), predicate, startOffset, endOffset);
    }

    public static <K, V> Map.Entry<K, V> find(Map<K, V> map, K key) {
        return find(map, (Predicate<K>) k -> ObjectUtils.isEqual(key, k));
    }
    public static <K, V> Map.Entry<K, V> find(Map<K, V> map, Predicate<K> predicateKey) {
        return find(map.entrySet().iterator(), map.size(), entry -> predicateKey.test(entry.getKey()), 0, map.size());
    }
    public static <K, V> Map.Entry<K, V> findValue(Map<K, V> map, V value) {
        return findValue(map, (Predicate<V>) v -> ObjectUtils.isEqual(value, v));
    }
    public static <K, V> Map.Entry<K, V> findValue(Map<K, V> map, Predicate<V> predicateValue) {
        return find(map.entrySet().iterator(), map.size(), entry -> predicateValue.test(entry.getValue()), 0, map.size());
    }
    public static <T> T find(Iterator<T> iterator, int size, Predicate<T> predicate, int startOffset, int endOffset) {
        for (int i = 0; i < size; i++) {
            T elem = iterator.next();

            if (startOffset <= i && i < endOffset) {
                if (predicate.test(elem)) {
                    return elem;
                }
            }
        }

        return null;
    }

    /**
     * Only support `ArrayList / HashSet / LinkedList / ArrayDeque`
     * @param t
     * @param comparable return 0 if you want keep the element
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T extends Collection, E> T filter(T t, Predicate<E> comparable) {
        T filtered;
        if (false) {
        } else if (t instanceof ArrayList) {
            filtered = (T) new ArrayList<>();
        } else if (t instanceof HashSet) {
            filtered = (T) new HashSet<>();
        } else if (t instanceof LinkedList) {
            filtered = (T) new LinkedList<>();
        } else if (t instanceof ArrayDeque) {
            filtered = (T) new ArrayDeque<>();
        } else {
            return null;
        }

        Iterator<E> iterator = t.iterator();
        while (iterator.hasNext()) {
            E e = iterator.next();
            if (comparable.test(e)) {
                filtered.add(e);
            }
        }

        return filtered;
    }

    /**
     * Fast create and initialize
     */
    public static <T> List<T> newArrayList(T... ts) {
        List<T> list = new ArrayList<>();
        if (ts != null) {
            for (T t : ts) {
                list.add(t);
            }
        }
        return list;
    }

    public static <K, V> Map<K, V> newHashMap(Object... objs) {
        Map<K, V> m = new HashMap<>();
        if (objs == null || objs.length % 2 != 0) {
            return m;
        }

        for (int i = 0; i < objs.length; i += 2) {
            int j = i + 1;

            K k = (K) objs[i];
            V v = (V) objs[j];

            m.put(k, v);
        }

        return m;
    }

    //
    public interface Predicate<T> {

        /**
         * Evaluates this predicate on the given argument.
         *
         * @param t the input argument
         * @return {@code true} if the input argument matches the predicate,
         * otherwise {@code false}
         */
        boolean test(T t);
    }
}
