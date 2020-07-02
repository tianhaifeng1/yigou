package com.work.doctor.fruits.activity.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.t.httplib.yigou.bean.resp.OrderDetailInfoShopInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerAdapter;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;

import java.util.List;

public class OrderCommentAdapter extends TRecyclerAdapter<OrderDetailInfoShopInfoBean> {

    public OrderCommentAdapter(@Nullable List<OrderDetailInfoShopInfoBean> data) {
        super( data);
    }

    @Override
    protected int addItemLayoutRes() {
        return R.layout.item_comment;
    }
    @Override
    protected void convert(BaseViewHolder helper, OrderDetailInfoShopInfoBean item) {
        helper.setText(R.id.item_comment_name, item.getGoodsName());
//        helper.setText(R.id.item_comment_attrStrValue, item.getGoodsDesc());
        GlideUtile.bindImageView(mContext, item.getGoodsImage(), helper.getView(R.id.item_comment_image));

//        ((EditText) helper.getView(R.id.item_comment_context)).addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                item.setCommentText(s.toString());
//            }
//        });

//        helper.setText(R.id.item_address_name, item.getName());
//        helper.setText(R.id.item_address_phone, item.getPhone());
//        helper.setText(R.id.item_address_address, item.getDetailAddress());
//
//        helper.addOnClickListener(R.id.item_address_editor,R.id.item_address_event, R.id.item_address_event_copy, R.id.item_address_event_default, R.id.item_address_event_delete);
//
//        int isDefault = item.getIsDefault();
//        if(isDefault == 1){
//            helper.getView(R.id.item_address_status).setVisibility(View.VISIBLE);
//            helper.getView(R.id.item_address_event_default).setVisibility(View.GONE);
//        }else{
//            helper.getView(R.id.item_address_status).setVisibility(View.GONE);
//            helper.getView(R.id.item_address_event_default).setVisibility(View.VISIBLE);
//        }
//
//        if(item.isShowEventView()){
//            helper.getView(R.id.item_address_event).setVisibility(View.VISIBLE);
//        }else{
//            helper.getView(R.id.item_address_event).setVisibility(View.GONE);
//        }

    }

}
