package com.work.doctor.fruits.activity.Goods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.GreenDaoAssist;
import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.trjx.tlibs.uils.SnackbarUtil;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.ShoppingAdapter;
import com.work.doctor.fruits.activity.adapter.ShoppingJrtmAdapter;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.MoreGoodsSpecInfoDialog2;
import com.work.doctor.fruits.dialog.RemindDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * 数据列表显示页面，支持所有的列表显示页
 * <p>
 * code：根据 code 区分不同的列表页面
 * <p>
 * code = 0 ：今日特卖列表页面
 * <p>
 * code = 1 ：推荐商品列表页面
 */
public class GoodsListMOMoreActivity extends DemoMVPActivity<GoodsListMOMoreView, GoodsListMOMorePresenter>
        implements BaseQuickAdapter.OnItemClickListener,
        BaseQuickAdapter.OnItemChildClickListener,
        TRecyclerViewListenter,
        GoodsListMOMoreView,
        MoreGoodsSpecInfoDialog2.OnMoreGoodsListSpecInfoListener {

    private int code = -1;

    private TRecyclerModule recyclerModule;

    private GreenDaoAssist greenDaoAssist;

    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodslist_momore);
    }

    @Override
    protected void initView() {
        super.initView();

        greenDaoAssist = new GreenDaoAssist(((DemoApplication) getApplication()).databaseAssist);

        relativeLayout = findViewById(R.id.agm_rl);

        Intent intent = getIntent();
        code = intent.getIntExtra("code", -1);

        titleModule.initTitle("", true);
        String titleStr = "";
//            今日特卖
        if (code == 0) {
            titleStr = "今日特卖";
            relativeLayout.setVisibility(View.VISIBLE);
            ShoppingJrtmAdapter jrtmAdapter = new ShoppingJrtmAdapter(null,false);
            //初始化Module
            recyclerModule = new TRecyclerModule.Builder(context)
                    .setLayoutManager(new GridLayoutManager(context, 3))
                    .createAdapter(jrtmAdapter)
                    .setPageSize(24)
                    .setTRecyclerViewListenter(this)
                    .creat(rootView);
            recyclerModule.setRefreshing(true);
            recyclerModule.setDefImg(R.mipmap.default_goodslist);
            recyclerModule.setDefText("暂无商品");
//            recyclerModule.getRecyclerView().setBackground(getResources().getDrawable(R.drawable.waikuang_r5_white,null));
            jrtmAdapter.setOnItemClickListener(this);
            jrtmAdapter.setOnItemChildClickListener(this);
        } else if (code == 1) {
            titleStr = "推荐商品";
            relativeLayout.setVisibility(View.GONE);
            ShoppingAdapter shoppingAdapter = new ShoppingAdapter(null);
            //初始化Module
            recyclerModule = new TRecyclerModule.Builder(context)
                    .setLayoutManager(new GridLayoutManager(context, 3))
                    .createAdapter(shoppingAdapter)
                    .setPageSize(24)
                    .setTRecyclerViewListenter(this)
                    .creat(rootView);
            recyclerModule.setRefreshing(true);
            recyclerModule.setDefImg(R.mipmap.default_goodslist);
            recyclerModule.setDefText("暂无商品");
//            recyclerModule.getRecyclerView().setBackground(getResources().getDrawable(R.drawable.waikuang_r5_white,null));
            shoppingAdapter.setOnItemClickListener(this);
            shoppingAdapter.setOnItemChildClickListener(this);
        }
        //设置标题
        titleModule.setTitleText(titleStr);

        if (recyclerModule != null) {
            getRecyclerListData();
        }

    }

    @Override
    protected GoodsListMOMorePresenter initPersenter() {
        return new GoodsListMOMorePresenter(this);
    }

    //======================  获取不同列表的数据  ====================
    @Override
    public void getListDataSuccess(List<GoodsInfoBean> list) {
        //今日特卖更改库存
        if (code == 0 && list != null && list.size() > 0) {
            for (int i = 0, size = list.size(); i < size; i++) {
                GoodsInfoBean listBean = list.get(i);
                //更改库存
                if(listBean.getIsTemai()==0){
                    if (listBean.getSpecialSale() == 0 && listBean.getStock() > 0) {
                        listBean.setStock(1);
                    } else {
                        listBean.setStock(0);
                    }
                }

            }
        }

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
            goodsInfo.setGoodsId(goodsInfoBean.getId() + "");
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
            greenDaoAssist.insertGoods(DemoConstant.shopInfoBean.getShopId() + "", goodsInfo, isChange -> DemoConstant.isChangeDatabase = isChange);

            ReqCartAddInfo addInfo = new ReqCartAddInfo();
            ReqCartAddInfo.ReqCartAddInfoBean infoBean2 = new ReqCartAddInfo.ReqCartAddInfoBean();
            infoBean2.setGoodsId(goodsInfo.getGoodsId());
            infoBean2.setAttrStrId(goodsInfo.getSpecId());
            infoBean2.setTotalNum(goodsInfo.getGoodsNumber());
            List<ReqCartAddInfo.ReqCartAddInfoBean> goods = new ArrayList<>();
            goods.add(infoBean2);
            addInfo.setGoods(goods);
            if (YigouConstant.token.equals("")) {
                SnackbarUtil.showToast(rootView, "添加成功");
            } else {
                getPresenter().addGoodsToShoppingCart(addInfo);
            }
        }
    }

    @Override
    public void addGoodsToShoppingCartSuccess() {
        SnackbarUtil.showToast(rootView, "添加成功");
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
        switch (ids) {
            case R.id.item_shop_cart:
                goodsInfoBean = (GoodsInfoBean) adapter.getData().get(position);
                if (goodsInfoBean.getSpecialSale() == 1) {
                    RemindDialog remindDialog = new RemindDialog.Builder(context)
                            .setTitle("提示")
                            .setMessage("亲，此商品每天仅限购一件哦！")
                            .setAffirmText("确认")
                            .setCancelable(true)
                            .create();
                    remindDialog.show(getSupportFragmentManager(),"dialog_remind_xiangou");
                    return;
                }
                getPresenter().getGoodsItem(goodsInfoBean.getId());
                break;
        }
    }


    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        getPresenter().getListData(code, recyclerModule.getPage(), recyclerModule.getPageSize());
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
        goodsInfo.setGoodsId(goodsInfoBean.getId() + "");
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
        greenDaoAssist.insertGoods(DemoConstant.shopInfoBean.getShopId() + "", goodsInfo, isChange -> DemoConstant.isChangeDatabase = isChange);

        ReqCartAddInfo addInfo = new ReqCartAddInfo();
        ReqCartAddInfo.ReqCartAddInfoBean infoBean = new ReqCartAddInfo.ReqCartAddInfoBean();
        infoBean.setGoodsId(goodsInfo.getGoodsId());
        infoBean.setAttrStrId(goodsInfo.getSpecId());
        infoBean.setTotalNum(goodsInfo.getGoodsNumber());
        List<ReqCartAddInfo.ReqCartAddInfoBean> goods = new ArrayList<>();
        goods.add(infoBean);
        addInfo.setGoods(goods);
        if (YigouConstant.token.equals("")) {
            SnackbarUtil.showToast(rootView, "添加成功");
        } else {
            getPresenter().addGoodsToShoppingCart(addInfo);
        }
    }
}