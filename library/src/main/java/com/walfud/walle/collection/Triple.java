package com.walfud.walle.collection;

import com.walfud.walle.lang.ObjectUtils;

/**
 * Created by walfud on 2016/3/11.
 */
public class Triple<F, S, T> {

    public static final String TAG = "Triple";

    public F first;
    public S second;
    public T third;

    public Triple() {
    }

    public Triple(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Triple)) {
            return false;
        }
        Triple<?, ?, ?> p = (Triple<?, ?, ?>) o;
        return ObjectUtils.isEqual(p.first, first) && ObjectUtils.isEqual(p.second, second) && ObjectUtils.isEqual(p.third, third);
    }

    @Override
    public int hashCode() {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode()) & (third == null ? 0 : third.hashCode());
    }
}
