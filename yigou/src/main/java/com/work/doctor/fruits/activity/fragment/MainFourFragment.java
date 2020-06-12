package com.work.doctor.fruits.activity.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.resp.OrderNumberInfoBean;
import com.t.httplib.yigou.bean.resp.UserInfoBean;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.FensActivity;
import com.work.doctor.fruits.activity.SettingActivity;
import com.work.doctor.fruits.activity.SharePageActivity;
import com.work.doctor.fruits.activity.SigninActivity;
import com.work.doctor.fruits.activity.address.AddressManageActivity;
import com.work.doctor.fruits.activity.coupon.CouponActivity;
import com.work.doctor.fruits.activity.order.OrderListActivity;
import com.work.doctor.fruits.activity.order.TkOrderActivity;
import com.work.doctor.fruits.activity.vip.MVipActivity;
import com.work.doctor.fruits.activity.vip.MyEarningActivity;
import com.work.doctor.fruits.activity.vip.MyEarningRankingActivity;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.DemoUtils;
import com.work.doctor.fruits.base.DemoMVPFragment;

public class MainFourFragment extends DemoMVPFragment<MainFourView, MainFourPresenter>
        implements MainFourView, View.OnClickListener {

    private LinearLayout mFmMainFourLoginLl;
    private ImageView mFmMainFourHead;
    private TextView mFmMainFourName;
    private ImageView mFmMainFourMember;
    private TextView mFmMainFourMemberText;
    private TextView mFmMainFourOrderAll;
    private TextView mFmMainFourOrderDzfNum;
    private RelativeLayout mFmMainFourOrderDzf;
    private TextView mFmMainFourOrderDfhNum;
    private RelativeLayout mFmMainFourOrderDfh;
    private TextView mFmMainFourOrderDshNum;
    private RelativeLayout mFmMainFourOrderDsh;
    private TextView mFmMainFourOrderDpjNum;
    private RelativeLayout mFmMainFourOrderDpj;
    private RelativeLayout mFmMainFourOrderTk;
    private RelativeLayout mFmMainFourRlAddress;
    private RelativeLayout mFmMainFourRlVip;
    private RelativeLayout mFmMainFourRlYaoqing;
    private RelativeLayout mFmMainFourRlYhj;
    private RelativeLayout mFmMainFourRlFens;
    private RelativeLayout mFmMainFourRlShouyi;
    private RelativeLayout mFmMainFourRlShouyiSort;
    private RelativeLayout mFmMainFourRlSetting;
    private RelativeLayout mFmMainFourSignin;

    @Override
    protected int initLayout() {
        return R.layout.fragment_main_four;
    }

    @Override
    protected void initFragmentView(View view) {

        mFmMainFourLoginLl = view.findViewById(R.id.fm_main_four_login_ll);
        mFmMainFourHead = view.findViewById(R.id.fm_main_four_head);
        mFmMainFourName = view.findViewById(R.id.fm_main_four_name);
        mFmMainFourMember = view.findViewById(R.id.fm_main_four_member);
        mFmMainFourMemberText = view.findViewById(R.id.fm_main_four_member_text);
        mFmMainFourOrderAll = view.findViewById(R.id.fm_main_four_order_all);
        mFmMainFourOrderDzfNum = view.findViewById(R.id.fm_main_four_order_dzf_num);
        mFmMainFourOrderDzf = view.findViewById(R.id.fm_main_four_order_dzf);
        mFmMainFourOrderDfhNum = view.findViewById(R.id.fm_main_four_order_dfh_num);
        mFmMainFourOrderDfh = view.findViewById(R.id.fm_main_four_order_dfh);
        mFmMainFourOrderDshNum = view.findViewById(R.id.fm_main_four_order_dsh_num);
        mFmMainFourOrderDsh = view.findViewById(R.id.fm_main_four_order_dsh);
        mFmMainFourOrderDpjNum = view.findViewById(R.id.fm_main_four_order_dpj_num);
        mFmMainFourOrderDpj = view.findViewById(R.id.fm_main_four_order_dpj);
        mFmMainFourOrderTk = view.findViewById(R.id.fm_main_four_order_tk);
        mFmMainFourRlAddress = view.findViewById(R.id.fm_main_four_rl_address);
        mFmMainFourRlVip = view.findViewById(R.id.fm_main_four_rl_vip);
        mFmMainFourRlYaoqing = view.findViewById(R.id.fm_main_four_rl_yaoqing);
        mFmMainFourRlYhj = view.findViewById(R.id.fm_main_four_rl_yhj);
        mFmMainFourRlFens = view.findViewById(R.id.fm_main_four_rl_fens);
        mFmMainFourRlShouyi = view.findViewById(R.id.fm_main_four_rl_shouyi);
        mFmMainFourRlShouyiSort = view.findViewById(R.id.fm_main_four_rl_shouyi_sort);
        mFmMainFourRlSetting = view.findViewById(R.id.fm_main_four_rl_setting);
        mFmMainFourSignin = view.findViewById(R.id.fm_main_four_signin);

        mFmMainFourLoginLl.setOnClickListener(this);
        mFmMainFourOrderAll.setOnClickListener(this);
        mFmMainFourOrderDzf.setOnClickListener(this);
        mFmMainFourOrderDfh.setOnClickListener(this);
        mFmMainFourOrderDsh.setOnClickListener(this);
        mFmMainFourOrderDpj.setOnClickListener(this);
        mFmMainFourOrderTk.setOnClickListener(this);
        mFmMainFourSignin.setOnClickListener(this);

//        {"收货地址", "会员中心", "邀请有礼", "我的粉丝", "我的收益", "收益排名","我的优惠券",  "设置"};
        mFmMainFourRlAddress.setOnClickListener(this);
        mFmMainFourRlVip.setOnClickListener(this);
        mFmMainFourRlYaoqing.setOnClickListener(this);
        mFmMainFourRlYhj.setOnClickListener(this);
        mFmMainFourRlFens.setOnClickListener(this);
        mFmMainFourRlShouyi.setOnClickListener(this);
        mFmMainFourRlShouyiSort.setOnClickListener(this);
        mFmMainFourRlSetting.setOnClickListener(this);

    }

    @Override
    public void initData() {
        super.initData();
        if(YigouConstant.token.equals("")){

            mFmMainFourHead.setImageResource(R.mipmap.t_head);
            mFmMainFourName.setText("立即登录");
            initOrdeNumber(new OrderNumberInfoBean());
            GlideUtile.bindImageView(activity.context, R.mipmap.t_member_no, mFmMainFourMember);
            mFmMainFourMemberText.setText("游客");

            mFmMainFourRlYaoqing.setVisibility(View.VISIBLE);
            mFmMainFourRlFens.setVisibility(View.VISIBLE);
            mFmMainFourRlShouyi.setVisibility(View.VISIBLE);
            mFmMainFourRlShouyiSort.setVisibility(View.VISIBLE);
            mFmMainFourRlYhj.setVisibility(View.VISIBLE);
            mFmMainFourSignin.setVisibility(View.GONE);

        }else{

            getPresenter().getUserInfo();
            getPresenter().getOrderNumber();
        }

    }

    @Override
    protected MainFourPresenter initPersenter() {
        return new MainFourPresenter(this);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.fm_main_four_login_ll:
                //登陆
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                break;
            case R.id.fm_main_four_order_all:
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                //所有订单
                Intent intentAll = new Intent(activity.context, OrderListActivity.class);
                intentAll.putExtra("index", 0);
                activity.skipActivity(intentAll);
                break;
            case R.id.fm_main_four_order_dzf:
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                //待支付
                Intent intentDzf = new Intent(activity.context, OrderListActivity.class);
                intentDzf.putExtra("index", 1);
                activity.skipActivity(intentDzf);
                break;
            case R.id.fm_main_four_order_dfh:
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                //待发货
                Intent intentDfh = new Intent(activity.context, OrderListActivity.class);
                intentDfh.putExtra("index", 2);
                activity.skipActivity(intentDfh);
                break;
            case R.id.fm_main_four_order_dsh:
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                //待收货
                Intent intentDsh = new Intent(activity.context, OrderListActivity.class);
                intentDsh.putExtra("index", 3);
                activity.skipActivity(intentDsh);
                break;
            case R.id.fm_main_four_order_dpj:
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                //待评价
                Intent intentDpj = new Intent(activity.context, OrderListActivity.class);
                intentDpj.putExtra("index", 4);
                activity.skipActivity(intentDpj);
                break;
            case R.id.fm_main_four_order_tk:
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                //退款
                Intent intentTk = new Intent(activity.context, TkOrderActivity.class);
                activity.skipActivity(intentTk);
                break;
            case R.id.fm_main_four_rl_address:
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                //收货地址
                activity.skipActivity(AddressManageActivity.class);
                break;
            case R.id.fm_main_four_rl_vip:
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                //会员中心
                activity.skipActivity(MVipActivity.class);
                break;
            case R.id.fm_main_four_rl_yaoqing:
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                //邀请有礼
                activity.skipActivity(SharePageActivity.class);
                break;
            case R.id.fm_main_four_rl_yhj:
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                //我的优惠劵
                activity.skipActivity(CouponActivity.class);
                break;
            case R.id.fm_main_four_rl_fens:
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                //我的粉丝
                activity.skipActivity(FensActivity.class);
                break;
            case R.id.fm_main_four_rl_shouyi:
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                //我的收益
                activity.skipActivity(MyEarningActivity.class);
                break;
            case R.id.fm_main_four_rl_shouyi_sort:
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                //收益排名
                activity.skipActivity(MyEarningRankingActivity.class);
                break;
            case R.id.fm_main_four_rl_setting:
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                //设置
                activity.skipActivity(SettingActivity.class);
                break;
            case R.id.fm_main_four_signin:
                if (YigouConstant.token.equals("")) {
                    relogin();
                    return;
                }
                //签到
                activity.skipActivity(SigninActivity.class);
                break;
        }

    }

    @Override
    public void getUserInfoSuccess(UserInfoBean userInfoBean) {

        if(DemoUtils.isPfUser()){
            mFmMainFourRlYaoqing.setVisibility(View.GONE);
            mFmMainFourRlFens.setVisibility(View.GONE);
            mFmMainFourRlShouyi.setVisibility(View.GONE);
            mFmMainFourRlShouyiSort.setVisibility(View.GONE);
            mFmMainFourRlYhj.setVisibility(View.GONE);
        }else{
            mFmMainFourRlYaoqing.setVisibility(View.VISIBLE);
            mFmMainFourRlFens.setVisibility(View.VISIBLE);
            mFmMainFourRlShouyi.setVisibility(View.VISIBLE);
            mFmMainFourRlShouyiSort.setVisibility(View.VISIBLE);
            mFmMainFourRlYhj.setVisibility(View.VISIBLE);
        }
        if(DemoConstant.shopInfoBean.getAutoSignin()==0){
            mFmMainFourSignin.setVisibility(View.GONE);
        }else{
            mFmMainFourSignin.setVisibility(View.VISIBLE);
        }


        DemoConstant.balance = userInfoBean.getBalance();
        DemoConstant.userStatus = userInfoBean.getStatus();

        GlideUtile.bindImageViewRound(activity.context, userInfoBean.getAvatarUrl(), R.mipmap.t_head, mFmMainFourHead);

        mFmMainFourName.setText(userInfoBean.getNickName());

        int status = userInfoBean.getStatus();

        if (status == 1 || status == 2) {
            GlideUtile.bindImageView(activity.context, R.mipmap.t_member, mFmMainFourMember);
            if (status == 2) {
                mFmMainFourMemberText.setText("批发商");
            } else {
                mFmMainFourMemberText.setText("会员");
            }
        }/* else {
            GlideUtile.bindImageView(activity.context, R.mipmap.t_member_no, mFmMainFourMember);
            mFmMainFourMemberText.setText("游客");

        }*/

    }

    @Override
    public void getOrderNumberSuccess(OrderNumberInfoBean numberInfoBean) {
        initOrdeNumber(numberInfoBean);
    }

    private void initOrdeNumber(OrderNumberInfoBean numberInfoBean){
        int status0 = numberInfoBean.getTrade_status0();
        if (status0 > 0) {
            mFmMainFourOrderDzfNum.setVisibility(View.VISIBLE);
            mFmMainFourOrderDzfNum.setText(status0 + "");
        } else {
            mFmMainFourOrderDzfNum.setVisibility(View.GONE);
        }
        int status1 = numberInfoBean.getTrade_status1();
        if (status1 > 0) {
            mFmMainFourOrderDfhNum.setVisibility(View.VISIBLE);
            mFmMainFourOrderDfhNum.setText(status1 + "");
        } else {
            mFmMainFourOrderDfhNum.setVisibility(View.GONE);
        }
        int status2 = numberInfoBean.getTrade_status2();
        if (status2 > 0) {
            mFmMainFourOrderDshNum.setVisibility(View.VISIBLE);
            mFmMainFourOrderDshNum.setText(status2 + "");
        } else {
            mFmMainFourOrderDshNum.setVisibility(View.GONE);
        }
        int status3 = numberInfoBean.getTrade_status3();
        if (status3 > 0) {
            mFmMainFourOrderDpjNum.setVisibility(View.VISIBLE);
            mFmMainFourOrderDpjNum.setText(status3 + "");
        } else {
            mFmMainFourOrderDpjNum.setVisibility(View.GONE);
        }
    }

}