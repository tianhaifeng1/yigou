package com.work.rulong.delivery.base;

import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.trjx.tbase.activity.InitActivity;
import com.trjx.tbase.adapter.TViewPagerStateAdapter;
import com.trjx.tbase.fragment.TFragment;
import com.trjx.tbase.module.titlemodule.TitleListenter;
import com.trjx.tbase.module.titlemodule.TitleModule;
import com.work.rulong.delivery.R;

import java.util.List;

public abstract class DemoTabViewPagerActivity extends InitActivity implements TitleListenter {

    protected TitleModule titleModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_demo_tab_view_pager);
        initWork();
        initView();
    }

    @Override
    protected void initView() {
        super.initView();

        titleModule = new TitleModule(context, rootView);
        titleModule.setListenter(this);


    }

    protected TabLayout tabLayout;
    protected ViewPager viewPager;

    protected int mode = TabLayout.MODE_FIXED;

    private List<TFragment> fragments;
    private int index = 0;


    protected void initView2(){
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        List<String> titleList = getTabTitle();

        tabLayout.setTabMode(mode);

        for (int i = 0; i < titleList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titleList.get(i)).setTag(i));
        }

        viewPager.setOffscreenPageLimit(titleList.size());

        fragments = initFragments();
        TViewPagerStateAdapter adapter = new TViewPagerStateAdapter(getSupportFragmentManager(), fragments, titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                index = tab.getPosition();
                viewPager.setCurrentItem(index);
                fragments.get(index).initData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position != index){
                    index = position;
                    fragments.get(index).initData();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    protected abstract List<TFragment> initFragments();

    protected abstract List<String> getTabTitle();

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


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 100){
//            if (resultCode == 200) {
//                index = data.getIntExtra("index", 0);
//                viewPager.setCurrentItem(index);
//                fragments.get(index).initData();
//            }
//        }
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        if (intent != null) {
//            index = intent.getIntExtra("index", 0);
//            viewPager.setCurrentItem(index);
//            fragments.get(index).initData();
//        }
//    }
}
