package com.work.doctor.fruits.activity.myrecycler;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.httplib.yigou.bean.resp.EvaluateInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.trjx.tlibs.uils.ToastUtil2;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.EvaluateAdapter;
import com.work.doctor.fruits.base.DemoMVPFragment;

import java.util.List;

/**
 * 数据列表显示页面，支持所有的列表显示页
 *
 * code：根据 code 区分不同的列表页面
 *
 * code = 101 ：评价
 *
 *
 */
public class MyRecyclerFragment extends DemoMVPFragment<MyRecyclerView, MyRecyclerPresenter>
        implements MyRecyclerView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, TRecyclerViewListenter {

    private TRecyclerModule recyclerModule;

    private int code = -1;

    public static MyRecyclerFragment newInstance(int code) {
        return new MyRecyclerFragment(code);
    }

    public MyRecyclerFragment(int code) {
        this.code = code;
    }

    @Override
    protected int initLayout() {
        return R.layout.layout_recyclerview;
    }

    @Override
    protected void initFragmentView(View view) {
        //初始化Module
        initRecyclerModule(view);
        getRecyclerListData();
    }

    @Override
    protected MyRecyclerPresenter initPersenter() {
        return new MyRecyclerPresenter(this);
    }


    private void initRecyclerModule(View view){
        if(code == 101){
            recyclerModule = new TRecyclerModule.Builder(activity.context)
                    .setDefImgRes(R.mipmap.default_list)
                    .setDefTextStr("暂无评价内容")
                    .setLayoutManager(new LinearLayoutManager(activity.context))
                    .createAdapter(new EvaluateAdapter(null))
                    .setTRecyclerViewListenter(this)
                    .creat(view);
            recyclerModule.setRefreshing(true);
        }

    }


    @Override
    public void getListDataSuccess(List<?> list) {
        if (recyclerModule != null) {
            recyclerModule.setRefreshing(false);
            recyclerModule.bindListData(list);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if(code == 101){
            EvaluateInfoBean infoBean = (EvaluateInfoBean) adapter.getData().get(position);
            ToastUtil2.showToast(activity.context, "点击了：" + position);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        getPresenter().getListData(code , recyclerModule.getPage());
    }
}
