package com.work.doctor.fruits.activity.vip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.t.httplib.yigou.bean.resp.UserInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.MVipUseListAdapter;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.base.DemoWebActivity;

import java.util.List;

public class MVipActivity extends DemoMVPActivity<MVipView, MVipPresenter>
        implements MVipView, View.OnClickListener, TRecyclerViewListenter {


    private TextView mVipGuize;
    private TextView mVipJilu;
    private TextView mVipYue;
    private TextView mVipChongzhi;

    private TRecyclerModule recyclerModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvip);
    }

    @Override
    protected void initView() {
        super.initView();

        titleModule.initTitle("会员中心", true);

        mVipGuize = findViewById(R.id.vip_guize);
        mVipJilu = findViewById(R.id.vip_jilu);
        mVipYue = findViewById(R.id.vip_yue);
        mVipChongzhi = findViewById(R.id.vip_chongzhi);

        mVipGuize.setOnClickListener(this);
        mVipJilu.setOnClickListener(this);
        mVipChongzhi.setOnClickListener(this);

        recyclerModule = new TRecyclerModule.Builder(context)
                .setLayoutManager(new LinearLayoutManager(context))
                .createAdapter(new MVipUseListAdapter(null))
                .setTRecyclerViewListenter(this)
                .creat(rootView);
        recyclerModule.setRefreshing(true);
        recyclerModule.setDefImg(R.mipmap.default_moneylist);
        recyclerModule.setDefText("您还没有余额记录哦");
        getRecyclerListData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().getUserInfo();
    }

    @Override
    protected MVipPresenter initPersenter() {
        return new MVipPresenter(this);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.vip_guize:
                Intent intent = new Intent(context, DemoWebActivity.class);
                intent.putExtra("code", 1);
                skipActivity(intent);
                break;
            case R.id.vip_jilu:
                skipActivity(MyVipPayRecordListActivity.class);
                break;
            case R.id.vip_chongzhi:
                skipActivity(MyVipPayListActivity.class, 100);
                break;
        }

    }

    @Override
    public void getUseListDataSuccess(List<?> list) {
        recyclerModule.setRefreshing(false);
        recyclerModule.bindListData(list, false);
    }

    @Override
    public void getUserInfoSuccess(UserInfoBean userInfoBean) {
        DemoConstant.balance = userInfoBean.getBalance();
        DemoConstant.userStatus = userInfoBean.getStatus();
        mVipYue.setText(DemoConstant.balance + " 元");
    }

    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        getPresenter().getListDataPurchaseRecord(recyclerModule.getPage(), recyclerModule.getPageSize());
    }
}
