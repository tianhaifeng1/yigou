package com.work.doctor.fruits.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.t.httplib.yigou.bean.resp.BalanceStatInfoBean;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.trjx.tlibs.uils.ToastUtil2;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.DemoUtils;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.CommonPopupWindow;

import java.util.Timer;
import java.util.TimerTask;

public class SharePageActivity extends DemoMVPActivity<SharePageView , SharePagePresenter> implements SharePageView, View.OnClickListener, CommonPopupWindow.ViewInterface {

    private IWXAPI api;


    private RelativeLayout intRegisterRel;
    private EditText intRegisterPhone;
    private EditText intRegisterPsw;
    private TextView intRegisterSendsms;
    private TextView intRegister;
    private RelativeLayout sharePageScr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_page);
    }

    @Override
    protected void initView() {
        super.initView();
        intRegisterRel = findViewById(R.id.int_register_rel);
        sharePageScr = findViewById(R.id.share_page_scr);

        if(DemoConstant.userStatus==3){
            titleModule.initTitle("注册",true);
            intRegisterRel.setVisibility(View.VISIBLE);
            sharePageScr.setVisibility(View.GONE);

            intRegisterPhone = findViewById(R.id.int_register_phone);
            intRegisterPsw = findViewById(R.id.int_register_psw);
            intRegisterSendsms = findViewById(R.id.int_register_sendsms);
            intRegister = findViewById(R.id.int_register);

            intRegisterSendsms.setOnClickListener(this);
            intRegister.setOnClickListener(this);

        } else {
            titleModule.initTitle("邀好友 赚奖励",true);
            intRegisterRel.setVisibility(View.GONE);
            sharePageScr.setVisibility(View.VISIBLE);

            api = WXAPIFactory.createWXAPI(this, DemoConstant.wx_app_id,false);

            getPresenter().getDataInfo();
        }



    }

    private static final int THUMB_SIZE = 150;
    public void onShareEvent(View view) {

        WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
        miniProgramObj.webpageUrl = "http://www.qq.com"; // 兼容低版本的网页链接
        miniProgramObj.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;// 正式版:0，测试版:1，体验版:2
        miniProgramObj.userName = "gh_eb67faa2d2d4";     // 小程序原始id
        miniProgramObj.path = "/pages/invitation/link?inviterId=" + DemoConstant.userId;            //小程序页面路径
        WXMediaMessage msg = new WXMediaMessage(miniProgramObj);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.share_out2);
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
//        bmp.recycle();

//        msg.title = "";                    // 小程序消息title
        msg.description = "";               // 小程序消息desc
        msg.thumbData = DemoUtils.bmpToByteArray(bmp, true);      // 小程序消息封面图片，小于128k
        bmp.recycle();
        if (!api.isWXAppInstalled()) {
            ToastUtil2.showToast(context, "您还未安装微信客户端");
            return;
        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("miniProgram");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;  // 目前只支持会话
        api.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public void onFaceToFaceShareEvent(View view) {
        skipActivity(ShareFaceToFacePageActivity.class);
    }

    public void onShareDetailEvent(View view) {
        skipActivity(ShareDetailActivity.class);
    }

    @Override
    protected SharePagePresenter initPersenter() {
        return new SharePagePresenter(this);
    }

    @Override
    public void getDataInfoSuccess(BalanceStatInfoBean infoBean) {

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int whas = msg.what;
            if (whas == 1) {
                if (djs >= 0) {
                    intRegisterSendsms.setText(djs + "秒");
                    djs--;
                } else {
                    timer.cancel();
                    timer = null;
                    intRegisterSendsms.setEnabled(true);
                    intRegisterSendsms.setText("重新获取");
                }
            }
        }
    };

    private Timer timer;
    private int djs = 60;

    @Override
    public void eventDjs(String data) {
        intRegisterSendsms.setEnabled(false);
        djs = 60;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, 0, 1000);
    }

    private CommonPopupWindow popupWindow;

    private void getSuccessPop() {
        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popw_sharepage_yes, null);

        //测量View的宽高
        DemoUtils.measureWidthAndHeight(upView);
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popw_sharepage_yes)
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
    public void registerSuccess() {
        getSuccessPop();
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.int_register_sendsms:
                String phoneNumber = intRegisterPhone.getText().toString().trim();
                getPresenter().getYzm(phoneNumber);
                break;
            case R.id.int_register:
                String phoneNumber2 = intRegisterPhone.getText().toString().trim();
                String psw2 = intRegisterPsw.getText().toString().trim();
                getPresenter().register(phoneNumber2, psw2);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isFinishing()) {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId) {
            case R.layout.popw_sharepage_yes:
                ImageView tvSure = view.findViewById(R.id.popw_yes_no);
                tvSure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                break;
        }
    }
}
