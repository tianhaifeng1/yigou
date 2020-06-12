package com.t.httplib.yigou.bean.resp;

import java.util.List;

public class CategoryGoodsBean {

    private List<GoodsInfoBean> goods;
    private String cnname;
    private int activityId;
    private String categoryIcon;
    private int categoryId;

    public List<GoodsInfoBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsInfoBean> goods) {
        this.goods = goods;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
