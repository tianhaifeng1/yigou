package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.GoodsTypeInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.util.List;

public class ShopTypeLeftAdapter extends TRecyclerAdapter<GoodsTypeInfoBean> {

    public ShopTypeLeftAdapter(@Nullable List<GoodsTypeInfoBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_type_left;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsTypeInfoBean item) {

        helper.setText(R.id.type_left_text, item.getCnname());
        if (item.isSelect()) {
//            helper.setTextColor(R.id.type_left_text, mContext.getResources().getColor(R.color.textcolor_point));
            helper.setBackgroundColor(R.id.type_left_view, mContext.getResources().getColor(R.color.textcolor_point));
            helper.setBackgroundColor(R.id.type_left_text, mContext.getResources().getColor(R.color.color_white));
        }else{
//            helper.setTextColor(R.id.type_left_text, mContext.getResources().getColor(R.color.color_name));
            helper.setBackgroundColor(R.id.type_left_view, mContext.getResources().getColor(R.color.transparent));
            helper.setBackgroundColor(R.id.type_left_text, mContext.getResources().getColor(R.color.transparent));
        }

//        helper.addOnClickListener(R.id.type_left_text);
    }

}
