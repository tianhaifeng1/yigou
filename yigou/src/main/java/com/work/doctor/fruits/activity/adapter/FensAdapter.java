package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.FensInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 评价
 */
public class FensAdapter extends TRecyclerAdapter<FensInfoBean> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public FensAdapter(@Nullable List<FensInfoBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_fens;
    }

    @Override
    protected void convert(BaseViewHolder helper, FensInfoBean item) {

        helper.setText(R.id.item_fens_name, item.getNickName());
        helper.setText(R.id.item_fens_time, dateFormat.format(new Date(item.getInviterTime())));
        GlideUtile.bindImageViewRound(mContext, item.getAvatarUrl(), R.mipmap.default_img, helper.getView(R.id.item_fens_icon));
    }
}
