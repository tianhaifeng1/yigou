package com.work.doctor.fruits.activity;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqLoginInfo;
import com.t.httplib.yigou.bean.req.ReqYzmInfo;
import com.t.httplib.yigou.bean.resp.BalanceStatInfoBean;
import com.t.httplib.yigou.bean.resp.LoginInfoBean;
import com.trjx.tlibs.uils.TUtils;
import com.work.doctor.fruits.base.DemoPresenter;

import static com.work.doctor.fruits.assist.DemoConstant.POST_NORMAL;

public class SharePagePresenter extends DemoPresenter<SharePageView> {
    public SharePagePresenter(@NonNull SharePageView view) {
        super(view);
    }

    protected void getDataInfo(){
        model.requestUserBalanceStat(new TObserver<DemoRespBean<BalanceStatInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<BalanceStatInfoBean> bean) {
                if (responseState(bean)) {
                    if(isViewAttach()){
                        getView().getDataInfoSuccess(bean.getData());
                    }
                }
            }
        });
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

    protected void register(String phoneNumber, String psw) {

        if (phoneNumber == null || phoneNumber.equals("")) {
            onRemind("手机号不能为空");
        } else if (phoneNumber.length() != 11 || !TUtils.isMobileNO(phoneNumber)) {
            onRemind("请输入正确的手机号");
        } else if (psw == null || psw.equals("")) {
            onRemind("验证码不能为空");
        } else {
            showDialog(POST_NORMAL);

            ReqLoginInfo info = new ReqLoginInfo();
            info.setPhone(phoneNumber);
            info.setValcode(psw);
            model.requestRegister(info, new TObserver<DemoRespBean<LoginInfoBean>>() {
                @Override
                public void onNext(DemoRespBean<LoginInfoBean> bean) {
                    if (responseState(bean, "")) {
                        //请求登录
                        if (isViewAttach()) {
                            getView().registerSuccess();
                        }
                    }
                }
            });

        }
    }

}
