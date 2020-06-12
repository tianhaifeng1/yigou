package com.work.doctor.fruits.activity.fragment;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.resp.OrderNumberInfoBean;
import com.t.httplib.yigou.bean.resp.UserInfoBean;
import com.work.doctor.fruits.base.DemoPresenter;

public class MainFourPresenter extends DemoPresenter<MainFourView> {

    public MainFourPresenter(@NonNull MainFourView view) {
        super(view);
    }


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

    //获取订单数量
    protected void getOrderNumber(){
        model.requestOrderNumber(new TObserver<DemoRespBean<OrderNumberInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<OrderNumberInfoBean> bean) {
                if (responseState(bean)) {
                    if (isViewAttach()) {
                        getView().getOrderNumberSuccess(bean.getData());
                    }
                }
            }
        });
    }


}
