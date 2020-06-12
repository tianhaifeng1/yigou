package com.work.doctor.fruits.assist.filtermodule2;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.work.doctor.fruits.R;

import java.util.List;

/**
 * 作者：小童
 * 创建时间：2019/8/22 17:13
 */
public class TFilterItemAdapter<T extends TFilterItemInfo> extends BaseQuickAdapter<T,BaseViewHolder> {


    // 增加条目标识
    private boolean tag;

    public TFilterItemAdapter(@Nullable List<T> data) {
        super(data);
        mLayoutResId = R.layout.item_filter_item;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        helper.setText(R.id.item_filter_item_text, item.getFilterItemName());

        if(tag){
            if(item.getIsSelelct()){
//                helper.setBackgroundRes(R.id.item_filter_item_text, R.color.filter_item_select_bg);
                helper.getView(R.id.item_filter_item_icon).setVisibility(View.VISIBLE);
                helper.setTextColor(R.id.item_filter_item_text, mContext.getResources().getColor(R.color.filter_item_select_textcolor));
            }else{
//                helper.setBackgroundRes(R.id.item_filter_item_text, R.color.filter_item_bg);
                helper.getView(R.id.item_filter_item_icon).setVisibility(View.GONE);
                helper.setTextColor(R.id.item_filter_item_text, mContext.getResources().getColor(R.color.filter_item_textcolor));
            }
        }

    }
}
