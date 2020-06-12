package com.work.doctor.fruits.activity.adapter;

import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.OrderListInfoBean;
import com.t.httplib.yigou.bean.resp.RefundDetailInfoBean;
import com.t.httplib.yigou.bean.resp.TkOrderInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.order.OrderListAllItemAdapter2;

import java.math.BigDecimal;
import java.util.List;

public class TkOrderAdapter extends TRecyclerAdapter<TkOrderInfoBean> {

    public TkOrderAdapter(@Nullable List<TkOrderInfoBean> data) {
        super(data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_tk;
    }

    @Override
    protected void convert(BaseViewHolder helper, TkOrderInfoBean item) {

        OrderListInfoBean infoBean = item.getOrder();
        RefundDetailInfoBean refundDetailInfoBean = item.getRefund();

        helper.setText(R.id.item_tk_shopname, infoBean.getShopName());
        helper.setText(R.id.item_tk_shopaddress, infoBean.getShopName());
        int status = refundDetailInfoBean.getStatus();
//        0申请 1处理中（未收到货的情况） 2已退款 3驳回
        String str = "退款中";
        if(status == 2){
            str = "退款成功    退款成功"+  "￥" + refundDetailInfoBean.getRefundFee().setScale(2, BigDecimal.ROUND_HALF_UP)+"元";
            helper.getView(R.id.item_tk_tk).setVisibility(View.GONE);
        }else if(status == 3){
            str = "退款失败    " + refundDetailInfoBean.getReason();
            helper.getView(R.id.item_tk_tk).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.item_tk_tk).setVisibility(View.GONE);
        }

        helper.setText(R.id.item_tk_result,str);

        OrderListAllItemAdapter2 allItemAdapter = new OrderListAllItemAdapter2(infoBean.getDetails());
        RecyclerView recyclerView = helper.getView(R.id.item_tk_recyclerview);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
        int row = 0;
        if (infoBean.getDetails() != null) {
            row = infoBean.getDetails().size();
        }
        layoutParams.height = mContext.getResources().getDimensionPixelOffset(R.dimen.dp110) * row;
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        recyclerView.setLayoutParams(layoutParams);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setAdapter(allItemAdapter);

        helper.addOnClickListener(R.id.item_tk_look, R.id.item_tk_tk);

    }

}
