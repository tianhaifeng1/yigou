package com.work.doctor.fruits.activity.Goods;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.t.httplib.yigou.bean.resp.GmrOutInfoBean;
import com.trjx.tbase.fragment.TInitFragment;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.GmrAdapter;

/**
 * 商品购买列表
 */
public class GoodsGmrFragment extends TInitFragment
        implements TRecyclerViewListenter {

    private GoodsDetial3Activity goodsDetial3Activity;

    private TextView textView;
    private TRecyclerModule recyclerModule;

    @Override
    protected int initLayout() {
        return R.layout.fm_shop_gmr;
    }

    private GmrAdapter adapter;

    @Override
    protected void initFragmentView(View view) {

        goodsDetial3Activity = (GoodsDetial3Activity) getActivity();

        textView = view.findViewById(R.id.fm_shop_gmr_text);

        //初始化Module
        adapter = new GmrAdapter(null);
        recyclerModule = new TRecyclerModule.Builder(activity.context)
                .setDefImgRes(R.mipmap.default_list)
                .setDefTextStr("暂无评价内容")
                .setLayoutManager(new LinearLayoutManager(activity.context))
                .createAdapter(adapter)
                .setTRecyclerViewListenter(this)
                .creat(view);
        getRecyclerListData();
    }


    @Override
    public void initData() {
        super.initData();
        GmrOutInfoBean infoBean = goodsDetial3Activity.gmrOutInfoBean;
        recyclerModule.setRefreshing(false);
        if (infoBean != null) {
            textView.setText(Html.fromHtml("目前共 <font color=\"#FA2843\">" + infoBean.getParam1() + "</font> 人参与购买，商品共销售 <font color=\"#ff0000\">" + infoBean.getParam2() + "</font> 份"));
            recyclerModule.bindListData(infoBean.getParam3());
        } else {
            textView.setText(Html.fromHtml("目前共 <font color=\"#FA2843\">" + 0 + "</font> 人参与购买，商品共销售 <font color=\"#ff0000\">" + 0 + "</font> 份"));
        }
    }

    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        if (goodsDetial3Activity.goodsId > -1) {
            goodsDetial3Activity.getPresenter().getGmrData(goodsDetial3Activity.goodsId,
                    recyclerModule.getPage(), recyclerModule.getPageSize());
        }
    }

    @Override
    public void tPostFinish(int code) {
        super.tPostFinish(code);
        recyclerModule.setRefreshing(false);
    }
}
