package com.work.doctor.fruits.activity.adapter;

import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.GoodsTypeInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.util.List;

public class ShopTypeRightTitleAdapter extends TRecyclerAdapter<GoodsTypeInfoBean> {

    public ShopTypeRightTitleAdapter(@Nullable List<GoodsTypeInfoBean> data) {
        super(data);
        showTitle = true;
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_type_title;
    }

    private boolean showTitle;

    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsTypeInfoBean item) {

        helper.addOnClickListener(R.id.type_title_rl);

        if (showTitle) {
            helper.getView(R.id.type_title_rl).setVisibility(View.VISIBLE);
            helper.setText(R.id.type_title_text, item.getCnname());
        } else {
            helper.getView(R.id.type_title_rl).setVisibility(View.GONE);
        }

        List<GoodsTypeInfoBean> childOneList = item.getChildList();
        ShopTypeRightContentAdapter inAdapter = new ShopTypeRightContentAdapter(childOneList);
        RecyclerView recyclerView = helper.getView(R.id.type_title_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        int height = 0;
        if (childOneList != null && childOneList.size() > 0) {
            height = (int) (mContext.getResources().getDimensionPixelOffset(R.dimen.recycler_in_item_hh) * (Math.ceil(childOneList.size() / 3.0f)));
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
        recyclerView.setLayoutParams(layoutParams);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(inAdapter);
        inAdapter.setOnItemClickListener((adapter, view, position) -> {
            ShopTypeRightTitleAdapter.this.getOnItemChildClickListener().onItemChildClick(adapter, view, position);
        });

    }

}
