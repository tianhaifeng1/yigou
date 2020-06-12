package com.xzte.maplib.baidu;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.google.gson.Gson;
import com.t.databaselib.DatabaseCityInfo;
import com.t.databaselib.GreenDaoAssist;
import com.trjx.tbase.activity.InitTitleActivity;
import com.trjx.tbase.module.titlemodule.TitleModule;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.TUtils;
import com.xzte.maplib.MapApplication;
import com.xzte.maplib.R;
import com.xzte.maplib.baidu.adapter.HistroyDataAdapter;
import com.xzte.maplib.baidu.adapter.MapDataAdapter;
import com.xzte.maplib.city.CitySelectActivity;

import java.util.List;


public class SearchAddressActivity extends InitTitleActivity
        implements View.OnClickListener, TMapInfoListener, SensorEventListener, OnGetGeoCoderResultListener, TMapStatusChangeListener, OnGetPoiSearchResultListener {

    private BaiduMap baiduMap;
    private MapView mSaMapview;
    private ImageView mSaDingwei;
    private ImageView mSaDtz;
    private RecyclerView mSaRecyclerviewMapdata;
    private TextView resultTextView;
    private ProgressBar progressBar;
    private ImageView mSaDelete;
    private RecyclerView mSaRecyclerviewHistorydata;
    private RecyclerView mSaRecyclerviewSearchdata;
    private TextView mSaRecyclerviewSearchdataDefaultview;
    private RelativeLayout mSaLeveltwo;
    private ImageView mSaIcon;
    private TextView mSaCitytext;
    private ImageView mSaSearchicon;
    private EditText mSaSearchcontent;

    private MapApplication application;
    private boolean isShowMapView;

    private SensorManager mSensorManager;
    private GreenDaoAssist greenDaoAssist;

    //搜索类型
    private String cityName;

    private int mapDataPage = 1;
    private int mapDataPageSize = 20;
    private MapDataAdapter mapDataAdapter;

    private LatLng targetLatLng;//默认拉萨市政府

    private int searchDataPage = 1;
    private int searchDataPageSize = 20;
    private PoiSearch poiSearch;
    private LinearLayoutManager layoutManagerSearchData;
    private MapDataAdapter searchDataAdapter;
    private String searchStr = "";


    private HistroyDataAdapter histroyDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_address);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("", true);
        titleModule.initTitleMenu(TitleModule.MENU_TEXT, "");
        titleModule.setMenuTextColor(R.color.color_name);
        mSaMapview = findViewById(R.id.sa_mapview);
        mSaDingwei = findViewById(R.id.sa_dingwei);
        mSaDtz = findViewById(R.id.sa_dtz);
        mSaRecyclerviewMapdata = findViewById(R.id.sa_recyclerview_mapdata);
        progressBar = findViewById(R.id.sa_progressbar);
        resultTextView = findViewById(R.id.sa_result_tv);
        mSaDelete = findViewById(R.id.sa_delete);
        mSaRecyclerviewHistorydata = findViewById(R.id.sa_recyclerview_historydata);
        mSaRecyclerviewSearchdata = findViewById(R.id.sa_recyclerview_searchdata);
        mSaRecyclerviewSearchdataDefaultview = findViewById(R.id.sa_recyclerview_searchdata_defaultview);
        mSaLeveltwo = findViewById(R.id.sa_leveltwo);
        mSaIcon = findViewById(R.id.sa_icon);
        mSaCitytext = findViewById(R.id.sa_citytext);
        mSaSearchicon = findViewById(R.id.sa_searchicon);
        mSaSearchcontent = findViewById(R.id.sa_searchcontent);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器管理服务
        greenDaoAssist = new GreenDaoAssist(((MapApplication) getApplication()).databaseAssist);

        initInfo();

        initMap();

    }

    private void initMap() {

        application = (MapApplication) getApplication();
        application.applicationAssist.setTMapInfoListener(this);

        baiduMap = mSaMapview.getMap();
        //默认拉萨市政府
        targetLatLng = new LatLng(29.659504, 91.178454);
        MapStatusUpdate status2 = MapStatusUpdateFactory.newLatLng(targetLatLng);
        baiduMap.setMapStatus(status2);

//        baiduMap.setMapStatus(status2);
//        设置位置显示属性信息
        baiduMap.setMyLocationConfiguration(application.applicationAssist.baiduLocationConfiguration());
//        开启地图的定位图层
        baiduMap.setMyLocationEnabled(true);
//        //比例尺：默认显示
        mSaMapview.showScaleControl(false);
//        //缩放按钮
        mSaMapview.showZoomControls(false);
        UiSettings settings = baiduMap.getUiSettings();
//        //选择是否显示指南针:默认显示
        settings.setCompassEnabled(false);
////        控制是否启用或禁用俯视（3D）功能，默认开启
        settings.setOverlookingGesturesEnabled(false);
////        控制是否启用或禁用地图旋转功能，默认开启
        settings.setRotateGesturesEnabled(false);

        //添加监听大头针的位置信息
        application.applicationAssist.setTMapStatusChangeListener(baiduMap, this);
        application.applicationAssist.startLoction();


    }

    /**
     * 初始化显示内容
     */
    private void initInfo() {

        Intent intent = getIntent();
        //标题
        String titleStr = intent.getStringExtra("title");
        titleModule.setTitleText(titleStr);
        cityName = intent.getStringExtra("cityName");
        if (cityName == null || cityName.equals("")) {
            cityName = "拉萨市";
        }
        mSaCitytext.setText(cityName);
        //是否显示Map:默认显示
        isShowMapView = intent.getBooleanExtra("isShowMapView", true);
        if (!isShowMapView) {
            mSaLeveltwo.setVisibility(View.VISIBLE);
            titleModule.setMenuText("取消");
        }

        //地图数据
        mapDataAdapter = new MapDataAdapter(null);
        mapDataAdapter.setOnLoadMoreListener(() -> {
            geoCoder(targetLatLng, ++mapDataPage, mapDataPageSize);
        }, mSaRecyclerviewMapdata);
        mSaRecyclerviewMapdata.setLayoutManager(new LinearLayoutManager(context));
        mSaRecyclerviewMapdata.setAdapter(mapDataAdapter);
        mapDataAdapter.setOnItemClickListener((adapter, view, position) -> {
            PoiInfo poiInfo = (PoiInfo) adapter.getData().get(position);
            DatabaseCityInfo cityInfo = new DatabaseCityInfo();
            cityInfo.setProvince(reverseGeoCodeResult.getAddressDetail().province);
            cityInfo.setCity(reverseGeoCodeResult.getAddressDetail().city);
            cityInfo.setDistrict(reverseGeoCodeResult.getAddressDetail().district);
            addSearchCityData(cityInfo, poiInfo);
        });

        // 搜索数据
        searchDataAdapter = new MapDataAdapter(null);
        searchDataAdapter.setOnLoadMoreListener(() -> {
            searchContent(searchStr, ++searchDataPage);
        }, mSaRecyclerviewSearchdata);
        layoutManagerSearchData = new LinearLayoutManager(context);
        mSaRecyclerviewSearchdata.setLayoutManager(layoutManagerSearchData);
        mSaRecyclerviewSearchdata.setAdapter(searchDataAdapter);
        searchDataAdapter.setOnItemClickListener((adapter, view, position) -> {
            PoiInfo poiInfo = (PoiInfo) adapter.getData().get(position);
            DatabaseCityInfo cityInfo = new DatabaseCityInfo();
            cityInfo.setProvince(poiInfo.getProvince());
            cityInfo.setCity(poiInfo.getCity());
            cityInfo.setDistrict(poiInfo.getArea());
            addSearchCityData(cityInfo, poiInfo);
        });

        // 历史搜索数据
        List<DatabaseCityInfo> cityInfoList = greenDaoAssist.queryCityInfo();
        histroyDataAdapter = new HistroyDataAdapter(cityInfoList);
        mSaRecyclerviewHistorydata.setLayoutManager(new LinearLayoutManager(context));
        mSaRecyclerviewHistorydata.setAdapter(histroyDataAdapter);
        histroyDataAdapter.setOnItemClickListener((adapter, view, position) -> {
            DatabaseCityInfo cityInfo = (DatabaseCityInfo) adapter.getData().get(position);
            greenDaoAssist.updataCityInfoData(cityInfo.getId());

            skipBack(cityInfo);
        });

        mSaDingwei.setOnClickListener(this);
        mSaDelete.setOnClickListener(this);
        mSaIcon.setOnClickListener(this);
        mSaCitytext.setOnClickListener(this);
        mSaSearchicon.setOnClickListener(this);
        mSaSearchcontent.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                mSaLeveltwo.setVisibility(View.VISIBLE);
                mSaSearchcontent.setCursorVisible(true);
                TUtils.showSystemKeyboard(context, view);
                titleModule.setMenuText("取消");
            }
            return false;
        });

        mSaSearchcontent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchStr = s.toString().trim();
                if (searchStr.equals("")) {
                    //清空列表：根据需求
                    mSaRecyclerviewSearchdata.setVisibility(View.GONE);
                    return;
                } else {
                    mSaRecyclerviewSearchdata.setVisibility(View.VISIBLE);
                    searchDataPage = 1;
                    searchContent(searchStr, searchDataPage);
                }

            }
        });

    }


    private void addSearchCityData(DatabaseCityInfo cityInfo, PoiInfo poiInfo) {
        cityInfo.setName(poiInfo.getName());
        cityInfo.setAddressDetail(poiInfo.getAddress());
        cityInfo.setLatitude(poiInfo.getLocation().latitude);
        cityInfo.setLongitude(poiInfo.getLocation().longitude);
        greenDaoAssist.insertCityInfoData(cityInfo);
        skipBack(cityInfo);
    }

    private void skipBack(DatabaseCityInfo cityInfo) {

        Intent intent = new Intent();
        intent.putExtra("name", cityInfo.getName());
        intent.putExtra("sheng", cityInfo.getProvince());
        intent.putExtra("shi", cityInfo.getCity());
        intent.putExtra("qu", cityInfo.getDistrict());
        intent.putExtra("la", cityInfo.getLatitude());
        intent.putExtra("lo", cityInfo.getLongitude());
        setResult(601, intent);
        finish();
    }


    @Override
    public void onClick(View view) {
        int ids = view.getId();
        if (ids == R.id.sa_dingwei) {
            //回到定位点
            application.applicationAssist.startLoction();
        } else if (ids == R.id.sa_delete) {
//            删除搜素历史记录
            greenDaoAssist.clearCityInfoDatabase();
            histroyDataAdapter.setNewData(null);
            histroyDataAdapter.notifyDataSetChanged();
        } else if (ids == R.id.sa_icon || ids == R.id.sa_citytext) {
//            选择城市
            skipActivity(CitySelectActivity.class,100);
        } else if (ids == R.id.sa_searchicon) {
//            搜索
            mSaLeveltwo.setVisibility(View.VISIBLE);
            mSaSearchcontent.setCursorVisible(true);
            TUtils.showSystemKeyboard(context, view);
            titleModule.setMenuText("取消");
        }
    }

    @Override
    public void onClickRightText(View view) {
        super.onClickRightText(view);
        if (!isShowMapView) {
            finish();
        } else {
            mSaLeveltwo.setVisibility(View.GONE);
            mSaSearchcontent.setCursorVisible(false);
            mSaSearchcontent.setText("");
            TUtils.hideSystemKeyboard(context, view);
            titleModule.setMenuText("");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if(resultCode == 701){
                if (data != null) {
                    cityName = data.getStringExtra("cityName");
                    mSaCitytext.setText(cityName);
                }
            }
        }
    }


    @Override
    protected void onResume() {
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mSaMapview.onResume();
        super.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mSaMapview.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        //取消注册传感器监听
        mSensorManager.unregisterListener(this);
        //释放实例
        if (poiSearch != null) {
            poiSearch.destroy();
            poiSearch = null;
        }
        if (mCoder != null) {
            mCoder.destroy();
            mCoder = null;
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        baiduMap.setMyLocationEnabled(false);
        //在activity执行onDestro y时执行mMapView.onDestroy()，实现地图生命周期管理
        mSaMapview.onDestroy();
        mSaMapview = null;
        super.onDestroy();
    }

    protected BDLocation aMapLocation;

    @Override
    public void bdSuccess(BDLocation aMapLocation) {
        this.aMapLocation = aMapLocation;

        application.applicationAssist.stopLoction();
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(aMapLocation.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(aMapLocation.getDirection()).latitude(aMapLocation.getLatitude())
                .longitude(aMapLocation.getLongitude()).build();
        baiduMap.setMyLocationData(locData);
        application.applicationAssist.movePosition(aMapLocation.getLatitude(), aMapLocation.getLongitude(), baiduMap);

        if (cityName == null || cityName.equals("")) {
            cityName = aMapLocation.getCity();
            mSaCitytext.setText(cityName);
        }

        targetLatLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
        geoCoder(targetLatLng, 1, mapDataPageSize);
    }

    private ObjectAnimator objectAnimator;

    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {
        if (mapStatus == null) {
            return;
        }
        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(mSaDtz, "translationY", 0, -40, -60, 0);
        }
        objectAnimator.setDuration(800);
        objectAnimator.start();
        mapDataPage = 1;
        targetLatLng = mapStatus.target;
        geoCoder(targetLatLng, 1, mapDataPageSize);
    }

    public void searchContent(String searchStr, int page) {
        /**
         *  PoiCiySearchOption 设置检索属性
         *  city 检索城市
         *  keyword 检索内容关键字
         *  pageNum 分页页码
         */
        if (poiSearch == null) {
            poiSearch = PoiSearch.newInstance();
            poiSearch.setOnGetPoiSearchResultListener(this);
        }
        if(cityName==null||cityName.equals("")){
            poiSearch.searchInCity(new PoiCitySearchOption()
                    .city("拉萨市") //必填
                    .cityLimit(false)
                    .keyword(searchStr) //必填
                    .scope(2)
                    .pageNum(page - 1 < 0 ? 0 : page - 1)
                    .pageCapacity(searchDataPageSize));
        }else{
            poiSearch.searchInCity(new PoiCitySearchOption()
                    .city(cityName) //必填
                    .cityLimit(true)
                    .keyword(searchStr) //必填
                    .scope(2)
                    .pageNum(page - 1 < 0 ? 0 : page - 1)
                    .pageCapacity(searchDataPageSize));
        }

    }

    private GeoCoder mCoder;

    //逆地理编码（即坐标转地址）
    public void geoCoder(LatLng point, int page, int pageSize) {
        progressBar.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.GONE);
        if (mCoder == null) {
            mCoder = GeoCoder.newInstance();
            mCoder.setOnGetGeoCodeResultListener(this);
        }
        mCoder.reverseGeoCode(new ReverseGeoCodeOption()
                .location(point)
                .newVersion(1)
                .pageNum(page - 1 < 0 ? 0 : page - 1)
                .pageSize(pageSize)
                // POI召回半径，允许设置区间为0-1000米，超过1000米按1000米召回。默认值为1000
                .radius(1000));
    }

    @Override
    public void bdFail(String failStr) {
//        "latitude": 29.659504,
//                "latitudeE6": 2.9659504E7,
//                "longitude": 91.178454,
//                "longitudeE6": 9.1178454E7

        geoCoder(targetLatLng, 1, mapDataPageSize);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    private ReverseGeoCodeResult reverseGeoCodeResult;

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        progressBar.setVisibility(View.GONE);

        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            //没有找到检索结果
            Log.i("tong", "没有找到检索结果");
            List<PoiInfo> poiInfoList = mapDataAdapter.getData();
            if (poiInfoList == null || poiInfoList.size() == 0) {
                resultTextView.setVisibility(View.VISIBLE);
            }
            return;
        } else {
            this.reverseGeoCodeResult = reverseGeoCodeResult;
            Log.i("tong", "出结果了");
            List<PoiInfo> poiInfoList = reverseGeoCodeResult.getPoiList();
            final int size = poiInfoList == null ? 0 : poiInfoList.size();
            if (mapDataPage == 1) {
                if(size == 0){
                    resultTextView.setVisibility(View.VISIBLE);
                }
                mapDataAdapter.setNewData(poiInfoList);
            } else {
                if (size > 0) {
                    mapDataAdapter.addData(poiInfoList);
                }
            }
            if (size < mapDataPageSize) {
                //false：显示结尾没有更多数据，反之不显示
                mapDataAdapter.loadMoreEnd(false);
            } else {
                mapDataAdapter.loadMoreComplete();
            }

        }
    }

    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        Logger.t(new Gson().toJson(poiResult));
        List<PoiInfo> poiInfoList = poiResult.getAllPoi();
        final int size = poiInfoList == null ? 0 : poiInfoList.size();
        if (searchDataPage == 1) {
            if (size == 0) {
                mSaRecyclerviewSearchdataDefaultview.setVisibility(View.VISIBLE);
            }else{
                mSaRecyclerviewSearchdataDefaultview.setVisibility(View.GONE);
                layoutManagerSearchData.scrollToPosition(0);
            }
            searchDataAdapter.setNewData(poiInfoList);
        } else {
            if (size > 0) {
                searchDataAdapter.addData(poiInfoList);
            }
        }
        if (size < searchDataPageSize) {
            //false：显示结尾没有更多数据，反之不显示
            searchDataAdapter.loadMoreEnd(false);
        } else {
            searchDataAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }
}