package com.xiongliang.common_network.bean;

import android.os.Build;

import com.xiongliang.common_util.AppUtils;
import com.xiongliang.common_util.DeviceUtils;


public class BaseParams {
    /**
     * 终端类型
     * 0=Android
     * 1=iOS
     */
    public int term = 0;

    /**
     * 当前版本
     * 只初始化一次
     */
    public int version = AppUtils.getVersionCode();

    /**
     * 设备ID
     */
    public String udid = DeviceUtils.getDeviceID();

    /**
     * 手机型号
     */
    public String model = Build.MODEL;

    /**
     * 包名
     */
    public String app = AppUtils.getPackageName();

    /**
     * 时间戳
     */
    public long ts = System.currentTimeMillis();

    /**
     * 数据体
     */
    public Object data;

    public BaseParams() {

    }

    private BaseParams(Object object) {
        this.data = object;
    }

    public static BaseParams newParams() {
        return new BaseParams();
    }

    public static BaseParams newParams(Object object) {
        return new BaseParams(object);
    }

}
