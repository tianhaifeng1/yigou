package com.work.doctor.fruits.base;

import com.trjx.tlibs.uils.Logger;
import com.xzte.maplib.MapApplication;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;

/**
 * 需要继承MapApplication
 */
public class DemoApplication extends MapApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initBaidu();


        //激光统计的初始化
        JAnalyticsInterface.init(this);
        //设置调试模式：参数为 true 表示打开调试模式，可看到 sdk 的日志
        JAnalyticsInterface.setDebugMode(true);
        //统计上报周期：period：周期，单位秒，最小10秒，最大1天，超出范围会打印调用失败日志。传0表示统计数据即时上报
        JAnalyticsInterface.setAnalyticsReportPeriod(this, 0);
        //CrashLog 收集并上报:2.1.8 及以后版本，默认为开启状态，并增加 stopCrashHandler 接口（关闭此功能）
        JAnalyticsInterface.initCrashHandler(this);
        //关闭异常收集上报功能
//        JAnalyticsInterface.stopCrashHandler(this);

        //打开Log打印
        Logger.LOG_ENABLE = true;

        setBugglyAppid("45f28af007");

    }

}
