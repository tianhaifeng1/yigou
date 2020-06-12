package com.work.doctor.fruits.dialog;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.util.List;

/**
 * 规格
 */
public class GoodsSpecInfoAdapter extends TRecyclerAdapter<GoodsSpecInfoBean.GoodsItemListBean.SpecListBean> {

    public GoodsSpecInfoAdapter(@Nullable List<GoodsSpecInfoBean.GoodsItemListBean.SpecListBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.dialog_goods_specinfo_item;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsSpecInfoBean.GoodsItemListBean.SpecListBean item) {
        helper.setText(R.id.item_goods_si_tv,item.getAttrValue());
        if(item.isSelect()){
            helper.setBackgroundRes(R.id.item_goods_si_tv, R.drawable.shape_goodspec2);
        }else{
            helper.setBackgroundRes(R.id.item_goods_si_tv, R.drawable.shape_goodspec1);
        }
    }

}
