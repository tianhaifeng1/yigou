package com.t.httplib.yigou.bean.req;

public class ReqTestInfo{

    private String timestamp;
    private String userId;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "timestamp" + timestamp +
                "userId" + userId ;
    }

}