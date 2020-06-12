package com.xzte.maplib.baidu;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.trjx.tbase.module.titlemodule.TitleModule;
import com.trjx.tlibs.uils.TUtils;
import com.trjx.tlibs.uils.ToastUtil2;
import com.xzte.maplib.R;

import java.util.ArrayList;
import java.util.List;

public class BaiduMapSearchActivity extends BaseBdMapActivity {

    private TextView mapSearch;
    private TextView mapCity;
    private AutoCompleteTextView mapSearchContent;
    private ListView mapSearchList;

    private RelativeLayout relativeLayout;
    private ImageView dtzImg;

    private PoiSearch mPoiSearch;
    private SuggestionSearch suggestionSearch;

    private List<PoiInfo> poiInfoList = new ArrayList<>();
    private SearchAdapter searchAdapter;

    private ArrayAdapter<String> arrayAdapter;
    private List<SuggestionResult.SuggestionInfo> sugInfoList = new ArrayList<>();
    private List<String> strList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_map_search);
    }

    @Override
    protected void initView() {
        super.initView();

        TitleModule titleModule = new TitleModule(context, rootView);
        titleModule.setListenter(this);
        titleModule.initTitle("搜索",true);

        application.applicationAssist.setTMapStatusChangeListener(baiduMap,this);
        application.applicationAssist.startLoction();

        //显示定位按钮
//        showDwView(true);

        mapCity = findViewById(R.id.map_city);
        mapSearch = findViewById(R.id.map_search);
        mapSearchContent = findViewById(R.id.map_search_content);
        mapSearchList = findViewById(R.id.map_search_list);

        relativeLayout = findViewById(R.id.map_search_rl);
        dtzImg = findViewById(R.id.map_search_dtz);


        arrayAdapter = new ArrayAdapter<>(context, R.layout.item_text, R.id.item_text1, strList);
        mapSearchContent.setAdapter(arrayAdapter);
        mapSearchContent.setThreshold(1);
//
//        searchStringAdapter = new SearchStringAdapter(context, strList);
//        mapSearchContent.setAdapter(searchStringAdapter);

//        arrayAdapter = new ArrayAdapter<>(context, R.layout.item_text, R.id.item_text1, strList);
//        mapSearchContent.setAdapter(arrayAdapter);



        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
        mapSearch.setOnClickListener(v ->searchEvent());

        suggestionSearch = SuggestionSearch.newInstance();
        suggestionSearch.setOnGetSuggestionResultListener(listenerSug);
        mapSearchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                suggestionSearch.requestSuggestion(new SuggestionSearchOption()
                        .city(location.getCity())
                        .keyword(s.toString()));
            }
        });

        searchAdapter = new SearchAdapter(context, poiInfoList);
        mapSearchList.setAdapter(searchAdapter);
        mapSearchList.setOnItemClickListener((parent, view, position, id) -> {
//            ToastUtils.toastShort(context,"点击了" + poiInfoList.get(position).getName());
            Intent intent = new Intent();
            intent.putExtra("info", poiInfoList.get(position));
            setResult(601, intent);
            finish();
        });

        mapSearchContent.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchEvent();
                return true;
            }

            return false;
        });



    }

    private ObjectAnimator objectAnimator;

    @Override
    protected void mapUiSetting() {
        super.mapUiSetting();
        UiSettings settings = baiduMap.getUiSettings();
//        //选择是否显示指南针:默认显示
        settings.setCompassEnabled(false);
//        //比例尺：默认显示
        mMapView.showScaleControl(false);
//        //缩放按钮
        mMapView.showZoomControls(false);
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
        settings.setOverlookingGesturesEnabled(false);
////        控制是否启用或禁用地图旋转功能，默认开启
        settings.setRotateGesturesEnabled(false);

    }

    private void searchEvent(){
        String contentStr = mapSearchContent.getText().toString().trim();
        if (contentStr != null) {
            //点击搜索的时候隐藏软键盘

            TUtils.hideSystemKeyboard(context, mapSearchContent);
            getSearchData(location.getCity(), contentStr);
        }else{
            //清空列表：根据需求
        }

    }


    /**
     * 根据实际逻辑重写此方法
     * @param location
     */
    @Override
    public void getLocationInfo(BDLocation location) {
        //        默认实现逻辑，满足定位需求
        super.getLocationInfo(location);
        mapCity.setText(location.getCity());

//        String addr = location.getAddrStr();    //获取详细地址信息
//        String country = location.getCountry();    //获取国家
//        String province = location.getProvince();    //获取省份
//        String city = location.getCity();    //获取城市
//        String district = location.getDistrict();    //获取区县
//        String street = location.getStreet();    //获取街道信息

//        Logger.t("----addr = " + addr);
//        Logger.t("----country = " + country);
//        Logger.t("----province = " + province);
//        Logger.t("----city = " + city);
//        Logger.t("----district = " + district);
//        Logger.t("----street = " + street);
//        Logger.t("----Describe = " + location.getLocationDescribe());////获取位置描述信息
//        Logger.t("----loc---la = " + location.getLatitude());////获取位置描述信息
//        Logger.t("----loc---lo = " + location.getLongitude());////获取位置描述信息

        geoCoder(new LatLng(location.getLatitude(), location.getLongitude()),1,20);

    }


    private void getSearchData(String cityName, String searchStr) {

        /**
         *  PoiCiySearchOption 设置检索属性
         *  city 检索城市
         *  keyword 检索内容关键字
         *  pageNum 分页页码
         */
        mPoiSearch.searchInCity(new PoiCitySearchOption()
                .city(cityName) //必填
                .keyword(searchStr) //必填
                .pageNum(10));

    }

    OnGetSuggestionResultListener listenerSug = suggestionResult -> {
        Log.i("tong","----进来了");
        //处理sug检索结果
        if (suggestionResult != null) {
            Log.i("tong","------1");
            List<SuggestionResult.SuggestionInfo> suggestionInfoList = suggestionResult.getAllSuggestions();
            if (suggestionInfoList != null && suggestionInfoList.size() > 0) {
                Log.i("tong","Sug-------" + suggestionInfoList.toString());
                sugInfoList.clear();
                sugInfoList.addAll(suggestionInfoList);
                
                strList.clear();
                for (SuggestionResult.SuggestionInfo info:suggestionInfoList) {
                    strList.add(info.getKey());
                }
                arrayAdapter = new ArrayAdapter<>(context, R.layout.item_text, R.id.item_text1, strList);
                mapSearchContent.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
            }
        }
    };


    OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            if (poiResult != null) {
//                List<CityInfo> ci = poiResult.getSuggestCityList();
//                if (ci != null) {
//                    Logger.t("poi---1----" + ci.toString());
//
//                }
//                List<PoiAddrInfo> aa = poiResult.getAllAddr();
//                if (aa != null) {
//                    Logger.t("poi---2----" + aa.toString());
//
//                }

//                List<PoiInfo> ap = poiResult.getAllPoi();
//                if (ap != null) {
//                    Logger.t("poi---3----" + ap.toString());
//                    poiInfoList.clear();
//                    poiInfoList.addAll(ap);
//                    searchAdapter.notifyDataSetChanged();
//                    if (poiInfoList.size() > 0) {
//                        PoiInfo poiInfo = poiInfoList.get(0);
//                        removeMarker();
//                        addMarker(poiInfo.getLocation());
//                        movePosition(poiInfo.getLocation());
//                    }
//                }else{
//                    ToastUtil.showToast(context,"没有找到相关数据");
//                }
            }else{
                ToastUtil2.showToast(context,"没有找到相关数据");
            }

        }
        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
            Log.i("tong","poi---onGetPoiDetailResult----");
        }
        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
            Log.i("tong","poi---onGetPoiIndoorResult----");
        }
        //废弃
        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }
    };

    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {
        if (mapStatus == null) {
            return;
        }
        Point point = mapStatus.targetScreen;
        Log.i("tong","targetScreen = " + point.toString());
        LatLng latLng = mapStatus.target;
        Log.i("tong","targetScreen = "+latLng.toString());

        if (objectAnimator == null) {
            int height = relativeLayout.getHeight();
            Log.i("tong","height = " + height);
            objectAnimator = ObjectAnimator.ofFloat(dtzImg, "translationY", 0, -height / 8, -height * 3 / 16, 0);
        }
        objectAnimator.setDuration(800);
        objectAnimator.start();
        geoCoder(latLng,1,20);

    }

