package com.work.doctor.fruits.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.t.httplib.yigou.bean.WlList;
import com.trjx.tbase.adapter.TBaseAdapter;
import com.trjx.tlibs.views.DotView;
import com.work.doctor.fruits.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WlAdapter extends TBaseAdapter<WlList> {

    final String PATTERN_YMD_HMS = "yyyy-MM-dd HH:mm:ss";

    public WlAdapter(Context context, List<WlList> listBeans) {
        super(context, listBeans);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_wl, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        int size = tList.size();
        WlList info = tList.get(size - position - 1);


//        if(position == 0){
//            //第一个
//            if (size - position == 1) {
//                viewHolder.itemWlDotview.setDotType(DotView.DotType.DOT_TYPE_FILL);
//            }else{
//                viewHolder.itemWlDotview.setDotType(DotView.DotType.DOT_TYPE_FILL | DotView.DotType.DOT_TYPE_LINE_TOP);
//            }
//
//        }
//
//        if (position == size - 1) {
//            //最后一个
//            if (position > 0) {
//                viewHolder.itemWlDotview.setDotType(DotView.DotType.DOT_TYPE_HOLLOW| DotView.DotType.DOT_TYPE_LINE_BOTTOM);
//            }else{
//                viewHolder.itemWlDotview.setDotType(DotView.DotType.DOT_TYPE_HOLLOW);
//            }
//        }


        if (position == 0) {
            //最后一个
            viewHolder.itemWlDotview.setDotColor(context.getResources().getColor(R.color.color_blue));
        } else {
            viewHolder.itemWlDotview.setDotColor(context.getResources().getColor(R.color.color_darkgray));
        }
        if (position == size - 1) {
            //第一个
            viewHolder.itemWlDotview.setDotType(DotView.DotType.DOT_TYPE_FILL | DotView.DotType.DOT_TYPE_LINE_TOP);
        }else{
            viewHolder.itemWlDotview.setDotType(DotView.DotType.DOT_TYPE_FILL | DotView.DotType.DOT_TYPE_LINE_VERTIACL);
        }

        viewHolder.itemWlName.setText(info.getName());
        Date date = new Date(info.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YMD_HMS);
        viewHolder.itemWlTime.setText(sdf.format(date));
//

        return convertView;
    }

    public static class ViewHolder {

        View view;
        DotView itemWlDotview;
        TextView itemWlName;
        TextView itemWlTime;

        public ViewHolder(View view) {
            this.view = view;

            itemWlDotview = view.findViewById(R.id.item_wl_dotview);
            itemWlName = view.findViewById(R.id.item_wl_name);
            itemWlTime = view.findViewById(R.id.item_wl_time);
        }
    }

}