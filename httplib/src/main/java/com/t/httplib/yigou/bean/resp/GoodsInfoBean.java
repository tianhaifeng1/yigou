package com.t.httplib.yigou.bean.resp;

import java.util.List;

/**
 * 商品列表的bean
 */
public class GoodsInfoBean {

    /**
     * 主键id
     */
    private Integer id;
    private String goodsName;
    /**
     * 普通价格
     */
    private float sellPrice;
    /**
     * 会员价格
     */
    private float sellPriceDiscount;
    /**
     * 缩略图
     */
    private String goodsImage;

    private int itemNum;
    private int stock;

    private int shopId;
    private String shopName;

    private int sellTotalNum;

    private int specialSale;

    private int isTemai;

    //预售商品参数
//    "limitNum": 2,
//    "endTime": 1580436000000,
//    "startTime": 1578967200000,
//    "deliveryTime": 1580522400000,

    private Integer limitNum;
    private Long startTime;
    private Long endTime;
    private Long deliveryTime;

    private Long currenttime;

    private int goodsType;

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    private List<GmrRecordInfoBean> specialSaleList;

    public List<GmrRecordInfoBean> getSpecialSaleList() {
        return specialSaleList;
    }

    public void setSpecialSaleList(List<GmrRecordInfoBean> specialSaleList) {
        this.specialSaleList = specialSaleList;
    }

    public Integer getLimitNum() {
        return limitNum == null ? 0 : limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Long getStartTime() {
        return startTime==null?0:startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime == null ? 0 : endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getDeliveryTime() {
        return deliveryTime == null ? 0 : deliveryTime;
    }

    public void setDeliveryTime(Long deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Long getCurrenttime() {
        return currenttime == null ? 0 : currenttime;
    }

    public void setCurrenttime(Long currenttime) {
        this.currenttime = currenttime;
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

    public int getSellTotalNum() {
        return sellTotalNum;
    }

    public void setSellTotalNum(int sellTotalNum) {
        this.sellTotalNum = sellTotalNum;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public float getSellPriceDiscount() {
        return sellPriceDiscount;
    }

    public void setSellPriceDiscount(float sellPriceDiscount) {
        this.sellPriceDiscount = sellPriceDiscount;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

}
