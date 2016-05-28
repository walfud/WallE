package com.walfud.walle.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walfud on 9/28/15.
 */
public class JumpBar extends LinearLayout {

    public static final String TAG = "JumpBar";
    private static final int INVALID_INDEX = -1;

    private Context mContext;
    private OnJumpListener mOnJumpListener;
    private int mLastIndex;
    private List<View> mChildrenList;

    public JumpBar(Context context) {
        this(context, null);
    }

    public JumpBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mLastIndex = INVALID_INDEX;
        mChildrenList = new ArrayList<>();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            mChildrenList.add(v);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                requestDisallowInterceptTouchEvent(true);
                mLastIndex = INVALID_INDEX;
                // Go through
            case MotionEvent.ACTION_MOVE:
                int currentIndex = getIndexByXy(event.getX(), event.getY());

                if (currentIndex != INVALID_INDEX && currentIndex != mLastIndex) {
                    if (mOnJumpListener != null) {
                        mOnJumpListener.onJump(mChildrenList.get(currentIndex), currentIndex, mChildrenList.size());
                    }

                    mLastIndex = currentIndex;
                }
                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                break;
        }

        return true;
    }

    // Function
    public void setOnJumpListener(OnJumpListener listener) {
        mOnJumpListener = listener;
    }

    // Internal
    private int getIndexByXy(double x, double y) {
        for (int i = 0; i < mChildrenList.size(); i++) {
            View view = mChildrenList.get(i);
            if (view.getX() <= x && x < view.getX() + view.getWidth()
                    && view.getY() <= y && y < view.getY() + view.getHeight()) {
                return i;
            }
        }

        return INVALID_INDEX;
    }

    //
    public interface OnJumpListener {
        /**
         * When you switch different children, this callback will be called
         * @param view the event view
         * @param index 0 based
         * @return unused
         */
        boolean onJump(View view, int index, int totalIndex);
    }
}