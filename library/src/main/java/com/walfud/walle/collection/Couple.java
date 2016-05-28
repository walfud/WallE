package com.walfud.walle.collection;

/**
 * @deprecated Use {@code android.util.Pair} instead.
 * Created by walfud on 2015/11/19.
 */
public class Couple<F, S> {

    public static final String TAG = "Couple";

    public F first;
    public S second;

    public Couple() {
    }

    public Couple(F first, S second) {
        this.first = first;
        this.second = second;
    }
}
