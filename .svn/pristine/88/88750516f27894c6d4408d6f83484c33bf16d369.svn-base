package com.t.httplib.yigou.bean.req;

import java.util.List;

/**
 * 商品订单支付信息请求参数
 */
public class ReqGoodsPayInfo extends ReqTimeInfo {

    protected String shopId;

    protected List<ReqGoodsPayInfoBean> goods;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public List<ReqGoodsPayInfoBean> getGoods() {
        return goods;
    }

    public void setGoods(List<ReqGoodsPayInfoBean> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "shopId" + shopId +
                "timestamp" + timestamp;
    }

    public static class ReqGoodsPayInfoBean {
        private String goodsId;
        private String attrStrId;
        private String totalNum;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getAttrStrId() {
            return attrStrId;
        }

        public void setAttrStrId(String attrStrId) {
            this.attrStrId = attrStrId;
        }

        public String getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

    }

}
