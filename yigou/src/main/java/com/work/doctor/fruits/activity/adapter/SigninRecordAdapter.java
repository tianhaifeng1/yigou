package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.SigninRecordBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class SigninRecordAdapter extends TRecyclerAdapter<SigninRecordBean> {


    public SigninRecordAdapter(@Nullable List<SigninRecordBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SigninRecordBean item) {
        helper.setText(R.id.item_record_intro,item.getShopIntro());
        helper.setText(R.id.item_record_xq,item.getGoodsName());
        helper.setText(R.id.item_record_date,getDateToString(item.getSigninTime()));
        if(item.getSigninGift()==0){
            helper.setText(R.id.item_record_status,"已失效");
        }else{
            helper.setText(R.id.item_record_status,"已领取");
        }
        GlideUtile.bindImageView(mContext, item.getGoodsImage(), helper.getView(R.id.item_record_img));

    }

    public static String getDateToString(long milSecond) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(milSecond);
    }
    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_record;
    }
}
