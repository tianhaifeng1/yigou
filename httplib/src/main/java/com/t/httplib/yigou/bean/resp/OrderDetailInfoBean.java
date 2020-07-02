package com.t.httplib.yigou.bean.resp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单详情信息
 */
public class OrderDetailInfoBean {


    /**
     * order : {"paymentTime":1559093565000,"expressType":"","couponValue":0,"receiverAddress":"西藏自治区拉萨市城关区西藏儒龙易购二楼","id":379,"receiverAddressId":82,"details":[{"sellPrice":1.8,"totalNum":1,"goodsId":1085,"systemOrderNo":"egouwx201905290923564","totalFee":1.8,"attrStrValue":"","attrStrId":"","memberId":78,"goodsDesc":"伊利优酸乳原味牛奶250ML","goodsImage":"https://rulongegou.oss-cn-beijing.aliyuncs.com/goodsImg/1557732001.png","goodsName":"伊利优酸乳原味牛奶250ML"}],"shopId":888,"expressName":"","expressNo":"","receiverName":"周浪","longitude":"91.14510035237143","memberName":"兜兜里有米","deliverTime":null,"note":"","receiverPhone":"15196870119","tradeStatus":1,"systemOrderNo":"egouwx201905290923564","orderType":0,"totalGoodsFee":1.8,"totalOrderFee":1.9,"takeoverTime":null,"distribFee":0.1,"memberAvatar":"https://wx.qlogo.cn/mmopen/vi_32/ibZbrvJM6ttDhu10TApXLG9LWQrO8NaOMI4fYXnOks23wzUJ96LajBTgibRTKwIaAVuldnkhLibF55nnPCa1BGsFA/132","shopName":"","distribTimeSort":"00:00:00 - 23:59:59","totalGoodsNum":1,"memberId":142,"payWay":2,"latitude":"29.65833125291544","addTime":1559093549000}
     * invoice : null
     */

    private OrderBean order;
    private InvoiceBean invoice;

    private int useNum;
    private int unUseNum;

    private ArrayList<CouponInfoBean> unuseList;
    private ArrayList<CouponInfoBean> useList;

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

    public int getUseNum() {
        return useNum;
    }

    public void setUseNum(int useNum) {
        this.useNum = useNum;
    }

    public int getUnUseNum() {
        return unUseNum;
    }

    public void setUnUseNum(int unUseNum) {
        this.unUseNum = unUseNum;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public InvoiceBean getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceBean invoice) {
        this.invoice = invoice;
    }

    public static class OrderBean {
        /**
         * paymentTime : 1559093565000
         * expressType :
         * couponValue : 0.0
         * receiverAddress : 西藏自治区拉萨市城关区西藏儒龙易购二楼
         * id : 379
         * receiverAddressId : 82
         * details : [{"sellPrice":1.8,"totalNum":1,"goodsId":1085,"systemOrderNo":"egouwx201905290923564","totalFee":1.8,"attrStrValue":"","attrStrId":"","memberId":78,"goodsDesc":"伊利优酸乳原味牛奶250ML","goodsImage":"https://rulongegou.oss-cn-beijing.aliyuncs.com/goodsImg/1557732001.png","goodsName":"伊利优酸乳原味牛奶250ML"}]
         * shopId : 888
         * expressName :
         * expressNo :
         * receiverName : 周浪
         * longitude : 91.14510035237143
         * memberName : 兜兜里有米
         * deliverTime : null
         * note :
         * receiverPhone : 15196870119
         * tradeStatus : 1
         * systemOrderNo : egouwx201905290923564
         * orderType : 0
         * totalGoodsFee : 1.8
         * totalOrderFee : 1.9
         * takeoverTime : null
         * distribFee : 0.1
         * memberAvatar : https://wx.qlogo.cn/mmopen/vi_32/ibZbrvJM6ttDhu10TApXLG9LWQrO8NaOMI4fYXnOks23wzUJ96LajBTgibRTKwIaAVuldnkhLibF55nnPCa1BGsFA/132
         * shopName :
         * distribTimeSort : 00:00:00 - 23:59:59
         * totalGoodsNum : 1
         * memberId : 142
         * payWay : 2
         * latitude : 29.65833125291544
         * addTime : 1559093549000
         */

        private long paymentTime;
        private String expressType;
        private BigDecimal couponValue = BigDecimal.ZERO;
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
        private BigDecimal distribFee = BigDecimal.ZERO;
        private String memberAvatar;
        private String shopName;
        private String distribTimeSort;
        private int totalGoodsNum;
        private int memberId;
        private int payWay;
        private String latitude;
        private long addTime;
        private String shopLongitude;
        private String shopLatitude;
        private String shopAddress;

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

        public String getShopPhone() {
            return shopPhone;
        }

        public void setShopPhone(String shopPhone) {
            this.shopPhone = shopPhone;
        }

        private String shopPhone;
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

        public BigDecimal getCouponValue() {
            return couponValue;
        }

        public void setCouponValue(BigDecimal couponValue) {
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
            return note == null ? "" : note;
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

        public BigDecimal getDistribFee() {
            return distribFee;
        }

        public void setDistribFee(BigDecimal distribFee) {
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

    public static class InvoiceBean{
        private String openingBank;
        private String systemOrderNo;
        private String phone;
        private String raised;
        private int state;
        private int type;
        private Object invoiceNo;
        private int id;
        private String accountNumber;
        private int category;
        private String address;
        private int shopId;
        private int memberId;
        private String identifyNo;
        private long addTime;
        private String email;

        public String getEmail() {
            return email == null || email.equals("") || email.equals("null") ? "" : email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getOpeningBank() {
            return openingBank;
        }

        public void setOpeningBank(String openingBank) {
            this.openingBank = openingBank;
        }

        public String getSystemOrderNo() {
            return systemOrderNo;
        }

        public void setSystemOrderNo(String systemOrderNo) {
            this.systemOrderNo = systemOrderNo;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRaised() {
            return raised;
        }

        public void setRaised(String raised) {
            this.raised = raised;
        }

        public String getIdentifyNo() {
            return identifyNo;
        }

        public void setIdentifyNo(String identifyNo) {
            this.identifyNo = identifyNo;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(Object invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }



        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }
    }
}
