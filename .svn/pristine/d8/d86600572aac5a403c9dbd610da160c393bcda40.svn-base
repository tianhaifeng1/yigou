package com.work.doctor.fruits.activity.vip;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqPageInfo;
import com.t.httplib.yigou.bean.resp.PayOrderInfoBean2;
import com.t.httplib.yigou.bean.resp.UserInfoBean;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class MVipPresenter extends DemoPresenter<MVipView> {

    public MVipPresenter(@NonNull MVipView view) {
        super(view);
    }

    //    余额使用记录
    public void getListDataPurchaseRecord(int page,int pageSize){
        ReqPageInfo info = new ReqPageInfo();
        info.setPage(page);
        info.setPageSize(pageSize);
        model.requestPurchaseRecordList(info, new TObserver<DemoRespBean<List<PayOrderInfoBean2>>>() {
            @Override
            public void onNext(DemoRespBean<List<PayOrderInfoBean2>> bean) {
                if(responseState(bean)){
                    if(isViewAttach()){
                        getView().getUseListDataSuccess(bean.getData());
                    }
                }
            }
        });
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


}
