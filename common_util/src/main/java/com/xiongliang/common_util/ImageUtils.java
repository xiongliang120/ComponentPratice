package com.xiongliang.common_util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.StringSignature;


import java.io.File;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;


public class ImageUtils {

    public static final String TAG = "ImageUtils";

    private static Context sContext = AppCore.getAppContext();

    private static ImageUtils sImageUtils;

    private LruCache<Object, Bitmap> mCacheBitmaps;

    private Random random = new Random(System.currentTimeMillis());

    private int[] mPlaceHolder = {
            R.drawable.base_image_holder_color_1,
            R.drawable.base_image_holder_color_2,
            R.drawable.base_image_holder_color_3,
            R.drawable.base_image_holder_color_4,
            R.drawable.base_image_holder_color_5,
            R.drawable.base_image_holder_color_6,
            R.drawable.base_image_holder_color_7,
            R.drawable.base_image_holder_color_8};

    private ImageUtils() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory());
        LogUtils.d(TAG, "Max Memory is " + maxMemory);
        int cacheSize = maxMemory / 8;
        mCacheBitmaps = new LruCache<Object, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(Object key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    public static ImageUtils get() {
        if (sImageUtils == null) {
            sImageUtils = new ImageUtils();
        }
        return sImageUtils;
    }

    public void putCache(Object key, Bitmap bitmap) {
        if (key != null && bitmap != null) {
            mCacheBitmaps.put(key, bitmap);
        }
    }

    public Bitmap getCache(Object key) {
        if (key != null) {
            return mCacheBitmaps.get(key);
        }
        return null;
    }

    private int getPlaceHolderRes() {
        int index = random.nextInt(8);
        return mPlaceHolder[index];
    }

    private GenericRequestBuilder getBuilder(String url) {
        return Glide.with(sContext)
                .load(url)
                .asBitmap()
                .dontAnimate();
    }

    /**
     * 加载图片
     *
     * @param imageView
     * @param url
     */
    public Target loadImage(ImageView imageView, String url) {
        if (imageView == null)
            return null;
        return getBuilder(url).placeholder(getPlaceHolderRes()).into(imageView);
    }

    /**
     * 加载图片
     *
     * @param imageView
     * @param url
     */
    public void loadImageSkipCache(ImageView imageView, String url) {
        if (imageView == null)
            return;
        final String signature = String.valueOf(System.currentTimeMillis());
        getBuilder(url).signature(new StringSignature(signature)).into(imageView);
    }

    /**
     * 从文件加入
     *
     * @param imageView
     * @param file
     */
    public void loadImage(ImageView imageView, File file) {
        if (imageView == null)
            return;
        Glide.with(sContext)
                .load(file)
                .asBitmap()
                .dontAnimate()
                .into(imageView);
    }

    /**
     * 加载图片
     *
     * @param imageView
     * @param drawableRes
     */
    public void loadImage(ImageView imageView, int drawableRes) {
        if (imageView == null)
            return;
        Glide.with(sContext).load(drawableRes).into(imageView);
    }

    /**
     * 加载图片
     *
     * @param imageView
     * @param url
     * @param resDrawable 默认占位图
     */
    public void loadImage(ImageView imageView, String url, int resDrawable) {
        if (imageView == null)
            return;
        getBuilder(url).placeholder(resDrawable).into(imageView);
    }

    public void loadImage(ImageView imageView, int drawableRes, int placeDrawable) {
        if (imageView == null)
            return;
        Glide.with(sContext).load(drawableRes).placeholder(placeDrawable).into(imageView);
    }

    /**
     * 获取资源尺寸
     *
     * @param resSize
     * @return 默认返回原始尺寸
     */
    private int getSize(int resSize) {
        if (resSize == 0) {
            return SimpleTarget.SIZE_ORIGINAL;
        } else {
            return ResUtils.getDimenPixRes(resSize);
        }
    }

    public void loadImage(String url, int width, int height, Target target) {
        getBuilder(url).override(width, height).into(target);
    }

    public void loadImage(String url, Target target) {
        getBuilder(url).into(target);
    }

    public void loadImage(int drawableRes, int width, int height, Target target) {
        Glide.with(sContext)
                .load(drawableRes)
                .asBitmap()
                .override(width, height)
                .into(target);
    }

    /**
     * 加载高斯模糊背景图片
     *
     * @param imageView
     * @param url
     */
    public void imageGauss(ImageView imageView, String url) {
        if (imageView != null) {
            Glide.with(sContext)
                    .load(url)
                    .crossFade(500)
                    .bitmapTransform(new BlurTransformation(sContext, url))
                    .into(imageView);
        }
    }

    public void imageGauss(final ImageView imageView, final Bitmap toTransform) {
        if (imageView != null) {
            Observable.just(toTransform)
                    .subscribeOn(Schedulers.newThread())
                    .map(new Function<Bitmap, Bitmap>() {
                        @Override
                        public Bitmap apply(Bitmap bitmap) throws Exception {
                            try {
                                final float prop = caculateSize(toTransform.getWidth(), toTransform.getHeight());
                                Bitmap temp = BitmapUtils.bitmapSetSize(toTransform, prop, prop);
                                bitmap = BitmapUtils.fastBlur(temp, 50);
                            } catch (OutOfMemoryError oom) {
                                oom.printStackTrace();
                            }
                            return bitmap;
                        }
                    }).observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new ResourceObserver<Bitmap>() {
                        @Override
                        public void onNext(Bitmap bitmap) {
                            imageView.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    private float caculateSize(int width, int height) {
        float widthProp = 360 / width;
        float heightProp = 540 / height;

        final float targetProp = Math.min(widthProp, heightProp);
        return targetProp >= 1 ? 1.0F : targetProp;
    }

}
