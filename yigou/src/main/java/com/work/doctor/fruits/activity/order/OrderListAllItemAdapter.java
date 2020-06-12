package com.work.doctor.fruits.activity.order;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.OrderDetailInfoShopInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.util.List;

public class OrderListAllItemAdapter extends TRecyclerAdapter<OrderDetailInfoShopInfoBean> {

    public OrderListAllItemAdapter(@Nullable List<OrderDetailInfoShopInfoBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetailInfoShopInfoBean item) {

        GlideUtile.bindImageView(mContext, item.getGoodsImage(), R.mipmap.qd_bg_default, helper.getView(R.id.item_orderlist_item_shop_icon));
        helper.setText(R.id.item_orderlist_item_shop_name, item.getGoodsName());
        helper.setText(R.id.item_orderlist_item_shop_price, "￥" + item.getSellPrice().floatValue());
        helper.setText(R.id.item_orderlist_item_shop_guige, item.getAttrStrValue());
        helper.setText(R.id.item_orderlist_item_shop_number, "×" + item.getTotalNum());

//        if (item.getIsTemai() == 0) {
//            helper.getView(R.id.item_orderlist_item_shop_tm).setVisibility(View.VISIBLE);
//        }else{
//            helper.getView(R.id.item_orderlist_item_shop_tm).setVisibility(View.GONE);
//        }
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_orderlist_item;
    }
}