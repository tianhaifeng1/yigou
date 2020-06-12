package com.xzte.maplib.baidu;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.trjx.tbase.module.titlemodule.TitleModule;
import com.trjx.tbase.module.xlistviewmodule.TXListViewListenter;
import com.trjx.tbase.module.xlistviewmodule.TXListviewModule;
import com.trjx.tlibs.uils.TUtils;
import com.trjx.tlibs.uils.ToastUtil2;
import com.xzte.maplib.R;
import com.xzte.maplib.city.CitySelectActivity;

import java.util.ArrayList;
import java.util.List;

public class BdMapAddressSelectActivity extends BaseBdMapActivity {

    private TextView mapCity;
    private TextView mapCancle;
    private TextView mapSearchContentText;
    private EditText mapSearchContent;
    private ImageView mapSearchDtz;

    private View include1;
    private RelativeLayout relativeLayout;
    private View include2;
    private TXListviewModule xListviewModule1;
    private TXListviewModule xListviewModule2;


    private PoiSearch mPoiSearch;
    private SuggestionSearch suggestionSearch;

    private int searchType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdmap_address_select);
    }

    @Override
    protected void initView() {
        super.initView();
        TitleModule titleModule = new TitleModule(context, rootView);
        titleModule.setListenter(this);
        titleModule.initTitle("地址选择", true);

        //添加监听大头针的位置信息
        application.applicationAssist.setTMapStatusChangeListener(baiduMap, this);
        application.applicationAssist.startLoction();

        //是否显示Map:默认显示
        boolean isShowMapView = getIntent().getBooleanExtra("isShowMapView", false);
        if(isShowMapView){
            lbmRl.setVisibility(View.VISIBLE);
        }

  //显示定位按钮
        showDwView(true);

        mapCity = findViewById(R.id.map_city);
        mapCancle = findViewById(R.id.map_cancle);
        mapSearchContentText = findViewById(R.id.map_search_content_text);
        mapSearchContent = findViewById(R.id.map_search_content);
        mapSearchDtz = findViewById(R.id.map_search_dtz);

        include1 = findViewById(R.id.map_search_include1);
        relativeLayout = findViewById(R.id.relativelayout);
        include2 = findViewById(R.id.map_search_include2);

        xListviewModule1 = new TXListviewModule(include1, false, true);
        xListviewModule1.setTXListViewListenter(txListViewListenter1);
        xListviewModule1.setDefText("暂无数据");
        xListviewModule1.setPageSize(20);
        xListviewModule1.isShowListView(true);
        xListviewModule1.isShowDefLayout(false);

        xListviewModule2 = new TXListviewModule(include2, false, false);
        xListviewModule2.setTXListViewListenter(txListViewListenter2);
        xListviewModule2.setDefText("暂无数据");
        xListviewModule2.setPageSize(20);
        xListviewModule2.isShowListView(true);
        xListviewModule2.isShowDefLayout(false);

        mapCity.setOnClickListener(v -> {
            //切换城市
            skipActivity(CitySelectActivity.class, 100);
        });

        mapSearchContentText.setOnClickListener(v -> {
            mapSearchContentText.setVisibility(View.GONE);
            mapSearchContent.setVisibility(View.VISIBLE);
            mapCancle.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.VISIBLE);
            mapSearchContent.setFocusable(true);
            TUtils.showSystemKeyboard(context, mapSearchContent);
        });

        mapCancle.setOnClickListener(v -> {
            mapSearchContentText.setVisibility(View.VISIBLE);
            mapSearchContent.setVisibility(View.GONE);
            mapCancle.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);
            mapSearchContent.setText("");
            TUtils.hideSystemKeyboard(context, mapSearchContent);
        });


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
                String searchStr = s.toString().trim();
                if (searchStr.equals("")) {
                    //清空列表：根据需求
                    return;
                }
                suggestionSearch.requestSuggestion(new SuggestionSearchOption()
                        .city(cityName)
                        .citylimit(true)
                        .keyword(searchStr));
            }
        });


    }

    private String cityName = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == 701) {
                if (data != null) {
                    cityName = data.getStringExtra("cityName");
                    mapCity.setText(cityName);
                }
            }
        }
    }

    private List<SuggestionResult.SuggestionInfo> suggestionInfoList;
    private int index;

    private List<TSuggestionInfo> tSuggestionInfoList = new ArrayList<>();

    OnGetSuggestionResultListener listenerSug = suggestionResult -> {
        //处理sug检索结果
        if (suggestionResult != null) {
            suggestionInfoList = suggestionResult.getAllSuggestions();
            index = 0;

            //对查询到的结果进行过滤
            //进行地理编码，得到详细信息

            if (suggestionInfoList == null || suggestionInfoList.size() == 0) {
                ToastUtil2.showToast(context, "没有数据");
                xListviewModule2.error();
                return;
            }

            tSuggestionInfoList.clear();
            searchType = 2;
            getDetialAddress();

//            bindData2_2(suggestionInfoList);

        }
    };


    private void getDetialAddress() {
        if (suggestionInfoList.size() > index) {
            SuggestionResult.SuggestionInfo sug = suggestionInfoList.get(index);
            LatLng latLng1 = sug.getPt();
            if (latLng1 == null) {
                index++;
                getDetialAddress();
                return;
            }
            TSuggestionInfo info = new TSuggestionInfo();
            info.setSuggestionInfo(sug);
            tSuggestionInfoList.add(info);
            geoCoder(sug.getPt(), 1, xListviewModule2.getPageSize());
        } else {
            //过滤完成，添加数据

            bindData2(tSuggestionInfoList);

        }

    }


    private SearchStringAdapter2 searchStringAdapter;
    private List<TSuggestionInfo> tSuggestionInfoListAll = new ArrayList<>();

    private void bindData2(List<TSuggestionInfo> suggestionInfoList) {
        xListviewModule2.bindXListViewData(suggestionInfoList, tSuggestionInfoListAll, () -> {
            if (null == searchStringAdapter) {

                searchStringAdapter = new SearchStringAdapter2(context, tSuggestionInfoListAll);
                xListviewModule2.getxListView().setAdapter(searchStringAdapter);
                xListviewModule2.getxListView().setOnItemClickListener((parent, view, position, id) -> {

//                    ToastUtil.showToast(context, "点击了" + tSuggestionInfoListAll.get(position - 1).getSuggestionInfo().getKey());

                    TSuggestionInfo tSuggestionInfo = tSuggestionInfoListAll.get(position - 1);
                    Intent intent = new Intent();
//                    intent.putExtra("info", poiInfoList1.get(position - 1));
//                    intent.putExtra("cityinfo", reverseGeoCodeResult.getAddressDetail());
//                    intent.putExtra("name", tSuggestionInfo.getSuggestionInfo().getKey());

//                    intent.putExtra("name", tSuggestionInfo.getReverseGeoCodeResult().getAddress());
                    intent.putExtra("name", tSuggestionInfo.getSuggestionInfo().getKey());
                    intent.putExtra("sheng", tSuggestionInfo.getReverseGeoCodeResult().getAddressDetail().province);
                    intent.putExtra("shi", tSuggestionInfo.getReverseGeoCodeResult().getAddressDetail().city);
                    intent.putExtra("qu", tSuggestionInfo.getReverseGeoCodeResult().getAddressDetail().district);
                    intent.putExtra("la", tSuggestionInfo.getReverseGeoCodeResult().getLocation().latitude);
                    intent.putExtra("lo", tSuggestionInfo.getReverseGeoCodeResult().getLocation().longitude);
                    setResult(601, intent);
                    finish();

                });

            } else {
                searchStringAdapter.notifyDataSetChanged();
            }

        });

    }

    private SearchStringAdapter searchStringAdapter2;
    private List<SuggestionResult.SuggestionInfo> suggestionInfoList2 = new ArrayList<>();

    private void bindData2_2(List<SuggestionResult.SuggestionInfo> suggestionInfoList) {
        xListviewModule2.bindXListViewData(suggestionInfoList, suggestionInfoList2, () -> {
            if (null == searchStringAdapter2) {

                searchStringAdapter2 = new SearchStringAdapter(context, suggestionInfoList2);
                xListviewModule2.getxListView().setAdapter(searchStringAdapter2);
                xListviewModule2.getxListView().setOnItemClickListener((parent, view, position, id) -> {
//                    ToastUtil.showToast(context, "点击了" + suggestionInfoList2.get(position - 1).getKey());
                });

            } else {
                searchStringAdapter2.notifyDataSetChanged();
            }

        });

    }


    private TXListViewListenter txListViewListenter1 = new TXListViewListenter() {
        @Override
        public void onClickXListExceptionPageEvent() {

        }

        @Override
        public void getXListData(int page) {
            searchType = 1;
            geoCoder(latLng, page, xListviewModule1.getPageSize());
        }
    };
    private TXListViewListenter txListViewListenter2 = new TXListViewListenter() {
        @Override
        public void onClickXListExceptionPageEvent() {

        }

        @Override
        public void getXListData(int page) {

        }
    };

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

    @Override
    public void getLocationInfo(BDLocation location) {
        super.getLocationInfo(location);
        cityName = location.getCity();
        mapCity.setText(cityName);

        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        xListviewModule1.setPage(1);
        txListViewListenter1.getXListData(1);

    }


    private ReverseGeoCodeResult reverseGeoCodeResult;

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            //没有找到检索结果
            Log.i("tong", "没有找到检索结果");
            return;
        } else {
            this.reverseGeoCodeResult = reverseGeoCodeResult;
            Log.i("tong", "出结果了");
            if (searchType == 1) {
                List<PoiInfo> poiInfoList = reverseGeoCodeResult.getPoiList();
                bindData(poiInfoList);
            } else if (searchType == 2) {
                TSuggestionInfo info = tSuggestionInfoList.get(tSuggestionInfoList.size() - 1);
                info.setReverseGeoCodeResult(reverseGeoCodeResult);
                index++;
                getDetialAddress();
            }
        }

    }

    private List<PoiInfo> poiInfoList1 = new ArrayList<>();
    private SearchAdapter searchAdapter1;

    public void bindData(List<PoiInfo> poiInfoList) {
        xListviewModule1.bindXListViewData(poiInfoList, poiInfoList1, () -> {
            if (null == searchAdapter1) {

                searchAdapter1 = new SearchAdapter(context, poiInfoList1);
                xListviewModule1.getxListView().setAdapter(searchAdapter1);
                xListviewModule1.getxListView().setOnItemClickListener((parent, view, position, id) -> {
//                    ToastUtil.showToast(context, "点击了" + poiInfoList1.get(position - 1).getName());
                    PoiInfo poiInfo = poiInfoList1.get(position - 1);
                    Intent intent = new Intent();
//                    intent.putExtra("info", poiInfoList1.get(position - 1));
//                    intent.putExtra("cityinfo", reverseGeoCodeResult.getAddressDetail());
//                    intent.putExtra("name", poiInfo.address + poiInfo.name);
//                    intent.putExtra("name", poiInfo.address + poiInfo.name);
                    intent.putExtra("name", poiInfo.name);
                    intent.putExtra("sheng", reverseGeoCodeResult.getAddressDetail().province);
                    intent.putExtra("shi", reverseGeoCodeResult.getAddressDetail().city);
                    intent.putExtra("qu", reverseGeoCodeResult.getAddressDetail().district);
                    intent.putExtra("la", poiInfo.location.latitude);
                    intent.putExtra("lo", poiInfo.location.longitude);
                    setResult(601, intent);
                    finish();

                });

            } else {
                searchAdapter1.notifyDataSetChanged();
            }

        });
    }


    private ObjectAnimator objectAnimator;

    private LatLng latLng;

    //获取大头针的位置信息
    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {
        if (mapStatus == null) {
            return;
        }
        latLng = mapStatus.target;

        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(mapSearchDtz, "translationY", 0, -40, -60, 0);
        }
        objectAnimator.setDuration(800);
        objectAnimator.start();

        xListviewModule1.setPage(1);
        txListViewListenter1.getXListData(1);

    }


}
