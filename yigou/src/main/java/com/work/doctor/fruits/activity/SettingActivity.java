package com.work.doctor.fruits.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.t.databaselib.GreenDaoAssist;
import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.DemoRespBean;
import com.trjx.tbase.http.HttpBase;
import com.trjx.tbase.manage.DataCleanManager;
import com.trjx.tbase.mvp.TView;
import com.trjx.tlibs.uils.SharedPreferencesUtils;
import com.trjx.tlibs.uils.SnackbarUtil;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.DemoUtils;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.base.DemoPresenter;
import com.work.doctor.fruits.base.DemoWebActivity;
import com.work.doctor.fruits.dialog.RemindDialog;

import java.math.BigDecimal;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SettingActivity extends DemoMVPActivity<TView, DemoPresenter<TView>>
        implements View.OnClickListener {

    private TextView mSettingClearcacheText;
    private RelativeLayout mSettingClearcache;
    private TextView mSettingVersionText;
    private RelativeLayout mSettingVersion;
    private RelativeLayout mSettingAbout;
    private TextView mSettingExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected DemoPresenter initPersenter() {
        return new DemoPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();

        titleModule.initTitle("设置", true);

        mSettingClearcacheText = findViewById(R.id.setting_clearcache_text);
        mSettingClearcache = findViewById(R.id.setting_clearcache);
        mSettingVersionText = findViewById(R.id.setting_version_text);
        mSettingVersion = findViewById(R.id.setting_version);
        mSettingAbout = findViewById(R.id.setting_about);
        mSettingExit = findViewById(R.id.setting_exit);

        mSettingClearcache.setOnClickListener(this);
        mSettingVersion.setOnClickListener(this);
        mSettingAbout.setOnClickListener(this);
        mSettingExit.setOnClickListener(this);

        try {
            mSettingClearcacheText.setText(DataCleanManager.getTotalCacheSize(context));
        } catch (Exception e) {
            e.printStackTrace();
        }


        String version = "";
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } finally {
            mSettingVersionText.setText("v" + version);
        }
        String sizeCache = "0 B";
        try {
            sizeCache = DataCleanManager.getTotalCacheSize(context);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mSettingClearcacheText.setText(sizeCache);
        }


    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.setting_clearcache:
                new RemindDialog.Builder(context)
                        .setMessage("确定清空缓存？")
                        .setCancleText("取消")
                        .setAffirmText("清空")
                        .setCancelable(true)
                        .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                            @Override
                            public void onRemindClickAffirm(View view) {
                                try {
                                    DataCleanManager.clearAllCache(context);
                                    mSettingClearcacheText.setText(DataCleanManager.getTotalCacheSize(context));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onRemindClickCancle(View view) {

                            }
                        }).create().show(getSupportFragmentManager(), "dialog_clearcache");
                break;
            case R.id.setting_version:
                break;
            case R.id.setting_about:
                Intent intent = new Intent(context, DemoWebActivity.class);
                intent.putExtra("code", 2);
                skipActivity(intent);
                break;
            case R.id.setting_exit:

                new RemindDialog.Builder(context)
                        .setCancelable(false)
                        .setAffirmText("确认")
                        .setCancleText("取消")
                        .setMessage("确认退出登录？")
                        .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                            @Override
                            public void onRemindClickAffirm(View view) {
                                exitLogin();
                            }

                            @Override
                            public void onRemindClickCancle(View view) {

                            }
                        }).create().show(getSupportFragmentManager(),"dialog_remind_exit_login");
//                new TSelectBottomDialog.Builder(context)
//                        .setText("退出系统")
//                        .setText("重新登录")
//                        .setCancelable(false)
//                        .setOnTdSelect(position -> {
//                            if (position == 0) {
//                                activityManager.exit();
//                            } else if (position == 1) {
////                              数据初始化
//                                DemoConstant.isExit = true;
//                                DemoConstant.token = "";
//                                DemoConstant.balance = BigDecimal.ZERO;
//                                DemoConstant.userId = -1;
//
//                                new GreenDaoAssist(this).clearDatabase();
//
//                                SharedPreferencesUtils.setParam(context, DemoConstant.user_token, "");
//                                SharedPreferencesUtils.setParam(context, DemoConstant.user_id, -1);
//                                skipActivity(LoginActivity.class);
//                            }
//                        })
//                        .create()
//                        .show(getSupportFragmentManager(), "sfm_exit");
//
//                break;
        }
    }

    private void exitLogin(){

        new DemoModel().requestSignout(new Observer<DemoRespBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DemoRespBean respBean) {
                try {
                    if(respBean.getResultState() == HttpBase.POST_SUCCESS){
                        //                              数据初始化
//                                DemoConstant.isExit = true;
                        if (DemoUtils.changePfUser(0)) {
                            DemoConstant.isChangeShop = true;
                            DemoConstant.refershOne = true;
                            DemoConstant.refershTwo = true;
                            DemoConstant.refershThree = true;

                            DemoConstant.isRefershShopInfo = true;
                        }

                        YigouConstant.token = "";
                        DemoConstant.balance = BigDecimal.ZERO;
                        DemoConstant.userId = -1;
                        DemoConstant.userStatus = 0;
                        DemoConstant.addressInfoBean = null;
                        SharedPreferencesUtils.setParam(context, DemoConstant.user_token, "");
                        SharedPreferencesUtils.setParam(context, DemoConstant.user_id, -1);
                        SharedPreferencesUtils.setParam(context, DemoConstant.user_status, 0);
                        SharedPreferencesUtils.setParam(context, DemoConstant.user_approve, 0);

                        //清除数据库数据
                        new GreenDaoAssist(((DemoApplication)getApplication()).databaseAssist).clearDatabase();
                        //跳转
                        Intent intent_home = new Intent(context, MainNavActivity.class);
                        intent_home.putExtra("position", 3);
                        startActivity(intent_home);
                    }else{
                        SnackbarUtil.show(rootView,respBean.getRemindMessage());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                SnackbarUtil.show(rootView,"服务器异常");
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
