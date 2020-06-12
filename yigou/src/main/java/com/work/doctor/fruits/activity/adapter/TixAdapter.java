package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.MoneyRecordApplyListInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class TixAdapter extends TRecyclerAdapter<MoneyRecordApplyListInfoBean> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public TixAdapter(@Nullable List<MoneyRecordApplyListInfoBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_record_tx;
    }

    @Override
    protected void convert(BaseViewHolder helper, MoneyRecordApplyListInfoBean infoBean) {

        helper.setText(R.id.item_record_tx_money, BigDecimalUtil.roundOffString(infoBean.getMoneyFee(), 2) + "元");
        helper.setText(R.id.item_record_tx_title, "提现到" + infoBean.getTitle());
        helper.setText(R.id.item_record_tx_datetime, dateFormat.format(new Date(infoBean.getApplyTime())));

        int status = infoBean.getStatus();
        String str = "";
        if(status == 0){
            str = "申请中";
        }else if(status == 1){
            str = "提现成功";
        }else if(status == 2){
            str = "提现失败";
        }
        helper.setText(R.id.item_record_tx_status, str);

    }

}
