package com.xzte.maplib.city;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.xzte.maplib.R;

import java.util.List;

/**
 * 热门城市
 */
public class HotCityAdapter extends TRecyclerAdapter<String> {

    public HotCityAdapter(@Nullable List<String> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_city_hot;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_goods_si_tv,item);
    }

}
