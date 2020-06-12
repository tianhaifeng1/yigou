package com.work.doctor.fruits.activity.Goods;

import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tbase.mvp.TView;

import java.util.List;

public interface GoodsListMOMoreView extends TView {

    void getListDataSuccess(List<GoodsInfoBean> list);

    //    获取商品规格数据
    void getGoodsSpecSuccess(GoodsSpecInfoBean infoBean);

    //    添加数据到购物车成功
    void addGoodsToShoppingCartSuccess();

}
