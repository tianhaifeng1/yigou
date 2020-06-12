package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.databaselib.BigDecimalUtil;
import com.t.databaselib.DatabaseGoodsInfo;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.util.List;

public class ShopCartAdapter2 extends TRecyclerAdapter<DatabaseGoodsInfo> {

    public ShopCartAdapter2(@Nullable List<DatabaseGoodsInfo> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DatabaseGoodsInfo item) {

        helper.setText(R.id.item_shopcart_name, item.getGoodsName());
        helper.setText(R.id.item_shopcart_guige, item.getSpecName());
        helper.setText(R.id.item_shopcart_num, "×" + item.getGoodsNumber());
        helper.setText(R.id.item_shopcart_price, "￥" + BigDecimalUtil.roundOffString(item.getGoodsPriceVip(),2));
        GlideUtile.bindImageView(mContext, item.getGoodsUrl(), R.mipmap.qd_bg_default, helper.getView(R.id.item_shopcart_icon));

    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_shopcart2;
    }
}