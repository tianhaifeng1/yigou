package com.work.doctor.fruits.activity.Goods;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsListInfo2;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tbase.http.HttpBase;
import com.trjx.tlibs.uils.Logger;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class GoodsListMOMorePresenter extends DemoPresenter<GoodsListMOMoreView> {

    public GoodsListMOMorePresenter(@NonNull GoodsListMOMoreView view) {
        super(view);
    }

    //获取集合数据
    protected void getListData(int type ,int page,int pageSize) {
        if (DemoConstant.shopInfoBean == null) {
            Logger.t("店铺信息不能为空");
            return;
        }
        ReqGoodsListInfo2 info = new ReqGoodsListInfo2();
        info.setTypes(type);
        info.setShopId(DemoConstant.shopInfoBean.getShopId());
        info.setPage(page);
        info.setPageSize(pageSize);
        model.requestHomePageGoodsList(info, new TObserver<DemoRespBean<List<GoodsInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<GoodsInfoBean>> bean) {
                if (bean.getResultState() == HttpBase.POST_SUCCESS) {
                    if (isViewAttach()) {
                        getView().getListDataSuccess(bean.getData());
                    }
                }
            }

        });

    }

    //商品规格
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