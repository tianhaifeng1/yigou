package com.work.doctor.fruits.activity.Goods;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.GreenDaoAssist;
import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsListInfo;
import com.t.httplib.yigou.bean.resp.BrandInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsTypeInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.SnackbarUtil;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.GoodsTypeAdapter;
import com.work.doctor.fruits.activity.adapter.SearchGoodsAdapter;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.base.DemoMVPFragment;
import com.work.doctor.fruits.dialog.MoreGoodsSpecInfoDialog2;

import java.util.ArrayList;
import java.util.List;

public class GoodsTypeFragment extends DemoMVPFragment<GoodsListView, GoodsListPresenter>
        implements TRecyclerViewListenter,
        BaseQuickAdapter.OnItemChildClickListener,
        BaseQuickAdapter.OnItemClickListener,
        GoodsListView,
        MoreGoodsSpecInfoDialog2.OnMoreGoodsListSpecInfoListener {

    private RecyclerView mFgtRecyclerview;

    private GoodsTypeAdapter typeAdapter;
    private LinearLayoutManager layoutManager;
    private List<GoodsTypeInfoBean> beanList;
    private int position;
    private int initRelatedCatids;

    private TRecyclerModule recyclerModule;
    private LinearLayoutManager rmLayoutManager;

    private GreenDaoAssist greenDaoAssist;

    public GoodsTypeFragment(int position,int relatedCatids, List<GoodsTypeInfoBean> beanList) {
        this.position = position;
        this.beanList = beanList;
        this.initRelatedCatids = relatedCatids;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_goods_type;
    }

    @Override
    protected void initFragmentView(View view) {

        mFgtRecyclerview = view.findViewById(R.id.fgt_recyclerview);
        Logger.t("GoodsTypeFragment = 初始化了");

        listInfo.setBrandId(0);
        listInfo.setWhole(0);
        listInfo.setSell(0);
        listInfo.setPrice(0);
        listInfo.setShopId(DemoConstant.shopInfoBean.getShopId());
        listInfo.setPageSize(30);

        greenDaoAssist = new GreenDaoAssist(((DemoApplication)activity.getApplication()).databaseAssist);

        typeAdapter = new GoodsTypeAdapter(beanList);
        layoutManager = new LinearLayoutManager(activity.context);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mFgtRecyclerview.setLayoutManager(layoutManager);
        mFgtRecyclerview.setAdapter(typeAdapter);

        typeAdapter.setOnItemClickListener((adapter, view1, position) -> initTypeState(position));

        SearchGoodsAdapter goodsAdapter = new SearchGoodsAdapter(null);
        rmLayoutManager = new LinearLayoutManager(activity.context);
        recyclerModule = new TRecyclerModule.Builder(activity.context)
                .setLayoutManager(rmLayoutManager)
                .setPage(1)
                .setPageSize(30)
                .setTRecyclerViewListenter(this)
                .createAdapter(goodsAdapter)
                .creat(rootView);
        recyclerModule.setRefreshing(true);
        recyclerModule.setDefImg(R.mipmap.default_goodslist);
        recyclerModule.setDefText("暂无商品");
        recyclerModule.getOView().setPadding(0, 0, 0, 200);
        goodsAdapter.setOnItemChildClickListener(this);
        goodsAdapter.setOnItemClickListener(this);

        if (GoodsTypeAndListActivity.index == position) {
            initData();
        }

    }

    private ReqGoodsListInfo listInfo = new ReqGoodsListInfo();

    @Override
    public void initData() {
        super.initData();
        //初始化处理
        initTypeState(-1);
    }

    private void initTypeState(int index) {
        //避免后台数据有误造成的异常
        try {
//            if (beanList != null ) {
            int size = beanList.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    GoodsTypeInfoBean infoBean = beanList.get(i);
                    if (i == index) {
                        infoBean.setSelect(true);
                    } else {
                        infoBean.setSelect(false);
                    }
                }
                if (index <= 0) {
                    layoutManager.scrollToPosition(0);
                }
                typeAdapter.notifyDataSetChanged();
            }
            if(index == -1){
                listInfo.setRelatedCatids(initRelatedCatids);
            }else{
                listInfo.setRelatedCatids(beanList.get(index).getId());
            }
            listInfo.setPage(1);
            recyclerModule.setPage(1);
            getRecyclerListData();
//            }
        } catch (Exception e) {
            e.printStackTrace();
            recyclerModule.setRefreshing(false);
            SnackbarUtil.showToast(rootView,"数据异常");
        }

    }


    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        getPresenter().getGoodsListData(listInfo);
    }

    private GoodsInfoBean goodsInfoBean;
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        int ids = view.getId();
        switch (ids){
            case R.id.item_shop_cart:
                goodsInfoBean = (GoodsInfoBean) adapter.getData().get(position);
                getPresenter().getGoodsItem(goodsInfoBean.getId());
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        GoodsInfoBean shopInfoBean = (GoodsInfoBean) adapter.getData().get(position);
        int id = shopInfoBean.getId();
        Intent intent = new Intent(activity.context, GoodsDetial2Activity.class);
        intent.putExtra("id", id);
        activity.skipActivity(intent);
    }

    @Override
    protected GoodsListPresenter initPersenter() {
        return new GoodsListPresenter(this);
    }

    @Override
    public void getBrandListDataSuccess(List<BrandInfoBean> list) {

    }

    @Override
    public void getListDataSuccess(List<?> list) {
        recyclerModule.setRefreshing(false);
        recyclerModule.bindListData(list);
        if (recyclerModule.getPage() == 1 && list != null && list.size() > 0) {
            rmLayoutManager.scrollToPosition(0);
        }
    }

    @Override
    public void getGoodsSpecSuccess(GoodsSpecInfoBean infoBean) {
        if (infoBean != null && infoBean.getGoodsItemList() != null && infoBean.getGoodsItemList().size() > 0) {
            // 有规格
            goodsItemDialog(infoBean);
        } else {

            if (goodsInfoBean.getStock() <= 0) {

                SnackbarUtil.showToast(rootView, "暂无库存");
                return;
            }

            //没有规格
            //直接添加到购物车
            DatabaseGoodsInfo goodsInfo = new DatabaseGoodsInfo();
//        商品信息
            goodsInfo.setGoodsId(goodsInfoBean.getId()+"");
            goodsInfo.setGoodsName(goodsInfoBean.getGoodsName());
            goodsInfo.setGoodsPrice(goodsInfoBean.getSellPriceDiscount());
            goodsInfo.setGoodsNumber(1);
            goodsInfo.setGoodsUrl(goodsInfoBean.getGoodsImage());
            goodsInfo.setGoodsTotal(goodsInfoBean.getStock());
            //规格
            goodsInfo.setSpecId("");
            goodsInfo.setSpecName("");
//        店铺信息
            goodsInfo.setShopId(goodsInfoBean.getShopId() + "");
            greenDaoAssist.insertGoods(DemoConstant.shopInfoBean.getShopId()+"", goodsInfo, isChange -> DemoConstant.isChangeDatabase = isChange);

            ReqCartAddInfo addInfo = new ReqCartAddInfo();
            ReqCartAddInfo.ReqCartAddInfoBean infoBean2 = new ReqCartAddInfo.ReqCartAddInfoBean();
            infoBean2.setGoodsId(goodsInfo.getGoodsId());
            infoBean2.setAttrStrId(goodsInfo.getSpecId());
            infoBean2.setTotalNum(goodsInfo.getGoodsNumber());
            List<ReqCartAddInfo.ReqCartAddInfoBean> goods = new ArrayList<>();
            goods.add(infoBean2);
            addInfo.setGoods(goods);
            if(YigouConstant.token.equals("")){
                SnackbarUtil.showToast(rootView,"添加成功");
            }else{
                getPresenter().addGoodsToShoppingCart(addInfo);
            }
        }
    }

    @Override
    public void addGoodsToShoppingCartSuccess() {
        SnackbarUtil.showToast(rootView,"添加成功");
    }


    //弹框
    private void goodsItemDialog(GoodsSpecInfoBean specListBeanList) {
        MoreGoodsSpecInfoDialog2 infoDialog = new MoreGoodsSpecInfoDialog2.Builder(activity.context)
                .setCancelable(false)
                .setGoodsInfo(goodsInfoBean)
                .setGoodsItemList(specListBeanList)
                .setOnMoreGoodsListSpecInfoListener(this)
                .create();
        infoDialog.show(activity.getSupportFragmentManager(), "tishi_itemspec");
    }

    @Override
    public void onOnMoreGoodsListAddcartClick(View view, String specIds, String specName, float goodsPrice, int number) {
        DatabaseGoodsInfo goodsInfo = new DatabaseGoodsInfo();
//        商品信息
        goodsInfo.setGoodsId(goodsInfoBean.getId()+"");
        goodsInfo.setGoodsName(goodsInfoBean.getGoodsName());
        goodsInfo.setGoodsPrice(goodsPrice);
        goodsInfo.setGoodsNumber(number);
        goodsInfo.setGoodsUrl(goodsInfoBean.getGoodsImage());
        goodsInfo.setGoodsTotal(goodsInfoBean.getStock());
        //规格
        goodsInfo.setSpecId(specIds);
        goodsInfo.setSpecName(specName);
//        店铺信息
        goodsInfo.setShopId(goodsInfoBean.getShopId() + "");
        greenDaoAssist.insertGoods(DemoConstant.shopInfoBean.getShopId()+"", goodsInfo, isChange -> DemoConstant.isChangeDatabase = isChange);

        ReqCartAddInfo addInfo = new ReqCartAddInfo();
        ReqCartAddInfo.ReqCartAddInfoBean infoBean = new ReqCartAddInfo.ReqCartAddInfoBean();
        infoBean.setGoodsId(goodsInfo.getGoodsId());
        infoBean.setAttrStrId(goodsInfo.getSpecId());
        infoBean.setTotalNum(goodsInfo.getGoodsNumber());
        List<ReqCartAddInfo.ReqCartAddInfoBean> goods = new ArrayList<>();
        goods.add(infoBean);
        addInfo.setGoods(goods);
        if(YigouConstant.token.equals("")){
            SnackbarUtil.showToast(rootView,"添加成功");
        }else{
            getPresenter().addGoodsToShoppingCart(addInfo);
        }

    }
}