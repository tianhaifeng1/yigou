package com.work.doctor.fruits.activity.order;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqOrderInfo;
import com.t.httplib.yigou.bean.resp.OrderDetailInfoBean;
import com.work.doctor.fruits.base.DemoPresenter;

public class OrderDetailPresenter extends DemoPresenter<OrderDetailView> {

    public OrderDetailPresenter(@NonNull OrderDetailView view) {
        super(view);
    }

    protected void getDataOrderDetail(String orderNo){
        ReqOrderInfo info = new ReqOrderInfo();
        info.setSystemOrderNo(orderNo);
        model.requestOrderDetail(info, new TObserver<DemoRespBean<OrderDetailInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<OrderDetailInfoBean> bean) {
                if(responseState(bean))
                    if(isViewAttach())
                        getView().getDataOrderDetail(bean.getData());
            }
        });
    }
    protected void getDataOrderCancel(String orderNo){
        ReqOrderInfo info = new ReqOrderInfo();
        info.setSystemOrderNo(orderNo);
        model.requestOrderCancel(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if(responseState(bean))
                    if(isViewAttach())
                        getView().getDataOrderCancelSuccess();
            }
        });
    }
    protected void getDataOrderDelete(String orderNo){
        ReqOrderInfo info = new ReqOrderInfo();
        info.setSystemOrderNo(orderNo);
        model.requestOrderDelete(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if(responseState(bean))
                    if(isViewAttach())
                        getView().getDataOrderDeleteSuccess();
            }
        });
    }
    protected void getDataOrderReceive(String orderNo){
        ReqOrderInfo info = new ReqOrderInfo();
        info.setSystemOrderNo(orderNo);
        model.requestOrderReceive(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if(responseState(bean))
                    if(isViewAttach())
                        getView().getDataOrderReceiveSuccess();
            }
        });
    }


}
