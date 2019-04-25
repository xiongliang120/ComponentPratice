package com.xiongliang.common_network.config;


import com.xiongliang.common_util.AppCore;

public class WebConfig {
    /**
     * 正式环境
     */
    public static String WEB_API = "http://www.baidu.com/api";  //http://api.xxx.cn/

    /**
     * 测试环境
     * one.live.nagezan.net
     */
    public static String WEB_DEV = "http://47.88.58.164:10005";

    /**
     * Web地址
     */
    public static String getWebAddress() {
        if (AppCore.isDebugModel()) {
            return WEB_DEV;
        } else {
            return WEB_API;
        }
    }
}
