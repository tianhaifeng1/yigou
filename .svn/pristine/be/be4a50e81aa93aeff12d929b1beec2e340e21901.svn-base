package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.PayListInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.util.List;

/**
 * 支付列表
 */
public class PayListAdapter extends TRecyclerAdapter<PayListInfoBean> {

    public PayListAdapter(@Nullable List<PayListInfoBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_paylist;
    }

    @Override
    protected void convert(BaseViewHolder helper, PayListInfoBean item) {
        helper.setText(R.id.item_paylist_number, BigDecimalUtil.roundOffString(item.getPayMoney(), 2));
        helper.setText(R.id.item_paylist_real_number, "售价 " + BigDecimalUtil.roundOffString(item.getSellPrice(),2)+"元");
    }

}
