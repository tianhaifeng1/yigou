package com.t.httplib.yigou.bean.resp;

import java.util.ArrayList;

public class GoodsAffirmBean {

    private int useNum;
    private Order order;
    private int unuseNum;
    private ArrayList<CouponInfoBean> unuseList;
    private ArrayList<CouponInfoBean> useList;

    public int getUseNum() {
        return useNum;
    }

    public void setUseNum(int useNum) {
        this.useNum = useNum;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getUnuseNum() {
        return unuseNum;
    }

    public void setUnuseNum(int unuseNum) {
        this.unuseNum = unuseNum;
    }

    public ArrayList<CouponInfoBean> getUnuseList() {
        return unuseList;
    }

    public void setUnuseList(ArrayList<CouponInfoBean> unuseList) {
        this.unuseList = unuseList;
    }

    public ArrayList<CouponInfoBean> getUseList() {
        return useList;
    }

    public void setUseList(ArrayList<CouponInfoBean> useList) {
        this.useList = useList;
    }

    public static class Order{
        private String paymentTime;
        private String expressType;
        private String receiverAddress;
        private int couponValue;
        private String id;
        private ArrayList<DetailsBean> details;
        private int shopId;
        private String expressNo;
        private String expressName;
        private String receiverName;
        private String longitude;
        private String memberName;
        private String deliverTime;
        private String note;
        private String receiverPhone;
        private int tradeStatus;
        private String systemOrderNo;
        private int orderType;
        private double totalGoodsFee;
        private double totalOrderFee;
        private String takeoverTime;
        private int distribFee;
        private String shopLongitude;
        private String shopLatitude;
        private String shopAddress;
        private String memberAvatar;
        private String shopName;
        private String distribTimeSort;
        private int totalGoodsNum;
        private int payWay;
        private int memberId;
        private String shopPhone;
        private String latitude;
        private String addTime;

        public String getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(String paymentTime) {
            this.paymentTime = paymentTime;
        }

        public String getExpressType() {
            return expressType;
        }

        public void setExpressType(String expressType) {
            this.expressType = expressType;
        }

        public String getReceiverAddress() {
            return receiverAddress;
        }

        public void setReceiverAddress(String receiverAddress) {
            this.receiverAddress = receiverAddress;
        }

        public int getCouponValue() {
            return couponValue;
        }

        public void setCouponValue(int couponValue) {
            this.couponValue = couponValue;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ArrayList<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(ArrayList<DetailsBean> details) {
            this.details = details;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getExpressNo() {
            return expressNo;
        }

        public void setExpressNo(String expressNo) {
            this.expressNo = expressNo;
        }

        public String getExpressName() {
            return expressName;
        }

        public void setExpressName(String expressName) {
            this.expressName = expressName;
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

        public String getDeliverTime() {
            return deliverTime;
        }

        public void setDeliverTime(String deliverTime) {
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

        public double getTotalGoodsFee() {
            return totalGoodsFee;
        }

        public void setTotalGoodsFee(double totalGoodsFee) {
            this.totalGoodsFee = totalGoodsFee;
        }

        public double getTotalOrderFee() {
            return totalOrderFee;
        }

        public void setTotalOrderFee(double totalOrderFee) {
            this.totalOrderFee = totalOrderFee;
        }

        public String getTakeoverTime() {
            return takeoverTime;
        }

        public void setTakeoverTime(String takeoverTime) {
            this.takeoverTime = takeoverTime;
        }

        public int getDistribFee() {
            return distribFee;
        }

        public void setDistribFee(int distribFee) {
            this.distribFee = distribFee;
        }

        public String getShopLongitude() {
            return shopLongitude;
        }

        public void setShopLongitude(String shopLongitude) {
            this.shopLongitude = shopLongitude;
        }

        public String getShopLatitude() {
            return shopLatitude;
        }

        public void setShopLatitude(String shopLatitude) {
            this.shopLatitude = shopLatitude;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
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

        public int getPayWay() {
            return payWay;
        }

        public void setPayWay(int payWay) {
            this.payWay = payWay;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getShopPhone() {
            return shopPhone;
        }

        public void setShopPhone(String shopPhone) {
            this.shopPhone = shopPhone;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }
    }
}
