package com.xiongliang.common_network.network;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SchedulerUtils {
    /**
     * IO操作
     *
     * @param run
     * @return
     */
    public static Disposable runOnIoThread(Runnable run) {
        return Schedulers.io().scheduleDirect(run);
    }

    /**
     * IO操作
     *
     * @param run
     * @param milliseconds
     * @return
     */
    public static Disposable runOnIoThreadDelayed(Runnable run, long milliseconds) {
        return Schedulers.io().scheduleDirect(run, milliseconds, TimeUnit.MILLISECONDS);
    }

    /**
     * 用于计算任务,如事件循环或和回调处理,不要用于IO操作
     *
     * @param run
     * @return
     */
    public static Disposable runOnComputeThreadDelayed(Runnable run) {
        return Schedulers.computation().scheduleDirect(run);
    }

    /**
     * 用于计算任务,如事件循环或和回调处理,不要用于IO操作
     *
     * @param run
     * @param delay
     * @param timeUnit
     */
    public static Disposable runOnComputeThreadDelayed(Runnable run, long delay, TimeUnit timeUnit) {
        return Schedulers.computation().scheduleDirect(run, delay, timeUnit);
    }

    /**
     * 新线程执行
     *
     * @param run
     */
    public static Disposable runOnNewThread(Runnable run) {
        return Schedulers.newThread().scheduleDirect(run);
    }

    /**
     * 新线程延迟执行
     *
     * @param run
     * @param delay
     * @param timeUnit
     */
    public static Disposable runOnNewThreadDelayed(Runnable run, long delay, TimeUnit timeUnit) {
        return Schedulers.newThread().scheduleDirect(run, delay, timeUnit);
    }

    /**
     * 主线程操作选择这个
     *
     * @param run
     */
    public static void runInMain(Runnable run) {
        AndroidSchedulers.mainThread().scheduleDirect(run);
    }


    /**
     * 在主线程执行
     *
     * @param run
     * @return
     */
    public static Disposable runOnUiThread(Runnable run) {
        return AndroidSchedulers.mainThread().scheduleDirect(run);
    }

    /**
     * 在主线程执行
     *
     * @param run
     * @return
     */
    public static Disposable runOnUiThread(Runnable run, long delayMillis) {
        return AndroidSchedulers.mainThread().scheduleDirect(run, delayMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * 在主线程执行
     *
     * @param run
     * @param delay
     * @param timeUnit
     * @return
     */
    public static Disposable runOnUiThreadDelayed(Runnable run, long delay, TimeUnit timeUnit) {
        return AndroidSchedulers.mainThread().scheduleDirect(run, delay, timeUnit);
    }

    public static void dispose(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
