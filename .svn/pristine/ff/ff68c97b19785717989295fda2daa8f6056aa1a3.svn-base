package com.work.doctor.fruits.activity.adapter;

import android.graphics.Color;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.CouponCenterInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class CouponCenterAdapter extends TRecyclerAdapter<CouponCenterInfoBean> {

    final String PATTERN_YMD_HMS = "yyyy.MM.dd HH:mm";

    private SimpleDateFormat sdf;

    public CouponCenterAdapter(@Nullable List<CouponCenterInfoBean> data) {
        super(data);
        sdf = new SimpleDateFormat(PATTERN_YMD_HMS);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_coupon_center;
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponCenterInfoBean item) {

        helper.setText(R.id.item_coupon_money, BigDecimalUtil.roundOffString(item.getCouponValue(), 2));

        String useValue = BigDecimalUtil.roundOffString(item.getUseValue(), 2);
        if (useValue.equals("0")) {
            helper.setText(R.id.item_coupon_term, "无门槛");
        }else{
            helper.setText(R.id.item_coupon_term, "满" + useValue + "元可用");
        }
        helper.setText(R.id.item_coupon_title, item.getName());

        int isStart = item.getIsStart();
        if(isStart == 0){//未开始
            helper.getView(R.id.item_coupon_icon).setVisibility(View.GONE);
            helper.getView(R.id.item_coupon_btn).setVisibility(View.GONE);
            helper.setText(R.id.item_coupon_time, sdf.format(item.getStartTime()) + "开始领取");
            helper.setTextColor(R.id.item_coupon_time, Color.parseColor("#ec8989"));
        }else if(isStart == 1){
            helper.getView(R.id.item_coupon_icon).setVisibility(View.VISIBLE);
            helper.setImageResource(R.id.item_coupon_icon, R.mipmap.yishiyong);
            helper.getView(R.id.item_coupon_btn).setVisibility(View.GONE);

            helper.setTextColor(R.id.item_coupon_time, Color.parseColor("#666666"));
            int isOver = item.getIsOver();
            if(isOver == 1){
                helper.setImageResource(R.id.item_coupon_icon, R.mipmap.yilingquwan);
            }else{
                helper.setImageResource(R.id.item_coupon_icon, R.mipmap.yilingqu);
            }
            helper.setText(R.id.item_coupon_time, sdf.format(item.getEndTime())+"领取结束");
            //以开始
            int grantStatus = item.getGrantStatus();
            if(grantStatus == 0){//未领取
                helper.setBackgroundRes(R.id.item_coupon_btn, R.drawable.shape_yhj1);
                helper.setText(R.id.item_coupon_btn, "立即领取");
                helper.setTextColor(R.id.item_coupon_btn, Color.parseColor("#ffffff"));
                if(isOver == 1){
                    helper.getView(R.id.item_coupon_icon).setVisibility(View.VISIBLE);
                    helper.getView(R.id.item_coupon_btn).setVisibility(View.GONE);
                }else{
                    helper.getView(R.id.item_coupon_icon).setVisibility(View.GONE);
                    helper.getView(R.id.item_coupon_btn).setVisibility(View.VISIBLE);
                }
            }else if(grantStatus == 1){//已领取
                helper.setImageResource(R.id.item_coupon_icon, R.mipmap.yilingqu);
                helper.setBackgroundRes(R.id.item_coupon_btn, R.drawable.shape_yhj2);
                helper.setText(R.id.item_coupon_btn, "去使用");
                helper.setTextColor(R.id.item_coupon_btn, Color.parseColor("#ef2a35"));
                helper.getView(R.id.item_coupon_icon).setVisibility(View.VISIBLE);
                helper.getView(R.id.item_coupon_btn).setVisibility(View.VISIBLE);
            }

        }

        helper.addOnClickListener(R.id.item_coupon_btn);
    }


}
