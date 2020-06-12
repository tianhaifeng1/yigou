package com.t.httplib.yigou.bean.resp;

/**
 * 购买人信息
 */
public class GmrRecordInfoBean {

    /**
     * id : 31
     * goodsId : 13
     * nickName :
     * avatarUrl :
     * paymentTime : 1578983131000
     * sellNum : 2
     * memberId : 141
     */

    private int id;
    private int goodsId;
    private String nickName;
    private String avatarUrl;
    private Long paymentTime;
    private int sellNum;
    private int memberId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Long getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Long paymentTime) {
        this.paymentTime = paymentTime;
    }

    public int getSellNum() {
        return sellNum;
    }

    public void setSellNum(int sellNum) {
        this.sellNum = sellNum;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

}
