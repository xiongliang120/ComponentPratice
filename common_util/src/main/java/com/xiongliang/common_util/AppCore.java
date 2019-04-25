package com.xiongliang.common_util;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

public class AppCore {

    /**
     * 全局的Application
     */
    private static Application sApplication;

    /**
     * 是否开启调试模式
     */
    private static boolean sEnableDebug;

    private static boolean sEnableLogger;

    private AppCore() {
        throw new IllegalStateException("not support");
    }

    public static void init(Application application) {
        init(application, false);
    }

    public static void init(Application application, boolean isDebug) {
        sApplication = application;
        sEnableDebug = isDebug;
    }

    /**
     * 是否开启debug模式
     *
     * @param enable
     */
    public static void enableDebug(boolean enable) {
        sEnableDebug = enable;
    }

    public static boolean isDebugModel() {
        return sEnableDebug;
    }

    /**
     * 是否开启debug模式
     *
     * @param enable
     */
    public static void enableLogger(boolean enable) {
        sEnableLogger = enable;
    }

    public static boolean isLoggerModel() {
        return sEnableLogger;
    }

    /**
     * 获取全局的Application
     *
     * @return
     */
    public static Application getApplication() {
        if (sApplication == null) {
            throw new IllegalArgumentException("you should call AppCore.init to init first");
        }
        return sApplication;
    }

    public static Context getAppContext() {
        if (sApplication == null) {
            throw new IllegalArgumentException("you should call AppCore.init to init first");
        }
        return sApplication;
    }

    public static Resources getAppResources() {
        if (sApplication == null) {
            throw new IllegalArgumentException("you should call AppCore.init to init first");
        }
        return sApplication.getResources();
    }

    public static Object getSystemService(String serviceName) {
        return sApplication.getSystemService(serviceName);
    }

}
