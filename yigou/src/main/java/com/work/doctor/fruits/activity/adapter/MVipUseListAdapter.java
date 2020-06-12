package com.work.doctor.fruits.activity.adapter;

import android.graphics.Color;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.PayOrderInfoBean2;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MVipUseListAdapter extends TRecyclerAdapter<PayOrderInfoBean2> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public MVipUseListAdapter(@Nullable List<PayOrderInfoBean2> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_czrecord;
    }

    @Override
    protected void convert(BaseViewHolder helper, PayOrderInfoBean2 item) {
//        helper.setText(R.id.item_czrecord_money, "-￥" + BigDecimalUtil.roundOffString(item.getUsePrice(),2));
        helper.setText(R.id.item_czrecord_money, BigDecimalUtil.roundOffString(item.getUsePrice(),2)+"元");
        helper.setTextColor(R.id.item_czrecord_money, Color.parseColor("#333333"));
        helper.setText(R.id.item_czrecord_title, "购买商品");
        helper.setText(R.id.item_czrecord_datetime, dateFormat.format(new Date(item.getCreateTime())));
    }

}