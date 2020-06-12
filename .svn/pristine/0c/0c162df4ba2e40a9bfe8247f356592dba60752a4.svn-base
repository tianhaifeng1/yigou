package com.xzte.maplib.city;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.t.httplib.yigou.DObserver;
import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.bean.resp.HotCityInfoBean;
import com.trjx.tbase.activity.InitTitleActivity;
import com.trjx.tlibs.bean.PinyinBean;
import com.trjx.tlibs.bean.SidebarInfo;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.TUtils;
import com.trjx.tlibs.views.SidebarView2;
import com.xzte.maplib.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CitySelectActivity extends InitTitleActivity implements BaseQuickAdapter.OnItemChildClickListener {

    private RecyclerView mRecyclerview;
    private SidebarView2 mSiderbarview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_select);
    }

    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("选择城市", true);

        mRecyclerview = findViewById(R.id.recyclerview);
        mSiderbarview = findViewById(R.id.siderbarview);

        linearLayoutManager = new LinearLayoutManager(context);
        mRecyclerview.setLayoutManager(linearLayoutManager);


        new DemoModel().requestHotCityList(new DObserver<List<HotCityInfoBean>>(this) {
            @Override
            public void onTPostSuccess(List<HotCityInfoBean> list) {
                initData(list);
            }
        });

    }

    private void initData(List<HotCityInfoBean> list) {

        try {
            String jsonStr = toConvertString(getAssets().open("city.json"));
            List<TCity> cityList = new Gson().fromJson(jsonStr, new TypeToken<List<TCity>>() {
            }.getType());
            if (cityList != null && cityList.size() > 0) {
                for (int i = 0; i < cityList.size(); i++) {
                    TCity tCity = cityList.get(i);
//                    Logger.t("------" + new Gson().toJson(tCity));
                    PinyinBean pinyinBean = TUtils.toPinyin(tCity.getAreaName());
                    tCity.setPinyinStr(pinyinBean.getPinyinStr());
                    tCity.setFirstChar(pinyinBean.getFirstChar().substring(0, 1).toUpperCase());
                    tCity.setItemType(2);
                }
                Collections.sort(cityList);
                //排序后的集合

                initSidebarData(cityList);

                List<TCity> cityListAll = new ArrayList<>();
                TCity tCity = new TCity();
                tCity.setItemType(1);
                List<String> hotCityList = new ArrayList<>();
                if (list != null && list.size() > 0) {
                    for (int i = 0,size = list.size(); i < size; i++) {
                        hotCityList.add(list.get(i).getCity());
                    }
                }
                tCity.setHotCityList(hotCityList);
                cityListAll.add(tCity);
                cityListAll.addAll(cityList);

                CitySelectAdapter selectAdapter = new CitySelectAdapter(1, cityListAll) {
                    @Override
                    public void onClickHotCity(String hotCityName) {
                        Intent intent = new Intent();
                        intent.putExtra("cityName", hotCityName);
                        setResult(701, intent);
                        finish();
                    }
                };
                mRecyclerview.setAdapter(selectAdapter);
                selectAdapter.setOnItemChildClickListener(this);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加侧边数据
     * @param cityList
     */
    private void initSidebarData(List<TCity> cityList) {
        List<SidebarInfo> infos = new ArrayList<>();
        SidebarInfo sidebarInfoHot = new SidebarInfo();
        sidebarInfoHot.setSidebarName("热门");
        sidebarInfoHot.setSidebarPosition(0);
        infos.add(sidebarInfoHot);
        String firstChar = "";
        for (int i = 0, size = cityList.size(); i < size; i++) {
            TCity tCity = cityList.get(i);
//            Logger.t("------" + new Gson().toJson(tCity));
            if (firstChar.equals("") || !firstChar.equals(tCity.getFirstChar())) {
                firstChar = tCity.getFirstChar();
                Logger.t("------" + tCity.getPinyinStr());
                Logger.t("------" + firstChar);
                SidebarInfo sidebarInfo = new SidebarInfo();
                sidebarInfo.setSidebarName(firstChar);
                sidebarInfo.setSidebarPosition(i + 1);
                infos.add(sidebarInfo);
            }else{
                tCity.setFirstChar("");
            }
        }

        mSiderbarview.setListData(infos);
        mSiderbarview.setOnTouchingLetterChangedListener((s, position) -> {
            linearLayoutManager.scrollToPositionWithOffset(position,0);
            linearLayoutManager.setStackFromEnd(false);
        });
    }


    /**
     * 将一个InputStream流转换成字符串
     *
     * @param is
     * @return
     */
    private String toConvertString(InputStream is) {
        StringBuffer res = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader read = new BufferedReader(isr);
        try {
            String line = read.readLine();
            while (line != null) {
                res.append(line);
                line = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != isr) {
                    isr.close();
                    isr.close();
                }
                if (null != read) {
                    read.close();
                }
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res.toString();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        TCity tCity = (TCity) adapter.getData().get(position);
//        ToastUtil2.showToast(context, tCity.getAreaName());
        Intent intent = new Intent();
        intent.putExtra("cityName", tCity.getAreaName());
        setResult(701, intent);
        finish();

    }
}
