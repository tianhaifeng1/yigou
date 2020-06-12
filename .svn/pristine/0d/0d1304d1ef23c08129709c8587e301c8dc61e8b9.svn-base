package com.work.doctor.fruits.activity;

import android.view.View;

import com.trjx.tbase.activity.GuidePageActivity;
import com.trjx.tlibs.uils.SharedPreferencesUtils;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.dialog.RemindDialog;

import java.util.List;

public class GuideActivity extends GuidePageActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_guide);
    }

    @Override
    protected void initView() {
        super.initView();
        SharedPreferencesUtils.setParam(context, DemoConstant.user_first, false);
    }

    @Override
    public boolean setPageData(List<Object> imgList) {
        imgList.add(R.mipmap.guide_page1);
        imgList.add(R.mipmap.guide_page2);
        imgList.add(R.mipmap.guide_page3);
        imgList.add(R.mipmap.guide_page4);
        return true;
    }

    @Override
    protected void onClickSkipView() {
        skipActivity(MainNavActivity.class);
    }

    @Override
    protected boolean backBefore() {
        RemindDialog remindDialog = new RemindDialog.Builder(context)
                .setCancleText("取消")
                .setAffirmText("退出")
                .setMessage("确认要退出应用？")
                .setCancelable(false)
                .setTitle("提示")
                .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                    @Override
                    public void onRemindClickAffirm(View view) {
                        activityManager.exit();
                    }

                    @Override
                    public void onRemindClickCancle(View view) {

                    }
                })
                .create();
        remindDialog.show(getSupportFragmentManager(),"dialog_remind_exit");

        return false;
    }

}
