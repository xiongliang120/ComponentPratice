package com.xiongliang.common_network.network;

/**
 * 公共返回码
 *
 * @author jerry
 * @date 2016/03/21
 */
public class ErrorType {

    /**
     * 网络错误
     */
    public static final int E_NETWORK = -2;
    /**
     * json解析错误
     */
    public static final int E_JSON_PARSE = -3;
    /**
     * json解析错误
     */
    public static final int E_IO_EXCEPTION = -4;
    /**
     * 测试用 看错误提示
     */
    public static final int E_UNKONW_EXCEPTION = -999;
    /**
     * 正常
     */
    public static final int E_OK = 0;
    /**
     * 缺少参数
     */
    public static final int E_MISS_PARAM = 1;
    /**
     * 参数异常
     */
    public static final int E_INVAL_PARAM = 2;
    /**
     * 数据库错误
     */
    public static final int E_DATABASE = 3;
    /**
     * 服务器内部错误
     */
    public static final int E_INNER = 4;
    /**
     * token验证失败
     */
    public static final int E_TOKEN = 101;
    /**
     * 无操作权限
     */
    public static final int E_NO_PERM = 102;
    /**
     * 资源不存在
     */
    public static final int E_NOT_EXISTS = 103;

    /**
     * 图片大小异常（太小或太大>4M)
     */
    public static final int E_IMAGE_SIZE = 105;

    /**
     * 用户已被拉黑
     */
    public static final int E_DELETED_USER = 114;
}
