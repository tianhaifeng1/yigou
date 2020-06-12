package com.xzte;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.trjx.tbase.activity.InitActivity;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.trjx.tbase.module.titlemodule.TitleListenter;
import com.trjx.tbase.module.titlemodule.TitleModule;
import com.trjx.tlibs.assist.ImgPaths;
import com.trjx.tlibs.uils.GlideUtile;
import com.trjx.tlibs.uils.ToastUtil2;

import java.util.ArrayList;
import java.util.List;

public class TestNestRecyclerActivity extends InitActivity implements TitleListenter, TRecyclerViewListenter {

    private TitleModule titleModule;

    private RecyclerView recyclerViewLeft;

    private TRecyclerModule tRecyclerModule;

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_nest_recycler);
        initWork();
        initView();
    }

    private TestLeftAdapter leftAdapter;

    private TestOutAdapter outAdapter;

    @Override
    protected void initView() {
        super.initView();

        handler = new Handler();

        recyclerViewLeft = findViewById(R.id.recyclerview_left);

        titleModule = new TitleModule(context, rootView);
        titleModule.setListenter(this);
        titleModule.initTitle("测试 RecyclerView 嵌套");

        recyclerViewLeft.setLayoutManager(new LinearLayoutManager(context));
        leftAdapter = new TestLeftAdapter(null);
        recyclerViewLeft.setAdapter(leftAdapter);

        outAdapter = new TestOutAdapter(null);
        tRecyclerModule = new TRecyclerModule.Builder(context)
                .setLayoutManager(new LinearLayoutManager(context))
                .setTRecyclerViewListenter(this)
                .createAdapter(outAdapter)
                .creat(rootView);

        leftAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<TestRecyclerTypeInfoBean> infoBeanList = adapter.getData();
            if(index != position){
                infoBeanList.get(index).setSelect(false);
                infoBeanList.get(position).setSelect(true);
                leftAdapter.notifyItemChanged(index);
                leftAdapter.notifyItemChanged(position);
                index = position;

                tRecyclerModule.setRefreshing(true);
                handler.postDelayed(() -> {
                    tRecyclerModule.setPage(1);
                    getRecyclerListData();
                }, 2000);

            }
        });

        outAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            TestRecyclerOutInfoBean outInfoBean = (TestRecyclerOutInfoBean) adapter.getData().get(position);
            ToastUtil2.showToast(context, outInfoBean.getName());
        });

        tRecyclerModule.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        //滚动停止后

                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        //手指还停留在屏幕上滚动时

                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        // 惯性滚动时

                        break;


                }

            }
        });


        handler.postDelayed(() -> getLeftListData(),1000);


    }

    @Override
    public void onClickBack(View view) {

    }

    @Override
    public void onClickLeftText(View view) {

    }

    @Override
    public void onClickRightText(View view) {

    }

    @Override
    public void onClickMenu(View view) {

    }

    @Override
    public void onMenuItemClick(int position) {

    }

    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    private int index = -1;

    private void getLeftListData(){
        List<TestRecyclerTypeInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TestRecyclerTypeInfoBean infoBean = new TestRecyclerTypeInfoBean();
            infoBean.setType(i + 1);
            if (i == 0) {
                index = 0;
                infoBean.setSelect(true);
                infoBean.setTypeName("热门分类");
            }else{
                infoBean.setSelect(false);
                infoBean.setTypeName("其它分类" + i);
            }
            list.add(infoBean);
        }

        leftAdapter.setNewData(list);
        leftAdapter.loadMoreComplete();

        tRecyclerModule.setRefreshing(true);
        handler.postDelayed(() -> {
            tRecyclerModule.setPage(1);
            getRecyclerListData();
        }, 2000);

    }

    @Override
    public void getRecyclerListData() {

        List<TestRecyclerOutInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            TestRecyclerOutInfoBean outInfoBean = new TestRecyclerOutInfoBean();
            List<TestRecyclerOutInfoBean.TestRecyclerInInfoBean> inList = new ArrayList<>();
            int num = (int) (Math.random() * 20) + 10;
            for (int j = 0; j < num; j++) {
                TestRecyclerOutInfoBean.TestRecyclerInInfoBean inInfoBean = new TestRecyclerOutInfoBean.TestRecyclerInInfoBean();
                inInfoBean.setName("内部内容" + j);
                inInfoBean.setPath(ImgPaths.path[j + 5]);
                inList.add(inInfoBean);
            }
            outInfoBean.setName("分类标题" + i);
            outInfoBean.setInList(inList);
            list.add(outInfoBean);
        }


        tRecyclerModule.bindListData(list);
        tRecyclerModule.setRefreshing(false);

        int type = leftAdapter.getData().get(index).getType();
        if (type == 1) {
            outAdapter.setShowTitle(false);
        }else{
            outAdapter.setShowTitle(true);
        }

        if (tRecyclerModule.getPage() == 1 && outAdapter.getData().size() > 0) {
            handler.post(() -> {
                tRecyclerModule.getRecyclerView().getLayoutManager().scrollToPosition(0);
            });
        }
