package com.work.doctor.fruits.activity;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.resp.BalanceStatInfoBean;
import com.work.doctor.fruits.base.DemoPresenter;

public class SharePagePresenter extends DemoPresenter<SharePageView> {
    public SharePagePresenter(@NonNull SharePageView view) {
        super(view);
    }

    protected void getDataInfo(){
        model.requestUserBalanceStat(new TObserver<DemoRespBean<BalanceStatInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<BalanceStatInfoBean> bean) {
                if (responseState(bean)) {
                    if(isViewAttach()){
                        getView().getDataInfoSuccess(bean.getData());
                    }
                }
            }
        });
    }

}