//    //逆地理编码（即坐标转地址）
//    public void geoCoder(LatLng point) {
//        GeoCoder mCoder = GeoCoder.newInstance();
//        application.applicationAssist.setTMapGeoCoderResultListener(mCoder,this);
//        mCoder.reverseGeoCode(new ReverseGeoCodeOption()
//                .location(point)
//                .pageSize(20)
//                // POI召回半径，允许设置区间为0-1000米，超过1000米按1000米召回。默认值为1000
//                .radius(1000));
//    }


    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            //没有找到检索结果
            return;
        } else {
            Log.i("tong","出结果了");
            List<PoiInfo> poiInfoList = reverseGeoCodeResult.getPoiList();
            if (poiInfoList != null
                    && poiInfoList.size() > 0) {
                this.poiInfoList.clear();
                addPoiInfoList(poiInfoList);
            }
        }

    }

    private void addPoiInfoList(List<PoiInfo> poiList){
        poiInfoList.addAll(poiList);
        searchAdapter.notifyDataSetChanged();
//        if (poiInfoList.size() > 0) {
//            PoiInfo poiInfo = poiInfoList.get(0);
//            removeMarker();
//            addMarker(poiInfo.getLocation());
//            movePosition(poiInfo.getLocation());
//        }
    }


    @Override
    protected void onDestroy() {
        if(mPoiSearch!=null){
            mPoiSearch.destroy();
        }
        if(suggestionSearch!=null){
            suggestionSearch.destroy();
        }
        super.onDestroy();
    }
}
