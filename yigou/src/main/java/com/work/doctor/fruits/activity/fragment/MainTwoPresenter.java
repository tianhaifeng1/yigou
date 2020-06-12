package com.work.doctor.fruits.activity.fragment;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqGoodsTypeInfo;
import com.t.httplib.yigou.bean.resp.GoodsTypeOutInfoBean;
import com.trjx.tlibs.uils.Logger;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoPresenter;

public class MainTwoPresenter extends DemoPresenter<MainTwoView> {

    public MainTwoPresenter(@NonNull MainTwoView view) {
        super(view);
    }


    protected void getShopTypeData(){
        if (DemoConstant.shopInfoBean == null) {
            Logger.t("店铺信息不能为空");
            return;
        }
        ReqGoodsTypeInfo info = new ReqGoodsTypeInfo();
        info.setApply(3);
        info.setShopId(DemoConstant.shopInfoBean.getShopId());
        showDialog("加载中...");
        model.requestCategory(info, new TObserver<DemoRespBean<GoodsTypeOutInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<GoodsTypeOutInfoBean> bean) {
                if(isViewAttach()){
                    getView().getShopTypeSuccess(bean.getData());
                }
            }
        });
    }

}
