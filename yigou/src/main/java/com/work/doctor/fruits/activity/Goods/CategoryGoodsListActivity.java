package com.work.doctor.fruits.activity.Goods;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo4;
import com.t.httplib.yigou.bean.resp.CategoryGoodsBean;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.CategoryGoodsListAdapter;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.util.List;

public class CategoryGoodsListActivity extends DemoMVPActivity<CategoryGoodsListView, CategoryGoodsListPresenter>
        implements CategoryGoodsListView, BaseQuickAdapter.OnItemChildClickListener {

    private int colors[] = {
            Color.rgb(47, 223, 189),
            Color.rgb(223, 47, 189),
            Color.rgb(189, 223, 47),
            Color.rgb(47, 55, 80)
    };

    private ImageView mItemActivityImage;

    private RecyclerView mCategoryGoodlist;

    private SwipeRefreshLayout swipeRefreshLayout;

    private int index=-1;
    private int page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_goods_list);
    }

    @Override
    protected void initView() {
        super.initView();

        mItemActivityImage = findViewById(R.id.item_activity_image);
        mCategoryGoodlist = findViewById(R.id.category_recyclerview_goodlist);
        swipeRefreshLayout = findViewById(R.id.category_swiperefreshlayout);

        titleModule.initTitle(getIntent().getStringExtra("activityName"), true);
        GlideUtile.bindImageView(this, getIntent().getStringExtra("activityIcon"), mItemActivityImage);

        reInitData(1);

        initAdapter();

        swipeRefreshLayout.setColorSchemeColors(colors);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> reInitData(1));
    }

    private void reInitData(int index) {

        ReqGoodsInfo4 info = new ReqGoodsInfo4();
        info.setActivityId(getIntent().getIntExtra("activityId", -1)+"");
        info.setShopId(DemoConstant.shopInfoBean.getShopId()+"");
        info.setPage(index);
        info.setPageSize(9);
        getPresenter().getCategoryGoodsList(info);
    }


    private CategoryGoodsListAdapter mCategoryGoods;

    private void initAdapter() {
        mCategoryGoodlist.setLayoutManager(new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mCategoryGoods = new CategoryGoodsListAdapter(null);
        mCategoryGoods.setOnItemChildClickListener(this);
        mCategoryGoodlist.setAdapter(mCategoryGoods);

    }

    @Override
    protected CategoryGoodsListPresenter initPersenter() { return new CategoryGoodsListPresenter(this); }

    @Override
    public void getCategoryGoodsListSuccess(List<CategoryGoodsBean> list) {
        if(index==-1){
            mCategoryGoods.setNewData(list);
        }else{
            mCategoryGoods.adapterJrtm.addData(list.get(index).getGoods());
        }


//        mCategoryGoods.addData(list);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        int ids = view.getId();
        switch (ids) {
            case R.id.category_footview:
                index = position;
                page++;
                reInitData(page);
                break;
        }
    }
}
