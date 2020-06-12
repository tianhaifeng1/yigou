package com.work.doctor.fruits.dialog;

import android.graphics.Color;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.BandcardInterface;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.util.List;

/**
 * 规格
 */
public class DialogBankcardSelectAdapter<T extends BandcardInterface> extends TRecyclerAdapter<T> {

    public DialogBankcardSelectAdapter(@Nullable List<T> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.dialog_bankcard_select_item;
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        helper.setText(R.id.dialog_bankcard_select_item_name, item.getBankcardBankname());
        if(item.getSelect()){
            helper.setTextColor(R.id.dialog_bankcard_select_item_name, Color.parseColor("#ff0000"));
        }else{
            helper.setTextColor(R.id.dialog_bankcard_select_item_name, Color.parseColor("#333333"));
        }

        helper.addOnClickListener(R.id.dialog_bankcard_select_item_name);

    }

}
