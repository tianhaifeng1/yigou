package com.work.doctor.fruits.activity.Goods;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo2;
import com.t.httplib.yigou.bean.resp.EvaluateInfoBean;
import com.t.httplib.yigou.bean.resp.GmrOutInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsDetailInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.t.httplib.yigou.bean.resp.OutInfoBean;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.List;

public class GoodsDetialPresenter extends DemoPresenter<GoodsDetialView> {

    public GoodsDetialPresenter(@NonNull GoodsDetialView view) {
        super(view);
    }

    public void getGoodsDetail(int goodsId) {
        ReqGoodsInfo info = new ReqGoodsInfo();
        info.setGoodsId(goodsId);
        model.requestGoodsDetail(info, new TObserver<DemoRespBean<OutInfoBean<GoodsDetailInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<OutInfoBean<GoodsDetailInfoBean>> bean) {
                if(responseState(bean)){
                    if(isViewAttach()){
                        try{
                            getView().getGoodsDetailSuccess(bean.getData().getModel());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    //购买人数据
    protected void getGmrData(int goodsId,int page,int pageSize) {

        ReqGoodsInfo2 info = new ReqGoodsInfo2();
        info.setGoodsId(goodsId);
        info.setPage(page);
        info.setPageSize(pageSize);
        model.requestPurchaseRrecord(info, new TObserver<DemoRespBean<GmrOutInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<GmrOutInfoBean> bean) {
                if (responseState(bean))
                    if (isViewAttach())
                        getView().getGmrDataSuccess(bean.getData());
            }

        });
    }


    public void getGoodsItem(int id,int dialogState){
        ReqGoodsInfo info = new ReqGoodsInfo();
        info.setGoodsId(id);
        model.requestGoodsItem(info, new TObserver<DemoRespBean<GoodsSpecInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<GoodsSpecInfoBean> bean) {
                if (responseState(bean)) {
                    if (isViewAttach()) {
                        getView().getGoodsItemSuccess(bean.getData(),dialogState);
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

    //商品评价
    public void getListDataEvaluate(TextView defaultTextview,int goodsId, int page, int pageSize){
        ReqGoodsInfo2 info2 = new ReqGoodsInfo2();
        info2.setGoodsId(goodsId);
        info2.setPage(page);
        info2.setPageSize(pageSize);
        model.requestCommentList(info2, new TObserver<DemoRespBean<List<EvaluateInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<EvaluateInfoBean>> bean) {
                if(responseState(bean)){
                    if(isViewAttach()){
                        getView().getListDataEvaluateSuccess(bean.getData());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                defaultTextview.setText("获取评价数据异常");
            }

            @Override
            public void onComplete() {
                super.onComplete();
                defaultTextview.setText("暂无评价");
            }
        });
    }

}