package com.work.doctor.fruits.dialog;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.util.List;

/**
 * 规格
 */
public abstract class GoodsSpecInfoTitleAdapter extends TRecyclerAdapter<GoodsSpecInfoBean.GoodsItemListBean> {

    public GoodsSpecInfoTitleAdapter(@Nullable List<GoodsSpecInfoBean.GoodsItemListBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.dialog_goods_specinfo_more_item;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsSpecInfoBean.GoodsItemListBean item) {
        helper.setText(R.id.item_goods_smi_tv, item.getItemName());

        RecyclerView recyclerView = helper.getView(R.id.item_goods_smi_recyclerview);
        List<GoodsSpecInfoBean.GoodsItemListBean.SpecListBean> specListBeanList = item.getSpecList();
        GoodsSpecInfoAdapter infoAdapter = new GoodsSpecInfoAdapter(specListBeanList);
//            dialog.viewHolder.mDialogGoodspecinfoRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL){

        recyclerView.setLayoutManager(new FlexboxLayoutManager(mContext){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setAdapter(infoAdapter);

        //初始化选中
        int selectIndex = item.getSelectIndex();
        specListBeanList.get(selectIndex).setSelect(true);
        item.setSelectId(specListBeanList.get(selectIndex).getId());
        infoAdapter.setOnItemClickListener((adapter, view, position) -> {
            int selectIndex2 = item.getSelectIndex();
            if (selectIndex2 != position) {
                GoodsSpecInfoBean.GoodsItemListBean.SpecListBean specListBeanPos = specListBeanList.get(position);
                specListBeanPos.setSelect(true);
                item.setSelectId(specListBeanPos.getId());
                infoAdapter.notifyItemChanged(position);

                GoodsSpecInfoBean.GoodsItemListBean.SpecListBean specListBeanIndex = specListBeanList.get(selectIndex2);
                specListBeanIndex.setSelect(false);
                infoAdapter.notifyItemChanged(selectIndex2);

                item.setSelectIndex(position);

                changeDataEvent();

            }

        });

    }

    public abstract void changeDataEvent();

}
