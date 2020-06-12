package com.work.doctor.fruits.activity.Goods;

import com.t.httplib.yigou.bean.resp.CategoryGoodsBean;
import com.trjx.tbase.mvp.TView;

import java.util.List;

public interface CategoryGoodsListView extends TView {

    void getCategoryGoodsListSuccess(List<CategoryGoodsBean> list);
}
