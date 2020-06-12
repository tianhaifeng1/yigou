package com.t.httplib.yigou.bean.resp;

/**
 * 店铺信息
 */
public class ShopInfoBean {

    private int shopId;
    private String shopName;
    //等级
    private int shopLevel;
    // 简介
    private String shopIntro;

    private String shopLogo;
    private int shopCity;
    private String shopCityName;
    private String shopAddress;
    private String shopLongitude;
    private String shopLatitude;
    private int deliveryScope;//配送范围

    private int status;
    private long regTime;
    private String shopPhone;

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

    public int getShopLevel() {
        return shopLevel;
    }

    public void setShopLevel(int shopLevel) {
        this.shopLevel = shopLevel;
    }

    public String getShopIntro() {
        return shopIntro;
    }

    public void setShopIntro(String shopIntro) {
        this.shopIntro = shopIntro;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public int getShopCity() {
        return shopCity;
    }

    public void setShopCity(int shopCity) {
        this.shopCity = shopCity;
    }

    public String getShopCityName() {
        return shopCityName;
    }

    public void setShopCityName(String shopCityName) {
        this.shopCityName = shopCityName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopLongitude() {
        return shopLongitude;
    }

    public void setShopLongitude(String shopLongitude) {
        this.shopLongitude = shopLongitude;

    }

    public String getShopLatitude() {
        return shopLatitude;
//        return shopLatitude==null||shopLatitude.equals("")||shopLatitude.equals("null")?"0":shopLatitude;
    }

    public void setShopLatitude(String shopLatitude) {
        this.shopLatitude = shopLatitude;
    }

    public int getDeliveryScope() {
        return deliveryScope;
    }

    public void setDeliveryScope(int deliveryScope) {
        this.deliveryScope = deliveryScope;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getRegTime() {
        return regTime;
    }

    public void setRegTime(long regTime) {
        this.regTime = regTime;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }
}
