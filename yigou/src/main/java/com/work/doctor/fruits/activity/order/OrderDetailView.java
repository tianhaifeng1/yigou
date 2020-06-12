package com.work.doctor.fruits.activity.order;

import com.t.httplib.yigou.bean.resp.OrderDetailInfoBean;
import com.trjx.tbase.mvp.TView;

public interface OrderDetailView extends TView {

    void getDataOrderDetail(OrderDetailInfoBean infoBean);

     void getDataOrderCancelSuccess();
     void getDataOrderDeleteSuccess();
     void getDataOrderReceiveSuccess();

}
