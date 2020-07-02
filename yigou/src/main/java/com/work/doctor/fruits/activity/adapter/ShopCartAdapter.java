package com.work.doctor.fruits.activity.adapter;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.databaselib.BigDecimalUtil;
import com.t.databaselib.DatabaseGoodsInfo;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.trjx.tlibs.uils.TextStyleHelper;
import com.work.doctor.fruits.R;

import java.util.List;

public class ShopCartAdapter extends TRecyclerAdapter<DatabaseGoodsInfo> {

    public ShopCartAdapter(@Nullable List<DatabaseGoodsInfo> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DatabaseGoodsInfo item) {

        helper.addOnClickListener(R.id.item_shopcart_select, R.id.item_shopcart_add, R.id.item_shopcart_minus);

        helper.setText(R.id.item_shopcart_num, item.getGoodsNumber() + "");

        helper.setText(R.id.item_shopcart_guige, item.getSpecName());
        helper.setText(R.id.item_shopcart_price, "￥" + BigDecimalUtil.roundOffString(item.getGoodsPrice(), 2));
        helper.setText(R.id.item_shopcart_price_vip, "￥" + BigDecimalUtil.roundOffString(item.getGoodsPriceVip(), 2));
        TextView textView = helper.getView(R.id.item_shopcart_price);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        if (item.getGoodsTotal() <= item.getGoodsNumber() && item.getGoodsTotal()!=0) {
            helper.getView(R.id.item_shopcart_remind).setVisibility(View.VISIBLE);

            String str = "";
            int goodsType = item.getGoodsType();
            if (goodsType == 3) {
                if(item.getGoodsTotal() != 0){
                    str = "此商品限购 " + item.getGoodsTotal() + " 件";
                }
            } else if (item.getIsTemai() == 0) {
                //特卖商品
                if (item.getSpecialSale() == 1) {
                    str = "此商品为限购商品，已购买";
                } else {
                    str = "此商品限购 1 件";
                }
            } else {
//                普通商品
                if (item.getGoodsTotal() == 0) {
                    str = "此商品暂无库存";
                } else {
                    str = "当前库存仅剩 " + item.getGoodsTotal() + " 件";
                }
            }
            helper.setText(R.id.item_shopcart_remind, str);
            helper.getView(R.id.item_shopcart_add).setEnabled(false);
            GlideUtile.bindImageView(mContext, R.mipmap.goodscart_jia_default, helper.getView(R.id.item_shopcart_add));
        } else if(item.getStock()<=item.getGoodsNumber()) {
            helper.getView(R.id.item_shopcart_remind).setVisibility(View.VISIBLE);
            String str = "";
            if (item.getStock() == 0) {
                str = "此商品暂无库存";
            } else {
                str = "当前库存仅剩 " + item.getStock() + " 件";
            }
            helper.setText(R.id.item_shopcart_remind, str);
            helper.getView(R.id.item_shopcart_add).setEnabled(false);
            GlideUtile.bindImageView(mContext, R.mipmap.goodscart_jia_default, helper.getView(R.id.item_shopcart_add));
        } else {
            helper.getView(R.id.item_shopcart_remind).setVisibility(View.GONE);
            helper.getView(R.id.item_shopcart_add).setEnabled(true);
            GlideUtile.bindImageView(mContext, R.mipmap.goodscart_jia, helper.getView(R.id.item_shopcart_add));
        }
        if (item.getGoodsNumber() > 1) {
            helper.getView(R.id.item_shopcart_minus).setEnabled(true);
            GlideUtile.bindImageView(mContext, R.mipmap.goodscart_jian, helper.getView(R.id.item_shopcart_minus));
        } else {
            helper.getView(R.id.item_shopcart_minus).setEnabled(false);
            GlideUtile.bindImageView(mContext, R.mipmap.goodscart_jian_default, helper.getView(R.id.item_shopcart_minus));
        }

        GlideUtile.bindImageView(mContext, item.getGoodsUrl(), helper.getView(R.id.item_shopcart_icon));

        boolean select = item.getIsSelect();
        if (select) {
            GlideUtile.bindImageView(mContext, R.mipmap.goodscart_select, helper.getView(R.id.item_shopcart_select));
        } else {
            GlideUtile.bindImageView(mContext, R.mipmap.round_n, helper.getView(R.id.item_shopcart_select));
        }


        if (item.getGoodsType() == 3) {
            if(item.getGoodsTotal()==0){
                helper.setText(R.id.item_shopcart_tm, "预售商品");
            }else{
                helper.setText(R.id.item_shopcart_tm, "预售商品(限购" + item.getGoodsTotal() + ")");
            }

            helper.getView(R.id.item_shopcart_tm).setVisibility(View.VISIBLE);
//            helper.setText(R.id.item_shopcart_name, "【预售】" + item.getGoodsName());
            String strName = "【预售】" + item.getGoodsName();
            helper.setText(R.id.item_shopcart_name, new TextStyleHelper(strName)
                    .addForeColorSpan(Color.parseColor("#ff0000"), 0, 4)
                    .show());
        } else {
            helper.setText(R.id.item_shopcart_name, item.getGoodsName());
            if (item.getIsTemai() == 0) {
                helper.setText(R.id.item_shopcart_tm, "此商品一天仅限购一件");
                helper.getView(R.id.item_shopcart_tm).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.item_shopcart_tm).setVisibility(View.GONE);
            }
        }

    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_shopcart;
    }
}
