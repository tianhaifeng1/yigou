package com.t.httplib.yigou.bean.resp;

import java.util.List;

public class GoodsListInfoBean {
    private int id;
    private List<GoodsInfoBean> goods;
    private String activityIcon;
    private String activityName;
    private int activityCategoryNum;
    private int shopId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<GoodsInfoBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsInfoBean> goods) {
        this.goods = goods;
    }

    public String getActivityIcon() {
        return activityIcon;
    }

    public void setActivityIcon(String activityIcon) {
        this.activityIcon = activityIcon;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getActivityCategoryNum() {
        return activityCategoryNum;
    }

    public void setActivityCategoryNum(int activityCategoryNum) {
        this.activityCategoryNum = activityCategoryNum;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }
}
