package com.xzte.maplib.baidu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trjx.tbase.adapter.TBaseAdapter;
import com.xzte.maplib.R;

import java.util.List;

/**
 * 作者：小童
 * 创建时间：2019/6/13 18:20
 */
public class SearchStringAdapter2 extends TBaseAdapter<TSuggestionInfo> {

    public SearchStringAdapter2(Context context, List<TSuggestionInfo> poiInfos) {
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
        TSuggestionInfo info = tList.get(position);

//        viewHolder.textView.setText("name" + position);
//        viewHolder.textView2.setText("address" + position);
        viewHolder.textView.setText(info.getSuggestionInfo().getKey());
        viewHolder.textView2.setText(info.getReverseGeoCodeResult().getAddress());

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
