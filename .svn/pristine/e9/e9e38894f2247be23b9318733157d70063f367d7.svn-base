package com.work.doctor.fruits.activity.order;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqCommentInfo;
import com.work.doctor.fruits.base.DemoPresenter;

public class OrderCommentPresenter extends DemoPresenter<OrderCommentView> {

    public OrderCommentPresenter(@NonNull OrderCommentView view) {
        super(view);
    }

    protected void getComment(ReqCommentInfo info){
        model.requestAddComment(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if (responseState(bean))
                    if (isViewAttach())
                        getView().getCommentSuccess();
            }
        });

    }


}
