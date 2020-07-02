package com.work.doctor.fruits.base;

import android.content.Context;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.WechetPayInfo;
import com.t.httplib.yigou.bean.resp.BalanceInfoBean;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.trjx.tbase.mvp.TPresenter;
import com.trjx.tbase.mvp.TView;
import com.trjx.tlibs.uils.ToastUtil2;
import com.work.doctor.fruits.assist.DemoConstant;

public class DemoPresenter<V extends TView> extends TPresenter<V, DemoModel> {

    public DemoPresenter(@NonNull V view) {
        super(view);
    }
//
//    protected String getCurrentTimeStr() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        return sdf.format(System.currentTimeMillis());
//    }
//
//    /**
//     * 把数组所有元素排序，并按照“参数参数值”的模式拼接成字符串
//     *
//     * @param prestr
//     * @return
//     */
//    protected String getSignMD5String(String prestr) {
//        Log.i("tong", "-befour----" + prestr);
//        prestr = MD5Utils.getMD5(DemoConstant.secret + prestr + DemoConstant.secret, true);
//        Log.i("tong", "-after----" + prestr);
//        return prestr;
//    }
//
//    protected <B extends ReqTimeInfo> void setHeadInfo(B info) {
//        this.setHeadInfo("1.0", info);
//    }
//
//    protected <B extends ReqTimeInfo> void setHeadInfo(String versionStr, B info) {
//        HeadInfoModel headInfoModel = new HeadInfoModel();
//        headInfoModel.setTimestamp(info.getTimestamp());
//        headInfoModel.setVersion(versionStr);
//        headInfoModel.setSign(getSignMD5String(info.toString()));
//        HttpBase.headerInfo = headInfoModel.toString();
//    }

    /**
     * 针对于请求失败返回余额参数的情况
     * @param respBean
     * @param <T>
     * @return
     */
    public <T extends BalanceInfoBean> boolean responseStateBlance(DemoRespBean<T> respBean) {
        if (respBean != null) {
            int state = respBean.getResultState();
            if (state == 203) {//返回余额数据
                DemoConstant.balance = respBean.getData().getBalance();

            }
        }
        return super.responseState(respBean, "");
    }

    private IWXAPI wxAPI;
    public void registerWxApp(Context context){
        wxAPI = WXAPIFactory.createWXAPI(context, DemoConstant.wx_app_id, true);
        wxAPI.registerApp(DemoConstant.wx_app_id);
    }

    public void unRegisterWxApp(){
        if (wxAPI != null) {
            wxAPI.unregisterApp();
            wxAPI = null;
        }
    }



    /**
     * 获取微信信息
     *
     * @param context
     * @param state
     */
    public void getWxInfo(Context context, String state) {
        if (wxAPI == null) {
            return;
        }
        if (!wxAPI.isWXAppInstalled()) {
            ToastUtil2.showToast(context, "您还未安装微信客户端");
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = state;
//        req.state = "wechat_sdk_demo_test";
        wxAPI.sendReq(req);
    }

    public void getWxPayInfo(Context context, WechetPayInfo infoBean) {
        registerWxApp(context);
        //这里注意要放在子线程
//        Runnable payRunnable = () -> {
        if (wxAPI == null) {
            return;
        }
        if (!wxAPI.isWXAppInstalled()) {
            ToastUtil2.showToast(context, "您还未安装微信客户端");
            return;
        }
        PayReq request = new PayReq(); //调起微信APP的对象
        //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
        request.appId = DemoConstant.wx_app_id;
        request.partnerId = infoBean.getPartnerId();
        request.prepayId = infoBean.getPrepayId();
        request.packageValue = infoBean.getPackageX();
        request.nonceStr = infoBean.getNonceStr();
        request.timeStamp = infoBean.getTimeStamp();
        request.sign = infoBean.getSign();
        wxAPI.sendReq(request);//发送调起微信的请求
//        };
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
    }


//    public abstract class DemoObserver<T extends DemoRespBean<T>> extends TObserver<DemoRespBean<T>>{
//
//        @Override
//        public void onNext(DemoRespBean<T> bean) {
//            if(responseState(bean)){
//                if(isViewAttach()){
//                    onNext2(bean.getData());
//                    //调用方法
//                }
//            }
//        }
//
//        public abstract void onNext2(T bean);
//
//    }

    protected void tishi(String msg){
        if(isViewAttach()){
            getView().tRemind(msg);
        }
    }


//    public void payMent(String systemOrderNo,int payWay){
//        ReqPaymentInfo info = new ReqPaymentInfo();
//        info.setSystemOrderNo(systemOrderNo);
//        info.setPayWay(payWay);
//        model.requestPayment(info, new TObserver<DemoRespBean<PaymentInfoBean>>() {
//            @Override
//            public void onNext(DemoRespBean<PaymentInfoBean> bean) {
//            }
//        });
//    }

}
