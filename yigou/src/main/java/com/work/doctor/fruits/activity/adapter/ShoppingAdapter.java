package com.work.doctor.fruits.activity.adapter;

import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.util.List;

public class ShoppingAdapter extends TRecyclerAdapter<GoodsInfoBean> {

    public ShoppingAdapter(@Nullable List<GoodsInfoBean> data) {
        super( data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_shop;
    }
    @Override
    protected void convert(BaseViewHolder helper, GoodsInfoBean item) {
        helper.setText(R.id.item_shop_name,item.getGoodsName());
        helper.setText(R.id.item_shop_price, "￥" + BigDecimalUtil.roundOffString(item.getSellPriceDiscount(), 2));
        helper.setText(R.id.item_shop_price2, "￥" + BigDecimalUtil.roundOffString(item.getSellPrice(),2));
        TextView textView = helper.getView(R.id.item_shop_price2);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        GlideUtile.bindImageView(mContext, item.getGoodsImage(), helper.getView(R.id.item_shop_icon));

        if (item.getStock() <= 0) {
            GlideUtile.bindImageView(mContext, R.mipmap.shopping_cart_, helper.getView(R.id.item_shop_cart));
            helper.getView(R.id.item_shop_masking).setVisibility(View.VISIBLE);
        }else{
            GlideUtile.bindImageView(mContext, R.mipmap.shopping_cart, helper.getView(R.id.item_shop_cart));
            helper.getView(R.id.item_shop_masking).setVisibility(View.GONE);
            helper.addOnClickListener(R.id.item_shop_cart);
        }

    }
}
