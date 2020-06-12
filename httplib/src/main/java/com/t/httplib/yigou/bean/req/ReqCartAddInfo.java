package com.t.httplib.yigou.bean.req;

import java.util.List;

public class ReqCartAddInfo extends ReqTimeInfo {

    protected List<ReqCartAddInfoBean> goods;

    public List<ReqCartAddInfoBean> getGoods() {
        return goods;
    }

    public void setGoods(List<ReqCartAddInfoBean> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "timestamp" + timestamp;
    }

    public static class ReqCartAddInfoBean {
        // 商品规格id
        private String attrStrId;
        //    商品Id
        private String goodsId;
        //    添加商品的数量
        private int totalNum;

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

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

    }

}
