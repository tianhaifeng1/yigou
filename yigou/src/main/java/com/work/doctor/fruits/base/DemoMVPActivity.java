package com.work.doctor.fruits.base;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.YigouConstant;
import com.trjx.tbase.activity.mvp.BaseMVPActivity;
import com.trjx.tbase.module.titlemodule.TitleModule;
import com.trjx.tbase.mvp.TPresenter;
import com.trjx.tbase.mvp.TView;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.SharedPreferencesUtils;
import com.work.doctor.fruits.activity.LoginActivity;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.dialog.RemindDialog;

import java.math.BigDecimal;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public abstract class DemoMVPActivity<V extends TView, P extends TPresenter<V, DemoModel>>
        extends BaseMVPActivity<V, DemoModel, P> {

    public TitleModule titleModule;

//    protected boolean isRefresh = true;
//    protected TRecyclerModule recyclerModule;

    @Override
    protected void initView() {
        titleModule = new TitleModule(context, rootView);
        titleModule.setListenter(this);
    }

    @Override
    protected DemoModel initModel() {
        return new DemoModel();
    }

//    //重新登录

//    public static final int POST_LOGIN_EXPRIES = 401;
//    //请求超时
//    public static final int POST_TIMEOUT = 300;
//    //秘钥错误
//    public static final int POST_SIGN_ERROR = 402;
//    //参数错误
//    public static final int POST_PARAM_ERROR = 403;
//    //支付余额不足
//    public static final int POST_REFRESH = 203;
//    //非法操作
//    public static final int POST_ERROR = 202;
//
//    //微信登陆失败
//    public static final int POST_LOGIN_WX = 310;

    @Override
    public void tPostFail(int resultState) {
        super.tPostFail(resultState);
        switch (resultState) {
            case 401://重新登录
                //登陆过期的情况下，重新登录
                if (!YigouConstant.token.equals("")) {
                    relogin();
                }
                break;
            case 202:////非法操作
                break;
            case 204:////弹框提醒
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

//        if (resultState == RespState.POST_LOGIN_EXPRIES) {
////            跳转到登录页面重新登录
//            skipActivity(LoginActivity.class);
//
//        } else if (resultState == RespState.POST_ERROR) {
////          请求异常
//        } else if (resultState == RespState.POST_SIGN_ERROR) {
//            Logger.t("秘钥错误");
//        } else if (resultState == RespState.POST_PARAM_ERROR) {
//            Logger.t("参数错误");
//        } else if (resultState == RespState.POST_TIMEOUT) {
//            Logger.t("请求超时");
//        } else if (resultState == RespState.POST_REFRESH) {
//            Logger.t("支付余额不足");
//        }
    }

    protected void relogin() {
        YigouConstant.token = "";
        DemoConstant.balance = BigDecimal.ZERO;
        DemoConstant.userId = -1;
        DemoConstant.userStatus = 0;
        SharedPreferencesUtils.setParam(context, DemoConstant.user_token, "");
        SharedPreferencesUtils.setParam(context, DemoConstant.user_id, -1);
        SharedPreferencesUtils.setParam(context, DemoConstant.user_status, 0);
        SharedPreferencesUtils.setParam(context, DemoConstant.user_approve, 0);
        skipActivity(LoginActivity.class);
    }

    /**
     * 打电话
     */
    public void callPhone(String phoneNum) {
        //我们需要告诉系统，我们的动作：我要打电话
        //创建意图对象
        Intent intent = new Intent();
        //  直接打电话的动作
        //intent.setAction(Intent.ACTION_CALL);
        //  跳转到拨号界面
        intent.setAction(Intent.ACTION_DIAL);
        //设置打给谁
        intent.setData(Uri.parse("tel:" + phoneNum));//这个tel：必须要加上，表示我要打电话。否则不会有打电话功能，由于在打电话清单文件里设置了这个“协议”
        //把动作告诉系统,启动系统打电话功能。
        startActivity(intent);

    }


    public void showPremission_all() {
        DemoMVPActivityPermissionsDispatcher.getPremission_allWithPermissionCheck(this);
    }

    public void showPremission_call_phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        DemoMVPActivityPermissionsDispatcher.getPremission_call_phoneWithPermissionCheck(this);
    }

    public void showPremission_camera() {
        DemoMVPActivityPermissionsDispatcher.getPremission_cameraWithPermissionCheck(this);
    }

    public void showPremission_location() {
        DemoMVPActivityPermissionsDispatcher.getPremission_locationWithPermissionCheck(this);
    }

    @NeedsPermission({
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    })
    void getPremission_all() {
        Logger.t("-------- all ");
    }


    private String phoneNumber = "0891-6373803";

    @NeedsPermission({Manifest.permission.CALL_PHONE})
    void getPremission_call_phone() {
//        AlertDialog alertDialog = new AlertDialog.Builder(context)
//                .setTitle("是否联系客服？")
//                .setMessage("客服电话：" + phoneNumber)
//                .setPositiveButton("拨打", (dialog, which) -> {
//                    callPhone(phoneNumber);
//                })
//                .setNegativeButton("取消", null)
//                .create();
//        alertDialog.show();

        RemindDialog remindDialog = new RemindDialog.Builder(context)
                .setTitle("是否联系客服？")
                .setMessage("客服电话："+phoneNumber)
                .setAffirmText("拨打")
                .setCancleText("取消")
                .setCancelable(true)
                .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                    @Override
                    public void onRemindClickAffirm(View view) {
                        callPhone(phoneNumber);
                    }

                    @Override
                    public void onRemindClickCancle(View view) {

                    }
                }).create();
        remindDialog.show(getSupportFragmentManager(),"dialog_remind_callphone");


    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void getPremission_camera() {

    }

    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    void getPremission_location() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        DemoMVPActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

}
