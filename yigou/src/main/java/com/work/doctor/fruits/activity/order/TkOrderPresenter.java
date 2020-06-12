package com.work.doctor.fruits.activity.order;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqPageInfo;
import com.t.httplib.yigou.bean.resp.TkOrderInfoBean;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class TkOrderPresenter extends DemoPresenter<TkOrderView> {
    public TkOrderPresenter(@NonNull TkOrderView view) {
        super(view);
    }

    public void getTkOrderList(int page,int pageSize){
        ReqPageInfo pageInfo = new ReqPageInfo();
        pageInfo.setPage(page);
        pageInfo.setPageSize(pageSize);
        model.requestTkOrderList(pageInfo, new TObserver<DemoRespBean<List<TkOrderInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<TkOrderInfoBean>> bean) {
                if(responseState(bean))
                    if(isViewAttach())
                        getView().getTkListDataSuccess(bean.getData());
            }
        });
    }

}
