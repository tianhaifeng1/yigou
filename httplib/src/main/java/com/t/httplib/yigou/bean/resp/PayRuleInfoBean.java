package com.t.httplib.yigou.bean.resp;

import java.math.BigDecimal;

/**
 * 充值规则信息的Bean
 */
public class PayRuleInfoBean {

    private int id;
    //充值到账金额
    private BigDecimal payMoney;
    //充值支付金额
    private BigDecimal sellMoney;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public BigDecimal getSellMoney() {
        return sellMoney;
    }

    public void setSellMoney(BigDecimal sellMoney) {
        this.sellMoney = sellMoney;
    }
}
