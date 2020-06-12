package com.t.httplib.yigou.bean.resp;

import com.t.httplib.yigou.bean.BandcardInterface;

public class BankcardInfoBean implements BandcardInterface {

    private String dataKey;
    private String dataValue;

//    private String bankName;
//    private String bankIcon;

    private boolean isSelect;

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public boolean getIsSelect() {
        return isSelect;
    }
    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    @Override
    public String getBankcardBankname() {
        return dataValue;
    }

    @Override
    public boolean getSelect() {
        return isSelect;
    }

    @Override
    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }
}
