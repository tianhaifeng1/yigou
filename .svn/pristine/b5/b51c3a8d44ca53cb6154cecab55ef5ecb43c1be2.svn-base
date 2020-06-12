package com.work.doctor.fruits.activity.vip;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqApplyTxInfo;
import com.t.httplib.yigou.bean.req.ReqPageInfo;
import com.t.httplib.yigou.bean.resp.BalanceStatInfoBean;
import com.t.httplib.yigou.bean.resp.BankcardInfoBean;
import com.t.httplib.yigou.bean.resp.MoneyRecordApplyListInfoBean;
import com.t.httplib.yigou.bean.resp.MoneyRecordListInfoBean;
import com.trjx.tlibs.uils.TUtils;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class MyEarningPresenter extends DemoPresenter<MyEarningView> {
    public MyEarningPresenter(@NonNull MyEarningView view) {
        super(view);
    }


    public void getListDataShouy(int page, int pageSize) {
        ReqPageInfo info = new ReqPageInfo();
        info.setPage(page);
        info.setPageSize(pageSize);
        model.requestUserMoneyRecordList(info, new TObserver<DemoRespBean<List<MoneyRecordListInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<MoneyRecordListInfoBean>> bean) {
                if (responseState(bean))
                    if (isViewAttach())
                        getView().getListDataSySuccess(bean.getData());
            }
        });
    }

    public void getListDataTix(int page, int pageSize) {
        ReqPageInfo info = new ReqPageInfo();
        info.setPage(page);
        info.setPageSize(pageSize);
        model.requestApplyMoneyRecordList(info, new TObserver<DemoRespBean<List<MoneyRecordApplyListInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<MoneyRecordApplyListInfoBean>> bean) {
                if (responseState(bean))
                    if (isViewAttach())
                        getView().getTxListDataTxSuccess(bean.getData());
            }
        });
    }


    public void getUserBalanceDataInfo() {
        model.requestUserBalanceStat(new TObserver<DemoRespBean<BalanceStatInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<BalanceStatInfoBean> bean) {
                if (responseState(bean))
                    if (isViewAttach())
                        getView().getUserBalanceDataInfoSuccess(bean.getData());
            }
        });
    }

    public void getZye() {
        model.requestRateToBalance(new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if (responseState(bean, ""))
                    if (isViewAttach())
                        getView().getZyeSuccess();
            }
        });
    }

    public void getApply(String moneyTx, String moneyStr, String bankName, String bankRealName, String bankNo1,
                         String bankNo2, String phoneNumber, String bankKey, String startMoneyStr) {

        try {
            float money = Float.parseFloat(moneyTx);
            float moneyTotal = Float.parseFloat(moneyStr);
            float startMoney = Float.parseFloat(startMoneyStr);
            if (money > moneyTotal) {
                if (isViewAttach())
                    getView().tRemind("提现金额不能大于总金额");
                return;
            } else if (money < startMoney) {
                if (isViewAttach())
                    getView().tRemind("提现金额不能小于" + startMoneyStr + "元");
                return;
            } else if (bankName == null || bankName.equals("")) {
                if (isViewAttach())
                    getView().tRemind("请选择银行名称");
                return;
            } else if (bankRealName == null || bankRealName.equals("")) {
                if (isViewAttach())
                    getView().tRemind("请填写账户姓名");
                return;
            } else if (bankNo1 == null || bankNo1.equals("")) {
                if (isViewAttach())
                    getView().tRemind("请填写账户号码");
                return;
            } else if (bankNo1.length() < 15) {
                if (isViewAttach())
                    getView().tRemind("账户号码号码有误，请确认");
                return;
            } else if (bankNo2 == null || bankNo2.equals("") || !bankNo1.equals(bankNo2)) {
                if (isViewAttach())
                    getView().tRemind("两次账户号码不一致，请再次确认");
                return;
            } else if (!TUtils.isMobileNO(phoneNumber)) {
                if (isViewAttach())
                    getView().tRemind("请确认手机号码是否正确");
                return;
            } else {
                ReqApplyTxInfo info = new ReqApplyTxInfo();
                info.setMoneyFee(moneyTx);
                info.setDrawNo(bankNo1);
                info.setDrawName(bankName);
                info.setDrawCode(bankKey);
                info.setRealName(bankRealName);
                info.setPhone(phoneNumber);
                model.requestApplyMoney(info, new TObserver<DemoRespBean>() {
                    @Override
                    public void onNext(DemoRespBean bean) {
                        if (responseState(bean, ""))
                            if (isViewAttach())
                                getView().getApplySuccess();
                    }
                });
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (isViewAttach())
                getView().tRemind("金额异常，暂时不能提现");
            return;
        }

    }

    public void getBankcardListData() {
        model.requestBankcardList("bank" ,new TObserver<DemoRespBean<List<BankcardInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<BankcardInfoBean>> bean) {
                if (responseState(bean, ""))
                    if (isViewAttach())
                        getView().getBankcardListDataSuccess(bean.getData());
            }
        });
    }

    public void getTxMoneyData() {
        model.requestBankcardList("applyMoney", new TObserver<DemoRespBean<List<BankcardInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<BankcardInfoBean>> bean) {
                if (responseState(bean))
                    if (isViewAttach()){
                        List<BankcardInfoBean> infoBeanList = bean.getData();
                        getView().getTxStartMoneySuccess(infoBeanList==null||infoBeanList.size()==0?null:infoBeanList.get(0));
                    }

            }
        });
    }

}
