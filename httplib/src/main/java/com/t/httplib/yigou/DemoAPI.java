package com.t.httplib.yigou;


import com.t.databaselib.bean.CartShopInfoBean;
import com.t.databaselib.bean.ShopInfoBean;
import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.WechatCodeBean;
import com.t.httplib.yigou.bean.WechatUserInfo;
import com.t.httplib.yigou.bean.WechetPayInfo;
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
import com.t.httplib.yigou.bean.resp.TimeLongInfo;
import com.t.httplib.yigou.bean.resp.TkOrderInfoBean;
import com.t.httplib.yigou.bean.resp.UserInfoBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface DemoAPI {


    //=================================  正式接口  =============================================
    // 系统相关
    //系统规则
    @POST("system/singlePage")
    Observable<DemoRespBean<RuleInfoBean>> postRule(@Header("extend") String headInfoModelToString,
                                                    @Body RequestBody body);

    //热门城市列表
    @POST("system/hotCityList")
    Observable<DemoRespBean<List<HotCityInfoBean>>> postHotCityList(@Header("extend") String headInfoModelToString);

    //商品分类
    @POST("system/category")
    Observable<DemoRespBean<GoodsTypeOutInfoBean>> postCategory(@Header("extend") String headInfoModelToString,
                                                                @Body RequestBody body);

    @POST("system/category")
    Observable<DemoRespBean<List<GoodsTypeInfoBean>>> postCategoryMain(@Header("extend") String headInfoModelToString,
                                                                       @Body RequestBody body);

    @POST("system/brandList")
    Observable<DemoRespBean<List<BrandInfoBean>>> postBrandList(@Header("extend") String headInfoModelToString,
                                                                @Body RequestBody body);

    //搜索商品
    @POST("system/searchGoods")
    Observable<DemoRespBean<List<GoodsInfoBean>>> postSearchGoods(@Header("extend") String headInfoModelToString,
                                                                  @Body RequestBody body);

    //首页banner图
    @POST("system/banner")
    Observable<DemoRespBean<List<BannerInfoBean>>> postBanner(@Header("extend") String headInfoModelToString,
                                                              @Body RequestBody body);

    //获取银行卡信息\获取提现起始金额
    @POST("system/sysDataDictionary")
    Observable<DemoRespBean<List<BankcardInfoBean>>> postBankcardList(@Header("extend") String headInfoModelToString,
                                                                      @Body RequestBody body);

    // 位置信息
    @POST("location/getLocation")
    Observable<DemoRespBean<HorsemanLocationInfoBean>> postLocation(@Header("extend") String headInfoModelToString,
                                                                    @Body RequestBody body);


    //登陆注册接口
    @POST("login/sendSms")
    Observable<DemoRespBean> postYzm(@Header("extend") String headInfoModelToString,
                                     @Body RequestBody body);

    @POST("login/app/phoneLogin")
    Observable<DemoRespBean<LoginInfoBean>> postLogin(@Header("extend") String headInfoModelToString,
                                                      @Body RequestBody body);

    @POST("login/app/wxlogin")
    Observable<DemoRespBean<LoginInfoBean>> postLoginWx(@Header("extend") String headInfoModelToString,
                                                        @Body RequestBody body);

    @POST("login/shopInfo")
    Observable<DemoRespBean<ShopInfoBean>> postShopInfo(@Header("extend") String headInfoModelToString,
                                                        @Body RequestBody body);

//  频道广场
    @POST("goods/activityGoodsList")
    Observable<DemoRespBean<List<GoodsListInfoBean>>> postActivityGoodsList(@Header("extend") String headInfoModelToString,
                                                             @Body RequestBody body);

//  频道广场三级列表
    @POST("goods/activityCategoryGoodsList")
    Observable<DemoRespBean<List<CategoryGoodsBean>>> postCategoryGoodsList(@Header("extend") String headInfoModelToString,
                                                                            @Body RequestBody body);



    @POST("login/app/signout")
    Observable<DemoRespBean> postSignout(@Header("extend") String headInfoModelToString);


//    @POST("http://192.168.1.152:8080/demo/test/goods/goodsList")
//    Observable<DemoRespBean<List<BannerInfoBean>>> banner(@Body RequestBody body);

//    @POST("http://192.168.1.152:8080/demo/test/goods/goodsList")
//    Observable<DemoRespBean<WebInfoBean>> postWebInfo(@Body RequestBody body);


    //优惠卷相关

    //我的优惠卷
    @POST("coupon/mCouponList")
    Observable<DemoRespBean<List<CouponInfoBean>>> postCouponMyInfo(@Header("extend") String headInfoModelToString,
                                                                    @Body RequestBody body);

    //优惠卷中心列表
    @POST("coupon/couponList")
    Observable<DemoRespBean<List<CouponCenterInfoBean>>> postCouponCenterInfo(@Header("extend") String headInfoModelToString,
                                                                              @Body RequestBody body);

    //领取优惠卷
    @POST("coupon/grantCoupon")
    Observable<DemoRespBean<CouponGrantInfoBean>> postCouponGrantInfo(@Header("extend") String headInfoModelToString,
                                                                      @Body RequestBody body);


    //    搜索相关
    @POST("system/searchGoods")
    Observable<DemoRespBean<List<GoodsInfoBean>>> postSearchInfo(@Header("extend") String headInfoModelToString,
                                                                 @Body RequestBody body);

    @POST("system/popularGoods")
    Observable<DemoRespBean<List<String>>> postSearchPopularGoodsList(@Header("extend") String headInfoModelToString);


    //    个人信息相关
//    用户信息
    @POST("member/memberInfo")
    Observable<DemoRespBean<UserInfoBean>> postUserInfo(@Header("extend") String headInfoModelToString);

    //    我的收益统计
    @POST("member/balanceStat")
    Observable<DemoRespBean<BalanceStatInfoBean>> postBalanceStat(@Header("extend") String headInfoModelToString);

    //    收益转余额
    @POST("member/rateToBalance")
    Observable<DemoRespBean> postRateToBalance(@Header("extend") String headInfoModelToString);

    //    收益提现
    @POST("member/applyMoney")
    Observable<DemoRespBean> postApplyMoney(@Header("extend") String headInfoModelToString,
                                            @Body RequestBody body);

    //    粉丝列表
    @POST("member/memberFans")
    Observable<DemoRespBean<List<FensInfoBean>>> postMemberFans(@Header("extend") String headInfoModelToString,
                                                                @Body RequestBody body);

    //    收益记录列表
    @POST("member/moneyRecordList")
    Observable<DemoRespBean<List<MoneyRecordListInfoBean>>> postMoneyRecordList(@Header("extend") String headInfoModelToString,
                                                                                @Body RequestBody body);

    //    提现记录列表
    @POST("member/applyMoneyList")
    Observable<DemoRespBean<List<MoneyRecordApplyListInfoBean>>> postMoneyRecordApplyList(@Header("extend") String headInfoModelToString,
                                                                                          @Body RequestBody body);

    //    生成用户二维码
    @POST("member/wxaqrcode")
    Observable<DemoRespBean<String>> postCreatWxaqrcode(@Header("extend") String headInfoModelToString);


//    商品相关

    //    商品列表
    @POST("goods/goodsList")
    Observable<DemoRespBean<List<GoodsInfoBean>>> postGoodsList(@Header("extend") String headInfoModelToString,
                                                                @Body RequestBody body);

    //    预售商品列表
    @POST("goods/presellGoodsList")
    Observable<DemoRespBean<List<GoodsInfoBean>>> postPresellGoodsList(@Header("extend") String headInfoModelToString,
                                                                       @Body RequestBody body);

    // 首页商品列表
    @POST("goods/homePageGoodsList")
    Observable<DemoRespBean<List<GoodsInfoBean>>> postHomePageGoodsList(@Header("extend") String headInfoModelToString,
                                                                        @Body RequestBody body);

    //    商品详情
    @POST("goods/goodsDetail")
    Observable<DemoRespBean<OutInfoBean<GoodsDetailInfoBean>>> postGoodsDetail(@Header("extend") String headInfoModelToString,
                                                                               @Body RequestBody body);

    //    商品规格列表
    @POST("goods/goodsItem")
    Observable<DemoRespBean<GoodsSpecInfoBean>> postGoodsItem(@Header("extend") String headInfoModelToString,
                                                              @Body RequestBody body);

    //    订单相关
    @POST("order/orderNum")
    Observable<DemoRespBean<OrderNumberInfoBean>> postOrderNumber(@Header("extend") String headInfoModelToString);

    @POST("order/orderList")
    Observable<DemoRespBean<List<OrderListInfoBean>>> postOrderList(@Header("extend") String headInfoModelToString,
                                                                    @Body RequestBody body);

    @POST("order/refundList")
    Observable<DemoRespBean<List<TkOrderInfoBean>>> postTkOrderList(@Header("extend") String headInfoModelToString,
                                                                    @Body RequestBody body);

    @POST("order/orderDetail")
    Observable<DemoRespBean<OrderDetailInfoBean>> postOrderDetail(@Header("extend") String headInfoModelToString,
                                                                  @Body RequestBody body);

    @POST("order/cancelOrder")
    Observable<DemoRespBean> postOrderCancel(@Header("extend") String headInfoModelToString,
                                             @Body RequestBody body);

    // 确认收货
    @POST("order/receiveOrder")
    Observable<DemoRespBean> postOrderReceive(@Header("extend") String headInfoModelToString,
                                              @Body RequestBody body);

    // 查看物流
    @POST("order/expressPs")
    Observable<DemoRespBean<ExpressPsInfoBean>> postOrderExpress(@Header("extend") String headInfoModelToString,
                                                                 @Body RequestBody body);

    // 删除订单
    @POST("order/deleteOrder")
    Observable<DemoRespBean> postOrderDelete(@Header("extend") String headInfoModelToString,
                                             @Body RequestBody body);


    // 提交订单
    @POST("order/submitOrder")
    Observable<DemoRespBean<String>> postOrderSubmit(@Header("extend") String headInfoModelToString,
                                                     @Body RequestBody body);

    // 购买支付
    @POST("order/payment")
    Observable<DemoRespBean<WechetPayInfo>> postPayment(@Header("extend") String headInfoModelToString,
                                                        @Body RequestBody body);

    // 支付订单
    @POST("order/payInfoOrder")
    Observable<DemoRespBean<OrderDetailInfoBean>> postPayInfoOrder(@Header("extend") String headInfoModelToString,
                                                                   @Body RequestBody body);

    // 配送费列表
    @POST("order/distribution")
    Observable<DemoRespBean<DistributionInfoBean>> postDistribution(@Header("extend") String headInfoModelToString,
                                                                          @Body RequestBody body);

    // 配送费列表
    @POST("order/applyInvoice")
    Observable<DemoRespBean> postApplyInvoice(@Header("extend") String headInfoModelToString,
                                              @Body RequestBody body);

    // 申请退款
    @POST("order/applyRefund")
    Observable<DemoRespBean> postApplyRefund(@Header("extend") String headInfoModelToString,
                                             @Body RequestBody body);

    // 退款详情
    @POST("order/refundDetail")
    Observable<DemoRespBean<RefundDetailInfoBean>> postRefundDetial(@Header("extend") String headInfoModelToString,
                                                                    @Body RequestBody body);


//    评论

    //    评论列表
    @POST("order/commentList")
    Observable<DemoRespBean<List<EvaluateInfoBean>>> postCommentList(@Header("extend") String headInfoModelToString,
                                                                     @Body RequestBody body);

    //    添加评论
    @POST("order/addComment")
    Observable<DemoRespBean> postAddComment(@Header("extend") String headInfoModelToString,
                                            @Body RequestBody body);

    //    购物车相关
    @POST("cart/addCart")
    Observable<DemoRespBean> postCartAdd(@Header("extend") String headInfoModelToString,
                                         @Body RequestBody body);

    @POST("cart/cartList")
    Observable<DemoRespBean<List<CartShopInfoBean>>> postCartList(@Header("extend") String headInfoModelToString,
                                                                  @Body RequestBody body);

    @POST("cart/deleteCart")
    Observable<DemoRespBean> postCartDelete(@Header("extend") String headInfoModelToString,
                                            @Body RequestBody body);

    @POST("cart/selectCart")
    Observable<DemoRespBean> postCartSelect(@Header("extend") String headInfoModelToString,
                                            @Body RequestBody body);

    @POST("cart/updateCartNum")
    Observable<DemoRespBean> postCartUpdateNum(@Header("extend") String headInfoModelToString,
                                               @Body RequestBody body);

    @POST("cart/synchroCart")
    Observable<DemoRespBean> postSynchorCart(@Header("extend") String headInfoModelToString,
                                             @Body RequestBody body);


// 收货地址信息相关

    @POST("address/editAddress")
    Observable<DemoRespBean<AddressInfoBean>> postAddressEdit(@Header("extend") String headInfoModelToString,
                                                              @Body RequestBody body);

    @POST("address/deleteAddress")
    Observable<DemoRespBean> postAddressDelete(@Header("extend") String headInfoModelToString,
                                               @Body RequestBody body);

    @POST("address/addressList")
    Observable<DemoRespBean<List<AddressInfoBean>>> postAddressList(@Header("extend") String headInfoModelToString,
                                                                    @Body RequestBody body);

    @POST("address/addressDetail")
    Observable<DemoRespBean<AddressInfoBean>> postAddressDetail(@Header("extend") String headInfoModelToString,
                                                                @Body RequestBody body);

    @POST("address/editDefault")
    Observable<DemoRespBean> postAddressSetDefault(@Header("extend") String headInfoModelToString,
                                                   @Body RequestBody body);

    //    会员充值信息相关
//    余额使用记录
    @POST("pay/purchaseRecordList")
    Observable<DemoRespBean<List<PayOrderInfoBean2>>> postPurchaseRecordList(@Header("extend") String headInfoModelToString,
                                                                             @Body RequestBody body);

    //    充值规则列表
    @POST("pay/payList")
    Observable<DemoRespBean<List<PayListInfoBean>>> postPayList(@Header("extend") String headInfoModelToString,
                                                                @Body RequestBody body);

    // 生成充值订单
    @POST("pay/payOrder")
    Observable<DemoRespBean<PayOrderInfoBean>> postPayOrder(@Header("extend") String headInfoModelToString,
                                                            @Body RequestBody body);

    // 充值支付
    @POST("pay/payOrderPayMent")
    Observable<DemoRespBean<WechetPayInfo>> postPayOrderPayMent(@Header("extend") String headInfoModelToString,
                                                                @Body RequestBody body);

    //    查询充值记录
    @POST("pay/payRecordList")
    Observable<DemoRespBean<List<PayOrderInfoBean>>> postPayRecordList(@Header("extend") String headInfoModelToString,
                                                                       @Body RequestBody body);


    //    统计信息接口
//    分销收益排行
    @POST("rank/balanceRanking")
    Observable<DemoRespBean<OutInfoBean<List<ShouyRankingInfoBean>>>> postBalanceRankingList(@Header("extend") String headInfoModelToString,
                                                                                             @Body RequestBody body);
//    预售商品购买记录
    @POST("rank/purchaseRrecord")
    Observable<DemoRespBean<GmrOutInfoBean>> postPurchaseRrecord(@Header("extend") String headInfoModelToString,
                                                                 @Body RequestBody body);
//  签到展示
    @POST("signin/signin")
    Observable<DemoRespBean<SigninInfoBean>> postSigninInfo(@Header("extend") String headInfoModelToString,
                                                            @Body RequestBody body);
//  签到
    @POST("signin/signinOperate")
    Observable<DemoRespBean<String>> postSigninOperate(@Header("extend") String headInfoModelToString,
                                                            @Body RequestBody body);
//  签到列表
    @POST("signin/signinRecord")
    Observable<DemoRespBean<List<SigninRecordBean>>> postSigninRecord(@Header("extend") String headInfoModelToString,
                                                                @Body RequestBody body);

    /**
     * 获取微信的AccessTolen
     *
     * @param path
     * @return
     */
    @GET
    Observable<WechatCodeBean> wxAccesstoken(@Url String path);

    @GET
    Observable<WechatUserInfo> wxUserInfo(@Url String path);

    @GET
    Observable<TimeLongInfo> getTimeLong(@Url String path);


//    @POST
//    Observable<BufferedInputStream> wxXcxEwm(@Url String path, @Body RequestBody body);

}
