package com.work.doctor.fruits.activity.Goods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.work.doctor.fruits.activity.ActivityTimerAssist;
import com.work.doctor.fruits.activity.adapter.ShoppingYsAdapter;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.TLoadMoreView;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.MoreGoodsSpecInfoDialog2;

import java.util.ArrayList;
import java.util.List;

/**
 * 预售商品列表
 */
public class GoodsListYsActivity extends DemoMVPActivity<GoodsListYsView, GoodsListYsPresenter> implements GoodsListYsView, TRecyclerViewListenter, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener, MoreGoodsSpecInfoDialog2.OnMoreGoodsListSpecInfoListener, ActivityTimerAssist.TimerToTimeListener {

    private TRecyclerModule recyclerModule;

    private ShoppingYsAdapter ysAdapter;

    private GreenDaoAssist greenDaoAssist;

//    抢购商品
    private GoodsInfoBean goodsInfoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list_ys);
    }

    @Override
    protected GoodsListYsPresenter initPersenter() {
        return new GoodsListYsPresenter(this);
    }

    private ActivityTimerAssist timerAssist;

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("预售专区", true);

        timerAssist = new ActivityTimerAssist();
        timerAssist.setOnTimerToTimeListener(this);

        greenDaoAssist = new GreenDaoAssist(((DemoApplication) getApplication()).databaseAssist);

        ysAdapter = new ShoppingYsAdapter(null){
            @Override
            public void addTimer(long time, int position,long currentTime) {
                timerAssist.setTime(time, position,currentTime);
            }
        };
        ysAdapter.setLoadMoreView(new TLoadMoreView());
        recyclerModule = new TRecyclerModule.Builder(context)
                .setLayoutManager(new LinearLayoutManager(context))
                .setPage(1)
                .setPageSize(30)
                .setTRecyclerViewListenter(this)
                .createAdapter(ysAdapter)
                .creat(rootView);
        recyclerModule.setDefImg(R.mipmap.default_goodslist);
        recyclerModule.setDefText("暂无商品");
        ysAdapter.setOnItemChildClickListener(this);
        ysAdapter.setOnItemClickListener(this);

        getRecyclerListData();

    }

    @Override
    public void getYshouDataSuccess(List<?> list) {
        recyclerModule.setRefreshing(false);
        recyclerModule.bindListData(list, false);
    }

    @Override
    public void getGoodsSpecSuccess(GoodsSpecInfoBean infoBean) {
        if (infoBean != null && infoBean.getGoodsItemList() != null && infoBean.getGoodsItemList().size() > 0) {
            // 有规格
            goodsItemDialog(infoBean);
        } else {
            DatabaseGoodsInfo databaseGoodsInfo = greenDaoAssist.queryGoodsInfo(goodsInfoBean.getId() + "",goodsInfoBean.getShopId()+"","");
            if(goodsInfoBean.getGoodsType() == 3){
//                预售商品
                int goodsNumber = goodsInfoBean.getSpecialSale();
                if (goodsInfoBean.getLimitNum() <= 0 || goodsNumber >= goodsInfoBean.getLimitNum()) {
                    SnackbarUtil.showToast(rootView, "此商品限购" + goodsInfoBean.getLimitNum() + "份");
                    return;
                }
                if (databaseGoodsInfo != null && databaseGoodsInfo.getGoodsNumber() >= goodsInfoBean.getLimitNum()) {
                    SnackbarUtil.showToast(rootView, "添加成功");
                    return;
                }
            }else{
//                普通商品
                if (goodsInfoBean.getStock() <= 0) {
                    SnackbarUtil.showToast(rootView, "暂无库存");
                    return;
                }
                if (databaseGoodsInfo != null && databaseGoodsInfo.getGoodsNumber() >= goodsInfoBean.getStock()) {
                    SnackbarUtil.showToast(rootView, "添加成功");
                    return;
                }
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

            goodsInfo.setIsTemai(goodsInfoBean.getIsTemai());

//        店铺信息
            goodsInfo.setShopId(goodsInfoBean.getShopId() + "");

            //            商品类型
            int goodsType = goodsInfoBean.getGoodsType();
            goodsInfo.setGoodsType(goodsType);
            if(goodsType==3){
                goodsInfo.setStartTime(goodsInfoBean.getStartTime());
                goodsInfo.setEndTime(goodsInfoBean.getEndTime());
                goodsInfo.setGoodsTotal(goodsInfoBean.getLimitNum());
            }else{
                goodsInfo.setGoodsTotal(goodsInfoBean.getStock());
            }

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
//                SnackbarUtil.showToast(activity.context, "添加成功");
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
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        if (recyclerModule.getPage() == 1) {
            timerAssist.refresh();
        }
        getPresenter().getYshouData(recyclerModule.getPage(),recyclerModule.getPageSize());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        int ids = view.getId();
        switch (ids){
            case R.id.item_shop_gmr_ll:
                //购买人
                GoodsInfoBean infoBean = (GoodsInfoBean) adapter.getData().get(position);
                Intent intent = new Intent(context, GoodsDetial3Activity.class);
                intent.putExtra("index", 1);
                intent.putExtra("id", infoBean.getId());
                skipActivity(intent);
                break;
            case R.id.item_shop_cart:
                //抢购
                goodsInfoBean = (GoodsInfoBean) adapter.getData().get(position);
                getPresenter().getGoodsItem(goodsInfoBean.getId());
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        GoodsInfoBean infoBean = (GoodsInfoBean) adapter.getData().get(position);
        Intent intent = new Intent(context, GoodsDetial3Activity.class);
        intent.putExtra("index", 0);
        intent.putExtra("id", infoBean.getId());
        skipActivity(intent);
    }


    private MoreGoodsSpecInfoDialog2 infoDialog;

    //弹框
    private void goodsItemDialog(GoodsSpecInfoBean specListBeanList) {
        if (infoDialog == null || infoDialog.getDialog() == null) {
            infoDialog = new MoreGoodsSpecInfoDialog2.Builder(context)
                    .setCancelable(false)
                    .setGoodsInfo(goodsInfoBean)
                    .setGoodsItemList(specListBeanList)
                    .setOnMoreGoodsListSpecInfoListener(this)
                    .create();
            infoDialog.show(getSupportFragmentManager(), "tishi_itemspec");
        }
    }

    @Override
    public void onOnMoreGoodsListAddcartClick(View view, String specIds, String specName, float goodsPrice, int number) {
        //添加购物车
//        SnackbarUtil.showToast(activity.context, "添加购物车商品为：" + goodsInfoBean.getGoodsName());

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

        //            商品类型
        int goodsType = goodsInfoBean.getGoodsType();
        goodsInfo.setGoodsType(goodsType);

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
//            SnackbarUtil.showToast(activity.context, "添加成功");
        } else {
            getPresenter().addGoodsToShoppingCart(addInfo);
        }
    }

    @Override
    protected void onDestroy() {
        if (timerAssist != null) {
            timerAssist.onDestory();
        }
        super.onDestroy();
    }

    @Override
    public void timerToTimeEvent(List<Integer> list,long time) {
        if (ysAdapter != null && list != null && list.size() > 0) {
            for (Integer index : list
            ) {
                List<GoodsInfoBean> infoBeanList = ysAdapter.getData();
                if (infoBeanList != null && infoBeanList.size() > index) {
                    if (time <= 0) {
                        ysAdapter.refreshNotifyItemChanged(index);
                    }
                }
            }
        }
    }
}
