package com.work.doctor.fruits.activity;

import android.os.Bundle;
import android.view.View;

import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.bean.TestBean;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.trjx.tlibs.uils.ToastUtil;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.util.List;

@Deprecated
public class MainActivity extends DemoMVPActivity<MainView,MainPresenter> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("测试页面");
//        titleModule.setTitleBackground(R.color.color_black);
    }

    @Override
    public void testSuccess(TestBean testBean) {
        ToastUtil.showToast(context,"成功");
    }

    @Override
    public void test2Success(List<GoodsInfoBean> goodsList) {
        ToastUtil.showToast(context,"成功");
    }

    @Override
    public void test3Success(GoodsInfoBean goodsInfoBean) {
        ToastUtil.showToast(context,"成功");
    }

    @Override
    protected MainPresenter initPersenter() {
        return new MainPresenter(this);
    }

    @Override
    protected DemoModel initModel() {
        return new DemoModel();
    }

    public void onClickTest1(View view) {
        getPresenter().getTest2Info_2();
    }

    public void onClickTest2(View view) {
        getPresenter().getTest2Info_3();
    }

    public void onClickStateBar(View view) {
        titleModule.setTitleBackground(R.color.color_black);
        changeStateBar(false);
    }
}



