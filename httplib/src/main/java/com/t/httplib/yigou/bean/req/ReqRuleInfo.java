package com.t.httplib.yigou.bean.req;

/**
 * 系统规则请求参数的Bean
 */
public class ReqRuleInfo extends ReqTimeInfo {

    // JFGZ:积分规则
    // TXGZ:提现协议
    // YHXY:用户协议
    // GYWM:关于我们
    // CZGZ:会员充值规则
    // YQGZ:邀请活动规则
    // YSZC:隐私政策
    private String catype;

    public String getCatype() {
        return catype;
    }

    public void setCatype(String catype) {
        this.catype = catype;
    }

    @Override
    public String toString() {
        return "catype" + catype +
                "timestamp" + timestamp ;
    }
}
