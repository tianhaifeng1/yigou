package com.work.doctor.fruits.activity.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.work.doctor.fruits.R;

@Deprecated
public class CzViewHolder extends BaseViewHolder {
     TextView mItemCzrecordMoney;
     TextView mItemCzrecordTitle;
     TextView mItemCzrecordDatetime;

    public CzViewHolder(View view) {
        super(view);

        mItemCzrecordMoney = view.findViewById(R.id.item_czrecord_money);
        mItemCzrecordTitle = view.findViewById(R.id.item_czrecord_title);
        mItemCzrecordDatetime = view.findViewById(R.id.item_czrecord_datetime);

    }

}
