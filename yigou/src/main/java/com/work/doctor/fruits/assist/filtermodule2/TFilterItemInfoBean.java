package com.work.doctor.fruits.assist.filtermodule2;

/**
 * 作者：小童
 * 创建时间：2019/8/22 18:16
 *
 * 列表项的实现类
 *
 */
public class TFilterItemInfoBean implements TFilterItemInfo {

    //条目名
    private String filterItemName;
//条目选择项参数
    private boolean filterItemSelect;

    public void setFilterItemName(String filterItemName) {
        this.filterItemName = filterItemName;
    }

    public boolean isFilterItemSelect() {
        return filterItemSelect;
    }

    public void setFilterItemSelect(boolean filterItemSelect) {
        this.filterItemSelect = filterItemSelect;
    }

    @Override
    public String getFilterItemName() {
        return filterItemName;
    }

    @Override
    public boolean getIsSelelct() {
        return filterItemSelect;
    }

    @Override
    public void selectSelelct(boolean isSelect) {
        filterItemSelect = isSelect;
    }


}
