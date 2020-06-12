package com.xzte.test2;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;

import java.util.List;

public class TestBackAdapter extends TRecyclerAdapter<String> {

    public TestBackAdapter(@Nullable List<String> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(android.R.id.text1, item);
    }

    @Override
    protected int addItemLayoutRes() {
        return android.R.layout.simple_list_item_1;
    }
}
