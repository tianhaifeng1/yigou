package com.t.httplib.yigou.bean.resp;

/**
 * 个人信息
 */
public class UserInfoBean extends BalanceInfoBean {

    /**
     * inviterTime : null
     * balance : 0.0
     * phone : 13112345678
     * status : 1
     * nickName : RL_b85mo
     * email :
     * name :
     * avatarUrl : https://rulongegou.oss-cn-beijing.aliyuncs.com/member/defaultavataturl.png
     * parentid : 0
     * addTime : 1564560027000
     */

    private long inviterTime;
    private String phone;
    private int status;
    private String nickName;
    private String email;
    private String name;
    private String avatarUrl;
    private int parentid;
    private long addTime;

    public long getInviterTime() {
        return inviterTime;
    }

    public void setInviterTime(long inviterTime) {
        this.inviterTime = inviterTime;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }
}