package com.t.httplib.yigou.bean.req;

import java.math.BigDecimal;

/**
 * 提交订单参数
 */
public class ReqOrderSubmitInfo extends ReqGoodsPayInfo {

    private String addressId;

    //0：单个商品；1：购物车
    private int isCart;

    //    备注
    private String note;

    //配送信息
    private String distribId;

    //    我的优惠券信息id
    private String mCouponId;
    //配送天数
    private String distribDay;

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    //自提电话
    private String receiverPhone;
    //自提姓名
    private String receiverName;

    public String getDistribDay() {
        return distribDay;
    }

    public void setDistribDay(String distribDay) {
        this.distribDay = distribDay;
    }

    //    我的配送券信息id
    private String dCouponId;

    //支付金额
    private BigDecimal totalOrderFee = BigDecimal.ZERO;

    public BigDecimal getTotalOrderFee() {
        return totalOrderFee;
    }

    public void setTotalOrderFee(BigDecimal totalOrderFee) {
        this.totalOrderFee = totalOrderFee;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public int getIsCart() {
        return isCart;
    }

    public void setIsCart(int isCart) {
        this.isCart = isCart;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDistribId() {
        return distribId;
    }

    public void setDistribId(String distribId) {
        this.distribId = distribId;
    }

    public String getmCouponId() {
        return mCouponId;
    }

    public void setmCouponId(String mCouponId) {
        this.mCouponId = mCouponId;
    }

    public String getdCouponId() {
        return dCouponId;
    }

    public void setdCouponId(String dCouponId) {
        this.dCouponId = dCouponId;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("addressId" + addressId);
        if (dCouponId != null && !dCouponId.equals("")) {
            stringBuilder.append("dCouponId" + dCouponId);
        }
        stringBuilder.append("distribDay" + distribDay);
        stringBuilder.append("distribId" + distribId);
        stringBuilder.append("isCart" + isCart);
        if (mCouponId != null && !mCouponId.equals("")) {
            stringBuilder.append("mCouponId" + mCouponId);
        }
        if (note != null && !note.equals("")) {
            stringBuilder.append("note" + note);
        }
        stringBuilder.append("receiverName" + receiverName);
        stringBuilder.append("receiverPhone" + receiverPhone);

        stringBuilder.append("shopId" + shopId);
        stringBuilder.append("timestamp" + timestamp);
        stringBuilder.append("totalOrderFee" + totalOrderFee);
        return stringBuilder.toString();
    }
}
