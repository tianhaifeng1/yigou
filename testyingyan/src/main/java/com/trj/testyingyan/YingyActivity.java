package com.trj.testyingyan;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.baidu.location.BDLocation;
import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.Trace;
import com.baidu.trace.api.track.HistoryTrackRequest;
import com.baidu.trace.api.track.HistoryTrackResponse;
import com.baidu.trace.api.track.OnTrackListener;
import com.baidu.trace.api.track.TrackPoint;
import com.baidu.trace.model.OnTraceListener;
import com.baidu.trace.model.PushMessage;
import com.google.gson.Gson;
import com.trjx.tbase.activity.InitTitleActivity;
import com.trjx.tlibs.uils.Logger;
import com.xzte.maplib.baidu.TMapInfoListener;

import java.util.List;

public class YingyActivity extends InitTitleActivity implements TMapInfoListener {

    private TestYyApp app;

    private Trace mTrace;

    private LBSTraceClient mTraceClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yingy);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("测试鹰眼服务", true);

        keepScreenLongLight(true);

        app = (TestYyApp) getApplication();
        app.applicationAssist.setScanSpan(1000);
        app.applicationAssist.setTMapInfoListener(this);
        app.applicationAssist.startLoction();

        // 轨迹服务ID
        long serviceId = 217384;
// 设备标识
        String entityName = "tongrj";
// 是否需要对象存储服务，默认为：false，关闭对象存储服务。
// 注：鹰眼 Android SDK v3.0以上版本支持随轨迹上传图像等对象数据，若需使用此功能，该参数需设为 true，且需导入bos-android-sdk-1.0.2.jar。
        boolean isNeedObjectStorage = false;
// 初始化轨迹服务
        mTrace = new Trace(serviceId, entityName, isNeedObjectStorage);
// 初始化轨迹服务客户端
        mTraceClient = new LBSTraceClient(getApplicationContext());

        // 定位周期(单位:秒)
        int gatherInterval = 5;
// 打包回传周期(单位:秒)
        int packInterval = 10;
// 设置定位和打包周期
        mTraceClient.setInterval(gatherInterval, packInterval);


        // 开启服务
        mTraceClient.startTrace(mTrace, onTraceListener);


    }

    @Override
    public void bdSuccess(BDLocation aMapLocation) {

    }

    @Override
    public void bdFail(String failStr) {

    }


    public void onClickLook(View view) {
        // 请求标识
        int tag = 1;
// 轨迹服务ID
        long serviceId = 217384;
// 设备标识
        String entityName = "tongrj";
// 创建历史轨迹请求实例
        HistoryTrackRequest historyTrackRequest = new HistoryTrackRequest(tag, serviceId, entityName);

//设置轨迹查询起止时间
// 开始时间(单位：秒)
        long startTime = System.currentTimeMillis() / 1000 - 12 * 60 * 60;
// 结束时间(单位：秒)
        long endTime = System.currentTimeMillis() / 1000;
// 设置开始时间
        historyTrackRequest.setStartTime(startTime);
// 设置结束时间
        historyTrackRequest.setEndTime(endTime);


// 初始化轨迹监听器
        OnTrackListener mTrackListener = new OnTrackListener() {
            // 历史轨迹回调
            @Override
            public void onHistoryTrackCallback(HistoryTrackResponse response) {
                List<TrackPoint> list = response.getTrackPoints();
                for (int i = 0, size = list.size(); i < size; i++) {
                    Logger.t("result：" + new Gson().toJson(list.get(i)));
                }
            }
        };


// 查询历史轨迹
        mTraceClient.queryHistoryTrack(historyTrackRequest, mTrackListener);

    }



    OnTraceListener onTraceListener = new OnTraceListener() {
        @Override
        public void onBindServiceCallback(int i, String s) {

        }

        // 开启服务回调
        @Override
        public void onStartTraceCallback(int i, String s) {
            if(i==0){
                // 开启采集
                mTraceClient.startGather(onTraceListener);
            }
        }

        // 停止服务回调
        @Override
        public void onStopTraceCallback(int i, String s) {

        }

        // 开启采集回调
        @Override
        public void onStartGatherCallback(int i, String s) {

        }

        // 停止采集回调
        @Override
        public void onStopGatherCallback(int i, String s) {

        }

        // 推送回调
        @Override
        public void onPushCallback(byte b, PushMessage pushMessage) {

        }

        @Override
        public void onInitBOSCallback(int i, String s) {

        }
    };

    @Override
    protected void onDestroy() {
        keepScreenLongLight(false);
        // 停止服务
        if (mTraceClient != null) {
            mTraceClient.stopTrace(mTrace, onTraceListener);
        }
        super.onDestroy();
    }


    /**
     * 是否使屏幕常亮
     *
     */
    public void keepScreenLongLight(boolean isOpenLight) {
        Window window = getWindow();
        if (isOpenLight) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

}
