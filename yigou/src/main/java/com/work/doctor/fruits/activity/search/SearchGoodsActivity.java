package com.work.doctor.fruits.activity.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.DatabaseSearchGoodsInfo;
import com.t.databaselib.GreenDaoAssist;
import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.t.httplib.yigou.bean.resp.SearchGoodsInfoBean;
import com.trjx.tlibs.uils.SnackbarUtil;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.Goods.GoodsDetial2Activity;
import com.work.doctor.fruits.activity.adapter.SearchContentAdapter;
import com.work.doctor.fruits.activity.adapter.SearchGoodsAdapter;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.MoreGoodsSpecInfoDialog2;

import java.util.ArrayList;
import java.util.List;

public class SearchGoodsActivity extends DemoMVPActivity<SearchGoodsView, SearchGoodsPresenter>
        implements View.OnClickListener, SearchGoodsView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, MoreGoodsSpecInfoDialog2.OnMoreGoodsListSpecInfoListener {

    private GreenDaoAssist greenDaoAssist;

    private ImageView mSgBack;
    private EditText mSgEdittext;
    private TextView mSgSearch;
    private RecyclerView mSgRecyclerviewone;
    private RecyclerView mSgRecyclerviewtwo;
    private RelativeLayout defaultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);
    }

    @Override
    protected void initView() {

        mSgBack = findViewById(R.id.sg_back);
        mSgEdittext = findViewById(R.id.sg_edittext);
        mSgSearch = findViewById(R.id.sg_search);
        mSgRecyclerviewone = findViewById(R.id.sg_recyclerviewone);
        mSgRecyclerviewtwo = findViewById(R.id.sg_recyclerviewtwo);
        defaultView = findViewById(R.id.sg_defaultview);

        mSgBack.setOnClickListener(this);
        mSgSearch.setOnClickListener(this);

        greenDaoAssist = new GreenDaoAssist(((DemoApplication) getApplication()).databaseAssist);

        mSgEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString().trim();
                if (string.equals("")) {
                    mSgRecyclerviewtwo.setVisibility(View.GONE);
                    defaultView.setVisibility(View.GONE);
                    mSgRecyclerviewone.setVisibility(View.VISIBLE);
                }
            }
        });

        initData();

        getSearchHistroyData();

        getPresenter().getSearchPopularGoodsList();

    }

    private SearchContentAdapter searchContentAdapter;
    private SearchGoodsAdapter searchGoodsAdapter;

    private void initData() {
        List<SearchGoodsInfoBean> infoBeanList = new ArrayList<>();
        SearchGoodsInfoBean infoBean = new SearchGoodsInfoBean();
        infoBean.setTitle("热门");
        infoBean.setCode(1);
        infoBeanList.add(infoBean);

        SearchGoodsInfoBean infoBeanHistory = new SearchGoodsInfoBean();
        infoBeanHistory.setTitle("历史记录");
        infoBeanHistory.setCode(2);
        infoBeanList.add(infoBeanHistory);

        searchContentAdapter = new SearchContentAdapter(infoBeanList){

            @Override
            public void onItemChildClickListener(String strName) {
                if (strName != null && !strName.equals("")) {
                    mSgEdittext.setText(strName);
                    mSgEdittext.setSelection(strName.length());
                    searchContent(strName);
                }
            }
        };
        mSgRecyclerviewone.setAdapter(searchContentAdapter);
        mSgRecyclerviewone.setLayoutManager(new LinearLayoutManager(context));
//        searchContentAdapter.setOnItemClickListener((adapter, view, position) -> {
//            SearchGoodsInfoBean infoBean1 = (SearchGoodsInfoBean) adapter.getData().get(position);
//            String strName = (String) adapter.getData().get(position);
//            if (strName != null && !strName.equals("")) {
//                mSgEdittext.setText(strName);
//                mSgEdittext.setSelection(strName.length());
//                searchContent(strName);
//            }
//        });
        searchContentAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            int ids = view.getId();
            switch (ids) {
                case R.id.item_searchgoods_clear:
                    greenDaoAssist.clearSearchGoodsInfoDatabase();
                    getSearchHistroyData();
                    break;
            }
        });

        searchGoodsAdapter = new SearchGoodsAdapter(null);
        mSgRecyclerviewtwo.setLayoutManager(new LinearLayoutManager(context));
        mSgRecyclerviewtwo.setAdapter(searchGoodsAdapter);

        searchGoodsAdapter.setOnItemClickListener(this);
        searchGoodsAdapter.setOnItemChildClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.sg_back:
                finish();
                break;
            case R.id.sg_search:
                String searchStr = mSgEdittext.getText().toString().trim();
                if (!searchStr.equals("")) {
                    mSgRecyclerviewone.setVisibility(View.GONE);
                    mSgRecyclerviewtwo.setVisibility(View.VISIBLE);
                    searchContent(searchStr);
                } else {
                    mSgRecyclerviewone.setVisibility(View.VISIBLE);
                    mSgRecyclerviewtwo.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    protected SearchGoodsPresenter initPersenter() {
        return new SearchGoodsPresenter(this);
    }

    @Override
    public void getSearchPopularGoodsListSuccess(List<String> list) {
        searchContentAdapter.getData().get(0).setStringList(list);
        searchContentAdapter.notifyItemChanged(0);
    }

    private void getSearchHistroyData(){
        List<DatabaseSearchGoodsInfo> databaseSearchGoodsInfoList = greenDaoAssist.querySearchGoodsInfo();
        List<String> stringList = new ArrayList<>();
        if (databaseSearchGoodsInfoList != null && databaseSearchGoodsInfoList.size() > 0) {
            for (DatabaseSearchGoodsInfo goodsInfo : databaseSearchGoodsInfoList
            ) {
                stringList.add(goodsInfo.getSearchName());
            }
        }
        searchContentAdapter.getData().get(1).setStringList(stringList);
        searchContentAdapter.notifyItemChanged(1);
    }

    @Override
    public void getGoodsItemSuccess(GoodsSpecInfoBean specInfoBean,int position) {
        goodsSpecInfoBean = specInfoBean;
        bymay(position);
    }

    @Override
    public void getSearchInfoSuccess(List<GoodsInfoBean> list) {
        if (list == null || list.size() == 0) {
            defaultView.setVisibility(View.VISIBLE);
            mSgRecyclerviewtwo.setVisibility(View.GONE);
        }else{
            defaultView.setVisibility(View.GONE);
            mSgRecyclerviewtwo.setVisibility(View.VISIBLE);
            searchGoodsAdapter.setNewData(list);
            searchGoodsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addGoodsToShoppingCartSuccess() {
        SnackbarUtil.showToast(rootView, "添加成功");
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        GoodsInfoBean goodsInfoBean = (GoodsInfoBean) adapter.getData().get(position);
        Intent intent = new Intent(context, GoodsDetial2Activity.class);
        intent.putExtra("id", goodsInfoBean.getId());
        skipActivity(intent);
    }

    //搜索
    private void searchContent(String searchContent) {
        mSgRecyclerviewone.setVisibility(View.GONE);
        mSgRecyclerviewtwo.setVisibility(View.VISIBLE);
        greenDaoAssist.insertSearchGoodsInfoData(searchContent);
        getSearchHistroyData();
        getPresenter().getSearchInfo(searchContent);
    }

    private GoodsInfoBean goodsInfoBean;
    private int index = -1;
    private GoodsSpecInfoBean goodsSpecInfoBean;


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        goodsInfoBean = (GoodsInfoBean) adapter.getData().get(position);
        if (index == position) {
            bymay(position);
        } else {
            //获取规格
            getPresenter().getGoodsItem(goodsInfoBean.getId(), position);
        }
    }

    private void bymay(int position) {
        if (goodsSpecInfoBean != null && goodsSpecInfoBean.getGoodsItemList() != null && goodsSpecInfoBean.getGoodsItemList().size() > 0) {
            // 有规格
            index = position;
            goodsItemDialog(goodsSpecInfoBean);
        } else {

            if (goodsInfoBean.getStock() <= 0) {
                tRemind("暂无库存");
                return;
            }

            //没有规格
            //直接添加到购物车
            DatabaseGoodsInfo goodsInfo = new DatabaseGoodsInfo();
//        商品信息
            goodsInfo.setGoodsId(goodsInfoBean.getId() + "");
            goodsInfo.setGoodsName(goodsInfoBean.getGoodsName());
            goodsInfo.setGoodsPrice(goodsInfoBean.getSellPriceDiscount());
            goodsInfo.setGoodsNumber(1);
            goodsInfo.setGoodsUrl(goodsInfoBean.getGoodsImage());
            goodsInfo.setGoodsTotal(goodsInfoBean.getStock());
            //规格
            goodsInfo.setSpecId("");
            goodsInfo.setSpecName("");
//        店铺信息
            goodsInfo.setShopId(goodsInfoBean.getShopId() + "");
            greenDaoAssist.insertGoods(DemoConstant.shopInfoBean.getShopId() + "", goodsInfo, isChange -> DemoConstant.isChangeDatabase = isChange);

            ReqCartAddInfo addInfo = new ReqCartAddInfo();
            ReqCartAddInfo.ReqCartAddInfoBean infoBean2 = new ReqCartAddInfo.ReqCartAddInfoBean();
            infoBean2.setGoodsId(goodsInfo.getGoodsId());
            infoBean2.setAttrStrId(goodsInfo.getSpecId());
            infoBean2.setTotalNum(goodsInfo.getGoodsNumber());
            List<ReqCartAddInfo.ReqCartAddInfoBean> goods = new ArrayList<>();
            goods.add(infoBean2);
            addInfo.setGoods(goods);

            if (YigouConstant.token.equals("")) {
                SnackbarUtil.showToast(rootView, "添加成功");
            } else {
                getPresenter().addGoodsToShoppingCart(addInfo);
            }

        }
    }

    //弹框
    private void goodsItemDialog(GoodsSpecInfoBean specListBeanList) {
        MoreGoodsSpecInfoDialog2 infoDialog = new MoreGoodsSpecInfoDialog2.Builder(context)
                .setCancelable(false)
                .setGoodsInfo(goodsInfoBean)
                .setGoodsItemList(specListBeanList)
                .setOnMoreGoodsListSpecInfoListener(this)
                .create();
        infoDialog.show(getSupportFragmentManager(), "tishi_itemspec");
    }

    @Override
    public void onOnMoreGoodsListAddcartClick(View view, String specIds, String specName, float goodsPrice, int number) {
        DatabaseGoodsInfo goodsInfo = new DatabaseGoodsInfo();
//        商品信息
        goodsInfo.setGoodsId(goodsInfoBean.getId() + "");
        goodsInfo.setGoodsName(goodsInfoBean.getGoodsName());
        goodsInfo.setGoodsPrice(goodsPrice);
        goodsInfo.setGoodsNumber(number);
        goodsInfo.setGoodsUrl(goodsInfoBean.getGoodsImage());
        goodsInfo.setGoodsTotal(goodsInfoBean.getStock());
        //规格
        goodsInfo.setSpecId(specIds);
        goodsInfo.setSpecName(specName);
//        店铺信息
        goodsInfo.setShopId(goodsInfoBean.getShopId() + "");
        greenDaoAssist.insertGoods(DemoConstant.shopInfoBean.getShopId() + "", goodsInfo, isChange -> DemoConstant.isChangeDatabase = isChange);

        ReqCartAddInfo addInfo = new ReqCartAddInfo();
        ReqCartAddInfo.ReqCartAddInfoBean infoBean = new ReqCartAddInfo.ReqCartAddInfoBean();
        infoBean.setGoodsId(goodsInfo.getGoodsId());
        infoBean.setAttrStrId(goodsInfo.getSpecId());
        infoBean.setTotalNum(goodsInfo.getGoodsNumber());
        List<ReqCartAddInfo.ReqCartAddInfoBean> goods = new ArrayList<>();
        goods.add(infoBean);
        addInfo.setGoods(goods);
        if (YigouConstant.token.equals("")) {
            SnackbarUtil.showToast(rootView, "添加成功");
        } else {
            getPresenter().addGoodsToShoppingCart(addInfo);
        }
    }
}
