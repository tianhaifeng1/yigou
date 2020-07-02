package com.work.doctor.fruits.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.httplib.yigou.bean.resp.TkOrderInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.TkOrderAdapter;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.util.List;

public class TkOrderActivity extends DemoMVPActivity<TkOrderView,TkOrderPresenter> implements TkOrderView, TRecyclerViewListenter, BaseQuickAdapter.OnItemChildClickListener {

    private TkOrderAdapter tkOrderAdapter;
    private TRecyclerModule recyclerModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tk_order);
    }

    @Override
    protected TkOrderPresenter initPersenter() {
        return new TkOrderPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("退款", true);

        tkOrderAdapter = new TkOrderAdapter(null);
        recyclerModule = new TRecyclerModule.Builder(context)
                .setLayoutManager(new LinearLayoutManager(context))
                .createAdapter(tkOrderAdapter)
                .setTRecyclerViewListenter(this)
                .setPage(1)
                .setPageSize(20)
                .creat(rootView);
        recyclerModule.setRefreshing(true);
        recyclerModule.setDefImg(R.mipmap.default_orderlist);
        recyclerModule.setDefText("暂无退款订单");
        recyclerModule.getOView().setPadding(0, 0, 0, context.getResources().getDimensionPixelOffset(R.dimen.dp300));

        tkOrderAdapter.setOnItemChildClickListener(this);

        getRecyclerListData();
    }


    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        getPresenter().getTkOrderList(recyclerModule.getPage(), recyclerModule.getPageSize());
    }


    @Override
    public void getTkListDataSuccess(List<TkOrderInfoBean> list) {
        recyclerModule.setRefreshing(false);
        recyclerModule.bindListData(list);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        TkOrderInfoBean infoBean = (TkOrderInfoBean) adapter.getData().get(position);
        int ids = view.getId();
        if(ids == R.id.item_tk_look){
            //退款详情
            int code = 2;
            int status = infoBean.getRefund().getStatus();
            if(status == 2||status == 3){
                code = 3;
            }
            Intent intent = new Intent(context, OrderRefundActivity.class);
            intent.putExtra("info", infoBean.getOrder());
            intent.putExtra("code", code);
            skipActivity(intent);
        } else if (ids == R.id.item_tk_tk) {
            //申请退款
            Intent intent = new Intent(context, OrderRefundActivity.class);
            intent.putExtra("info", infoBean.getOrder());
            intent.putExtra("code", 1);
            startActivityForResult(intent, 102);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==200){
            if(requestCode == 102){
                recyclerModule.setPage(1);
                getRecyclerListData();
            }
        }

    }


}
