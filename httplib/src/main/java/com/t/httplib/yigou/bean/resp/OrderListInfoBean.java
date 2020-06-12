package com.t.httplib.yigou.bean.resp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单列表
 */
public class OrderListInfoBean implements Serializable {
    /**
     * paymentTime : null
     * expressType :
     * couponValue : 0.0
     * receiverAddress : 西藏自治区拉萨市市辖区急急急
     * id : 312
     * receiverAddressId : 75
     * details : [{"sellPrice":0.01,"totalNum":1,"goodsId":22,"systemOrderNo":"egouapp201905201093405","totalFee":0.01,"attrStrValue":"250克","attrStrId":"43","memberId":71,"goodsDesc":"山竹","goodsImage":"http://newdoctor.oss-cn-beijing.aliyuncs.com/goodsImg/1552359777.png","goodsName":"山竹"}]
     * shopId : 888
     * expressName :
     * expressNo :
     * receiverName : 童
     * longitude :
     * memberName :
     * deliverTime : null
     * note :
     * receiverPhone : 15091288100
     * tradeStatus : 0
     * systemOrderNo : egouapp201905201093405
     * orderType : 0
     * totalGoodsFee : 0.01
     * totalOrderFee : 0.11
     * takeoverTime : null
     * distribFee : 0.1
     * memberAvatar :
     * shopName :
     * distribTimeSort : 00:00:00 - 23:59:59
     * totalGoodsNum : 1
     * memberId : 142
     * payWay : 0
     * latitude :
     * addTime : 1558318951000
     */

    private long paymentTime;
    private String expressType;
    private double couponValue;
    private String receiverAddress;
    private int id;
    private int receiverAddressId;
    private int shopId;
    private String expressName;
    private String expressNo;
    private String receiverName;
    private String longitude;
    private String memberName;
    private long deliverTime;
    private String note;
    private String receiverPhone;
    private int tradeStatus;
    private String systemOrderNo;
    private int orderType;
    private BigDecimal totalGoodsFee = BigDecimal.ZERO;
    private BigDecimal totalOrderFee = BigDecimal.ZERO;
    private long takeoverTime;
    private double distribFee;
    private String memberAvatar;
    private String shopName;
    private String distribTimeSort;
    private int totalGoodsNum;
    private int memberId;
    private int payWay;
    private String latitude;
    private long addTime;
    private List<OrderDetailInfoShopInfoBean> details;

    public long getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(long paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }

    public double getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(double couponValue) {
        this.couponValue = couponValue;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(int receiverAddressId) {
        this.receiverAddressId = receiverAddressId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public long getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(long deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public int getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(int tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getSystemOrderNo() {
        return systemOrderNo;
    }

    public void setSystemOrderNo(String systemOrderNo) {
        this.systemOrderNo = systemOrderNo;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getTotalGoodsFee() {
        return totalGoodsFee;
    }

    public void setTotalGoodsFee(BigDecimal totalGoodsFee) {
        this.totalGoodsFee = totalGoodsFee;
    }

    public BigDecimal getTotalOrderFee() {
        return totalOrderFee;
    }

    public void setTotalOrderFee(BigDecimal totalOrderFee) {
        this.totalOrderFee = totalOrderFee;
    }

    public long getTakeoverTime() {
        return takeoverTime;
    }

    public void setTakeoverTime(long takeoverTime) {
        this.takeoverTime = takeoverTime;
    }

    public double getDistribFee() {
        return distribFee;
    }

    public void setDistribFee(double distribFee) {
        this.distribFee = distribFee;
    }

    public String getMemberAvatar() {
        return memberAvatar;
    }

    public void setMemberAvatar(String memberAvatar) {
        this.memberAvatar = memberAvatar;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDistribTimeSort() {
        return distribTimeSort;
    }

    public void setDistribTimeSort(String distribTimeSort) {
        this.distribTimeSort = distribTimeSort;
    }

    public int getTotalGoodsNum() {
        return totalGoodsNum;
    }

    public void setTotalGoodsNum(int totalGoodsNum) {
        this.totalGoodsNum = totalGoodsNum;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public List<OrderDetailInfoShopInfoBean> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailInfoShopInfoBean> details) {
        this.details = details;
    }




}
