package com.work.doctor.fruits.activity;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.WechatUserInfo;
import com.t.httplib.yigou.bean.req.ReqLoginInfo;
import com.t.httplib.yigou.bean.req.ReqLoginWxInfo2;
import com.t.httplib.yigou.bean.req.ReqSynchroCartInfoOut;
import com.t.httplib.yigou.bean.req.ReqYzmInfo;
import com.t.httplib.yigou.bean.resp.LoginInfoBean;
import com.trjx.tlibs.uils.TUtils;
import com.work.doctor.fruits.base.DemoPresenter;

import static com.work.doctor.fruits.assist.DemoConstant.POST_NORMAL;

public class LoginPresenter extends DemoPresenter<LoginView> {

    public LoginPresenter(@NonNull LoginView view) {
        super(view);
    }


    protected void getYzm(String phoneNumber) {

        if (phoneNumber == null || phoneNumber.equals("")) {
            onRemind("手机号不能为空");
        } else if (phoneNumber.length() != 11 || !TUtils.isMobileNO(phoneNumber)) {
            onRemind("请输入正确的手机号");
        } else {

            ReqYzmInfo info = new ReqYzmInfo();
            info.setPhone(phoneNumber);
            model.requestYzm(info, new TObserver<DemoRespBean>() {
                @Override
                public void onNext(DemoRespBean bean) {
                    if (responseState(bean)) {
                        //请求获取验证码
                        //倒计时
                        if (isViewAttach()) {
                            getView().eventDjs(bean.getData()+"");
                        }
                    }
                }

            });
        }
    }

    protected void login(String phoneNumber, String psw, boolean isAgree) {

        if (phoneNumber == null || phoneNumber.equals("")) {
            onRemind("手机号不能为空");
        } else if (phoneNumber.length() != 11 || !TUtils.isMobileNO(phoneNumber)) {
            onRemind("请输入正确的手机号");
        } else if (psw == null || psw.equals("")) {
            onRemind("验证码不能为空");
        } else if (!isAgree) {
            onRemind("请先阅读并同意用户协议");
        } else {
            showDialog(POST_NORMAL);

            ReqLoginInfo info = new ReqLoginInfo();
            info.setPhone(phoneNumber);
            info.setValcode(psw);
            model.requestLogin(info, new TObserver<DemoRespBean<LoginInfoBean>>() {
                @Override
                public void onNext(DemoRespBean<LoginInfoBean> bean) {
                    if (responseState(bean, "")) {
                        //请求登录
                        if (isViewAttach()) {
                            getView().loginSuccess(bean.getData());
                        }
                    }
                }
            });

        }
    }
    protected void loginBindWx(String phoneNumber, String psw, boolean isAgree, WechatUserInfo userInfo) {

        if (phoneNumber == null || phoneNumber.equals("")) {
            onRemind("手机号不能为空");
        } else if (phoneNumber.length() != 11 || !TUtils.isMobileNO(phoneNumber)) {
            onRemind("请输入正确的手机号");
        } else if (psw == null || psw.equals("")) {
            onRemind("验证码不能为空");
        } else if (!isAgree) {
            onRemind("请先阅读并同意用户协议");
        } else {
            showDialog(POST_NORMAL);

            ReqLoginInfo info = new ReqLoginInfo();
            info.setPhone(phoneNumber);
            info.setUnionid(userInfo.getUnionid());
            info.setOpenid(userInfo.getOpenid());
            info.setValcode(psw);
            info.setAvatarUrl(userInfo.getHeadimgurl());
            info.setNickName(userInfo.getNickname());

            model.requestLogin(info, new TObserver<DemoRespBean<LoginInfoBean>>() {
                @Override
                public void onNext(DemoRespBean<LoginInfoBean> bean) {
                    if (responseState(bean, "")) {
                        //请求登录
                        if (isViewAttach()) {
                            getView().loginSuccess(bean.getData());
                        }
                    }
                }
            });

        }
    }

    protected void loginWx(String openid) {
        showDialog(POST_NORMAL);

        ReqLoginWxInfo2 info = new ReqLoginWxInfo2();
        info.setOpenid(openid);
        model.requestLoginWx(info, new TObserver<DemoRespBean<LoginInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<LoginInfoBean> bean) {
                if (responseState(bean)) {
                    //请求登录
                    if (isViewAttach()) {
                        getView().loginSuccess(bean.getData());
                    }
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
