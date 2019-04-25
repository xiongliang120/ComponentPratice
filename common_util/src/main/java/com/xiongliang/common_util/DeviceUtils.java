package com.xiongliang.common_util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;



import java.security.MessageDigest;


public class DeviceUtils {

    private static final String TAG = "DevicesUtils";


    private static DisplayMetrics mDisplayMetrics;

    private static Context getAppContext() {
        return AppCore.getAppContext();
    }

    /**
     * 获取设备的宽和高
     *
     * @return
     */
    public static DisplayMetrics getDisplayMetrics() {
        if (mDisplayMetrics == null) {
            mDisplayMetrics = getAppContext().getResources().getDisplayMetrics();
        }
        return mDisplayMetrics;
    }

    public static int getDisplayWidth() {
        if (mDisplayMetrics == null) {
            mDisplayMetrics = getAppContext().getResources().getDisplayMetrics();
        }
        return mDisplayMetrics.widthPixels;
    }

    public static int getDisplayHeight() {
        if (mDisplayMetrics == null) {
            mDisplayMetrics = getAppContext().getResources().getDisplayMetrics();
        }
        return mDisplayMetrics.heightPixels;
    }

    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = AppCore.getAppResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = AppCore.getAppResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getRealHeight(Context context) {
        if (context instanceof Activity && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Point point = new Point();
            ((Activity) context).getWindowManager().getDefaultDisplay().getRealSize(point);
            return point.y;
        } else {
            return getDisplayHeight() + getStatusBarHeight();
        }
    }

    public static String getDeviceID() {

        StringBuilder builder = new StringBuilder();
        builder.append(Build.BOARD);
        builder.append(Build.BRAND);
        builder.append(Build.CPU_ABI);
        builder.append(Build.DEVICE);
        builder.append(Build.DISPLAY);
        builder.append(Build.HOST);
        builder.append(Build.ID);
        builder.append(Build.MANUFACTURER);
        builder.append(Build.MODEL);
        builder.append(Build.PRODUCT);
        builder.append(Build.TAGS);
        builder.append(Build.TYPE);
        builder.append(Build.USER);

        String result = builder.toString();
        try {
            String serialStr = android.os.Build.class.getField("SERIAL").get(null).toString();
            result = builder.toString() + serialStr;
            Log.d(TAG, serialStr);
        } catch (Exception exception) {

        }
        return MD5(result);
    }

    /**
     * 获取MD5
     *
     * @param str
     * @return
     */
    public static String MD5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            char[] charArray = str.toCharArray();
            byte[] byteArray = new byte[charArray.length];

            for (int i = 0; i < charArray.length; i++) {
                byteArray[i] = (byte) charArray[i];
            }
            byte[] md5Bytes = md5.digest(byteArray);

            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
