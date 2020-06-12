package com.t.httplib.yigou.bean.resp;

import java.util.List;

/**
 * 商品规格数据对象
 */
public class GoodsSpecInfoBean {

    private List<ItemSkuBean> itemSku;
    private List<GoodsItemListBean> goodsItemList;

    public List<ItemSkuBean> getItemSku() {
        return itemSku;
    }

    public void setItemSku(List<ItemSkuBean> itemSku) {
        this.itemSku = itemSku;
    }

    public List<GoodsItemListBean> getGoodsItemList() {
        return goodsItemList;
    }

    public void setGoodsItemList(List<GoodsItemListBean> goodsItemList) {
        this.goodsItemList = goodsItemList;
    }

    public static class ItemSkuBean {
        /**
         "attrStrId":"802,803",
         "attrStrValue":"黑色,S (腕围：16~19cm)",
         "goodsId":537,
         "price":580,
         "priceDiscount":580,
         "id":2453,
         "stock":99,
         "sellNum":0
         */
        private String attrStrId;
        private String attrStrValue;
        private int goodsId;
        private float price;
        private float priceDiscount;
        private int id;
        private int stock;
        private int sellNum;

        public String getAttrStrId() {
            return attrStrId;
        }

        public void setAttrStrId(String attrStrId) {
            this.attrStrId = attrStrId;
        }

        public String getAttrStrValue() {
            return attrStrValue;
        }

        public void setAttrStrValue(String attrStrValue) {
            this.attrStrValue = attrStrValue;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public float getPriceDiscount() {
            return priceDiscount;
        }

        public void setPriceDiscount(float priceDiscount) {
            this.priceDiscount = priceDiscount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getSellNum() {
            return sellNum;
        }

        public void setSellNum(int sellNum) {
            this.sellNum = sellNum;
        }
    }
    public static class GoodsItemListBean {
        /**
         "specList":Array[4],
         "itemName":"规格",
         "id":446,
         "goodsId":537
         */
        private List<SpecListBean> specList;
        private String itemName;
        private int id;
        private int goodsId;

        //选择的id
        private int selectId;
//        选择的位置
        private int selectIndex;



        public int getSelectIndex() {
            return selectIndex;
        }

        public void setSelectIndex(int selectIndex) {
            this.selectIndex = selectIndex;
        }

        public List<SpecListBean> getSpecList() {
            return specList;
        }

        public void setSpecList(List<SpecListBean> specList) {
            this.specList = specList;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getSelectId() {
            return selectId;
        }

        public void setSelectId(int selectId) {
            this.selectId = selectId;
        }

        public static class SpecListBean{
            /**
             "attrKeyId":446,
             "attrValue":"S (腕围：16~19cm)",
             "id":803,
             "goodsId":537
             */
            private int attrKeyId;
            private String attrValue;
            private int id;
            private int goodsId;

            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public int getAttrKeyId() {
                return attrKeyId;
            }

            public void setAttrKeyId(int attrKeyId) {
                this.attrKeyId = attrKeyId;
            }

            public String getAttrValue() {
                return attrValue;
            }

            public void setAttrValue(String attrValue) {
                this.attrValue = attrValue;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }
        }
    }


}
