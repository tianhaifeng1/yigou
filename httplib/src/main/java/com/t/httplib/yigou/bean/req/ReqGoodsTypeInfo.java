package com.t.httplib.yigou.bean.req;

/**
 * 请求商品分类的参数Bean
 */
public class ReqGoodsTypeInfo extends ReqTimeInfo {

    private int apply;

    private int shopId;

    public int getApply() {
        return apply;
    }

    public void setApply(int apply) {
        this.apply = apply;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "apply" + apply +
                "shopId" + shopId +
                "timestamp" + timestamp;
    }
}
