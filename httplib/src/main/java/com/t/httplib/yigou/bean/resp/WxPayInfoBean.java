package com.t.httplib.yigou.bean.resp;

import com.google.gson.annotations.SerializedName;

public class WxPayInfoBean {

    /**
     * sign : 1EC29023F9294B32D4F272EE8B6B392D
     * timeStamp : 1560239923
     * package : Sign=WXPay
     * partnerId : 1518950031
     * appid : wxe73ebab3e07e1b57
     * nonceStr : YBtKp14lwj8SJD9fmKOYLDtYGKQwpCak
     * prepayId : wx111558446089326113a4bcaa1851220500
     */

    private String sign;
    private String timeStamp;
    @SerializedName("package")
    private String packageX;
    private String partnerId;
    private String appid;
    private String nonceStr;
    private String prepayId;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }
}
