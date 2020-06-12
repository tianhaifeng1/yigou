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
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class WXEntry2Activity extends DemoMVPActivity<WXEntryView, WXEntryPresenter>
        implements IWXAPIEventHandler, WXEntryView {
//    private static String TAG = "MicroMsg.WXEntryActivity";

private GreenDaoAssist greenDaoAssist;

    private IWXAPI api;
//    private MyHandler handler;

//	private static class MyHandler extends Handler {
//		private final WeakReference<WXEntryActivity> wxEntryActivityWeakReference;
//
//		public MyHandler(WXEntryActivity wxEntryActivity){
//			wxEntryActivityWeakReference = new WeakReference<WXEntryActivity>(wxEntryActivity);
//		}
//
//		@Override
//		public void handleMessage(Message msg) {
//			int tag = msg.what;
//			switch (tag) {
//				case NetworkUtil.GET_TOKEN: {
//					Bundle data = msg.getData();
//					JSONObject json = null;
//					try {
//						json = new JSONObject(data.getString("result"));
//						String openId, accessToken, refreshToken, scope;
//						openId = json.getString("openid");
//						accessToken = json.getString("access_token");
//						refreshToken = json.getString("refresh_token");
//						scope = json.getString("scope");
//						Intent intent = new Intent(wxEntryActivityWeakReference.get(), SendToWXActivity.class);
//						intent.putExtra("openId", openId);
//						intent.putExtra("accessToken", accessToken);
//						intent.putExtra("refreshToken", refreshToken);
//						intent.putExtra("scope", scope);
//						wxEntryActivityWeakReference.get().startActivity(intent);
//					} catch (JSONException e) {
//						Log.e(TAG, e.getMessage());
//					}
//				}
//			}
//		}
//	}

//    private DemoModel demoModel;

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
//			goToGetMsg();
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                Logger.t("---------COMMAND_SHOWMESSAGE_FROM_WX");
//			goToShowMsg((ShowMessageFromWX.Req) req);
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
//		Toast.makeText(this, getResources().getString(result) + ", type=" + resp.getType(), Toast.LENGTH_SHORT).show();
//
//
//		if (resp.getType() == ConstantsAPI.COMMAND_SUBSCRIBE_MESSAGE) {
//			SubscribeMessage.Resp subscribeMsgResp = (SubscribeMessage.Resp) resp;
//			String text = String.format("openid=%s\ntemplate_id=%s\nscene=%d\naction=%s\nreserved=%s",
//					subscribeMsgResp.openId, subscribeMsgResp.templateID, subscribeMsgResp.scene, subscribeMsgResp.action, subscribeMsgResp.reserved);
//
//			Toast.makeText(this, text, Toast.LENGTH_LONG).show();
//		}
//
//        if (resp.getType() == ConstantsAPI.COMMAND_LAUNCH_WX_MINIPROGRAM) {
//            WXLaunchMiniProgram.Resp launchMiniProgramResp = (WXLaunchMiniProgram.Resp) resp;
//            String text = String.format("openid=%s\nextMsg=%s\nerrStr=%s",
//                    launchMiniProgramResp.openId, launchMiniProgramResp.extMsg,launchMiniProgramResp.errStr);
//
//            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
//        }
//
//        if (resp.getType() == ConstantsAPI.COMMAND_OPEN_BUSINESS_VIEW) {
//            WXOpenBusinessView.Resp launchMiniProgramResp = (WXOpenBusinessView.Resp) resp;
//            String text = String.format("openid=%s\nextMsg=%s\nerrStr=%s\nbusinessType=%s",
//                    launchMiniProgramResp.openId, launchMiniProgramResp.extMsg,launchMiniProgramResp.errStr,launchMiniProgramResp.businessType);
//
//            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
//        }
//
//        if (resp.getType() == ConstantsAPI.COMMAND_OPEN_BUSINESS_WEBVIEW) {
//            WXOpenBusinessWebview.Resp response = (WXOpenBusinessWebview.Resp) resp;
//            String text = String.format("businessType=%d\nresultInfo=%s\nret=%d",response.businessType,response.resultInfo,response.errCode);
//
//            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
//        }
//
//		if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
//			SendAuth.Resp authResp = (SendAuth.Resp)resp;
////			final String code = authResp.code;
////			NetworkUtil.sendWxAPI(handler, String.format("https://api.weixin.qq.com/sns/oauth2/access_token?" +
////							"appid=%s&secret=%s&code=%s&grant_type=authorization_code", "wxd930ea5d5a258f4f",
////					"1d6d1d57a3dd063b36d917bc0b44d964", code), NetworkUtil.GET_TOKEN);
//			String path = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?" +
//							"appid=%s&secret=%s&code=%s&grant_type=authorization_code", "wxd930ea5d5a258f4f",
//					"1d6d1d57a3dd063b36d917bc0b44d964",);
//			Call<WechatCodeBean> call = demoModel.getWxLoginData(path);
//			call.enqueue(new Callback<WechatCodeBean>() {
//				@Override
//				public void onResponse(Call<WechatCodeBean> call, Response<WechatCodeBean> response) {
//
//
//				}
//
//				@Override
//				public void onFailure(Call<WechatCodeBean> call, Throwable t) {
//
//					finish();
//				}
//
//			});
//
//		}


//		int errCode = resp.errCode;
//		if(errCode == BaseResp.ErrCode.ERR_OK){//发送成功
//
//			if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {//身份验证
//
//			}
//
//
//		}else{//其他情况
//
//			finish();
//		}


     }


//	private void goToGetMsg() {
//		Intent intent = new Intent(this, GetFromWXActivity.class);
//		intent.putExtras(getIntent());
//		startActivity(intent);
//		finish();
//	}

    private void goToShowMsg(ShowMessageFromWX.Req showReq) {
        WXMediaMessage wxMsg = showReq.message;
        WXAppExtendObject obj = (WXAppExtendObject) wxMsg.mediaObject;

        StringBuffer msg = new StringBuffer();
        msg.append("description: ");
        msg.append(wxMsg.description);
        msg.append("\n");
        msg.append("extInfo: ");
        msg.append(obj.extInfo);
        msg.append("\n");
        msg.append("filePath: ");
        msg.append(obj.filePath);

//		Intent intent = new Intent(this, ShowFromWXActivity.class);
//		intent.putExtra(Constants.ShowMsgActivity.STitle, wxMsg.title);
//		intent.putExtra(Constants.ShowMsgActivity.SMessage, msg.toString());
//		intent.putExtra(Constants.ShowMsgActivity.BAThumbData, wxMsg.thumbData);
//		startActivity(intent);
//		finish();
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
            Intent intent = new Intent(WXEntry2Activity.this, RegisterActivity.class);
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
                getPresenter().synchorShoppingCartData(infoOut);
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