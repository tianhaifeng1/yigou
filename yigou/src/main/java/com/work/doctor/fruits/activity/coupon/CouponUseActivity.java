package com.work.doctor.fruits.activity.coupon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.tabs.TabLayout;
import com.t.httplib.yigou.bean.resp.CouponInfoBean;
import com.trjx.tbase.activity.InitTitleActivity;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.CouponUserAdapter;
import com.work.doctor.fruits.activity.myrecycler.MyRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CouponUseActivity extends InitTitleActivity
        implements MyRecyclerView, BaseQuickAdapter.OnItemChildClickListener, TRecyclerViewListenter {

    private TabLayout tabLayout;
    private TextView yhjText;
    private TextView yhjAffirm;

    private TRecyclerModule recyclerModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_use);
    }


    private CouponUserAdapter couponUserAdapter;

    private int status = 0;
    private ArrayList<CouponInfoBean> unyhjList;
    private ArrayList<CouponInfoBean> yhjList;

    private int psjIndex = -1;
    private int mjjIndex = -1;


    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("我的优惠券", true);

        tabLayout = findViewById(R.id.yhj_tablayout);
        yhjText = findViewById(R.id.use_yhj_text);
        yhjAffirm = findViewById(R.id.use_yhj_affirm);

        Intent intent = getIntent();

        unyhjList = (ArrayList<CouponInfoBean>) intent.getSerializableExtra("nolist");
        yhjList = (ArrayList<CouponInfoBean>) intent.getSerializableExtra("list");
        mjjIndex = intent.getIntExtra("mjjindex", -1);
        psjIndex = intent.getIntExtra("psjindex", -1);

        int selectNum = 0;
        if (mjjIndex > -1) {
            yhjList.get(mjjIndex).setSelect(true);
            selectNum++;
        }
        if (psjIndex > -1) {
            yhjList.get(psjIndex).setSelect(true);
            selectNum++;
        }
        yhjText.setText("已选择 " + selectNum + " 个优惠券");

        yhjAffirm.setOnClickListener(v -> {
            Intent intent1 = new Intent();
            intent1.putExtra("mjjindex", mjjIndex);
            intent1.putExtra("psjindex", psjIndex);
            setResult(200, intent1);
            finish();
        });

        couponUserAdapter = new CouponUserAdapter(null);
        recyclerModule = new TRecyclerModule.Builder(context)
                .setLayoutManager(new LinearLayoutManager(context))
                .createAdapter(couponUserAdapter)
                .setPageSize(1000)
                .setTRecyclerViewListenter(this)
                .creat(rootView);
        recyclerModule.setRefreshing(false);
        recyclerModule.setDefImg(R.mipmap.default_couponlist);
        recyclerModule.setDefText("没有优惠券数据");
        recyclerModule.getOView().setPadding(0, 0, 0, context.getResources().getDimensionPixelOffset(R.dimen.dp300));
        recyclerModule.setSwipeRefreshEnable(false);

        couponUserAdapter.setOnItemChildClickListener(this);

        getTabTitle();

//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < titleStr.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titleStr.get(i)).setTag(i));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index = tab.getPosition();
                if (index == 0) {
                    status = 0;
                } else if (index == 1) {
                    status = 1;
                }
                couponUserAdapter.setState(status);
                recyclerModule.setPage(1);
                getRecyclerListData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        getRecyclerListData();
    }

    private List<String> titleStr;

    private void getTabTitle() {
        titleStr = new ArrayList<>();
        titleStr.add("可用优惠券");
        titleStr.add("不可用优惠券");
    }


    @Override
    public void getListDataSuccess(List<?> list) {
        recyclerModule.bindListData(list);
    }

//    @Override
//    public void tPostFinish(int code) {
//        super.tPostFinish(code);
//        recyclerModule.setRefreshing(false);
//    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

        if (status == 0) {
            CouponInfoBean bean = (CouponInfoBean) adapter.getData().get(position);
            int ids = view.getId();
            switch (ids) {
                case R.id.item_coupon_icon_select:

                    int type = bean.getType();
                    boolean select = bean.isSelect();
                    if (type == 6) {
                        //配送卷
                        if (select) {
                            psjIndex = -1;
                        } else {

                            if (psjIndex > -1) {
                                CouponInfoBean beanPsj = yhjList.get(psjIndex);
                                beanPsj.setSelect(false);
                                adapter.notifyItemChanged(psjIndex);
                            }
                            psjIndex = position;
                        }

                    } else {
                        if (select) {
                            mjjIndex = -1;
                        } else {
                            if (mjjIndex > -1) {
                                CouponInfoBean beanMjj = yhjList.get(mjjIndex);
                                beanMjj.setSelect(false);
                                adapter.notifyItemChanged(mjjIndex);
                            }
                            mjjIndex = position;
                        }
                    }

                    bean.setSelect(!select);
                    int selectNum = 0;
                    if (psjIndex > -1) {
                        selectNum++;
                    }
                    if (mjjIndex > -1) {
                        selectNum++;
                    }
                    yhjText.setText("已选择 " + selectNum + " 个优惠券");
                    adapter.notifyItemChanged(position);

                    break;
            }
        }

    }

    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        couponUserAdapter.setState(status);
        if (status == 0) {
            recyclerModule.bindListData(yhjList);
        } else if (status == 1) {
            recyclerModule.bindListData(unyhjList);
        }
    }
}
