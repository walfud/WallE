package com.walfud.walle.graphic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Pair;

/**
 * Created by walfud on 2015/9/26.
 */
public class GraphicUtils {

    public static final String TAG = "GraphicUtils";

    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);

        return bitmap;
    }

    /**
     * If `bitmap` is mutable, then return it. otherwise, return a new mutable instance
     * @param bitmap
     * @return
     */
    public static Bitmap makeMutable(Bitmap bitmap) {
        if (bitmap.isMutable()) {
            return bitmap;
        }

        Bitmap mutable = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mutable);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);

        return mutable;
    }

    public static Bitmap getSampleBitmap(String path, int dstWidth, int dstHeight) {
        Pair<Integer, Integer> widthHeight = getBitmapBound(path);
        int sampleValue = getSampleValue(widthHeight.first, widthHeight.second, dstWidth, dstHeight);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleValue;
        return BitmapFactory.decodeFile(path, options);
    }

    public static Pair<Integer, Integer> getBitmapBound(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        return new Pair<>(options.outWidth, options.outHeight);
    }

    /**
     * Used for `BitmapFactory.Options.inSampleSize`
     * @param srcWidth
     * @param srcHeight
     * @param dstWidth
     * @param dstHeight
     * @return
     */
    public static int getSampleValue(int srcWidth, int srcHeight, int dstWidth, int dstHeight) {
        int src;
        int dst;
        if (dstWidth > dstHeight) {
            src = srcWidth;
            dst = dstWidth;
        } else {
            src = srcHeight;
            dst = dstHeight;
        }

        int sampleValue = 1;
        while ((dst *= 2) < src) {
            sampleValue++;
        }

        return sampleValue;
    }

    public static RectF makeRectFByCenter(double centerX, double centerY, double radius) {
        return makeRectFByCenter(centerX, centerY, radius, radius);
    }
    public static RectF makeRectFByCenter(double centerX, double centerY, double radiusX, double radiusY) {
        return new RectF((float) (centerX - radiusX), (float) (centerY - radiusY), (float) (centerX + radiusX), (float) (centerY + radiusY));
    }
}
