package com.walfud.walle.widget;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

/**
 * Simulate the developer toggle hint.

 * Created by walfud on 12/16/15.
 */
public class SecretEvent {

    public static final String TAG = "SecretEvent";

    /**
     * Application context is necessary
     */
    private Context mContext;

    private View mTargetView;
    private Runnable mTheEvent;

    /**
     * The max time between two click
     */
    private long mMaxContinuousTimeInMillis;
    /**
     * The max continuous count to trigger event
     */
    private int mMaxContinuousCount = 7;

    private long mLastClickTimeInMillis = 0;
    private int mContinuousCount = 0;

    /**
     * The tip FORMAT. Must be format with only one `%d` (such as, `"You are now %d steps away from being a developer"`)
     */
    private String mBeforeTipFmt;
    /**
     * The tip when the event is being triggered
     */
    private String mOnEventTip;
    /**
     * The tip when click the view anymore after the event has been triggered
     */
    private String mAfterEventTip;

    private Toast mToast;

    private boolean mAlreadyTrigger;

    public SecretEvent(Context context) {
        this(context, null, null);
    }

    public SecretEvent(Context context, View targetView, Runnable event) {
        this(context, targetView, event,
                1000, 7,
                "You are now %d steps away from being a developer!", null, "No need, you are already a developer.");
    }

    public SecretEvent(Context context, View targetView, Runnable event,
                       long maxContinuousTimeInMillis, int maxContinuousCount,
                       String beforeEventTip, String onEventTip, String afterEventTip) {
        mContext = context;
        mTargetView = targetView;
        mTheEvent = event;

        mMaxContinuousTimeInMillis = maxContinuousTimeInMillis;
        mMaxContinuousCount = maxContinuousCount;

        mBeforeTipFmt = beforeEventTip;
        mOnEventTip = onEventTip;
        mAfterEventTip = afterEventTip;

        mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        mAlreadyTrigger = false;
    }

    // Function
    public SecretEvent setTargetView(View targetView) {
        mTargetView = targetView;
        return this;
    }

    public SecretEvent setEvent(Runnable event) {
        mTheEvent = event;
        return this;
    }

    public SecretEvent setMaxContinuosTimeInMillis(long maxContinuosTimeInMillis) {
        mMaxContinuousTimeInMillis = maxContinuosTimeInMillis;
        return this;
    }

    public SecretEvent setMaxContinuousCount(int maxContinuousCount) {
        mMaxContinuousCount = maxContinuousCount;
        return this;
    }

    public SecretEvent setBeforeEventTipFmt(String beforeEventTipFmt) {
        mBeforeTipFmt = beforeEventTipFmt;
        return this;
    }

    public SecretEvent setOnEventTip(String onEventTip) {
        mOnEventTip = onEventTip;
        return this;
    }

    public SecretEvent setAfterEventTip(String afterEventTip) {
        mAfterEventTip = afterEventTip;
        return this;
    }

    public SecretEvent setAlreadyTrigger(boolean alreadyTrigger) {
        mAlreadyTrigger = alreadyTrigger;
        return this;
    }

    public void make() {
        mTargetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SystemClock.uptimeMillis() - mLastClickTimeInMillis >= mMaxContinuousTimeInMillis) {
                    // New begin
                    mContinuousCount = 1;
                } else {
                    // Continuous click
                    String tip;

                    int eventLeftCount = mMaxContinuousCount - mContinuousCount;
                    if (eventLeftCount < 0 || mAlreadyTrigger) {
                        // After
                        tip = mAfterEventTip;
                    } else if (eventLeftCount == 0) {
                        // Trigger event!
                        mAlreadyTrigger = true;

                        if (mTheEvent != null) {
                            mTheEvent.run();
                        }

                        tip = mOnEventTip;
                    } else {    // eventLeftCount > 0
                        // Before
                        tip = String.format(mBeforeTipFmt, eventLeftCount);
                    }

                    if (!TextUtils.isEmpty(tip)) {
                        mToast.setText(tip);
                        mToast.show();
                    }
                }

                mLastClickTimeInMillis = SystemClock.uptimeMillis();
                mContinuousCount++;
            }
        });
    }
}
