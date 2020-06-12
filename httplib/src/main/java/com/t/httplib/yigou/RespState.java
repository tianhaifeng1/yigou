package com.t.httplib.yigou;

/**
 * 请求相应状态码
 * <p>
 * 需要新增加一个微信登陆失败的状态码，不能用401，（401有特殊含义）
 */
@Deprecated
public class RespState {

    //重新登录
    public static final int POST_LOGIN_EXPRIES = 401;
    //请求超时
    public static final int POST_TIMEOUT = 300;
    //秘钥错误
    public static final int POST_SIGN_ERROR = 402;
    //参数错误
    public static final int POST_PARAM_ERROR = 403;
    //支付余额不足
    public static final int POST_REFRESH = 203;
    //非法操作
    public static final int POST_ERROR = 202;

    //微信登陆失败
    public static final int POST_LOGIN_WX = 310;


}
