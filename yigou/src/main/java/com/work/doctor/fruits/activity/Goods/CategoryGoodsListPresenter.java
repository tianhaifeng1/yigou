package com.work.doctor.fruits.activity.Goods;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo4;
import com.t.httplib.yigou.bean.resp.CategoryGoodsBean;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class CategoryGoodsListPresenter extends DemoPresenter<CategoryGoodsListView> {

    public CategoryGoodsListPresenter(@NonNull CategoryGoodsListView view) {
        super(view);
    }

    protected void getCategoryGoodsList(ReqGoodsInfo4 info) {
        showDialog("加载中...");
        model.requestCategoryGoodsList(info, new TObserver<DemoRespBean<List<CategoryGoodsBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<CategoryGoodsBean>> bean) {
                if(isViewAttach()) {
                    getView().getCategoryGoodsListSuccess(bean.getData());
                }
            }
        });
    }
}
