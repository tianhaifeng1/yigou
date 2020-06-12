package com.work.doctor.fruits.activity.coupon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.tabs.TabLayout;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.MainNavActivity;
import com.work.doctor.fruits.activity.adapter.CouponAdapter;
import com.work.doctor.fruits.activity.myrecycler.MyRecyclerView;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.util.ArrayList;
import java.util.List;

public class CouponActivity extends DemoMVPActivity<MyRecyclerView, CouponPresenter>
        implements MyRecyclerView,BaseQuickAdapter.OnItemChildClickListener, TRecyclerViewListenter {

    private TabLayout tabLayout;
    private LinearLayout linearLayout;

    private int status = 0;

    private TRecyclerModule recyclerModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
    }


    private CouponAdapter couponAdapter;
    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("我的优惠券", true);

        tabLayout = findViewById(R.id.yhj_tablayout);
        linearLayout = findViewById(R.id.yhj_center);

        couponAdapter = new CouponAdapter(null);
        recyclerModule = new TRecyclerModule.Builder(context)
                .setLayoutManager(new LinearLayoutManager(context))
                .createAdapter(couponAdapter)
                .setTRecyclerViewListenter(this)
                .creat(rootView);
        recyclerModule.setRefreshing(true);
        recyclerModule.setDefImg(R.mipmap.default_couponlist);
        recyclerModule.setDefText("没有优惠券数据");
        recyclerModule.getOView().setPadding(0, 0, 0, context.getResources().getDimensionPixelOffset(R.dimen.dp200));
        couponAdapter.setOnItemChildClickListener(this);
        getTabTitle();

//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < titleStr.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titleStr.get(i)).setTag(i));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index = tab.getPosition();
                if(index == 0){
                    status = 0;
                }else if(index == 1){
                    status = 1;
                }else if(index == 2){
                    status = 2;
                }
                couponAdapter.setState(status);
                recyclerModule.setRefreshing(true);
                recyclerModule.setPage(1);
                getRecyclerListData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        linearLayout.setOnClickListener(v->skipActivity(CouponCenterActivity.class,100));

        getRecyclerListData();

    }

    private List<String> titleStr;
    private void getTabTitle() {
        titleStr = new ArrayList<>();
        titleStr.add("未使用");
        titleStr.add("已使用");
        titleStr.add("已过期");
    }

    @Override
    protected CouponPresenter initPersenter() {
        return new CouponPresenter(this);
    }

    @Override
    public void getListDataSuccess(List<?> list) {
        recyclerModule.setRefreshing(false);
        recyclerModule.bindListData(list);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        int ids = view.getId();
        if(ids == R.id.item_coupon_btn){
            Intent intent_home = new Intent(context, MainNavActivity.class);
            intent_home.putExtra("position", 1);
            startActivity(intent_home);
        }
    }

    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        getPresenter().getListDataCoupon(recyclerModule.getPage(),recyclerModule.getPageSize(),status);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            if(resultCode == 200){
                if(status == 0){
//                    刷新未使用列表
                    getRecyclerListData();
                }
            }
        }
    }
}
