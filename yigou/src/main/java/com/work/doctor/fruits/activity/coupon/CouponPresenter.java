package com.work.doctor.fruits.activity.coupon;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqCouponInfo;
import com.t.httplib.yigou.bean.resp.CouponInfoBean;
import com.work.doctor.fruits.activity.myrecycler.MyRecyclerView;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class CouponPresenter extends DemoPresenter<MyRecyclerView> {

    public CouponPresenter(@NonNull MyRecyclerView view) {
        super(view);
    }

    public void getListDataCoupon(int page, int pageSize, int status) {

        ReqCouponInfo info = new ReqCouponInfo();
        info.setPage(page);
        info.setPageSize(pageSize);
        info.setStatus(status);
        model.requestCouponMyInfo(info, new TObserver<DemoRespBean<List<CouponInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<CouponInfoBean>> bean) {
                if (responseState(bean)) {
                    if (isViewAttach()) {
                        getView().getListDataSuccess(bean.getData());
                    }
                }
            }
        });
    }


}
