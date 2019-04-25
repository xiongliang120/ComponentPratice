package com.xiongliang.common_network.network;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.xiongliang.common_network.R;
import com.xiongliang.common_network.bean.BaseResponse;
import com.xiongliang.common_util.AppCore;
import com.xiongliang.common_util.LogUtils;
import com.xiongliang.common_util.ResUtils;

import io.reactivex.ObservableEmitter;
import okhttp3.Call;
import okhttp3.Response;

public class HttpObservables {
    private static final String TAG = "HttpCallBack";

    public static <T> void doPost(Call call, ObservableEmitter<T> observable, Class<T> clazz) {
        try {
            if (observable.isDisposed()) {
                //已经取消
            } else if (call == null) {
                notifyError(observable, new IllegalArgumentException("request call is null"));
            } else {
                Response response = call.execute();
                final int code = response.code();
                if (code == 200) {
                    final String body = response.body().string();
                    //输出调试信息
                    LogUtils.i(TAG, "Response: %s\r\n%s", call.request().url(), body);
                    //解析返回结果
                    if (clazz != null) {
                        BaseResponse baseResult = JSON.parseObject(body, BaseResponse.class);
                        if (baseResult == null) {
                            notifyError(observable, new HttpException(ErrorType.E_JSON_PARSE, ResUtils.getStringRes(R.string.error_json_parse, body)));
                        } else {
                            handleResponse(observable, baseResult, body, clazz);
                        }
                    }
                } else {
                    notifyError(observable, new HttpException(ErrorType.E_NETWORK, ResUtils.getStringRes(R.string.error_network, response.code())));
                }
            }
        } catch (Throwable e) {
            Log.d(TAG, "Error=" + e);
            if (AppCore.isDebugModel()) {
                notifyError(observable, new HttpException(ErrorType.E_UNKONW_EXCEPTION, e.getMessage() + ""));
            } else {
                notifyError(observable, new HttpException(ErrorType.E_NETWORK, "Network Error!"));
            }
        }
    }


    private static <T> void handleResponse(ObservableEmitter<T> observable, BaseResponse baseResult, String body, Class<T> clazz) {
        switch (baseResult.errno) {
            case ErrorType.E_OK:
                handleCorrectResponse(observable, baseResult, body, clazz);
                break;
            case ErrorType.E_TOKEN:
                //回到登录页
                break;
            case ErrorType.E_DELETED_USER:
                //回到登录页
                break;
            default:
                notifyError(observable, new HttpException(baseResult));
                break;
        }
    }


    /**
     * 处理正确的请求
     *
     * @param observable
     * @param baseResult
     * @param clazz
     * @param <T>
     */
    private static <T> void handleCorrectResponse(ObservableEmitter<T> observable, BaseResponse baseResult, String body, Class<T> clazz) {
        T data;
        //请求成功
        //后台没有返回data类型
        if (baseResult.data == null) {
            data = JSON.parseObject(body, clazz);
        } else {
            //data可能是json数组
            String dataStr = baseResult.data.startsWith("[") ? body : baseResult.data;
            data = JSON.parseObject(dataStr, clazz);
            if (data == null) {
                notifyError(observable, new NullPointerException("parse data is null !!"));
                return;
            }
        }
        //判定类型
        if (data.getClass().equals(clazz)) {
            notifyNext(observable, data);
            notifyComplete(observable);
        } else {
            notifyError(observable, new ClassCastException());
        }
    }


    /**
     * 回调下一步
     *
     * @param observable
     * @param value
     * @param <T>
     */
    private static <T> void notifyNext(ObservableEmitter<T> observable, T value) {
        if (!observable.isDisposed()) {
            observable.onNext(value);
        }
    }

    /**
     * 回调错误信息
     *
     * @param observable
     * @param throwable
     */
    private static void notifyError(ObservableEmitter observable, Throwable throwable) {
        if (!observable.isDisposed()) {
            observable.onError(throwable);
        }
    }

    /**
     * 回调完成处理
     *
     * @param observable
     */
    private static void notifyComplete(ObservableEmitter observable) {
        if (!observable.isDisposed()) {
            observable.onComplete();
        }
    }

}
