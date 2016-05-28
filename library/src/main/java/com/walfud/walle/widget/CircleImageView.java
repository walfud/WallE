package com.walfud.walle.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.walfud.walle.R;

/**
 * Created by walfud on 2015/9/26.
 */
public class CircleImageView extends RoundedImageView {

    public static final String TAG = "CircleImageView";

    private double mRadius;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        mRadius = typedArray.getDimension(R.styleable.CircleImageView_radius, 0);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Create layer for destination shape
        int layerCount = canvas.getSaveCount();
        canvas.saveLayerAlpha(getRectF(), 0xff);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) mRadius, mMaskPaint);

        // Supply a layer for origin drawable, this layer will xfer to above shape layer with `SRC_IN`
        canvas.saveLayer(getRectF(), mXferPaint, Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);

        // Combine newer layers back
        canvas.restoreToCount(layerCount);
    }
}
