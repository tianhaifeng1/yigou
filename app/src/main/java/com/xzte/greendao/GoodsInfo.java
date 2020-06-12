package com.xzte.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 商品信息
 */
@Entity
public class GoodsInfo {

    @Id
    private long id;

    //商品id
    @Unique
    private String goodsId;
    //商品名
    @NotNull
    private String goodsName;
    //商品图片地址
    private String goodsUrl;
    //商品数量
    @NotNull
    private int goodsNumber;
    //是否选择商品
    @Transient //表明这个字段不会被写入数据库，只是作为一个普通的java类字段，用来临时存储数据的，不会被持久化
    private int isSelect;
    // 关联的店铺id
    @NotNull
    private String shopId;
    @Generated(hash = 896556006)
    public GoodsInfo(long id, String goodsId, @NotNull String goodsName,
            String goodsUrl, int goodsNumber, @NotNull String shopId) {
        this.id = id;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsUrl = goodsUrl;
        this.goodsNumber = goodsNumber;
        this.shopId = shopId;
    }
    @Generated(hash = 1227172248)
    public GoodsInfo() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
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



}
