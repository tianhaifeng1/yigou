package com.xzte;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.trjx.tlibs.assist.ImgPaths;
import com.trjx.tlibs.uils.GlideUtile;
import com.trjx.tlibs.uils.ToastUtil2;

import java.util.ArrayList;
import java.util.List;

public class Test2Activity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        initView();
    }

    private MyBaseQuesAdapter myBaseQuesAdapter;
    private MyBaseQuesAdapter myBaseQuesAdapter2;

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView2 = findViewById(R.id.recyclerview2);
        toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("测试");
//        toolbar.setLogo(android.R.drawable.ic_menu_camera);
//        toolbar.setSubtitle("小标题");
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(view -> {
            ToastUtil2.showToast(Test2Activity.this,"点击推出");
        });

        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,5){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        myBaseQuesAdapter = new MyBaseQuesAdapter(null);
        myBaseQuesAdapter.setOnItemClickListener((adapter, view, position) -> initData2());
        recyclerView.setAdapter(myBaseQuesAdapter);
        myBaseQuesAdapter2 = new MyBaseQuesAdapter(null);
        myBaseQuesAdapter2.setOnItemClickListener((adapter, view, position) -> ToastUtil2.showToast(Test2Activity.this, "item" + position));
        recyclerView2.setAdapter(myBaseQuesAdapter2);

        initData();

    }

    public void initData() {
        List<TitleBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TitleBean titleBean = new TitleBean();
            titleBean.setName("name" + i);
            titleBean.setIndex(i + 5);
            list.add(titleBean);
        }

        myBaseQuesAdapter.setNewData(list);
        myBaseQuesAdapter.loadMoreComplete();

        initData2();

    }


    public void initData2(){
        List<TitleBean> list = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            TitleBean titleBean = new TitleBean();
            titleBean.setName("itemname" + i);
            titleBean.setIndex(i + 5);
            list.add(titleBean);
        }
        myBaseQuesAdapter2.setNewData(list);
        myBaseQuesAdapter2.loadMoreComplete();
    }

    public void onClickImgView(View view) {
        ToastUtil2.showToast(this,"点击了");
    }


    class MyBaseQuesAdapter extends BaseQuickAdapter<TitleBean,BaseViewHolder>{

        public MyBaseQuesAdapter(@Nullable List<TitleBean> data) {
            super(data);
            mLayoutResId = R.layout.item_img;
        }

        @Override
        protected void convert(BaseViewHolder helper, TitleBean item) {
            GlideUtile.bindImageView(Test2Activity.this, ImgPaths.path[item.getIndex()], helper.getView(R.id.item_img_imageview));
            helper.setText(R.id.item_img_textview, item.getName());
        }
    }

    class TitleBean{

        private String name;

        private int index;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    public static class MyBehavior extends CoordinatorLayout.Behavior{

        float den = 1;
        public MyBehavior(Context context, AttributeSet attrs) {
            super(context, attrs);
            den = context.getResources().getDisplayMetrics().density;
        }

        @Override
        public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
            return dependency instanceof RecyclerView;
        }

        @Override
        public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
            child.setX(dependency.getX());
            child.setY(dependency.getY() + den * 180);
            return true;
        }
    }

}
