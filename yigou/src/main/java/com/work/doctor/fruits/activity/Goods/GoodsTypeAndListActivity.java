package com.work.doctor.fruits.activity.Goods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.bean.resp.GoodsTypeInfoBean;
import com.trjx.tbase.adapter.TViewPagerStateAdapter;
import com.trjx.tbase.fragment.TFragment;
import com.trjx.tbase.mvp.TPresenter;
import com.trjx.tbase.mvp.TView;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.MainNavActivity;
import com.work.doctor.fruits.activity.search.SearchGoodsActivity;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.util.ArrayList;
import java.util.List;

public class GoodsTypeAndListActivity extends DemoMVPActivity<TView, TPresenter<TView, DemoModel>> {

    private ImageView mTitleBack;
    private RelativeLayout mTitleSearch;
    private TabLayout mGtalTablayout;
    private ViewPager mGtalViewpager;
    private ImageView titleGwc;

    public static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_type_and_list);
    }

    @Override
    protected TPresenter initPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();

        mTitleBack = findViewById(R.id.title_back);
        mTitleSearch = findViewById(R.id.title_search);
        mGtalTablayout = findViewById(R.id.gtal_tablayout);
        mGtalViewpager = findViewById(R.id.gtal_viewpager);
        titleGwc =findViewById(R.id.title_gwc);

        //返回
        mTitleBack.setOnClickListener(v -> finish());
        //搜索
        mTitleSearch.setOnClickListener(v -> skipActivity(SearchGoodsActivity.class));
        //购物车跳转
        titleGwc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_shopcart = new Intent(context, MainNavActivity.class);
                intent_shopcart.putExtra("position", 2);
                startActivity(intent_shopcart);
            }
        });

        Intent intent = getIntent();
        List<GoodsTypeInfoBean> infoBeanArrayList = (List<GoodsTypeInfoBean>) intent.getSerializableExtra("list");
        index = intent.getIntExtra("index", 0);

        mGtalTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        List<GoodsTypeFragment> fragments = new ArrayList<>();
        List<String> titlelist = new ArrayList<>();
        if (infoBeanArrayList != null && infoBeanArrayList.size() > 0) {
            //初始化Tab数据
            for (int i = 0, size = infoBeanArrayList.size(); i < size; i++) {
                GoodsTypeInfoBean goodsTypeInfoBean = infoBeanArrayList.get(i);
                titlelist.add(goodsTypeInfoBean.getCnname());
                fragments.add(new GoodsTypeFragment(i,goodsTypeInfoBean.getId(), goodsTypeInfoBean.getChildList()));
                mGtalTablayout.addTab(mGtalTablayout.newTab().setText(goodsTypeInfoBean.getCnname()));
            }

            mGtalViewpager.setOffscreenPageLimit(fragments.size());
        }

        TViewPagerStateAdapter<TFragment> adapter = new TViewPagerStateAdapter(getSupportFragmentManager(), fragments, titlelist);
        mGtalViewpager.setAdapter(adapter);
        mGtalTablayout.setupWithViewPager(mGtalViewpager);
        mGtalViewpager.setCurrentItem(index,false);
        mGtalTablayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                fragments.get(position).initData();
                mGtalViewpager.setCurrentItem(position,false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                fragments.get(position).initData();
            }
        });


    }

}
