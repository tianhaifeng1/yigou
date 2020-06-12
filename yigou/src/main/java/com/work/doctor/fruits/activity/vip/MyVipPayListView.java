package com.work.doctor.fruits.activity.vip;

import com.t.httplib.yigou.bean.WechetPayInfo;
import com.t.httplib.yigou.bean.resp.PayListInfoBean;
import com.t.httplib.yigou.bean.resp.PayOrderInfoBean;
import com.trjx.tbase.mvp.TView;

import java.util.List;

public interface MyVipPayListView extends TView {

    void getCzhiListDataSuccess(List<PayListInfoBean> list);
    void getPayOrderSuccess(PayOrderInfoBean infoBean);
    void getPayOrderMentSuccess(WechetPayInfo infoBean);

}