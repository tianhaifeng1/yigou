package com.t.httplib.yigou.bean.req;

public class ReqGoodsInfo4 extends ReqPageInfo {
    private String shopId;

    private String activityId;

    @Override
    public String toString() {
        return  "activityId" + activityId +
                "page" + page +
                "pageSize" + pageSize +
                "shopId" + shopId +
                "timestamp" + timestamp ;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
}
