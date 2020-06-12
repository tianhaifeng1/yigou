package com.xzte.maplib.baidu;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.model.LatLng;

/**
 * 作者：小童
 * 创建时间：2019/6/10 11:19
 */
public class BaiduMapApplicationAssist {

    private TMapInfoListener tMapInfoListener;

    private LocationClient locationClient = null;


    /**
     * 是否定位成功
     */
    public boolean isLocation = false;

//    public Context applicationContext;
//    public BaiduMapApplicationAssist(Context applicationContext) {
//        this.applicationContext = applicationContext;
//    }

    public void initBaidu(Context applicationContext) {
        SDKInitializer.initialize(applicationContext);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        initBaiduPosition(applicationContext);
    }

    private LocationClientOption option;

    private void initBaiduPosition(Context applicationContext) {

        locationClient = new LocationClient(applicationContext);

        //通过LocationClientOption设置LocationClient相关参数
        option = new LocationClientOption();
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//默认值
//        option.setNeedDeviceDirect(false);//设备方向
//        option.setLocationNotify(true);//设置是否当gps有效时，按照1s1次的频率输出gps结果
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
//      当setScanSpan < 1000时，为 APP主动请求定位； 当setScanSpan>=1000时，为定时定位模式（setScanSpan的值就是定时定位的时间间隔）；
        option.setScanSpan(1);//定位时间
        //可以获取到地址详情：不设置获取不到地址
        option.setIsNeedAddress(true);
        //可选，是否需要位置描述信息，默认为不需要，即参数为false
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        option.setIsNeedAltitude(true);
//设置locationClientOption
        locationClient.setLocOption(option);
//注册LocationListener监听器
        locationClient.registerLocationListener(listener);
//开启地图定位图层
        locationClient.start();
    }


    //启动定位
    public void startLoction() {
        //启动定位
        locationClient.restart();
    }

    //停止定位
    public void stopLoction() {
        locationClient.stop();
    }

    /**
     * 设置扫描时间
     *
     * @param scanSpan 注：小于1000 为手动定位
     *                 大于1000 为自动定位
     */
    public void setScanSpan(int scanSpan) {
        locationClient.getLocOption().setScanSpan(scanSpan);
    }


    //将地图移动到某一点
    public void movePosition(double la, double lo, BaiduMap baiduMap) {
        this.movePosition(new LatLng(la, lo), baiduMap);
    }

    public void movePosition(LatLng weizhi, BaiduMap baiduMap) {
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(weizhi)
                .zoom(18)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        baiduMap.animateMapStatus(mMapStatusUpdate);
    }


    public void setTMapInfoListener(TMapInfoListener tMapInfoListener) {
        this.tMapInfoListener = tMapInfoListener;
    }


    BDAbstractLocationListener listener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            Log.i("tong_map", "进来了");
            if (null == location) {
                if (null != tMapInfoListener) {
                    isLocation = false;
                    tMapInfoListener.bdFail("定位失败");
                }
                return;
            }
            int type = location.getLocType();
            if (type == BDLocation.TypeGpsLocation
                    || type == BDLocation.TypeOffLineLocation
                    || type == BDLocation.TypeNetWorkLocation) {
                //Gps定位、离线、网络定位成功
                if (null != tMapInfoListener) {
                    isLocation = true;
                    tMapInfoListener.bdSuccess(location);
                }
            } else {
                if (null != tMapInfoListener) {
                    isLocation = false;
                    tMapInfoListener.bdFail("定位异常，请检查");
                }
            }

//                if (location.getLocType() == BDLocation.TypeGpsLocation) {
//
//                    // GPS定位结果
//                    if (null != tMapInfoListener) {
//                        isLocation = true;
//                        tMapInfoListener.bdSuccess(location);
//                    }
//                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//                    // 网络定位结果
//                    if (null != tMapInfoListener) {
//                        isLocation = true;
//                        tMapInfoListener.bdSuccess(location);
//                    }
//                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
//                    // 离线定位结果
//                    if (null != tMapInfoListener) {
//                        isLocation = true;
//                        tMapInfoListener.bdSuccess(location);
//                    }
//                } else if (location.getLocType() == BDLocation.TypeServerError) {
////                    MapApplication.this.location = null;
//                    if (null != tMapInfoListener) {
//                        isLocation = false;
//                        tMapInfoListener.bdFail("服务器错误，请检查" );
//                    }
//                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
////                    MapApplication.this.location = null;
//                    if (null != tMapInfoListener) {
//                        isLocation = false;
//                        tMapInfoListener.bdFail("网络错误，请检查");
//                    }
//
//
//                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
////                    MapApplication.this.location = null;
//                    if (null != tMapInfoListener) {
//                        isLocation = false;
//                        tMapInfoListener.bdFail("手机模式错误，请检查是否飞行");
//                    }
//                }
        }
    };


    //        设置位置显示属性信息
    public MyLocationConfiguration baiduLocationConfiguration() {
        //        定位模式
        MyLocationConfiguration.LocationMode mode = MyLocationConfiguration.LocationMode.NORMAL;
//        是否开启方向
        boolean enableDirection = true;
//        设置自定义定位图标
//        BitmapDescriptor customMarker = BitmapDescriptorFactory.fromResource(R.mipmap.home_position);
//        精度圈填充颜色
        int accuracyCircleFillColor = 0xAAFFFF88;
//        精度圈边框颜色
        int accuracyCircleStrokeColor = 0xAA00FF00;
        MyLocationConfiguration configuration = new MyLocationConfiguration(
                mode,
                enableDirection,
                null,
                accuracyCircleFillColor,
                accuracyCircleStrokeColor);
        return configuration;
//        baiduMap.setMyLocationConfiguration(configuration);
    }

    private TMapStatusChangeListener tMapStatusChangeListener;

    public void setTMapStatusChangeListener(BaiduMap baiduMap, TMapStatusChangeListener tMapStatusChangeListener) {
        this.tMapStatusChangeListener = tMapStatusChangeListener;
        baiduMap.setOnMapStatusChangeListener(changeListener);
    }


    /**
     * 地图状态改变的监听
     */
    private BaiduMap.OnMapStatusChangeListener changeListener = new BaiduMap.OnMapStatusChangeListener() {
        @Override
        public void onMapStatusChangeStart(MapStatus mapStatus) {

        }

        @Override
        public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

        }

        @Override
        public void onMapStatusChange(MapStatus mapStatus) {

        }

        @Override
        public void onMapStatusChangeFinish(MapStatus mapStatus) {
            if (tMapStatusChangeListener != null) {
                tMapStatusChangeListener.onMapStatusChangeFinish(mapStatus);
            }
        }
    };

}