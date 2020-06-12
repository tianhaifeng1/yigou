package com.work.doctor.fruits.activity.address;

import com.t.httplib.yigou.bean.resp.AddressInfoBean;
import com.trjx.tbase.mvp.TView;

import java.util.List;

public interface SelectSHAddressView extends TView {
    void getListDataSuccess(List<AddressInfoBean> list);
}
