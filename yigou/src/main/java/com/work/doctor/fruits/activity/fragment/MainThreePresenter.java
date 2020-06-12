package com.work.doctor.fruits.activity.fragment;

import androidx.annotation.NonNull;

import com.t.databaselib.bean.CartShopInfoBean;
import com.t.databaselib.bean.ReqCartDeleteInfo;
import com.t.databaselib.bean.ShopInfoBean;
import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqShopInfo;
import com.t.httplib.yigou.bean.req.ReqShopInfo2;
import com.trjx.tbase.http.HttpBase;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class MainThreePresenter extends DemoPresenter<MainThreeView> {

    public MainThreePresenter(@NonNull MainThreeView view) {
        super(view);
    }

    protected void getShoppingCartListData() {
        try{
            ReqShopInfo2 info = new ReqShopInfo2();
            info.setShopId(DemoConstant.shopInfoBean.getShopId()+"");
            model.requestCartList(info, new TObserver<DemoRespBean<List<CartShopInfoBean>>>() {
                @Override
                public void onNext(DemoRespBean<List<CartShopInfoBean>> bean) {
                    if (responseState(bean)) {
                        if (isViewAttach()) {
                            getView().getShoppingCartListDataSuccess(bean.getData());
                        }
                    }
                    if (bean.getResultState() != HttpBase.POST_SUCCESS) {
                        if (isViewAttach()) {
                            getView().getShoppingCartListDataFail();
                        }
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

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

    protected void cartDelete(ReqCartDeleteInfo info) {
        model.requestCartDelete(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if(responseState(bean))
                    if(isViewAttach())
                        getView().cartDeleteSuccess();
            }
        });
    }


}