package com.work.doctor.fruits.activity;

import com.t.httplib.yigou.bean.resp.BalanceStatInfoBean;
import com.trjx.tbase.mvp.TView;

public interface SharePageView extends TView {

    void getDataInfoSuccess(BalanceStatInfoBean infoBean);

}
