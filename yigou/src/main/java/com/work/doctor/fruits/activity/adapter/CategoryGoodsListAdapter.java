package com.work.doctor.fruits.activity.adapter;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.CategoryGoodsBean;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.Goods.GoodsDetial2Activity;

import java.util.List;

public class CategoryGoodsListAdapter extends TRecyclerAdapter<CategoryGoodsBean> {

    public ShoppingJrtmAdapter adapterJrtm;

    public CategoryGoodsListAdapter(@Nullable List<CategoryGoodsBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryGoodsBean item) {
        GlideUtile.bindImageView(mContext, item.getCategoryIcon(), helper.getView(R.id.item_activity_image));

        helper.getView(R.id.item_activity_text).setVisibility(View.VISIBLE);
        helper.setText(R.id.item_activity_text,item.getCnname());
        helper.addOnClickListener(R.id.category_footview);

        adapterJrtm = new ShoppingJrtmAdapter(item.getGoods(), false);
        RecyclerView recyclerView = helper.getView(R.id.item_activity_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;//禁止垂直滚动
            }
        });
        adapterJrtm.setOnItemClickListener((adapter, view, position) -> {
            GoodsInfoBean infoBean = (GoodsInfoBean) adapter.getData().get(position);
            Intent intent = new Intent(mContext, GoodsDetial2Activity.class);
            intent.putExtra("id", infoBean.getId());
            mContext.startActivity(intent);
        });
        adapterJrtm.setOnItemChildClickListener((adapter, view, position) -> {
            CategoryGoodsListAdapter.this.getOnItemChildClickListener().onItemChildClick(adapter, view, position);
        });

        recyclerView.setAdapter(adapterJrtm);

    }



    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_activitygoodslist;
    }
}
