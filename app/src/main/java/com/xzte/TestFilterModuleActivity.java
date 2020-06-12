package com.xzte;

import android.os.Bundle;

import com.trjx.tbase.activity.InitActivity;
import com.trjx.tbase.module.titlemodule.TitleModule;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.ToastUtil2;
import com.xzte.filtermodule2.OnTFilterItemClickListener;
import com.xzte.filtermodule2.TFilterItemInfoBean;
import com.xzte.filtermodule2.TFilterModule2;
import com.xzte.filtermodule2.TFilterParams;
import com.xzte.filtermodule2.TFilterTabInfoBean;

import java.util.ArrayList;
import java.util.List;

public class TestFilterModuleActivity extends InitActivity implements OnTFilterItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_filter_module);

        initWork();
        initView();

    }

    @Override
    protected void initView() {
        super.initView();

        TitleModule titleModule = new TitleModule(context, rootView);
        titleModule.initTitle("测试筛选框模块");

        List<TFilterTabInfoBean> tabInfoBeanList = new ArrayList<>();
        TFilterTabInfoBean infoBean1 = new TFilterTabInfoBean();
        infoBean1.setName("综合");
        infoBean1.setIconRes(new int[]{R.mipmap.filter_default2,R.mipmap.filter_xiala2, R.mipmap.filter_shouqi2});
        infoBean1.setSelectState(1);
        infoBean1.setFilterTabStateTotal(3);
        tabInfoBeanList.add(infoBean1);
        TFilterTabInfoBean infoBean2 = new TFilterTabInfoBean();
        infoBean2.setName("销量");
        infoBean2.setIconRes(new int[]{R.mipmap.filter_default, R.mipmap.filter_xiala,R.mipmap.filter_shouqi});
        infoBean2.setSelectState(0);
        infoBean2.setFilterTabStateTotal(3);
        tabInfoBeanList.add(infoBean2);
        TFilterTabInfoBean infoBean3 = new TFilterTabInfoBean();
        infoBean3.setName("价格");
        infoBean3.setIconRes(new int[]{R.mipmap.filter_default, R.mipmap.filter_xiala,R.mipmap.filter_shouqi});
        infoBean3.setSelectState(0);
        infoBean3.setFilterTabStateTotal(3);
        tabInfoBeanList.add(infoBean3);
        TFilterTabInfoBean infoBean4 = new TFilterTabInfoBean();
        infoBean4.setName("品牌");
        infoBean4.setIconRes(new int[]{R.mipmap.filter_default2,R.mipmap.filter_xiala2, R.mipmap.filter_shouqi2});
        infoBean4.setSelectState(0);
        infoBean4.setFilterTabStateTotal(3);
        tabInfoBeanList.add(infoBean4);

        String[] itemListTags = {TFilterParams.FILTER_ITEM_ONE, TFilterParams.FILTER_ITEM_FOUR};
        List<TFilterItemInfoBean> itemInfoListOne = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TFilterItemInfoBean infoBean = new TFilterItemInfoBean();
            infoBean.setFilterItemName("条目1Item +" + i);
            if (i == 0) {
                infoBean.setFilterItemSelect(true);
            } else {
                infoBean.setFilterItemSelect(false);
            }
            itemInfoListOne.add(infoBean);
        }
        List<TFilterItemInfoBean> itemInfoListThree = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TFilterItemInfoBean infoBean = new TFilterItemInfoBean();
            infoBean.setFilterItemName("条目4Item +" + i);
            infoBean.setFilterItemSelect(false);
            itemInfoListThree.add(infoBean);
        }

        TFilterModule2 filterModule2 = new TFilterModule2.Builder(context)
                .setFilterItemTag(true)
                .setFilterItemTextSize(14)
                .setFilterTabTextSize(14)
                .setFilterTabList(tabInfoBeanList)
                .setFilterItemList(itemListTags, itemInfoListOne, itemInfoListThree)
                .creat(rootView);
        filterModule2.setOnTFilterModuleListener(this);

    }

    @Override
    public void onFilterItem(int tabIndex, int itemPosition,boolean haveList) {
        if(haveList){
            Logger.t("youjihe ");
        }else{
            Logger.t("meiyoujihe ");
        }
        ToastUtil2.showToast(context, "tabIndex , pos = " + tabIndex + " , " + itemPosition);
    }
}
