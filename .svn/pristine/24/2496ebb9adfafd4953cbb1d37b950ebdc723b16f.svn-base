package com.work.doctor.fruits.activity;

import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.GreenDaoAssist;
import com.t.httplib.yigou.DObserver2;
import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.bean.req.ReqSynchroCartInfo;
import com.t.httplib.yigou.bean.req.ReqSynchroCartInfoOut;
import com.trjx.tbase.activity.NavBottomActivity;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.ToastUtil2;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.fragment.MainFourFragment;
import com.work.doctor.fruits.activity.fragment.MainOneFragment;
import com.work.doctor.fruits.activity.fragment.MainThreeFragment;
import com.work.doctor.fruits.activity.fragment.MainTwoFragment;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoApplication;

import java.util.ArrayList;
import java.util.List;

public class MainNavActivity extends NavBottomActivity{

    public static String name = "";
    public static String getprovince = "";
    public static String getcity = "";
    public static String getcounty = "";
    public static double la = 0;
    public static double lo =  0;

    public GreenDaoAssist greenDaoAssist;

    @Override
    protected void initView() {
        super.initView();

        greenDaoAssist = new GreenDaoAssist(((DemoApplication) getApplication()).databaseAssist);

//        smoothScroll = false;
//        reselectedRefresh = true;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_nav_bottom;
    }

    @Override
    protected void initFragmentData() {
        fragmentList.add(new MainOneFragment());
        fragmentList.add(new MainTwoFragment());
        fragmentList.add(new MainThreeFragment());
        fragmentList.add(new MainFourFragment());
    }

    @Override
    public void skipTab(int position) {
        if(position != 2){
            synchorShoppingCart();
        }
        super.skipTab(position);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId != R.id.nav_bottom_item3) {
            synchorShoppingCart();
        }
        return super.onNavigationItemSelected(item);
    }

    private void synchorShoppingCart(){
        // 同步改变后的本地数据
        if(DemoConstant.isChangeDatabase){
            List<DatabaseGoodsInfo> shopInfoList = greenDaoAssist.queryAllGoods();
            if (shopInfoList != null && shopInfoList.size() > 0) {
                DemoConstant.isChangeDatabase = false;
                ReqSynchroCartInfoOut infoOut = new ReqSynchroCartInfoOut();
                List<ReqSynchroCartInfo> list = new ArrayList<>();
                for (DatabaseGoodsInfo info : shopInfoList) {
                    ReqSynchroCartInfo cartInfo = new ReqSynchroCartInfo();
                    cartInfo.setGoodsId(info.getGoodsId());
                    cartInfo.setAttrStrId(info.getSpecId());
                    cartInfo.setTotalNum(info.getGoodsNumber());
                    list.add(cartInfo);
                }
                infoOut.setGoods(list);
                // 同步本地数据
                new DemoModel().requestSynchorCart(infoOut, new DObserver2(this) {
                    @Override
                    public void onTPostSuccess() {
                        Logger.t("同步数据完成");
                    }
                });
            }
        }

    }


    @Override
    protected int initTabMenu() {
        return R.menu.navigation_main;
    }

    @Override
    protected boolean backBefore() {
        ToastUtil2.showToast(context, "再次点击退出系统");
        activityManager.exitApp(true);
        return false;
    }

}
