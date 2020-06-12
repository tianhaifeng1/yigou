package com.work.doctor.fruits.activity.address;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.resp.AddressInfoBean;
import com.trjx.tlibs.uils.Logger;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.AddressAdapter2;
import com.work.doctor.fruits.activity.adapter.AddressFjAdapter;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.xzte.maplib.baidu.SearchAddressActivity;
import com.xzte.maplib.baidu.TMapInfoListener;
import com.xzte.maplib.city.CitySelectActivity;

import java.util.List;

public class SelectSHAddressActivity extends DemoMVPActivity<SelectSHAddressView, SelectSHAddressPresenter>
        implements SelectSHAddressView, TMapInfoListener, View.OnClickListener, OnGetGeoCoderResultListener {

    private DemoApplication demoApplication;

    private TextView mShaddressSearch;
    private TextView mShaddressSearchCitytext;
    private LinearLayout mShaddressRemindLl;
    private TextView mShaddressRemind;
    private ImageView mShaddressRemindDelete;
    private TextView mShaddressDwtext;
    private TextView mShaddressDwbtn;
    private RecyclerView mShaddressRecyclerview;
    private TextView mShaddressAdd;
    private RecyclerView mShaddressFjRecyclerview;

    private RelativeLayout shaddressRl;
    private RelativeLayout shaddressRl2;
    private TextView shaddressRl2Login;

    private AddressAdapter2 adapter2;
    private AddressFjAdapter fjAdapter;

    private String cityName = "拉萨市";//默认城市

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_shaddress);
    }

    @Override
    protected SelectSHAddressPresenter initPersenter() {
        return new SelectSHAddressPresenter(this);
    }

    private int fjPage = 1;
    private int pageSize = 20;

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("选择收货地址",true);

        mShaddressSearch = findViewById(R.id.shaddress_search);
        mShaddressSearchCitytext = findViewById(R.id.shaddress_search_citytext);
        mShaddressRemindLl = findViewById(R.id.shaddress_remind_ll);
        mShaddressRemind = findViewById(R.id.shaddress_remind);
        mShaddressRemindDelete = findViewById(R.id.shaddress_remind_delete);
        mShaddressDwtext = findViewById(R.id.shaddress_dwtext);
        mShaddressDwbtn = findViewById(R.id.shaddress_dwbtn);
        mShaddressRecyclerview = findViewById(R.id.shaddress_recyclerview);
        mShaddressAdd = findViewById(R.id.shaddress_add);
        mShaddressFjRecyclerview = findViewById(R.id.shaddress_fj_recyclerview);

        shaddressRl = findViewById(R.id.shaddress_rl);
        shaddressRl2 = findViewById(R.id.shaddress_rl2);
        shaddressRl2Login = findViewById(R.id.shaddress_login);

        mShaddressDwtext.setOnClickListener(this);
        mShaddressRemindDelete.setOnClickListener(this);
        mShaddressSearch.setOnClickListener(this);
        mShaddressSearchCitytext.setOnClickListener(this);
        mShaddressDwbtn.setOnClickListener(this);
        mShaddressAdd.setOnClickListener(this);
        shaddressRl2Login.setOnClickListener(this);


        mShaddressRecyclerview.setLayoutManager(new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        adapter2 = new AddressAdapter2(null);
        mShaddressRecyclerview.setAdapter(adapter2);
        adapter2.setOnItemClickListener((adapter, view, position) -> {
            AddressInfoBean infoBean = (AddressInfoBean) adapter.getData().get(position);
            DemoConstant.addressInfoBean = infoBean;
            Intent intent = new Intent();
            intent.putExtra("name", infoBean.getAddress());
            intent.putExtra("sheng", infoBean.getProvince());
            intent.putExtra("shi", infoBean.getCity());
            intent.putExtra("qu", infoBean.getCounty());
            intent.putExtra("la", infoBean.getLatitude());
            intent.putExtra("lo", infoBean.getLongitude());
            setResult(601, intent);
            finish();
        });
//        adapter2.setOnItemChildClickListener((adapter, view, position) -> {
//            List<AddressInfoBean> infoBeanList = adapter.getData();
//            if (infoBeanList != null && infoBeanList.size() > 0) {
//                for (int i = 0; i < infoBeanList.size(); i++) {
//                    if (i == position) {
//                        infoBeanList.get(i).setSelect(true);
//                    }else{
//                        infoBeanList.get(i).setSelect(false);
//                    }
//                }
//                adapter.notifyDataSetChanged();
//            }
//        });

        mShaddressFjRecyclerview.setLayoutManager(new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        fjAdapter = new AddressFjAdapter(null);
        mShaddressFjRecyclerview.setAdapter(fjAdapter);
        //加载更多
        fjAdapter.setOnLoadMoreListener(() -> {
            fjPage++;
            geoCoder();
        }, mShaddressFjRecyclerview);
        fjAdapter.setOnItemClickListener((adapter, view, position) -> {
            PoiInfo poiInfo = (PoiInfo) adapter.getData().get(position);
            DemoConstant.addressInfoBean = null;
            Intent intent = new Intent();
            intent.putExtra("name", poiInfo.getName());
            intent.putExtra("sheng", province);
            intent.putExtra("shi", city);
            intent.putExtra("qu", district);
            intent.putExtra("la", poiInfo.getLocation().latitude);
            intent.putExtra("lo", poiInfo.getLocation().longitude);
            setResult(601, intent);
            finish();
        });

//
        demoApplication = (DemoApplication) getApplication();
        demoApplication.applicationAssist.setTMapInfoListener(this);
        demoApplication.applicationAssist.setScanSpan(1);//手动定位
        demoApplication.applicationAssist.startLoction();


        if (YigouConstant.token == null || YigouConstant.token.equals("")) {
            shaddressRl.setVisibility(View.GONE);
            shaddressRl2.setVisibility(View.VISIBLE);
        }else{
            shaddressRl.setVisibility(View.VISIBLE);
            shaddressRl2.setVisibility(View.GONE);
//        地址列表
            if (DemoConstant.shopInfoBean != null) {
                getPresenter().getListDataAddress(DemoConstant.shopInfoBean.getShopId()+"", 1, 1000);
            }
        }

    }


    private BDLocation aMapLocation;

    @Override
    public void bdSuccess(BDLocation aMapLocation) {
        Logger.t("定位成功" + aMapLocation.getCity());
        hideDialog();
        this.aMapLocation = aMapLocation;
        String addressStr = aMapLocation.getAddrStr();
        if (addressStr != null && !addressStr.equals("")) {
            if(addressStr.contains("区")){
                addressStr = addressStr.substring(addressStr.indexOf("区") + 1);
            }
        }
        mShaddressDwtext.setText(addressStr);
        cityName = aMapLocation.getCity();
        mShaddressSearchCitytext.setText(cityName);
        geoCoder();

    }

    @Override
    public void bdFail(String failStr) {
        mShaddressDwtext.setText("无法获取定位，请刷新重试");
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids){
            case R.id.shaddress_dwtext:
                //选择定位地址
                if(aMapLocation!=null){
                    DemoConstant.addressInfoBean = null;
                    Intent intentWd = new Intent();
                    intentWd.putExtra("name", aMapLocation.getAddrStr());
                    intentWd.putExtra("sheng", aMapLocation.getProvince());
                    intentWd.putExtra("shi", aMapLocation.getCity());
                    intentWd.putExtra("qu", aMapLocation.getDistrict());
                    intentWd.putExtra("la", aMapLocation.getLatitude());
                    intentWd.putExtra("lo", aMapLocation.getLongitude());
                    setResult(601, intentWd);
                    finish();
                }
                break;
            case R.id.shaddress_remind_delete:
                //隐藏
                mShaddressRemindLl.setVisibility(View.GONE);
                break;
            case R.id.shaddress_search:
                //搜索地址
                Intent intentA = new Intent(context, SearchAddressActivity.class);
                intentA.putExtra("isShowMapView", false);
                intentA.putExtra("title", "选择收货地址");
                intentA.putExtra("cityName", cityName);
                skipActivity(intentA, 100);
                break;
            case R.id.shaddress_search_citytext:
                //切换城市
                skipActivity(CitySelectActivity.class,100);
                break;
            case R.id.shaddress_dwbtn:
                showDialog("重新定位");
                demoApplication.applicationAssist.startLoction();
                break;
            case R.id.shaddress_add:
                Logger.t("地址 ： 新增");
                Intent intent = new Intent(context, AddressUpdateActivity.class);
                intent.putExtra("code", 0);
                skipActivity(intent, 100);
                break;
            case R.id.shaddress_login:
                Logger.t("地址 ： 登陆");

                relogin();

                break;
        }
    }


    private GeoCoder mCoder;

    //逆地理编码（即坐标转地址）
    public void geoCoder() {
        if (mCoder == null) {
            mCoder = GeoCoder.newInstance();
            mCoder.setOnGetGeoCodeResultListener(this);
        }
        mCoder.reverseGeoCode(new ReverseGeoCodeOption()
                .location(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()))
                .pageNum(fjPage)
                .pageSize(pageSize)
                // POI召回半径，允许设置区间为0-1000米，超过1000米按1000米召回。默认值为1000
                .radius(1000));
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
    }

    private String province = "";
    private String city = "";
    private String district = "";

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            //没有找到检索结果
            Log.i("tong", "没有找到检索结果");
            return;
        } else {
//            this.reverseGeoCodeResult = reverseGeoCodeResult;
//            Log.i("tong", "出结果了"+new Gson().toJson(reverseGeoCodeResult));

            province = reverseGeoCodeResult.getAddressDetail().province;
            city = reverseGeoCodeResult.getAddressDetail().city;
            district = reverseGeoCodeResult.getAddressDetail().district;

            List<PoiInfo> poiInfoList = reverseGeoCodeResult.getPoiList();
            if (poiInfoList != null && poiInfoList.size() > 0) {
//                Log.i("tong", "出结果了"+new Gson().toJson(poiInfoList));
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mShaddressFjRecyclerview.getLayoutParams();
                layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
                layoutParams.height = context.getResources().getDimensionPixelOffset(R.dimen.dp40) * poiInfoList.size();
                mShaddressFjRecyclerview.setLayoutParams(layoutParams);

                fjAdapter.setNewData(poiInfoList);
                if (poiInfoList.size() < 20) {
                    fjAdapter.loadMoreEnd(false);
                } else {
                    fjAdapter.loadMoreComplete();
                }
            }
        }
    }

    @Override
    public void getListDataSuccess(List<AddressInfoBean> list) {
        //设置默认选中项
        if (DemoConstant.addressInfoBean != null) {
            if (list != null && list.size() > 0) {
                e:
                for (int i = 0; i < list.size(); i++) {
                    AddressInfoBean infoBean = list.get(i);
                    if (DemoConstant.addressInfoBean.getId().equals(infoBean.getId())) {
                        infoBean.setSelect(true);
                        break e;
                    }
                }
            }
        }
        adapter2.setNewData(list);
        adapter2.loadMoreEnd(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == 200) {
                //刷新数据
                if (DemoConstant.shopInfoBean != null) {
                    getPresenter().getListDataAddress(DemoConstant.shopInfoBean.getShopId()+"",1, 1000);
                }
            }else if (resultCode == 601) {
                DemoConstant.addressInfoBean = null;
                setResult(resultCode,data);
                finish();
            }else if(resultCode == 701){
                if (data != null) {
                    cityName = data.getStringExtra("cityName");
                    mShaddressSearchCitytext.setText(cityName);
                }
            }
        }
    }

}
