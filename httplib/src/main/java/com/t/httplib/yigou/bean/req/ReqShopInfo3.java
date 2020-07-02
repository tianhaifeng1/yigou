package com.t.httplib.yigou.bean.req;

public class ReqShopInfo3 extends ReqTimeInfo {

    private String shopId;

    public String getShopId() {
        return shopId;
    }


    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "shopId" + shopId +
                "timestamp" + timestamp ;
    }
}
