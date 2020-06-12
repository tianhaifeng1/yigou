package com.work.doctor.fruits.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.GreenDaoAssist;
import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.WechatUserInfo;
import com.t.httplib.yigou.bean.req.ReqSynchroCartInfo;
import com.t.httplib.yigou.bean.req.ReqSynchroCartInfoOut;
import com.t.httplib.yigou.bean.resp.LoginInfoBean;
import com.trjx.tlibs.uils.SharedPreferencesUtils;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.DemoUtils;
import com.work.doctor.fruits.assist.JgUtils;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.base.DemoWebActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RegisterActivity extends DemoMVPActivity<LoginView, LoginPresenter>
        implements LoginView, View.OnClickListener {

    private EditText mLoginPhone;
    private EditText mLoginPsw;
    private TextView mLoginSendsms;
    private Button mLogin;
    private CheckBox mLoginCheckbox;
    private TextView mLoginYhxy;

    private WechatUserInfo info;

    private GreenDaoAssist greenDaoAssist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initView() {
        super.initView();

        greenDaoAssist = new GreenDaoAssist(((DemoApplication)getApplication()).databaseAssist);
        titleModule.initTitle("绑定手机号");

        mLoginPhone = findViewById(R.id.login_phone);
        mLoginPsw = findViewById(R.id.login_psw);
        mLoginSendsms = findViewById(R.id.login_sendsms);
        mLogin = findViewById(R.id.login);
        mLoginCheckbox = findViewById(R.id.login_checkbox);
        mLoginYhxy = findViewById(R.id.login_yhxy);

        mLoginYhxy.setOnClickListener(this);
        mLoginSendsms.setOnClickListener(this);
        mLogin.setOnClickListener(this);

        info = (WechatUserInfo) getIntent().getSerializableExtra("info");

        String phoneStr = (String) SharedPreferencesUtils.getParam(context, DemoConstant.user_phone, "");
        mLoginPhone.setText(phoneStr);
        mLoginPsw.setText("");

    }

    @Override
    protected LoginPresenter initPersenter() {
        return new LoginPresenter(this);
    }


    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.login_yhxy:
                Intent intent = new Intent(context, DemoWebActivity.class);
                intent.putExtra("code", 3);
                skipActivity(intent);
                break;
            case R.id.login_sendsms:
                String phoneNumber = mLoginPhone.getText().toString().trim();
                getPresenter().getYzm(phoneNumber);
                break;
            case R.id.login:
                String phoneNumber2 = mLoginPhone.getText().toString().trim();
                String psw2 = mLoginPsw.getText().toString().trim();
                boolean isAgree = mLoginCheckbox.isChecked();
                getPresenter().loginBindWx(phoneNumber2, psw2, isAgree, info);
                break;
        }
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int whas = msg.what;
            if (whas == 1) {
                if (djs >= 0) {
                    mLoginSendsms.setText(djs + "秒");
                    djs--;
                } else {
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                    mLoginSendsms.setEnabled(true);
                    mLoginSendsms.setText("重新获取");
                }
            }
        }
    };

    private Timer timer;
    private int djs = 60;

    @Override
    public void eventDjs() {
        mLoginSendsms.setEnabled(false);
        djs = 60;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, 0, 1000);
    }


    @Override
    public void loginSuccess(LoginInfoBean infoBean) {

        if (DemoUtils.changePfUser(infoBean.getStatus())) {
            DemoConstant.isChangeShop = true;
            DemoConstant.refershOne = true;
            DemoConstant.refershTwo = true;
            DemoConstant.refershThree = true;

            DemoConstant.isRefershShopInfo = true;
        }

        YigouConstant.token = infoBean.getToken();
        DemoConstant.balance = infoBean.getBalance();
        DemoConstant.userId = infoBean.getUserId();
        DemoConstant.userStatus = infoBean.getStatus();

        SharedPreferencesUtils.setParam(context, DemoConstant.user_token, infoBean.getToken());
        SharedPreferencesUtils.setParam(context, DemoConstant.user_id, infoBean.getUserId());
        SharedPreferencesUtils.setParam(context, DemoConstant.user_status, infoBean.getStatus());
        SharedPreferencesUtils.setParam(context, DemoConstant.user_phone, infoBean.getPhone());

        List<DatabaseGoodsInfo> shopInfoList = greenDaoAssist.queryAllGoods();
        if (shopInfoList != null && shopInfoList.size() > 0) {
            ReqSynchroCartInfoOut infoOut = new ReqSynchroCartInfoOut();
            List<ReqSynchroCartInfo> list = new ArrayList<>();
            for (DatabaseGoodsInfo info : shopInfoList) {
                ReqSynchroCartInfo cartInfo = new ReqSynchroCartInfo();
                cartInfo.setGoodsId(info.getGoodsId());
                cartInfo.setAttrStrId(info.getSpecId());
                cartInfo.setTotalNum(info.getGoodsNumber());
                list.add(cartInfo);
            }
            infoOut.setGoods(list);
            // 同步本地数据
            getPresenter().synchorShoppingCartData(infoOut);
        }else{
//            DemoConstant.isExit = false;
            synchorShoppingCartDataSuccess();
        }
    }

    @Override
    public void synchorShoppingCartDataSuccess() {
        JgUtils.loginEvent(this,"login_wx",true);
        Intent intent_home = new Intent(context, MainNavActivity.class);
        intent_home.putExtra("position", 0);
        startActivity(intent_home);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isFinishing()) {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }
    }

}
