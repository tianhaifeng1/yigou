package com.work.doctor.fruits.activity.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.AddressInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.util.List;

public class AddressAdapter extends TRecyclerAdapter<AddressInfoBean> {

    public AddressAdapter(@Nullable List<AddressInfoBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_addresslist;
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressInfoBean item) {

        helper.setText(R.id.item_address_name, item.getName());
        helper.setText(R.id.item_address_phone, item.getPhone());
        helper.setText(R.id.item_address_address, item.getProvince() + item.getCity() + item.getCounty() + item.getAddress() + item.getDetailAddress());

        helper.addOnClickListener(R.id.item_address_editor, R.id.item_address_event, R.id.item_address_event_copy, R.id.item_address_event_default, R.id.item_address_event_delete);

        int isDefault = item.getIsDefault();
        if (isDefault == 1) {
            helper.setText(R.id.item_address_event_default, "[默认地址]");
            helper.getView(R.id.item_address_event_default).setEnabled(false);
            helper.setTextColor(R.id.item_address_event_default,mContext.getResources().getColor(R.color.textcolor_point));
        } else {
            helper.setText(R.id.item_address_event_default, "[设为默认]");
            helper.getView(R.id.item_address_event_default).setEnabled(true);
            helper.setTextColor(R.id.item_address_event_default,mContext.getResources().getColor(R.color.color_name));
        }

        if (item.isShowEventView()) {
            helper.getView(R.id.item_address_event).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.item_address_event).setVisibility(View.GONE);
        }

    }

}
