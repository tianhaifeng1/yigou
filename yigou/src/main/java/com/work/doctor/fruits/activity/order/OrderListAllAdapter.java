package com.work.doctor.fruits.activity.order;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.OrderListInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;

import java.math.BigDecimal;
import java.util.List;

public class OrderListAllAdapter extends TRecyclerAdapter<OrderListInfoBean> {

    private OrderAllFragment orderAllFragment;

    public OrderListAllAdapter(OrderAllFragment orderAllFragment,@Nullable List<OrderListInfoBean> data) {
        super(data);
        this.orderAllFragment = orderAllFragment;
        mLayoutResId = addItemLayoutRes();
    }


    @Override
    protected int addItemLayoutRes() {
        //        0：待支付；1：待发货；2：待收货；3：待评价；4：已完成；5：已取消；6：退款中；7：已退款；
        return R.layout.item_orderlist;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListInfoBean item) {

        helper.setText(R.id.item_orderlist_shopname, item.getShopName());

        int orderType = item.getOrderType();
        if(orderType == 3){
            helper.setVisible(R.id.item_orderlist_ysicon, true);
        }else{
            helper.setVisible(R.id.item_orderlist_ysicon, false);
        }

        helper.setText(R.id.item_orderlist_goodstotal, "共" + item.getTotalGoodsNum() + "件商品  合计：￥" + item.getTotalOrderFee().setScale(2, BigDecimal.ROUND_HALF_UP));

        OrderListAllItemAdapter allItemAdapter = new OrderListAllItemAdapter(item.getDetails());
        RecyclerView recyclerView = helper.getView(R.id.item_orderlist_goodslist);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
        int row = 0;
        if (item.getDetails() != null) {
            row = item.getDetails().size();
        }
        layoutParams.height = mContext.getResources().getDimensionPixelOffset(R.dimen.dp100) * row;
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        recyclerView.setLayoutParams(layoutParams);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setAdapter(allItemAdapter);

        allItemAdapter.setOnItemClickListener((adapter, view, position) -> orderAllFragment.skipOrderDetailPage(item));

        helper.addOnClickListener(R.id.item_orderlist_btnright, R.id.item_orderlist_btnmiddle, R.id.item_orderlist_btnleft);

        int status = item.getTradeStatus();
        if (status == 0) {
            helper.setText(R.id.item_orderlist_orderstatus, "待支付");
            helper.getView(R.id.item_orderlist_bottom_rl).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_orderlist_btnleft).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_orderlist_btnmiddle).setVisibility(View.GONE);
            helper.getView(R.id.item_orderlist_btnright).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_orderlist_btnleft, "取消订单");
            helper.setText(R.id.item_orderlist_btnright, "付款");
//            helper.setBackgroundRes(R.id.item_orderlist_btnleft, R.drawable.shape_r_gray_line);
//            helper.setBackgroundRes(R.id.item_orderlist_btnright, R.drawable.shape_r_red);

        } else if (status == 1) {
            helper.setText(R.id.item_orderlist_orderstatus, "待发货");

            helper.getView(R.id.item_orderlist_bottom_rl).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_orderlist_btnleft).setVisibility(View.GONE);
            helper.getView(R.id.item_orderlist_btnmiddle).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_orderlist_btnright).setVisibility(View.GONE);
            helper.setText(R.id.item_orderlist_btnmiddle, "申请退款");

//            helper.getView(R.id.item_orderlist_bottom_rl).setVisibility(View.VISIBLE);
//            helper.getView(R.id.item_orderlist_btnleft).setVisibility(View.VISIBLE);
//            helper.setText(R.id.item_orderlist_btnleft, "提醒发货");
//            helper.setText(R.id.item_orderlist_btnright, "申请退款");

        } else if (status == 2) {
            helper.setText(R.id.item_orderlist_orderstatus, "待收货");

            helper.getView(R.id.item_orderlist_bottom_rl).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_orderlist_btnleft).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_orderlist_btnmiddle).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_orderlist_btnright).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_orderlist_btnleft, "查看物流");
            helper.setText(R.id.item_orderlist_btnmiddle, "申请退款");
            helper.setText(R.id.item_orderlist_btnright, "确认收货");

        } else if (status == 3) {
            helper.setText(R.id.item_orderlist_orderstatus, "待评价");

            helper.getView(R.id.item_orderlist_bottom_rl).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_orderlist_btnleft).setVisibility(View.GONE);
            helper.getView(R.id.item_orderlist_btnmiddle).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_orderlist_btnright).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_orderlist_btnmiddle, "删除订单");
            helper.setText(R.id.item_orderlist_btnright, "去评价");
        } else if (status == 4) {
            helper.setText(R.id.item_orderlist_orderstatus, "已完成");
            helper.getView(R.id.item_orderlist_bottom_rl).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_orderlist_btnleft).setVisibility(View.GONE);
            helper.getView(R.id.item_orderlist_btnmiddle).setVisibility(View.GONE);
            helper.getView(R.id.item_orderlist_btnright).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_orderlist_btnright, "删除订单");
        } else if (status == 5) {
            helper.setText(R.id.item_orderlist_orderstatus, "已取消");
            helper.getView(R.id.item_orderlist_bottom_rl).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_orderlist_btnleft).setVisibility(View.GONE);
            helper.getView(R.id.item_orderlist_btnmiddle).setVisibility(View.GONE);
            helper.getView(R.id.item_orderlist_btnright).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_orderlist_btnright, "删除订单");
        } else if (status == 6) {
            helper.setText(R.id.item_orderlist_orderstatus, "退款中");
            helper.getView(R.id.item_orderlist_bottom_rl).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_orderlist_btnleft).setVisibility(View.GONE);
            helper.getView(R.id.item_orderlist_btnmiddle).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_orderlist_btnright).setVisibility(View.GONE);

            helper.setText(R.id.item_orderlist_btnmiddle, "退款中");

        } else if (status == 7) {
            helper.setText(R.id.item_orderlist_orderstatus, "退款完成");
            helper.getView(R.id.item_orderlist_bottom_rl).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_orderlist_btnleft).setVisibility(View.GONE);
            helper.getView(R.id.item_orderlist_btnmiddle).setVisibility(View.VISIBLE);
            helper.getView(R.id.item_orderlist_btnright).setVisibility(View.VISIBLE);

            helper.setText(R.id.item_orderlist_btnmiddle, "退款完成");
            helper.setText(R.id.item_orderlist_btnright, "删除订单");
        } else {
            //其它
            helper.setText(R.id.item_orderlist_orderstatus, "未知");
            helper.getView(R.id.item_orderlist_bottom_rl).setVisibility(View.GONE);
        }
    }

}