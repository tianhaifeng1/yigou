package com.t.httplib.yigou;

import com.google.gson.Gson;
import com.t.databaselib.bean.CartShopInfoBean;
import com.t.databaselib.bean.ReqCartDeleteInfo;
import com.t.databaselib.bean.ShopInfoBean;
import com.t.httplib.Util;
import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.HeadInfoModel;
import com.t.httplib.yigou.bean.WechatCodeBean;
import com.t.httplib.yigou.bean.WechatUserInfo;
import com.t.httplib.yigou.bean.WechetPayInfo;
import com.t.httplib.yigou.bean.req.ReqAddressEditInfoBean;
import com.t.httplib.yigou.bean.req.ReqAddressInfo;
import com.t.httplib.yigou.bean.req.ReqApplyTxInfo;
import com.t.httplib.yigou.bean.req.ReqBalanceRankingInfo;
import com.t.httplib.yigou.bean.req.ReqBankInfo;
import com.t.httplib.yigou.bean.req.ReqBrandInfo;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.req.ReqCommentInfo;
import com.t.httplib.yigou.bean.req.ReqCouponGrantInfo;
import com.t.httplib.yigou.bean.req.ReqCouponInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo2;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo3;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo4;
import com.t.httplib.yigou.bean.req.ReqGoodsListInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsListInfo2;
import com.t.httplib.yigou.bean.req.ReqGoodsPayInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsTypeInfo;
import com.t.httplib.yigou.bean.req.ReqHorsemanInfo;
import com.t.httplib.yigou.bean.req.ReqInvoice2Info;
import com.t.httplib.yigou.bean.req.ReqInvoiceInfo;
import com.t.httplib.yigou.bean.req.ReqLoginInfo;
import com.t.httplib.yigou.bean.req.ReqLoginWxInfo2;
import com.t.httplib.yigou.bean.req.ReqOrderInfo;
import com.t.httplib.yigou.bean.req.ReqOrderListInfo;
import com.t.httplib.yigou.bean.req.ReqOrderSubmitInfo;
import com.t.httplib.yigou.bean.req.ReqPageInfo;
import com.t.httplib.yigou.bean.req.ReqPayOrderInfo;
import com.t.httplib.yigou.bean.req.ReqPayOrderMentInfo;
import com.t.httplib.yigou.bean.req.ReqPaymentInfo;
import com.t.httplib.yigou.bean.req.ReqRefundInfo;
import com.t.httplib.yigou.bean.req.ReqRuleInfo;
import com.t.httplib.yigou.bean.req.ReqSearchGoodsInfo;
import com.t.httplib.yigou.bean.req.ReqShopInfo;
import com.t.httplib.yigou.bean.req.ReqShopInfo2;
import com.t.httplib.yigou.bean.req.ReqShopInfo3;
import com.t.httplib.yigou.bean.req.ReqSynchroCartInfoOut;
import com.t.httplib.yigou.bean.req.ReqTimeInfo;
import com.t.httplib.yigou.bean.req.ReqYgSearchInfo;
import com.t.httplib.yigou.bean.req.ReqYzmInfo;
import com.t.httplib.yigou.bean.resp.AddressInfoBean;
import com.t.httplib.yigou.bean.resp.BalanceStatInfoBean;
import com.t.httplib.yigou.bean.resp.BankcardInfoBean;
import com.t.httplib.yigou.bean.resp.BannerInfoBean;
import com.t.httplib.yigou.bean.resp.BrandInfoBean;
import com.t.httplib.yigou.bean.resp.CategoryGoodsBean;
import com.t.httplib.yigou.bean.resp.CouponCenterInfoBean;
import com.t.httplib.yigou.bean.resp.CouponGrantInfoBean;
import com.t.httplib.yigou.bean.resp.CouponInfoBean;
import com.t.httplib.yigou.bean.resp.DistributionInfoBean;
import com.t.httplib.yigou.bean.resp.EvaluateInfoBean;
import com.t.httplib.yigou.bean.resp.ExpressPsInfoBean;
import com.t.httplib.yigou.bean.resp.FensInfoBean;
import com.t.httplib.yigou.bean.resp.GmrOutInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsDetailInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsListInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsTypeInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsTypeOutInfoBean;
import com.t.httplib.yigou.bean.resp.HorsemanLocationInfoBean;
import com.t.httplib.yigou.bean.resp.HotCityInfoBean;
import com.t.httplib.yigou.bean.resp.LoginInfoBean;
import com.t.httplib.yigou.bean.resp.MoneyRecordApplyListInfoBean;
import com.t.httplib.yigou.bean.resp.MoneyRecordListInfoBean;
import com.t.httplib.yigou.bean.resp.OrderDetailInfoBean;
import com.t.httplib.yigou.bean.resp.OrderListInfoBean;
import com.t.httplib.yigou.bean.resp.OrderNumberInfoBean;
import com.t.httplib.yigou.bean.resp.OutInfoBean;
import com.t.httplib.yigou.bean.resp.PayListInfoBean;
import com.t.httplib.yigou.bean.resp.PayOrderInfoBean;
import com.t.httplib.yigou.bean.resp.PayOrderInfoBean2;
import com.t.httplib.yigou.bean.resp.RefundDetailInfoBean;
import com.t.httplib.yigou.bean.resp.RuleInfoBean;
import com.t.httplib.yigou.bean.resp.ShouyRankingInfoBean;
import com.t.httplib.yigou.bean.resp.SigninInfoBean;
import com.t.httplib.yigou.bean.resp.SigninRecordBean;
import com.t.httplib.yigou.bean.resp.TkOrderInfoBean;
import com.t.httplib.yigou.bean.resp.UserInfoBean;
import com.trjx.tbase.http.HttpBase;
import com.trjx.tbase.mvp.TModel;
import com.trjx.tlibs.uils.Logger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class DemoModel extends TModel {

    private DemoHttpRetrofit httpRetrofit;

    private Gson gson;

    public DemoModel() {
        httpRetrofit = DemoHttpRetrofit.getInstance();
        //这里可以设置固定的HeadInfoModel
//        HttpBase.headerName = "extend";
//        HttpBase.headerInfo = "";
        gson = new Gson();
    }

//    public void test3(ReqTest2Info info, Observer<DemoRespBean<List<GoodsInfoBean>>> observer) {
//
//        httpRetrofit.httpAPI.test3(getRequestBody(info))
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }
//
//    public void test3_(String jsonStr, Observer<DemoRespBean<GoodsInfoBean>> observer) {
//
//        httpRetrofit.httpAPI.test3_(HttpBase.getRequestBody(jsonStr))
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }


    //登陆注册
    public void requestYzm(final ReqYzmInfo info, final Observer<DemoRespBean> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postYzm(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });

    }

    public void requestLogin(final ReqLoginInfo info, final Observer<DemoRespBean<LoginInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postLogin(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
    }

    public void requestLoginWx(final ReqLoginWxInfo2 info, final Observer<DemoRespBean<LoginInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postLoginWx(setHeadInfoString("1.1", info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postLoginWx(setHeadInfoString("1.1", info), getRequestBody(info)), observer);
    }

    public void requestShopInfo(final ReqShopInfo info, final Observer<DemoRespBean<ShopInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postShopInfo(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postShopInfo(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestSignout(final Observer<DemoRespBean> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                bindObserver(httpRetrofit.httpAPI.postSignout(setTimeHeadInfoString(currenTime+"")), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postSignout(setTimeHeadInfoString()), observer);
    }


    // 系统相关

    //系统规则
    public void requestRule(final ReqRuleInfo info, final Observer<DemoRespBean<RuleInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postRule(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postRule(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //热门城市
    public void requestHotCityList(final Observer<DemoRespBean<List<HotCityInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                bindObserver(httpRetrofit.httpAPI.postHotCityList(setTimeHeadInfoString(currenTime+"")), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postHotCityList(setTimeHeadInfoString()), observer);
    }

    //商品分类
    public void requestCategory(final ReqGoodsTypeInfo info, final Observer<DemoRespBean<GoodsTypeOutInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postCategory(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postCategory(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestCategoryMain(final ReqGoodsTypeInfo info, final Observer<DemoRespBean<List<GoodsTypeInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postCategoryMain(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postCategoryMain(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //    品牌
    public void requestBrandList(final ReqBrandInfo info, final Observer<DemoRespBean<List<BrandInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postBrandList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postBrandList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //搜索商品
    public void requestSearchGoods(final ReqSearchGoodsInfo info, final Observer<DemoRespBean<List<GoodsInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postSearchGoods(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postSearchGoods(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //首页banner图
    public void requestBanner(final ReqShopInfo3 info, final Observer<DemoRespBean<List<BannerInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postBanner(setHeadInfoString(info), getRequestBody(info)), observer);;
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postBanner(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //获取银行卡信息\获取提现起始金额
    public void requestBankcardList(final String dataDictName, final Observer<DemoRespBean<List<BankcardInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                ReqBankInfo info = new ReqBankInfo();
                info.setDataDictName(dataDictName);
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postBankcardList(setHeadInfoString(info), getRequestBody(info)), observer);

            }
        });
//        ReqBankInfo info = new ReqBankInfo();
//        info.setDataDictName(dataDictName);
//        bindObserver(httpRetrofit.httpAPI.postBankcardList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    // 位置信息
//    获取骑手位置信息
    public void requestLocation(final ReqHorsemanInfo info, final Observer<DemoRespBean<HorsemanLocationInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postLocation(setHeadInfoString("1.1", info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postLocation(setHeadInfoString("1.1", info), getRequestBody(info)), observer);
    }

    //    优惠卷
    public void requestCouponMyInfo(final ReqCouponInfo info, final Observer<DemoRespBean<List<CouponInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postCouponMyInfo(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postCouponMyInfo(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestCouponCenterInfo(final ReqPageInfo info, final Observer<DemoRespBean<List<CouponCenterInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postCouponCenterInfo(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postCouponCenterInfo(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestCouponGrantInfo(final ReqCouponGrantInfo info, final Observer<DemoRespBean<CouponGrantInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postCouponGrantInfo(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postCouponGrantInfo(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //    搜索
    public void requestSearchInfo(final ReqYgSearchInfo info, final Observer<DemoRespBean<List<GoodsInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postSearchInfo(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postSearchInfo(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestSearchPopularGoodsList(final Observer<DemoRespBean<List<String>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                bindObserver(httpRetrofit.httpAPI.postSearchPopularGoodsList(setTimeHeadInfoString(currenTime+"")), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postSearchPopularGoodsList(setTimeHeadInfoString()), observer);
    }


    //    个人信息
    public void requestUserInfo(final Observer<DemoRespBean<UserInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                bindObserver(httpRetrofit.httpAPI.postUserInfo(setTimeHeadInfoString(currenTime+"")), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postUserInfo(setTimeHeadInfoString()), observer);
    }

    //    我的收益统计
    public void requestUserBalanceStat(final Observer<DemoRespBean<BalanceStatInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                bindObserver(httpRetrofit.httpAPI.postBalanceStat(setTimeHeadInfoString(currenTime+"")), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postBalanceStat(setTimeHeadInfoString()), observer);
    }

    //    收益转余额
    public void requestRateToBalance(final Observer<DemoRespBean> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                bindObserver(httpRetrofit.httpAPI.postRateToBalance(setTimeHeadInfoString(currenTime+"")), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postRateToBalance(setTimeHeadInfoString()), observer);
    }

    //    收益提现
    public void requestApplyMoney(final ReqApplyTxInfo info, final Observer<DemoRespBean> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postApplyMoney(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postApplyMoney(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //    我的粉丝列表
    public void requestUserFans(final ReqPageInfo info, final Observer<DemoRespBean<List<FensInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postMemberFans(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postMemberFans(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //    余额记录列表
    public void requestUserMoneyRecordList(final ReqPageInfo info, final Observer<DemoRespBean<List<MoneyRecordListInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postMoneyRecordList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postMoneyRecordList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //    提现记录列表
    public void requestApplyMoneyRecordList(final ReqPageInfo info, final Observer<DemoRespBean<List<MoneyRecordApplyListInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postMoneyRecordApplyList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postMoneyRecordApplyList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //    生成用户二维码
    public void requestUserCreatWxaqrcode(final Observer<DemoRespBean<String>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                bindObserver(httpRetrofit.httpAPI.postCreatWxaqrcode(setTimeHeadInfoString(currenTime+"")), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postCreatWxaqrcode(setTimeHeadInfoString()), observer);
    }

    //    商品相关

    //    商品列表
    public void requestGoodsList(final ReqGoodsListInfo info, final Observer<DemoRespBean<List<GoodsInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postGoodsList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postGoodsList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //    预售商品列表
    public void requestPreselGoodsList(final ReqGoodsInfo3 info, final Observer<DemoRespBean<List<GoodsInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postPresellGoodsList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postPresellGoodsList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestHomePageGoodsList(final ReqGoodsListInfo2 info, final Observer<DemoRespBean<List<GoodsInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postHomePageGoodsList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postHomePageGoodsList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //    商品详情
    public void requestGoodsDetail(final ReqGoodsInfo info, final Observer<DemoRespBean<OutInfoBean<GoodsDetailInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postGoodsDetail(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postGoodsDetail(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //    商品规格列表
    public void requestGoodsItem(final ReqGoodsInfo info, final Observer<DemoRespBean<GoodsSpecInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postGoodsItem(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postGoodsItem(setHeadInfoString(info), getRequestBody(info)), observer);
    }


    //    订单相关
//    订单数量
    public void requestOrderNumber(final Observer<DemoRespBean<OrderNumberInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                bindObserver(httpRetrofit.httpAPI.postOrderNumber(setTimeHeadInfoString(currenTime + "")), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postOrderNumber(setTimeHeadInfoString()), observer);
    }

    public void requestOrderList(final ReqOrderListInfo info, final Observer<DemoRespBean<List<OrderListInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postOrderList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postOrderList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestTkOrderList(final ReqPageInfo info, final Observer<DemoRespBean<List<TkOrderInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postTkOrderList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postTkOrderList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestOrderDetail(final ReqOrderInfo info, final Observer<DemoRespBean<OrderDetailInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postOrderDetail(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postOrderDetail(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestOrderCancel(final ReqOrderInfo info, final Observer<DemoRespBean> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postOrderCancel(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postOrderCancel(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestOrderReceive(final ReqOrderInfo info, final Observer<DemoRespBean> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postOrderReceive(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postOrderReceive(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestOrderExpress(final ReqOrderInfo info, final Observer<DemoRespBean<ExpressPsInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postOrderExpress(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postOrderExpress(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestOrderDelete(final ReqOrderInfo info, final Observer<DemoRespBean> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postOrderDelete(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postOrderDelete(setHeadInfoString(info), getRequestBody(info)), observer);
    }


    //    评论相关
    public void requestCommentList(final ReqGoodsInfo2 info, final Observer<DemoRespBean<List<EvaluateInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postCommentList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postCommentList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestAddComment(final ReqCommentInfo info, final Observer<DemoRespBean> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postAddComment(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postAddComment(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestCartAdd(final ReqCartAddInfo info, final Observer<DemoRespBean> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postCartAdd(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postCartAdd(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestCartList(final ReqShopInfo2 info, final Observer<DemoRespBean<List<CartShopInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postCartList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postCartList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestCartDelete(final ReqCartDeleteInfo info, final Observer<DemoRespBean> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                HeadInfoModel headInfoModel = new HeadInfoModel();
                headInfoModel.setTimestamp(info.getTimestamp());
                headInfoModel.setVersion("1.0");
                Logger.t("DemoModel 加密前 ：" + info.toString());
                String myao = Util.MD5EncodeUtf8(info.toString());
                Logger.t("DemoModel 加密后 ：" + myao);
                headInfoModel.setSign(myao);
                bindObserver(httpRetrofit.httpAPI.postCartDelete(headInfoModel.toString(), getRequestBody(info)), observer);
            }
        });

    }


    //同步购物车消息
    public void requestSynchorCart(final ReqSynchroCartInfoOut info, final Observer<DemoRespBean> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postSynchorCart(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
    }


    //    购物车相关
//    public void requestCartAdd(String jsonStr,Observer<DemoRespBean> observer){
//
//        bindObserver(httpRetrofit.httpAPI.postCartAdd(getRequestBody(info)),observer);
//    }
//    public void requestCartList(String jsonStr,Observer<DemoRespBean<List<CartShopInfoBean>>> observer){
//        bindObserver(httpRetrofit.httpAPI.postCartList(getRequestBody(info)),observer);
//    }
//    public void requestCartDelete(String jsonStr,Observer<DemoRespBean> observer){
//        bindObserver(httpRetrofit.httpAPI.postCartDelete(getRequestBody(info)),observer);
//    }
//    public void requestCartSelect(String jsonStr,Observer<DemoRespBean> observer){
//        bindObserver(httpRetrofit.httpAPI.postCartSelect(getRequestBody(info)),observer);
//    }
//    public void requestCartUpdateNum(String jsonStr,Observer<DemoRespBean> observer){
//        bindObserver(httpRetrofit.httpAPI.postCartUpdateNum(getRequestBody(info)),observer);
//    }


// 收货地址信息相关

    public void requestAddressEdit(final AddressInfoBean addressInfoBean, final Observer<DemoRespBean<AddressInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                //赋值
                ReqAddressEditInfoBean infoBean = new ReqAddressEditInfoBean();
                infoBean.setAddress(addressInfoBean.getAddress());
                infoBean.setAddressId(addressInfoBean.getId());
                infoBean.setCity(addressInfoBean.getCity());
                infoBean.setCounty(addressInfoBean.getCounty());
                infoBean.setDetailAddress(addressInfoBean.getDetailAddress());
                infoBean.setIsDefault(addressInfoBean.getIsDefault());
                infoBean.setLatitude(addressInfoBean.getLatitude());
                infoBean.setLongitude(addressInfoBean.getLongitude());
                infoBean.setName(addressInfoBean.getName());
                infoBean.setPhone(addressInfoBean.getPhone());
                infoBean.setProvince(addressInfoBean.getProvince());
                infoBean.setSex(addressInfoBean.getSex());
                infoBean.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postAddressEdit(setHeadInfoString(infoBean), getRequestBody(infoBean)), observer);
            }
        });


    }

    public void requestAddressDelete(final ReqAddressInfo info, final Observer<DemoRespBean> observer) {

        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postAddressDelete(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postAddressDelete(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestAddressList(final ReqShopInfo2 info, final Observer<DemoRespBean<List<AddressInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postAddressList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postAddressList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestAddressDetail(final ReqAddressInfo info, final Observer<DemoRespBean<AddressInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postAddressDetail(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postAddressDetail(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestAddressSetDefault(final ReqAddressInfo info, final Observer<DemoRespBean> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postAddressSetDefault(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postAddressSetDefault(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //    会员充值信息相关
    //充值规则列表
    public void requestPurchaseRecordList(final ReqPageInfo info, final Observer<DemoRespBean<List<PayOrderInfoBean2>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postPurchaseRecordList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postPurchaseRecordList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //充值规则列表
    public void requestPayList(final ReqPageInfo info, final Observer<DemoRespBean<List<PayListInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postPayList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postPayList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //    生成充值订单
    public void requestPayOrder(final ReqPayOrderInfo info, final Observer<DemoRespBean<PayOrderInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postPayOrder(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postPayOrder(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //    充值支付
    public void requestPayOrderPayMent(final ReqPayOrderMentInfo info, final Observer<DemoRespBean<WechetPayInfo>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postPayOrderPayMent(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postPayOrderPayMent(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //    充值记录列表
    public void requestPayRecordList(final ReqPageInfo info, final Observer<DemoRespBean<List<PayOrderInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postPayRecordList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postPayRecordList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //    提交订单
    public void requestOrderSubmit(final ReqOrderSubmitInfo info, final Observer<DemoRespBean<String>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postOrderSubmit(setHeadInfoString("1.1", info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postOrderSubmit(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //    购买支付
    public void requestPayment(final ReqPaymentInfo info, final Observer<DemoRespBean<WechetPayInfo>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postPayment(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postPayment(setHeadInfoString(info), getRequestBody(info)), observer);
    }


    //    商品订单支付信息
    public void requestPayInfoOrder(final ReqGoodsPayInfo info, final Observer<DemoRespBean<OrderDetailInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postPayInfoOrder(setHeadInfoString("1.1", info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postPayInfoOrder(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    // 配送费列表
    public void requestDistribution(final ReqShopInfo3 info, final Observer<DemoRespBean<DistributionInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postDistribution(setHeadInfoString("1.1", info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postDistribution(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    // 申请开发票
    public void requestApplyInvoice(final ReqInvoiceInfo info, final Observer<DemoRespBean> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postApplyInvoice(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postApplyInvoice(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestApplyInvoice2(final ReqInvoice2Info info, final Observer<DemoRespBean> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postApplyInvoice(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postApplyInvoice(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //申请退款
    public void requestApplyRefund(final ReqRefundInfo info, final Observer<DemoRespBean> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postApplyRefund(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postApplyRefund(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //退款详情
    public void requestRefundDetial(final ReqOrderInfo info, final Observer<DemoRespBean<RefundDetailInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postRefundDetial(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postRefundDetial(setHeadInfoString(info), getRequestBody(info)), observer);
    }


    public void requestBalanceRankingList(final ReqBalanceRankingInfo info, final Observer<DemoRespBean<OutInfoBean<List<ShouyRankingInfoBean>>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postBalanceRankingList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postBalanceRankingList(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    public void requestPurchaseRrecord(final ReqGoodsInfo2 info, final Observer<DemoRespBean<GmrOutInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postPurchaseRrecord(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postPurchaseRrecord(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //签到信息
    public void requestSigninInfo(final ReqShopInfo3 info, final Observer<DemoRespBean<SigninInfoBean>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postSigninInfo(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postSigninInfo(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //用户签到
    public void requestSigninOperate(final ReqShopInfo3 info, final Observer<DemoRespBean<String>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postSigninOperate(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postSigninOperate(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //签到记录
    public void requestSigninRecord(final ReqShopInfo2 info, final Observer<DemoRespBean<List<SigninRecordBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postSigninRecord(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postSigninRecord(setHeadInfoString(info), getRequestBody(info)), observer);
    }

    //频道广场
    public void requestActivityGoodsList(final ReqShopInfo3 info, final Observer<DemoRespBean<List<GoodsListInfoBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postActivityGoodsList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postActivityGoodsList(setHeadInfoString(info), getRequestBody(info)), observer);
    }
    //频道广场三级分类
    public void requestCategoryGoodsList(final ReqGoodsInfo4 info, final Observer<DemoRespBean<List<CategoryGoodsBean>>> observer) {
        getTimeLong(new CurrenTimeListener() {
            @Override
            public void getTimeLong(long currenTime) {
                info.setTimestamp(currenTime+"");
                bindObserver(httpRetrofit.httpAPI.postCategoryGoodsList(setHeadInfoString(info), getRequestBody(info)), observer);
            }
        });
//        bindObserver(httpRetrofit.httpAPI.postCategoryGoodsList(setHeadInfoString(info), getRequestBody(info)), observer);
    }





    //微信
    public void requestWxAccesstoken(String path, Observer<WechatCodeBean> observer) {
        bindObserver(httpRetrofit.httpAPI.wxAccesstoken(path), observer);
    }

    public void requestWxUserInfo(String path, Observer<WechatUserInfo> observer) {
//        bindObserver(httpRetrofit.httpAPI.wxUserInfo(path), observer);
    }
//    public void requestWxXcxEwm(String path,String jsonStr,Observer<BufferedInputStream> observer) {
//
//        bindObserver(httpRetrofit.httpAPI.wxXcxEwm(path,getRequestBody(info)),observer);
//    }


    public String setTimeHeadInfoString(String timglong) {
        return setTimeHeadInfoString("1.0",timglong);
    }

    public String setTimeHeadInfoString(String versionStr,String timglong) {
        ReqTimeInfo info = new ReqTimeInfo();
        info.setTimestamp(timglong);

        return setHeadInfoString(versionStr, info);
    }

//    public String getCurrentTimeStr() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        return sdf.format(System.currentTimeMillis());
////        return GetNetworkTime.getBaiduNetTime() + "";
//    }

    public <B extends ReqTimeInfo> String setHeadInfoString(B info) {
        return this.setHeadInfoString("1.0", info);
    }

    public <B extends ReqTimeInfo> String setHeadInfoString(String versionStr, B info) {

//        info.setTimestamp(getCurrentTimeStr());
        HeadInfoModel headInfoModel = new HeadInfoModel();
        headInfoModel.setTimestamp(info.getTimestamp());
        headInfoModel.setVersion(versionStr);
        Logger.t("DemoModel 加密前 ：" + info.toString());
        String myao = Util.MD5EncodeUtf8(info.toString());
        Logger.t("DemoModel 加密后 ：" + myao);
        headInfoModel.setSign(myao);
        return headInfoModel.toString();
    }

    private <T> RequestBody getRequestBody(T t) {
        return HttpBase.getRequestBody(gson.toJson(t));
    }

    //添加观察者模式
    private <B> void bindObserver(Observable<B> observable, Observer<B> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getTimeLong(final CurrenTimeListener listener) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String webUrl = "http://www.baidu.com";
                    URL url = new URL(webUrl);// 取得资源对象
                    URLConnection uc = url.openConnection();// 生成连接对象
                    uc.connect();// 发出连接
                    long timelong = uc.getDate();// 读取网站日期时间
                    if (listener != null) {
                        listener.getTimeLong(timelong);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    interface CurrenTimeListener {
        void getTimeLong(long currenTime);
    }


//    private long getCurrenTime(CurrenTimeListener listener) {
//        GetNetworkTime.getBaiduNetTime()+"";
//    }

//    interface CurrenTimeListener{
//        void getTimeLong(long currenTime)
//    }


}
