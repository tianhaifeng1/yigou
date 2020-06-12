package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.baidu.mapapi.search.core.PoiInfo;
import com.chad.library.adapter.base.BaseViewHolder;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.util.List;

/**
 * 地址附近
 */
public class AddressFjAdapter extends TRecyclerAdapter<PoiInfo> {

    public AddressFjAdapter(@Nullable List<PoiInfo> data) {
        super( data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_address_fj;
    }
    @Override
    protected void convert(BaseViewHolder helper, PoiInfo item) {
        helper.setText(R.id.item_address_fj_text, item.getName());
    }

}
