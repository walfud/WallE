package com.walfud.walle.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.walfud.walle.R;

/**
 * Created by walfud on 2015/9/26.
 */
public class RoundedImageView extends ImageView {

    public static final String TAG = "RoundedImageView";

    protected Paint mMaskPaint;
    protected Paint mXferPaint;

    /**
     * Round radius
     */
    private double mRadiusX;
    private double mRadiusY;

    public RoundedImageView(Context context) {
        this(context, null);
    }
    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundedImageView);
        mRadiusX = typedArray.getDimension(R.styleable.RoundedImageView_walle_radiusX, 0);
        mRadiusY = typedArray.getDimension(R.styleable.RoundedImageView_walle_radiusY, 0);
        typedArray.recycle();

        mMaskPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mXferPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mXferPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Create layer for destination shape
        canvas.saveLayerAlpha(getRectF(), 0xff);
        canvas.drawRoundRect(getRectF(), (float) mRadiusX, (float) mRadiusY, mMaskPaint);

        // Supply a layer for origin drawable, this layer will xfer to above shape layer with `SRC_IN`
        canvas.saveLayer(getRectF(), mXferPaint, Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);

        // Combine two newer layers to origin canvas
        canvas.restore();
        canvas.restore();
    }

    //
    protected RectF getRectF() {
        return new RectF(0.0f, 0.0f, getWidth(), getHeight());
    }
}
