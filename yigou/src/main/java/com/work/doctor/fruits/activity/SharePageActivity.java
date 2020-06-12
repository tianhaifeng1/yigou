package com.work.doctor.fruits.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.BalanceStatInfoBean;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.DemoUtils;
import com.work.doctor.fruits.base.DemoMVPActivity;

public class SharePageActivity extends DemoMVPActivity<SharePageView , SharePagePresenter> implements SharePageView {

    private IWXAPI api;

    private TextView sharePageMoney;
    private TextView sharePageNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_page);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("邀好友 赚奖励",true);
        api = WXAPIFactory.createWXAPI(this, DemoConstant.wx_app_id,false);

        sharePageMoney = findViewById(R.id.share_page_money);
        sharePageNum = findViewById(R.id.share_page_num);

        getPresenter().getDataInfo();

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
        sharePageMoney.setText(BigDecimalUtil.formatNumberString(infoBean.getParam2()));
        sharePageNum.setText(infoBean.getParam1());
    }

}
