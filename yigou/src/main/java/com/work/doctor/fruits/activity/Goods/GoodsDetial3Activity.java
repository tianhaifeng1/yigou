package com.work.doctor.fruits.activity.Goods;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.GreenDaoAssist;
import com.t.databaselib.bean.PresellInfoBean;
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
import com.work.doctor.fruits.activity.ActivityTimerAssist;
import com.work.doctor.fruits.activity.MainNavActivity;
import com.work.doctor.fruits.activity.order.GoodsAffirmActivity;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.GetNetworkTime;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.MoreGoodsSpecInfoDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品详情页的另一种实现方式，暂时未采纳
 */
public class GoodsDetial3Activity extends DemoMVPActivity<GoodsDetialView, GoodsDetialPresenter>
        implements View.OnClickListener, GoodsDetialView, MoreGoodsSpecInfoDialog.OnMoreGoodsSpecInfoListener, ActivityTimerAssist.TimerToTimeListener {

    private TabLayout mTablayout;
    private ViewPager mViewpager;

    private List<TFragment> fragments;
    private GoodsDetialInfo3Fragment shopDetialFragment;
    private GoodsGmrFragment goodsGmrFragment;
    private RelativeLayout mSdHomepage;
    private RelativeLayout mSdServer;
    private RelativeLayout mSdShopcart;
    private TextView mSdShopcartNum;
    private TextView mSdAdd;
    private TextView mSdBuy;

    public int goodsId;

    private int index = 0;

    private GreenDaoAssist greenDaoAssist;

    public ActivityTimerAssist timerAssist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detial3);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("商品详情", true);

        timerAssist = new ActivityTimerAssist(true);
        timerAssist.setOnTimerToTimeListener(this);

        index = getIntent().getIntExtra("index", 0);
        goodsId = getIntent().getIntExtra("id", -1);
        if (goodsId == -1) {
            goodsId = 118;
        }

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

        List<String> stringList = getTabTitle();
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < stringList.size(); i++) {
            mTablayout.addTab(mTablayout.newTab().setText(stringList.get(i)));
        }

        mViewpager.setOffscreenPageLimit(2);

        fragments = new ArrayList<>();

        shopDetialFragment = new GoodsDetialInfo3Fragment(goodsId);
        fragments.add(shopDetialFragment);
        goodsGmrFragment = new GoodsGmrFragment();
        fragments.add(goodsGmrFragment);

        TViewPagerStateAdapter<TFragment> adapter = new TViewPagerStateAdapter(getSupportFragmentManager(), fragments, stringList);
        mViewpager.setAdapter(adapter);
        mViewpager.setCurrentItem(index, false);
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

        greenDaoAssist = new GreenDaoAssist(((DemoApplication) getApplication()).databaseAssist);
        initGoodsDetail();
        setLaber();
    }

    public void initGoodsDetail() {
        if (goodsId > -1) {
            getPresenter().getGoodsDetail(goodsId);
        }
    }


    private void setLaber() {
        long number = greenDaoAssist.queryGoodsTypeNumber();
        if (number > 0) {
            mSdShopcartNum.setText(number > 99 ? "99" : number + "");
            mSdShopcartNum.setVisibility(View.VISIBLE);
        } else {
            mSdShopcartNum.setVisibility(View.GONE);
        }
    }

    private List<String> getTabTitle() {
        List<String> stringList = new ArrayList<>();
        stringList.add("基本信息");
        stringList.add("购买记录");
        return stringList;
    }


    @Override
    protected GoodsDetialPresenter initPersenter() {
        return new GoodsDetialPresenter(this);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
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
    private DatabaseGoodsInfo databaseGoodsInfo;

    @Override
    public void getGoodsDetailSuccess(GoodsDetailInfoBean infoBean) {
        this.goodsDetailInfoBean = infoBean;
        databaseGoodsInfo = greenDaoAssist.queryGoodsInfo(goodsDetailInfoBean.getId() + "", goodsDetailInfoBean.getShopId() + "", "");
        fragments.get(0).initData();

        PresellInfoBean presellInfoBean = infoBean.getPresell();

        new GetNetworkTime(currentTime -> {
            long cha = presellInfoBean.getStartTime();
            long cha2 = presellInfoBean.getEndTime();
            if (cha > currentTime) {
                changeViewState(1);
                shopDetialFragment.setTextDjsText("距离本商品开始还剩:");
                timerAssist.setTime(cha, -1,currentTime);
            } else if (cha2 > currentTime) {
                changeViewState(0);
                shopDetialFragment.setTextDjsText("距离本商品结束还剩:");
                timerAssist.setTime(cha2, -1,currentTime);
            } else {
                changeViewState(1);
                shopDetialFragment.setTextDjsText("本商品预售已结束");
                shopDetialFragment.setTextDjsTime("00：00：00");
            }
        });
    }

    public void changeViewState(int state) {
        if (state == 1) {
            int tint = Color.parseColor("#d7d7d7");
            mSdAdd.getBackground().setColorFilter(tint, PorterDuff.Mode.SRC_IN);
            mSdBuy.getBackground().setColorFilter(tint, PorterDuff.Mode.SRC_IN);
            mSdAdd.setEnabled(false);
            mSdBuy.setEnabled(false);
        } else {
            int tint1 = Color.parseColor("#ffa800");
            int tint2 = Color.parseColor("#E00605");
            mSdAdd.getBackground().setColorFilter(tint1, PorterDuff.Mode.SRC_IN);
            mSdBuy.getBackground().setColorFilter(tint2, PorterDuff.Mode.SRC_IN);
            mSdAdd.setEnabled(true);
            mSdBuy.setEnabled(true);
        }
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
        SnackbarUtil.showToast(rootView, "添加成功");
    }

    public List<EvaluateInfoBean> evaluateInfoBeanList;

    @Override
    public void getListDataEvaluateSuccess(List<EvaluateInfoBean> list) {
        evaluateInfoBeanList = list;
        shopDetialFragment.getListDataEvaluateSuccess(list);
    }

    public GmrOutInfoBean gmrOutInfoBean;

    @Override
    public void getGmrDataSuccess(GmrOutInfoBean infoBean) {
        if (infoBean != null) {
            gmrOutInfoBean = infoBean;
            fragments.get(1).initData();
        }
    }

    //获取规格数据
    public void getGoodsItemData(int dialogState) {
        if (goodsDetailInfoBean != null && goodsDetailInfoBean.getPresell() != null
                && goodsDetailInfoBean.getPresell().getBuyNum() >= goodsDetailInfoBean.getPresell().getLimitNum()&&goodsDetailInfoBean.getPresell().getLimitNum()!=0) {
            SnackbarUtil.showToast(rootView, "此商品限购" + goodsDetailInfoBean.getPresell().getLimitNum() + "份，您已经购买过此商品");
            return;
        }

        if (!ispost) {
            if (goodsDetailInfoBean != null) {
                getPresenter().getGoodsItem(goodsId, dialogState);
            }
        } else {
            goodsItemDialog(dialogState, goodsSpecInfoBean);
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
    public void onOnMoreGoodsAffirmClick(View view, String specIds, String specName, float goodsPrice, float goodsPriceVip, int number) {
        //立即购买
        if (goodsDetailInfoBean != null) {
            DatabaseGoodsInfo goodsInfo = new DatabaseGoodsInfo();
            goodsInfo.setId(null);
            goodsInfo.setGoodsId(goodsDetailInfoBean.getId() + "");
            goodsInfo.setGoodsName(goodsDetailInfoBean.getGoodsName());
            goodsInfo.setGoodsPrice(goodsPrice);
            goodsInfo.setGoodsPriceVip(goodsPriceVip);
//            goodsInfo.setGoodsPrice(goodsDetailInfoBean.getSellPriceDiscount().floatValue());
            goodsInfo.setGoodsTotal(goodsDetailInfoBean.getStock());
            goodsInfo.setGoodsAddTime(0);
            goodsInfo.setGoodsNumber(number);
            goodsInfo.setGoodsUrl(goodsDetailInfoBean.getGoodsImage());
            goodsInfo.setShopId(DemoConstant.shopInfoBean.getShopId() + "");
            goodsInfo.setSpecId(specIds);
            goodsInfo.setSpecName(specName);
            //            商品类型
            int goodsType = goodsDetailInfoBean.getGoodsType();
            goodsInfo.setGoodsType(goodsType);
            if (goodsType == 3 && goodsDetailInfoBean.getPresell() != null) {
                goodsInfo.setStartTime(goodsDetailInfoBean.getPresell().getStartTime());
                goodsInfo.setEndTime(goodsDetailInfoBean.getPresell().getEndTime());
                goodsInfo.setGoodsTotal(goodsDetailInfoBean.getPresell().getLimitNum());
            } else {
                goodsInfo.setGoodsTotal(goodsDetailInfoBean.getStock());
            }

            ArrayList<DatabaseGoodsInfo> list = new ArrayList<DatabaseGoodsInfo>();
            list.add(goodsInfo);

            Intent intent = new Intent(context, GoodsAffirmActivity.class);
            intent.putExtra("code", 1);
            intent.putExtra("list", list);
            intent.putExtra("shopid", DemoConstant.shopInfoBean.getShopId() + "");
            skipActivity(intent);
        }


    }

    @Override
    public void onOnMoreGoodsAddcartClick(View view, String specIds, String specName, float goodsPrice, float goodsPriceVip, int number) {

        if (goodsDetailInfoBean.getGoodsType() == 3) {
//                预售商品
            int goodsNumber = goodsDetailInfoBean.getSpecialSale();
            if (goodsDetailInfoBean.getPresell() == null) {
                return;
            }
            if (goodsDetailInfoBean.getPresell().getLimitNum() != 0 && goodsNumber >= goodsDetailInfoBean.getPresell().getLimitNum()) {
                SnackbarUtil.showToast(rootView, "此商品限购" + goodsDetailInfoBean.getPresell().getLimitNum() + "份");
                return;
            }
            if (databaseGoodsInfo != null && databaseGoodsInfo.getGoodsNumber() >= goodsDetailInfoBean.getPresell().getLimitNum() && goodsDetailInfoBean.getPresell().getLimitNum() != 0) {
                SnackbarUtil.showToast(rootView, "添加成功");
                return;
            }
            if(databaseGoodsInfo != null && databaseGoodsInfo.getGoodsNumber() >= goodsDetailInfoBean.getStock()){
                SnackbarUtil.showToast(rootView, "暂无库存");
                return;
            }

        } else {
//                普通商品
            if (goodsDetailInfoBean.getPresell().getStock() <= 0) {
                SnackbarUtil.showToast(rootView, "暂无库存");
                return;
            }
            if (databaseGoodsInfo != null && databaseGoodsInfo.getGoodsNumber() >= goodsDetailInfoBean.getPresell().getStock()) {
                SnackbarUtil.showToast(rootView, "添加成功");
                return;
            }
        }

        //添加购物车
        DatabaseGoodsInfo goodsInfo = new DatabaseGoodsInfo();
//        商品信息
        goodsInfo.setGoodsId(goodsDetailInfoBean.getId() + "");
        goodsInfo.setGoodsName(goodsDetailInfoBean.getGoodsName());
        goodsInfo.setGoodsPrice(goodsPrice);
        goodsInfo.setGoodsPriceVip(goodsPriceVip);
        goodsInfo.setGoodsNumber(number);
        goodsInfo.setGoodsUrl(goodsDetailInfoBean.getGoodsImage());
        //规格
        goodsInfo.setSpecId(specIds);
        goodsInfo.setSpecName(specName);
        goodsInfo.setShopId(goodsDetailInfoBean.getShopId() + "");

        //            商品类型
        int goodsType = goodsDetailInfoBean.getGoodsType();
        goodsInfo.setGoodsType(goodsType);
        if (goodsType == 3 && goodsDetailInfoBean.getPresell() != null) {
            goodsInfo.setStartTime(goodsDetailInfoBean.getPresell().getStartTime());
            goodsInfo.setEndTime(goodsDetailInfoBean.getPresell().getEndTime());
            goodsInfo.setGoodsTotal(goodsDetailInfoBean.getPresell().getLimitNum());
            goodsInfo.setStock(goodsDetailInfoBean.getPresell().getStock());
        } else {
            goodsInfo.setGoodsTotal(goodsDetailInfoBean.getStock());
            goodsInfo.setStock(goodsDetailInfoBean.getStock());
        }

        greenDaoAssist.insertGoods(DemoConstant.shopInfoBean.getShopId() + "", goodsInfo, isChange -> DemoConstant.isChangeDatabase = isChange);

        setLaber();

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

    @Override
    public void timerToTimeEvent(List<Integer> list, long time) {
        long shi = time / 3600;
        long fen = time % 3600 / 60;
        long miao = time % 60;
        String textStr = (shi < 10 ? "0" + shi : shi) + "：" + (fen < 10 ? "0" + fen : fen) + "：" + (miao < 10 ? "0" + miao : miao);
        shopDetialFragment.setTextDjsTime(textStr);
        if (time <= 0) {
            initGoodsDetail();
        }
    }

    @Override
    protected void onDestroy() {
        if (timerAssist != null) {
            timerAssist.onDestory();
        }
        super.onDestroy();
    }

}
