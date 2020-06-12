package com.t.httplib.yigou.bean.resp;

import java.math.BigDecimal;

public class BalanceInfoBean {

    protected BigDecimal balance = BigDecimal.ZERO;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
