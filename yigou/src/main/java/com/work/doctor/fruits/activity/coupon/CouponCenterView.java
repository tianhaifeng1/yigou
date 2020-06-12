package com.work.doctor.fruits.activity.coupon;

import com.t.httplib.yigou.bean.resp.CouponGrantInfoBean;
import com.work.doctor.fruits.activity.myrecycler.MyRecyclerView;

public interface CouponCenterView extends MyRecyclerView {

    void getCouponGrantSuccess(CouponGrantInfoBean infoBean, int position);

}
