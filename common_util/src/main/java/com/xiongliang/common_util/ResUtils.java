package com.xiongliang.common_util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;


public class ResUtils {
    private static Context getAppContext() {
        return AppCore.getApplication();
    }

    public static String getStringRes(int id) {
        try {
            return getAppContext().getResources().getString(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getStringRes(int id, Object... args) {
        try {
            return getAppContext().getResources().getString(id, args);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取 String[] 值. 如果id对应的资源文件不存在, 则返回 null.
     *
     * @param id
     * @return
     */
    public static String[] getStringArrayRes(int id) {
        try {
            return getAppContext().getResources().getStringArray(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取dimension px值. 如果id对应的资源文件不存在, 则返回 -1.
     *
     * @param id
     * @return
     */
    public static int getDimenPixRes(int id) {
        try {
            return getAppContext().getResources().getDimensionPixelOffset(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取dimension float形式的 px值. 如果id对应的资源文件不存在, 则返回 -1.
     *
     * @param id
     * @return
     */
    public static float getDimenFloatPixRes(int id) {
        try {
            return getAppContext().getResources().getDimension(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取 color 值. 如果id对应的资源文件不存在, 则返回 -1.
     *
     * @param id
     * @return
     */
    @ColorInt
    public static int getColorRes(int id) {
        try {
            return ContextCompat.getColor(getAppContext(), id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取 Drawable 对象. 如果id对应的资源文件不存在, 则返回 null.
     *
     * @param id
     * @return
     */
    public static Drawable getDrawableRes(int id) {
        try {
            return ContextCompat.getDrawable(getAppContext(), id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取资源
     *
     * @return
     */
    public static Resources getResources() {
        return getAppContext().getResources();
    }

    /**
     * 根据名称获取资源id
     *
     * @param name
     * @return
     */
    public static int getDrawableByName(String name) {
        return getResources().getIdentifier(name, "drawable", getAppContext().getPackageName());
    }

}
