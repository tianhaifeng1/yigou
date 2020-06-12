package com.work.doctor.fruits.activity.coupon;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqCouponGrantInfo;
import com.t.httplib.yigou.bean.req.ReqPageInfo;
import com.t.httplib.yigou.bean.resp.CouponCenterInfoBean;
import com.t.httplib.yigou.bean.resp.CouponGrantInfoBean;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class CouponCenterPresenter extends DemoPresenter<CouponCenterView> {

    public CouponCenterPresenter(@NonNull CouponCenterView view) {
        super(view);
    }

    public void getCouponListData (int page, int pageSize){

        ReqPageInfo info = new ReqPageInfo();
        info.setPage(page);
        info.setPageSize(pageSize);
        model.requestCouponCenterInfo(info, new TObserver<DemoRespBean<List<CouponCenterInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<CouponCenterInfoBean>> bean) {
                if (responseState(bean)) {
                    if (isViewAttach()) {
                        getView().getListDataSuccess(bean.getData());
                    }
                }
            }
        });
    }

    public void getCouponGrant(String couponId,int position){
        ReqCouponGrantInfo info = new ReqCouponGrantInfo();
        info.setCouponId(couponId);
        model.requestCouponGrantInfo(info, new TObserver<DemoRespBean<CouponGrantInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<CouponGrantInfoBean> bean) {
                if(responseState(bean)){
                    if(isViewAttach()){
                        getView().getCouponGrantSuccess(bean.getData(),position);
                    }
                }
            }
        });

    }


}
