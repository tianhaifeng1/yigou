package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.util.List;

/**
 * 规格
 */
public class SearchContentInAdapter extends TRecyclerAdapter<String> {

    public SearchContentInAdapter(@Nullable List<String> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_selectbtn;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_name,item);
        helper.setBackgroundRes(R.id.item_name, R.drawable.shape_goodspec1);
    }

}
