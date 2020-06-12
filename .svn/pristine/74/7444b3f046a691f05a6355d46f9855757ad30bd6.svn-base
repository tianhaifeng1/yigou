package com.work.doctor.fruits.activity.order;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqInvoice2Info;
import com.t.httplib.yigou.bean.req.ReqInvoiceInfo;
import com.work.doctor.fruits.base.DemoPresenter;

public class InvoicePresenter extends DemoPresenter<InvoiceView> {

    public InvoicePresenter(@NonNull InvoiceView view) {
        super(view);
    }


    protected void applyInvoice(ReqInvoiceInfo info){
        info.setCategory(1);
        model.requestApplyInvoice(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if(responseState(bean))
                    if(isViewAttach())
                        getView().applyInvoiceSuccess();
            }
        });
    }

    protected void applyInvoice2(ReqInvoice2Info info){
        info.setCategory(0);
        model.requestApplyInvoice2(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if(responseState(bean))
                    if(isViewAttach())
                        getView().applyInvoiceSuccess();
            }
        });
    }

}
