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

public class ShouyAdapter extends TRecyclerAdapter<MoneyRecordListInfoBean> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ShouyAdapter(@Nullable List<MoneyRecordListInfoBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_record_sy;
    }

    @Override
    protected void convert(BaseViewHolder helper, MoneyRecordListInfoBean infoBean) {
        String status = infoBean.getOperate();
        String string = BigDecimalUtil.roundOffString(infoBean.getMoneyFee(), 2) + "元";
        helper.setText(R.id.item_record_sy_money, status.equals("in") ? string : "-" + string);
        helper.setText(R.id.item_record_sy_title, infoBean.getTitle());
        helper.setText(R.id.item_record_sy_datetime, dateFormat.format(new Date(infoBean.getEnterTime())));
    }

}
