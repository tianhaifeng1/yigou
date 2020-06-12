package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.GoodsTypeInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.util.List;

/**
 * 首页分类
 */
public class ShopTypeAdapter extends TRecyclerAdapter<GoodsTypeInfoBean> {

    public ShopTypeAdapter(@Nullable List<GoodsTypeInfoBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_shoptype;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsTypeInfoBean item) {
        helper.setText(R.id.item_shoptype_name,item.getCnname());
        GlideUtile.bindImageView(mContext, item.getImage(), helper.getView(R.id.item_shoptype_icon));
    }

}
