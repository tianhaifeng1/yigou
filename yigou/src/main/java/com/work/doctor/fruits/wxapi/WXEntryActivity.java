package com.work.doctor.fruits.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.GreenDaoAssist;
import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.WechatCodeBean;
import com.t.httplib.yigou.bean.WechatUserInfo;
import com.t.httplib.yigou.bean.req.ReqSynchroCartInfo;
import com.t.httplib.yigou.bean.req.ReqSynchroCartInfoOut;
import com.t.httplib.yigou.bean.resp.LoginInfoBean;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.SharedPreferencesUtils;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.MainNavActivity;
import com.work.doctor.fruits.activity.RegisterActivity;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.DemoUtils;
import com.work.doctor.fruits.assist.JgUtils;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.util.ArrayList;
import java.util.List;

public class WXEntryActivity extends DemoMVPActivity<WXEntryView, WXEntryPresenter>
        implements IWXAPIEventHandler, WXEntryView {

    private GreenDaoAssist greenDaoAssist;

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wxentry);
//        initWork();
        initView();
    }

    @Override
    protected void initView() {

        api = WXAPIFactory.createWXAPI(this, DemoConstant.wx_app_id, false);
        try {
            Intent intent = getIntent();
            api.handleIntent(intent, this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                Logger.t("---------COMMAND_GETMESSAGE_FROM_WX");
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                Logger.t("---------COMMAND_SHOWMESSAGE_FROM_WX");
                break;
            default:
                break;
        }
        finish();
    }

    @Override
    public void onResp(BaseResp resp) {
        int result = 0;

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = R.string.errcode_success;
                Logger.t(getResources().getString(result) + ", type=" + resp.getType());
                if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {//身份验证
                    SendAuth.Resp authResp = (SendAuth.Resp) resp;
                    getPresenter().getAccess_token(authResp.code, authResp.state);
                }else{
                    finish();
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.errcode_cancel;
                Logger.t(getResources().getString(result) + ", type=" + resp.getType());
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.errcode_deny;
                Logger.t(getResources().getString(result) + ", type=" + resp.getType());
                finish();
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                result = R.string.errcode_unsupported;
                Logger.t(getResources().getString(result) + ", type=" + resp.getType());
                finish();
                break;
            default:
                result = R.string.errcode_unknown;
                Logger.t(getResources().getString(result) + ", type=" + resp.getType());
                finish();
                break;
        }

    }


    @Override
    protected WXEntryPresenter initPersenter() {
        return new WXEntryPresenter(this);
    }

    @Override
    public void getAccess_tokenSuccess(WechatCodeBean bean) {
        if (bean != null) {
            getPresenter().getWxUserInfo(bean.getAccess_token(), bean.getOpenid());
        } else {
//            ToastUtil2.showToast(this, "获取微信信息异常");
            finish();
        }
    }

    private WechatUserInfo wechatUserInfo;

    @Override
    public void getWxUserInfoSuccess(WechatUserInfo info) {
        if (info != null) {
            wechatUserInfo = info;
            getPresenter().loginWx(info.getOpenid());
        } else {
//            ToastUtil2.showToast(this, "获取微信信息异常");
            finish();
        }
    }

    @Override
    public void loginWxSuccess(LoginInfoBean infoBean) {

        // 判断是否绑定手机号
        if (infoBean.getPhone() == null || infoBean.getPhone().equals("")) {
            //去绑定
            Intent intent = new Intent(WXEntryActivity.this, RegisterActivity.class);
            intent.putExtra("info", wechatUserInfo);
            startActivity(intent);
            finish();
        } else {

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

            SharedPreferencesUtils.setParam(this, DemoConstant.user_token, infoBean.getToken());
            SharedPreferencesUtils.setParam(this, DemoConstant.user_phone, infoBean.getPhone());
            SharedPreferencesUtils.setParam(this, DemoConstant.user_id, infoBean.getUserId());
            SharedPreferencesUtils.setParam(this, DemoConstant.user_status, infoBean.getStatus());
            SharedPreferencesUtils.setParam(this, DemoConstant.user_approve, infoBean.getApprove());

            greenDaoAssist = new GreenDaoAssist(((DemoApplication)getApplication()).databaseAssist);
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
                }else{
                    synchorShoppingCartDataSuccess();
                }
            }else{
//            DemoConstant.isExit = false;
                synchorShoppingCartDataSuccess();
            }

        }

    }


    @Override
    public void synchorShoppingCartDataSuccess() {
        JgUtils.loginEvent(this,"login_wx",true);
        Intent intent_home = new Intent(this, MainNavActivity.class);
        intent_home.putExtra("position", 0);
        startActivity(intent_home);
    }

    @Override
    public void tPostFinish(int code) {
        super.tPostFinish(code);
        if (code == 10) {
            finish();
        }
    }

    @Override
    public void setStartActivityAnim(int enterAnim, int exitAnim) {
        super.setStartActivityAnim(0, 0);
    }

    @Override
    public void setEndActivityAnim(int enterAnim, int exitAnim) {
        super.setEndActivityAnim(0, 0);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }
}