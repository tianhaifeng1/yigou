package com.t.httplib.yigou;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.trjx.tbase.activity.InitActivity;
import com.trjx.tbase.http.HttpBase;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class DObserver2 implements Observer<DemoRespBean> {

    private InitActivity initActivity;

    public DObserver2(InitActivity initActivity) {
        this.initActivity = initActivity;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(DemoRespBean respBean) {
        try {
            if(respBean.getResultState() == HttpBase.POST_SUCCESS){
                onTPostSuccess();
            }else{
                if (initActivity != null) {
                    initActivity.tPostFail(respBean.getResultState());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public abstract void onTPostSuccess();

    @Override
    public void onError(Throwable e) {
        if (initActivity != null) {
            initActivity.tPostError(e.getMessage());
            initActivity.tPostFinish(-1);
        }
    }

    @Override
    public void onComplete() {
        if (initActivity != null) {
            initActivity.tPostFinish(-1);
        }
    }
}
