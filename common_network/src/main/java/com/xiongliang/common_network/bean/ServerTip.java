package com.xiongliang.common_network.bean;


import com.xiongliang.common_network.network.ErrorCode;

public class ServerTip {
    /**
     * 错误码
     */
    public int errno;

    /**
     * 错误描述
     */
    public String desc;

    public ServerTip() {

    }

    public ServerTip(ErrorCode errorCode) {
        this.errno = errorCode.code();
        this.desc = errorCode.desc();
    }

    public ServerTip(int errno, String desc) {
        this.errno = errno;
        this.desc = desc;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return errno + " : " + getDesc();
    }


}
