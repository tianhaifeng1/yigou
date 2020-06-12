package com.work.doctor.fruits.activity.myrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.trjx.tlibs.uils.ToastUtil2;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.CouponAdapter;
import com.work.doctor.fruits.activity.adapter.ShoppingAdapter;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.util.List;


/**
 * 数据列表显示页面，支持所有的列表显示页
 *
 * code：根据 code 区分不同的列表页面
 *
 * code = 1 ：领卷中心
 *
 * code = 2 ：今日特卖列表页面
 *
 * code = 3 ：推荐商品列表页面
 *
 *  参考事例
 */
@Deprecated
public class MyRecyclerActivity extends DemoMVPActivity<MyRecyclerView, MyRecyclerPresenter>
implements MyRecyclerView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, TRecyclerViewListenter {

    private int code = -1;

    private TRecyclerModule recyclerModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecycler);
    }

    @Override
    protected void initView() {
        super.initView();

        Intent intent = getIntent();
        code = intent.getIntExtra("code", -1);

        titleModule.initTitle("", true);

        //初始化Module
        initRecyclerModule(rootView);

        getRecyclerListData();

    }

    private void initRecyclerModule(View rootView){
        String titleStr = "";
        @DrawableRes int resDrawable = R.mipmap.default_list;
        String defTextStr = "暂无数据";
        if(code == 1){
            titleStr = "领卷中心";
            recyclerModule = new TRecyclerModule.Builder(context)
                    .setDefImgRes(R.mipmap.default_list)
                    .setDefTextStr("还没有优惠券可以领取哦")
                    .setLayoutManager(new LinearLayoutManager(context))
                    .createAdapter(new CouponAdapter(null))
                    .setTRecyclerViewListenter(this)
                    .creat(rootView);
            recyclerModule.setRefreshing(true);
        }else if(code == 2||code == 3){
//            今日特卖
            if(code == 2){
                titleStr = "今日特卖";
            }else{
                titleStr = "推荐商品";
            }
            recyclerModule = new TRecyclerModule.Builder(context)
                    .setDefImgRes(R.mipmap.default_list)
                    .setDefTextStr("暂无数据")
                    .setLayoutManager(new GridLayoutManager(context,2))
                    .createAdapter(new ShoppingAdapter(null))
                    .setTRecyclerViewListenter(this)
                    .creat(rootView);
            recyclerModule.setRefreshing(true);
        }

        //设置标题
        titleModule.setTitleText(titleStr);

    }

    @Override
    protected MyRecyclerPresenter initPersenter() {
        return new MyRecyclerPresenter(this);
    }

    //======================  获取不同列表的数据  ====================
    @Override
    public void getListDataSuccess(List<?> list) {
        if (recyclerModule != null) {
            recyclerModule.setRefreshing(false);
            recyclerModule.bindListData(list);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if(code == 1){
//            CouponInfoBean bean = (CouponInfoBean) adapter.getData().get(position);
//            if(!bean.isUse()){
//                bean.setUse(true);
//                adapter.notifyItemChanged(position);
//            }else{
//                ToastUtil2.showToast(context, "去使用：" + position);
//            }
        }else if(code == 2 || code == 3){
            GoodsInfoBean bean = (GoodsInfoBean) adapter.getData().get(position);
            ToastUtil2.showToast(context, "添加商品到购物车：" + position);
        }
    }


    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        getPresenter().getListData(code,recyclerModule.getPage());
    }
}
