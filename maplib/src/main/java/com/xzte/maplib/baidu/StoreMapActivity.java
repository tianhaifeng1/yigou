package com.xzte.maplib.baidu;

import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.trjx.tbase.activity.InitTitleActivity;
import com.trjx.tbase.module.titlemodule.TitleModule;
import com.xzte.maplib.MapApplication;
import com.xzte.maplib.R;

public class StoreMapActivity extends InitTitleActivity {


    private BaiduMap baiduMap;
    private MapView mMapview;

    private MapApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_map);


    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("门店位置", true);
        titleModule.initTitleMenu(TitleModule.MENU_TEXT, "");
        titleModule.setMenuTextColor(R.color.color_name);

        mMapview = findViewById(R.id.store_mapview);

        initMap();

    }

    private void initMap() {
        baiduMap = mMapview.getMap();

        application = (MapApplication) getApplication();

        String shopLongitude = getIntent().getStringExtra("shopLongitude");
        String shopLatitude = getIntent().getStringExtra("shopLatitude");

        //        设置位置显示属性信息
        baiduMap.setMyLocationConfiguration(application.applicationAssist.baiduLocationConfiguration());
//        开启地图的定位图层
        baiduMap.setMyLocationEnabled(true);

        mMapview.showScaleControl(true);
//        //缩放按钮
        mMapview.showZoomControls(true);

        UiSettings settings = baiduMap.getUiSettings();
////        控制是否启用或禁用俯视（3D）功能，默认开启
        settings.setOverlookingGesturesEnabled(false);
////        控制是否启用或禁用地图旋转功能，默认开启
        settings.setRotateGesturesEnabled(false);


        //显示门店位置
        LatLng targetLatLng = new LatLng(Double.valueOf(shopLatitude),Double.valueOf(shopLongitude));
        MapStatusUpdate status2 = MapStatusUpdateFactory.newLatLng(targetLatLng);
        baiduMap.setMapStatus(status2);

        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(19.0f);
        baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.datouzhen);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(targetLatLng)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        baiduMap.addOverlay(option);
    }
}
