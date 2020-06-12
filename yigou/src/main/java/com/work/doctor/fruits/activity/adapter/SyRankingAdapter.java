package com.work.doctor.fruits.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.ShouyRankingInfoBean;
import com.trjx.tbase.adapter.TBaseAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.util.List;

public class SyRankingAdapter extends TBaseAdapter<ShouyRankingInfoBean> {

    public SyRankingAdapter(Context context, List<ShouyRankingInfoBean> listBeans) {
        super(context, listBeans);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_syranking, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ShouyRankingInfoBean listBean = tList.get(position);
        viewHolder.itemSyrankingId.setText(position + 4 + "");
        viewHolder.itemSyrankingName.setText(listBean.getNickName());
        GlideUtile.bindImageViewRound(context,listBean.getAvatarUrl(),R.mipmap.t_head,viewHolder.itemSyrankingIcon);
        viewHolder.itemSyrankingTime.setText("￥ " + BigDecimalUtil.roundOffString(listBean.getSumNum(),2));

        return convertView;
    }

    public static class ViewHolder {

        View view;
         TextView itemSyrankingId;
         ImageView itemSyrankingIcon;
         TextView itemSyrankingName;
         TextView itemSyrankingTime;

        public ViewHolder(View view) {
            this.view = view;

            itemSyrankingId = view.findViewById(R.id.item_syranking_id);
            itemSyrankingIcon = view.findViewById(R.id.item_syranking_icon);
            itemSyrankingName = view.findViewById(R.id.item_syranking_name);
            itemSyrankingTime = view.findViewById(R.id.item_syranking_time);
        }
    }

}