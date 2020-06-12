package com.work.doctor.fruits.dialog;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.AddressInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.util.List;

/**
 * 规格
 */
public class DialogAddressSelectAdapter extends TRecyclerAdapter<AddressInfoBean> {

    public DialogAddressSelectAdapter(@Nullable List<AddressInfoBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.dialog_address_select_item;
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressInfoBean item) {
        helper.setText(R.id.dialog_address_select_item_address, item.getAddress() + item.getDetailAddress());
        helper.setText(R.id.dialog_address_select_item_name,item.getName());
        helper.setText(R.id.dialog_address_select_item_phone,item.getPhone());

        if(item.isSelect()){
            helper.setImageResource(R.id.dialog_address_select_item_select, R.mipmap.round);
        }else{
            helper.setImageResource(R.id.dialog_address_select_item_select, R.mipmap.round_n);
        }

        helper.addOnClickListener(R.id.dialog_address_select_item_select);
    }

}
