package com.xzte.maplib.baidu.adapter;

import androidx.annotation.Nullable;

import com.baidu.mapapi.search.core.PoiInfo;
import com.chad.library.adapter.base.BaseViewHolder;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.xzte.maplib.R;

import java.util.List;

public class MapDataAdapter extends TRecyclerAdapter<PoiInfo> {

    public MapDataAdapter(@Nullable List<PoiInfo> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PoiInfo item) {
        helper.setText(R.id.item_text1, item.getName());
        helper.setText(R.id.item_text2, item.getAddress());
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_mapdata;
    }
}
