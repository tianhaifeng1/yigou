package com.trj.testyingyan;

import com.trjx.tbase.InitApplication;
import com.xzte.maplib.baidu.BaiduMapApplicationAssist;

public class TestYyApp extends InitApplication {

    public BaiduMapApplicationAssist applicationAssist;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationAssist = new BaiduMapApplicationAssist();
        applicationAssist.initBaidu(this);

    }
}
