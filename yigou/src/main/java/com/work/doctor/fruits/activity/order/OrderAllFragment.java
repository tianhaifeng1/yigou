package com.work.doctor.fruits.activity.order;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.httplib.yigou.bean.WechetPayInfo;
import com.t.httplib.yigou.bean.resp.OrderDetailInfoShopInfoBean;
import com.t.httplib.yigou.bean.resp.OrderListInfoBean;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.JgPurchaseBean;
import com.work.doctor.fruits.dialog.PaywaySelectDialog;
import com.work.doctor.fruits.dialog.RemindDialog;
import com.work.doctor.fruits.wxapi.WXPayEntryActivity;

import java.util.List;

import cn.jiguang.analytics.android.api.Currency;

public class OrderAllFragment extends OrderInitFragment<OrderListAllAdapter>
        implements PaywaySelectDialog.OnPaywayDialogClickListener {

    public OrderAllFragment(int tabStatus) {
        super(tabStatus);
    }

    @Override
    protected OrderListAllAdapter initAdapter() {
//         0：待支付；1：待发货；2：待收货；3：待评价；4：已完成；5：已取消；6：退款中；7：已退款；
        return new OrderListAllAdapter(this, null);
    }


    private String systemOrderNo = "";

    private int zfIndex = -1;

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//        ToastUtil2.showToast(activity.context,"点击了");
        int ids = view.getId();
        OrderListInfoBean infoBean = (OrderListInfoBean) adapter.getData().get(position);
        int status = infoBean.getTradeStatus();
        if (status == 0) {
            if (ids == R.id.item_orderlist_btnleft) {

                RemindDialog remindDialog = new RemindDialog.Builder(activity.context)
                        .setMessage("确定要取消订单吗？")
                        .setCancleText("考虑一下")
                        .setAffirmText("确定")
                        .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                            @Override
                            public void onRemindClickAffirm(View view) {
                                //取消订单
                                getPresenter().getDataOrderCancel(infoBean.getSystemOrderNo(), position);
                            }

                            @Override
                            public void onRemindClickCancle(View view) {

                            }
                        }).create();
                remindDialog.show(activity.getSupportFragmentManager(), "dialog_order_cancle");

            } else if (ids == R.id.item_orderlist_btnright) {
                //付款
                List<OrderDetailInfoShopInfoBean> infobeanList = infoBean.getDetails();
                if (infobeanList != null && infobeanList.size() > 0) {
                    DemoConstant.jgPurchaseBeanList.clear();
                    for (int i = 0, size = infobeanList.size(); i < size; i++) {
                        OrderDetailInfoShopInfoBean infoShopInfoBean = infobeanList.get(i);
                        JgPurchaseBean bean = new JgPurchaseBean();
                        bean.setPurchaseGoodsid(infoShopInfoBean.getGoodsId() + "");
                        bean.setPurchaseGoodsName(infoShopInfoBean.getGoodsName());
                        bean.setPurchasePrice(infoShopInfoBean.getSellPrice().doubleValue());
                        bean.setPurchaseSuccess(true);
                        bean.setPurchaseGoodsCount(infoShopInfoBean.getTotalNum());
                        bean.setPurchaseGoodsType(infoShopInfoBean.getCnname());
                        bean.setPurchaseCurrency(Currency.CNY);
                        DemoConstant.jgPurchaseBeanList.add(bean);
                    }
                }
                systemOrderNo = infoBean.getSystemOrderNo();
                zfIndex = position;

                new PaywaySelectDialog.Builder(activity.context)
                        .setCancelable(false)
                        .setOnPaywayDialogClickListener(this)
                        .create()
                        .show(getFragmentManager(), "dialog_payway");

            }

        } else if (status == 1) {
            if (ids == R.id.item_orderlist_btnmiddle) {
                //申请退款
                Intent intent = new Intent(activity.context, OrderRefundActivity.class);
                intent.putExtra("info", infoBean);
                intent.putExtra("code", 1);
                startActivityForResult(intent, 102);
            }

        } else if (status == 2) {
            if (ids == R.id.item_orderlist_btnleft) {
                //查看物流
                Intent intent = new Intent(activity.context, DdDeliveryDetailActivity.class);
                intent.putExtra("orderno", infoBean.getSystemOrderNo());
                intent.putExtra("shopId", infoBean.getShopId());
                activity.skipActivity(intent);
            } else if (ids == R.id.item_orderlist_btnmiddle) {
                //申请退款
                Intent intent = new Intent(activity.context, OrderRefundActivity.class);
                intent.putExtra("info", infoBean);
                intent.putExtra("code", 1);
                startActivityForResult(intent, 102);
            } else if (ids == R.id.item_orderlist_btnright) {

                RemindDialog remindDialog = new RemindDialog.Builder(activity.context)
                        .setMessage("确定此商品已经收到了吗？")
                        .setCancleText("还没有")
                        .setAffirmText("确定收到")
                        .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                            @Override
                            public void onRemindClickAffirm(View view) {
                                //确认收货
                                getPresenter().getDataOrderReceive(infoBean.getSystemOrderNo(), position);
                            }

                            @Override
                            public void onRemindClickCancle(View view) {

                            }
                        }).create();
                remindDialog.show(activity.getSupportFragmentManager(), "dialog_order_shouhuo");
            }
        } else if (status == 3) {
            if (ids == R.id.item_orderlist_btnright) {
                //去评价
                //上下文必须传当太难Fragment的上下文，不然不能触发onActivityResult回调方法
                Intent intent = new Intent(this.getContext(), OrderCommentActivity.class);
                intent.putExtra("comment", infoBean);
                startActivityForResult(intent, 101);

            } else if (ids == R.id.item_orderlist_btnmiddle) {
                //删除订单
                deleteOrder(infoBean, position);
            }

        } else if (status == 4) {
            //已完成
            if (ids == R.id.item_orderlist_btnright) {
                //删除订单
                deleteOrder(infoBean, position);
            }
        } else if (status == 5) {
            //已取消
            if (ids == R.id.item_orderlist_btnright) {
                //删除订单
                deleteOrder(infoBean, position);
            }
        } else if (status == 6) {
            //退款中
            if (ids == R.id.item_orderlist_btnmiddle) {
                //退款详情
                Intent intent = new Intent(activity.context, OrderRefundActivity.class);
                intent.putExtra("info", infoBean);
                intent.putExtra("code", 2);
                activity.skipActivity(intent);
            }
        } else if (status == 7) {
            //退款完成
            if (ids == R.id.item_orderlist_btnmiddle) {
                //退款详情
                Intent intent = new Intent(activity.context, OrderRefundActivity.class);
                intent.putExtra("info", infoBean);
                intent.putExtra("code", 3);
                activity.skipActivity(intent);
            }else if (ids == R.id.item_orderlist_btnright) {
                //删除订单
                deleteOrder(infoBean, position);
            }
        } else {
            //全部
        }
    }


    private void deleteOrder(OrderListInfoBean infoBean,int position){
        //删除订单
        RemindDialog remindDialog = new RemindDialog.Builder(activity.context)
                .setMessage("确定要删除此订单吗？")
                .setCancleText("考虑一下")
                .setAffirmText("确定")
                .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                    @Override
                    public void onRemindClickAffirm(View view) {
                        //删除订单
                        getPresenter().getDataOrderDelete(infoBean.getSystemOrderNo(), position);
                    }

                    @Override
                    public void onRemindClickCancle(View view) {

                    }
                }).create();
        remindDialog.show(activity.getSupportFragmentManager(), "dialog_order_delete");
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        OrderListInfoBean infoBean = (OrderListInfoBean) adapter.getData().get(position);
        skipOrderDetailPage(infoBean);
    }


    protected void skipOrderDetailPage(OrderListInfoBean infoBean) {
        String orderNo = infoBean.getSystemOrderNo();
        Intent intent = new Intent(activity.context, OrderDetailActivity.class);
        intent.putExtra("orderNo", orderNo);
        activity.skipActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200) {
            if (requestCode == 101) {
                recyclerModule.setPage(1);
                getRecyclerListData();
            } else if (requestCode == 102) {
                recyclerModule.setPage(1);
                getRecyclerListData();
            }
        }

    }


    private int payType = -1;

    @Override
    public void getPayMentSuccess(WechetPayInfo infoBean, int position) {
        super.getPayMentSuccess(infoBean, position);
        if (payType == 4) {
            tRemind("支付成功");
            adapter.remove(position);
            if (adapter.getData().size() == 0) {
                recyclerModule.isShowDefLayout(true);
            }
            Intent intent = new Intent(activity.context, WXPayEntryActivity.class);
            intent.putExtra("code", 1);//成功
            activity.skipActivity(intent);
        } else if (payType == 2) {
            //微信支付
            getPresenter().getWxPayInfo(activity.context, infoBean);
        }

    }

    @Override
    public void onPaywayDialogAffirmClick(View view,int payType) {
        this.payType = payType;
        getPresenter().payMent(systemOrderNo, payType, zfIndex);
    }
}
