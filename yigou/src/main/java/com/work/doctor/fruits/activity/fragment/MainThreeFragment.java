package com.work.doctor.fruits.activity.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.DatabaseShopInfo;
import com.t.databaselib.bean.CartShopInfoBean;
import com.t.databaselib.bean.ReqCartDeleteInfo;
import com.t.databaselib.bean.ShopInfoBean;
import com.t.httplib.yigou.YigouConstant;
import com.trjx.tbase.module.titlemodule.TitleListenter;
import com.trjx.tbase.module.titlemodule.TitleModule;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.SnackbarUtil;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.MainNavActivity;
import com.work.doctor.fruits.activity.adapter.ShopCartOutAdapter;
import com.work.doctor.fruits.activity.address.SelectSHAddressActivity;
import com.work.doctor.fruits.activity.order.GoodsAffirmActivity;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.DemoUtils;
import com.work.doctor.fruits.assist.JgPurchaseBean;
import com.work.doctor.fruits.base.DemoMVPFragment;

import java.util.ArrayList;
import java.util.List;

import cn.jiguang.analytics.android.api.Currency;

public class MainThreeFragment extends DemoMVPFragment<MainThreeView, MainThreePresenter>
        implements MainThreeView, TitleListenter, View.OnClickListener, BaseQuickAdapter.OnItemClickListener,
        BaseQuickAdapter.OnItemChildClickListener {

    private SwipeRefreshLayout swiperefreshlayout;

    private RecyclerView mFmMainThreeRecyclerview;
    private TextView mFmMainThreeEmptyBtn;
    private LinearLayout mFmMainThreeEmptyLl;
    private TextView mFmMainThreeAddressName;
    private TextView mFmMainThreeAddressPhone;
    private RelativeLayout mFmMainThreeAddressRl;

    @Override
    protected int initLayout() {
        return R.layout.fragment_main_three;
    }

    private TitleModule titleModule;

    public MainNavActivity mainNavActivity;

    private ShopCartOutAdapter shopCartOutAdapter;

    private boolean isNoEdit = false;

    private int colors[] = {
            Color.rgb(47, 223, 189),
            Color.rgb(223, 47, 189),
            Color.rgb(189, 223, 47),
            Color.rgb(47, 55, 80)
    };

    @Override
    protected void initFragmentView(View view) {

        mFmMainThreeAddressName = view.findViewById(R.id.fm_main_three_address_name);
        mFmMainThreeAddressPhone = view.findViewById(R.id.fm_main_three_address_phone);
        mFmMainThreeAddressRl = view.findViewById(R.id.fm_main_three_address_rl);

        swiperefreshlayout = view.findViewById(R.id.swiperefreshlayout);

        mFmMainThreeRecyclerview = view.findViewById(R.id.fm_main_three_recyclerview);
        mFmMainThreeEmptyBtn = view.findViewById(R.id.fm_main_three_empty_btn);
        mFmMainThreeEmptyLl = view.findViewById(R.id.fm_main_three_empty_ll);

        mainNavActivity = (MainNavActivity) getActivity();

        titleModule = new TitleModule(activity.context, view);
        titleModule.initTitle("购物车");
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.initTitleMenu(TitleModule.MENU_TEXT, "编辑");
        titleModule.setMenuTextColor(R.color.color_white);
        titleModule.setTitleBackground(R.color.transparent);
        titleModule.setListenter(this);
        titleModule.setTitleBottomLineShow(false);

        mFmMainThreeEmptyLl.setOnClickListener(this);
        mFmMainThreeAddressRl.setOnClickListener(this);
        mFmMainThreeEmptyBtn.setOnClickListener(this);

        swiperefreshlayout.setColorSchemeColors(colors);
        swiperefreshlayout.setOnRefreshListener(() -> {
            initData();
        });

        mFmMainThreeRecyclerview.setLayoutManager(new LinearLayoutManager(activity.context));
        shopCartOutAdapter = new ShopCartOutAdapter(this, null);
        //添加条目点击事件
        shopCartOutAdapter.setOnItemClickListener(this);
        shopCartOutAdapter.setOnItemChildClickListener(this);
        mFmMainThreeRecyclerview.setAdapter(shopCartOutAdapter);

    }

    @Override
    public void initData() {
        super.initData();
        if (swiperefreshlayout != null) {
            swiperefreshlayout.setRefreshing(false);
        }
        try{
            if (DemoConstant.addressInfoBean != null) {
                mainNavActivity.name = DemoUtils.showAddressDetail(DemoConstant.addressInfoBean.getAddress() + DemoConstant.addressInfoBean.getDetailAddress());
                String address = DemoUtils.changeAddressDetail(mainNavActivity.getprovince,
                        mainNavActivity.getcity,
                        mainNavActivity.getcounty,
                        mainNavActivity.name);

                mFmMainThreeAddressName.setText(address);
                String addressName = DemoConstant.addressInfoBean.getName();
                String addressPhone = DemoConstant.addressInfoBean.getPhone();
                if (addressName == null || addressName.equals("")) {
                    mFmMainThreeAddressPhone.setVisibility(View.GONE);
                } else {
                    mFmMainThreeAddressPhone.setVisibility(View.VISIBLE);
                    mFmMainThreeAddressPhone.setText(addressName + "  " + addressPhone);
                }
            } else {

                String address = DemoUtils.changeAddressDetail(mainNavActivity.getprovince,
                        mainNavActivity.getcity,
                        mainNavActivity.getcounty,
                        mainNavActivity.name);

                mFmMainThreeAddressName.setText(address);
                mFmMainThreeAddressPhone.setVisibility(View.GONE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //获取购物车数据：可以保证多端数据同步
        if (YigouConstant.token.equals("") && DemoConstant.shopInfoBean != null) {
            //未登录直接查询
            initDataShoppingCart(mainNavActivity.greenDaoAssist.queryShoppingCartData(DemoConstant.shopInfoBean.getShopId() + ""));
        } else {
            //登陆状态
            getPresenter().getShoppingCartListData();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(DemoConstant.isBuyShoppingCartGoods){
            DemoConstant.isBuyShoppingCartGoods = false;
            //涮新
            if (DemoConstant.shopInfoBean != null) {
                initDataShoppingCart(mainNavActivity.greenDaoAssist.queryShoppingCartData(DemoConstant.shopInfoBean.getShopId()+""));
            }
        }
    }

    private void initDataShoppingCart(List<DatabaseShopInfo> shopInfoList) {

        if (shopInfoList == null || shopInfoList.size() == 0) {
            mFmMainThreeEmptyLl.setVisibility(View.VISIBLE);
            titleModule.setMenuVisibility(TitleModule.MENU_NONE);
        } else {
            mFmMainThreeEmptyLl.setVisibility(View.GONE);
            titleModule.setMenuVisibility(TitleModule.MENU_TEXT);
            shopCartOutAdapter.setNewData(shopInfoList);
        }
    }


    @Override
    protected MainThreePresenter initPersenter() {
        return new MainThreePresenter(this);
    }

    @Override
    public void onClickBack(View view) {

    }

    @Override
    public void onClickLeftText(View view) {

    }

    @Override
    public void onClickRightText(View view) {
        if (!isNoEdit) {
            titleModule.setMenuText("完成");
        } else {
            titleModule.setMenuText("编辑");
        }
        isNoEdit = !isNoEdit;
        shopCartOutAdapter.setEdit(isNoEdit);
        shopCartOutAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickMenu(View view) {

    }

    @Override
    public void onMenuItemClick(int position) {

    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.fm_main_three_empty_ll:
                break;
            case R.id.fm_main_three_address_rl:
                if (DemoUtils.isFastClick()) {
                    startActivityForResult(new Intent(activity.context, SelectSHAddressActivity.class), 100);
                }
                break;
            case R.id.fm_main_three_empty_btn:
//                ToastUtil2.showToast(activity.context, "去逛逛");
                mainNavActivity.skipTab(1);
                break;
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ArrayList<DatabaseShopInfo> infoList = (ArrayList<DatabaseShopInfo>) adapter.getData();
        int ids = view.getId();
        switch (ids) {

            case R.id.item_shopcart_out_event_checkout:
//                Logger.t("购买");
                //购买成功，删除购物车数据
//                Logger.t("购买结算");

                //登陆
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }

                panduanSubmit(infoList, position);

                break;
            case R.id.item_shopcart_out_event_delete:
                Logger.t("删除");
                ReqCartDeleteInfo deleteInfo = mainNavActivity.greenDaoAssist.deleteGoods(infoList, position, isChange ->DemoConstant.isChangeDatabase = isChange);
                //------------删除
                getPresenter().cartDelete(deleteInfo);

                //-------------------

                titleModule.setMenuText("编辑");
                isNoEdit = false;
                shopCartOutAdapter.setEdit(isNoEdit);
                shopCartOutAdapter.refresh();

                if (infoList.size() == 0) {
                    mFmMainThreeEmptyLl.setVisibility(View.VISIBLE);
                    titleModule.setMenuVisibility(TitleModule.MENU_NONE);
                }
                break;
            case R.id.item_shopcart_out_event_selectall:
            case R.id.item_shopcart_out_event_selectall2:
                Logger.t("选择所有");
                DatabaseShopInfo shopInfo = infoList.get(position);
                boolean isSelect = shopInfo.getIsAllSelect();
                shopInfo.setIsAllSelect(!isSelect);
                mainNavActivity.greenDaoAssist.updateShopSelectState(shopInfo);
                adapter.notifyItemChanged(position);
                break;
        }
    }

    private void panduanSubmit(ArrayList<DatabaseShopInfo> infoList, int position) {
        ArrayList<DatabaseGoodsInfo> goodsInfoAll = new ArrayList<>();
        DatabaseShopInfo databaseShopInfo = infoList.get(position);
        if (databaseShopInfo.getIsAllSelect()) {
            goodsInfoAll.addAll(databaseShopInfo.getGoodsInfoList());
        } else {
            ArrayList<DatabaseGoodsInfo> goodsInfos = databaseShopInfo.getGoodsInfoList();
            for (int i = 0; i < goodsInfos.size(); i++) {
                DatabaseGoodsInfo goodsInfo = goodsInfos.get(i);
                if (goodsInfos.get(i).getIsSelect()) {
                    goodsInfoAll.add(goodsInfo);
                }
            }
        }

        int size = goodsInfoAll.size();
        if (size > 0) {
            //判断商品数量
            List<String> stringList = new ArrayList<>();
            DemoConstant.jgPurchaseBeanList.clear();
            for (int i = 0; i < size; i++) {
                DatabaseGoodsInfo goodsInfo = goodsInfoAll.get(i);
                if (goodsInfo.getGoodsTotal() < goodsInfo.getGoodsNumber()) {
                    SnackbarUtil.showToast(rootView, goodsInfo.getGoodsName() + "  库存不足");
                    return;
                }
                if(goodsInfo.getIsTemai() == 0){
                    String goodsId = goodsInfo.getGoodsId();
                    for (int j = 0, size2 = stringList.size(); j < size2; j++) {
                        if (goodsId.equals(stringList.get(j))) {
                            SnackbarUtil.showToast(rootView, goodsInfo.getGoodsName() + "  只能购买一件");
                            return;
                        }
                    }
                    stringList.add(goodsId);
                }

                //统计数据
                JgPurchaseBean bean = new JgPurchaseBean();
                bean.setPurchaseGoodsid(goodsInfo.getGoodsId() + "");
                bean.setPurchaseGoodsName(goodsInfo.getGoodsName());
                bean.setPurchasePrice(goodsInfo.getGoodsPrice());
                bean.setPurchaseSuccess(true);
                bean.setPurchaseGoodsCount(goodsInfo.getGoodsNumber());
                bean.setPurchaseGoodsType(goodsInfo.getCnname());
                bean.setPurchaseCurrency(Currency.CNY);
                DemoConstant.jgPurchaseBeanList.add(bean);

            }

            Intent intent = new Intent(activity.context, GoodsAffirmActivity.class);
            intent.putExtra("code", 2);
            intent.putExtra("shop_list", infoList);
            intent.putExtra("shop_index", position);
            intent.putExtra("goods_list", goodsInfoAll);
            intent.putExtra("shopid", infoList.get(position).getShopId());
            activity.skipActivity(intent);
        }

    }

    @Override
    public void getShoppingCartListDataSuccess(List<CartShopInfoBean> list) {
        initDataShoppingCart(mainNavActivity.greenDaoAssist.updateGoodsCartDatabaseData(DemoConstant.shopInfoBean.getShopId() + "", list));
    }

    @Override
    public void getShoppingCartListDataFail() {
        initDataShoppingCart(null);
    }

    @Override
    public void getShopInfoSuccess(ShopInfoBean infoBean) {
        if (infoBean == null) {
            return;
        }/*else{
            mFmMainThreeAddressName.setText(infoBean.getShopAddress() + infoBean.getShopIntro());
            mFmMainThreeAddressPhone.setText(infoBean.getShopName() + "  " + infoBean.getShopPhone());
        }*/

        if (DemoConstant.shopInfoBean == null || DemoConstant.shopInfoBean.getShopId() != infoBean.getShopId()) {
            //店铺以改变
            DemoConstant.isChangeShop = true;
            DemoConstant.shopInfoBean = infoBean;
            DemoConstant.refershOne = true;
            DemoConstant.refershTwo = true;
            DemoConstant.refershThree = false;

            //添加店铺
            mainNavActivity.greenDaoAssist.insertShop(DemoConstant.shopInfoBean);

            initDataShoppingCart(mainNavActivity.greenDaoAssist.queryShoppingCartData(DemoConstant.shopInfoBean.getShopId()+""));

        } else {
            //店铺未改变
            DemoConstant.isChangeShop = false;
        }
    }

    @Override
    public void cartDeleteSuccess() {
        tRemind("删除成功");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == 601) {
                if (data != null) {
                    String addrStr = data.getStringExtra("name");
                    mainNavActivity.getprovince = data.getStringExtra("sheng");
                    mainNavActivity.getcity = data.getStringExtra("shi");
                    mainNavActivity.getcounty = data.getStringExtra("qu");
                    mainNavActivity.la = data.getDoubleExtra("la", 0);
                    mainNavActivity.lo = data.getDoubleExtra("lo", 0);

                    if (DemoConstant.addressInfoBean != null) {

                        mainNavActivity.name = DemoUtils.showAddressDetail(DemoConstant.addressInfoBean.getAddress() + DemoConstant.addressInfoBean.getDetailAddress());

                        String address = DemoUtils.changeAddressDetail(mainNavActivity.getprovince,
                                mainNavActivity.getcity,
                                mainNavActivity.getcounty,
                                mainNavActivity.name);

                        mFmMainThreeAddressName.setText(address);
                        String addressName = DemoConstant.addressInfoBean.getName();
                        String addressPhone = DemoConstant.addressInfoBean.getPhone();
                        if (addressName == null || addressName.equals("")) {
                            mFmMainThreeAddressPhone.setVisibility(View.GONE);
                        } else {
                            mFmMainThreeAddressPhone.setVisibility(View.VISIBLE);
                            mFmMainThreeAddressPhone.setText(addressName + "  " + addressPhone);
                        }
                    } else {

                        mainNavActivity.name = DemoUtils.showAddressDetail(addrStr);
                        String address = DemoUtils.changeAddressDetail(mainNavActivity.getprovince,
                                mainNavActivity.getcity,
                                mainNavActivity.getcounty,
                                mainNavActivity.name);
                        mFmMainThreeAddressName.setText( address);
                        mFmMainThreeAddressPhone.setVisibility(View.GONE);
                    }

                    getPresenter().getShopInfo(mainNavActivity.la, mainNavActivity.lo);

                }
            }
        }
    }

}