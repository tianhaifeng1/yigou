package com.xzte.maplib.baidu.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.databaselib.DatabaseCityInfo;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.xzte.maplib.R;

import java.util.List;

public class HistroyDataAdapter extends TRecyclerAdapter<DatabaseCityInfo> {

    public HistroyDataAdapter(@Nullable List<DatabaseCityInfo> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DatabaseCityInfo item) {
        helper.setText(R.id.item_text1, item.getName());
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_histroydata;
    }
}