//        tRecyclerModule.getRecyclerView().scrollToPosition(0);

    }

    class TestLeftAdapter extends BaseQuickAdapter<TestRecyclerTypeInfoBean, BaseViewHolder> {

        public TestLeftAdapter(@Nullable List<TestRecyclerTypeInfoBean> data) {
            super(data);
            mLayoutResId = R.layout.item_recycler_left;
        }

        @Override
        protected void convert(BaseViewHolder helper, TestRecyclerTypeInfoBean item) {
            helper.setText(R.id.left_text, item.getTypeName());

            if(item.isSelect()){
                helper.getView(R.id.left_text).setBackgroundResource(R.color.color_white);
            }else{
                helper.getView(R.id.left_text).setBackgroundResource(R.color.color_gray);
            }
        }
    }


    class TestOutAdapter extends BaseQuickAdapter<TestRecyclerOutInfoBean, BaseViewHolder> {

        public TestOutAdapter(@Nullable List<TestRecyclerOutInfoBean> data) {
            super(data);
            mLayoutResId = R.layout.item_recycler_out;
            showTitle = true;//默认现是标题
        }

        private boolean showTitle;

        public void setShowTitle(boolean showTitle){
            this.showTitle = showTitle;
        }


        @Override
        protected void convert(BaseViewHolder helper, TestRecyclerOutInfoBean item) {

            if(showTitle){
                helper.getView(R.id.out_rl).setVisibility(View.VISIBLE);
                helper.setText(R.id.out_text, item.getName());
            }else{
                helper.getView(R.id.out_rl).setVisibility(View.GONE);
            }

            TestInAdapter inAdapter = new TestInAdapter(item.getInList());
            RecyclerView recyclerView = helper.getView(R.id.out_recycler);

            helper.addOnClickListener(R.id.out_rl);

            GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            int height = (int) (mContext.getResources().getDimensionPixelOffset(R.dimen.recycler_type_item_content_hh) * (Math.ceil(item.getInList().size() / 3.0f)));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
            recyclerView.setLayoutParams(layoutParams);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(inAdapter);
            inAdapter.setOnItemClickListener((adapter, view, position) -> {
                ToastUtil2.showToast(mContext, ((TestRecyclerOutInfoBean.TestRecyclerInInfoBean) adapter.getData().get(position)).getName() + "--" + position);
            });
        }
    }

    class TestInAdapter extends BaseQuickAdapter<TestRecyclerOutInfoBean.TestRecyclerInInfoBean, BaseViewHolder> {

        public TestInAdapter(@Nullable List<TestRecyclerOutInfoBean.TestRecyclerInInfoBean> data) {
            super(data);
            mLayoutResId = R.layout.item_recycler_in;
        }

        @Override
        protected void convert(BaseViewHolder helper, TestRecyclerOutInfoBean.TestRecyclerInInfoBean item) {
            helper.setText(R.id.in_text, item.getName());
            GlideUtile.bindImageView(mContext, item.getPath(), R.mipmap.default_img, helper.getView(R.id.in_img));
        }
    }

}
