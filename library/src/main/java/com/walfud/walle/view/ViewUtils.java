package com.walfud.walle.view;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Rect;
import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by walfud on 2016/8/5.
 */
public class ViewUtils {
    public static final String TAG = "ViewUtils";

    /**
     * 判断某个 `MotionEvent` 事件是否在某控件内.
     *
     * @param motionEvent
     * @param view
     * @return
     */
    public static boolean isTouchInView(MotionEvent motionEvent, View view) {
        int[] viewXy = new int[2];
        view.getLocationOnScreen(viewXy);
        Rect viewRect = new Rect(viewXy[0], viewXy[1], viewXy[0] + view.getWidth(), viewXy[1] + view.getHeight());

        float x = motionEvent.getRawX();
        float y = motionEvent.getRawY();

        return viewRect.contains((int) x, (int) y);
    }

    /**
     * Simpler version of {@link View#findViewById(int)} which infers the target type.
     */
    @SuppressWarnings({"unchecked", "UnusedDeclaration"}) // Checked by runtime cast. Public API.
    @CheckResult
    public static <T extends View> T findViewById(@NonNull View view, @IdRes int id) {
        return (T) view.findViewById(id);
    }

    /**
     * Simpler version of {@link Activity#findViewById(int)} which infers the target type.
     */
    @SuppressWarnings({"unchecked", "UnusedDeclaration"}) // Checked by runtime cast. Public API.
    @CheckResult
    public static <T extends View> T findViewById(@NonNull Activity activity, @IdRes int id) {
        return (T) activity.findViewById(id);
    }

    /**
     * Simpler version of {@link Dialog#findViewById(int)} which infers the target type.
     */
    @SuppressWarnings({"unchecked", "UnusedDeclaration"}) // Checked by runtime cast. Public API.
    @CheckResult
    public static <T extends View> T findViewById(@NonNull Dialog dialog, @IdRes int id) {
        return (T) dialog.findViewById(id);
    }
}
