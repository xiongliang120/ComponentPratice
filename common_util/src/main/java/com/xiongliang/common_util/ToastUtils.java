package com.xiongliang.common_util;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.text.TextUtils;
import android.widget.Toast;


public class ToastUtils {
    private static Context getAppContext() {
        return AppCore.getAppContext();
    }

    /**
     * 调试模式下可显示
     *
     * @param msg
     */
    public static void showDebug(String msg) {
        if (AppCore.isDebugModel()) {
            Toast.makeText(getAppContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 调试模式下可显示
     *
     * @param resId
     */
    public static void showDebug(@IntegerRes int resId) {
        if (AppCore.isDebugModel()) {
            final String text = ResUtils.getStringRes(resId);
            Toast.makeText(getAppContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 短暂显示
     *
     * @param msg
     */
    public static void showShort(CharSequence msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(getAppContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 短暂显示
     *
     * @param resId
     */
    public static void showShort(int resId) {
        final String text = ResUtils.getStringRes(resId);
        Toast.makeText(getAppContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示
     *
     * @param msg
     */
    public static void showLong(CharSequence msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(getAppContext(), msg, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 短暂显示
     *
     * @param resId
     */
    public static void showLong(int resId) {
        final String text = ResUtils.getStringRes(resId);
        Toast.makeText(getAppContext(), text, Toast.LENGTH_LONG).show();
    }
}
