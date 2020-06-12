package com.work.rulong.delivery.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.YigouConstant;
import com.trjx.tbase.activity.mvp.BaseMVPFragment;
import com.trjx.tbase.mvp.TPresenter;
import com.trjx.tbase.mvp.TView;
import com.trjx.tlibs.uils.SharedPreferencesUtils;

import java.math.BigDecimal;

public abstract class DemoMVPFragment<V extends TView, P extends TPresenter<V, DemoModel>>
        extends BaseMVPFragment<V, DemoModel, P> {

    public DemoMVPFragment() {
    }

    protected View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(initLayout(), null);
        initFragmentView(rootView);
        return rootView;
    }

    @Override
    protected DemoModel initModel() {
        return new DemoModel();
    }

    //初始化布局文件
    protected abstract int initLayout();

    //初始化View
    protected abstract void initFragmentView(View view);

    @Override
    public void tPostFail(int resultState) {
        super.tPostFail(resultState);
        switch (resultState) {
            case 401://重新登录:
                //登陆过期的情况下，重新登录
                if (!YigouConstant.token.equals("")) {
                    relogin();
                }
                break;
            case 202:////非法操作
                break;
            case 402://秘钥错误
                break;
            case 403://参数错误
                break;
            case 300://请求超时
                break;
            case 203://支付余额不足
                break;
//            case 310://微信登陆失败
//                loginWxFail();
//                break;
        }

    }

    protected void relogin() {
        YigouConstant.token = "";
//        DemoConstant.balance = BigDecimal.ZERO;
//        DemoConstant.userId = -1;
//        DemoConstant.userStatus = 0;
//        SharedPreferencesUtils.setParam(activity.context, DemoConstant.user_token, "");
//        SharedPreferencesUtils.setParam(activity.context, DemoConstant.user_id, -1);
//        SharedPreferencesUtils.setParam(activity.context, DemoConstant.user_status, 0);
//        activity.skipActivity(LoginActivity.class);
    }

}
