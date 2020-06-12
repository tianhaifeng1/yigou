package com.t.httplib.yigou.bean.resp;

import java.io.Serializable;
import java.util.List;

/**
 * 商品分类信息
 */
public class GoodsTypeInfoBean implements Serializable {

    private int id;
    // 分类明
    private String cnname;
    //图片
    private String image;
    // 分类编码
    private int relatedCatids;
    // 子类集合
    private List<GoodsTypeInfoBean> childList;

    private int parentId;

    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<GoodsTypeInfoBean> getChildList() {
        return childList;
    }

    public void setChildList(List<GoodsTypeInfoBean> childList) {
        this.childList = childList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public String getImage() {
        return image != null && !image.equals("") && image.startsWith("https://") ? image.replace("https://", "http://") : image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRelatedCatids() {
        return relatedCatids;
    }

    public void setRelatedCatids(int relatedCatids) {
        this.relatedCatids = relatedCatids;
    }
}
