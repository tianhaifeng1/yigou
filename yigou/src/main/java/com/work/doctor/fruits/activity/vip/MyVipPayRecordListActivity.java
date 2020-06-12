package com.work.doctor.fruits.activity.vip;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.CzListAdapter;
import com.work.doctor.fruits.activity.myrecycler.MyRecyclerPresenter;
import com.work.doctor.fruits.activity.myrecycler.MyRecyclerView;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.util.List;


/**
 *  充值记录列表页面
 */
public class MyVipPayRecordListActivity extends DemoMVPActivity<MyRecyclerView, MyRecyclerPresenter>
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

        titleModule.initTitle("充值记录", true);

        //初始化Module
        CzListAdapter czListAdapter = new CzListAdapter(null);
        recyclerModule = new TRecyclerModule.Builder(context)
                .setLayoutManager(new LinearLayoutManager(context))
                .createAdapter(czListAdapter)
                .setPageSize(20)
                .setTRecyclerViewListenter(this)
                .creat(rootView);
        recyclerModule.setRefreshing(true);
        recyclerModule.setDefImg(R.mipmap.default_moneylist);
        recyclerModule.setDefText("暂无数据");
        getRecyclerListData();

    }

    @Override
    protected MyRecyclerPresenter initPersenter() {
        return new MyRecyclerPresenter(this);
    }

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
        getPresenter().getListDataPayRecord(recyclerModule.getPage(), recyclerModule.getPageSize());
    }

}