package com.t.httplib.yigou.bean.req;

public class ReqShopInfo3 extends ReqTimeInfo {

    private String shopId;

    public String getShopId() {
        return shopId;
    }

    private String distype;

    public String getDistype() {
        return distype;
    }

    public void setDistype(String distype) {
        this.distype = distype;
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
