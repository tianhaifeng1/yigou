package com.work.doctor.fruits.activity.Goods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.GreenDaoAssist;
import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsListInfo;
import com.t.httplib.yigou.bean.resp.BrandInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.SnackbarUtil;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.MainNavActivity;
import com.work.doctor.fruits.activity.adapter.SearchGoodsAdapter;
import com.work.doctor.fruits.activity.search.SearchGoodsActivity;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.filtermodule2.OnTFilterItemClickListener;
import com.work.doctor.fruits.assist.filtermodule2.TFilterModuleAssist;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.MoreGoodsSpecInfoDialog2;

import java.util.ArrayList;
import java.util.List;

public class GoodsListActivity extends DemoMVPActivity<GoodsListView, GoodsListPresenter>
        implements GoodsListView, OnTFilterItemClickListener, TRecyclerViewListenter, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, MoreGoodsSpecInfoDialog2.OnMoreGoodsListSpecInfoListener {

    private int typeId = -1;

    private ImageView titleBack;
    private RelativeLayout titleSearch;
    private ImageView titleGwc;

    private ReqGoodsListInfo info;

    private TRecyclerModule recyclerModule;

    private GreenDaoAssist greenDaoAssist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
    }

    @Override
    protected void initView() {
        titleBack = findViewById(R.id.title_back);
        titleSearch = findViewById(R.id.title_search);
        titleGwc = findViewById(R.id.title_gwc);

        //返回
        titleBack.setOnClickListener(v -> finish());
        //搜索
        titleSearch.setOnClickListener(v -> skipActivity(SearchGoodsActivity.class));
        //购物车跳转
        titleGwc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_shopcart = new Intent(context, MainNavActivity.class);
                intent_shopcart.putExtra("position", 2);
                startActivity(intent_shopcart);
            }
        });

        greenDaoAssist = new GreenDaoAssist(((DemoApplication)getApplication()).databaseAssist);

        typeId = getIntent().getIntExtra("id", -1);

        info = new ReqGoodsListInfo();

        SearchGoodsAdapter goodsAdapter = new SearchGoodsAdapter(null);
        recyclerModule = new TRecyclerModule.Builder(context)
                .setLayoutManager(new LinearLayoutManager(context))
                .setPage(1)
                .setPageSize(30)
                .setTRecyclerViewListenter(this)
                .createAdapter(goodsAdapter)
                .creat(rootView);
        recyclerModule.setDefImg(R.mipmap.default_goodslist);
        recyclerModule.setDefText("该条件下还没有商品哦");
        goodsAdapter.setOnItemChildClickListener(this);
        goodsAdapter.setOnItemClickListener(this);

        int shopId = DemoConstant.shopInfoBean == null ? -1 : DemoConstant.shopInfoBean.getShopId();
        if (shopId > -1) {
            // 初始化参数
            info.setWhole(1);
            info.setBrandId(0);
            info.setSell(0);
            info.setPrice(0);
            info.setShopId(shopId);
            info.setRelatedCatids(typeId);
            info.setPage(1);
            info.setPageSize(recyclerModule.getPageSize());

            getPresenter().getBrandListData(shopId, typeId);

        }else{
            Logger.t("shopid 异常");
        }

    }


    @Override
    protected GoodsListPresenter initPersenter() {
        return new GoodsListPresenter(this);
    }

    private List<BrandInfoBean> beanList;

    private TFilterModuleAssist moduleAssist;

    @Override
    public void getBrandListDataSuccess(List<BrandInfoBean> list) {
        beanList = list;
        moduleAssist = new TFilterModuleAssist(context);
        moduleAssist.initData(list);

        moduleAssist.creat(rootView).setOnTFilterModuleListener(this);

        getRecyclerListData();

    }

    @Override
    public void getListDataSuccess(List<?> list) {
        recyclerModule.setRefreshing(false);
        recyclerModule.bindListData(list);
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

    @Override
    public void onFilterItem(int tabIndex, int itemPosition, boolean haveList) {

        moduleAssist.refreshTab(tabIndex,itemPosition);
        info.setWhole(1);
        info.setSell(0);
        info.setPrice(0);
        info.setPage(1);

        if(haveList){
            Logger.t("youjihe ");

            if(tabIndex == 0){
                if(itemPosition == 0){
                    info.setWhole(1);
                }else if(itemPosition == 1){
                    info.setWhole(2);
                }
            }else if(tabIndex == 3){
                if(itemPosition>-1){
                    info.setBrandId(beanList.get(itemPosition).getId());
                }else{
                    info.setBrandId(0);
                }
            }


        }else{
            Logger.t("meiyoujihe ");
            info.setWhole(0);
            if(tabIndex == 1){
                //销量：判断状态
                info.setSell(itemPosition);
            }else if(tabIndex == 2){
                //价格：判断状态
                info.setPrice(itemPosition);
            }

        }
        getRecyclerListData();

    }

    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        info.setPage(recyclerModule.getPage());
        getPresenter().getGoodsListData(info);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        GoodsInfoBean shopInfoBean = (GoodsInfoBean) adapter.getData().get(position);
        int id = shopInfoBean.getId();
        Intent intent = new Intent(context, GoodsDetial2Activity.class);
        intent.putExtra("id", id);
        skipActivity(intent);
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

    //弹框
    private void goodsItemDialog(GoodsSpecInfoBean specListBeanList) {
        MoreGoodsSpecInfoDialog2 infoDialog = new MoreGoodsSpecInfoDialog2.Builder(context)
                .setCancelable(false)
                .setGoodsInfo(goodsInfoBean)
                .setGoodsItemList(specListBeanList)
                .setOnMoreGoodsListSpecInfoListener(this)
                .create();
        infoDialog.show(getSupportFragmentManager(), "tishi_itemspec");
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
