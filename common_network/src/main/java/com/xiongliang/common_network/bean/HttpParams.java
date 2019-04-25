package com.xiongliang.common_network.bean;


import com.xiongliang.common_util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpParams extends BaseParams{
    /**
     * 基本参数
     */
    public BaseParams mBaseParams;

    /**
     * 参数键值对
     */
    private HashMap<String, Object> mapData;

    public HttpParams() {
        mBaseParams = BaseParams.newParams();
    }

    private HttpParams(Object object) {
        mBaseParams = BaseParams.newParams(object);
    }

    public static HttpParams get() {
        return new HttpParams();
    }

    public static HttpParams get(Object object) {
        return new HttpParams(object);
    }

    /**
     * 添加请求参数
     *
     * @param key
     * @param obj
     */
    public HttpParams addParam(String key, Object obj) {
        if (mapData == null) {
            mapData = new HashMap<>();
            mBaseParams.data = mapData;
        }
        mapData.put(key, obj);
        return this;
    }


    /**
     * 设置请求实体
     * 推荐
     *
     * @param obj
     */
    public HttpParams setEntity(Object obj) {
        mBaseParams.data = obj;
        return this;
    }

    /**
     * 格式化输出参数
     *
     * @return
     */
    public String toParams() {
        return JsonUtils.toJSONString(mBaseParams);
    }

    public Map<String, Object> getData() {
        return mapData;
    }
}
