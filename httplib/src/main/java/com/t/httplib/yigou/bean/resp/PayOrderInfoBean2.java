package com.t.httplib.yigou.bean.resp;

import java.math.BigDecimal;

/**
 * 查询余额使用记录信息的Bean
 */
public class PayOrderInfoBean2 {
    /**
     * systemOrderNo : egouapp201907021649524
     */
    private String id;
    //用户id
    private String memberId;
    //金额
    private BigDecimal usePrice = BigDecimal.ZERO;
    //单号
    private String systemOrderNo;
    //充值时间
    private long createTime;
    // 充值到账时间
    private long updataTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getUsePrice() {
        return usePrice;
    }

    public void setUsePrice(BigDecimal usePrice) {
        this.usePrice = usePrice;
    }

    public String getSystemOrderNo() {
        return systemOrderNo;
    }

    public void setSystemOrderNo(String systemOrderNo) {
        this.systemOrderNo = systemOrderNo;
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
