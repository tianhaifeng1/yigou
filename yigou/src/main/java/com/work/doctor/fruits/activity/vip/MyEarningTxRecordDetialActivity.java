package com.work.doctor.fruits.activity.vip;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.MoneyRecordApplyListInfoBean;
import com.trjx.tbase.activity.InitTitleActivity;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MyEarningTxRecordDetialActivity extends InitTitleActivity {

    private ImageView mAmetrdBankicon;
    private TextView mAmetrdBankname;
    private TextView mAmetrdMoney;
    private TextView mAmetrdStatus;
    private TextView mAmetrdTime;
    private TextView mAmetrdName;
    private TextView mAmetrdStarttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_earning_tx_record_detial);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("提现申请", true);

        mAmetrdBankicon = findViewById(R.id.ametrd_bankicon);
        mAmetrdBankname = findViewById(R.id.ametrd_bankname);
        mAmetrdMoney = findViewById(R.id.ametrd_money);
        mAmetrdStatus = findViewById(R.id.ametrd_status);
        mAmetrdTime = findViewById(R.id.ametrd_time);
        mAmetrdName = findViewById(R.id.ametrd_name);
        mAmetrdStarttime = findViewById(R.id.ametrd_starttime);

        MoneyRecordApplyListInfoBean infoBean = (MoneyRecordApplyListInfoBean) getIntent().getSerializableExtra("info");

        mAmetrdBankname.setText(infoBean.getDrawName());
        mAmetrdMoney.setText("￥"+BigDecimalUtil.roundOffString(infoBean.getMoneyFee(), 2) + "元");

        String url = "https://rulongegou.oss-cn-beijing.aliyuncs.com/bank/bank_"+infoBean.getDrawCode()+".png";
        GlideUtile.bindImageView(context,url,mAmetrdBankicon);
        int status = infoBean.getStatus();
        String str = "";
        if(status == 0){
            str = "申请中...";
        }else if(status == 1){
            str = "提现成功";
        }else if(status == 2){
            str = "提现失败";
        }
        mAmetrdStatus.setText(str);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Long enterTime = infoBean.getEnterTime();
        Long applyTime = infoBean.getApplyTime();
        if (enterTime == null || enterTime == 0) {
            if (applyTime != null && applyTime > 0) {
                mAmetrdTime.setText(dateFormat.format(new Date(applyTime)));
            }
        }else{
            mAmetrdTime.setText(dateFormat.format(new Date(enterTime)));
        }

        if (applyTime != null && applyTime > 0) {
            mAmetrdStarttime.setText(dateFormat.format(new Date(applyTime)));
        }

        String string = "(尾号 ";
        String bankNo = infoBean.getDrawNo();
        String realName = infoBean.getRealName();
        if (bankNo != null && bankNo.length() > 4) {
            string = string + bankNo.substring(bankNo.length() - 4) + ")"+realName;
        }else{
            string = string + "****" + ")" + realName;
        }
        mAmetrdName.setText(string);

    }


}
