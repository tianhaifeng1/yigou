package com.work.doctor.fruits.activity.order;

import android.util.Log;

import androidx.annotation.NonNull;

import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.bean.ReqCartDeleteInfo;
import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.WechetPayInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsPayInfo;
import com.t.httplib.yigou.bean.req.ReqInvoice2Info;
import com.t.httplib.yigou.bean.req.ReqInvoiceInfo;
import com.t.httplib.yigou.bean.req.ReqOrderSubmitInfo;
import com.t.httplib.yigou.bean.req.ReqPaymentInfo;
import com.t.httplib.yigou.bean.req.ReqShopInfo2;
import com.t.httplib.yigou.bean.req.ReqShopInfo4;
import com.t.httplib.yigou.bean.resp.AddressInfoBean;
import com.t.httplib.yigou.bean.resp.DistributionInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsAffirmBean;
import com.trjx.tbase.http.HttpBase;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.TUtils;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoPresenter;

import java.util.ArrayList;
import java.util.List;

public class GoodsAffirmPresenter extends DemoPresenter<GoodsAffirmView> {

    public GoodsAffirmPresenter(@NonNull GoodsAffirmView view) {
        super(view);
    }

    private List<ReqGoodsPayInfo.ReqGoodsPayInfoBean> goods = new ArrayList<>();

    protected void getInfo(String shopId, ArrayList<DatabaseGoodsInfo> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            ReqGoodsPayInfo info = new ReqGoodsPayInfo();
            info.setShopId(shopId);
            goods.clear();
            for (int i = 0; i < arrayList.size(); i++) {
                DatabaseGoodsInfo goodsInfo = arrayList.get(i);
                ReqGoodsPayInfo.ReqGoodsPayInfoBean infoBean = new ReqGoodsPayInfo.ReqGoodsPayInfoBean();
                infoBean.setGoodsId(goodsInfo.getGoodsId());
                infoBean.setAttrStrId(goodsInfo.getSpecId());
                infoBean.setTotalNum(goodsInfo.getGoodsNumber() + "");
                goods.add(infoBean);
            }
            info.setGoods(goods);

            model.requestPayInfoOrder(info, new TObserver<DemoRespBean<GoodsAffirmBean>>() {
                @Override
                public void onNext(DemoRespBean<GoodsAffirmBean> bean) {
                    int state = bean.getResultState();
                    if (state == HttpBase.POST_SUCCESS) {
                        if (isViewAttach()){
                            getView().getInfoSuccess(bean.getData());
                        }
                    } else if (state == 202) {
                        if (isViewAttach())
                            getView().getInfoFail(bean.getRemindMessage());
                    }else{
                        if (isViewAttach()) {
                            getView().getInfoFail("数据异常");
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.d("错误信息",""+e.getMessage());
                    super.onError(e);
                }
            });
        } else {
            Logger.t("商品列表不能为空");
        }

    }

    protected void getListDataAddress(String shopId, int page, int pageSize) {
        ReqShopInfo2 info = new ReqShopInfo2();
        info.setShopId(shopId);
        info.setPage(page);
        info.setPageSize(pageSize);
        model.requestAddressList(info, new TObserver<DemoRespBean<List<AddressInfoBean>>>() {
            @Override
            public void onNext(DemoRespBean<List<AddressInfoBean>> bean) {
                if (responseState(bean)) {
                    if (isViewAttach()) {
                        getView().getListDataSuccess(bean.getData());
                    }
                }
            }
        });
    }

    protected void updateAddress(AddressInfoBean info) {
        //判断
        if (info.getName() == null || info.getName().isEmpty()) {
            tishi("请填写收货人的姓名");
        } else if (info.getPhone() == null || info.getPhone().isEmpty()) {
            tishi("手机号码不能为空");
        } else if (!TUtils.isMobileNO(info.getPhone())) {
            tishi("手机号码无效，请检查");
        } else if (info.getAddress() == null || info.getAddress().isEmpty()) {
            tishi("请选择省市区");
        } else if (info.getDetailAddress() == null || info.getDetailAddress().isEmpty()) {
            tishi("请填写详细地址");
        } else {
            model.requestAddressEdit(info, new TObserver<DemoRespBean<AddressInfoBean>>() {
                @Override
                public void onNext(DemoRespBean<AddressInfoBean> bean) {
                    if (responseState(bean))
                        if (isViewAttach())
                            getView().updateAddressSuccess(bean.getData());
                }
            });
        }
    }

    /**
     * 获取配送费信息列表数据
     */
    protected void getDataDistribution(int distype) {
        if (DemoConstant.shopInfoBean == null) {
            Logger.t("店铺信息不能为空");
            return;
        }
        ReqShopInfo4 info4 = new ReqShopInfo4();
        info4.setShopId(DemoConstant.shopInfoBean.getShopId()+"");
        info4.setDistype(distype+"");


        model.requestDistribution(info4,new TObserver<DemoRespBean<DistributionInfoBean>>() {
            @Override
            public void onNext(DemoRespBean<DistributionInfoBean> bean) {
                if (responseState(bean))
                    if (isViewAttach())
                        getView().getDistributionInfoSuccess(bean.getData());
            }

        });
    }

    /**
     * 提交
     */
    protected void submitOrder(ReqOrderSubmitInfo submitInfo) {
            showDialog("生成订单中...");
            submitInfo.setGoods(goods);
            model.requestOrderSubmit(submitInfo, new TObserver<DemoRespBean<String>>() {
                @Override
                public void onNext(DemoRespBean<String> bean) {
                    if (responseState(bean, "")) {
                        if (isViewAttach())
                            getView().getSubmitOrderSuccess(bean.getData());
                    }
                }
            });
    }

    protected void payMent(String systemOrderNo,int payWay){
        DemoConstant.zfOrderId = systemOrderNo;
        DemoConstant.orderType = 1;
        ReqPaymentInfo info = new ReqPaymentInfo();
        info.setSystemOrderNo(systemOrderNo);
        info.setPayWay(payWay);
        model.requestPayment(info, new TObserver<DemoRespBean<WechetPayInfo>>() {
            @Override
            public void onNext(DemoRespBean<WechetPayInfo> bean) {
                if (bean != null) {
                    int state = bean.getResultState();
                    if (state == 203) {//返回余额数据
                        DemoConstant.balance = bean.getData().getBalance();
                        if (isViewAttach())
                            getView().getPayMentFaile203();
                    }
                }
                if (responseState(bean,"")) {
                    if (isViewAttach())
                        getView().getPayMentSuccess(bean.getData());
                }

            }
        });
    }

    protected void cartDelete(ReqCartDeleteInfo info) {
        model.requestCartDelete(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if(responseState(bean)){}
            }
        });
    }

    protected void applyInvoice(ReqInvoiceInfo info){
        info.setCategory(1);
        model.requestApplyInvoice(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if(responseState(bean))
                    if(isViewAttach())
                        getView().applyInvoiceSuccess();
            }
        });
    }

    protected void applyInvoice2(ReqInvoice2Info info){
        info.setCategory(0);
        model.requestApplyInvoice2(info, new TObserver<DemoRespBean>() {
            @Override
            public void onNext(DemoRespBean bean) {
                if(responseState(bean))
                    if(isViewAttach())
                        getView().applyInvoiceSuccess();
            }
        });
    }

}
