package com.work.doctor.fruits.assist;

import android.content.Context;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.analytics.android.api.LoginEvent;
import cn.jiguang.analytics.android.api.PurchaseEvent;

public class JgUtils {

    /**
     * 登陆事件
     * @param context
     * @param loginMethod
     * @param loginSuccess
     */
    public static void loginEvent(Context context,String loginMethod , boolean loginSuccess){
        LoginEvent cEvent = new LoginEvent(loginMethod,loginSuccess);
        JAnalyticsInterface.onEvent(context, cEvent);
    }

//    /**
//     * 购买事件
//     * @param context
//     * @param purchaseGoodsid
//     * @param purchaseGoodsName
//     * @param purchasePrice
//     * @param purchaseSuccess
//     * @param purchaseGoodsType
//     * @param purchaseGoodsCount
//     */
//    public static void purchaseEvent(Context context,
//                                     String purchaseGoodsid ,
//                                     String purchaseGoodsName ,
//                                     double purchasePrice ,
//                                     boolean purchaseSuccess,
//                                     String purchaseGoodsType,
//                                     int purchaseGoodsCount
//                                     ){
//        purchaseEvent(context, purchaseGoodsid, purchaseGoodsName, purchasePrice, purchaseSuccess,
//                Currency.CNY, purchaseGoodsType, purchaseGoodsCount);
//    }
//    public static void purchaseEvent(Context context,
//                                     String purchaseGoodsid ,
//                                     String purchaseGoodsName ,
//                                     double purchasePrice ,
//                                     boolean purchaseSuccess,
//                                     Currency purchaseCurrency,
//                                     String purchaseGoodsType,
//                                     int purchaseGoodsCount
//                                     ){
//        PurchaseEvent pEvent = new PurchaseEvent(purchaseGoodsid,
//                purchaseGoodsName,
//                purchasePrice,
//                purchaseSuccess, purchaseCurrency,purchaseGoodsType,purchaseGoodsCount);
//        JAnalyticsInterface.onEvent(context, pEvent);
//    }

    /**
     * 购买事件
     * @param context
     * @param jgPurchaseBean
     */
    public static void purchaseEvent(Context context, JgPurchaseBean jgPurchaseBean){
        PurchaseEvent pEvent = new PurchaseEvent(jgPurchaseBean.getPurchaseGoodsid(),
                jgPurchaseBean.getPurchaseGoodsName(),
                jgPurchaseBean.getPurchasePrice(),
                jgPurchaseBean.isPurchaseSuccess(),
                jgPurchaseBean.getPurchaseCurrency(),
                jgPurchaseBean.getPurchaseGoodsType(),
                jgPurchaseBean.getPurchaseGoodsCount());
        JAnalyticsInterface.onEvent(context, pEvent);
    }

}
