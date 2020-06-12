package com.t.httplib.yigou.bean.req;

/**
 * 请求商品列表数据的参数Bean
 */
public class ReqGoodsListInfo2 extends ReqPageInfo {

    //分类：0特卖；1新品
    private int types;
    //店铺id
    private int shopId;

    public int getTypes() {
        return types;
    }

    public void setTypes(int types) {
        this.types = types;
    }

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
                "timestamp" + timestamp +
                "types" + types
                ;
    }
}
