package com.work.doctor.fruits.activity.vip;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.WechetPayInfo;
import com.t.httplib.yigou.bean.req.ReqPageInfo;
import com.t.httplib.yigou.bean.req.ReqPayOrderInfo;
import com.t.httplib.yigou.bean.req.ReqPayOrderMentInfo;
import com.t.httplib.yigou.bean.resp.PayListInfoBean;
import com.t.httplib.yigou.bean.resp.PayOrderInfoBean;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class MyVipPayListPresenter extends DemoPresenter<MyVipPayListView> {
    public MyVipPayListPresenter(@NonNull MyVipPayListView view) {
        super(view);
    }

    protected void getPayListData(int page,int pageSize){
        ReqPageInfo info = new ReqPageInfo();
        info.setPage(page);
        info.setPageSize(pageSize);
        model.requestPayList(info, new TObserver<DemoRespBean<List<PayListInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<PayListInfoBean>> bean) {
                if(responseState(bean)){
                    if(isViewAttach()){
                        getView().getCzhiListDataSuccess(bean.getData());
                    }
                }
            }
        });
    }

    protected void getPayOrder(String payId) {
        ReqPayOrderInfo info = new ReqPayOrderInfo();
        info.setMemberPayId(payId);
        model.requestPayOrder(info, new TObserver<DemoRespBean<PayOrderInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<PayOrderInfoBean> bean) {
                if (responseState(bean)) {
                    if (isViewAttach()) {
                        getView().getPayOrderSuccess(bean.getData());
                    }
                }
            }
        });
    }

    protected void getPayOrderMent(String payNo) {
        ReqPayOrderMentInfo info = new ReqPayOrderMentInfo();
        info.setPayNo(payNo);
        model.requestPayOrderPayMent(info, new TObserver<DemoRespBean<WechetPayInfo>>() {
            @Override
            public void onNext(DemoRespBean<WechetPayInfo> bean) {
                if (responseState(bean)) {
                    if (isViewAttach()) {
                        getView().getPayOrderMentSuccess(bean.getData());
                    }
                }
            }
        });
    }

}
