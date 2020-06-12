package com.t.httplib.yigou.bean;

import java.io.Serializable;

public class WechatCodeBean implements Serializable{
    /**
     "access_token":"17_yoRRSTXrwOT4xtIj8cDH1yeZEuXZ-hoBDkmoFmM6fh4upwBXgmX6GfpKv5RJk7uFPisH6obKdKZThaI6zrNkfw",
     "expires_in":7200,
     "refresh_token":"17_T2UTiJ1n5oRzv6aQ3PkuaHtLz26dW_QLMhm8iKVL69wp3jxH1SQMA0g1oadpEJgKDoTMHOsiJBo5kB05SiHvjg",
     "openid":"oM0SJ1ORKA6xRHAyUvc4gpwuDet4",
     "scope":"snsapi_userinfo",
     "unionid":"o_ZQV1nordBKtFC2LaibJejqqIwA"
     */
    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
