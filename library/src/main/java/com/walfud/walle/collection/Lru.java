package com.walfud.walle.collection;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by walfud on 2015/11/16.
 */
public class Lru<E> {

    public static final String TAG = "Lru";

    private Deque<E> mContainer = new ArrayDeque<>();

    /**
     * Add item to front if not exist and return null. Otherwise, replace existing one and move it
     * to front and return old one
     * @param e
     */
    public E set(E e) {
        E old = CollectionUtils.find(mContainer, e);

        mContainer.remove(old);
        mContainer.addFirst(e);

        return old;
    }

    /**
     * If `e` exists in container, then move it to front and return it. Otherwise, do nothing and
     * return null
     * @param e
     * @return
     */
    public E get(E e) {
        E old = CollectionUtils.find(mContainer, e);

        if (mContainer.remove(old)) {
            mContainer.addFirst(old);
            return old;
        }

        return null;
    }

    /**
     * Remove item and return it
     */
    public E remove(E e) {
        E old = CollectionUtils.find(mContainer, e);

        return mContainer.remove(old) ? old : null;
    }
}
