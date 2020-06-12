package com.work.doctor.fruits.activity.search;

import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tbase.mvp.TView;

import java.util.List;

public interface SearchGoodsView extends TView {

    void getSearchPopularGoodsListSuccess(List<String> list);
    void getSearchInfoSuccess(List<GoodsInfoBean> list);

    //    商品规格
    void getGoodsItemSuccess(GoodsSpecInfoBean specInfoBean,int position);

    //    添加数据到购物车成功
    void addGoodsToShoppingCartSuccess();

}
