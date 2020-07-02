package com.work.doctor.fruits.assist;

import com.t.databaselib.bean.ShopInfoBean;
import com.t.httplib.yigou.bean.resp.AddressInfoBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DemoConstant {

    //用户id
    public static int userId = -1;
    //0:游客；1会员；2批发商；
    public static int userStatus = 0;

    public static int userApprove = 0;

    //是否改变店铺
    public static boolean isChangeShop;
    public static boolean refershOne;
    public static boolean refershTwo;
    public static boolean refershThree;

    public static boolean isSignin = false;

//    public static boolean refershFour;

    //是否刷新店铺信息
    public static boolean isRefershShopInfo = false;


//    public static boolean isExit = false;
//    店铺的信息
    public static ShopInfoBean shopInfoBean;

    public static BigDecimal balance = BigDecimal.ZERO;

    //是否改变数据库
    public static boolean isChangeDatabase = false;
    //是否已买购物车商品
    public static boolean isBuyShoppingCartGoods = false;

    //已经选择的送货地址
    public static AddressInfoBean addressInfoBean;

    //支付的订单id
    public static String zfOrderId = "";
    //默认为-1，0代表充值，1代表订单支付
    public static int orderType = -1;
    //充值金额
    public static BigDecimal czBlance = BigDecimal.ZERO;

    //购买的商品信息
    public static List<JgPurchaseBean> jgPurchaseBeanList = new ArrayList<>();

    //============================================ 微信参数 ========================================
    public static final String wx_app_id = "wxe73ebab3e07e1b57";
    public static final String wx_app_screet = "4553fafa933be5cfebb70bc39d07992d";


    //微信的应用授权作用域
    public static final String scope = "snsapi_userinfo";
    //用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
    public static final String state_login = "com.work.doctor.fruits:state_login";
    public static final String state_er_wei_ma = "com.work.doctor.fruits:state_er_wei_ma";



    //============================================share对象：用户的常量key========================================
    public static final String user_token = "user_token";
    public static final String user_first = "user_first";
    public static final String user_id = "user_id";
    public static final String user_status = "user_status";
    public static final String user_approve = "user_approve";
    public static final String user_phone = "user_phone";
    public static final String user_onepage_dialog_remind = "user_onepage_dialog_remind";


    //============================================ 请求参数 ========================================
    public static final String POST_NORMAL = "请求中...";


}
