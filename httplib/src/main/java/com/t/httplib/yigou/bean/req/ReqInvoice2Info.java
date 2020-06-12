package com.t.httplib.yigou.bean.req;

public class ReqInvoice2Info extends ReqTimeInfo {

    private String systemOrderNo;
    private String raised;
    private String phone;
    private String email;
    private String types;

//    category = 0
    private int category;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getSystemOrderNo() {
        return systemOrderNo;
    }

    public void setSystemOrderNo(String systemOrderNo) {
        this.systemOrderNo = systemOrderNo;
    }

    public String getRaised() {
        return raised;
    }

    public void setRaised(String raised) {
        this.raised = raised;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "category" + category +
                "email" + email +
                "phone" + phone +
                "raised" + raised +
                "systemOrderNo" + systemOrderNo +
                "timestamp" + timestamp +
                "types" + types ;
    }
}
