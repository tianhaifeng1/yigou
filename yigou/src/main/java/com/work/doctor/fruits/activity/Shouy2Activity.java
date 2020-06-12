package com.work.doctor.fruits.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.SyAdapter;
import com.work.doctor.fruits.activity.myrecycler.MyRecyclerPresenter;
import com.work.doctor.fruits.activity.myrecycler.MyRecyclerView;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.util.List;

/**
 *  收益列表页面
 */
public class Shouy2Activity extends DemoMVPActivity<MyRecyclerView, MyRecyclerPresenter>
        implements TRecyclerViewListenter, MyRecyclerView {

    private TRecyclerModule recyclerModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecycler);
    }

    @Override
    protected void initView() {
        super.initView();

        titleModule.initTitle("我的收益", true);

        //初始化Module
        SyAdapter syAdapter = new SyAdapter(null);
        recyclerModule = new TRecyclerModule.Builder(context)
                .setLayoutManager(new LinearLayoutManager(context))
                .createAdapter(syAdapter)
                .setPageSize(20)
                .setTRecyclerViewListenter(this)
                .creat(rootView);
        recyclerModule.setRefreshing(true);
        recyclerModule.setDefImg(R.mipmap.default_moneylist);
        recyclerModule.setDefText("您还没有收益哦");
        recyclerModule.getOView().setPadding(0, 0, 0, context.getResources().getDimensionPixelOffset(R.dimen.dp300));
        getRecyclerListData();

    }

    @Override
    protected MyRecyclerPresenter initPersenter() {
        return new MyRecyclerPresenter(this);
    }

    //======================  获取不同列表的数据  ====================
    @Override
    public void getListDataSuccess(List<?> list) {
        recyclerModule.setRefreshing(false);
        recyclerModule.bindListData(list);
    }


    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        getPresenter().getListDataShouy(recyclerModule.getPage(), recyclerModule.getPageSize());
    }

}