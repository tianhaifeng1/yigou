package com.work.doctor.fruits.activity.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.CouponInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class CouponUserAdapter extends TRecyclerAdapter<CouponInfoBean> {

    final String PATTERN_YMD_HMS = "yyyy.MM.dd HH:mm";

    private SimpleDateFormat sdf;

    public CouponUserAdapter(@Nullable List<CouponInfoBean> data) {
        super(data);
        sdf = new SimpleDateFormat(PATTERN_YMD_HMS);
        setState(0);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_coupon_use;
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

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mContext.getResources().getDimensionPixelOffset(R.dimen.dp80));
            helper.getView(R.id.item_height_rl).setLayoutParams(layoutParams);
            helper.getView(R.id.item_coupon_bottomtext).setVisibility(View.GONE);
            helper.getView(R.id.item_coupon_bottomline).setVisibility(View.GONE);
            helper.getView(R.id.item_coupon_rlright).setVisibility(View.VISIBLE);

            helper.getView(R.id.item_coupon_icon).setVisibility(View.GONE);
            helper.getView(R.id.item_coupon_icon_select).setVisibility(View.VISIBLE);

            helper.setText(R.id.item_coupon_time, "失效日期为：" + sdf.format(item.getValidTime()));

            helper.addOnClickListener(R.id.item_coupon_icon_select);

            helper.setBackgroundRes(R.id.item_coupon_texttype, R.mipmap.yhj_use);

            boolean select = item.isSelect();
            if(select){
                GlideUtile.bindImageView(mContext, R.mipmap.round, helper.getView(R.id.item_coupon_icon_select));
            }else{
                GlideUtile.bindImageView(mContext, R.mipmap.round_n, helper.getView(R.id.item_coupon_icon_select));
            }

        }else if(state == 1){

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mContext.getResources().getDimensionPixelOffset(R.dimen.dp100));
            helper.getView(R.id.item_height_rl).setLayoutParams(layoutParams);

            helper.getView(R.id.item_coupon_bottomtext).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_coupon_bottomline).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_coupon_rlright).setVisibility(View.GONE);

            helper.getView(R.id.item_coupon_icon).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_coupon_icon_select).setVisibility(View.GONE);

            helper.setText(R.id.item_coupon_time, "失效日期为：" + sdf.format(item.getValidTime()));

            helper.setBackgroundRes(R.id.item_coupon_texttype, R.mipmap.yhj_no);

        }

        if(item.getType() == 6){
            helper.setText(R.id.item_coupon_texttype, "运费券");
        }else{
            helper.setText(R.id.item_coupon_texttype, "满减券");
        }

        helper.setText(R.id.item_coupon_money, BigDecimalUtil.roundOffString(item.getCouponValue(), 2));

        String useValue = BigDecimalUtil.roundOffString(item.getUseValue(), 2);
        if (useValue.equals("0")) {
            helper.setText(R.id.item_coupon_term, "无门槛");
        }else{
            helper.setText(R.id.item_coupon_term, "满" + useValue + "元可用");
        }

        helper.setText(R.id.item_coupon_title, item.getName());


    }


}
