package com.work.doctor.fruits.activity.vip;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.httplib.yigou.bean.WechetPayInfo;
import com.t.httplib.yigou.bean.resp.PayListInfoBean;
import com.t.httplib.yigou.bean.resp.PayOrderInfoBean;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.PayListAdapter;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.RemindDialog;

import java.util.List;

public class MyVipPayListActivity extends DemoMVPActivity<MyVipPayListView,MyVipPayListPresenter>
        implements MyVipPayListView,BaseQuickAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private PayListAdapter payListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vip_czhi);
    }

    @Override
    protected void initView() {
        super.initView();

        titleModule.initTitle("充值中心", true);

        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new GridLayoutManager(context, 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        //初始化Module
        payListAdapter = new PayListAdapter(null);
        recyclerView.setAdapter(payListAdapter);

        getPresenter().getPayListData(1, 20);

    }

    @Override
    protected MyVipPayListPresenter initPersenter() {
        return new MyVipPayListPresenter(this);
    }

    @Override
    public void getCzhiListDataSuccess(List<PayListInfoBean> listData) {
        payListAdapter.setNewData(listData);
    }

    @Override
    public void getPayOrderSuccess(PayOrderInfoBean infoBean) {
        DemoConstant.orderType = 0;
        DemoConstant.zfOrderId = infoBean.getPayNo();
        DemoConstant.czBlance = infoBean.getPayMoney();
        getPresenter().getPayOrderMent(infoBean.getPayNo());
    }

    @Override
    public void getPayOrderMentSuccess(WechetPayInfo infoBean) {
        getPresenter().getWxPayInfo(context,infoBean);
        finish();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        PayListInfoBean infoBean = (PayListInfoBean) adapter.getData().get(position);
        new RemindDialog.Builder(context)
                .setTitle("充值提醒")
                .setMessage("确认要充值" + infoBean.getPayMoney() + "元 到平台吗？")
                .setCancleText("取消")
                .setAffirmText("充值")
                .setCancelable(false)
                .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                    @Override
                    public void onRemindClickAffirm(View view) {
                        getPresenter().getPayOrder(infoBean.getId());
                    }

                    @Override
                    public void onRemindClickCancle(View view) {

                    }
                }).create().show(getSupportFragmentManager(),"dialog_remind_chongzhi");
    }

}
