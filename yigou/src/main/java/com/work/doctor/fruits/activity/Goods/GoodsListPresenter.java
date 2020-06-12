package com.work.doctor.fruits.activity.Goods;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqBrandInfo;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsListInfo;
import com.t.httplib.yigou.bean.resp.BrandInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tbase.http.HttpBase;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class GoodsListPresenter extends DemoPresenter<GoodsListView> {

    public GoodsListPresenter(@NonNull GoodsListView view) {
        super(view);
    }

    protected void getBrandListData(int shopId, int typeId) {
        ReqBrandInfo info = new ReqBrandInfo();
        info.setShopId(shopId);
        info.setRelatedCatids(typeId);
        model.requestBrandList(info, new TObserver<DemoRespBean<List<BrandInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<BrandInfoBean>> bean) {
                if(isViewAttach()){
                    getView().getBrandListDataSuccess(bean.getData());
                }
            }
        });
    }


    protected void getGoodsListData(ReqGoodsListInfo info) {
        model.requestGoodsList(info, new TObserver<DemoRespBean<List<GoodsInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<GoodsInfoBean>> bean) {
                if (bean.getResultState() == HttpBase.POST_SUCCESS) {
                    if(isViewAttach()){
                        getView().getListDataSuccess(bean.getData());
                    }
                }
            }

        });

    }

    protected void getGoodsItem(int id){
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