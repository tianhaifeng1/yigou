package com.xzte.maplib.baidu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.trjx.tbase.adapter.TBaseAdapter;
import com.xzte.maplib.R;

import java.util.List;

/**
 * 作者：小童
 * 创建时间：2019/6/13 18:20
 */
public class SearchAdapter extends TBaseAdapter<PoiInfo> {

    public SearchAdapter(Context context, List<PoiInfo> poiInfos) {
        super(context, poiInfos);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_text2, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(tList.get(position).getName());
        viewHolder.textView2.setText(tList.get(position).getAddress());

        return convertView;
    }

    class ViewHolder {

        TextView textView;
        TextView textView2;

        public ViewHolder(View convertView) {
            textView = convertView.findViewById(R.id.item_text1);
            textView2 = convertView.findViewById(R.id.item_text2);
        }
    }
}
