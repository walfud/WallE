package com.walfud.walle.graphic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

/**
 * Created by walfud on 2015/9/26.
 */
public class BitmapTransformer {

    public static final String TAG = "BitmapTransformer";

    private Bitmap mSrc;
    private Bitmap mDst;
    private Paint mFilterPaint;
    private Paint mXferPaint;

    public BitmapTransformer(Bitmap bitmap) {
        mSrc = bitmap;
        mDst = GraphicUtils.makeMutable(bitmap);
        mFilterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFilterPaint.setColor(0xff000000);
        mXferPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mXferPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mXferPaint.setColor(0xff000000);
    }

    /**
     * Make circle bitmap from center
     * @return
     */
    public BitmapTransformer circle() {
        double minLength = Math.min(mSrc.getWidth() / 2, mSrc.getHeight() / 2);
        return circle(minLength);
    }

    public BitmapTransformer circle(double radius) {
        RectF resultRectF = GraphicUtils.makeRectFByCenter(mSrc.getWidth() / 2, mSrc.getHeight() / 2, radius);
        return transform(resultRectF, (canvas, width, height, paint, args) -> {
            double radius1 = (Double) args[0];

            canvas.drawCircle(width / 2, height / 2, (float) radius1, paint);
        }, new Double(resultRectF.width() / 2));
    }

    /**
     * Make round bitmap from center
     * @return
     */
    public BitmapTransformer round(double radius) {
        return round(radius, radius);
    }
    public BitmapTransformer round(double radiusX, double radiusY) {
        RectF resultRectF = new RectF(0.0f, 0.0f, mSrc.getWidth(), mSrc.getHeight());
        return transform(resultRectF, (canvas, width, height, paint, args) -> {
            double rx = (Double) args[0];
            double ry = (Double) args[1];

            canvas.drawRoundRect(0.0f, 0.0f, width, height, (float) rx, (float) ry, paint);
        }, radiusX, radiusY);
    }

    public BitmapTransformer crop(int width, int height) {
        int srcWidth = mSrc.getWidth();
        int srcHeight = mSrc.getHeight();
        double widthPercent = (double) srcWidth / width;
        double heightPercent = (double) srcHeight / height;

        Bitmap tmpResult;

        // Scale if needed
        if ((widthPercent < 1 || heightPercent < 1)             // Scale up
                || (widthPercent > 1 && heightPercent > 1)) {   // Scale down
            double scaleValue = 1 / Math.min(widthPercent, heightPercent);
            Matrix matrix = new Matrix();
            matrix.setScale((float) scaleValue, (float) scaleValue);
            tmpResult = Bitmap.createBitmap(mSrc, 0, 0, srcWidth, srcHeight, matrix, false);
        } else {
            tmpResult = mDst;
        }

        // Crop if needed
        int centerX = tmpResult.getWidth() / 2;
        int centerY = tmpResult.getHeight() / 2;
        if ((double) tmpResult.getWidth() / width > (double) tmpResult.getHeight() / height) {
            // Crop width
            mDst = Bitmap.createBitmap(tmpResult, centerX - width / 2, 0, width, height);
        } else {
            // Crop height
            mDst = Bitmap.createBitmap(tmpResult, 0, centerY - height / 2, width, height);
        }

        return this;
    }

    public BitmapTransformer centerInside(int width, int height) {
        int srcWidth = mSrc.getWidth();
        int srcHeight = mSrc.getHeight();
        double widthPercent = (double) srcWidth / width;
        double heightPercent = (double) srcHeight / height;

        // Scale down if needed
        if (widthPercent > 1 && heightPercent > 1) {
            double scaleValue = 1 / Math.max(widthPercent, heightPercent);
            Matrix matrix = new Matrix();
            matrix.setScale((float) scaleValue, (float) scaleValue);
            mDst = Bitmap.createBitmap(mSrc, 0, 0, srcWidth, srcHeight, matrix, false);
        }

        return this;
    }

    /**
     * Get transform result
     * @return
     */
    public Bitmap get() {
        return mDst;
    }

    // Internal
    /**
     * Helper function to abstract src-dst xfer procedure
     * @param transformer
     * @return
     */
    private BitmapTransformer transform(RectF rectF, Transformer transformer, Object... args) {

        // dst
        Bitmap dst = Bitmap.createBitmap((int) rectF.width(), (int) rectF.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(dst);
        transformer.draw(canvas, dst.getWidth(), dst.getHeight(), mFilterPaint, args);

        // src
        Bitmap src = Bitmap.createBitmap(mSrc, (int) rectF.left, (int) rectF.top, (int) rectF.width(), (int) rectF.height());
        canvas.drawBitmap(src, 0, 0, mXferPaint);

        mDst = dst;
        return this;
    }

    private interface Transformer {
        /**
         *
         * @param canvas
         * @param width bitmap width to be drawn
         * @param height bitmap height to be drawn
         * @param paint
         * @param args
         */
        void draw(Canvas canvas, int width, int height, Paint paint, Object... args);
    }
}
