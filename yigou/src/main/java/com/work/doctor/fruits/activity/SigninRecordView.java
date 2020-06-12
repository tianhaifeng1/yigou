package com.work.doctor.fruits.activity;

import com.t.httplib.yigou.bean.resp.SigninRecordBean;
import com.trjx.tbase.mvp.TView;

import java.util.List;

public interface SigninRecordView extends TView {

    void getSigninRecordSuccess(List<SigninRecordBean> list);

}
