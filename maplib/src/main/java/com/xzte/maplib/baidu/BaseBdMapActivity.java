package com.xzte.maplib.baidu;

import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.trjx.tbase.activity.InitActivity;
import com.trjx.tbase.module.titlemodule.TitleListenter;
import com.trjx.tlibs.uils.ToastUtil2;
import com.xzte.maplib.MapApplication;
import com.xzte.maplib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：小童
 * 创建时间：2019/6/6 10:24
 */
public abstract class BaseBdMapActivity extends InitActivity
        implements TMapInfoListener, TMapGeoCoderResultListener,
        TMapStatusChangeListener, OnGetRoutePlanResultListener,
        SensorEventListener, OnGetGeoCoderResultListener,
        TitleListenter {

    protected MapView mMapView;
    protected ImageView dwView;
    protected BaiduMap baiduMap;
    protected RelativeLayout lbmRl;

    private SensorManager mSensorManager;

    protected MapApplication application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initWork();
        initView();
    }

    @Override
    protected void initView() {
        //获取地图控件引用
        mMapView = findViewById(R.id.bmapView);
        dwView = findViewById(R.id.dingwei);
        lbmRl = findViewById(R.id.lbm_rl);

        application = (MapApplication) getApplication();
        application.applicationAssist.setTMapInfoListener(this);

        if (dwView != null) {
            dwView.setOnClickListener(v->application.applicationAssist.startLoction());
        }

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器管理服务

//        上海为地图中心
//        LatLng GEO_SHANGHAI = new LatLng(31.227, 121.481);
//        MapStatusUpdate status2 = MapStatusUpdateFactory.newLatLng(GEO_SHANGHAI);

        if (mMapView != null) {
            baiduMap = mMapView.getMap();
//        baiduMap.setMapStatus(status2);
//        设置位置显示属性信息
            baiduMap.setMyLocationConfiguration(application.applicationAssist.baiduLocationConfiguration());
//        开启地图的定位图层
            baiduMap.setMyLocationEnabled(true);

            mapUiSetting();

        }

    }

    // 设置默认控件和手势
    protected void mapUiSetting(){

//        UiSettings settings = baiduMap.getUiSettings();
//        //选择是否显示指南针:默认显示
//        settings.setCompassEnabled(false);
//        //比例尺：默认显示
//        mMapView.showScaleControl(false);
//        //缩放按钮
//        mMapView.showZoomControls(false);
//
//        //设置maxZoomLevel和minZoomLevel:范围为[4,21]
//        baiduMap.setMaxAndMinZoomLevel(10,21);
//        //获取当前地图级别下比例尺所表示的距离大小
////        mMapView.getMapLevel();
//
////        控制是否启用或禁用平移的功能，默认开启
//        settings.setScrollGesturesEnabled(true);
////        控制是否启用或禁用缩放手势，默认开启
//        settings.setZoomGesturesEnabled(true);
////        控制是否启用或禁用俯视（3D）功能，默认开启
//        settings.setOverlookingGesturesEnabled(true);
////        控制是否启用或禁用地图旋转功能，默认开启
//        settings.setRotateGesturesEnabled(true);
//
//
////        控制是否一并禁止所有手势，默认关闭。
//        settings.setAllGesturesEnabled(false);

    }

    /**
     * 是否显示定位按钮:默认不显示
     * @param isShow
     */
    protected void showDwView(boolean isShow){
        if(dwView == null){
            return;
        }
        if(isShow){
            dwView.setVisibility(View.VISIBLE);
        }else{
            dwView.setVisibility(View.GONE);
        }
    }

    protected BDLocation location;

    //根据需要可以重写此方法
    @Override
    public void bdSuccess(BDLocation location) {
        Log.i("tong","la = " + location.getLatitude() + "---lo = " + location.getLongitude());
        this.location = location;
        getLocationInfo(location);
    }

    //根据需要可以重写此方法
    public void getLocationInfo(BDLocation location){
        application.applicationAssist.stopLoction();
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(location.getDirection()).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        baiduMap.setMyLocationData(locData);

        movePosition(location.getLatitude(), location.getLongitude());
    }



    @Override
    public void bdFail(String failStr) {
        if(failStr == null || failStr.equals("")){
            return;
        }

        ToastUtil2.showToast(context, failStr);
    }

    private List<Overlay> mOverlayList = new ArrayList<>();

