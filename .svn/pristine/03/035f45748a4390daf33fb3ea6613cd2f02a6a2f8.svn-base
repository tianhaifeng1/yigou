package com.work.doctor.fruits.activity.myrecycler;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo2;
import com.t.httplib.yigou.bean.req.ReqPageInfo;
import com.t.httplib.yigou.bean.resp.EvaluateInfoBean;
import com.t.httplib.yigou.bean.resp.FensInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.MoneyRecordApplyListInfoBean;
import com.t.httplib.yigou.bean.resp.MoneyRecordListInfoBean;
import com.t.httplib.yigou.bean.resp.PayOrderInfoBean;
import com.trjx.tlibs.assist.ImgPaths;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerPresenter extends DemoPresenter<MyRecyclerView> {

    public MyRecyclerPresenter(@NonNull MyRecyclerView view) {
        super(view);
    }

    public void getListData(int code,int page){
        switch (code){
            case 1:
                getListDataCode1(page);
                break;
            case 2:
                getListDataCode2(page);
                break;
            case 3:
                getListDataCode3(page);
                break;
            case 101:
                getListDataCode101(page);
                break;
        }
    }


    private void getListDataCode1(int page){

//        List<CouponInfoBean> listBeans = new ArrayList<>();
//        for (int i = 0; i < (page < 3 ? 20 : 10); i++) {
//            CouponInfoBean bean = new CouponInfoBean();
//            if (i % 3 == 0) {
//                bean.setUse(true);
//            }
//            listBeans.add(bean);
//        }
//
//        if (isViewAttach()) {
//            getView().getListDataSuccess(listBeans);
//        }
    }

    private void getListDataCode2(int page){

        List<GoodsInfoBean> listBeans = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            GoodsInfoBean bean = new GoodsInfoBean();
            bean.setSellPrice(2.5f);
            bean.setSellPriceDiscount(2.3f);
            bean.setGoodsName("商品名称");
            bean.setGoodsImage(ImgPaths.path[i+10]);
            bean.setId(i+100);
            listBeans.add(bean);
        }

        if (isViewAttach()) {
            getView().getListDataSuccess(listBeans);
        }
    }

    private void getListDataCode3(int page){

        List<GoodsInfoBean> listBeans = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            GoodsInfoBean bean = new GoodsInfoBean();
            bean.setSellPrice(2.5f);
            bean.setSellPriceDiscount(2.3f);
            bean.setGoodsName("商品名称2");
            bean.setGoodsImage(ImgPaths.path[i+20]);
            bean.setId(i+100);
            listBeans.add(bean);
        }

        if (isViewAttach()) {
            getView().getListDataSuccess(listBeans);
        }
    }

    private void getListDataCode101(int page){

        List<EvaluateInfoBean> listBeans = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            EvaluateInfoBean bean = new EvaluateInfoBean();
            listBeans.add(bean);
        }

        if (isViewAttach()) {
            getView().getListDataSuccess(listBeans);
        }
    }

    //商品评价
    public void getListDataEvaluate(int goodsId, int page,int pageSize){
        ReqGoodsInfo2 info2 = new ReqGoodsInfo2();
        info2.setGoodsId(goodsId);
        info2.setPage(page);
        info2.setPageSize(pageSize);
        model.requestCommentList(info2, new TObserver<DemoRespBean<List<EvaluateInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<EvaluateInfoBean>> bean) {
                if(responseState(bean)){
                    if(isViewAttach()){
                        getView().getListDataSuccess(bean.getData());
                    }
                }
            }
        });
    }

//    粉丝
    public void getListDataFens(int page,int pageSize){
        ReqPageInfo info = new ReqPageInfo();
        info.setPage(page);
        info.setPageSize(pageSize);
        model.requestUserFans(info, new TObserver<DemoRespBean<List<FensInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<FensInfoBean>> bean) {
                if(responseState(bean)){
                    if(isViewAttach()){
                        getView().getListDataSuccess(bean.getData());
                    }
                }
            }
        });
    }

    // 充值记录
    public void getListDataPayRecord(int page , int pageSize){
        ReqPageInfo info = new ReqPageInfo();
        info.setPage(page);
        info.setPageSize(pageSize);
        model.requestPayRecordList(info, new TObserver<DemoRespBean<List<PayOrderInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<PayOrderInfoBean>> bean) {
                if(responseState(bean)){

                    if(isViewAttach()){
                        getView().getListDataSuccess(bean.getData());
                    }
                }
            }
        });
    }

    public void getListDataShouy(int page,int pageSize){
        ReqPageInfo info = new ReqPageInfo();
        info.setPage(page);
        info.setPageSize(pageSize);
        model.requestUserMoneyRecordList(info, new TObserver<DemoRespBean<List<MoneyRecordListInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<MoneyRecordListInfoBean>> bean) {
                if(responseState(bean)){
                    if(isViewAttach()){
                        getView().getListDataSuccess(bean.getData());
                    }
                }
            }
        });
    }

    public void getListDataApply(int page,int pageSize){
        ReqPageInfo info = new ReqPageInfo();
        info.setPage(page);
        info.setPageSize(pageSize);
        model.requestApplyMoneyRecordList(info, new TObserver<DemoRespBean<List<MoneyRecordApplyListInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<MoneyRecordApplyListInfoBean>> bean) {
                if(responseState(bean)){
                    if(isViewAttach()){
                        getView().getListDataSuccess(bean.getData());
                    }
                }
            }
        });
    }




}
