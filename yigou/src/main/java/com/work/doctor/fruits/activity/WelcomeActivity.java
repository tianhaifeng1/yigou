package com.work.doctor.fruits.activity;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.t.httplib.yigou.YigouConstant;
import com.trjx.tbase.mvp.TPresenter;
import com.trjx.tlibs.uils.SharedPreferencesUtils;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.RemindDialog;

public class WelcomeActivity extends DemoMVPActivity {

    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void initView() {

        YigouConstant.token = (String) SharedPreferencesUtils.getParam(context, DemoConstant.user_token, "");
        DemoConstant.userId = (int) SharedPreferencesUtils.getParam(context, DemoConstant.user_id, -1);
        DemoConstant.userStatus = (int) SharedPreferencesUtils.getParam(context, DemoConstant.user_status, 0);
        DemoConstant.userApprove = (int) SharedPreferencesUtils.getParam(context, DemoConstant.user_approve, 0);


        handler = new Handler();
        showPremission_all();


    }

    @Override
    protected TPresenter initPersenter() {
        return null;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        boolean isGspOPen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(!isGspOPen){
            //gps未打开
            RemindDialog remindDialog = new RemindDialog.Builder(context)
                    .setTitle("提示")
                    .setMessage("定位服务未开启，将影响软件的正常使用，是否去打开？")
                    .setMessageGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL)
                    .setCancleText("取消")
                    .setAffirmText("打开定位")
                    .setCancelable(false)
                    .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                        @Override
                        public void onRemindClickAffirm(View view) {
                            //打开GPS
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, 100);
                        }

                        @Override
                        public void onRemindClickCancle(View view) {
                            skip();
                        }
                    })
                    .create();
            remindDialog.show(getSupportFragmentManager(), "dialog_gps");
        }else{
            skip();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            skip();
        }
    }

//    private void panduanNet(){
//        //判断网络是否可用
//        if(NetWorkManage.getInstance().isNetworkConnected(context)) {
//            skip();
//        }else{
//            //网络未打开
//            RemindDialog remindDialog = new RemindDialog.Builder(context)
//                    .setTitle("提示")
//                    .setMessage("网络未打开？")
//                    .setCancleText("取消")
//                    .setAffirmText("打开网络")
//                    .setCancelable(false)
//                    .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
//                        @Override
//                        public void onRemindClickAffirm(View view) {
//                        }
//
//                        @Override
//                        public void onRemindClickCancle(View view) {
//
//                        }
//                    })
//                    .create();
//
//            remindDialog.show(getSupportFragmentManager(), "dialog_gps");
//        }
//
//    }


    private void skip(){
        handler.postDelayed(() -> {
            boolean isFirst = (Boolean) SharedPreferencesUtils.getParam(context, DemoConstant.user_first, true);
            if (isFirst) {
                skipActivity(GuideActivity.class);
            } else {
                skipActivity(MainNavActivity.class);
            }
        }, 500);
    }
}
