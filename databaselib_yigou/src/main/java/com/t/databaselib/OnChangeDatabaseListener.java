package com.t.databaselib;

public interface OnChangeDatabaseListener {

    /**
     * 数据库数据是否改变
     * @param isChange true 为改变，反之未改变
     */
    void isChangeDatabase(boolean isChange);

}
