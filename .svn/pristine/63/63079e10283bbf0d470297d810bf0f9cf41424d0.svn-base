package com.work.doctor.fruits.activity.vip;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.t.httplib.yigou.DObserver;
import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.bean.req.ReqBalanceRankingInfo;
import com.t.httplib.yigou.bean.resp.OutInfoBean;
import com.t.httplib.yigou.bean.resp.ShouyRankingInfoBean;
import com.trjx.tbase.activity.InitTitleActivity;
import com.trjx.tlibs.uils.GlideUtile;
import com.trjx.tlibs.views.TListView;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.SyRankingAdapter;

import java.text.DecimalFormat;
import java.util.List;


/**
 *  收益列表页面
 */
public class MyEarningRankingActivity extends InitTitleActivity {

    private ScrollView scrollView;

    private RelativeLayout fristRl, secondRl, thirdRl;

    private ImageView mFristImg;
    private TextView mFristName;
    private TextView mFristPrice;
    private ImageView mSecondImg;
    private TextView mSecondName;
    private TextView mSecondPrice;
    private ImageView mThirdImg;
    private TextView mThirdName;
    private TextView mThirdPrice;


    private TListView tListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouy_ranking);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("收益排名", true);
        scrollView = findViewById(R.id.scrollview);
        fristRl = findViewById(R.id.frist_rl);
        secondRl = findViewById(R.id.second_rl);
        thirdRl = findViewById(R.id.third_rl);

        mFristImg = findViewById(R.id.frist_img);
        mFristName = findViewById(R.id.frist_name);
        mFristPrice = findViewById(R.id.frist_price);
        mSecondImg = findViewById(R.id.second_img);
        mSecondName = findViewById(R.id.second_name);
        mSecondPrice = findViewById(R.id.second_price);
        mThirdImg = findViewById(R.id.third_img);
        mThirdName = findViewById(R.id.third_name);
        mThirdPrice = findViewById(R.id.third_price);

        tListView = findViewById(R.id.tlistview);

        getListData();

    }

    public void getListData() {
        ReqBalanceRankingInfo info = new ReqBalanceRankingInfo();
        info.setSort("all");
        new DemoModel().requestBalanceRankingList(info, new DObserver<OutInfoBean<List<ShouyRankingInfoBean>>>(this) {
            @Override
            public void onTPostSuccess(OutInfoBean<List<ShouyRankingInfoBean>> listOutInfoBean) {
                try {
                    bindData(listOutInfoBean.getModel());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void bindData(List<ShouyRankingInfoBean> infoList) throws Exception {

        if (infoList != null || infoList.size() > 0) {
            int size = infoList.size();
            if (size > 3) {
                SyRankingAdapter listAadapter = new SyRankingAdapter(context, infoList.subList(3, infoList.size()));
                tListView.setAdapter(listAadapter);
            }

            DecimalFormat df = new DecimalFormat("0.00");
            if (size > 3) {
                size = 3;
            }
            switch (size) {
                case 3:
                    thirdRl.setVisibility(View.VISIBLE);

                    ShouyRankingInfoBean listBean = infoList.get(2);
                    mThirdName.setText(listBean.getNickName());
                    GlideUtile.bindImageViewRound(context,listBean.getAvatarUrl(),R.mipmap.t_head,mThirdImg);
                    mThirdPrice.setText("￥ " + df.format(listBean.getSumNum()));

                case 2:
                    secondRl.setVisibility(View.VISIBLE);

                    ShouyRankingInfoBean listBean2 = infoList.get(1);
                    mSecondName.setText(listBean2.getNickName());
                    GlideUtile.bindImageViewRound(context,listBean2.getAvatarUrl(),R.mipmap.t_head,mSecondImg);
                    mSecondPrice.setText("￥ " + df.format(listBean2.getSumNum()));

                case 1:

                    ShouyRankingInfoBean listBean3 = infoList.get(0);
                    mFristName.setText(listBean3.getNickName());
                    GlideUtile.bindImageViewRound(context,listBean3.getAvatarUrl(),R.mipmap.t_head,mFristImg);
                    mFristPrice.setText("￥ " + df.format(listBean3.getSumNum()));
                    break;
            }


        } else {
            scrollView.setVisibility(View.GONE);
        }


    }

}
