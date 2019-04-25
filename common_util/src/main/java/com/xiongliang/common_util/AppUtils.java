package com.xiongliang.common_util;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;



import java.util.List;


public class AppUtils {

    private static final String CHANNEL_NAME = "UMENG_CHANNEL";

    private static int sVersionCode = 0;

    private static Context getAppContext() {
        return AppCore.getAppContext();
    }

    /**
     * 安全的启动APP
     *
     * @param ctx
     * @param intent
     */
    public static boolean launchApp(Context ctx, Intent intent) {
        if (ctx == null)
            throw new NullPointerException("ctx is null");
        try {
            ctx.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            LogUtils.e(e);
            return false;
        }
    }

    /**
     * Launch From Notifycation
     *
     * @param context
     * @param intent
     */
    public static void launchAppWithPending(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        Context ctx = context.getApplicationContext();
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        try {
            pendingIntent.send(ctx, 0, intent);
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    /**
     * 带有动画切换效果的启动Activity
     *
     * @param ctx
     * @param intent
     * @return
     */
    public static boolean launchAppWithAnim(Context ctx, Intent intent) {
        return launchAppWithAnim(ctx, intent, android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /**
     * 带有动画切换效果的启动Activity
     *
     * @param ctx
     * @param intent
     * @param enterAnim
     * @param exitAnim
     * @return
     */
    public static boolean launchAppWithAnim(Context ctx, Intent intent, int enterAnim, int exitAnim) {
        if (ctx == null)
            throw new NullPointerException("ctx is null");
        try {
            ctx.startActivity(intent);
            if (ctx instanceof Activity) {
                ((Activity) ctx).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            return true;
        } catch (ActivityNotFoundException e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static boolean startActivityForResult(Activity ctx, Intent intent, int code) {
        if (ctx == null)
            throw new NullPointerException("ctx is null");
        try {
            ctx.startActivityForResult(intent, code);
            return true;
        } catch (ActivityNotFoundException e) {
            LogUtils.e(e);
            return false;
        }
    }

    /**
     * Fragment和Activity均可唤起
     *
     * @param ctx
     * @param intent
     * @param requestCode
     * @return
     */
    public static boolean launchAppForResult(Activity ctx, Intent intent, int requestCode) {
        if (ctx == null)
            throw new NullPointerException("ctx is null");
        try {
            ctx.startActivityForResult(intent, requestCode);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 启动APP
     *
     * @param ctx   上下文
     * @param clazz 类名
     */
    public static void launchApp(Context ctx, Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("the parameter is null");
        Intent intent = new Intent(ctx, clazz);
        launchApp(ctx, intent);
    }

    /**
     * 启动APP
     *
     * @param ctx         上下文
     * @param packageName 包名
     * @param className   类名
     */
    public static void launchApp(Context ctx, String packageName, String className) {
        if (packageName == null || className == null)
            throw new NullPointerException("the parameter is null");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName(packageName, className));
        launchApp(ctx, intent);
    }

    /**
     * 结束当前页面
     *
     * @param ctx
     */
    public static void finish(Context ctx) {
        if (ctx == null)
            throw new NullPointerException("ctx is null");
        if (ctx instanceof Activity) {
            ((Activity) ctx).finish();
        }
    }

    /**
     * 卸载APP
     *
     * @param ctx        上下文参数
     * @param packageUri 包URI
     */
    public static void uninstallApp(Context ctx, Uri packageUri) {
        if (ctx == null || packageUri == null)
            throw new NullPointerException("the parameter is null");
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageUri);
        launchApp(ctx, uninstallIntent);
    }

    /**
     * 卸载APP
     *
     * @param ctx     上下文
     * @param pkgName 包名
     */
    public static void uninstallApp(Context ctx, String pkgName) {
        if (pkgName == null)
            throw new NullPointerException("the parameter is null");
        Uri packageUri = Uri.parse("package:" + pkgName);
        uninstallApp(ctx, packageUri);
    }

    /**
     * 启动系统设置页面
     *
     * @param ctx 上下文
     */
    public static void launchSettings(Context ctx) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        launchApp(ctx, intent);
    }

    /**
     * 启动系统浏览器页面
     *
     * @param ctx 上下文
     */
    public static void launchBrowser(Context ctx, String url) {
        Uri browserUri = Uri.parse(url);
        if (null != browserUri) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, browserUri);
            browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            launchApp(ctx, browserIntent);
        }
    }

    /**
     * 获取包信息
     *
     * @return 包信息
     */
    public static PackageInfo getPackageInfo(Context ctx) {
        final PackageManager pm = ctx.getPackageManager();
        try {
            return pm.getPackageInfo(ctx.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取包信息
     *
     * @return 包信息
     */
    public static PackageManager getPackageManager(Context ctx) {
        return ctx.getPackageManager();
    }

    /**
     * 获取指定APP上下文
     *
     * @param context     上下文
     * @param packageName 包名
     * @return
     */
    public static Context getAppContext(Context context, String packageName) {
        if (context == null || packageName == null)
            throw new NullPointerException("the parameter is null");
        Context ctx = null;
        try {
            ctx = context.createPackageContext(packageName, Context.CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return ctx;
    }

    /**
     * 判断是否为系统APP
     *
     * @return
     */
    public static boolean isSystemApp(Context ctx, String packageName) {
        PackageManager packageManager = ctx.getPackageManager();
        boolean isSystemApp = false;
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            if (applicationInfo != null) {
                isSystemApp = ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0)
                        || ((applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return isSystemApp;
    }

    /**
     * 获取版本Code
     *
     * @param packageName
     * @return
     */
    public static int getVersionCode(String packageName) {
        final PackageManager packageManager = getAppContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int getVersionCode() {
        if (sVersionCode > 0)
            return sVersionCode;
        final PackageManager packageManager = getAppContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getAppContext().getPackageName(), 0);
            sVersionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return sVersionCode;
    }

    /**
     * 获取VersonName
     *
     * @return
     */
    public static String getVersionName() {
        try {
            final PackageManager packageManager = getAppContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(getAppContext().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取包名
     *
     * @return
     */
    public static String getPackageName() {
        return getAppContext().getPackageName();
    }

    /**
     * com.tencent.mobileqq
     *
     * @param packageName
     * @return
     */
    public static boolean isAppExist(String packageName) {
        PackageManager packageManager = getAppContext().getPackageManager();
        List<ApplicationInfo> applicationInfos = packageManager.getInstalledApplications(0);
        for (ApplicationInfo info : applicationInfos) {
            if (TextUtils.equals(info.packageName, packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取渠道
     *
     * @return
     */
    public static String getChannel() {
        try {
            PackageManager packageManager = getAppContext().getPackageManager();
            ApplicationInfo appinfo = packageManager.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            if (appinfo.metaData != null) {
                return String.valueOf(appinfo.metaData.get(CHANNEL_NAME));
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e(e);
        }
        return "";
    }

    public static String getMeta(String name) {
        try {
            PackageManager packageManager = getAppContext().getPackageManager();
            ApplicationInfo appinfo = packageManager.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            if (appinfo.metaData != null) {
                return String.valueOf(appinfo.metaData.get(name));
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e(e);
        }
        return "";
    }

}
