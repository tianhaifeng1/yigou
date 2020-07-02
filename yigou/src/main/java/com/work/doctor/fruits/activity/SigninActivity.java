package com.work.doctor.fruits.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.resp.SigninInfoBean;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.SigninAdapter;
import com.work.doctor.fruits.assist.DemoUtils;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.CommonPopupWindow;

import java.util.ArrayList;

public class SigninActivity extends DemoMVPActivity<SigninView, SigninPresenter>
        implements SigninView, View.OnClickListener, CommonPopupWindow.ViewInterface {

    private RecyclerView mSigninRecyclerview;
    private SigninAdapter mAdapterSignin;
    private ImageView mSigninImage;
    private Button mSigninBut;
    private TextView mSigninParam;
    private TextView mSigninTxtRule;
    private TextView mSigninRecord;
    private LinearLayout mSigninRecyShow;
    private LinearLayout mSigninRompleteShow;
    private TextView mSigninXq;

    private CommonPopupWindow popupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }


    @Override
    protected void initView() {
        super.initView();
        if (YigouConstant.token.equals("")) {
            relogin();
            this.finish();
        }


        titleModule.initTitle("每日签到", true);
        mSigninRecyclerview = findViewById(R.id.signin_recyclerview);
        mSigninImage = findViewById(R.id.signin_image);
        mSigninBut = findViewById(R.id.signin_but);
        mSigninParam = findViewById(R.id.signin_param);
        mSigninTxtRule = findViewById(R.id.signin_txt_rule);
        mSigninRecord = findViewById(R.id.signin_record);
        mSigninRecyShow = findViewById(R.id.signin_recy_show);
        mSigninRompleteShow = findViewById(R.id.signin_complete_show);
        mSigninXq = findViewById(R.id.signin_xq);

        mSigninBut.setOnClickListener(this);
        mSigninTxtRule.setOnClickListener(this);
        mSigninRecord.setOnClickListener(this);

        GlideUtile.bindImageViewC(this,R.mipmap.signin_seven,10,mSigninImage);

        initAdapter();
        getPresenter().getSigninData();

    }


    private void initAdapter() {
        mSigninRecyclerview.setLayoutManager(new GridLayoutManager(context, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;//禁止垂直滚动
            }
        });
        mAdapterSignin = new SigninAdapter(null);
        mSigninRecyclerview.setAdapter(mAdapterSignin);
    }

    @Override
    protected SigninPresenter initPersenter() {
        return new SigninPresenter(this);
    }

    @Override
    public void getSigninDataSuccess(SigninInfoBean bean) {
        if(bean.getKey().equals("1")){
            mSigninBut.setEnabled(false);
        }
        ArrayList<SigninInfoBean.Model> list = new ArrayList<>();
        for (int i = 0;i < 6;i++){
            list.add(bean.getModel().get(i));
        }

        mSigninParam.setText("已连续签到"+bean.getParam()+"天");
        if(bean.getParam().equals("7")){
            mSigninXq.setText("连续签到获得更多免费商品");
            mSigninRecyShow.setVisibility(View.GONE);
            mSigninRompleteShow.setVisibility(View.VISIBLE);
        }

        mAdapterSignin.setNewData(list);
    }

    @Override
    public void getSigninOperateSuccess(int operate) {
        if(operate==200){
            getPresenter().getSigninData();
            getSuccessPop();
            mSigninBut.setEnabled(false);
        }else if(operate==202){
            mSigninBut.setEnabled(false);
        }
    }

    private void getSuccessPop() {
        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popw_siginin_yes, null);

        //测量View的宽高
        DemoUtils.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popw_siginin_yes)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                // .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗   此方法无用 必须重写
                .setAnimationStyle(R.style.MyPopupWindow_anim_style)
                .setViewOnclickListener(this)
                .create();
        // 产生背景变暗效果，设置透明度  必须重写 否则背景无法变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.2f;
        //之前不写这一句也是可以实现的，这次突然没效果了。网搜之后加上了这一句就好了。据说是因为popUpWindow没有getWindow()方法。
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
//        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.BOTTOM, 0, 0);
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.signin_but:
                getPresenter().getSigninOperate();
                break;
            case R.id.signin_txt_rule:
                getRulePop();
                break;
            case R.id.signin_record:
                Intent intent = new Intent(this, SigninRecordActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void getRulePop() {
        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popw_signin_rule, null);

        //测量View的宽高
        DemoUtils.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popw_signin_rule)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                // .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗   此方法无用 必须重写
                .setAnimationStyle(R.style.MyPopupWindow_anim_style)
                .setViewOnclickListener(this)
                .create();
        // 产生背景变暗效果，设置透明度  必须重写 否则背景无法变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.2f;
        //之前不写这一句也是可以实现的，这次突然没效果了。网搜之后加上了这一句就好了。据说是因为popUpWindow没有getWindow()方法。
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
//        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.BOTTOM, 0, 0);
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0);
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId) {
            case R.layout.popw_signin_rule:
                ImageView tvSure = view.findViewById(R.id.popw_rule_no);
                tvSure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                break;
            case R.layout.popw_siginin_yes:
                ImageView sigYes = view.findViewById(R.id.popw_yes_no);
                sigYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                break;
        }
    }
}
