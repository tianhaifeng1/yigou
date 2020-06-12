package com.work.doctor.fruits.activity;

import com.t.httplib.yigou.bean.TestBean;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.trjx.tbase.mvp.TView;

import java.util.List;

public interface MainView extends TView {

    void testSuccess(TestBean testBean);
    void test2Success(List<GoodsInfoBean> goodsList);
    void test3Success(GoodsInfoBean goodsInfoBean);

}
