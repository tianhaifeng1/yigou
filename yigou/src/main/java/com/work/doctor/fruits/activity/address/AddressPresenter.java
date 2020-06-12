package com.work.doctor.fruits.activity.address;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqAddressInfo;
import com.t.httplib.yigou.bean.req.ReqShopInfo2;
import com.t.httplib.yigou.bean.resp.AddressInfoBean;
import com.trjx.tlibs.uils.TUtils;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class AddressPresenter extends DemoPresenter<AddressView> {

    public AddressPresenter(@NonNull AddressView view) {
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


    protected void updateAddress(AddressInfoBean info) {
        //判断
        if (info.getName() == null || info.getName().isEmpty()) {
            tishi("请填写收货人的姓名");
        }else if(info.getPhone()==null||info.getPhone().isEmpty()){
            tishi("手机号码不能为空");
        }else if(!TUtils.isMobileNO(info.getPhone())){
            tishi("手机号码无效，请检查");
        }else if(info.getAddress()==null||info.getAddress().isEmpty()){
            tishi("请选择省市区");
        }else if(info.getDetailAddress()==null||info.getDetailAddress().isEmpty()){
            tishi("请填写详细地址");
        }else{
            model.requestAddressEdit(info, new TObserver<DemoRespBean<AddressInfoBean>>() {
                @Override
                public void onNext(DemoRespBean<AddressInfoBean> bean) {
                    if(responseState(bean))
                        if(isViewAttach())
                            getView().updateAddressSuccess(bean.getData());
                }
            });
        }
    }

    protected void deleteAddress(String addressId) {
        //判断
        if (addressId == null || addressId.isEmpty()) {
            tishi("删除失败");
        }else{
            ReqAddressInfo info = new ReqAddressInfo();
            info.setAddressId(addressId);
            model.requestAddressDelete(info, new TObserver<DemoRespBean>() {
                @Override
                public void onNext(DemoRespBean bean) {
                    if(responseState(bean))
                        if(isViewAttach())
                            getView().deleteAddressSuccess();
                }
            });
        }
    }

    protected void setDefaultAddress(String addressId) {
        //判断
        if (addressId == null || addressId.isEmpty()) {
            tishi("设置失败");
        }else{
            ReqAddressInfo info = new ReqAddressInfo();
            info.setAddressId(addressId);
            model.requestAddressSetDefault(info, new TObserver<DemoRespBean>() {
                @Override
                public void onNext(DemoRespBean bean) {
                    if(responseState(bean))
                        if(isViewAttach())
                            getView().setDefaultAddressSuccess();
                }
            });
        }
    }

}
