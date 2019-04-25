package com.xiongliang.common_network.network;


import com.xiongliang.common_network.bean.BaseResponse;

public class HttpException extends Exception {

    public int code;

    public String error;

    public String data;

    public HttpException(BaseResponse baseResult) {
        this.code = baseResult.errno;
        this.error = baseResult.getDesc();
        this.data = baseResult.data;
    }

    public HttpException(int code, String error) {
        super(code + " " + error);
        this.code = code;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
