package com.xiongliang.common_network.network;

import com.xiongliang.common_network.bean.HttpParams;
import com.xiongliang.common_network.config.WebConfig;
import com.xiongliang.common_util.LogUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtils {
    public final static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    /**
     * 连接超时
     */
    private static final long CONNECT_TIMEOUT_MILLIS = 10;

    /**
     * 读取超时
     */
    private static final long READ_TIMEOUT_MILLIS = 10;

    /**
     * 写入超时
     */
    private static final long WRITE_TIMEOUT_MILLIS = 10;

    private static final String TAG = "HttpUtils";

    /**
     * 网络请求客户端
     */
    private static OkHttpClient okHttpClient;

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (HttpUtils.class) {
                if (okHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder()
                            .connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.SECONDS)
                            .readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIMEOUT_MILLIS, TimeUnit.SECONDS);
                    okHttpClient = builder.build();
                }
            }
        }
        return okHttpClient;
    }



    /**
     * Post请求
     *
     * @param url
     * @param httpParams
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Observable<T> doPost(final String url, final HttpParams httpParams, final Class<T> clazz) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> observable) throws Exception {
                final String resultPath = obtainPath(url);
                final String paramStr = httpParams.toParams();
                LogUtils.i(TAG, "Request: %s\r\n%s", resultPath, paramStr);
                RequestBody requestBody = RequestBody.create(JSON, paramStr);
                Request request = new Request.Builder().url(resultPath).post(requestBody).build();
                Call call = getOkHttpClient().newCall(request);
                HttpObservables.doPost(call, observable, clazz);
            }
        }).subscribeOn(Schedulers.io());
    }


    /**
     * Get请求
     *
     * @param url
     * @param mapParams
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Observable<T> doGet(final String url, final Map<String, Object> mapParams, final Class<T> clazz) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> observable) throws Exception {
                final String requestPath = obtainPath(url);
                final String resultPath = getResultRequestPath(requestPath, mapParams);
                LogUtils.i(TAG, "Request: %s", resultPath);
                Request request = new Request.Builder().url(requestPath).get().build();
                Call call = getOkHttpClient().newCall(request);
                HttpObservables.doPost(call, observable, clazz);
            }
        }).subscribeOn(Schedulers.io());
    }

    private static String getResultRequestPath(String url, Map<String, Object> map) {
        if (map == null)
            return "";
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        StringBuilder builder = new StringBuilder(url);
        builder.append("?");
        boolean hasNext = iterator.hasNext();
        while (hasNext) {
            Map.Entry<String, Object> entry = iterator.next();
            builder.append(entry.getKey()).append("=").append(entry.getValue());
            if (hasNext = iterator.hasNext()) {
                //这里是=赋值 不是==判断
                builder.append("&");
            }
        }
        return builder.toString();
    }

    /**
     * 包装请求路径
     *
     * @param url
     * @return
     */
    private static String obtainPath(String url) {
        String resultPath;
        if (!url.startsWith("http")) {
            resultPath = WebConfig.getWebAddress() + url;
        } else {
            resultPath = url;
        }
        return resultPath;
    }




















}
