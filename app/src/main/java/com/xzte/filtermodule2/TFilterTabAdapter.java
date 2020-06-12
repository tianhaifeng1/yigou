package com.xzte.filtermodule2;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzte.R;

import java.util.List;

/**
 * 作者：小童
 * 创建时间：2019/8/22 17:13
 */
public class TFilterTabAdapter<T extends TFilterTabInfo> extends BaseQuickAdapter<T,BaseViewHolder> {

    public TFilterTabAdapter(@Nullable List<T> data) {
        super(data);
        mLayoutResId = R.layout.item_filter_tab;
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        helper.setText(R.id.item_filter_tab_text, item.getFilterTabName());
        int state = item.getFilterTabSelectState();
        int[] iconRes = item.getFilterTabIconRes();
        if(state == 0){
            helper.setTextColor(R.id.item_filter_tab_text, mContext.getResources().getColor(R.color.filter_tab_textcolor));
            ((ImageView) helper.getView(R.id.item_filter_tab_icon)).setImageResource(iconRes[0]);
        }else if (state == 1){
            helper.setTextColor(R.id.item_filter_tab_text, mContext.getResources().getColor(R.color.filter_tab_select_textcolor));
            ((ImageView) helper.getView(R.id.item_filter_tab_icon)).setImageResource(iconRes[1]);
        }else if (state == 2){
            helper.setTextColor(R.id.item_filter_tab_text, mContext.getResources().getColor(R.color.filter_tab_select_textcolor));
            ((ImageView) helper.getView(R.id.item_filter_tab_icon)).setImageResource(iconRes[2]);
        }

    }
}
