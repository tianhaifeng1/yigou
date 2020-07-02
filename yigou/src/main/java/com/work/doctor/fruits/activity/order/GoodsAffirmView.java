package com.work.doctor.fruits.activity.order;

import com.t.httplib.yigou.bean.WechetPayInfo;
import com.t.httplib.yigou.bean.resp.AddressInfoBean;
import com.t.httplib.yigou.bean.resp.DistributionInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsAffirmBean;
import com.trjx.tbase.mvp.TView;

import java.util.List;

public interface GoodsAffirmView extends TView {

    void getInfoSuccess(GoodsAffirmBean infoBean);

    void getInfoFail(String msg);

    void getListDataSuccess(List<AddressInfoBean> beanList);

    void updateAddressSuccess(AddressInfoBean infoBean);

    void getDistributionInfoSuccess(DistributionInfoBean list);

    void getSubmitOrderSuccess(String systemOrderNo);

    void getPayMentSuccess(WechetPayInfo infoBean);
    void getPayMentFaile203();

    void applyInvoiceSuccess();
}
