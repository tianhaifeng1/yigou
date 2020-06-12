package com.work.doctor.fruits.activity.Goods;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.t.httplib.yigou.bean.resp.GoodsDetailInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.EvaluateAdapter;
import com.work.doctor.fruits.activity.myrecycler.MyRecyclerPresenter;
import com.work.doctor.fruits.activity.myrecycler.MyRecyclerView;
import com.work.doctor.fruits.base.DemoMVPFragment;

import java.util.List;

/**
 * 商品评价页
 */
@Deprecated
public class GoodsEvaluateFragment extends DemoMVPFragment<MyRecyclerView, MyRecyclerPresenter>
        implements MyRecyclerView,TRecyclerViewListenter {

    private TRecyclerModule recyclerModule;

    private GoodsDetialActivity goodsDetialActivity;

    @Override
    protected int initLayout() {
        return R.layout.layout_recyclerview;
    }

    private EvaluateAdapter adapter;

    @Override
    protected void initFragmentView(View view) {

        goodsDetialActivity = (GoodsDetialActivity) getActivity();

        //初始化Module
        adapter =  new EvaluateAdapter(null);
        recyclerModule = new TRecyclerModule.Builder(activity.context)
                .setDefImgRes(R.mipmap.default_list)
                .setDefTextStr("暂无评价内容")
                .setLayoutManager(new LinearLayoutManager(activity.context))
                .createAdapter(adapter)
                .setTRecyclerViewListenter(this)
                .creat(view);
        recyclerModule.setRefreshing(true);

    }

    @Override
    protected MyRecyclerPresenter initPersenter() {
        return new MyRecyclerPresenter(this);
    }


    @Override
    public void initData() {
        super.initData();
        GoodsDetailInfoBean infoBean = goodsDetialActivity.goodsDetailInfoBean;
        if (infoBean != null || adapter.getData().size() == 0) {
            getRecyclerListData();
        }
    }

    @Override
    public void getListDataSuccess(List<?> list) {
        recyclerModule.setRefreshing(false);
        recyclerModule.bindListData(list);
    }

    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        GoodsDetailInfoBean infoBean = goodsDetialActivity.goodsDetailInfoBean;
        if (infoBean != null) {
            getPresenter().getListDataEvaluate(infoBean.getId(), recyclerModule.getPage(),recyclerModule.getPageSize());
        }
    }

    @Override
    public void tPostFinish(int code) {
        super.tPostFinish(code);
        recyclerModule.setRefreshing(false);
    }
}
