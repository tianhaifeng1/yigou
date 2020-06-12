package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.GmrRecordInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 购买人列表适配器
 */
public class GmrAdapter extends TRecyclerAdapter<GmrRecordInfoBean> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public GmrAdapter(@Nullable List<GmrRecordInfoBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_gmr;
    }

    @Override
    protected void convert(BaseViewHolder helper, GmrRecordInfoBean item) {

        helper.setText(R.id.item_gmr_name, item.getNickName());
        helper.setText(R.id.item_gmr_time, dateFormat.format(new Date(item.getPaymentTime())));
        helper.setText(R.id.item_gmr_number, item.getSellNum()+"份");
        GlideUtile.bindImageViewRound(mContext, item.getAvatarUrl(), R.mipmap.default_img, helper.getView(R.id.item_gmr_icon));


    }
}
