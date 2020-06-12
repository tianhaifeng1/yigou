package com.work.doctor.fruits.activity.fragment;

import com.t.databaselib.bean.ShopInfoBean;
import com.t.httplib.yigou.bean.resp.BannerInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsListInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsTypeInfoBean;
import com.t.httplib.yigou.bean.resp.UserInfoBean;
import com.trjx.tbase.mvp.TView;

import java.util.List;

public interface MainOneView extends TView {

    void getUserInfoSuccess(UserInfoBean userInfoBean);

    void getShopInfoSuccess(ShopInfoBean infoBean);

//    void getShoppingCartListDataSuccess(List<CartShopInfoBean> list);
    //今日特卖
    void getJrtmDataSuccess(List<GoodsInfoBean> list);
//    今日推荐
    void getJrtjDataSuccess(List<GoodsInfoBean> list);
//    预售商品
    void getYshouDataSuccess(List<GoodsInfoBean> list);
//    获取banner 数据
    void getBannerDataSuccess(List<BannerInfoBean> list);
    /**
     * 获取商品类型
     */
    void getShopTypeSuccess(List<GoodsTypeInfoBean> list);

    //    获取商品规格数据
    void getGoodsSpecSuccess(GoodsSpecInfoBean infoBean);


    //    添加数据到购物车成功
    void addGoodsToShoppingCartSuccess();

    void getActivityGoodsListSuccess(List<GoodsListInfoBean> list);

}
