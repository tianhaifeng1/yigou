package com.xzte.maplib;

import com.t.databaselib.DatabaseApplicationAssist;
import com.trjx.tbase.InitApplication;
import com.xzte.maplib.baidu.BaiduMapApplicationAssist;

/**
 * Application 需要实现此接口
 */
public class MapApplication extends InitApplication {

    public BaiduMapApplicationAssist applicationAssist;
    public DatabaseApplicationAssist databaseAssist;

    public void initBaidu() {

        applicationAssist = new BaiduMapApplicationAssist();
        applicationAssist.initBaidu(this);

        //初始化GreenDao,直接在Application中进行初始化操作
        databaseAssist = new DatabaseApplicationAssist(this);
        databaseAssist.initGreenDao();
    }

}
