package com.xzte.maplib.baidu;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.mapapi.search.sug.SuggestionResult;
import com.trjx.tbase.adapter.TBaseAdapter;
import com.xzte.maplib.R;

import java.util.List;

/**
 * 作者：小童
 * 创建时间：2019/6/13 18:20
 */
public class SearchStringAdapter extends TBaseAdapter<SuggestionResult.SuggestionInfo> {

    public SearchStringAdapter(Context context, List<SuggestionResult.SuggestionInfo> poiInfos) {
        super(context, poiInfos);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_text, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(tList.get(position).getKey());

        return convertView;
    }

    class ViewHolder {

        TextView textView;

        public ViewHolder(View convertView) {
            textView = convertView.findViewById(R.id.item_text1);
            textView.setGravity(Gravity.CENTER_VERTICAL);
        }
    }
}
