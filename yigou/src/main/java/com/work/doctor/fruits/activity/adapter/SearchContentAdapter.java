package com.work.doctor.fruits.activity.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.t.httplib.yigou.bean.resp.SearchGoodsInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.util.List;

public abstract class SearchContentAdapter extends TRecyclerAdapter<SearchGoodsInfoBean> {

    public SearchContentAdapter(@Nullable List<SearchGoodsInfoBean> data) {
        super( data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_searchgoods;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchGoodsInfoBean item) {

        helper.setText(R.id.item_searchgoods_title, item.getTitle());
        helper.addOnClickListener(R.id.item_searchgoods_clear);

        int code = item.getCode();
        if(code == 2){
            helper.getView(R.id.item_searchgoods_clear).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.item_searchgoods_clear).setVisibility(View.GONE);
        }

        RecyclerView recyclerView = helper.getView(R.id.item_searchgoods_recyclerview);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(mContext){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        SearchContentInAdapter inAdapter = new SearchContentInAdapter(item.getStringList());
        recyclerView.setAdapter(inAdapter);
        inAdapter.setOnItemClickListener((adapter, view, position) -> onItemChildClickListener((String) adapter.getData().get(position)));

    }

    public abstract void onItemChildClickListener(String strName);

}