package com.xiongliang.common_util;

import android.content.Context;
import android.content.SharedPreferences;


public class SPUtils {

    /**
     * 本地配置名称
     */
    private static final String SP_NAME = "com.fungo.xplayer";

    private static Context getAppContext() {
        return AppCore.getAppContext();
    }

    private static SharedPreferences getSharedPreferences(String name) {
        return getAppContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    private static SharedPreferences getSharedPreferences() {
        return getAppContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor() {
        return getSharedPreferences().edit();
    }

    /**
     * 保存信息
     *
     * @param key
     * @param value
     * @return
     */
    public synchronized static boolean put(String key, Object value) {
        if (value != null) {
            try {
                SharedPreferences.Editor editor = getEditor();
                if (value instanceof Long) {
                    editor.putLong(key, (Long) value);
                } else if (value instanceof Integer) {
                    editor.putInt(key, (Integer) value);
                } else if (value instanceof Float) {
                    editor.putFloat(key, (Float) value);
                } else if (value instanceof Boolean) {
                    editor.putBoolean(key, (Boolean) value);
                } else if (value instanceof String) {
                    editor.putString(key, (String) value);
                } else {
                    editor.putString(key, value.toString());
                }
                return editor.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 读取数据
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        return getString(key, null);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static Long getLong(String key) {
        return getLong(key, 0);
    }

    public static Integer getInteger(String key) {
        return getInteger(key, 0);
    }

    public static Float getFloat(String key) {
        return getFloat(key, 0);
    }

    public synchronized static String getString(String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    public synchronized static boolean getBoolean(String key, boolean defaultValue) {
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    public synchronized static Long getLong(String key, long defaultValue) {
        return getSharedPreferences().getLong(key, defaultValue);
    }

    public synchronized static Integer getInteger(String key, int defaultValue) {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    public synchronized static Float getFloat(String key, float defaultValue) {
        return getSharedPreferences().getFloat(key, defaultValue);
    }

    /**
     * 清除所有
     *
     * @return
     */
    public synchronized static boolean clearAll() {
        try {
            SharedPreferences.Editor editor = getEditor();
            editor.clear();
            return editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除某个
     *
     * @param key
     * @return
     */
    public synchronized static boolean clear(String key) {
        try {
            SharedPreferences.Editor editor = getEditor();
            editor.remove(key);
            return editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
