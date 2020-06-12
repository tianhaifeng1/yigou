package com.work.doctor.fruits.activity;

import com.t.httplib.yigou.bean.resp.SigninInfoBean;
import com.trjx.tbase.mvp.TView;

public interface SigninView extends TView {

    void getSigninDataSuccess(SigninInfoBean bean);

    void getSigninOperateSuccess(int operate);

}
