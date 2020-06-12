package com.work.rulong.delivery.base;

import androidx.annotation.NonNull;

import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.resp.BalanceInfoBean;
import com.trjx.tbase.mvp.TPresenter;
import com.trjx.tbase.mvp.TView;

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
//        if (respBean != null) {
//            int state = respBean.getResultState();
//            if (state == 203) {//返回余额数据
//                DemoConstant.balance = respBean.getData().getBalance();
//            }
//        }
        return super.responseState(respBean, "");
    }

}
