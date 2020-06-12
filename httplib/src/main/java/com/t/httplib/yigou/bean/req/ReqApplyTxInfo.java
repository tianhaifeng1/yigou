package com.t.httplib.yigou.bean.req;

public class ReqApplyTxInfo extends ReqTimeInfo {

    private String moneyFee;
    private String drawNo;
    private String drawName;
    private String realName;
    private String phone;
    private String drawCode;

    public String getMoneyFee() {
        return moneyFee;
    }

    public void setMoneyFee(String moneyFee) {
        this.moneyFee = moneyFee;
    }

    public String getDrawNo() {
        return drawNo;
    }

    public void setDrawNo(String drawNo) {
        this.drawNo = drawNo;
    }

    public String getDrawName() {
        return drawName;
    }

    public void setDrawName(String drawName) {
        this.drawName = drawName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDrawCode() {
        return drawCode;
    }

    public void setDrawCode(String drawCode) {
        this.drawCode = drawCode;
    }

    @Override
    public String toString() {
        return "drawCode" + drawCode +
                "drawName" + drawName +
                "drawNo" + drawNo +
                "moneyFee" + moneyFee +
                "phone" + phone +
                "realName" + realName +
                "timestamp" + timestamp;
    }
}
