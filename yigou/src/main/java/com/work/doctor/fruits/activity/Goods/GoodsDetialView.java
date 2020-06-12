package com.work.doctor.fruits.activity.Goods;

import com.t.httplib.yigou.bean.resp.EvaluateInfoBean;
import com.t.httplib.yigou.bean.resp.GmrOutInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsDetailInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tbase.mvp.TView;

import java.util.List;

public interface GoodsDetialView extends TView {

    //商品详情
    void getGoodsDetailSuccess(GoodsDetailInfoBean infoBean);
//    商品规格

    void getGoodsItemSuccess(GoodsSpecInfoBean infoBean, int dialogState);
    //    添加数据到购物车成功
    void addGoodsToShoppingCartSuccess();

    void getListDataEvaluateSuccess(List<EvaluateInfoBean> list);

    void getGmrDataSuccess(GmrOutInfoBean infoBean);

}