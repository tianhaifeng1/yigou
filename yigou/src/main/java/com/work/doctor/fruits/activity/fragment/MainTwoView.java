package com.work.doctor.fruits.activity.fragment;

import com.t.httplib.yigou.bean.resp.GoodsTypeOutInfoBean;
import com.trjx.tbase.mvp.TView;

public interface MainTwoView extends TView {

    /**
     * 获取商品类型
     */
    void getShopTypeSuccess(GoodsTypeOutInfoBean infoBean);

}
