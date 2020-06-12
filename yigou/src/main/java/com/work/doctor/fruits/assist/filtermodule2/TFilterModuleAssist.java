package com.work.doctor.fruits.assist.filtermodule2;

import android.content.Context;
import android.view.View;

import com.t.httplib.yigou.bean.resp.BrandInfoBean;
import com.work.doctor.fruits.R;

import java.util.ArrayList;
import java.util.List;

public class TFilterModuleAssist {

    private Context context;

    public TFilterModuleAssist(Context context) {
        this.context = context;
    }

    private List<TFilterTabInfoBean> tabInfoBeanList;

    private String[] itemListTags;

    private List<TFilterItemInfoBean> itemInfoListOne;
    private List<TFilterItemInfoBean> itemInfoListThree;

    public void initData(List<BrandInfoBean> list){

        tabInfoBeanList = new ArrayList<>();
        TFilterTabInfoBean infoBean1 = new TFilterTabInfoBean();
        infoBean1.setName("综合排序");
        infoBean1.setIconRes(new int[]{R.mipmap.filter_default2, R.mipmap.filter_xiala2, R.mipmap.filter_shouqi2});
        infoBean1.setSelectState(1);
        tabInfoBeanList.add(infoBean1);
        TFilterTabInfoBean infoBean2 = new TFilterTabInfoBean();
        infoBean2.setName("销量");
        infoBean2.setIconRes(new int[]{R.mipmap.filter_default, R.mipmap.filter_xiala, R.mipmap.filter_shouqi});
        infoBean2.setSelectState(0);
        tabInfoBeanList.add(infoBean2);
        TFilterTabInfoBean infoBean3 = new TFilterTabInfoBean();
        infoBean3.setName("价格");
        infoBean3.setIconRes(new int[]{R.mipmap.filter_default, R.mipmap.filter_xiala, R.mipmap.filter_shouqi});
        infoBean3.setSelectState(0);
        tabInfoBeanList.add(infoBean3);
        if (list != null) {
            TFilterTabInfoBean infoBean4 = new TFilterTabInfoBean();
            infoBean4.setName("品牌");
            infoBean4.setIconRes(new int[]{R.mipmap.filter_default2, R.mipmap.filter_xiala2, R.mipmap.filter_shouqi2});
            infoBean4.setSelectState(0);
            tabInfoBeanList.add(infoBean4);
        }

        itemListTags = new String[]{TFilterParams.FILTER_ITEM_ONE, TFilterParams.FILTER_ITEM_FOUR};

        itemInfoListOne = new ArrayList<>();
        TFilterItemInfoBean itemInfoBean1 = new TFilterItemInfoBean();
        itemInfoBean1.setFilterItemName("综合排序");
        itemInfoBean1.setFilterItemSelect(true);
        itemInfoListOne.add(itemInfoBean1);
        TFilterItemInfoBean itemInfoBean2 = new TFilterItemInfoBean();
        itemInfoBean2.setFilterItemName("新品优先");
        itemInfoBean2.setFilterItemSelect(false);
        itemInfoListOne.add(itemInfoBean2);


        if (list != null) {
            itemInfoListThree = new ArrayList<>();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TFilterItemInfoBean infoBean = new TFilterItemInfoBean();
                infoBean.setFilterItemName(list.get(i).getBrandname());
                infoBean.setFilterItemSelect(false);
                itemInfoListThree.add(infoBean);
            }
            //行高40，最多5行
            if (size > 5 * 2) {
                layoutHeight4 = 5 * 40;
            }else{
                if (size % 2 == 0) {
                    layoutHeight4 = 40 * (size / 2);
                }else{
                    layoutHeight4 = 40 * (size + 1 / 2);
                }
            }
        }

    }


    private TFilterModule filterModule;
    private int layoutHeight4 = 0;

    public TFilterModule creat(View rootView){

        filterModule = new TFilterModule.Builder(context)
                .setFilterItemTag(true)
                .setFilterItemTextSize(14)
                .setFilterTabTextSize(14)
                .setFilterTabList(tabInfoBeanList)
                .setFilterItemList(itemListTags, itemInfoListOne, itemInfoListThree)
                .setFilterItemLayoutHeight(itemListTags,80,layoutHeight4)
                .creat(rootView);
        return filterModule;
    }



    public void refreshTab(int tabIndex, int itemPosition){
        if(tabIndex == 0){
            tabInfoBeanList.get(tabIndex).setName(itemInfoListOne.get(itemPosition).getFilterItemName());
        }else if(tabIndex == 3){
            if (itemPosition < 0) {
                tabInfoBeanList.get(tabIndex).setName("品牌");
            }else{
                tabInfoBeanList.get(tabIndex).setName(itemInfoListThree.get(itemPosition).getFilterItemName());
            }
        }

        filterModule.refreshTab(tabIndex);

    }

}
