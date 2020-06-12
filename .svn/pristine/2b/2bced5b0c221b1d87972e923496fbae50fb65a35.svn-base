package com.xzte.maplib.city;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.trjx.tlibs.bean.PinyinBean;

import java.util.List;

public class TCity extends PinyinBean implements Comparable<TCity>, MultiItemEntity {

    private int areaId;
    private String areaName;

    private int itemType;
    //热门城市
    private List<String> hotCityList;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<String> getHotCityList() {
        return hotCityList;
    }

    public void setHotCityList(List<String> hotCityList) {
        this.hotCityList = hotCityList;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int compareTo(TCity o) {
        return getPinyinStr().compareTo(o.getPinyinStr());
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
