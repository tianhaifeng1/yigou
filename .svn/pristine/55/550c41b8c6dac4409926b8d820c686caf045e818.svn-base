package com.work.doctor.fruits.activity.order;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqOrderInfo;
import com.t.httplib.yigou.bean.req.ReqRefundInfo;
import com.t.httplib.yigou.bean.resp.BankcardInfoBean;
import com.t.httplib.yigou.bean.resp.RefundDetailInfoBean;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

import static com.trjx.tbase.http.HttpBase.POST_SUCCESS;

public class OrderRefundPresenter extends DemoPresenter<OrderRefundView> {

    public OrderRefundPresenter(@NonNull OrderRefundView view) {
        super(view);
    }

    public void getQuestionListData(){
        model.requestBankcardList("refund", new TObserver<DemoRespBean<List<BankcardInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<BankcardInfoBean>> bean) {
                if (responseState(bean))
                    if (isViewAttach())
                        getView().getQuestionListDataSuccess(bean.getData());
            }
        });
    }

    public void getApplyRefund(ReqRefundInfo info){
        if (info.getAccount() == null || info.getAccount().equals("")) {
            if(isViewAttach())
                getView().tRemind("退款原因不能为空");
            return;
        }
        model.requestApplyRefund(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if (responseState(bean, ""))
                    if (isViewAttach())
                        getView().getApplyRefundSuccess();
            }
        });
    }

    public void getRefundDetial(String systemOrderNo){
        ReqOrderInfo info = new ReqOrderInfo();
        info.setSystemOrderNo(systemOrderNo);
        model.requestRefundDetial(info, new TObserver<DemoRespBean<RefundDetailInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<RefundDetailInfoBean> resultInfo) {
                if (resultInfo != null) {
                    int state = resultInfo.getResultState();
                    if (state == POST_SUCCESS) {//成功返回数据
                        if (isViewAttach())
                            getView().getRefundDetialSuccess(resultInfo.getData());
                    } else {
                        if (isViewAttach())
                            getView().tRemind(resultInfo.getRemindMessage());
                    }
                } else {
                    if (isViewAttach()) {
                        getView().tPostError("返回的数据异常");
                    }
                }

            }
        });
    }

}
