package com.xzte.filtermodule2;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * 作者：小童
 * 创建时间：2019/8/22 15:20
 */
public class TFilterModule2 {

    private Builder bulider;

    public void setOnTFilterModuleListener(OnTFilterItemClickListener listener) {
        bulider.params.listener = listener;
    }

    public static class Builder {

        private Context context;

        private TFilterParams params;

        public Builder(Context context) {
            this.context = context;
            params = new TFilterParams();
            params.init(context);
        }

        public Builder setFilterTabTextSize(int filterTabTextSize) {
            params.filterTabTextSize = filterTabTextSize;
            return this;
        }

        public Builder setFilterItemTextSize(int filterItemTextSize) {
            params.filterItemTextSize = filterItemTextSize;
            return this;
        }

        public Builder setFilterItemTag(boolean showTag) {
            params.showTag = showTag;
            return this;
        }

        public <T extends TFilterTabInfo>  Builder setFilterTabList(List<T> tabInfoBeanList) {
            params.tabInfoBeanList.addAll(tabInfoBeanList);
            return this;
        }

        public <T extends TFilterItemInfo> Builder setFilterItemList( String[] itemListTags, List<T>... filterItemInfos) {

            if (itemListTags != null && itemListTags.length > 0) {
                for (int i = 0; i < itemListTags.length; i++) {
                    List<T> list = null;
                    if (i < filterItemInfos.length) {
                        list = filterItemInfos[i];
                    }
                    params.itemInfoBeanList.put(itemListTags[i], (List<TFilterItemInfo>) list);
//                    设置默认选择项
                    if(itemListTags[i].equals(TFilterParams.FILTER_ITEM_FOUR)){
                        params.itemSelectIndexList.put(itemListTags[i], -1);
                    }else{
                        params.itemSelectIndexList.put(itemListTags[i], 0);
                    }
//                    设置默认选择布局的高度
                    params.itemLayoutHeightList.put(itemListTags[i], 200);
                }
            }

            return this;
        }

//        设置有列表项布局的高度
        public Builder setFilterItemLayoutHeight( String[] itemListTags, Integer... filterItemHeights) {

            if (itemListTags != null && itemListTags.length > 0) {
                for (int i = 0; i < itemListTags.length; i++) {
                    Integer list = null;
                    if (i < filterItemHeights.length) {
                        list = filterItemHeights[i];
                    }
                    params.itemLayoutHeightList.put(itemListTags[i], list);
                }
            }

            return this;
        }
//        设置默认选择列表的选择条目
        public Builder setFilterItemSelectIndex( String[] itemListTags, Integer... filterItemSelectIndexs) {
            if (itemListTags != null && itemListTags.length > 0) {
                for (int i = 0; i < itemListTags.length; i++) {
                    Integer list = null;
                    if (i < filterItemSelectIndexs.length) {
                        list = filterItemSelectIndexs[i];
                    }
                    params.itemSelectIndexList.put(itemListTags[i], list);
                }
            }
            return this;
        }



        public Builder setOnTFilterModuleListener(OnTFilterItemClickListener listener) {
            params.listener = listener;
            return this;
        }


        public TFilterModule2 creat(View rootView){

            TFilterModule2 filterModule2 = new TFilterModule2();
            filterModule2.bulider = this;

            params.initView(rootView);

            return filterModule2;
        }

    }

}
