package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.DetailsBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.util.List;

public class ShopCartAdapter2 extends TRecyclerAdapter<DetailsBean> {


    public ShopCartAdapter2(@Nullable List<DetailsBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DetailsBean item) {
        helper.setText(R.id.item_shopcart_name, item.getGoodsName());
        helper.setText(R.id.item_shopcart_guige, "");
        helper.setText(R.id.item_shopcart_num, "×" + item.getTotalNum());
        if(item.getGoodsType()==1){
            helper.setText(R.id.item_shopcart_price, "[签到赠送商品]");
        }else{
            helper.setText(R.id.item_shopcart_price, "￥" + BigDecimalUtil.roundOffString(item.getTotalFee(),2));
        }
        GlideUtile.bindImageView(mContext, item.getGoodsImage(), R.mipmap.qd_bg_default, helper.getView(R.id.item_shopcart_icon));
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_shopcart2;
    }
}
