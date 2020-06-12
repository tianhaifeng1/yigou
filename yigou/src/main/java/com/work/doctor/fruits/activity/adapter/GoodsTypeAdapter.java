package com.work.doctor.fruits.activity.adapter;

import android.graphics.Color;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.GoodsTypeInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.util.List;

public class GoodsTypeAdapter extends TRecyclerAdapter<GoodsTypeInfoBean> {

    public GoodsTypeAdapter(@Nullable List<GoodsTypeInfoBean> data) {
        super( data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_selecttype;
    }
    @Override
    protected void convert(BaseViewHolder helper, GoodsTypeInfoBean item) {

        helper.setText(R.id.item_name, item.getCnname());
        if(item.isSelect()){
            helper.setTextColor(R.id.item_name, Color.parseColor("#fa2f49"));
            helper.setBackgroundRes(R.id.item_name, R.drawable.shape_goodstype2);
        }else{
            helper.setTextColor(R.id.item_name, Color.parseColor("#666666"));
            helper.setBackgroundRes(R.id.item_name, R.drawable.shape_goodstype1);
        }

    }

}