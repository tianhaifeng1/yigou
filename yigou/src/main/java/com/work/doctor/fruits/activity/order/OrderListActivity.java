package com.work.doctor.fruits.activity.order;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.trjx.tbase.activity.InitTitleActivity;
import com.trjx.tbase.adapter.TViewPagerStateAdapter;
import com.trjx.tbase.fragment.TFragment;
import com.trjx.tlibs.uils.Logger;
import com.work.doctor.fruits.R;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends InitTitleActivity {

    private TabLayout mTablayout;
    private ViewPager mViewpager;

    private List<TFragment> fragments;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("订单中心",true);

        mTablayout = findViewById(R.id.tablayout);
        mViewpager = findViewById(R.id.viewpager);
        mViewpager.setOffscreenPageLimit(5);

        index = getIntent().getIntExtra("index", 0);

        initFragmentViewData();
    }

    private void initFragmentViewData(){
        List<String> stringList = getTabTitle();
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < stringList.size(); i++) {
            mTablayout.addTab(mTablayout.newTab().setText(stringList.get(i)));
        }

        fragments = new ArrayList<>();
//        0：待支付；1：待发货；2：待收货；3：待评价；4：已完成；5：已取消；6：退款中；7：已退款；
        OrderAllFragment allFragment = new OrderAllFragment(-1);
        fragments.add(allFragment);
        OrderAllFragment allFragment2 = new OrderAllFragment(0);
        fragments.add(allFragment2);
        OrderAllFragment allFragment3 = new OrderAllFragment(1);
        fragments.add(allFragment3);
        OrderAllFragment allFragment4 = new OrderAllFragment(2);
        fragments.add(allFragment4);
        OrderAllFragment allFragment5 = new OrderAllFragment(3);
        fragments.add(allFragment5);
//        OrderAllFragment allFragment6 = new OrderAllFragment(4);
//        fragments.add(allFragment6);

        TViewPagerStateAdapter<TFragment> adapter = new TViewPagerStateAdapter(getSupportFragmentManager(), fragments, stringList);
        mViewpager.setAdapter(adapter);
        mTablayout.setupWithViewPager(mViewpager);
        mViewpager.setCurrentItem(index,false);
        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Logger.t("=============tab : " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(index !=position){
                    index = position;
                    fragments.get(index).initData();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private List<String> getTabTitle() {
        List<String> stringList = new ArrayList<>();
        stringList.add("全部");
        stringList.add("待支付");
        stringList.add("待发货");
        stringList.add("待收货");
        stringList.add("待评价");
        return stringList;
    }


}