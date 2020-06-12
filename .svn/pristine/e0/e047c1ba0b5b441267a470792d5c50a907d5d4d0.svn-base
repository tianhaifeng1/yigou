package com.work.doctor.fruits.activity.Goods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.GreenDaoAssist;
import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.resp.EvaluateInfoBean;
import com.t.httplib.yigou.bean.resp.GmrOutInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsDetailInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tbase.adapter.TViewPagerStateAdapter;
import com.trjx.tbase.fragment.TFragment;
import com.trjx.tlibs.uils.SnackbarUtil;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.MainNavActivity;
import com.work.doctor.fruits.activity.order.GoodsAffirmActivity;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.MoreGoodsSpecInfoDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品详情页的另一种实现方式，暂时未采纳
 */
@Deprecated
public class GoodsDetialActivity extends DemoMVPActivity<GoodsDetialView, GoodsDetialPresenter>
        implements View.OnClickListener, GoodsDetialView, MoreGoodsSpecInfoDialog.OnMoreGoodsSpecInfoListener {

    private TabLayout mTablayout;
    private ViewPager mViewpager;

    private List<TFragment> fragments;
    private RelativeLayout mSdHomepage;
    private RelativeLayout mSdServer;
    private RelativeLayout mSdShopcart;
    private TextView mSdShopcartNum;
    private TextView mSdAdd;
    private TextView mSdBuy;

    private int index = 0;

    private GreenDaoAssist greenDaoAssist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detial);

    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("", true);

        mTablayout = findViewById(R.id.tablayout);
        mViewpager = findViewById(R.id.viewpager);

        mSdHomepage = findViewById(R.id.sd_homepage);
        mSdServer = findViewById(R.id.sd_server);
        mSdShopcart = findViewById(R.id.sd_shopcart);
        mSdShopcartNum = findViewById(R.id.sd_shopcart_num);
        mSdAdd = findViewById(R.id.sd_add);
        mSdBuy = findViewById(R.id.sd_buy);

        mSdAdd.setOnClickListener(this);
        mSdBuy.setOnClickListener(this);
        mSdHomepage.setOnClickListener(this);
        mSdServer.setOnClickListener(this);
        mSdShopcart.setOnClickListener(this);
        initFragmentViewData();

        int goodsId = getIntent().getIntExtra("id", -1);
        if (goodsId > -1) {
            getPresenter().getGoodsDetail(goodsId);
        }

        greenDaoAssist = new GreenDaoAssist(((DemoApplication)getApplication()).databaseAssist);

        setLaber();
    }

    private void setLaber(){
        long number = greenDaoAssist.queryGoodsTypeNumber();
        if (number > 0) {
            mSdShopcartNum.setText(number > 99 ? "99" : number + "");
            mSdShopcartNum.setVisibility(View.VISIBLE);
        }else{
            mSdShopcartNum.setVisibility(View.GONE);
        }
    }


    private void initFragmentViewData(){
        List<String> stringList = getTabTitle();
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < stringList.size(); i++) {
            mTablayout.addTab(mTablayout.newTab().setText(stringList.get(i)));
        }

        mViewpager.setOffscreenPageLimit(2);

        fragments = new ArrayList<>();

        GoodsDetialInfoFragment shopDetialFragment = new GoodsDetialInfoFragment(1);
        fragments.add(shopDetialFragment);
        GoodsEvaluateFragment goodsEvaluateFragment = new GoodsEvaluateFragment();
        fragments.add(goodsEvaluateFragment);

        TViewPagerStateAdapter<TFragment> adapter = new TViewPagerStateAdapter(getSupportFragmentManager(), fragments, stringList);
        mViewpager.setAdapter(adapter);
        mTablayout.setupWithViewPager(mViewpager);
        mTablayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                index = tab.getPosition();
                fragments.get(index).initData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private List<String> getTabTitle() {
        List<String> stringList = new ArrayList<>();
        stringList.add("商品");
        stringList.add("评价");
        return stringList;
    }


    @Override
    protected GoodsDetialPresenter initPersenter() {
        return new GoodsDetialPresenter(this);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids){
            case R.id.sd_add:
                getGoodsItemData(1);
                break;
            case R.id.sd_buy:
                getGoodsItemData(2);
                break;
            case R.id.sd_homepage:
                Intent intent_home = new Intent(context, MainNavActivity.class);
                intent_home.putExtra("position", 0);
                startActivity(intent_home);
                break;
            case R.id.sd_server:
                showPremission_call_phone("0891-6373803");
                break;
            case R.id.sd_shopcart:
                Intent intent_shopcart = new Intent(context, MainNavActivity.class);
                intent_shopcart.putExtra("position", 2);
                startActivity(intent_shopcart);
                break;
        }
    }


    public GoodsDetailInfoBean goodsDetailInfoBean;

    @Override
    public void getGoodsDetailSuccess(GoodsDetailInfoBean infoBean) {
        this.goodsDetailInfoBean = infoBean;
        fragments.get(index).initData();
    }

    @Override
    public void getGoodsItemSuccess(GoodsSpecInfoBean infoBean, int dialogState) {
//        if (infoBean != null && infoBean.getGoodsItemList() != null && infoBean.getGoodsItemList().size() > 0) {
            // 有规格
            goodsItemDialog(dialogState, infoBean);
//        } else {
//            //没有规格
//            //直接添加到购物车
//            DatabaseGoodsInfo goodsInfo = new DatabaseGoodsInfo();
////        商品信息
//            goodsInfo.setGoodsId(goodsDetailInfoBean.getId()+"");
//            goodsInfo.setGoodsName(goodsDetailInfoBean.getGoodsName());
//            goodsInfo.setGoodsPrice(goodsDetailInfoBean.getSellPriceDiscount().floatValue());
//            goodsInfo.setGoodsNumber(1);
//            goodsInfo.setGoodsUrl(goodsDetailInfoBean.getGoodsImage());
//            goodsInfo.setGoodsTotal(100);
//            //规格
//            goodsInfo.setSpecId("");
//            goodsInfo.setSpecName("");
//
//            insertGoods(goodsInfo);
//        }
    }

    @Override
    public void addGoodsToShoppingCartSuccess() {
        SnackbarUtil.showToast(rootView,"添加成功");
    }

    @Override
    public void getListDataEvaluateSuccess(List<EvaluateInfoBean> list) {

    }

    @Override
    public void getGmrDataSuccess(GmrOutInfoBean infoBean) {

    }

    //获取规格数据
    public void getGoodsItemData(int dialogState){
        if (!ispost) {
            if (goodsDetailInfoBean != null) {
                getPresenter().getGoodsItem(goodsDetailInfoBean.getId(),dialogState);
            }
        }else{
            goodsItemDialog(dialogState,goodsSpecInfoBean);
        }
    }


    private boolean ispost = false;
    private GoodsSpecInfoBean goodsSpecInfoBean;

    //弹框
    public void goodsItemDialog(int dialogState, GoodsSpecInfoBean goodsSpecInfoBean) {
        ispost = true;
        this.goodsSpecInfoBean = goodsSpecInfoBean;
        MoreGoodsSpecInfoDialog infoDialog = new MoreGoodsSpecInfoDialog.Builder(context)
                .setCancelable(false)
                .setDialogState(dialogState)
                .setGoodsInfo(goodsDetailInfoBean)
                .setGoodsItemList(goodsSpecInfoBean)
                .setOnMoreGoodsSpecInfoListener(this)
                .create();
        infoDialog.show(getSupportFragmentManager(), "tishi_itemspec");
    }


    @Override
    public void onOnMoreGoodsAffirmClick(View view, String specIds, String specName,float goodsPrice, float goodsPriceVip,int number) {
        //立即购买
        if (goodsDetailInfoBean != null) {
            DatabaseGoodsInfo goodsInfo = new DatabaseGoodsInfo();
            goodsInfo.setId(null);
            goodsInfo.setGoodsId(goodsDetailInfoBean.getId() + "");
            goodsInfo.setGoodsName(goodsDetailInfoBean.getGoodsName());
            goodsInfo.setGoodsPrice(goodsPrice);
//            goodsInfo.setGoodsPrice(goodsDetailInfoBean.getSellPriceDiscount().floatValue());
            goodsInfo.setGoodsTotal(goodsDetailInfoBean.getStock());
            goodsInfo.setGoodsAddTime(0);
            goodsInfo.setGoodsNumber(number);
            goodsInfo.setGoodsUrl(goodsDetailInfoBean.getGoodsImage());
            goodsInfo.setShopId(DemoConstant.shopInfoBean.getShopId()+"");
            goodsInfo.setSpecId(specIds);
            goodsInfo.setSpecName(specName);

            ArrayList<DatabaseGoodsInfo> list = new ArrayList<DatabaseGoodsInfo>();
            list.add(goodsInfo);

            Intent intent = new Intent(context, GoodsAffirmActivity.class);
            intent.putExtra("code", 1);
            intent.putExtra("list", list);
            intent.putExtra("shopid", DemoConstant.shopInfoBean.getShopId()+"");
            skipActivity(intent);
        }


    }

    @Override
    public void onOnMoreGoodsAddcartClick(View view, String specIds, String specName,float goodsPrice,float goodsPriceVip, int number) {
        //添加购物车
        DatabaseGoodsInfo goodsInfo = new DatabaseGoodsInfo();
//        商品信息
        goodsInfo.setGoodsId(goodsDetailInfoBean.getId()+"");
        goodsInfo.setGoodsName(goodsDetailInfoBean.getGoodsName());
        goodsInfo.setGoodsPrice(goodsPrice);
        goodsInfo.setGoodsNumber(number);
        goodsInfo.setGoodsUrl(goodsDetailInfoBean.getGoodsImage());
        goodsInfo.setGoodsTotal(goodsDetailInfoBean.getStock());
        //规格
        goodsInfo.setSpecId(specIds);
        goodsInfo.setSpecName(specName);
        goodsInfo.setShopId(goodsDetailInfoBean.getShopId()+"");

        greenDaoAssist.insertGoods(DemoConstant.shopInfoBean.getShopId()+"", goodsInfo, isChange -> DemoConstant.isChangeDatabase = isChange);

        setLaber();

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
