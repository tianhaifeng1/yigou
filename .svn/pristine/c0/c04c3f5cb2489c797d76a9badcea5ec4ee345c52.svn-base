package com.work.doctor.fruits.activity.order;

import com.t.httplib.yigou.bean.WechetPayInfo;
import com.t.httplib.yigou.bean.resp.OrderListInfoBean;
import com.trjx.tbase.mvp.TView;

import java.util.List;

public interface OrderListView extends TView {

    //获取列表信息
    void getDataListSuccess(List<OrderListInfoBean> listBean);

    void getDataOrderCancelSuccess(int position);
    void getDataOrderDeleteSuccess(int position);
    void getDataOrderReceiveSuccess(int position);

    void getPayMentSuccess(WechetPayInfo infoBean, int position);

}
