package com.t.httplib.yigou.bean.req;

/**
 * 充值支付请求参数
 */
public class ReqPayOrderMentInfo extends ReqTimeInfo {

    private String payNo;

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    @Override
    public String toString() {
        return "payNo" + payNo +
                "timestamp" + timestamp;
    }
}
