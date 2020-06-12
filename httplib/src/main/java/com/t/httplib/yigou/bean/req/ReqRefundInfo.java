package com.t.httplib.yigou.bean.req;

public class ReqRefundInfo extends ReqTimeInfo {

    private String systemOrderNo;
    private String account;
    private String note;

    public String getSystemOrderNo() {
        return systemOrderNo;
    }

    public void setSystemOrderNo(String systemOrderNo) {
        this.systemOrderNo = systemOrderNo;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "account" + account +
                "note" + note +
                "systemOrderNo" + systemOrderNo +
                "timestamp" + timestamp;
    }
}
