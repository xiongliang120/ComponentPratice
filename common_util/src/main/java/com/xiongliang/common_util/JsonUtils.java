package com.xiongliang.common_util;

import android.net.Uri;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JsonUtils {
    /**
     * 日志标签
     */
    private static final String TAG = "JsonUtils";

    public static String toJSONString(Object object) {
        try {
            String json = JSON.toJSONString(object);
            return json;
        } catch (JSONException e) {
            LogUtils.e(TAG, "JSONException: " + object);
        }

        return null;
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        try {
            T result = JSON.parseObject(text, clazz);//mGson.fromJson(text, clazz);
            return result;
        } catch (JSONException e) {
            LogUtils.e(TAG, "JSONException: " + text);
        }
        return null;
    }

    public static <T> T parseObject(String text, Type clazz) {
        try {
            return JSON.parseObject(text, clazz);
        } catch (JSONException e) {
            LogUtils.e(TAG, "JSONException: " + text);
        }
        return null;
    }

    public static <T> T parseObject(String text, TypeReference<T> clazz) {
        try {
            return JSON.parseObject(text, clazz);
        } catch (JSONException e) {
            LogUtils.e(TAG, "JSONException: " + text);
        }
        return null;
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        try {
            return JSON.parseArray(text, clazz);
        } catch (JSONException e) {
            LogUtils.e(TAG, "JSONException: " + text);
        }
        return null;
    }

    /**
     * 解析Uri
     *
     * @param uri
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(Uri uri, Class<T> clazz) {
        try {
            Set<String> parameter = uri.getQueryParameterNames();
            Iterator<String> iterator = parameter.iterator();
            HashMap<String, String> map = new HashMap<>();
            while (iterator.hasNext()) {
                final String key = iterator.next();
                map.put(key, uri.getQueryParameter(key));
            }
            final String result = JsonUtils.toJSONString(map);
            return JsonUtils.parseObject(result, clazz);
        } catch (Exception e) {
            LogUtils.e(TAG, "JSONException: " + uri);
        }
        return null;
    }
}
