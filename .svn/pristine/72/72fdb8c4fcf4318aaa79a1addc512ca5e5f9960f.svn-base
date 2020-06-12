package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.EvaluateInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 评价
 */
public class EvaluateAdapter extends TRecyclerAdapter<EvaluateInfoBean> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public EvaluateAdapter(@Nullable List<EvaluateInfoBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EvaluateInfoBean item) {
        helper.setText(R.id.item_pj_name, item.getNickName());
        helper.setText(R.id.item_pj_content, item.getContent());
        helper.setText(R.id.item_pj_time, dateFormat.format(new Date(item.getAddTime())));
        GlideUtile.bindImageViewRound(mContext, item.getAvatarUrl(), R.mipmap.default_img, helper.getView(R.id.item_pj_icon));
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_pj;
    }
}
