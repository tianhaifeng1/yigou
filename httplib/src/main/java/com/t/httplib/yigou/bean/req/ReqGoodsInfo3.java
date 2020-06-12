package com.t.httplib.yigou.bean.req;

/**
 * 请求商品列表数据的参数Bean
 */
public class ReqGoodsInfo3 extends ReqPageInfo {

    //店铺id
    private int shopId;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "page" + page +
                "pageSize" + pageSize +
                "shopId" + shopId +
                "timestamp" + timestamp
                ;
    }
}
