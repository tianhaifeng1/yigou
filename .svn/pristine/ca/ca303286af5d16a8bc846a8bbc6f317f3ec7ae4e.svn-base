package com.work.doctor.fruits.activity.vip;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.BalanceStatInfoBean;
import com.t.httplib.yigou.bean.resp.BankcardInfoBean;
import com.t.httplib.yigou.bean.resp.MoneyRecordApplyListInfoBean;
import com.t.httplib.yigou.bean.resp.MoneyRecordListInfoBean;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.assist.AndroidBug5497Workaround;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.BankcardSelectDialog;

import java.util.ArrayList;
import java.util.List;

public class MyEarningTxActivity extends DemoMVPActivity<MyEarningView, MyEarningPresenter>
        implements MyEarningView, View.OnClickListener {

    private TextView mAmeMoney;
    private EditText mAmeMoneyTx;
    private TextView mAmeMoneyTxall;
    private TextView mAmeMoneyTxStartmoney;
    private TextView mAmeMoneySelectbank;
    private EditText mAmeMoneyName;
    private EditText mAmeMoneyBanknumber;
    private EditText mAmeMoneyBanknumber2;
    private EditText mAmeMoneyPhonenumber;
    private TextView mAmeMoneyTxbtn;

    private String moneyStr = "";

    private String bankKey = "";

    private String startMoney = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_earning_tx);
        AndroidBug5497Workaround.assistActivity(rootView);
    }

    @Override
    protected MyEarningPresenter initPersenter() {
        return new MyEarningPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("提现申请", true);

        mAmeMoney = findViewById(R.id.ame_money);
        mAmeMoneyTx = findViewById(R.id.ame_money_tx);
        mAmeMoneyTxall = findViewById(R.id.ame_money_txall);
        mAmeMoneyTxStartmoney = findViewById(R.id.ame_money_tx_startmoney);
        mAmeMoneySelectbank = findViewById(R.id.ame_money_selectbank);
        mAmeMoneyName = findViewById(R.id.ame_money_name);
        mAmeMoneyBanknumber = findViewById(R.id.ame_money_banknumber);
        mAmeMoneyBanknumber2 = findViewById(R.id.ame_money_banknumber2);
        mAmeMoneyPhonenumber = findViewById(R.id.ame_money_phonenumber);
        mAmeMoneyTxbtn = findViewById(R.id.ame_money_txbtn);

        mAmeMoneyTxall.setOnClickListener(this);
        mAmeMoneySelectbank.setOnClickListener(this);
        mAmeMoneyTxbtn.setOnClickListener(this);

        moneyStr = getIntent().getStringExtra("money");

        mAmeMoney.setText(moneyStr+"元");

        getPresenter().getTxMoneyData();
        getPresenter().getBankcardListData();

    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids){
            case R.id.ame_money_txall:
                //全部提现
                mAmeMoneyTx.setText(moneyStr);
                break;
            case R.id.ame_money_selectbank:
                //选择银行
                BankcardSelectDialog selectDialog = new BankcardSelectDialog.Builder<BankcardInfoBean>(context)
                        .setPostion(0)
                        .setTitle("可选用的银行")
                        .setBankcardList(bankcardlist)
                        .setOnBankcardSelectListener((view, position) -> {
                            mAmeMoneySelectbank.setText(bankcardlist.get(position).getBankcardBankname());
                            bankKey = bankcardlist.get(position).getDataKey();
                        }).create();
                selectDialog.show(getSupportFragmentManager(),"dialog_bankcard_select");
                break;
            case R.id.ame_money_txbtn:
                //提现
                String moneyTx = mAmeMoneyTx.getText().toString().trim();
                String bankName = mAmeMoneySelectbank.getText().toString().trim();
                String bankRealName = mAmeMoneyName.getText().toString().trim();
                String bankNo1 = mAmeMoneyBanknumber.getText().toString().trim();
                String bankNo2 = mAmeMoneyBanknumber2.getText().toString().trim();
                String phoneNumber = mAmeMoneyPhonenumber.getText().toString().trim();
                getPresenter().getApply(moneyTx, moneyStr, bankName, bankRealName, bankNo1, bankNo2, phoneNumber, bankKey, startMoney);

                break;
        }
    }

    private List<BankcardInfoBean> bankcardlist = new ArrayList<>();

    @Override
    public void getListDataSySuccess(List<MoneyRecordListInfoBean> list) {

    }

    @Override
    public void getTxListDataTxSuccess(List<MoneyRecordApplyListInfoBean> list) {

    }

    @Override
    public void getUserBalanceDataInfoSuccess(BalanceStatInfoBean infoBean) {

    }

    @Override
    public void getZyeSuccess() {

    }

    @Override
    public void getApplySuccess() {
        tRemind("提现成功");
        finish();
    }

    @Override
    public void getBankcardListDataSuccess(List<BankcardInfoBean> list) {
        this.bankcardlist.clear();
        this.bankcardlist.addAll(list);
//        for (int i = 0; i < 10; i++) {
//            BankcardInfoBean infoBean = new BankcardInfoBean();
//            infoBean.setBankName("中国银行");
//            infoBean.setBankIcon("");
//            infoBean.setIsSelect(i == 0 ? true : false);
//            list.add(infoBean);
//        }
    }

    @Override
    public void getTxStartMoneySuccess(BankcardInfoBean infoBean) {
        if (infoBean == null || infoBean.getDataValue() == null || infoBean.getDataValue().equals("")) {
            startMoney = "100";//起始金额默认为100
        } else {
            startMoney = infoBean.getDataValue();
        }
        startMoney = BigDecimalUtil.formatNumberString(startMoney);
        mAmeMoneyTxStartmoney.setText("最低提现金额" + startMoney + "元");
    }
}
