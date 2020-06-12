package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.AddressInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.util.List;

public class AddressAdapter2 extends TRecyclerAdapter<AddressInfoBean> {

    public AddressAdapter2(@Nullable List<AddressInfoBean> data) {
        super( data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_addresslist2;
    }
    @Override
    protected void convert(BaseViewHolder helper, AddressInfoBean item) {

        helper.setText(R.id.item_address_name, item.getName());
        helper.setText(R.id.item_address_phone, item.getPhone());
        helper.setText(R.id.item_address_address,item.getProvince() + item.getCity() + item.getCounty() + item.getAddress() + item.getDetailAddress());

        boolean select = item.isSelect();
        if(select){
            helper.setVisible(R.id.item_address_select, true);
            GlideUtile.bindImageView(mContext,R.mipmap.address_select,helper.getView(R.id.item_address_select));
        }else{
            helper.setVisible(R.id.item_address_select, false);
        }
    }

}
