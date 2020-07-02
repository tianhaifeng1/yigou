package com.work.doctor.fruits.activity.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.databaselib.BigDecimalUtil;
import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.DatabaseShopInfo;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.trjx.tlibs.uils.Logger;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.Goods.GoodsDetial2Activity;
import com.work.doctor.fruits.activity.Goods.GoodsDetial3Activity;
import com.work.doctor.fruits.activity.fragment.MainThreeFragment;
import com.work.doctor.fruits.assist.DemoConstant;

import java.util.List;

public class ShopCartOutAdapter extends TRecyclerAdapter<DatabaseShopInfo> {

    private boolean isEdit;

    private MainThreeFragment threeFragment;

    private ShopCartAdapter shopCartAdapter;

    public ShopCartOutAdapter(MainThreeFragment threeFragment, @Nullable List<DatabaseShopInfo> data) {
        this(threeFragment, data, false);
    }

    public ShopCartOutAdapter(MainThreeFragment threeFragment, @Nullable List<DatabaseShopInfo> data, boolean edit) {
        super(data);
        this.threeFragment = threeFragment;
        this.isEdit = edit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_shopcart_out;
    }

    @Override
    protected void convert(BaseViewHolder helper, DatabaseShopInfo item) {

        helper.addOnClickListener(R.id.item_shopcart_out_event_selectall,
                R.id.item_shopcart_out_event_selectall2,
                R.id.item_shopcart_out_event_checkout,
                R.id.item_shopcart_out_event_delete);

        helper.setText(R.id.item_shopcart_out_shopname, item.getShopName());
        helper.setText(R.id.item_shopcart_out_shopaddress, item.getShopIntro());

        helper.setText(R.id.item_shopcart_out_event_pricetotal, BigDecimalUtil.roundOffString(item.getShopTotalPrice(),2));

        helper.setText(R.id.item_shopcart_out_dzname, item.getShopAddress());

        if (item.getShopId().equals(DemoConstant.shopInfoBean.getShopId() + "")) {
            helper.getView(R.id.item_shopcart_out_shopnotes).setVisibility(View.GONE);
            helper.getView(R.id.item_shopcart_out_dz).setVisibility(View.GONE);
            helper.getView(R.id.item_shopcart_out_dzname).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.item_shopcart_out_shopnotes).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_shopcart_out_dz).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_shopcart_out_dzname).setVisibility(View.VISIBLE);
        }

        boolean select = item.getIsAllSelect();
        if (select) {
            GlideUtile.bindImageView(mContext, R.mipmap.goodscart_select, helper.getView(R.id.item_shopcart_out_event_selectall));
            GlideUtile.bindImageView(mContext, R.mipmap.goodscart_select, helper.getView(R.id.item_shopcart_out_event_selectall2));
        } else {
            GlideUtile.bindImageView(mContext, R.mipmap.round_n, helper.getView(R.id.item_shopcart_out_event_selectall));
            GlideUtile.bindImageView(mContext, R.mipmap.round_n, helper.getView(R.id.item_shopcart_out_event_selectall2));
        }
        if (isEdit) {
            helper.getView(R.id.item_shopcart_out_event_delete).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_shopcart_out_event_checkout).setVisibility(View.GONE);
            helper.getView(R.id.item_shopcart_out_event_pricetotal).setVisibility(View.GONE);
            helper.getView(R.id.item_shopcart_out_event_pricetotalview).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.item_shopcart_out_event_delete).setVisibility(View.GONE);
            helper.getView(R.id.item_shopcart_out_event_checkout).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_shopcart_out_event_pricetotal).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_shopcart_out_event_pricetotalview).setVisibility(View.VISIBLE);
        }

        List<DatabaseGoodsInfo> goodsInfoList = item.getGoodsInfoList();
        shopCartAdapter = new ShopCartAdapter(goodsInfoList);
        RecyclerView recyclerView = helper.getView(R.id.item_shopcart_out_recyclerview);
        int row = 0;
        if (goodsInfoList != null && goodsInfoList.size() > 0) {
            row = goodsInfoList.size();
        }
        //计算高度
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = mContext.getResources().getDimensionPixelOffset(R.dimen.dp120) * row;
        recyclerView.setLayoutParams(params);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setAdapter(shopCartAdapter);
        shopCartAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            DatabaseGoodsInfo info = (DatabaseGoodsInfo) adapter.getData().get(position);
            int ids = view.getId();
            switch (ids) {
                case R.id.item_shopcart_select:
                    Logger.t("选择");

                    info.setIsSelect(!info.getIsSelect());
                    boolean isSelect = false;
                    if (info.getIsSelect()) {
                        isSelect = panduan(adapter.getData());
//                            item.setShopTotalPrice(BigDecimalUtil.add(item.getShopTotalPrice(), info.getGoodsNumber() * info.getGoodsPrice()).floatValue());
                    }/*else{
//                            item.setShopTotalPrice(BigDecimalUtil.sub(item.getShopTotalPrice(), info.getGoodsNumber() * info.getGoodsPrice()).floatValue());
                        }*/
                    item.setIsAllSelect(isSelect);
                    threeFragment.mainNavActivity.greenDaoAssist.updateGoodsSelectState(item, info);
                    helper.setText(R.id.item_shopcart_out_event_pricetotal, BigDecimalUtil.roundOffString(item.getShopTotalPrice(),2));
//                    adapter.notifyItemChanged(position);
                    ShopCartOutAdapter.this.notifyDataSetChanged();
                    break;
                case R.id.item_shopcart_add:
                    Logger.t("增加");
                    if (info.getGoodsTotal() <= info.getGoodsNumber() && info.getGoodsTotal()!=0) {
                        //选择数量大于限购数
                        return;
                    }
                    if(info.getGoodsNumber()>=info.getStock()){
                        //库存小于当前选择的数量时
                        return;
                    }
                    threeFragment.mainNavActivity.greenDaoAssist.updateGoodsNumber(info, 1, item.getShopId(),isChange -> DemoConstant.isChangeDatabase = isChange);
                    if (info.getIsSelect()) {
                        helper.setText(R.id.item_shopcart_out_event_pricetotal, BigDecimalUtil.roundOffString(item.getShopTotalPrice(),2));
                    }
                    adapter.notifyItemChanged(position);
                    break;
                case R.id.item_shopcart_minus:
                    Logger.t("减少");
                    if (info.getGoodsNumber() > 1) {
                        int jian = -1;
                        if (info.getGoodsNumber() > info.getGoodsTotal() && info.getGoodsTotal()!=0) {
                            jian = info.getGoodsTotal() - info.getGoodsNumber();
                        }
                        threeFragment.mainNavActivity.greenDaoAssist.updateGoodsNumber(info, jian, item.getShopId(),isChange -> DemoConstant.isChangeDatabase = isChange);
                        if (info.getIsSelect()) {
                            helper.setText(R.id.item_shopcart_out_event_pricetotal, BigDecimalUtil.roundOffString(item.getShopTotalPrice(),2));
                        }
                        adapter.notifyItemChanged(position);
                    }
                    break;
            }
        });
        shopCartAdapter.setOnItemClickListener((adapter, view, position) -> {
            DatabaseGoodsInfo infoBean = (DatabaseGoodsInfo) adapter.getData().get(position);
            if(infoBean.getGoodsType()==3){
                Intent intent = new Intent(threeFragment.activity.context, GoodsDetial3Activity.class);
                intent.putExtra("index", 0);
                intent.putExtra("id", Integer.parseInt(infoBean.getGoodsId()));
                threeFragment.activity.skipActivity(intent);
            }else{
                Intent intent = new Intent(threeFragment.activity.context, GoodsDetial2Activity.class);
                intent.putExtra("id", Integer.parseInt(infoBean.getGoodsId()));
                threeFragment.activity.skipActivity(intent);
            }
        });
    }


    private boolean panduan(List<DatabaseGoodsInfo> goodsInfos) {
        for (DatabaseGoodsInfo goodsInfo : goodsInfos) {
            if (!goodsInfo.getIsSelect()) {
                return false;
            }
        }
        return true;
    }
//    private void changeTotalPrice(){
//        if (info.isSelect()) {
//            item.setShopTotalPrice(item.getShopTotalPrice() + info.getGoodsNumber() * info.getGoodsPrice());
//        }else{
//            item.setShopTotalPrice(item.getShopTotalPrice() - info.getGoodsNumber() * info.getGoodsPrice());
//        }
//    }

    //刷新内部列表
    public void refresh() {
        if (shopCartAdapter != null) {
            shopCartAdapter.notifyDataSetChanged();
        }
        this.notifyDataSetChanged();
    }

}
