package com.work.doctor.fruits.activity.vip;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.BalanceStatInfoBean;
import com.t.httplib.yigou.bean.resp.BankcardInfoBean;
import com.t.httplib.yigou.bean.resp.MoneyRecordApplyListInfoBean;
import com.t.httplib.yigou.bean.resp.MoneyRecordListInfoBean;
import com.trjx.tlibs.uils.SnackbarUtil;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.ShouyAdapter;
import com.work.doctor.fruits.activity.adapter.TixAdapter;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.RemindDialog;

import java.util.List;

/**
 * 我的收益页面
 */
public class MyEarningActivity extends DemoMVPActivity<MyEarningView, MyEarningPresenter>
        implements MyEarningView, View.OnClickListener {

    private TextView mAmeGuize;
    private TextView mAmeMoney;
    private TextView mAmeTixian;
    private TextView mAmeMoneyTotal;
    private TextView mAmeZye;
    private TextView mAmeShouyi;
    private TextView mAmeZhichu;
    private View mAmeShouyiLine;
    private View mAmeZhichuLine;
    private RecyclerView mAmeRecyclerview;
    private LinearLayout linearLayout;
    private int pageSize = 20;
    private int pageSy = 1;
    private int pageTx = 1;

    private ShouyAdapter shouyAdapter;
    private TixAdapter tixAdapter;

    //点击位置
    private int clickIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_earning);
    }

    @Override
    protected void initView() {
        super.initView();

        titleModule.initTitle("我的收益", true);

        mAmeGuize = findViewById(R.id.ame_guize);
        mAmeMoney = findViewById(R.id.ame_money);
        mAmeTixian = findViewById(R.id.ame_tixian);
        mAmeMoneyTotal = findViewById(R.id.ame_money_total);
        mAmeZye = findViewById(R.id.ame_zye);
        mAmeShouyi = findViewById(R.id.ame_shouyi);
        mAmeZhichu = findViewById(R.id.ame_zhichu);
        mAmeShouyiLine = findViewById(R.id.ame_shouyi_line);
        mAmeZhichuLine = findViewById(R.id.ame_zhichu_line);
        mAmeRecyclerview = findViewById(R.id.ame_recyclerview);
        linearLayout = findViewById(R.id.ame_ll);

        mAmeGuize.setOnClickListener(this);
        mAmeTixian.setOnClickListener(this);
        mAmeZye.setOnClickListener(this);

        mAmeShouyi.setOnClickListener(this);
        mAmeZhichu.setOnClickListener(this);

        mAmeRecyclerview.setLayoutManager(new LinearLayoutManager(context));

//        getPresenter().getUserBalanceDataInfo();


    }

    @Override
    protected MyEarningPresenter initPersenter() {
        return new MyEarningPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().getUserBalanceDataInfo();
        clickIndexEvent(clickIndex, true);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.ame_guize:
                //规则
//                Intent intent = new Intent(context, DemoWebActivity.class);
//                intent.putExtra("code", 4);
//                skipActivity(intent);
                skipActivity(MyEarningGzActivity.class);
                break;
            case R.id.ame_tixian:
                //提现
                if (moneyStr == null || moneyStr.equals("")||moneyStr.equals("0")) {
                    SnackbarUtil.showToast(rootView,"暂无收益");
                    return;
                }
                Intent intentTx = new Intent(context, MyEarningTxActivity.class);
                intentTx.putExtra("money", moneyStr);
                skipActivity(intentTx);
                break;
            case R.id.ame_zye:
                //转余额
                if (moneyStr == null || moneyStr.equals("")||moneyStr.equals("0")) {
                    SnackbarUtil.showToast(rootView,"暂无收益");
                    return;
                }
                clickZye();

                break;
            case R.id.ame_shouyi:
                //收益
                clickIndexEvent(0, false);
                break;
            case R.id.ame_zhichu:
                //支出
                clickIndexEvent(1, false);
                break;
        }
    }

    private void clickZye() {
        RemindDialog remindDialog = new RemindDialog.Builder(context)
                .setMessage("确认将收益全部转入余额？")
                .setCancleText("取消")
                .setAffirmText("确认")
                .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                    @Override
                    public void onRemindClickAffirm(View view) {
                        getPresenter().getZye();
                    }

                    @Override
                    public void onRemindClickCancle(View view) {

                    }
                }).create();
        remindDialog.show(getSupportFragmentManager(), "dialog_remind_zye");
    }


    private void clickIndexEvent(int clickIndex, boolean refresh) {
        if (!refresh && this.clickIndex == clickIndex) {
            //重复点击
            return;
        }
        this.clickIndex = clickIndex;
        if (clickIndex == 1) {
            mAmeZhichu.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelOffset(R.dimen.dp16));
            mAmeZhichu.setTextColor(getResources().getColor(R.color.color_name));
            mAmeZhichuLine.setVisibility(View.VISIBLE);

            mAmeShouyi.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelOffset(R.dimen.dp14));
            mAmeShouyi.setTextColor(getResources().getColor(R.color.color_content));
            mAmeShouyiLine.setVisibility(View.INVISIBLE);
            //获取支出列表
            pageTx = 1;
            getPresenter().getListDataTix(pageTx, pageSize);
        } else {
            mAmeShouyi.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelOffset(R.dimen.dp16));
            mAmeShouyi.setTextColor(getResources().getColor(R.color.color_name));
            mAmeShouyiLine.setVisibility(View.VISIBLE);

            mAmeZhichu.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelOffset(R.dimen.dp14));
            mAmeZhichu.setTextColor(getResources().getColor(R.color.color_content));
            mAmeZhichuLine.setVisibility(View.INVISIBLE);
            //获取收益列表
            pageSy = 1;
            getPresenter().getListDataShouy(pageSy, pageSize);
        }


    }


    @Override
    public void getListDataSySuccess(List<MoneyRecordListInfoBean> list) {
        final int size = list == null ? 0 : list.size();
        if (shouyAdapter == null || pageSy == 1) {
            shouyAdapter = new ShouyAdapter(list);
            shouyAdapter.setOnLoadMoreListener(() -> {
                pageSy++;
                getPresenter().getListDataShouy(pageSy, pageSize);
            }, mAmeRecyclerview);
            mAmeRecyclerview.setAdapter(shouyAdapter);
            if (pageSy == 1 && size == 0) {
                linearLayout.setVisibility(View.VISIBLE);
            } else {
                linearLayout.setVisibility(View.GONE);
            }
        } else {
            if (size > 0) {
                shouyAdapter.addData(list);
            }
        }
        if (size < pageSize) {
            //false：显示结尾没有更多数据，反之不显示
            shouyAdapter.loadMoreEnd(false);
        } else {
            shouyAdapter.loadMoreComplete();
        }

    }

    @Override
    public void getTxListDataTxSuccess(List<MoneyRecordApplyListInfoBean> list) {
        final int size = list == null ? 0 : list.size();
        if (tixAdapter == null || pageTx == 1) {
            tixAdapter = new TixAdapter(list);
            tixAdapter.setOnLoadMoreListener(() -> {
                pageTx++;
                getPresenter().getListDataTix(pageTx, pageSize);
            }, mAmeRecyclerview);
            mAmeRecyclerview.setAdapter(tixAdapter);
            tixAdapter.setOnItemClickListener((adapter, view, position) -> {
                Intent intent = new Intent(context, MyEarningTxRecordDetialActivity.class);
                MoneyRecordApplyListInfoBean infoBean = (MoneyRecordApplyListInfoBean) adapter.getData().get(position);
                intent.putExtra("info", infoBean);
                skipActivity(intent);
            });
            if (pageTx == 1 && size == 0) {
                linearLayout.setVisibility(View.VISIBLE);
            } else {
                linearLayout.setVisibility(View.GONE);
            }
        } else {
            if (size > 0) {
                tixAdapter.addData(list);
            }
        }
        if (size < pageSize) {
            //false：显示结尾没有更多数据，反之不显示
            tixAdapter.loadMoreEnd(false);
        } else {
            tixAdapter.loadMoreComplete();
        }
    }

    private String moneyStr = "";

    @Override
    public void getUserBalanceDataInfoSuccess(BalanceStatInfoBean infoBean) {
        moneyStr = BigDecimalUtil.formatNumberString(infoBean.getParam3());
        mAmeMoney.setText(moneyStr + "元");
        mAmeMoneyTotal.setText(BigDecimalUtil.formatNumberString(infoBean.getParam2()) + "元");
    }

    @Override
    public void getZyeSuccess() {
        SnackbarUtil.showToast(rootView, "转余额成功");
        getPresenter().getUserBalanceDataInfo();
    }

    @Override
    public void getApplySuccess() {

    }

    @Override
    public void getBankcardListDataSuccess(List<BankcardInfoBean> list) {

    }

    @Override
    public void getTxStartMoneySuccess(BankcardInfoBean list) {

    }
}
