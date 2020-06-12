package com.work.doctor.fruits.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.FensAdapter;
import com.work.doctor.fruits.activity.myrecycler.MyRecyclerPresenter;
import com.work.doctor.fruits.activity.myrecycler.MyRecyclerView;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.util.List;


/**
 *  粉丝列表页面
 */
public class FensActivity extends DemoMVPActivity<MyRecyclerView, MyRecyclerPresenter>
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

        titleModule.initTitle("我的粉丝", true);

        //初始化Module
        FensAdapter fensAdapter = new FensAdapter(null);
        recyclerModule = new TRecyclerModule.Builder(context)
                .setLayoutManager(new LinearLayoutManager(context))
                .createAdapter(fensAdapter)
                .setPageSize(24)
                .setTRecyclerViewListenter(this)
                .creat(rootView);
        recyclerModule.setRefreshing(true);
        recyclerModule.setDefImg(R.mipmap.default_fenslist);
        recyclerModule.setDefText("您还没有粉丝哦");
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
        getPresenter().getListDataFens(recyclerModule.getPage(), recyclerModule.getPageSize());
    }

}
