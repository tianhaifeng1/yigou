package com.t.httplib.yigou.bean.resp;

import java.math.BigDecimal;

public class MoneyRecordListInfoBean {

    private String title;
    private long enterTime;
    private BigDecimal moneyFee = BigDecimal.ZERO;

    private String operate;

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(long enterTime) {
        this.enterTime = enterTime;
    }

    public BigDecimal getMoneyFee() {
        return moneyFee;
    }

    public void setMoneyFee(BigDecimal moneyFee) {
        this.moneyFee = moneyFee;
    }
}
