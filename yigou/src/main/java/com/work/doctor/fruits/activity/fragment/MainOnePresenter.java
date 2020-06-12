package com.work.doctor.fruits.activity.fragment;

import androidx.annotation.NonNull;

import com.t.databaselib.bean.ShopInfoBean;
import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo3;
import com.t.httplib.yigou.bean.req.ReqGoodsListInfo2;
import com.t.httplib.yigou.bean.req.ReqGoodsTypeInfo;
import com.t.httplib.yigou.bean.req.ReqShopInfo;
import com.t.httplib.yigou.bean.req.ReqShopInfo3;
import com.t.httplib.yigou.bean.resp.BannerInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsListInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsTypeInfoBean;
import com.t.httplib.yigou.bean.resp.UserInfoBean;
import com.trjx.tlibs.uils.Logger;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class MainOnePresenter extends DemoPresenter<MainOneView> {

    public MainOnePresenter(@NonNull MainOneView view) {
        super(view);
    }

//    protected void getShoppingCartListData() {
//        if (DemoConstant.shopInfoBean == null) {
//            Logger.t("店铺信息不能为空");
//            return;
//        }
//        ReqShopInfo2 info = new ReqShopInfo2();
//        info.setShopId(DemoConstant.shopInfoBean.getShopId()+"");
//        model.requestCartList(info, new TObserver<DemoRespBean<List<CartShopInfoBean>>>() {
//            @Override
//            public void onNext(DemoRespBean<List<CartShopInfoBean>> bean) {
//                if (responseState(bean)) {
//                    if (isViewAttach()) {
//                        getView().getShoppingCartListDataSuccess(bean.getData());
//                    }
//                }
//            }
//        });
//    }

    //获取用户信息
    protected void getUserInfo(){
        model.requestUserInfo(new TObserver<DemoRespBean<UserInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<UserInfoBean> bean) {
                if (responseState(bean)) {
                    if (isViewAttach()) {
                        getView().getUserInfoSuccess(bean.getData());
                    }
                }
            }
        });
    }

    //获取Banner数据
    protected void getBannerData() {
        if (DemoConstant.shopInfoBean == null) {
            Logger.t("店铺信息不能为空");
            return;
        }
        ReqShopInfo3 info3 = new ReqShopInfo3();
        info3.setShopId(DemoConstant.shopInfoBean.getShopId()+"");
        model.requestBanner(info3,new TObserver<DemoRespBean<List<BannerInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<BannerInfoBean>> bean) {
                if (responseState(bean))
                    if (isViewAttach()) {
                        getView().getBannerDataSuccess(bean.getData());
                    }
            }
        });

    }

    //获取今日特卖数据
    protected void getJrtmData() {
        if (DemoConstant.shopInfoBean == null) {
            Logger.t("店铺信息不能为空");
            return;
        }
        ReqGoodsListInfo2 info = new ReqGoodsListInfo2();
        info.setTypes(0);
        info.setShopId(DemoConstant.shopInfoBean.getShopId());
        info.setPage(1);
        info.setPageSize(6);
        model.requestHomePageGoodsList(info, new TObserver<DemoRespBean<List<GoodsInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<GoodsInfoBean>> bean) {
                if (responseState(bean))
                    if (isViewAttach())
                        getView().getJrtmDataSuccess(bean.getData());
            }

        });

    }

    //获取今日推荐数据
    protected void getJrtjData() {

        if (DemoConstant.shopInfoBean == null) {
            Logger.t("店铺信息不能为空");
            return;
        }

        ReqGoodsListInfo2 info = new ReqGoodsListInfo2();
        info.setTypes(1);
        info.setShopId(DemoConstant.shopInfoBean.getShopId());
        info.setPage(1);
        info.setPageSize(6);
        model.requestHomePageGoodsList(info, new TObserver<DemoRespBean<List<GoodsInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<GoodsInfoBean>> bean) {
                if (responseState(bean))
                    if (isViewAttach())
                        getView().getJrtjDataSuccess(bean.getData());
            }

        });
    }

    //预售商品信息数据
    protected void getYshouData() {

        if (DemoConstant.shopInfoBean == null) {
            Logger.t("店铺信息不能为空");
            return;
        }

        ReqGoodsInfo3 info = new ReqGoodsInfo3();
        info.setShopId(DemoConstant.shopInfoBean.getShopId());
        info.setPage(1);
        info.setPageSize(4);
        model.requestPreselGoodsList(info, new TObserver<DemoRespBean<List<GoodsInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<GoodsInfoBean>> bean) {
                if (responseState(bean))
                    if (isViewAttach())
                        getView().getYshouDataSuccess(bean.getData());
            }

        });
    }

    protected void getShopInfo(double latitude, double longitude) {
        ReqShopInfo info = new ReqShopInfo();
        info.setLatitude(latitude);
        info.setLongitude(longitude);
        model.requestShopInfo(info, new TObserver<DemoRespBean<ShopInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<ShopInfoBean> bean) {
                if (responseState(bean)) {
                    //请求登录
                    if (isViewAttach()) {
                        getView().getShopInfoSuccess(bean.getData());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (isViewAttach()) {
                    getView().getShopInfoSuccess(null);
                }
            }
        });
    }

    protected void getShopTypeData(int shopId) {
        ReqGoodsTypeInfo info = new ReqGoodsTypeInfo();
        info.setApply(1);
        info.setShopId(shopId);
        model.requestCategoryMain(info, new TObserver<DemoRespBean<List<GoodsTypeInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<GoodsTypeInfoBean>> bean) {
                if (isViewAttach()) {
                    getView().getShopTypeSuccess(bean.getData());
                }
            }
        });
    }

    protected void getGoodsItem(int id) {
        ReqGoodsInfo info = new ReqGoodsInfo();
        info.setGoodsId(id);
        model.requestGoodsItem(info, new TObserver<DemoRespBean<GoodsSpecInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<GoodsSpecInfoBean> bean) {
                if (responseState(bean)) {
                    if (isViewAttach()) {
                        getView().getGoodsSpecSuccess(bean.getData());
                    }
                }
            }
        });
    }

    protected void getActivityGoodsList(int shopId){
        ReqShopInfo3 info = new ReqShopInfo3();
        info.setShopId(shopId+"");
        model.requestActivityGoodsList(info, new TObserver<DemoRespBean<List<GoodsListInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<GoodsListInfoBean>> bean) {
                getView().getActivityGoodsListSuccess(bean.getData());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }


    public void addGoodsToShoppingCart(ReqCartAddInfo info) {
        showDialog("添加中...");
        model.requestCartAdd(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if (responseState(bean)) {
                    if (isViewAttach()) {
                        getView().addGoodsToShoppingCartSuccess();
                    }
                }
            }
        });
    }

}