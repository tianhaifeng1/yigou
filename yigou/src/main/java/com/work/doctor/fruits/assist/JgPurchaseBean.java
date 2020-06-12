package com.work.doctor.fruits.assist;

import cn.jiguang.analytics.android.api.Currency;

public class JgPurchaseBean {

    private String purchaseGoodsid;
    private String purchaseGoodsName;
    private double purchasePrice;
    private boolean purchaseSuccess;
    private Currency purchaseCurrency;
    private String purchaseGoodsType;
    private int purchaseGoodsCount;

    public String getPurchaseGoodsid() {
        return purchaseGoodsid;
    }

    public void setPurchaseGoodsid(String purchaseGoodsid) {
        this.purchaseGoodsid = purchaseGoodsid;
    }

    public String getPurchaseGoodsName() {
        return purchaseGoodsName;
    }

    public void setPurchaseGoodsName(String purchaseGoodsName) {
        this.purchaseGoodsName = purchaseGoodsName;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public boolean isPurchaseSuccess() {
        return purchaseSuccess;
    }

    public void setPurchaseSuccess(boolean purchaseSuccess) {
        this.purchaseSuccess = purchaseSuccess;
    }

    public Currency getPurchaseCurrency() {
        return purchaseCurrency == null || purchaseCurrency.equals("") ? Currency.CNY : purchaseCurrency;
    }

    public void setPurchaseCurrency(Currency purchaseCurrency) {
        this.purchaseCurrency = purchaseCurrency;
    }

    public String getPurchaseGoodsType() {
        return purchaseGoodsType;
    }

    public void setPurchaseGoodsType(String purchaseGoodsType) {
        this.purchaseGoodsType = purchaseGoodsType;
    }

    public int getPurchaseGoodsCount() {
        return purchaseGoodsCount;
    }

    public void setPurchaseGoodsCount(int purchaseGoodsCount) {
        this.purchaseGoodsCount = purchaseGoodsCount;
    }
}
