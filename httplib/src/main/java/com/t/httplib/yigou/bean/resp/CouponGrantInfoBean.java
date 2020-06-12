package com.t.httplib.yigou.bean.resp;

import java.math.BigDecimal;

/**
 * 优惠卷领取的信息
 */
public class CouponGrantInfoBean {


    /**
     * isStart : 1
     * isOver : 0
     * couponValue : 20.25
     * endTime : 1569289200000
     * type : 2
     * grantStatus : 0
     * id : 14
     * indate : 60
     * startTime : 1563932399000
     * commonId : 712
     * name : 娃哈哈商品专用满60减20元
     * countValue : 0
     * useValue : 60.0
     */

    private int isStart;
    private int isOver;
    private BigDecimal couponValue = BigDecimal.ZERO;
    private long endTime;
    private int type;
    private int grantStatus;
    private int id;
    private int indate;
    private long startTime;
    private int commonId;
    private String name;
    private int countValue;
    private BigDecimal useValue = BigDecimal.ZERO;

    public int getIsStart() {
        return isStart;
    }

    public void setIsStart(int isStart) {
        this.isStart = isStart;
    }

    public int getIsOver() {
        return isOver;
    }

    public void setIsOver(int isOver) {
        this.isOver = isOver;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGrantStatus() {
        return grantStatus;
    }

    public void setGrantStatus(int grantStatus) {
        this.grantStatus = grantStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndate() {
        return indate;
    }

    public void setIndate(int indate) {
        this.indate = indate;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getCommonId() {
        return commonId;
    }

    public void setCommonId(int commonId) {
        this.commonId = commonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountValue() {
        return countValue;
    }

    public void setCountValue(int countValue) {
        this.countValue = countValue;
    }

    public BigDecimal getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(BigDecimal couponValue) {
        this.couponValue = couponValue;
    }

    public BigDecimal getUseValue() {
        return useValue;
    }

    public void setUseValue(BigDecimal useValue) {
        this.useValue = useValue;
    }
}
