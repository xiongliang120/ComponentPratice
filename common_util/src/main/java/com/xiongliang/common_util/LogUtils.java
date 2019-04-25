package com.xiongliang.common_util;

import android.util.Log;

public class LogUtils {
     public static String TAG = "LogUtils";
    /**
     * 对应多个参数
     *
     * @param tag
     * @param content
     * @param args
     */
    public static void v(String tag, String content, Object... args) {
        if (AppCore.isLoggerModel() && args != null && args.length > 0) {
            final String msg = String.format(content, args);
            Log.v(tag, msg);
        }
    }

    public static void i(String tag, String content, Object... args) {
        if (AppCore.isLoggerModel() && args != null && args.length > 0) {
            final String msg = String.format(content, args);
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String content, Object... args) {
        if (AppCore.isLoggerModel() && args != null && args.length > 0) {
            Log.d(tag, String.format(content, args));
        }
    }

    public static void e(String tag, String content, Object... args) {
        if (AppCore.isLoggerModel() && args != null && args.length > 0) {
            Log.e(tag, String.format(content, args));
        }
    }


    /**
     * 基本包装
     *
     * @param tag
     * @param content
     */
    public static void v(String tag, Object content) {
        if (AppCore.isLoggerModel()) {
            Log.v(tag, String.valueOf(content));
        }
    }

    public static void i(String tag, Object content) {
        if (AppCore.isLoggerModel()) {
            Log.i(tag, String.valueOf(content));
        }
    }

    public static void d(String tag, Object content) {
        if (AppCore.isLoggerModel()) {
            Log.d(tag, String.valueOf(content));
        }
    }

    public static void e(String tag, Object content) {
        if (AppCore.isLoggerModel()) {
            Log.e(tag, String.valueOf(content));
        }
    }

    /**
     * 打印异常信息
     *
     * @param tag
     * @param exception
     */
    public static void e(String tag, Exception exception) {
        if (AppCore.isLoggerModel() && exception != null) {
            e(tag, exception.toString());
        }
    }

    /**
     * 打印异常信息
     *
     * @param tag
     * @param throwable
     */
    public static void e(String tag, Throwable throwable) {
        if (AppCore.isLoggerModel() && throwable != null) {
            e(tag, throwable.toString());
        }
    }

    public static void e(Exception content) {
        if (AppCore.isLoggerModel()) {
            e(TAG, content);
        }
    }

    public static void e(Throwable content) {
        if (AppCore.isLoggerModel()) {
            e(TAG, content);
        }
    }
}
