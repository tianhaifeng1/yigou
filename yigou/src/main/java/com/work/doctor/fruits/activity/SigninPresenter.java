package com.work.doctor.fruits.activity;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqShopInfo3;
import com.t.httplib.yigou.bean.resp.SigninInfoBean;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoPresenter;

public class SigninPresenter extends DemoPresenter<SigninView> {

    public SigninPresenter(@NonNull SigninView view) {
        super(view);
    }

    protected void getSigninData(){

        ReqShopInfo3 info = new ReqShopInfo3();
        info.setShopId(DemoConstant.shopInfoBean.getShopId()+"");
        model.requestSigninInfo(info, new TObserver<DemoRespBean<SigninInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<SigninInfoBean> bean) {
                if(isViewAttach()) {
                    getView().getSigninDataSuccess(bean.getData());
                }
            }
        });
    }

    protected void getSigninOperate(){
        ReqShopInfo3 info = new ReqShopInfo3();
        info.setShopId(DemoConstant.shopInfoBean.getShopId()+"");
        model.requestSigninOperate(info, new TObserver<DemoRespBean<String>>() {

            @Override
            public void onNext(DemoRespBean<String> bean) {
                if(isViewAttach()) {
                    getView().getSigninOperateSuccess(bean.getStatus());
                }
            }
        });
    }
}
