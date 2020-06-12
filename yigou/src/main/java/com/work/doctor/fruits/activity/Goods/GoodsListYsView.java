package com.work.doctor.fruits.activity.Goods;

import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tbase.mvp.TView;

import java.util.List;

public interface GoodsListYsView extends TView {

//    获取商品列表数据
    void getYshouDataSuccess(List<?> list);

    //    获取商品规格数据
    void getGoodsSpecSuccess(GoodsSpecInfoBean infoBean);

    //    添加数据到购物车成功
    void addGoodsToShoppingCartSuccess();

}
