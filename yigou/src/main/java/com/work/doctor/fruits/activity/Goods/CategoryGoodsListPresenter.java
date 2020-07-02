package com.work.doctor.fruits.activity.Goods;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo4;
import com.t.httplib.yigou.bean.resp.CategoryGoodsBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
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

    protected void getGoodsItem(int id) {
        ReqGoodsInfo info = new ReqGoodsInfo();
        info.setGoodsId(id);
        model.requestGoodsItem(info, new TObserver<DemoRespBean<GoodsSpecInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<GoodsSpecInfoBean> bean) {
                if (responseState(bean)) {
                    if (isViewAttach()) {
                        getView().getGoodsSpecSuccess(bean.getData());
                    }
                }
            }
        });
    }

    public void addGoodsToShoppingCart(ReqCartAddInfo info){
        showDialog("添加中...");
        model.requestCartAdd(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if (responseState(bean)) {
                    if (isViewAttach()) {
                        getView().addGoodsToShoppingCartSuccess();
                    }
                }
            }
        });
    }

}
