package com.t.httplib.yigou.bean.resp;

import java.util.List;

/**
 * 搜索商品的bean
 */
public class SearchGoodsInfoBean {

    private String title;

    private int code;

    private List<String> stringList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }
}
