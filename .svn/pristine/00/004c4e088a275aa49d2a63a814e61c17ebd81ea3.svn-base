package com.work.doctor.fruits.activity.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.CouponInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class CouponAdapter extends TRecyclerAdapter<CouponInfoBean> {

    final String PATTERN_YMD_HMS = "yyyy.MM.dd HH:mm";

    private SimpleDateFormat sdf;

    public CouponAdapter(@Nullable List<CouponInfoBean> data) {
        super(data);
        sdf = new SimpleDateFormat(PATTERN_YMD_HMS);
        setState(0);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_coupon;
    }

    private int state = 0;

    public void setState(int state){
        this.state = state;
    }

    public int getState() {
        return state;
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponInfoBean item) {

        if(state == 0){
            helper.getView(R.id.item_coupon_icon).setVisibility(View.GONE);
            helper.getView(R.id.item_coupon_btn).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_coupon_time, "请于" + sdf.format(item.getValidTime()) + "前使用");
            helper.setBackgroundRes(R.id.item_coupon_rl, R.mipmap.item_yhj_bg_left);
        }else if(state == 1){
            helper.getView(R.id.item_coupon_icon).setVisibility(View.VISIBLE);
            helper.setImageResource(R.id.item_coupon_icon, R.mipmap.yishiyong);
            helper.getView(R.id.item_coupon_btn).setVisibility(View.GONE);
            helper.setText(R.id.item_coupon_time, "使用时间"+sdf.format(item.getUseTime()));
            helper.setBackgroundRes(R.id.item_coupon_rl, R.mipmap.item_yhj_bg_left_);

        }else if(state == 2){
            helper.getView(R.id.item_coupon_icon).setVisibility(View.VISIBLE);
            helper.setImageResource(R.id.item_coupon_icon, R.mipmap.yiguoqi);
            helper.getView(R.id.item_coupon_btn).setVisibility(View.GONE);
            helper.setText(R.id.item_coupon_time, "过期时间"+sdf.format(item.getValidTime()));
            helper.setBackgroundRes(R.id.item_coupon_rl, R.mipmap.item_yhj_bg_left_);
        }

        helper.setText(R.id.item_coupon_money, BigDecimalUtil.roundOffString(item.getCountValue(),2));
        String useValue = BigDecimalUtil.roundOffString(item.getUseValue(), 2);
        if (useValue.equals("0")) {
            helper.setText(R.id.item_coupon_term, "无门槛");
        }else{
            helper.setText(R.id.item_coupon_term, "满" + useValue + "元可用");
        }
        helper.setText(R.id.item_coupon_title, item.getName());

        helper.addOnClickListener(R.id.item_coupon_btn);

    }


}
