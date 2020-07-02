package com.work.doctor.fruits.activity.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.baidu.location.BDLocation;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnPageChangeListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.bean.ShopInfoBean;
import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.resp.BannerInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsListInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsTypeInfoBean;
import com.t.httplib.yigou.bean.resp.UserInfoBean;
import com.trjx.tlibs.uils.GlideUtile;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.SharedPreferencesUtils;
import com.trjx.tlibs.uils.SnackbarUtil;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.ActivityTimerAssist;
import com.work.doctor.fruits.activity.Goods.CategoryGoodsListActivity;
import com.work.doctor.fruits.activity.Goods.GoodsDetial2Activity;
import com.work.doctor.fruits.activity.Goods.GoodsDetial3Activity;
import com.work.doctor.fruits.activity.Goods.GoodsListMOMoreActivity;
import com.work.doctor.fruits.activity.Goods.GoodsListYsActivity;
import com.work.doctor.fruits.activity.Goods.GoodsTypeAndListActivity;
import com.work.doctor.fruits.activity.MainNavActivity;
import com.work.doctor.fruits.activity.SigninActivity;
import com.work.doctor.fruits.activity.adapter.GoodListAdapter;
import com.work.doctor.fruits.activity.adapter.ShopTypeAdapter;
import com.work.doctor.fruits.activity.adapter.ShoppingAdapter;
import com.work.doctor.fruits.activity.adapter.ShoppingJrtmAdapter;
import com.work.doctor.fruits.activity.adapter.ShoppingYsAdapter;
import com.work.doctor.fruits.activity.address.SelectSHAddressActivity;
import com.work.doctor.fruits.activity.search.SearchGoodsActivity;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.DemoUtils;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.base.DemoMVPFragment;
import com.work.doctor.fruits.dialog.CommonPopupWindow;
import com.work.doctor.fruits.dialog.KefuDialog;
import com.work.doctor.fruits.dialog.LocationDialog;
import com.work.doctor.fruits.dialog.MoreGoodsSpecInfoDialog2;
import com.work.doctor.fruits.dialog.RemindDialog;
import com.xzte.maplib.baidu.TMapInfoListener;