//    绘制点
    public void addMarker(double la, double lo){
        //定义Maker坐标点
        this.addMarker(la, lo,R.mipmap.ic_launcher);
//        LatLng point = new LatLng(39.963175, 116.400244);
    }
    //    绘制点
    public void addMarker(double la, double lo,int resMipmap){
        //定义Maker坐标点
        this.addMarker(new LatLng(la, lo),resMipmap);
//        LatLng point = new LatLng(39.963175, 116.400244);
    }
    public void addMarker(LatLng point){
        //定义Maker坐标点
        this.addMarker(point,R.mipmap.ic_launcher);
    }
    public void addMarker(LatLng point, int resMipmap){
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(resMipmap);
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
//在地图上添加Marker，并显示
        Overlay overlay = mMapView.getMap().addOverlay(option);
        mOverlayList.add(overlay);
    }

    public void removeMarker() {
        if (mOverlayList != null && mOverlayList.size() > 0) {
            for (Overlay overlay : mOverlayList
            ) {
                if (overlay != null && overlay.isVisible()) {
                    overlay.remove();
                }
                mOverlayList.remove(overlay);
            }
        }
    }

    //将地图移动到某一点
    public void movePosition(double la, double lo) {
        this.movePosition(new LatLng(la, lo));
    }

    public void movePosition(LatLng weizhi) {
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(weizhi)
                .zoom(18)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        baiduMap.animateMapStatus(mMapStatusUpdate);
    }


    @Override
    protected void onResume() {
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
        super.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        //取消注册传感器监听
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        baiduMap.setMyLocationEnabled(false);
        //在activity执行onDestro y时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }


    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {
        if (mapStatus == null) {
            return;
        }
        Point point = mapStatus.targetScreen;
        Log.i("tong","targetScreen = " + point.toString());
        LatLng latLng = mapStatus.target;
        Log.i("tong","targetScreen = " + latLng.toString());

    }


    private GeoCoder mCoder;
    //    地理编码（地址转坐标）
    public void geoCoder(String searchStr) {
        if (mCoder == null) {
            mCoder = GeoCoder.newInstance();
            mCoder.setOnGetGeoCodeResultListener(this);
        }
        mCoder.geocode(new GeoCodeOption()
                .city(location.getCity())
                .address(searchStr));

    }


    //逆地理编码（即坐标转地址）
    public void geoCoder(LatLng point, int page, int pageSize) {
        if (mCoder == null) {
            mCoder = GeoCoder.newInstance();
            mCoder.setOnGetGeoCodeResultListener(this);
        }
        mCoder.reverseGeoCode(new ReverseGeoCodeOption()
                .location(point)
                .pageNum(page - 1 < 0 ? 0 : page - 1)
                .pageSize(pageSize)
                // POI召回半径，允许设置区间为0-1000米，超过1000米按1000米召回。默认值为1000
                .radius(1000));
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        if (null != geoCodeResult && null != geoCodeResult.getLocation()) {
            if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有检索到结果
                return;
            } else {
                double latitude = geoCodeResult.getLocation().latitude;
                double longitude = geoCodeResult.getLocation().longitude;
            }
        }

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            //没有找到检索结果
            return;
        } else {
            //详细地址
            String address = reverseGeoCodeResult.getAddress();
            //行政区号
            int adCode = reverseGeoCodeResult. getCityCode();
        }
    }

    //用于设置定位点方向相关
    private double lastX = 0.0d;
    private int mCurrentDirection = 0;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[0];
        if (Math.abs(x - lastX) > 1.0) {
            if (location != null) {
                mCurrentDirection = (int) x;
                Log.i("tong","--------x = " + x);
                Log.i("tong","direction = " + location.getDirection() +
                        "---accuracy = " + location.getRadius());
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(location.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(mCurrentDirection).latitude(location.getLatitude())
                        .longitude(location.getLongitude()).build();
                mMapView.getMap().setMyLocationData(locData);

            }
        }
        lastX = x;

    }





    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClickBack(View view) {
        finish();
    }

    @Override
    public void onClickLeftText(View view) {

    }

    @Override
    public void onClickRightText(View view) {

    }

    @Override
    public void onClickMenu(View view) {

    }

    @Override
    public void onMenuItemClick(int position) {

    }
}
