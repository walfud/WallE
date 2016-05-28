package com.walfud.walle.collection;

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
}
