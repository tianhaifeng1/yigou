package com.t.httplib.yigou.bean.resp;

import java.math.BigDecimal;

/**
 * 充值订单信息的Bean
 */
public class PayOrderInfoBean {

    private int id;
    //用户id
    private String memberId;
    //充值到账金额
    private BigDecimal payMoney = BigDecimal.ZERO;
    //充值支付金额
    private BigDecimal sellPrice = BigDecimal.ZERO;
    //充值单号
    private String payNo;
    // 充值状态：　０．申请；１．成功；２．失败
    private int status;
    //充值时间
    private long createTime;
    // 充值到账时间
    private long updataTime;
    //充值方式
    private int payWay;

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(long updataTime) {
        this.updataTime = updataTime;
    }
}