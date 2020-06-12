package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.GoodsTypeInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.util.List;

public class ShopTypeRightContentAdapter extends TRecyclerAdapter<GoodsTypeInfoBean> {

    public ShopTypeRightContentAdapter(@Nullable List<GoodsTypeInfoBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_type_content;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsTypeInfoBean item) {
        helper.setText(R.id.type_content_text,item.getCnname());
        GlideUtile.bindImageView(mContext, item.getImage(), helper.getView(R.id.type_content_img));
    }

}
