package com.work.doctor.fruits.wxapi;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.t.databaselib.BigDecimalUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.trjx.tbase.module.titlemodule.TitleModule;
import com.trjx.tlibs.uils.GlideUtile;
import com.trjx.tlibs.uils.Logger;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.MainNavActivity;
import com.work.doctor.fruits.activity.order.OrderDetailActivity;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.JgUtils;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.math.BigDecimal;

public class WXPayEntryActivity extends DemoMVPActivity<WXPayEntryView, WXPayEntryPresenter> implements IWXAPIEventHandler, WXPayEntryView {

    private IWXAPI api;

    private ImageView mWxPayImg;
    private TextView mWxPayState;
    private TextView mWxPayMessage;
    private TextView mWxPayLook;
    private TextView mWxPayMain;
    private RelativeLayout mWxPayRl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_result);

        api = WXAPIFactory.createWXAPI(this, DemoConstant.wx_app_id);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("订单支付成功");
        titleModule.initTitleMenu(TitleModule.MENU_TEXT, "完成");
        mWxPayImg = findViewById(R.id.wx_pay_img);
        mWxPayState = findViewById(R.id.wx_pay_state);
        mWxPayMessage = findViewById(R.id.wx_pay_message);
        mWxPayLook = findViewById(R.id.wx_pay_look);
        mWxPayMain = findViewById(R.id.wx_pay_main);
        mWxPayRl = findViewById(R.id.wx_pay_rl);

        if(DemoConstant.orderType == 1){
            mWxPayLook.setVisibility(View.VISIBLE);
        }else{
            mWxPayLook.setVisibility(View.GONE);
        }

        mWxPayLook.setOnClickListener(v -> {
            Intent intent = new Intent(this, OrderDetailActivity.class);
            intent.putExtra("orderNo", DemoConstant.zfOrderId);
            startActivity(intent);
            finish();
        });
        mWxPayMain.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainNavActivity.class);
            intent.putExtra("position", 1);
            startActivity(intent);
            finish();
        });

        //余额支付成功的情况
        int code = getIntent().getIntExtra("code", -1);
        if(code == 1){

            if (DemoConstant.jgPurchaseBeanList != null && DemoConstant.jgPurchaseBeanList.size() > 0) {
                for (int i = 0, size = DemoConstant.jgPurchaseBeanList.size(); i < size; i++) {
                    //购买商品统计
                    JgUtils.purchaseEvent(this, DemoConstant.jgPurchaseBeanList.get(i));
                }
                DemoConstant.jgPurchaseBeanList.clear();
            }

            titleModule.initTitle("订单支付成功");
            mWxPayState.setText("订单支付成功");
            GlideUtile.bindImageView(context, R.mipmap.t_zf_success, mWxPayImg);
            mWxPayMessage.setVisibility(View.VISIBLE);
            mWxPayRl.setVisibility(View.VISIBLE);



        }
    }

    @Override
    public void onClickRightText(View view) {
        super.onClickRightText(view);
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Logger.t("onPayFinish, errCode = " + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
                //支付成功
                if(DemoConstant.orderType == 0){
                    titleModule.initTitle("充值成功");
                    mWxPayState.setText("充值成功");
//                    DemoConstant.czBlance = infoBean.getPayMoney();
                    DemoConstant.balance = BigDecimalUtil.add(DemoConstant.balance.doubleValue(), DemoConstant.czBlance.doubleValue());
                    DemoConstant.czBlance = BigDecimal.ZERO;
                }else{
                    if (DemoConstant.jgPurchaseBeanList != null && DemoConstant.jgPurchaseBeanList.size() > 0) {
                        for (int i = 0, size = DemoConstant.jgPurchaseBeanList.size(); i < size; i++) {
                            //购买商品统计
                            JgUtils.purchaseEvent(this, DemoConstant.jgPurchaseBeanList.get(i));
                        }
                        DemoConstant.jgPurchaseBeanList.clear();
                    }

                    titleModule.initTitle("订单支付成功");
                    mWxPayState.setText("订单支付成功");
                }

                GlideUtile.bindImageView(context, R.mipmap.t_zf_success, mWxPayImg);
                mWxPayMessage.setVisibility(View.VISIBLE);
                mWxPayRl.setVisibility(View.VISIBLE);
            } else if (resp.errCode == BaseResp.ErrCode.ERR_COMM) {
                //支付失败
                if(DemoConstant.orderType == 0){
                    titleModule.initTitle("充值失败");
                    mWxPayState.setText("充值失败");
                }else{
                    titleModule.initTitle("订单支付失败");
                    mWxPayState.setText("订单支付失败");
                }
                GlideUtile.bindImageView(context, R.mipmap.t_zf_fail, mWxPayImg);
                mWxPayRl.setVisibility(View.GONE);
                mWxPayMessage.setVisibility(View.GONE);
            } else if (resp.errCode == BaseResp.ErrCode.ERR_USER_CANCEL) {
                //支付取消
                if(DemoConstant.orderType == 0){
                    titleModule.initTitle("充值取消");
                    mWxPayState.setText("充值取消");
                }else{
                    titleModule.initTitle("订单支付取消");
                    mWxPayState.setText("订单支付取消");
                }
                GlideUtile.bindImageView(context, R.mipmap.t_zf_fail, mWxPayImg);
                mWxPayRl.setVisibility(View.GONE);
                mWxPayMessage.setVisibility(View.GONE);
            }

        }
    }

    @Override
    protected WXPayEntryPresenter initPersenter() {
        return new WXPayEntryPresenter(this);
    }
}