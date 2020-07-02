package com.work.doctor.fruits.activity.order;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.t.httplib.yigou.bean.req.ReqCommentInfo;
import com.t.httplib.yigou.bean.resp.OrderDetailInfoShopInfoBean;
import com.t.httplib.yigou.bean.resp.OrderListInfoBean;
import com.trjx.tlibs.uils.ToastUtil2;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.OrderCommentAdapter;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.util.ArrayList;
import java.util.List;

public class OrderCommentActivity extends DemoMVPActivity<OrderCommentView, OrderCommentPresenter>
        implements OrderCommentView, View.OnClickListener {

    private RecyclerView mCommentRecyclerview;
    private TextView mCommentCommit;
    private EditText mCommentContext;


    private OrderListInfoBean infoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_comment);
    }

    @Override
    protected OrderCommentPresenter initPersenter() {
        return new OrderCommentPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("评价", true);

        mCommentRecyclerview = findViewById(R.id.comment_recyclerview);
        mCommentCommit = findViewById(R.id.comment_commit);
        mCommentContext = findViewById(R.id.comment_context);

        mCommentCommit.setOnClickListener(this);

        infoBean = (OrderListInfoBean) getIntent().getSerializableExtra("comment");
        List<OrderDetailInfoShopInfoBean> shopInfoBeanList = infoBean.getDetails();

        if (shopInfoBeanList != null && shopInfoBeanList.size() > 0) {
            OrderCommentAdapter adapter = new OrderCommentAdapter(shopInfoBeanList);

//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mCommentRecyclerview.getLayoutParams();
//            layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
//            layoutParams.height = context.getResources().getDimensionPixelOffset(R.dimen.dp290) * shopInfoBeanList.size();
//            mCommentRecyclerview.setLayoutParams(layoutParams);

            mCommentRecyclerview.setLayoutManager(new LinearLayoutManager(context){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            mCommentRecyclerview.setAdapter(adapter);
        }else{
            mCommentCommit.setEnabled(false);
        }

    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids){
            case R.id.comment_commit:
                //提交
                List<OrderDetailInfoShopInfoBean> shopInfoBeanList = infoBean.getDetails();

                ReqCommentInfo commentInfo = new ReqCommentInfo();
                commentInfo.setSystemOrderNo(infoBean.getSystemOrderNo());
                List<ReqCommentInfo.ReqComment> commentList = new ArrayList<>();
                ReqCommentInfo.ReqComment comment;
                for (int i = 0; i < shopInfoBeanList.size(); i++) {
                    OrderDetailInfoShopInfoBean shopInfoBean = shopInfoBeanList.get(i);
                    if (TextUtils.isEmpty(mCommentContext.getText().toString().trim())) {
                        ToastUtil2.showToast(context,shopInfoBean.getGoodsName() + "未添加评价信息");
                        return;
                    }
                    comment = new ReqCommentInfo.ReqComment();
                    comment.setGoodsId(shopInfoBean.getGoodsId()+"");
                    comment.setContent(mCommentContext.getText().toString().trim());
                    commentList.add(comment);
                }
                commentInfo.setComments(commentList);

                getPresenter().getComment(commentInfo);
                break;
        }
    }

    @Override
    public void getCommentSuccess() {
        ToastUtil2.showToast(context,"评论成功");
        setResult(200);
        finish();
    }
}
