package com.walfud.walle.lang;

/**
 * Created by walfud on 2016/3/19.
 */
public class StrongReference<T> {
    private T t;

    public StrongReference(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }
}
