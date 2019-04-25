package com.xiongliang.common_network.network;

import com.xiongliang.common_network.R;

public enum ErrorCode {

    /**
     * 服务器返回错误代码
     */
    ERROR_OK(0),    //返回正常
    ERROR_VIDEO_DELETE(2),    //返回正常
    ERROR_TOKEN(101),    //Token验证失败
    ERROR_BLACK_USER(114),  //用户被拉黑

    ERROR_CHAT_OVER_LIMIT(106),  //私信次数达到上限
    ERROR_NOBALANCE(125),  //余额不足
    ERROR_UNLOCK_NOBALANCE(126),  //余额不足


    /**
     * Java服务器返回
     */
    ERROR_AUTHING(1004, R.string.error_unknown_host),   //请求地址错误!

    /**
     * 本地错误代码
     */
    ERROR_DATA_PARSE(2000, R.string.error_data_parse),   //数据解析错误!
    ERROR_RESP_NULL(2001, R.string.error_resp_null),   //请求响应为空!
    ERROR_DATA_NULL(2002, R.string.error_data_null),       //数据内容为空!
    ERROR_DATA_FORMAT(2003, R.string.error_data_format),   //数据格式错误!
    ERROR_UNKNOWN_HOST(2004, R.string.error_unknown_host),   //请求地址错误!
    ERROR_SOCKET_TIMEOUT(2005, R.string.error_socket_timeout),   //请求数据超时!
    ERROR_CONNECT(2006, R.string.error_connect);   //连接异常!


    private int code;

    private int desc;

    ErrorCode(int value) {
        this.code = value;
    }

    ErrorCode(int value, int desc) {
        this.code = value;
        this.desc = desc;
    }

    public int code() {
        return code;
    }

    public String desc() {
      //  return ResUtils.getStringRes(desc);
        return "";
    }

    @Override
    public String toString() {
        return code + " : " + desc();
    }

}
