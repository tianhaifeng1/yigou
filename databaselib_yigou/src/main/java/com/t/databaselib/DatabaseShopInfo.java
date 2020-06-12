package com.t.databaselib;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class DatabaseShopInfo implements Serializable {

    @Transient
    private static final long serialVersionUID = -1207654291537900405L;
    @Id
    private String id;

    //是否全选择
    private boolean isAllSelect;

    //是否编辑
    @Transient
    private boolean isEdit;

    //子集集合
    @Transient
    private ArrayList<DatabaseGoodsInfo> goodsInfoList;

    //总价格
    private float shopTotalPrice;

    // 关联的店铺id
    @Unique
    private String shopId;

    //店铺名字
    @NotNull
    private String shopName;

    //店铺描述getShopIntro
    private String shopIntro;

    //店铺地址
    @NotNull
    private String shopAddress;
    //店铺经纬度
    private double shopLa;

    private double shopLo;

    //店铺的配送区域
    private int shopArea;


    @Generated(hash = 1603986219)
    public DatabaseShopInfo(String id, boolean isAllSelect, float shopTotalPrice,
            String shopId, @NotNull String shopName, String shopIntro,
            @NotNull String shopAddress, double shopLa, double shopLo,
            int shopArea) {
        this.id = id;
        this.isAllSelect = isAllSelect;
        this.shopTotalPrice = shopTotalPrice;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopIntro = shopIntro;
        this.shopAddress = shopAddress;
        this.shopLa = shopLa;
        this.shopLo = shopLo;
        this.shopArea = shopArea;
    }

    @Generated(hash = 1478436431)
    public DatabaseShopInfo() {
    }


    public boolean getIsAllSelect() {
        return this.isAllSelect;
    }

    public void setIsAllSelect(boolean isAllSelect) {
        this.isAllSelect = isAllSelect;
    }

    public float getShopTotalPrice() {
        return this.shopTotalPrice;
    }

    public void setShopTotalPrice(float shopTotalPrice) {
        this.shopTotalPrice = shopTotalPrice;
    }

    public String getShopId() {
        return this.shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return this.shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return this.shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public double getShopLa() {
        return this.shopLa;
    }

    public void setShopLa(double shopLa) {
        this.shopLa = shopLa;
    }

    public double getShopLo() {
        return this.shopLo;
    }

    public void setShopLo(double shopLo) {
        this.shopLo = shopLo;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public ArrayList<DatabaseGoodsInfo> getGoodsInfoList() {
        return goodsInfoList;
    }

    public void setGoodsInfoList(ArrayList<DatabaseGoodsInfo> goodsInfoList) {
        this.goodsInfoList = goodsInfoList;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getShopArea() {
        return this.shopArea;
    }

    public void setShopArea(int shopArea) {
        this.shopArea = shopArea;
    }

    public String getShopIntro() {
        return this.shopIntro;
    }

    public void setShopIntro(String shopIntro) {
        this.shopIntro = shopIntro;
    }
}