package com.work.doctor.fruits.activity.vip;

import com.t.httplib.yigou.bean.resp.BalanceStatInfoBean;
import com.t.httplib.yigou.bean.resp.BankcardInfoBean;
import com.t.httplib.yigou.bean.resp.MoneyRecordApplyListInfoBean;
import com.t.httplib.yigou.bean.resp.MoneyRecordListInfoBean;
import com.trjx.tbase.mvp.TView;

import java.util.List;

public interface MyEarningView extends TView {

    void getListDataSySuccess(List<MoneyRecordListInfoBean> list);
    void getTxListDataTxSuccess(List<MoneyRecordApplyListInfoBean> list);
    void getUserBalanceDataInfoSuccess(BalanceStatInfoBean infoBean);
    void getZyeSuccess();

    void getApplySuccess();
    void getBankcardListDataSuccess(List<BankcardInfoBean> list);
    void getTxStartMoneySuccess(BankcardInfoBean bean);

}
