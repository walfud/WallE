package com.walfud.walle.rxjava;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by walfud on 2016/3/4.
 */
public class SimpleSingleObserver<T> implements SingleObserver<T> {
    @Override
    public final void onSubscribe(Disposable d) {
        onStart(d);
    }

    @Override
    public final void onSuccess(T t) {
        try {
            onSuc(t);
        } catch (Exception e) {
            e.printStackTrace();
        }

        onFinish(true, t, null);
    }

    @Override
    public final void onError(Throwable throwable) {
        try {
            onFail(new Exception(throwable));
        } catch (Exception e) {
            e.printStackTrace();
        }

        onFinish(false, null, throwable);
    }

    public void onStart(Disposable d) {

    }

    public void onSuc(T result) {

    }

    public void onFail(Exception e) {

    }

    public void onFinish(boolean suc, T result, Throwable throwable) {

    }
}
