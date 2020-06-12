package com.work.doctor.fruits.activity.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.SigninInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.util.List;

public class SigninAdapter extends TRecyclerAdapter<SigninInfoBean.Model> {


    public SigninAdapter(@Nullable List<SigninInfoBean.Model> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SigninInfoBean.Model item) {
        helper.setText(R.id.item_signin_name,item.getGoodsName());
        GlideUtile.bindImageView(mContext, item.getGoodsImage(), helper.getView(R.id.item_signin_icon));
        helper.setText(R.id.item_signin_day,item.getSignDay()+"天");
        if(item.getIsSignin()==1){
            helper.getView(R.id.signin_item_image).getBackground().setAlpha(200);
            helper.getView(R.id.signin_item_image).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.signin_item_image).setVisibility(View.GONE);
        }

    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_signin;
    }

}
