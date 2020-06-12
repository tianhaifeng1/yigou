package com.work.doctor.fruits.activity.search;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo;
import com.t.httplib.yigou.bean.req.ReqYgSearchInfo;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tlibs.uils.Logger;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class SearchGoodsPresenter extends DemoPresenter<SearchGoodsView> {
    public SearchGoodsPresenter(@NonNull SearchGoodsView view) {
        super(view);
    }

    public void getSearchPopularGoodsList(){
        model.requestSearchPopularGoodsList(new TObserver<DemoRespBean<List<String>>>() {
            @Override
            public void onNext(DemoRespBean<List<String>> bean) {
                if(responseState(bean))
                    if(isViewAttach())
                        getView().getSearchPopularGoodsListSuccess(bean.getData());
            }
        });
    }

    public void getSearchInfo(String searchContent){
        if (DemoConstant.shopInfoBean == null) {
            Logger.t("店铺信息不能为空");
            return;
        }
        ReqYgSearchInfo info = new ReqYgSearchInfo();
        info.setGoodsName(searchContent);
        info.setShopId(DemoConstant.shopInfoBean.getShopId() + "");
        model.requestSearchInfo(info, new TObserver<DemoRespBean<List<GoodsInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<GoodsInfoBean>> bean) {
                if(responseState(bean))
                    if(isViewAttach())
                        getView().getSearchInfoSuccess(bean.getData());
            }
        });

    }

    public void getGoodsItem(int id,int position){
        ReqGoodsInfo info = new ReqGoodsInfo();
        info.setGoodsId(id);
        model.requestGoodsItem(info, new TObserver<DemoRespBean<GoodsSpecInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<GoodsSpecInfoBean> bean) {
                if (responseState(bean)) {
                    if (isViewAttach()) {
                        getView().getGoodsItemSuccess(bean.getData(),position);
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
