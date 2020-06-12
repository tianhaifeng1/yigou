package com.t.httplib.yigou.bean.req;

/**
 * 品牌请求的参数Bean
 */
public class ReqBrandInfo extends ReqTimeInfo {

    //分类id
    private int relatedCatids;
    //店铺id
    private int shopId;

    public int getRelatedCatids() {
        return relatedCatids;
    }

    public void setRelatedCatids(int relatedCatids) {
        this.relatedCatids = relatedCatids;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "relatedCatids" + relatedCatids +
                "shopId" + shopId +
                "timestamp" + timestamp;
    }
}
