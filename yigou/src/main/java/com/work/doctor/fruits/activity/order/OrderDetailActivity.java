package com.work.doctor.fruits.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.httplib.yigou.bean.resp.OrderDetailInfoBean;
import com.t.httplib.yigou.bean.resp.OrderDetailInfoShopInfoBean;
import com.trjx.tlibs.uils.Logger;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.Goods.GoodsDetial2Activity;
import com.work.doctor.fruits.assist.DemoUtils;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.CommonPopupWindow;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDetailActivity extends DemoMVPActivity<OrderDetailView, OrderDetailPresenter>
        implements OrderDetailView, View.OnClickListener, CommonPopupWindow.ViewInterface, BaseQuickAdapter.OnItemClickListener {

    private ImageView ivAddress;
    private TextView tvConsignee;
    private TextView tvAddress;
    private TextView tvPhonenumber;
    private RecyclerView recyclerView;
    private TextView tvInvoiceInformationMessage;
    private TextView tvOrderPrice;
    private TextView tvOrderFreight;
    private TextView tvYhjmoney;
    private TextView tvInvoiceInformation;
    private TextView tvRealPayment;
    private TextView tvOrderStatus;
    private TextView tvOrderContentText;
//    private TextView tvOrderNumber;
//    private TextView tvOrderTime;
//    private TextView tvOrderPerson;
//    private TextView tvContact;
//    private TextView tvShippingAddress;
//    private TextView tvShippingBeizhu;

    private String orderNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
    }

    @Override
    protected OrderDetailPresenter initPersenter() {
        return new OrderDetailPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("订单详情", true);

        orderNo = getIntent().getStringExtra("orderNo");

        ivAddress = findViewById(R.id.iv_address);
        tvConsignee = findViewById(R.id.tv_consignee);
        tvAddress = findViewById(R.id.tv_address);
        tvPhonenumber = findViewById(R.id.tv_phonenumber);
        recyclerView = findViewById(R.id.recyclerView);
        tvInvoiceInformationMessage = findViewById(R.id.tv_invoice_information_message);
        tvOrderPrice = findViewById(R.id.tv_order_price);
        tvOrderFreight = findViewById(R.id.tv_order_freight);
        tvYhjmoney = findViewById(R.id.tv_yhjmoney);
        tvInvoiceInformation = findViewById(R.id.tv_invoice_information);
        tvRealPayment = findViewById(R.id.tv_real_payment);
        tvOrderStatus = findViewById(R.id.tv_order_status);
        tvOrderContentText = findViewById(R.id.tv_order_contenttext);
//        tvOrderNumber = findViewById(R.id.tv_order_number);
//        tvOrderTime = findViewById(R.id.tv_order_time);
//        tvOrderPerson = findViewById(R.id.tv_order_person);
//        tvContact = findViewById(R.id.tv_contact);
//        tvShippingAddress = findViewById(R.id.tv_shipping_address);
//        tvShippingBeizhu = findViewById(R.id.tv_shipping_beizhu);

        tvInvoiceInformation.setOnClickListener(this);

        getPresenter().getDataOrderDetail(orderNo);

    }

    private OrderDetailInfoBean orderDetailInfoBean;

    @Override
    public void getDataOrderDetail(OrderDetailInfoBean infoBean) {
        this.orderDetailInfoBean = infoBean;
        OrderDetailInfoBean.OrderBean orderBean = infoBean.getOrder();
        OrderDetailInfoBean.InvoiceBean invoiceBean = infoBean.getInvoice();

        //订单详情
        OrderListAllItemAdapter allItemAdapter = new OrderListAllItemAdapter(orderBean.getDetails());
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
        layoutParams.height = context.getResources().getDimensionPixelOffset(R.dimen.dp100) * orderBean.getDetails().size();
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        recyclerView.setLayoutParams(layoutParams);
        recyclerView.setLayoutManager(new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setAdapter(allItemAdapter);
        allItemAdapter.setOnItemClickListener(this);

//        if (orderBean.getOrderType() == 0) {
            tvOrderPrice.setText("￥" + orderBean.getTotalGoodsFee());
            tvRealPayment.setText("￥" + orderBean.getTotalOrderFee());
            tvOrderFreight.setText("￥" + orderBean.getDistribFee());
            if (invoiceBean == null) {
                tvInvoiceInformation.setText("补开发票");
            } else {
                if (invoiceBean.getCategory() == 0) {
                    int type = invoiceBean.getType();
                    if(type == 0){
                        tvInvoiceInformation.setText("电子发票(个人)");
                    }else{
                        tvInvoiceInformation.setText("纸质发票(个人)");
                    }
                } else if (invoiceBean.getCategory() == 1) {
                    int type = invoiceBean.getType();
                    if(type == 0){
                        tvInvoiceInformation.setText("电子发票(公司)");
                    }else{
                        tvInvoiceInformation.setText("纸质发票(公司)");
                    }
                }
            }
//        }

        tvYhjmoney.setText("-￥" + orderBean.getCouponValue().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
        tvConsignee.setText("收货人:" + orderBean.getReceiverName());
        tvPhonenumber.setText(orderBean.getReceiverPhone());
        tvAddress.setText("收货地址:" + orderBean.getReceiverAddress());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("订单编号：" + orderBean.getSystemOrderNo());
        stringBuilder.append("\n下单时间：" + sdf.format(new Date(orderBean.getAddTime())));
        stringBuilder.append("\n收货人：" + orderBean.getReceiverName());
        stringBuilder.append("\n联系方式：" + orderBean.getReceiverPhone());
        stringBuilder.append("\n收货地址：" + orderBean.getReceiverAddress());
        String noteStr = orderBean.getNote();
        if(!noteStr.equals("")){
            stringBuilder.append("\n备注:" + orderBean.getNote());
        }
        tvOrderContentText.setText(stringBuilder.toString());

//        tvOrderNumber.setText("订单编号:" + orderBean.getSystemOrderNo());
//        tvOrderPerson.setText("收货人:" + orderBean.getReceiverName());
//        tvContact.setText("联系方式:" + orderBean.getReceiverPhone());
//        tvShippingAddress.setText("收货地址:" + orderBean.getReceiverAddress());
//        tvShippingBeizhu.setText("备注:" + orderBean.getNote());
//
//        tvOrderTime.setText("下单时间:" + sdf.format(new Date(orderBean.getAddTime())));

        int type = orderBean.getTradeStatus();
        Logger.t("订单详情 状态：" + orderBean.getTradeStatus());
        if (type == 0) {
            tvOrderStatus.setText("待付款");
        } else if (type == 1) {
            tvOrderStatus.setText("待发货");
        } else if (type == 2) {
            tvOrderStatus.setText("待收货");
        } else if (type == 3) {
            tvOrderStatus.setText("待评价");
        } else if (type == 4) {
            tvOrderStatus.setText("已完成");
        } else if (type == 5) {
            tvOrderStatus.setText("已取消");
            //不显示发票行
            tvInvoiceInformationMessage.setVisibility(View.GONE);
            tvInvoiceInformation.setVisibility(View.GONE);
        } else if (type == 6) {
            tvOrderStatus.setText("退款中");
        } else if (type == 7) {
            tvOrderStatus.setText("已退款");
        }
    }

    @Override
    public void getDataOrderCancelSuccess() {
        tRemind("订单取消成功");
    }

    @Override
    public void getDataOrderDeleteSuccess() {
        tRemind("订单删除成功");
    }

    @Override
    public void getDataOrderReceiveSuccess() {
        tRemind("确认收货成功");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids){
            case R.id.tv_invoice_information:
                try {
                    int tradeStatus = orderDetailInfoBean.getOrder().getTradeStatus();
                    if (tradeStatus != 6 && tradeStatus != 7) {
                        if (!"补开发票".equals(tvInvoiceInformation.getText().toString())) {
                            showPop(v);
                        } else {
                            Intent intent = new Intent(context, InvoiceActivity.class);
                            intent.putExtra("invoice", String.valueOf(orderDetailInfoBean.getOrder().getSystemOrderNo()));
                            startActivityForResult(intent,100);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            if(resultCode == 200){
                getPresenter().getDataOrderDetail(orderNo);
            }
        }
    }


    private CommonPopupWindow popupWindow;

    private void showPop(View view) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popw_invoice_information, null);

        //测量View的宽高
        DemoUtils.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popw_invoice_information)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                // .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗   此方法无用 必须重写
                .setAnimationStyle(R.style.MyPopupWindow_anim_style)
                .setViewOnclickListener(this)
                .create();
        // 产生背景变暗效果，设置透明度  必须重写 否则背景无法变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.2f;
        //之前不写这一句也是可以实现的，这次突然没效果了。网搜之后加上了这一句就好了。据说是因为popUpWindow没有getWindow()方法。
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
//        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.BOTTOM, 0, 0);
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0);
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId) {
            case R.layout.popw_invoice_information:
                LinearLayout llinvoiceTypeCompany = view.findViewById(R.id.ll_invoice_type_company);
                LinearLayout llinvoiceTypePerson = view.findViewById(R.id.ll_invoice_type_person);
                LinearLayout llinvoiceTypeEmail = view.findViewById(R.id.ll_invoice_email);
                TextView tvSure = view.findViewById(R.id.tv_sure);
                TextView tvinvoiceTitle = view.findViewById(R.id.tv_invoice_title);
                TextView tvinvoiceType = view.findViewById(R.id.tv_invoice_type);
                TextView tvidentificationNumber = view.findViewById(R.id.tv_invoice_identification_number);
                TextView tvinvoiceAddress = view.findViewById(R.id.tv_invoice_address);
                TextView tvinvoicePhone = view.findViewById(R.id.tv_invoice_phone);
                TextView tvinvoiceOpeningBank = view.findViewById(R.id.tv_invoice_openingBank);
                TextView tvinvoiceAccountNumber = view.findViewById(R.id.tv_invoice_accountNumber);
                TextView tvinvoicePhonePerson = view.findViewById(R.id.tv_invoice_phone_person);
                TextView tvinvoiceEmail = view.findViewById(R.id.tv_invoice_email);
                tvinvoiceTitle.setText(orderDetailInfoBean.getInvoice().getRaised());
                if (orderDetailInfoBean.getInvoice().getCategory() == 0) {
                    llinvoiceTypeCompany.setVisibility(View.GONE);
                    llinvoiceTypePerson.setVisibility(View.VISIBLE);
                    tvinvoicePhonePerson.setText(orderDetailInfoBean.getInvoice().getPhone());
                    if(orderDetailInfoBean.getInvoice().getType() == 0){
                        llinvoiceTypeEmail.setVisibility(View.VISIBLE);
                        tvinvoiceEmail.setText(orderDetailInfoBean.getInvoice().getEmail());
                        tvinvoiceType.setText("电子发票(个人)");
                    }else{
                        tvinvoiceType.setText("纸质发票(个人)");
                        llinvoiceTypeEmail.setVisibility(View.GONE);
                    }

                } else if (orderDetailInfoBean.getInvoice().getCategory() == 1) {
                    llinvoiceTypeCompany.setVisibility(View.VISIBLE);
                    llinvoiceTypePerson.setVisibility(View.GONE);
                    tvinvoiceAddress.setText(orderDetailInfoBean.getInvoice().getAddress());
                    tvinvoicePhone.setText(orderDetailInfoBean.getInvoice().getPhone());
                    tvinvoiceOpeningBank.setText(orderDetailInfoBean.getInvoice().getOpeningBank());
                    tvinvoiceAccountNumber.setText(orderDetailInfoBean.getInvoice().getAccountNumber());
                    tvidentificationNumber.setText(orderDetailInfoBean.getInvoice().getIdentifyNo());
                    if(orderDetailInfoBean.getInvoice().getType() == 0){
                        llinvoiceTypeEmail.setVisibility(View.VISIBLE);
                        tvinvoiceEmail.setText(orderDetailInfoBean.getInvoice().getEmail());
                        tvinvoiceType.setText("电子发票(公司)");
                    }else{
                        llinvoiceTypeEmail.setVisibility(View.GONE);
                        tvinvoiceType.setText("纸质发票(公司)");
                    }
                }
                tvSure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        OrderDetailInfoShopInfoBean infoBean = (OrderDetailInfoShopInfoBean) adapter.getData().get(position);
        Intent intent = new Intent(context, GoodsDetial2Activity.class);
        intent.putExtra("id", infoBean.getGoodsId());
        skipActivity(intent);
    }
}