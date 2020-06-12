package com.t.databaselib.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车商品列表的Bean
 */
public class CartShopInfoBean {

    /**
     * shopLatitude : 34.270836
     * deliveryScope : 5000
     * shopIntro : 儒龙易购一分店
     * shopName : 儒龙易购
     * shopId : 999
     * list : [{"sellPrice":8,"id":822,"totalNum":1,"goodsId":12,"attrStrValue":"","attrStrId":"","memberId":148,"goodsImage":"https://newdoctor.oss-cn-beijing.aliyuncs.com/goodsImg/1552354716.png?x-oss-process=style/zip","goodsName":"哈密瓜"}]
     * shopLongitude : 108.941803
     * shopLogo :
     */

    private double shopLatitude;
    private int deliveryScope;
    private String shopIntro;
    private String shopName;
    private int shopId;
    private double shopLongitude;
    private String shopAddress;
    private String shopLogo;
    private List<ListBean> list;

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public double getShopLatitude() {
        return shopLatitude;
    }

    public void setShopLatitude(double shopLatitude) {
        this.shopLatitude = shopLatitude;
    }

    public int getDeliveryScope() {
        return deliveryScope;
    }

    public void setDeliveryScope(int deliveryScope) {
        this.deliveryScope = deliveryScope;
    }

    public String getShopIntro() {
        return shopIntro;
    }

    public void setShopIntro(String shopIntro) {
        this.shopIntro = shopIntro;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public double getShopLongitude() {
        return shopLongitude;
    }

    public void setShopLongitude(double shopLongitude) {
        this.shopLongitude = shopLongitude;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * sellPrice : 8.0
         * id : 822
         * totalNum : 1
         * goodsId : 12
         * attrStrValue :
         * attrStrId :
         * memberId : 148
         * goodsImage : https://newdoctor.oss-cn-beijing.aliyuncs.com/goodsImg/1552354716.png?x-oss-process=style/zip
         * goodsName : 哈密瓜
         * addTime :
         */

        private BigDecimal sellPrice = BigDecimal.ZERO;
        private BigDecimal sellPriceDiscount = BigDecimal.ZERO;
        private int id;
        private int totalNum;
        private int goodsId;
        private String attrStrValue;
        private String attrStrId;
        private int memberId;
        private String goodsImage;
        private String goodsName;
        private long addTime;
        private int stock;

        private int shopId;

        private int specialSale;

        private int isTemai;

        //分类id
        private String catid;
        //分类名称
        private String cnname;

        //商品分类：0普通商品，3预售商品
        private int goodsType;

        private PresellInfoBean presell;

        public PresellInfoBean getPresell() {
            return presell;
        }

        public void setPresell(PresellInfoBean presell) {
            this.presell = presell;
        }

        public int getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(int goodsType) {
            this.goodsType = goodsType;
        }

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getCnname() {
            return cnname;
        }

        public void setCnname(String cnname) {
            this.cnname = cnname;
        }

        public int getSpecialSale() {
            return specialSale;
        }

        public void setSpecialSale(int specialSale) {
            this.specialSale = specialSale;
        }

        public int getIsTemai() {
            return isTemai;
        }

        public void setIsTemai(int isTemai) {
            this.isTemai = isTemai;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public BigDecimal getSellPrice() {
            return sellPrice;
        }

        public void setSellPrice(BigDecimal sellPrice) {
            this.sellPrice = sellPrice;
        }

        public BigDecimal getSellPriceDiscount() {
            return sellPriceDiscount;
        }

        public void setSellPriceDiscount(BigDecimal sellPriceDiscount) {
            this.sellPriceDiscount = sellPriceDiscount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getAttrStrValue() {
            return attrStrValue == null ? "" : attrStrValue;
        }

        public void setAttrStrValue(String attrStrValue) {
            this.attrStrValue = attrStrValue;
        }

        public String getAttrStrId() {
            return attrStrId == null ? "" : attrStrId;
        }

        public void setAttrStrId(String attrStrId) {
            this.attrStrId = attrStrId;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getGoodsImage() {
            return goodsImage;
        }

        public void setGoodsImage(String goodsImage) {
            this.goodsImage = goodsImage;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }
    }
}