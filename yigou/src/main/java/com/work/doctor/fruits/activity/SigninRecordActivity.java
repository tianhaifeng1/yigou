package com.work.doctor.fruits.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.t.httplib.yigou.bean.req.ReqShopInfo2;
import com.t.httplib.yigou.bean.resp.SigninRecordBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.trjx.tlibs.uils.Logger;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.SigninRecordAdapter;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.util.List;

public class SigninRecordActivity extends DemoMVPActivity<SigninRecordView, SigninRecordPresenter>
        implements SigninRecordView, TRecyclerViewListenter {

    private TRecyclerModule recyclerModule;

    private ReqShopInfo2 info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_record);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("签到记录", true);

        info = new ReqShopInfo2();

        SigninRecordAdapter mSigninRecordAdapter = new SigninRecordAdapter(null);
        recyclerModule = new TRecyclerModule.Builder(context)
                .setLayoutManager(new LinearLayoutManager(context))
                .setPage(1)
                .setPageSize(20)
                .createAdapter(mSigninRecordAdapter)
                .creat(rootView);
        recyclerModule.setDefImg(R.mipmap.default_goodslist);
        recyclerModule.setDefText("暂无记录");

        int shopId = DemoConstant.shopInfoBean == null ? -1 : DemoConstant.shopInfoBean.getShopId();
        if (shopId > -1) {
            // 初始化参数
            info.setShopId(shopId+"");
            info.setPage(1);
            info.setPageSize(recyclerModule.getPageSize());
        }else{
            Logger.t("shopid 异常");
        }

        getRecyclerListData();
    }



    @Override
    protected SigninRecordPresenter initPersenter() { return new SigninRecordPresenter(this); }

    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        info.setPage(recyclerModule.getPage());
        info.setPageSize(recyclerModule.getPageSize());
        getPresenter().getSigninRecord(info);
    }


    @Override
    public void getSigninRecordSuccess(List<SigninRecordBean> list) {
        recyclerModule.setRefreshing(false);
        recyclerModule.bindListData(list);
    }
}
