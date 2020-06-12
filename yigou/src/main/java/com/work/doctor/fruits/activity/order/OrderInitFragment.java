package com.work.doctor.fruits.activity.order;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.httplib.yigou.bean.WechetPayInfo;
import com.t.httplib.yigou.bean.resp.OrderListInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.base.DemoMVPFragment;

import java.util.List;

public abstract class OrderInitFragment<Adapter extends TRecyclerAdapter> extends DemoMVPFragment<OrderListView, OrderListPresenter>
        implements OrderListView, BaseQuickAdapter.OnItemChildClickListener, TRecyclerViewListenter, BaseQuickAdapter.OnItemClickListener  {

    protected TRecyclerModule recyclerModule;

    protected Adapter adapter;

    private OrderListActivity orderListActivity;

    private int tabStatus;

    public OrderInitFragment(int tabStatus) {
        this.tabStatus = tabStatus;
    }

    @Override
    protected int initLayout() {
        return R.layout.layout_recyclerview;
    }

    @Override
    protected void initFragmentView(View view) {

        orderListActivity = (OrderListActivity) getActivity();

        adapter = initAdapter();
//        recyclerAdapter = new OrderListAllAdapter(null);
        recyclerModule = new TRecyclerModule.Builder(activity.context)
                .setLayoutManager(new LinearLayoutManager(activity.context))
                .createAdapter(adapter)
                .setPage(1)
                .setPageSize(10000)
                .setTRecyclerViewListenter(this)
                .creat(view);
        recyclerModule.setRefreshing(true);
        recyclerModule.setDefImg(R.mipmap.default_orderlist);
        recyclerModule.setDefText("您还没有相关的订单");
        recyclerModule.getOView().setPadding(0, 0, 0, 200);

        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);

        //初始化数据
        int pos = -1;
        switch (orderListActivity.index){
            case 0:
                //全部
                pos = -1;
                break;
            case 1:
                pos = 0;
                break;
            case 2:
                pos = 1;
                break;
            case 3:
                pos = 2;
                break;
            case 4:
                pos = 3;
                break;
            case 5:
                pos = 4;
                break;
        }

        if(tabStatus == pos){
            initData();
        }

    }

    protected abstract Adapter initAdapter();

    @Override
    public void initData() {
        super.initData();
//        if (adapter.getData() == null || adapter.getData().size() == 0) {
            //请求获取商品分类的数据
//            if (DemoConstant.shopInfoBean != null) {
            getRecyclerListData();

//            }
//        }
    }

//    //用于检测当前Fragment显示隐藏
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//
//        }
//    }

    @Override
    protected OrderListPresenter initPersenter() {
        return new OrderListPresenter(this);
    }


    @Override
    public void getDataListSuccess(List<OrderListInfoBean> listBean) {
        recyclerModule.setRefreshing(false);
        recyclerModule.bindListData(listBean);
    }

    @Override
    public void getDataOrderCancelSuccess(int position) {
        tRemind("订单取消成功");
        adapter.remove(position);
        if (adapter.getData().size() == 0) {
            recyclerModule.isShowDefLayout(true);
        }
    }

    @Override
    public void getDataOrderDeleteSuccess(int position) {
        tRemind("订单删除成功");
        adapter.remove(position);
        if (adapter.getData().size() == 0) {
            recyclerModule.isShowDefLayout(true);
        }
    }

    @Override
    public void getDataOrderReceiveSuccess(int position) {
        tRemind("确认收货成功");
        adapter.remove(position);
        if (adapter.getData().size() == 0) {
            recyclerModule.isShowDefLayout(true);
        }
    }

    @Override
    public void getPayMentSuccess(WechetPayInfo infoBean, int position) {
    }

    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        getPresenter().getDataOrderList(tabStatus, recyclerModule.getPage(), recyclerModule.getPageSize());
    }

}