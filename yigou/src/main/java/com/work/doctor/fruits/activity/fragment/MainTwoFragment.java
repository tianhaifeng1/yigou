package com.work.doctor.fruits.activity.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.t.httplib.yigou.bean.resp.GoodsTypeInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsTypeOutInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.trjx.tlibs.uils.Logger;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.Goods.GoodsListActivity;
import com.work.doctor.fruits.activity.adapter.ShopTypeLeftAdapter;
import com.work.doctor.fruits.activity.adapter.ShopTypeRightTitleAdapter;
import com.work.doctor.fruits.activity.myrecycler.TLinearLayoutManager;
import com.work.doctor.fruits.activity.search.SearchGoodsActivity;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoMVPFragment;

import java.util.ArrayList;
import java.util.List;

public class MainTwoFragment extends DemoMVPFragment<MainTwoView, MainTwoPresenter>
        implements MainTwoView, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, TRecyclerViewListenter {

    private RelativeLayout topSearchRl;

    private RecyclerView recyclerViewLeft,recyclerViewRight;

//    private TRecyclerModule recyclerModule;

    private boolean isFirstIn = false;

    private int showHeight = 0;

    @Override
    protected int initLayout() {
        return R.layout.fragment_main_two;
    }

    @Override
    protected void initFragmentView(View view) {
        topSearchRl = view.findViewById(R.id.main_top_search);
        recyclerViewLeft = view.findViewById(R.id.recyclerview_left);
        recyclerViewRight = view.findViewById(R.id.recyclerview_right);

        topSearchRl.setOnClickListener(this);

        showHeight = getResources().getDisplayMetrics().heightPixels - getResources().getDimensionPixelOffset(R.dimen.ttitle_and_top_h);
        Logger.t("height = " + showHeight);
        initAdapter();


    }

    @Override
    public void initData() {
        super.initData();

        if (DemoConstant.isChangeShop && DemoConstant.refershTwo) {
            DemoConstant.refershTwo = false;
            pdRefershPage();
//            切换店铺重新加载数据
            getPresenter().getShopTypeData();

        } else {
            //刷新
            if (shopTypeAdapter.getData() == null || shopTypeAdapter.getData().size() == 0) {
                    getPresenter().getShopTypeData();
            }
        }
    }

    private ShopTypeRightTitleAdapter typeRightTitleAdapter;

    private ShopTypeLeftAdapter shopTypeAdapter;
    private TLinearLayoutManager leftLlMaager;

    private void initAdapter() {
        leftLlMaager = new TLinearLayoutManager(activity.context);
        recyclerViewLeft.setLayoutManager(leftLlMaager);
        shopTypeAdapter = new ShopTypeLeftAdapter(null);
        //添加条目点击事件
        shopTypeAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (leftIndex != position) {
                List<GoodsTypeInfoBean> infoBeanList = (List<GoodsTypeInfoBean>) adapter.getData();
                infoBeanList.get(leftIndex).setSelect(false);
                infoBeanList.get(position).setSelect(true);
                shopTypeAdapter.notifyItemChanged(leftIndex);
                shopTypeAdapter.notifyItemChanged(position);
                leftIndex = position;
                getRecyclerListData();
            }
        });
        recyclerViewLeft.setAdapter(shopTypeAdapter);

        recyclerViewRight.setLayoutManager(new LinearLayoutManager(activity.context));
        typeRightTitleAdapter = new ShopTypeRightTitleAdapter(null);
//        recyclerModule = new TRecyclerModule.Builder(activity.context)
//                .setLayoutManager(new LinearLayoutManager(activity.context))
//                .createAdapter(typeRightTitleAdapter)
//                .setPage(1)
//                .setPageSize(10000)
//                .setTRecyclerViewListenter(this)
//                .creat(view);
//        recyclerModule.setRefreshing(false);
//        recyclerModule.setSwipeRefreshEnable(false);

        recyclerViewRight.setAdapter(typeRightTitleAdapter);
        typeRightTitleAdapter.setOnItemChildClickListener(this);

    }

    @Override
    protected MainTwoPresenter initPersenter() {
        return new MainTwoPresenter(this);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.main_top_search:
                //搜索
                activity.skipActivity(SearchGoodsActivity.class);
                break;
        }
    }

    private int leftIndex = -1;

    @Override
    public void getShopTypeSuccess(GoodsTypeOutInfoBean infoBean) {

        List<GoodsTypeInfoBean> list = new ArrayList<>();
        List<GoodsTypeInfoBean> paramList1 = infoBean.getParam1();
        List<GoodsTypeInfoBean> paramList2 = infoBean.getParam2();
        if (paramList1 == null && paramList2 == null) {
            return;
        }
        //热门数据
        GoodsTypeInfoBean infoBean1 = new GoodsTypeInfoBean();
        infoBean1.setSelect(true);
        infoBean1.setCnname("热门分类");
        infoBean1.setId(0);

        List<GoodsTypeInfoBean> list2 = new ArrayList<>();
        GoodsTypeInfoBean infoBean2 = new GoodsTypeInfoBean();
        infoBean2.setChildList(paramList1);
        list2.add(infoBean2);

        infoBean1.setChildList(list2);

        list.add(infoBean1);
        //其它数据
        list.addAll(paramList2);

        Logger.t("data = " + new Gson().toJson(list));

        leftIndex = 0;
//        if (list != null && list.size() > 0) {
//            leftIndex = 0;
//            list.get(leftIndex).setSelect(true);// 默认选择第一个
//        }
        Logger.t("list = " + new Gson().toJson(list));
        shopTypeAdapter.setNewData(list);
        shopTypeAdapter.loadMoreEnd(true);

        if (showHeight >= activity.context.getResources().getDimensionPixelOffset(R.dimen.dp50) * list.size()) {
            leftLlMaager.setCanScroll(false);
        }else{
            leftLlMaager.setCanScroll(true);
        }

//        recyclerModule.setPage(1);
        getRecyclerListData();

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        GoodsTypeInfoBean bean = (GoodsTypeInfoBean) adapter.getData().get(position);
//        ToastUtil2.showToast(activity.context, "点击标题或分类：" + bean.getCnname());
        Intent intent = new Intent(activity.context, GoodsListActivity.class);
        intent.putExtra("id", bean.getId());
        activity.skipActivity(intent);

    }
    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        if (leftIndex > -1) {
            GoodsTypeInfoBean infoBean = shopTypeAdapter.getData().get(leftIndex);

            int type = infoBean.getId();
            if (type == 0) {
                typeRightTitleAdapter.setShowTitle(false);
            }else{
                typeRightTitleAdapter.setShowTitle(true);
            }
            Logger.t("listshow = " + new Gson().toJson(infoBean.getChildList()));
            typeRightTitleAdapter.setNewData(infoBean.getChildList());
            typeRightTitleAdapter.loadMoreEnd(true);

        }

    }
}
