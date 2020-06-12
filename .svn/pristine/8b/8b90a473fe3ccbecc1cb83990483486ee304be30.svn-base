package com.t.databaselib;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * 商品信息
 */
@Entity
public class GoodsInfo {

    @Id
    private String id;

    //商品id
    @NotNull
    private String goodsId;
    //商品名
    @NotNull
    private String goodsName;
    //商品图片地址
    private String goodsUrl;

    private float goodsPrice;
    //商品数量
    @NotNull
    private int goodsNumber;

    //商品规格id
    @NotNull
    private String specId;
    //商品规格名字
    @NotNull
    private String specName;


    //添加商品到购物车的时间
    private long goodsAddTime;

    //是否选择
//    @Transient //表明这个字段不会被写入数据库，只是作为一个普通的java类字段，用来临时存储数据的，不会被持久化
    private boolean isSelect;

    //是否编辑
    @Transient
    private boolean isEdit;

    //子集集合
    @Transient
    private List<GoodsInfo> goodsInfoList;

    //子集选购商品的总价格
    @Transient
    private float shopTotalPrice;

    // 关联的店铺id
    @NotNull
    private String shopId;

    //店铺名字
    @NotNull
    private String shopName;
    //店铺地址
    @NotNull
    private String shopAddress;
    //店铺经纬度
    private double shopLa;

    private double shopLo;


    @Generated(hash = 1227172248)
    public GoodsInfo() {
    }


    @Generated(hash = 1674197521)
    public GoodsInfo(String id, @NotNull String goodsId, @NotNull String goodsName,
            String goodsUrl, float goodsPrice, int goodsNumber,
            @NotNull String specId, @NotNull String specName, long goodsAddTime,
            boolean isSelect, @NotNull String shopId, @NotNull String shopName,
            @NotNull String shopAddress, double shopLa, double shopLo) {
        this.id = id;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsUrl = goodsUrl;
        this.goodsPrice = goodsPrice;
        this.goodsNumber = goodsNumber;
        this.specId = specId;
        this.specName = specName;
        this.goodsAddTime = goodsAddTime;
        this.isSelect = isSelect;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopLa = shopLa;
        this.shopLo = shopLo;
    }


    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getGoodsId() {
        return this.goodsId;
    }
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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
    public int getGoodsNumber() {
        return this.goodsNumber;
    }
    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
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
    public float getGoodsPrice() {
        return this.goodsPrice;
    }
    public void setGoodsPrice(float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
    public long getGoodsAddTime() {
        return this.goodsAddTime;
    }
    public void setGoodsAddTime(long goodsAddTime) {
        this.goodsAddTime = goodsAddTime;
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

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public List<GoodsInfo> getGoodsInfoList() {
        return goodsInfoList;
    }

    public void setGoodsInfoList(List<GoodsInfo> goodsInfoList) {
        this.goodsInfoList = goodsInfoList;
    }

    public float getShopTotalPrice() {
        return shopTotalPrice;
    }

    public void setShopTotalPrice(float shopTotalPrice) {
        this.shopTotalPrice = shopTotalPrice;
    }


    public String getSpecId() {
        return this.specId;
    }


    public void setSpecId(String specId) {
        this.specId = specId;
    }


    public String getSpecName() {
        return this.specName;
    }


    public void setSpecName(String specName) {
        this.specName = specName;
    }


    public boolean getIsSelect() {
        return this.isSelect;
    }


    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }
}
