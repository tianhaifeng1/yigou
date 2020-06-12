package com.t.httplib.yigou.bean.req;

/**
 * 订单列表参数
 */
public class ReqOrderListInfo extends ReqPageInfo {

    //0：待支付；1：待发货；2：待收货；3：待评价；4：已完成；5：已取消；6：退款中；7：已退款；空为全部
    private String tradeStatus;

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    @Override
    public String toString() {
        return "page" + page +
                "pageSize" + pageSize +
                "timestamp" + timestamp +
                "tradeStatus" + tradeStatus
                ;
    }
}
