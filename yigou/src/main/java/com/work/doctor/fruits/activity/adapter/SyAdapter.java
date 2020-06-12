package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.MoneyRecordListInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SyAdapter extends TRecyclerAdapter<MoneyRecordListInfoBean> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public SyAdapter(@Nullable List<MoneyRecordListInfoBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_czrecord;
    }

    @Override
    protected void convert(BaseViewHolder helper, MoneyRecordListInfoBean item) {
        helper.setText(R.id.item_czrecord_money, BigDecimalUtil.roundOffString(item.getMoneyFee(), 2));
        helper.setText(R.id.item_czrecord_title, item.getTitle());
        helper.setText(R.id.item_czrecord_datetime, dateFormat.format(new Date(item.getEnterTime())));
    }

}