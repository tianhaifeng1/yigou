package com.work.doctor.fruits.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.GreenDaoAssist;
import com.t.httplib.yigou.YigouConstant;
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

public class LoginActivity extends DemoMVPActivity<LoginView, LoginPresenter>
        implements LoginView, View.OnClickListener {

    private EditText mLoginPhone;
    private EditText mLoginPsw;
    private TextView mLoginSendsms;
    private Button mLogin;
    private TextView mLoginYhxy;
    private ImageView mLoginWechat;
    private TextView loginYszc;

    private GreenDaoAssist greenDaoAssist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setStateBar(false);
    }

    @Override
    protected LoginPresenter initPersenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("",true);
//        if(DemoConstant.isExit){
//            titleModule.getTitleView().setVisibility(View.GONE);
//        }else{
            titleModule.getTitleView().setVisibility(View.VISIBLE);
            titleModule.setTitleBackground(R.color.transparent);
            titleModule.setTitleBottomLineShow(false);
        titleModule.setBackImage(R.mipmap.ic_back_white);

//        }

        greenDaoAssist = new GreenDaoAssist(((DemoApplication)getApplication()).databaseAssist);



        mLoginPhone = findViewById(R.id.login_phone);
        mLoginPsw = findViewById(R.id.login_psw);
        mLoginSendsms = findViewById(R.id.login_sendsms);
        mLogin = findViewById(R.id.login);
        mLoginYhxy = findViewById(R.id.login_yhxy);
        mLoginWechat = findViewById(R.id.login_wechat);
        loginYszc = findViewById(R.id.login_yszc);

        mLoginYhxy.setOnClickListener(this);
        mLoginSendsms.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mLoginWechat.setOnClickListener(this);
        loginYszc.setOnClickListener(this);

        String phoneStr = (String) SharedPreferencesUtils.getParam(context, DemoConstant.user_phone, "");
        mLoginPhone.setText(phoneStr);
        mLoginPsw.setText("");

        getPresenter().registerWxApp(context);


    }


//    private String unionid;
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        if (intent != null) {
//            int code = intent.getIntExtra("code", -1);
//            if (code == 0) {
//                unionid = intent.getStringExtra("unionid");
//                getPresenter().loginWx(unionid);
//            }
//        }
//    }

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
                getPresenter().login(phoneNumber2, psw2, true);
//                skipActivity(MainNavActivity.class);
                break;
            case R.id.login_wechat:
                //微信登录
                getPresenter().getWxInfo(context, DemoConstant.state_login);
                break;
            case R.id.login_yszc:
                Intent intent2 = new Intent(context, DemoWebActivity.class);
                intent2.putExtra("code", 5);
                skipActivity(intent2);
                break;
        }
    }

    @Override
    public void tPostFail(int resultState) {
        super.tPostFail(resultState);
        if (resultState == 310) {
            //微信登陆失败

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
                    timer.cancel();
                    timer = null;
                    mLoginSendsms.setEnabled(true);
                    mLoginSendsms.setText("重新获取");
                }
            }
        }
    };

    private Timer timer;
    private int djs = 60;

    @Override
    public void eventDjs(String data) {
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
        DemoConstant.userApprove = infoBean.getApprove();

        SharedPreferencesUtils.setParam(context, DemoConstant.user_token, infoBean.getToken());
        SharedPreferencesUtils.setParam(context, DemoConstant.user_phone, infoBean.getPhone());
        SharedPreferencesUtils.setParam(context, DemoConstant.user_id, infoBean.getUserId());
        SharedPreferencesUtils.setParam(context, DemoConstant.user_status, infoBean.getStatus());
        SharedPreferencesUtils.setParam(context, DemoConstant.user_approve, infoBean.getApprove());

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
            if(infoBean.getStatus()!=3){
                getPresenter().synchorShoppingCartData(infoOut);
            } else {
                synchorShoppingCartDataSuccess();
            }
        }else{
//            DemoConstant.isExit = false;
            synchorShoppingCartDataSuccess();
        }

    }

    @Override
    public void synchorShoppingCartDataSuccess() {
        JgUtils.loginEvent(this,"login_phone",true);
        Intent intent_home = new Intent(context, MainNavActivity.class);
        intent_home.putExtra("position", 0);
        startActivity(intent_home);
    }

//    @Override
//    protected boolean backBefore() {
//        if(DemoConstant.isExit){
//            activityManager.exitApp(true);
//            return false;
//        }
//        return super.backBefore();
//    }
//
//    /**
//     * 双击返回键退出
//     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (DemoConstant.isExit && keyCode == KeyEvent.KEYCODE_BACK) {
//            ToastUtil3.showToast(rootView, "再次点击退出系统");
//            activityManager.exitApp(true);
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    protected void onStop() {
        super.onStop();
        //关闭资源的方式尽量使用此方式，不推荐在onDestroy方式中使用

        if (isFinishing()) {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
