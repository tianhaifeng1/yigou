package com.t.httplib.yigou.bean.req;

public class ReqShopInfo4 extends ReqTimeInfo {

    private String shopId;

    private String distype;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getDistype() {
        return distype;
    }

    public void setDistype(String distype) {
        this.distype = distype;
    }

    @Override
    public String toString() {
        return  "distype" + distype +
                "shopId" + shopId +
                "timestamp" + timestamp ;
    }
}
