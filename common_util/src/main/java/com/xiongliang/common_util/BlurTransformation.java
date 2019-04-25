package com.xiongliang.common_util;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.xiongliang.common_util.BitmapUtils;


public class BlurTransformation extends BitmapTransformation {

    private float mTargetWidth = 360;

    private float mTargetHeight = 540;

    private String mImageUrl;

    public BlurTransformation(Context context, String imageUrl) {
        super(context);
        this.mImageUrl = imageUrl;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap bitmap = null;
        try {
            final float prop = caculateSize(toTransform.getWidth(), toTransform.getHeight());
            Bitmap temp = BitmapUtils.bitmapSetSize(toTransform, prop, prop);
            bitmap = BitmapUtils.fastBlur(temp, 50);
        } catch (OutOfMemoryError oom) {
            oom.printStackTrace();
        }
        return bitmap;
    }

    private float caculateSize(int width, int height) {
        float widthProp = mTargetWidth / width;
        float heightProp = mTargetHeight / height;

        final float targetProp = Math.min(widthProp, heightProp);
        return targetProp >= 1 ? 1.0F : targetProp;
    }

    @Override
    public String getId() {
        return String.valueOf(System.currentTimeMillis());
    }
}
