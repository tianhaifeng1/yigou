package com.work.doctor.fruits.activity.Goods;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo3;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tlibs.uils.Logger;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class GoodsListYsPresenter extends DemoPresenter<GoodsListYsView> {

    public GoodsListYsPresenter(@NonNull GoodsListYsView view) {
        super(view);
    }

    //预售商品信息数据
    protected void getYshouData(int page,int pageSize) {

        if (DemoConstant.shopInfoBean == null) {
            Logger.t("店铺信息不能为空");
            return;
        }

        ReqGoodsInfo3 info = new ReqGoodsInfo3();
        info.setShopId(DemoConstant.shopInfoBean.getShopId());
        info.setPage(page);
        info.setPageSize(pageSize);
        model.requestPreselGoodsList(info, new TObserver<DemoRespBean<List<GoodsInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<GoodsInfoBean>> bean) {
                if (responseState(bean))
                    if (isViewAttach())
                        getView().getYshouDataSuccess(bean.getData());
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
