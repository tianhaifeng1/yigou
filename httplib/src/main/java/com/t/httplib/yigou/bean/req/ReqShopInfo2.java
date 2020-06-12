package com.t.httplib.yigou.bean.req;

public class ReqShopInfo2 extends ReqPageInfo {

    private String shopId;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "page" + page +
                "pageSize" + pageSize +
                "shopId" + shopId +
                "timestamp" + timestamp ;
    }
}
