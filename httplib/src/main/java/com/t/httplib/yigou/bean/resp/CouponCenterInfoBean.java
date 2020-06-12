package com.t.httplib.yigou.bean.resp;

import java.math.BigDecimal;

public class CouponCenterInfoBean {
    /**
     * createTime : 1561427271000
     * isStart : 1
     * grantType : 0
     * type : 1
     * sCouponValue : null
     * endTime : 1564624053000
     * couponValue : 5.0
     * grantStatus : 0
     * startTime : 1561427250000
     * indate : 30
     * id : 6
     * createUser : null
     * name : 全品类5元优惠券
     * commonId : null
     * countValue : 0
     * inuse : 0
     * useValue : 5.0
     */

    private long createTime;
    private int isStart;
    private int grantType;
    private int type;
    private String sCouponValue;
    private long endTime;
    private BigDecimal couponValue = BigDecimal.ZERO;
    private int grantStatus;
    private long startTime;
    private int indate;
    private String id;
    private String createUser;
    private String name;
    private String commonId;
    private int countValue;//发放数量
    private int inuse;
    private BigDecimal useValue = BigDecimal.ZERO;//使用金额
    private int isOver;//是否已领完

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getIsStart() {
        return isStart;
    }

    public void setIsStart(int isStart) {
        this.isStart = isStart;
    }

    public int getGrantType() {
        return grantType;
    }

    public void setGrantType(int grantType) {
        this.grantType = grantType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getsCouponValue() {
        return sCouponValue;
    }

    public void setsCouponValue(String sCouponValue) {
        this.sCouponValue = sCouponValue;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(BigDecimal couponValue) {
        this.couponValue = couponValue;
    }

    public int getGrantStatus() {
        return grantStatus;
    }

    public void setGrantStatus(int grantStatus) {
        this.grantStatus = grantStatus;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getIndate() {
        return indate;
    }

    public void setIndate(int indate) {
        this.indate = indate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommonId() {
        return commonId;
    }

    public void setCommonId(String commonId) {
        this.commonId = commonId;
    }

    public int getCountValue() {
        return countValue;
    }

    public void setCountValue(int countValue) {
        this.countValue = countValue;
    }

    public int getInuse() {
        return inuse;
    }

    public void setInuse(int inuse) {
        this.inuse = inuse;
    }

    public BigDecimal getUseValue() {
        return useValue;
    }

    public void setUseValue(BigDecimal useValue) {
        this.useValue = useValue;
    }

    public int getIsOver() {
        return isOver;
    }

    public void setIsOver(int isOver) {
        this.isOver = isOver;
    }





}
