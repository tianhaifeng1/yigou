package com.work.doctor.fruits.activity.adapter;

import android.graphics.Color;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.PayOrderInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CzListAdapter extends TRecyclerAdapter<PayOrderInfoBean> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public CzListAdapter(@Nullable List<PayOrderInfoBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_czrecord;
    }

    @Override
    protected void convert(BaseViewHolder helper, PayOrderInfoBean item) {
        String string1 = "＋" +BigDecimalUtil.roundOffString(item.getPayMoney(), 2);
        String string2 = "售价" + BigDecimalUtil.roundOffString(item.getSellPrice(), 2);
        helper.setText(R.id.item_czrecord_money, string1);
        helper.setTextColor(R.id.item_czrecord_money, Color.parseColor("#333333"));
        helper.setText(R.id.item_czrecord_money_sm, string2);
        helper.getView(R.id.item_czrecord_money_sm).setVisibility(View.VISIBLE);
//        ０．申请；１．成功；２．失败
        String titleStr = "";
        int status = item.getStatus();
        int payWay = item.getPayWay();
        if(payWay == 1){
            titleStr = "支付宝充值";
        }else if(payWay == 2){
            titleStr = "微信充值";
        }else if(payWay == 3){
            titleStr = "收益转余额";
        }else if(payWay == 6){
            titleStr = "银行卡充值";
        }else if(payWay == 4){
            titleStr = "余额退款";
        }

        if(status == 1){
            titleStr =  titleStr + "成功";
        }else {
            titleStr =  titleStr + "失败";
        }
        helper.setText(R.id.item_czrecord_title, titleStr);
        helper.setText(R.id.item_czrecord_datetime, dateFormat.format(new Date(item.getCreateTime())));
    }

}
