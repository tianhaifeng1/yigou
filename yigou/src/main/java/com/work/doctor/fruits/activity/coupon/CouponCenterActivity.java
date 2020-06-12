package com.work.doctor.fruits.activity.coupon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.httplib.yigou.bean.resp.CouponCenterInfoBean;
import com.t.httplib.yigou.bean.resp.CouponGrantInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.MainNavActivity;
import com.work.doctor.fruits.activity.adapter.CouponCenterAdapter;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.util.List;

public class CouponCenterActivity extends DemoMVPActivity<CouponCenterView,CouponCenterPresenter>
        implements CouponCenterView, BaseQuickAdapter.OnItemChildClickListener, TRecyclerViewListenter {

    private TRecyclerModule recyclerModule;

    private CouponCenterAdapter couponCenterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_center);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("领券中心",true);

        couponCenterAdapter = new CouponCenterAdapter(null);
        recyclerModule = new TRecyclerModule.Builder(context)
                .setLayoutManager(new LinearLayoutManager(context))
                .createAdapter(couponCenterAdapter)
                .setTRecyclerViewListenter(this)
                .creat(rootView);
        recyclerModule.setRefreshing(true);
        recyclerModule.setDefImg(R.mipmap.default_couponlist);
        recyclerModule.setDefText("还没有优惠券可以领取哦");
        recyclerModule.getOView().setPadding(0, 0, 0, context.getResources().getDimensionPixelOffset(R.dimen.dp300));
        couponCenterAdapter.setOnItemChildClickListener(this);
        getRecyclerListData();
    }

    @Override
    protected CouponCenterPresenter initPersenter() {
        return new CouponCenterPresenter(this);
    }

    @Override
    public void getCouponGrantSuccess(CouponGrantInfoBean infoBean, int position) {

        if (infoBean != null) {
            CouponCenterInfoBean centerInfoBean = couponCenterAdapter.getData().get(position);
            centerInfoBean.setGrantStatus(1);
            centerInfoBean.setIsOver(infoBean.getIsOver());
            centerInfoBean.setIsStart(infoBean.getIsStart());
            couponCenterAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void getListDataSuccess(List<?> list) {
        recyclerModule.setRefreshing(false);
        recyclerModule.bindListData(list);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        CouponCenterInfoBean infoBean = (CouponCenterInfoBean) adapter.getData().get(position);

        int ids = view.getId();
        switch (ids) {
            case R.id.item_coupon_btn:
                int grantStatus = infoBean.getGrantStatus();
                if (grantStatus == 0) {
                    //立即领取
                    showDialog("领取中...");
                    getPresenter().getCouponGrant(infoBean.getId(),position);
                } else if (grantStatus == 1) {
                    //去使用
                    Intent intent_home = new Intent(context, MainNavActivity.class);
                    intent_home.putExtra("position", 1);
                    startActivity(intent_home);
                }
                break;
        }
    }

    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        getPresenter().getCouponListData(recyclerModule.getPage(), recyclerModule.getPageSize());
    }

    @Override
    protected boolean backBefore() {
        setResult(200);
        return super.backBefore();
    }

    @Override
    public void onClickBack(View view) {
        setResult(200);
        super.onClickBack(view);
    }
}
