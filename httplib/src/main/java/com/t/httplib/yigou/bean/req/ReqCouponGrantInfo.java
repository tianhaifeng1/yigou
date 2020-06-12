package com.t.httplib.yigou.bean.req;

/**
 * 领取优惠卷的请求参数
 */
public class ReqCouponGrantInfo extends ReqTimeInfo {

    private String couponId;

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    @Override
    public String toString() {
        return  "couponId" + couponId +
                "timestamp" + timestamp;
    }
}
