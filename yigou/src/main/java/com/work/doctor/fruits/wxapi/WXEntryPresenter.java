package com.work.doctor.fruits.wxapi;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.WechatCodeBean;
import com.t.httplib.yigou.bean.WechatUserInfo;
import com.t.httplib.yigou.bean.req.ReqLoginWxInfo2;
import com.t.httplib.yigou.bean.req.ReqSynchroCartInfoOut;
import com.t.httplib.yigou.bean.resp.LoginInfoBean;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoPresenter;

public class WXEntryPresenter extends DemoPresenter<WXEntryView> {

    public WXEntryPresenter(@NonNull WXEntryView view) {
        super(view);
    }


    protected void getAccess_token(String code, String state) {
//		https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        String path = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?" +
                        "appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                DemoConstant.wx_app_id,
                DemoConstant.wx_app_screet,
                code);

        model.requestWxAccesstoken(path, new TObserver<WechatCodeBean>() {
            @Override
            public void onNext(WechatCodeBean bean) {
                if (isViewAttach()) {
                    if (state.equals(DemoConstant.state_login)) {
                        getView().getAccess_tokenSuccess(bean);
//                        getWxUserInfo(bean.getAccess_token(), bean.getOpenid());
                    } else if (state.equals(DemoConstant.state_er_wei_ma)) {

                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttach()) {
                    getView().tPostFinish(10);
                }
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
        model.requestWxUserInfo(path, new TObserver<WechatUserInfo>() {
            @Override
            public void onNext(WechatUserInfo bean) {
                if (isViewAttach()) {
                    getView().getWxUserInfoSuccess(bean);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttach()) {
                    getView().tPostFinish(10);
                }
            }
        });

    }

    protected void loginWx(String openid) {
//        showDialog(POST_NORMAL);

        ReqLoginWxInfo2 info = new ReqLoginWxInfo2();
        info.setOpenid(openid);

        model.requestLoginWx(info, new TObserver<DemoRespBean<LoginInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<LoginInfoBean> bean) {
                if (responseState(bean, "")) {
                    //请求登录
                    if (isViewAttach()) {
                        getView().loginWxSuccess(bean.getData());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttach()) {
                    getView().tPostFinish(10);
                }
            }
        });
    }



    protected void synchorShoppingCartData(ReqSynchroCartInfoOut info) {
        model.requestSynchorCart(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if (responseState(bean, "")) {
                    //请求登录
                    if (isViewAttach()) {
                        getView().synchorShoppingCartDataSuccess();
                    }
                }
            }
        });
    }

}
