package com.work.doctor.fruits.activity.order;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.t.httplib.yigou.bean.req.ReqRefundInfo;
import com.t.httplib.yigou.bean.resp.BankcardInfoBean;
import com.t.httplib.yigou.bean.resp.OrderListInfoBean;
import com.t.httplib.yigou.bean.resp.RefundDetailInfoBean;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.assist.AndroidBug5497Workaround;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.BankcardSelectDialog;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderRefundActivity extends DemoMVPActivity<OrderRefundView, OrderRefundPresenter> implements OrderRefundView, View.OnClickListener {

    private LinearLayout mOrderRefundLlTop;
    private TextView mOrderRefundToptext;
    private TextView mOrderRefundToptext2;
    private RecyclerView mOrderRefundRecyclerview;
    private TextView mOrderRefundTotalprice;
    private RelativeLayout mOrderRefundRlTuikuai;
    private TextView mOrderRefundQuestiontext;
    private RelativeLayout mOrderRefundQuestion;
    private TextView mOrderRefundPricetext;
    private RelativeLayout mOrderRefundPrice;
    private TextView mOrderRefundBeizhu;
    private EditText mOrderRefundSmtext;
    private RelativeLayout mOrderRefundSm;
    private TextView mOrderRefundText;
    private TextView mOrderRefundCommit;


    private ReqRefundInfo refundInfo = new ReqRefundInfo();

    private int code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_refund);
        AndroidBug5497Workaround.assistActivity(rootView);
    }

    @Override
    protected OrderRefundPresenter initPersenter() {
        return new OrderRefundPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();

        titleModule.initTitle("退款", true);

        mOrderRefundLlTop = findViewById(R.id.order_refund_ll_top);
        mOrderRefundToptext = findViewById(R.id.order_refund_toptext);
        mOrderRefundToptext2 = findViewById(R.id.order_refund_toptext2);
        mOrderRefundRecyclerview = findViewById(R.id.order_refund_recyclerview);
        mOrderRefundTotalprice = findViewById(R.id.order_refund_totalprice);
        mOrderRefundRlTuikuai = findViewById(R.id.order_refund_rl_tuikuai);
        mOrderRefundQuestiontext = findViewById(R.id.order_refund_questiontext);
        mOrderRefundQuestion = findViewById(R.id.order_refund_question);
        mOrderRefundPricetext = findViewById(R.id.order_refund_pricetext);
        mOrderRefundPrice = findViewById(R.id.order_refund_price);
        mOrderRefundBeizhu = findViewById(R.id.order_refund_beizhu);
        mOrderRefundSmtext = findViewById(R.id.order_refund_smtext);
        mOrderRefundSm = findViewById(R.id.order_refund_sm);
        mOrderRefundText = findViewById(R.id.order_refund_text);
        mOrderRefundCommit = findViewById(R.id.order_refund_commit);

        mOrderRefundQuestiontext.setOnClickListener(this);
        mOrderRefundCommit.setOnClickListener(this);


        OrderListInfoBean infoBean = (OrderListInfoBean) getIntent().getSerializableExtra("info");
        code = getIntent().getIntExtra("code", 0);
        //初始化赋值
        refundInfo.setNote("");
        refundInfo.setAccount("");
        refundInfo.setSystemOrderNo(infoBean.getSystemOrderNo());

        mOrderRefundTotalprice.setText("￥" + infoBean.getTotalOrderFee().setScale(2, BigDecimal.ROUND_HALF_UP));

        OrderListAllItemAdapter allItemAdapter = new OrderListAllItemAdapter(infoBean.getDetails());
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mOrderRefundRecyclerview.getLayoutParams();
        layoutParams.height = context.getResources().getDimensionPixelOffset(R.dimen.dp100) * infoBean.getDetails().size();
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        mOrderRefundRecyclerview.setLayoutParams(layoutParams);
        mOrderRefundRecyclerview.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mOrderRefundRecyclerview.setAdapter(allItemAdapter);

        if (code == 1) {
            titleModule.setTitleText("申请退款");
            mOrderRefundBeizhu.setText("最多￥" + infoBean.getTotalOrderFee().setScale(2, BigDecimal.ROUND_HALF_UP) + "，包含邮费");
            mOrderRefundPricetext.setText("￥" + infoBean.getTotalOrderFee().setScale(2, BigDecimal.ROUND_HALF_UP));
            getPresenter().getQuestionListData();
        } else if (code == 2 || code == 3) {
            titleModule.setTitleText("退款详情");

            mOrderRefundRlTuikuai.setVisibility(View.GONE);
            mOrderRefundQuestion.setVisibility(View.GONE);
            mOrderRefundPrice.setVisibility(View.GONE);
            mOrderRefundBeizhu.setVisibility(View.GONE);
            mOrderRefundSm.setVisibility(View.GONE);
            mOrderRefundCommit.setVisibility(View.GONE);

            mOrderRefundText.setVisibility(View.VISIBLE);
            mOrderRefundLlTop.setVisibility(View.VISIBLE);

            getPresenter().getRefundDetial(infoBean.getSystemOrderNo());

        }
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.order_refund_questiontext:
                BankcardSelectDialog selectDialog = new BankcardSelectDialog.Builder<BankcardInfoBean>(context)
                        .setPostion(0)
                        .setTitle("选择退款原因")
                        .setBankcardList(list)
                        .setOnBankcardSelectListener((view, position) -> {
                            String string = list.get(position).getBankcardBankname();
                            mOrderRefundQuestiontext.setText(string);
                            refundInfo.setAccount(string);
                        }).create();
                selectDialog.show(getSupportFragmentManager(), "dialog_question_select");

                break;
            case R.id.order_refund_commit:

                String smStr = mOrderRefundSmtext.getText().toString().trim();
                refundInfo.setNote(smStr);

                getPresenter().getApplyRefund(refundInfo);

                break;
        }
    }

    private List<BankcardInfoBean> list;

    @Override
    public void getQuestionListDataSuccess(List<BankcardInfoBean> list) {
        this.list = list;
    }

    @Override
    public void getApplyRefundSuccess() {
        tRemind("申请成功");
        setResult(200);
        finish();
    }

    @Override
    public void getRefundDetialSuccess(RefundDetailInfoBean infoBean) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = "订单编号：" + infoBean.getSystemOrderNo() + "\n" +
                "退款原因：" + infoBean.getAccount() + "\n" +
                "退款金额：￥" + infoBean.getRefundFee().setScale(2, BigDecimal.ROUND_HALF_UP) + "\n" +
                "退款备注：" + infoBean.getNote() + "\n" +
                "申请时间：" + sdf.format(new Date(infoBean.getAddTime()));
        mOrderRefundText.setText(str);

        int status = infoBean.getStatus();
//        0申请 1处理中（未收到货的情况） 2已退款 3驳回
        String str2 = "申请退款中";
        if(status == 2){
            str2 = "申请退款成功";
        }else if(status == 3){
            str2 = "申请退款失败";
        }
        mOrderRefundToptext.setText(str2);

        if(code == 2){
            mOrderRefundToptext2.setText("请等待商家确认");
        }else if(code == 3){
            String reasonStr = infoBean.getReason();
            if (reasonStr != null && !reasonStr.equals("")) {
                mOrderRefundToptext2.setVisibility(View.VISIBLE);
                mOrderRefundToptext2.setText(reasonStr);
            }
        }
    }
}
