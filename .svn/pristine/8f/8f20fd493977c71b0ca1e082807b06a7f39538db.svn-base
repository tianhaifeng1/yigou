package com.work.doctor.fruits.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.GreenDaoAssist;
import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.WechatCodeBean;
import com.t.httplib.yigou.bean.WechatUserInfo;
import com.t.httplib.yigou.bean.req.ReqLoginWxInfo2;
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
import com.trjx.tlibs.uils.ToastUtil2;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.MainNavActivity;
import com.work.doctor.fruits.activity.RegisterActivity;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.DemoUtils;
import com.work.doctor.fruits.assist.JgUtils;
import com.work.doctor.fruits.base.DemoApplication;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.trjx.tbase.http.HttpBase.POST_SUCCESS;

@Deprecated
public class WXEntry3Activity extends Activity implements IWXAPIEventHandler {

    private GreenDaoAssist greenDaoAssist;

    private IWXAPI api;

    private DemoModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wxentry);
        initView();
    }

    protected void initView() {
        model = new DemoModel();

        api = WXAPIFactory.createWXAPI(this, DemoConstant.wx_app_id, false);
//		handler = new MyHandler(this);
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
//				https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
//                    getAccess_token(authResp.code,authResp.state);
                    getAccess_token(authResp.code, authResp.state);
                } else {
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

    private void getAccess_tokenSuccess(WechatCodeBean bean) {
        if (bean != null) {
            getWxUserInfo(bean.getAccess_token(), bean.getOpenid());
        } else {
//            ToastUtil2.showToast(this, "获取微信信息异常");
            finish();
        }
    }

    private WechatUserInfo wechatUserInfo;

    private void getWxUserInfoSuccess(WechatUserInfo info) {
        if (info != null) {
            wechatUserInfo = info;
            loginWx(info.getOpenid());
        } else {
//            ToastUtil2.showToast(this, "获取微信信息异常");
            finish();
        }
    }

    private void loginWxSuccess(LoginInfoBean infoBean) {

        // 判断是否绑定手机号
        if (infoBean.getPhone() == null || infoBean.getPhone().equals("")) {
            //去绑定
            Intent intent = new Intent(WXEntry3Activity.this, RegisterActivity.class);
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

            SharedPreferencesUtils.setParam(this, DemoConstant.user_token, infoBean.getToken());
            SharedPreferencesUtils.setParam(this, DemoConstant.user_phone, infoBean.getPhone());
            SharedPreferencesUtils.setParam(this, DemoConstant.user_id, infoBean.getUserId());
            SharedPreferencesUtils.setParam(this, DemoConstant.user_status, infoBean.getStatus());

            greenDaoAssist = new GreenDaoAssist(((DemoApplication) getApplication()).databaseAssist);
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
                synchorShoppingCartData(infoOut);
            } else {
//            DemoConstant.isExit = false;
                synchorShoppingCartDataSuccess();
            }

        }

    }

    private void synchorShoppingCartDataSuccess() {
        JgUtils.loginEvent(this, "login_wx", true);
        Intent intent_home = new Intent(this, MainNavActivity.class);
        intent_home.putExtra("position", 0);
        startActivity(intent_home);
    }

    private void tPostFinish(int code) {
        if (code == 10) {
            finish();
        }
    }


    // TODO: 2019/8/7 0007

    /**
     * 从输入流中获取数据
     *
     * @param inStream
     * @return
     */
    private byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }


    private void getAccess_token(String code, String state) {
//		https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        String path = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?" +
                        "appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                DemoConstant.wx_app_id,
                DemoConstant.wx_app_screet,
                code);
        model.requestWxAccesstoken(path, new Observer<WechatCodeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WechatCodeBean wechatCodeBean) {
                if (state.equals(DemoConstant.state_login)) {
                    getAccess_tokenSuccess(wechatCodeBean);
                } else if (state.equals(DemoConstant.state_er_wei_ma)) {

                }
            }

            @Override
            public void onError(Throwable e) {
                tPostFinish(10);
            }

            @Override
            public void onComplete() {

            }
        });

    }

    /**
     * 获取微信用户信息
     *
     * @param access_token
     * @param openId
     */
    protected void getWxUserInfo(String access_token, String openId) {
//        http请求方式:
//        GET
//        https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID

        String path = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s",
                access_token,
                openId);
        model.requestWxUserInfo(path, new Observer<WechatUserInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WechatUserInfo wechatUserInfo) {
                        getWxUserInfoSuccess(wechatUserInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        tPostFinish(10);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    protected void loginWx(String openid) {

        ReqLoginWxInfo2 info = new ReqLoginWxInfo2();
        info.setOpenid(openid);
        model.requestLoginWx(info, new Observer<DemoRespBean<LoginInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DemoRespBean<LoginInfoBean> loginInfoBeanDemoRespBean) {
                        loginWxSuccess(loginInfoBeanDemoRespBean.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        tPostFinish(10);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    protected void synchorShoppingCartData(ReqSynchroCartInfoOut info) {
        model.requestSynchorCart(info, new Observer<DemoRespBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DemoRespBean demoRespBean) {
                        if (demoRespBean != null) {
                            int state = demoRespBean.getResultState();
                            if (state == POST_SUCCESS) {//成功返回数据
                                synchorShoppingCartDataSuccess();
                            } else {
                                ToastUtil2.showToast(WXEntry3Activity.this,demoRespBean.getRemindMessage());
                            }
                        } else {
                            Logger.e("tong_error", "返回的数据异常");
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(0, 0);
    }

}