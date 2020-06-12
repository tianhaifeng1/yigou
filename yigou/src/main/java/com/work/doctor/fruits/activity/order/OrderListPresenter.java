package com.work.doctor.fruits.activity.order;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.WechetPayInfo;
import com.t.httplib.yigou.bean.req.ReqOrderInfo;
import com.t.httplib.yigou.bean.req.ReqOrderListInfo;
import com.t.httplib.yigou.bean.req.ReqPaymentInfo;
import com.t.httplib.yigou.bean.resp.OrderListInfoBean;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class OrderListPresenter extends DemoPresenter<OrderListView> {

    public OrderListPresenter(@NonNull OrderListView view) {
        super(view);
    }

    /**
     * 获取订单列表数据
     * @param tradeStatus  0：待支付；1：待发货；2：待收货；3：待评价；4：已完成；5：已取消；6：退款中；7：已退款；-1：全部
     * @param page
     * @param pageSize
     */
    protected void getDataOrderList(int tradeStatus, int page, int pageSize) {

        ReqOrderListInfo info = new ReqOrderListInfo();
        info.setTradeStatus(tradeStatus == -1 ? "" : tradeStatus + "");
        info.setPage(page);
        info.setPageSize(pageSize);
        model.requestOrderList(info, new TObserver<DemoRespBean<List<OrderListInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<OrderListInfoBean>> bean) {
                if (responseState(bean))
                    if (isViewAttach())
                        getView().getDataListSuccess(bean.getData());
            }
        });

    }

    protected void getDataOrderCancel(String orderNo,int position){
        ReqOrderInfo info = new ReqOrderInfo();
        info.setSystemOrderNo(orderNo);
        model.requestOrderCancel(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if(responseState(bean,""))
                    if(isViewAttach())
                        getView().getDataOrderCancelSuccess(position);
            }
        });
    }
    protected void getDataOrderDelete(String orderNo,int position){
        ReqOrderInfo info = new ReqOrderInfo();
        info.setSystemOrderNo(orderNo);
        model.requestOrderDelete(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if(responseState(bean,""))
                    if(isViewAttach())
                        getView().getDataOrderDeleteSuccess(position);
            }
        });
    }
    protected void getDataOrderReceive(String orderNo,int position){
        ReqOrderInfo info = new ReqOrderInfo();
        info.setSystemOrderNo(orderNo);
        model.requestOrderReceive(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if(responseState(bean,""))
                    if(isViewAttach())
                        getView().getDataOrderReceiveSuccess(position);
            }
        });
    }


    protected void payMent(String systemOrderNo,int payWay,int position){
        DemoConstant.zfOrderId = systemOrderNo;
        DemoConstant.orderType = 1;
        showDialog("支付中...");
        ReqPaymentInfo info = new ReqPaymentInfo();
        info.setSystemOrderNo(systemOrderNo);
        info.setPayWay(payWay);
        model.requestPayment(info, new TObserver<DemoRespBean<WechetPayInfo>>() {
            @Override
            public void onNext(DemoRespBean<WechetPayInfo> bean) {
                if (responseState(bean, "")) {
                    if (isViewAttach())
                        getView().getPayMentSuccess(bean.getData(),position);
                }
            }
        });
    }


}
