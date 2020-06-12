package com.work.doctor.fruits.activity.fragment;

import com.t.databaselib.bean.CartShopInfoBean;
import com.t.databaselib.bean.ShopInfoBean;
import com.trjx.tbase.mvp.TView;

import java.util.List;

public interface MainThreeView extends TView {

//    void getShopCartDataSuccess(List<GoodsInfo> list);

    void getShoppingCartListDataSuccess(List<CartShopInfoBean> list);
    void getShoppingCartListDataFail();


    void getShopInfoSuccess(ShopInfoBean infoBean);


    void cartDeleteSuccess();



}
