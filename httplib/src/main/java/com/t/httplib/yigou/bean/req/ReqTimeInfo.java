package com.t.httplib.yigou.bean.req;

/**
 * 参数的基类：请求参数的类 需要继承此类
 */
public class ReqTimeInfo {

    protected String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "timestamp" + timestamp;
    }
}
