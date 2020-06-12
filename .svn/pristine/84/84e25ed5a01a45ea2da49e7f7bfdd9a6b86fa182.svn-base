package com.work.doctor.fruits.activity.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.trjx.tbase.adapter.TBaseAdapter2;
import com.trjx.tbase.adapter.TBaseViewHolder2;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.util.List;

public abstract class YgSearchAdapter extends TBaseAdapter2<GoodsInfoBean, YgSearchAdapter.YgSearchViewHolder> {

    public YgSearchAdapter(Context context, List<GoodsInfoBean> ygSearchInfoBeans) {
        super(context, ygSearchInfoBeans);
    }

    @Override
    public int initLayout() {
        return R.layout.item_search_shop;
    }

    @Override
    public YgSearchViewHolder initViewHolder(View convertView) {
        return new YgSearchViewHolder(convertView);
    }

    @Override
    public void bindView(int position, YgSearchViewHolder viewHolder) {
        GoodsInfoBean infoBean = tList.get(position);

        GlideUtile.bindImageView(context, infoBean.getGoodsImage(), viewHolder.mItemShopIcon);

        viewHolder.mItemShopName.setText(infoBean.getGoodsName());
        viewHolder.mItemShopPrice.setText("￥" + BigDecimalUtil.roundOffString(infoBean.getSellPriceDiscount(), 2));
        viewHolder.mItemShopCart.setOnClickListener(v -> onClickCart(infoBean, position));

    }

    public abstract void onClickCart(GoodsInfoBean infoBean, int position);


    public static class YgSearchViewHolder extends TBaseViewHolder2 {

        ImageView mItemShopIcon;
        ImageView mItemShopCart;
        TextView mItemShopName;
        TextView mItemShopPrice;

        public YgSearchViewHolder(View view) {
            super(view);
            mItemShopIcon = view.findViewById(R.id.item_shop_icon);
            mItemShopCart = view.findViewById(R.id.item_shop_cart);
            mItemShopName = view.findViewById(R.id.item_shop_name);
            mItemShopPrice = view.findViewById(R.id.item_shop_price);
        }


    }

}
