package com.t.databaselib;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

@Entity
public class DatabaseGoodsInfo implements Serializable {

    @Transient
    private static final long serialVersionUID = 5872708102673933075L;
    @Id
    private String id;

    //商品id
    @NotNull
    private String goodsId;
    // 关联的店铺id
    @NotNull
    private String shopId;
    //商品规格id
    @NotNull
    private String specId;


    //商品名
    @NotNull
    private String goodsName;
    //商品图片地址
    private String goodsUrl;

    private float goodsPrice;

    private float goodsPriceVip;

    //商品数量
    @NotNull
    private int goodsNumber;

    //商品规格名字
    @NotNull
    private String specName;

    //商品总数：库存
    private int goodsTotal;

    //添加商品到购物车的时间
    private long goodsAddTime;

    //是否选择
    private boolean isSelect;

    //是否编辑
    @Transient
    private boolean isEdit;
    @Transient
    private int specialSale;

    private int isTemai;

    //分类id
    private String catid;
    //分类名称
    private String cnname;

    //商品类型
    @NotNull
    private int goodsType;
    //预售商品：预售开始时间
    private long startTime;
    //预售商品：预售结束时间
    private long endTime;


    @Generated(hash = 1165637342)
    public DatabaseGoodsInfo(String id, @NotNull String goodsId,
            @NotNull String shopId, @NotNull String specId,
            @NotNull String goodsName, String goodsUrl, float goodsPrice,
            float goodsPriceVip, int goodsNumber, @NotNull String specName,
            int goodsTotal, long goodsAddTime, boolean isSelect, int isTemai,
            String catid, String cnname, int goodsType, long startTime,
            long endTime) {
        this.id = id;
        this.goodsId = goodsId;
        this.shopId = shopId;
        this.specId = specId;
        this.goodsName = goodsName;
        this.goodsUrl = goodsUrl;
        this.goodsPrice = goodsPrice;
        this.goodsPriceVip = goodsPriceVip;
        this.goodsNumber = goodsNumber;
        this.specName = specName;
        this.goodsTotal = goodsTotal;
        this.goodsAddTime = goodsAddTime;
        this.isSelect = isSelect;
        this.isTemai = isTemai;
        this.catid = catid;
        this.cnname = cnname;
        this.goodsType = goodsType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Generated(hash = 1072149196)
    public DatabaseGoodsInfo() {
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

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getShopId() {
        return this.shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getSpecId() {
        return this.specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsUrl() {
        return this.goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public float getGoodsPrice() {
        return this.goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsNumber() {
        return this.goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getSpecName() {
        return this.specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public long getGoodsAddTime() {
        return this.goodsAddTime;
    }

    public void setGoodsAddTime(long goodsAddTime) {
        this.goodsAddTime = goodsAddTime;
    }

    public boolean getIsSelect() {
        return this.isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGoodsTotal() {
        return this.goodsTotal;
    }

    public void setGoodsTotal(int goodsTotal) {
        this.goodsTotal = goodsTotal;
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

    public int getGoodsType() {
        return this.goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public float getGoodsPriceVip() {
        return this.goodsPriceVip;
    }

    public void setGoodsPriceVip(float goodsPriceVip) {
        this.goodsPriceVip = goodsPriceVip;
    }
}