package com.t.databaselib.bean;

import java.util.List;

public class ReqCartDeleteInfo {

    protected List<ReqCartDeleteInfoBean> goods;
    protected String timestamp;


    public List<ReqCartDeleteInfoBean> getGoods() {
        return goods;
    }

    public void setGoods(List<ReqCartDeleteInfoBean> goods) {
        this.goods = goods;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "timestamp" + timestamp;
    }

    public static class ReqCartDeleteInfoBean {
        // 商品规格id
        private String attrStrId;
        //    商品Id
        private String goodsId;

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

    }

}
