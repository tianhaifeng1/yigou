package com.work.doctor.fruits.activity.address;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqShopInfo2;
import com.t.httplib.yigou.bean.resp.AddressInfoBean;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class SelectSHAddressPresenter extends DemoPresenter<SelectSHAddressView> {

    public SelectSHAddressPresenter(@NonNull SelectSHAddressView view) {
        super(view);
    }

    protected void getListDataAddress(String shopId,int page,int pageSize){
        ReqShopInfo2 info = new ReqShopInfo2();
        info.setShopId(shopId);
        info.setPage(page);
        info.setPageSize(pageSize);
        model.requestAddressList(info, new TObserver<DemoRespBean<List<AddressInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<AddressInfoBean>> bean) {
                if(responseState(bean)){
                    if(isViewAttach()){
                        getView().getListDataSuccess(bean.getData());
                    }
                }
            }
        });
    }
}
