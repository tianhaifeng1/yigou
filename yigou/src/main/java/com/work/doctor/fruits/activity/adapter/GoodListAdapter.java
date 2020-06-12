package com.work.doctor.fruits.activity.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsListInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.Goods.GoodsDetial2Activity;

import java.util.List;

public class GoodListAdapter extends TRecyclerAdapter<GoodsListInfoBean> {


    public GoodListAdapter(@Nullable List<GoodsListInfoBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsListInfoBean item) {
        GlideUtile.bindImageView(mContext, item.getActivityIcon(), helper.getView(R.id.item_activity_image));

        helper.addOnClickListener(R.id.item_activity_image);

        List<GoodsInfoBean> list = item.getGoods();
        RecyclerView recyclerView = helper.getView(R.id.item_activity_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        ActivityGoodsListAdapter mAdapter = new ActivityGoodsListAdapter(list);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            GoodsInfoBean infoBean = (GoodsInfoBean) adapter.getData().get(position);
            Intent intent = new Intent(mContext, GoodsDetial2Activity.class);
            intent.putExtra("id", infoBean.getId());
            mContext.startActivity(intent);
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            GoodListAdapter.this.getOnItemChildClickListener().onItemChildClick(adapter, view, position);
        });


        recyclerView.setAdapter(mAdapter);

    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_activitygoodslist;
    }
}
