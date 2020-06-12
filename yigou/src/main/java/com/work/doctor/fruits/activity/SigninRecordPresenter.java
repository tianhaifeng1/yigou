package com.work.doctor.fruits.activity;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqShopInfo2;
import com.t.httplib.yigou.bean.resp.SigninRecordBean;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class SigninRecordPresenter extends DemoPresenter<SigninRecordView> {


    public SigninRecordPresenter(@NonNull SigninRecordView view) {
        super(view);
    }

    protected void getSigninRecord(ReqShopInfo2 info){
        showDialog("添加中...");
        model.requestSigninRecord(info,new TObserver<DemoRespBean<List<SigninRecordBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<SigninRecordBean>> bean) {
                if(isViewAttach()) {
                    getView().getSigninRecordSuccess(bean.getData());
                }
            }
        });
    }
}
