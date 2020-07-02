package com.t.httplib.yigou.bean.resp;

public class LoginInfoBean extends BalanceInfoBean{

    private String token;

    private String avatarUrl;

    private String nickName;

    private String phone;

    private int approve;

    public int getApprove() {
        return approve;
    }

    public void setApprove(int approve) {
        this.approve = approve;
    }

    //会员状态：0普通会员，1会员，2普通批发商，3会员批发商
    private int status;

    private int userId;

    private String openId;

//    //是否是新用户:0登陆用户；1注册用户
//    private int register;
//
//    public int getRegister() {
//        return register;
//    }
//
//    public void setRegister(int register) {
//        this.register = register;
//    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
