package com.xzte.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 店铺信息
 */
@Entity
public class ShopInfo {

    @Id
    private long id;//主健
    //店铺id
    @Unique
    private String shopId;
    //店铺名字
    @NotNull
    private String shopName;
    //店铺地址
    @NotNull
    private String shopAddress;
    //店铺经纬度
    private double la;

    private double lo;

    @Generated(hash = 1554274354)
    public ShopInfo(long id, String shopId, @NotNull String shopName,
            @NotNull String shopAddress, double la, double lo) {
        this.id = id;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.la = la;
        this.lo = lo;
    }

    @Generated(hash = 1227838148)
    public ShopInfo() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getLa() {
        return this.la;
    }

    public void setLa(double la) {
        this.la = la;
    }

    public double getLo() {
        return this.lo;
    }

    public void setLo(double lo) {
        this.lo = lo;
    }

    @Override
    public String toString() {
        return "ShopInfo{" +
                "id=" + id +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", la=" + la +
                ", lo=" + lo +
                '}';
    }
}
