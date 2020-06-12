package com.t.httplib.yigou;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.trjx.tbase.activity.InitActivity;
import com.trjx.tbase.http.HttpBase;
import com.trjx.tlibs.uils.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class DObserver<T> implements Observer<DemoRespBean<T>> {

    private InitActivity initActivity;

    public DObserver(InitActivity initActivity) {
        this.initActivity = initActivity;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(DemoRespBean<T> respBean) {
        try{
            if(respBean.getResultState() == HttpBase.POST_SUCCESS){
                onTPostSuccess(respBean.getData());
            }else{
                if (initActivity != null) {
                    initActivity.tPostFail(respBean.getResultState());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.t("---" + e.getMessage());
        }

    }

    public abstract void onTPostSuccess(T t);

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