import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainOneFragment extends DemoMVPFragment<MainOneView, MainOnePresenter>
        implements MainOneView, View.OnClickListener, TMapInfoListener, MoreGoodsSpecInfoDialog2.OnMoreGoodsListSpecInfoListener, ActivityTimerAssist.TimerToTimeListener, CommonPopupWindow.ViewInterface {

    private MainNavActivity mainNavActivity;
    private DemoApplication demoApplication;

    private int colors[] = {
            Color.rgb(47, 223, 189),
            Color.rgb(223, 47, 189),
            Color.rgb(189, 223, 47),
            Color.rgb(47, 55, 80)
    };

    private FloatingActionButton mFmOneFb;

    private TextView topLocationView;
    private ImageView topLocationImg;
    private ImageView topCart;
    private RelativeLayout topSearchRl;

    private SwipeRefreshLayout swipeRefreshLayout;

    private ConvenientBanner banner;

    private LinearLayout mFmOneMoreJrtmTitleLl;
    private RecyclerView mFmOneRecyclerviewJrtm;
    private RecyclerView mFmOneRecyclerviewJrtj;

    private RecyclerView mFmOneRecyclerviewType;

    private RecyclerView mFmOneRecyclerviewGoodlist;

    private LinearLayout mFmOneYslayout;
    private RecyclerView mFmOneRecyclerviewYs;
    private TextView mFmOneMoreYs;
    private LinearLayout mFmOneLinGoodlist;
    private LinearLayout mFmoneBottomTitleXinpin;

    private GoodsInfoBean goodsInfoBean;
    private DatabaseGoodsInfo databaseGoodsInfo;
    private ActivityTimerAssist timerAssist;

    private CommonPopupWindow popupWindow;


    @Override
    protected int initLayout() {
        return R.layout.fragment_main_one;
    }


    @Override
    protected void initFragmentView(View view) {
        mainNavActivity = (MainNavActivity) getActivity();

        demoApplication = (DemoApplication) getActivity().getApplication();
        demoApplication.applicationAssist.setTMapInfoListener(this);
        demoApplication.applicationAssist.setScanSpan(1);//手动定位

        timerAssist = new ActivityTimerAssist();
        timerAssist.setOnTimerToTimeListener(this);

        mFmOneFb = view.findViewById(R.id.fm_one_fb);

        topLocationImg = view.findViewById(R.id.main_top_locationimg);
        topLocationView = view.findViewById(R.id.main_top_locationname);
        topSearchRl = view.findViewById(R.id.main_top_search);
        topCart = view.findViewById(R.id.main_top_cart);
        swipeRefreshLayout = view.findViewById(R.id.fm_one_swiperefreshlayout);
        banner = view.findViewById(R.id.fm_one_banner);

        mFmOneMoreJrtmTitleLl = view.findViewById(R.id.main_frist_jrtm_title_ll);
        mFmOneRecyclerviewJrtm = view.findViewById(R.id.fm_one_recyclerview_jrtm);
        mFmOneRecyclerviewJrtj = view.findViewById(R.id.fm_one_recyclerview_jrtj);

        mFmOneRecyclerviewType = view.findViewById(R.id.fm_one_recyclerview_type);

        mFmOneYslayout = view.findViewById(R.id.fm_one_yslayout);
        mFmOneRecyclerviewYs = view.findViewById(R.id.fm_one_recyclerview_ys);
        mFmOneMoreYs = view.findViewById(R.id.fm_one_more_ys);

        mFmOneRecyclerviewGoodlist = view.findViewById(R.id.fm_one_recyclerview_goodlist);
        mFmOneLinGoodlist = view.findViewById(R.id.fm_one_lin_goodlist);
        mFmoneBottomTitleXinpin = view.findViewById(R.id.main_bottom_title_xinpin);

        topLocationImg.setVisibility(View.VISIBLE);
        topLocationView.setVisibility(View.VISIBLE);
        topCart.setVisibility(View.VISIBLE);

        mFmOneFb.setOnClickListener(this);
        topCart.setOnClickListener(this);
        topLocationImg.setOnClickListener(this);
        topLocationView.setOnClickListener(this);
        topSearchRl.setOnClickListener(this);
        mFmOneMoreJrtmTitleLl.setOnClickListener(this);
        mFmOneMoreYs.setOnClickListener(this);
        mFmoneBottomTitleXinpin.setOnClickListener(this);

        initAdapter();

        swipeRefreshLayout.setColorSchemeColors(colors);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> reInitData());

        if (!YigouConstant.token.equals("")) {
            getPresenter().getUserInfo();
        }

        MainOneFragmentPermissionsDispatcher.getPremission_locationWithPermissionCheck(this);
    }

    @Override
    public void initData() {
        super.initData();
        try{
            topLocationView.setText(DemoUtils.showAddressDetail2(mainNavActivity.getprovince, mainNavActivity.name));
        }catch (Exception e){
            e.printStackTrace();
        }
        //店铺以改变
        if (DemoConstant.refershOne) {
            pdRefershPage();
            reInitData();
        }
    }

    private void reInitData() {
        if (mainNavActivity.la > 0 && mainNavActivity.lo > 0) {
            if (DemoConstant.shopInfoBean == null || DemoConstant.isRefershShopInfo) {
                DemoConstant.isRefershShopInfo = false;
                //无店铺信息
                activity.showDialog("请求中...");
                getPresenter().getShopInfo(mainNavActivity.la, mainNavActivity.lo);
            } else {
                //有店铺信息
                getPresenter().getBannerData();
                getPresenter().getShopTypeData(DemoConstant.shopInfoBean.getShopId());
                if (DemoUtils.isPfUser()) {
                    mFmOneMoreJrtmTitleLl.setVisibility(View.GONE);
                    mFmOneRecyclerviewJrtm.setVisibility(View.GONE);
                } else {
                    mFmOneMoreJrtmTitleLl.setVisibility(View.VISIBLE);
                    mFmOneRecyclerviewJrtm.setVisibility(View.VISIBLE);
                    getPresenter().getJrtmData();
                }
                getPresenter().getJrtjData();
                getPresenter().getYshouData();
                getPresenter().getActivityGoodsList(DemoConstant.shopInfoBean.getShopId());
            }
        } else {
            //无经纬度
            MainOneFragmentPermissionsDispatcher.getPremission_locationWithPermissionCheck(this);
        }
    }



    private ShoppingYsAdapter adapterys;
    private ShoppingJrtmAdapter adapterJrtm;
    private ShoppingAdapter adapterJrtj;
    private ShopTypeAdapter adapterType;
    private GoodListAdapter adapterActivityList;

    private void initAdapter() {

        mFmOneRecyclerviewYs.setLayoutManager(new LinearLayoutManager(activity.context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        adapterys = new ShoppingYsAdapter(null){
            @Override
            public void addTimer(long time, int position,long currentTime) {
                timerAssist.setTime(time, position,currentTime);
            }
        };
        adapterys.setOnItemClickListener((adapter, view, position) -> {
            GoodsInfoBean infoBean = (GoodsInfoBean) adapter.getData().get(position);
            Intent intent = new Intent(activity.context, GoodsDetial3Activity.class);
            intent.putExtra("index", 0);
            intent.putExtra("id", infoBean.getId());
            activity.skipActivity(intent);
        });
        mFmOneRecyclerviewYs.setAdapter(adapterys);
        adapterys.setOnItemChildClickListener((adapter, view, position) -> {
            int ids = view.getId();
            switch (ids){
                case R.id.item_shop_gmr_ll:
                    //购买人
                    GoodsInfoBean infoBean = (GoodsInfoBean) adapter.getData().get(position);
                    Intent intent = new Intent(activity.context, GoodsDetial3Activity.class);
                    intent.putExtra("index", 1);
                    intent.putExtra("id",infoBean.getId());
                    activity.skipActivity(intent);
                    break;
                case R.id.item_shop_cart:
                    //抢购
                    goodsInfoBean = (GoodsInfoBean) adapter.getData().get(position);
                    databaseGoodsInfo = mainNavActivity.greenDaoAssist.queryGoodsInfo(goodsInfoBean.getId() + "",goodsInfoBean.getShopId()+"","");
                    getPresenter().getGoodsItem(goodsInfoBean.getId());
                    break;
            }

        });

        mFmOneRecyclerviewJrtm.setLayoutManager(new GridLayoutManager(activity.context, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;//禁止垂直滚动
            }
        });
        adapterJrtm = new ShoppingJrtmAdapter(null, true);
        //添加条目点击事件
        adapterJrtm.setOnItemClickListener((adapter, view, position) -> {
            GoodsInfoBean infoBean = (GoodsInfoBean) adapter.getData().get(position);
            Intent intent = new Intent(activity.context, GoodsDetial2Activity.class);
            intent.putExtra("id", infoBean.getId());
            activity.skipActivity(intent);
        });
        mFmOneRecyclerviewJrtm.setAdapter(adapterJrtm);
        adapterJrtm.setOnItemChildClickListener((adapter, view, position) -> {
            goodsInfoBean = (GoodsInfoBean) adapter.getData().get(position);
            databaseGoodsInfo = mainNavActivity.greenDaoAssist.queryGoodsInfo(goodsInfoBean.getId() + "",goodsInfoBean.getShopId()+"","");
            if (goodsInfoBean.getSpecialSale() == 1) {
                RemindDialog remindDialog = new RemindDialog.Builder(activity)
                        .setTitle("提示")
                        .setMessage("亲，此商品每天仅限购一件哦！")
                        .setAffirmText("确认")
                        .setCancelable(true)
                        .create();
                remindDialog.show(MainOneFragment.this.getFragmentManager(), "dialog_remind_xiangou");
                return;
            }
            getPresenter().getGoodsItem(goodsInfoBean.getId());
        });

        mFmOneRecyclerviewJrtj.setLayoutManager(new GridLayoutManager(activity.context, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        adapterJrtj = new ShoppingAdapter(null);
        //添加条目点击事件
        adapterJrtj.setOnItemClickListener((adapter, view, position) -> {
            GoodsInfoBean infoBean = (GoodsInfoBean) adapter.getData().get(position);
            Intent intent = new Intent(activity.context, GoodsDetial2Activity.class);
            intent.putExtra("id", infoBean.getId());
            activity.skipActivity(intent);
        });
        mFmOneRecyclerviewJrtj.setAdapter(adapterJrtj);
        adapterJrtj.setOnItemChildClickListener((adapter, view, position) -> {
            goodsInfoBean = (GoodsInfoBean) adapter.getData().get(position);
            databaseGoodsInfo = mainNavActivity.greenDaoAssist.queryGoodsInfo(goodsInfoBean.getId() + "",goodsInfoBean.getShopId()+"","");
            getPresenter().getGoodsItem(goodsInfoBean.getId());
        });

        mFmOneRecyclerviewType.setLayoutManager(new GridLayoutManager(activity.context, 5) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        adapterType = new ShopTypeAdapter(null);
        //添加条目点击事件
        adapterType.setOnItemClickListener((adapter, view, position) -> {
//            GoodsTypeInfoBean infoBean = (GoodsTypeInfoBean) adapter.getData().get(position);
//            String cnname = infoBean.getCnname();
//            if (cnname != null && cnname.equals("暂未开放")) {
//                return;
//            }
//            Intent intent = new Intent(activity, GoodsListActivity.class);
//            intent.putExtra("id", infoBean.getId());
//            activity.skipActivity(intent);

            ArrayList<GoodsTypeInfoBean> infoBeanArrayList = (ArrayList<GoodsTypeInfoBean>) adapter.getData();
            Intent intent2 = new Intent(activity, GoodsTypeAndListActivity.class);
            intent2.putExtra("list", infoBeanArrayList);
            intent2.putExtra("index", position);
            activity.skipActivity(intent2);

        });
        mFmOneRecyclerviewType.setAdapter(adapterType);

        mFmOneRecyclerviewGoodlist.setLayoutManager(new LinearLayoutManager(activity.context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        adapterActivityList = new GoodListAdapter(null);
//        adapterActivityList.setOnItemClickListener((adapter, view, position) -> {
//
//            GoodsInfoBean infoBean = (GoodsInfoBean) adapter.getData().get(position);
//            Intent intent = new Intent(activity.context, GoodsDetial2Activity.class);
//            intent.putExtra("id", infoBean.getId());
//            activity.skipActivity(intent);
//
//        });
        adapterActivityList.setOnItemChildClickListener((adapter, view, position) -> {
            int ids = view.getId();
            switch (ids){
                case R.id.item_shop_cart:
                    //购物车
                    goodsInfoBean= (GoodsInfoBean) adapter.getData().get(position);
                    getPresenter().getGoodsItem(goodsInfoBean.getId());
                    break;
                case R.id.item_activity_image:
                    GoodsListInfoBean infoBean = (GoodsListInfoBean) adapter.getData().get(position);
                    if(infoBean.getActivityCategoryNum()==0){
                        SnackbarUtil.showToast(rootView, "该分类没有更多");
                        break;
                    }
                    Intent intent2 = new Intent(activity, CategoryGoodsListActivity.class);
                    intent2.putExtra("activityId", infoBean.getId());
                    intent2.putExtra("activityIcon",infoBean.getActivityIcon());
                    intent2.putExtra("activityName",infoBean.getActivityName());
                    activity.skipActivity(intent2);
                    break;
            }

        });
        mFmOneRecyclerviewGoodlist.setAdapter(adapterActivityList);

    }


    @Override
    protected MainOnePresenter initPersenter() {
        return new MainOnePresenter(this);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.fm_one_fb:
                MainOneFragmentPermissionsDispatcher.getPremission_call_phoneWithPermissionCheck(this);
                break;
            case R.id.main_top_cart:
                mainNavActivity.skipTab(2);
                break;
            case R.id.main_top_locationimg:
            case R.id.main_top_locationname:
                //选择地址
                startActivityForResult(new Intent(activity.context, SelectSHAddressActivity.class), 100);
                break;
            case R.id.main_top_search:
                //搜索
                activity.skipActivity(SearchGoodsActivity.class);
                break;
            case R.id.main_frist_jrtm_title_ll:
                //今日特卖
                Intent intent_jrtm = new Intent(activity.context, GoodsListMOMoreActivity.class);
                intent_jrtm.putExtra("code", 0);
                activity.startActivity(intent_jrtm);
                break;
            case R.id.main_bottom_title_xinpin:
                //今日推荐
                Intent intent_jrtj = new Intent(activity.context, GoodsListMOMoreActivity.class);
                intent_jrtj.putExtra("code", 1);
                activity.startActivity(intent_jrtj);
                break;
            case R.id.fm_one_more_ys:
                //今日推荐
                Intent intent_ys = new Intent(activity.context, GoodsListYsActivity.class);
                activity.startActivity(intent_ys);
                break;
        }
    }

    @NeedsPermission({Manifest.permission.CALL_PHONE})
    void getPremission_call_phone() {


        new KefuDialog.Builder(activity.context)
                .setCancelable(false)
                .setOnKefuDialogClickListener(view -> {
                    try {
                        if (DemoConstant.shopInfoBean.getAftersalePhone() != null
                                && !DemoConstant.shopInfoBean.getAftersalePhone().equals("")) {
                            //我们需要告诉系统，我们的动作：我要打电话
                            //创建意图对象
                            Intent intent = new Intent();
                            //  直接打电话的动作
                            //intent.setAction(Intent.ACTION_CALL);
                            //  跳转到拨号界面
                            intent.setAction(Intent.ACTION_DIAL);
                            //设置打给谁
                            intent.setData(Uri.parse("tel:" + DemoConstant.shopInfoBean.getAftersalePhone()));//这个tel：必须要加上，表示我要打电话。否则不会有打电话功能，由于在打电话清单文件里设置了这个“协议”
                            //把动作告诉系统,启动系统打电话功能。
                            startActivity(intent);
                        } else {
                            Logger.t("客服热线异常");
                            SnackbarUtil.showToast(rootView, "暂无客服");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Logger.t("数据异常");
                    }
                })
                .create().show(getFragmentManager(), "dialog_kefu");


    }

    @Override
    public void getUserInfoSuccess(UserInfoBean userInfoBean) {
        DemoConstant.balance = userInfoBean.getBalance();
        DemoConstant.userStatus = userInfoBean.getStatus();
    }

    @Override
    public void getShopInfoSuccess(ShopInfoBean infoBean) {
        if (infoBean == null) {
            return;
        }

        if (DemoConstant.shopInfoBean == null || DemoConstant.shopInfoBean.getShopId() != infoBean.getShopId()) {
            //店铺改变
            DemoConstant.isChangeShop = true;
            DemoConstant.shopInfoBean = infoBean;
            DemoConstant.refershOne = false;
            DemoConstant.refershTwo = true;
            DemoConstant.refershThree = true;

            pdRefershPage();
            //添加店铺
            mainNavActivity.greenDaoAssist.insertShop(DemoConstant.shopInfoBean);

            //重新初始化数据
            reInitData();


        } else {
            //店铺未改变
            DemoConstant.isChangeShop = false;
        }


//        //如果是批发用户，不用提示框
//        if (DemoUtils.isPfUser()) {
//            return;
//        }

        if(DemoConstant.isSignin==false&&infoBean.getSignin()==0){
            DemoConstant.isSignin=true;
            showPopwSignin();
            return;
        }else{

            showLocationDialog();
        }
    }

    private void showLocationDialog() {
        boolean isRemind = (boolean) SharedPreferencesUtils.getParam(activity.context, DemoConstant.user_onepage_dialog_remind, true);


        if (isRemind && locationDialog == null) {
            String address = DemoUtils.changeAddressDetail(mainNavActivity.getprovince,
                    mainNavActivity.getcity,
                    mainNavActivity.getcounty,
                    mainNavActivity.name);
            locationDialog = new LocationDialog.Builder(activity.context)
                    .setAddress(DemoConstant.shopInfoBean.getShopIntro())
                    .setAddressDetail(address)
                    .setCancelable(false)
                    .setOnLocationDialogClickListener(view -> {
                        //选择地址
                        startActivityForResult(new Intent(activity.context, SelectSHAddressActivity.class), 100);
                    })
                    .create();
            locationDialog.show(getFragmentManager(), "dialog_location_change");
        }
    }

    private void showPopwSignin() {
        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(getContext()).inflate(R.layout.popw_main_signin, null);

        //测量View的宽高
        DemoUtils.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(getContext())
                .setView(R.layout.popw_main_signin)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                // .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗   此方法无用 必须重写
                .setAnimationStyle(R.style.MyPopupWindow_anim_style)
                .setViewOnclickListener(this)
                .create();
        // 产生背景变暗效果，设置透明度  必须重写 否则背景无法变暗
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.2f;
        //之前不写这一句也是可以实现的，这次突然没效果了。网搜之后加上了这一句就好了。据说是因为popUpWindow没有getWindow()方法。
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
        popupWindow.showAtLocation(getActivity().findViewById(android.R.id.content), Gravity.CENTER, 0, 0);
    }

    @Override
    public void getShopTypeSuccess(List<GoodsTypeInfoBean> list) {
        int row = 0;
        if (list != null) {
            row = (int) Math.ceil(list.size() / 5.0f);
        }
        adapterType.setNewData(list);
        adapterType.loadMoreEnd(true);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mFmOneRecyclerviewType.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = activity.context.getResources().getDimensionPixelOffset(R.dimen.dp90) * row;
        mFmOneRecyclerviewType.setLayoutParams(layoutParams);
    }

    @Override
    public void getGoodsSpecSuccess(GoodsSpecInfoBean infoBean) {
        if (infoBean != null && infoBean.getGoodsItemList() != null && infoBean.getGoodsItemList().size() > 0) {
            // 有规格
            goodsItemDialog(infoBean);
        } else {

            if(goodsInfoBean.getGoodsType() == 3){
//                预售商品
                int goodsNumber = goodsInfoBean.getSpecialSale();
                if (goodsInfoBean.getLimitNum()!=0 && goodsNumber >= goodsInfoBean.getLimitNum()) {
                    SnackbarUtil.showToast(rootView, "此商品限购" + goodsInfoBean.getLimitNum() + "份");
                    return;
                }
                if (databaseGoodsInfo != null && databaseGoodsInfo.getGoodsNumber() >= goodsInfoBean.getLimitNum() && goodsInfoBean.getLimitNum()!=0) {
                    SnackbarUtil.showToast(rootView, "添加成功");
                    return;
                }
                if(databaseGoodsInfo != null && databaseGoodsInfo.getGoodsNumber() > goodsInfoBean.getStock()){
                    SnackbarUtil.showToast(rootView, "添加成功");
                    return;
                }
//                if(databaseGoodsInfo.get)

            }else{
//                普通商品
                if (goodsInfoBean.getStock() <= 0) {
                    SnackbarUtil.showToast(rootView, "暂无库存");
                    return;
                }
                if (databaseGoodsInfo != null && databaseGoodsInfo.getGoodsNumber() > goodsInfoBean.getStock()) {
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
            goodsInfo.setGoodsPrice(goodsInfoBean.getSellPrice());
            goodsInfo.setGoodsPriceVip(goodsInfoBean.getSellPriceDiscount());
            goodsInfo.setGoodsNumber(1);
            goodsInfo.setGoodsUrl(goodsInfoBean.getGoodsImage());

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
            goodsInfo.setStock(goodsInfoBean.getStock());

            mainNavActivity.greenDaoAssist.insertGoods(DemoConstant.shopInfoBean.getShopId() + "", goodsInfo, isChange -> DemoConstant.isChangeDatabase = isChange);

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
//        SnackbarUtil.showToast(activity.context, "添加成功");
    }

    @Override
    public void getActivityGoodsListSuccess(List<GoodsListInfoBean> list) {

        if(list!=null){
            mFmOneRecyclerviewGoodlist.setVisibility(View.VISIBLE);
            mFmOneLinGoodlist.setVisibility(View.VISIBLE);
            adapterActivityList.setNewData(list);
        }else{
            mFmOneRecyclerviewGoodlist.setVisibility(View.GONE);
            mFmOneLinGoodlist.setVisibility(View.GONE);
        }

    }

    @Override
    public void getJrtmDataSuccess(List<GoodsInfoBean> list) {
        int row = 0;
        if (list != null && list.size() > 0) {
            Logger.t("Jrtm_size = " + list.size());
            row = (int) Math.ceil(list.size() / 3.0f);
            for (int i = 0, size = list.size(); i < size; i++) {
                GoodsInfoBean listBean = list.get(i);
                //更改库存
                if (listBean.getIsTemai() == 0) {
                    if (listBean.getSpecialSale() == 0 && listBean.getStock() > 0) {
                        listBean.setStock(1);
                    } else {
                        listBean.setStock(0);
                    }
                }
            }
        }

        Logger.t("Jrtm_row = " + row);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mFmOneRecyclerviewJrtm.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = activity.context.getResources().getDimensionPixelOffset(R.dimen.dp180) * row;
        mFmOneRecyclerviewJrtm.setLayoutParams(layoutParams);
        adapterJrtm.setNewData(list);
        adapterJrtm.loadMoreEnd(true);
    }

    @Override
    public void getJrtjDataSuccess(List<GoodsInfoBean> list) {
        int row = 0;
        if (list != null && list.size() > 0) {
            Logger.t("Jrtj_size = " + list.size());
            row = (int) Math.ceil(list.size() / 3.0f);
        }
        Logger.t("Jrtj_row = " + row);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mFmOneRecyclerviewJrtj.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = activity.context.getResources().getDimensionPixelOffset(R.dimen.dp180) * row;
        mFmOneRecyclerviewJrtj.setLayoutParams(layoutParams);
        adapterJrtj.setNewData(list);
        adapterJrtj.loadMoreEnd(true);
    }

    @Override
    public void getYshouDataSuccess(List<GoodsInfoBean> list) {
        if (list == null || list.size() == 0) {
            mFmOneYslayout.setVisibility(View.GONE);
            return;
        }
        mFmOneYslayout.setVisibility(View.VISIBLE);
        int row = list.size();
        Logger.t("ys_size = " + row);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mFmOneRecyclerviewYs.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = activity.context.getResources().getDimensionPixelOffset(R.dimen.dp180) * row;
        mFmOneRecyclerviewYs.setLayoutParams(layoutParams);
        adapterys.setNewData(list);
        adapterys.loadMoreEnd(true);
    }

    @Override
    public void tPostFinish(int code) {
        super.tPostFinish(code);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void getBannerDataSuccess(List<BannerInfoBean> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public NetworkImageHolderView createHolder(View itemView) {
                return new NetworkImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_banner;
            }
        }, list)
                .setCanLoop(true)//默认循环
                .setOnItemClickListener(position -> {
//                    BannerInfoBean infoBean = list.get(position);
//                    String goodsId = infoBean.getGoodId();
//                    if (goodsId != null && !goodsId.equals("")) {
//                        Intent intent = new Intent(activity.context, GoodsDetial2Activity.class);
//                        intent.putExtra("id", Integer.parseInt(goodsId));
//                        activity.skipActivity(intent);
//                    } else {
//                        String path = infoBean.getLink();
//                        if (path != null && !path.equals("") && path.startsWith("http")) {
//                            Intent intent = new Intent(activity.context, DemoWebActivity.class);
//                            intent.putExtra("code", 0);
//                            intent.putExtra("path", path);
//                            intent.putExtra("title", "儒龙易购");
//                            activity.skipActivity(intent);
//                        }
//                    }
                }).setFirstItemPos(0)
//                .setCurrentItem(0,false)
                .setPageIndicator(new int[]{R.mipmap.dian_, R.mipmap.dian})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnPageChangeListener(new OnPageChangeListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    }

                    @Override
                    public void onPageSelected(int index) {

                    }
                });
        banner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 20);
            }
        });
        banner.setClipToOutline(true);
    }

    private LocationDialog locationDialog;

    @Override
    public void bdSuccess(BDLocation aMapLocation) {
        Logger.t("定位成功" + aMapLocation.getCity());

        String addrStr = aMapLocation.getAddrStr();

        mainNavActivity.name = DemoUtils.showAddressDetail(addrStr);
        topLocationView.setText(DemoUtils.showAddressDetail2(aMapLocation.getProvince(), addrStr));
        mainNavActivity.getprovince = aMapLocation.getProvince();
        mainNavActivity.getcity = aMapLocation.getCity();
        mainNavActivity.getcounty = aMapLocation.getDistrict();
        mainNavActivity.la = aMapLocation.getLatitude();
        mainNavActivity.lo = aMapLocation.getLongitude();

        activity.setDialogText("请求中...");
        getPresenter().getShopInfo(aMapLocation.getLatitude(), aMapLocation.getLongitude());

    }

    @Override
    public void bdFail(String failStr) {
        Logger.t("----------------3");
//        demoApplication.applicationAssist.startLoction();//手动定位
        if (failStr != null && !failStr.equals("")) {
            Logger.t("定位失败" + failStr);
        }
        hideDialog();
        swipeRefreshLayout.setRefreshing(false);
        SnackbarUtil.showToast(rootView, "定位失败,请确认网络和GPS功能是否正常？");
    }

    @Override
    public void tPostError(String errorMsg) {
        super.tPostError(errorMsg);
        swipeRefreshLayout.setRefreshing(false);
    }

    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    void getPremission_location() {
        showDialog("定位中...");
        demoApplication.applicationAssist.startLoction();
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainOneFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private MoreGoodsSpecInfoDialog2 infoDialog;

    //弹框
    private void goodsItemDialog(GoodsSpecInfoBean specListBeanList) {
        if (infoDialog == null || infoDialog.getDialog() == null) {
            infoDialog = new MoreGoodsSpecInfoDialog2.Builder(activity.context)
                    .setCancelable(false)
                    .setGoodsInfo(goodsInfoBean)
                    .setGoodsItemList(specListBeanList)
                    .setOnMoreGoodsListSpecInfoListener(this)
                    .create();
            infoDialog.show(activity.getSupportFragmentManager(), "tishi_itemspec");
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

        mainNavActivity.greenDaoAssist.insertGoods(DemoConstant.shopInfoBean.getShopId() + "", goodsInfo, isChange -> DemoConstant.isChangeDatabase = isChange);

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
    public void timerToTimeEvent(List<Integer> list, long time) {
        if (adapterys != null && list != null && list.size() > 0) {
            for (Integer index : list
            ) {
                List<GoodsInfoBean> infoBeanList = adapterys.getData();
                if (infoBeanList != null && infoBeanList.size() > index) {
                    if (time <= 0) {
                        adapterys.refreshNotifyItemChanged(index);
                    }
                }
            }
        }
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId) {
            case R.layout.popw_main_signin:
                ImageView tvSure = view.findViewById(R.id.popw_main_no);
                tvSure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showLocationDialog();
                        popupWindow.dismiss();
                    }
                });
                LinearLayout toSignin = view.findViewById(R.id.popw_rule_lin);
                toSignin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent2 = new Intent(activity, SigninActivity.class);
                        activity.skipActivity(intent2);
                        showLocationDialog();
                        popupWindow.dismiss();
                    }
                });

                break;

        }

    }

    public class NetworkImageHolderView extends Holder<BannerInfoBean> {

        private ImageView imageView;

        public NetworkImageHolderView(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View itemView) {
            imageView = itemView.findViewById(R.id.imageview);
        }

        @Override
        public void updateUI(BannerInfoBean data) {
            GlideUtile.bindImageView(activity, data.getImageUrl(), R.mipmap.banne_default, imageView);
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == 601) {

                String addrStr = data.getStringExtra("name");
                mainNavActivity.getprovince = data.getStringExtra("sheng");
                mainNavActivity.getcity = data.getStringExtra("shi");
                mainNavActivity.getcounty = data.getStringExtra("qu");
                mainNavActivity.la = data.getDoubleExtra("la", 0);
                mainNavActivity.lo = data.getDoubleExtra("lo", 0);

                mainNavActivity.name = DemoUtils.showAddressDetail(addrStr);
                topLocationView.setText(DemoUtils.showAddressDetail2(mainNavActivity.getprovince, addrStr));

                getPresenter().getShopInfo(mainNavActivity.la, mainNavActivity.lo);
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        activity.changeStateBar(false);
        //开始翻页
        banner.startTurning();
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        banner.stopTurning();
    }

    @Override
    public void onDestroy() {
        if (timerAssist != null) {
            timerAssist.onDestory();
        }
        super.onDestroy();
    }

}
