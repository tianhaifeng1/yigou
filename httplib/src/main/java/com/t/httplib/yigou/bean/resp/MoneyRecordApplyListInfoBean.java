package com.t.httplib.yigou.bean.resp;

import java.io.Serializable;
import java.math.BigDecimal;

public class MoneyRecordApplyListInfoBean implements Serializable {

    /**
     * phone : 14111111111
     * status : 0
     * drawName : 中国银行
     * applyNo : 201911061715223
     * applyTime : 1573033150000
     * drawCode : 1026
     * drawNo : 123456789123456789
     * applyWay : 6
     * id : 7
     * title : 邀请收益提现
     * enterTime : null
     * memberId : 141
     * realName : 测试
     * moneyFee : 100.0
     */

    private String phone;
    private int status;
    private String drawName;
    private String applyNo;
    private Long applyTime;
    private String drawCode;
    private String drawNo;
    private int applyWay;
    private int id;
    private String title;
    private Long enterTime;
    private int memberId;
    private String realName;
    private BigDecimal moneyFee = BigDecimal.ZERO;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDrawName() {
        return drawName;
    }

    public void setDrawName(String drawName) {
        this.drawName = drawName;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public Long getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Long applyTime) {
        this.applyTime = applyTime;
    }

    public String getDrawCode() {
        return drawCode;
    }

    public void setDrawCode(String drawCode) {
        this.drawCode = drawCode;
    }

    public String getDrawNo() {
        return drawNo;
    }

    public void setDrawNo(String drawNo) {
        this.drawNo = drawNo;
    }

    public int getApplyWay() {
        return applyWay;
    }

    public void setApplyWay(int applyWay) {
        this.applyWay = applyWay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Long enterTime) {
        this.enterTime = enterTime;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public BigDecimal getMoneyFee() {
        return moneyFee;
    }

    public void setMoneyFee(BigDecimal moneyFee) {
        this.moneyFee = moneyFee;
    }
}
