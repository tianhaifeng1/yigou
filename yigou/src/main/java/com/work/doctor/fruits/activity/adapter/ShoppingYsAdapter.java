package com.work.doctor.fruits.activity.adapter;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.GmrRecordInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.TextStyleHelper;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.assist.GetNetworkTime;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public abstract class ShoppingYsAdapter extends TRecyclerAdapter<GoodsInfoBean> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日 HH:mm:ss");


    public ShoppingYsAdapter(@Nullable List<GoodsInfoBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_shop_ys;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsInfoBean item) {
//        boolean isNull = helper.getUnmodifiedPayloads().isEmpty();

        String ygouStr = "";
        if(item.getLimitNum()==0){
            ygouStr = "(不限购)";
        }else{
            ygouStr = "(每人限购" + item.getLimitNum() + "份)";
        }
        String string = "已售" + item.getSellTotalNum() + "份/限量" + item.getStock() + "份" + ygouStr;
        helper.setText(R.id.item_shop_remark,
                new TextStyleHelper(string)
                        .addForeColorSpan(mContext.getResources().getColor(R.color.ys_color_two),
                                string.length() - ygouStr.length(),
                                string.length()
                        ).show());
        helper.setText(R.id.item_shop_name, item.getGoodsName());
        String startTimeStr = dateFormat.format(new Date(item.getStartTime()));
        helper.setText(R.id.item_shop_time, "预售时间：" + startTimeStr
                + "\n提货时间：" + dateFormat.format(new Date(item.getDeliveryTime())));
        helper.setText(R.id.item_shop_price, "￥" + BigDecimalUtil.roundOffString(item.getSellPriceDiscount(), 2));
        helper.setText(R.id.item_shop_price2, "￥" + BigDecimalUtil.roundOffString(item.getSellPrice(), 2));
        TextView textView = helper.getView(R.id.item_shop_price2);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        GlideUtile.bindImageView(mContext, item.getGoodsImage(), helper.getView(R.id.item_shop_icon));
        helper.addOnClickListener(R.id.item_shop_gmr_ll);


        Logger.t("position = " + helper.getAdapterPosition());
        List<GmrRecordInfoBean> gmrList = item.getSpecialSaleList();
        Logger.t("gmrList = " + (gmrList == null ? "null" : ("" + gmrList.size())));
        if (gmrList != null && gmrList.size() > 0) {
            helper.getView(R.id.item_shop_gmr_ll).setVisibility(View.VISIBLE);
            switch (gmrList.size()) {
                case 5:
                    helper.getView(R.id.item_shop_gmr1).setVisibility(View.VISIBLE);
                    GlideUtile.bindImageViewRound(mContext, gmrList.get(4).getAvatarUrl(), R.mipmap.default_img, helper.getView(R.id.item_shop_gmr1));
                case 4:
                    helper.getView(R.id.item_shop_gmr2).setVisibility(View.VISIBLE);
                    GlideUtile.bindImageViewRound(mContext, gmrList.get(3).getAvatarUrl(), R.mipmap.default_img, helper.getView(R.id.item_shop_gmr2));
                case 3:
                    helper.getView(R.id.item_shop_gmr3).setVisibility(View.VISIBLE);
                    GlideUtile.bindImageViewRound(mContext, gmrList.get(2).getAvatarUrl(), R.mipmap.default_img, helper.getView(R.id.item_shop_gmr3));
                case 2:
                    helper.getView(R.id.item_shop_gmr4).setVisibility(View.VISIBLE);
                    GlideUtile.bindImageViewRound(mContext, gmrList.get(1).getAvatarUrl(), R.mipmap.default_img, helper.getView(R.id.item_shop_gmr4));
                case 1:
                    helper.getView(R.id.item_shop_gmr5).setVisibility(View.VISIBLE);
                    GlideUtile.bindImageViewRound(mContext, gmrList.get(0).getAvatarUrl(), R.mipmap.default_img, helper.getView(R.id.item_shop_gmr5));
                    break;
            }
        }else{
              helper.getView(R.id.item_shop_gmr_ll).setVisibility(View.GONE);
//            GlideUtile.bindImageViewRound(mContext, R.mipmap.default_img, helper.getView(R.id.item_shop_gmr5));
//            GlideUtile.bindImageViewRound(mContext, R.mipmap.default_img, helper.getView(R.id.item_shop_gmr4));
//            GlideUtile.bindImageViewRound(mContext, R.mipmap.default_img, helper.getView(R.id.item_shop_gmr3));
//            GlideUtile.bindImageViewRound(mContext, R.mipmap.default_img, helper.getView(R.id.item_shop_gmr2));
//            GlideUtile.bindImageViewRound(mContext, R.mipmap.default_img, helper.getView(R.id.item_shop_gmr1));
        }



//        2020-01-21 16:01:17.610 8990-8990/com.work.doctor.fruits I/tong: cha = 1579593660000
//        2020-01-21 16:01:17.610 8990-8990/com.work.doctor.fruits I/tong: cha2 = 1581239100000
//        2020-01-21 16:01:17.610 8990-8990/com.work.doctor.fruits I/tong: currentTime = 1579593659146

        new GetNetworkTime(currentTime -> {
            long time = 0;
            long cha = item.getStartTime();
            long cha2 = item.getEndTime();
            Logger.t("cha = " + cha);
            Logger.t("cha2 = " + cha2);
            if (cha > currentTime) {
                Logger.t("-------------------1");
                time = cha;
//            还没开始
                helper.setText(R.id.item_shop_cart, startTimeStr.substring(7, 9) + "点开售");

                int tint = Color.parseColor("#67d7ab");
                helper.getView(R.id.item_shop_cart).getBackground().setColorFilter(tint, PorterDuff.Mode.SRC_IN);

                helper.getView(R.id.item_shop_cart).setEnabled(false);
                helper.getView(R.id.item_shop_masking).setVisibility(View.GONE);
            } else if (cha2 > currentTime) {
                Logger.t("-------------------2");
                time = cha2;
//            预售时间
                if (item.getStock() <= item.getSellTotalNum()) {
                    //抢光了
                    helper.setText(R.id.item_shop_cart, "抢光了");
                    helper.setText(R.id.item_shop_masking, "抢光了");
                    int tint = Color.parseColor("#d7d7d7");
                    helper.getView(R.id.item_shop_cart).getBackground().setColorFilter(tint, PorterDuff.Mode.SRC_IN);

                    helper.getView(R.id.item_shop_cart).setEnabled(false);
                    helper.getView(R.id.item_shop_masking).setVisibility(View.VISIBLE);
                } else {
                    //加入购物车
                    helper.setText(R.id.item_shop_cart, "加入购物车");

                    int tint = Color.parseColor("#fa2843");
                    helper.getView(R.id.item_shop_cart).getBackground().setColorFilter(tint, PorterDuff.Mode.SRC_IN);

                    helper.getView(R.id.item_shop_cart).setEnabled(true);
                    helper.getView(R.id.item_shop_masking).setVisibility(View.GONE);
                    helper.addOnClickListener(R.id.item_shop_cart);
                }
            } else {
                Logger.t("-------------------3");
//            预售结束
                time = 0;
                helper.setText(R.id.item_shop_cart, "预售时间结束");
                helper.setText(R.id.item_shop_masking, "活动结束");
                int tint = Color.parseColor("#d7d7d7");
                helper.getView(R.id.item_shop_cart).getBackground().setColorFilter(tint, PorterDuff.Mode.SRC_IN);
                helper.getView(R.id.item_shop_cart).setEnabled(false);
                helper.getView(R.id.item_shop_masking).setVisibility(View.VISIBLE);
            }

            if (time > 0) {
                addTimer(time, helper.getAdapterPosition(),currentTime);
            }
        });


    }

    public abstract void addTimer(long time, int position,long currentTime);

}
