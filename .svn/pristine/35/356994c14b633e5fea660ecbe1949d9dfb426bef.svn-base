package com.xzte.maplib.city;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.trjx.tbase.module.recyclermodule.TRecyclerMoreItemAdapter2;
import com.xzte.maplib.R;

import java.util.List;

public abstract class CitySelectAdapter extends TRecyclerMoreItemAdapter2<TCity> {

    public CitySelectAdapter(int code, @Nullable List<TCity> data) {
        super(code, data);
        initItemLayout();
    }


    private void initItemLayout() {
        if(code == 1){
            addItemType(1, R.layout.item_city_type1);
            addItemType(2, R.layout.item_city_type2);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, TCity item) {
        if(code == 1){
            int itemType = item.getItemType();
            switch (itemType){
                case 1:
                    RecyclerView recyclerView = helper.getView(R.id.item_city_type1_recyclerview);
                    recyclerView.setLayoutManager(new FlexboxLayoutManager(mContext){
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    });
                    HotCityAdapter hotCityAdapter = new HotCityAdapter(item.getHotCityList());
                    recyclerView.setAdapter(hotCityAdapter);
                    hotCityAdapter.setOnItemClickListener((adapter, view, position) -> onClickHotCity(adapter.getData().get(position).toString()));
                    break;
                case 2:
                    String firstChar = item.getFirstChar();
                    if (firstChar == null || firstChar.equals("")) {
                        helper.getView(R.id.item_city_type2_title).setVisibility(View.GONE);
                        helper.getView(R.id.item_city_type2_letter).setVisibility(View.GONE);
                    }else{
                        helper.getView(R.id.item_city_type2_letter).setVisibility(View.VISIBLE);
                        helper.setText(R.id.item_city_type2_letter, firstChar);
                        if(firstChar.equals("A")){
                            helper.getView(R.id.item_city_type2_title).setVisibility(View.VISIBLE);
                        }else{
                            helper.getView(R.id.item_city_type2_title).setVisibility(View.GONE);
                        }
                    }
                    helper.setText(R.id.item_city_type2_cityname, item.getAreaName());
                    helper.addOnClickListener(R.id.item_city_type2_ll);
                    break;
            }
        }
    }

    public abstract void onClickHotCity(String hotCityName);


}
