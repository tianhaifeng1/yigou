package com.work.doctor.fruits.activity.Goods;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.GreenDaoAssist;
import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo4;
import com.t.httplib.yigou.bean.resp.CategoryGoodsBean;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tlibs.uils.GlideUtile;
import com.trjx.tlibs.uils.SnackbarUtil;
import com.trjx.tlibs.uils.ToastUtil2;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.CategoryGoodsListAdapter;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.MoreGoodsSpecInfoDialog2;

import java.util.ArrayList;
import java.util.List;

public class CategoryGoodsListActivity extends DemoMVPActivity<CategoryGoodsListView, CategoryGoodsListPresenter>
        implements CategoryGoodsListView, BaseQuickAdapter.OnItemChildClickListener,MoreGoodsSpecInfoDialog2.OnMoreGoodsListSpecInfoListener {

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
    private boolean isshow = false;
    private int page=1;

    private List<CategoryGoodsBean> listCategoryGoodsBean;

    private GoodsInfoBean goodsInfoBean;

    private GreenDaoAssist greenDaoAssist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_goods_list);
    }

    @Override
    protected void initView() {
        super.initView();
        greenDaoAssist = new GreenDaoAssist(((DemoApplication) getApplication()).databaseAssist);

        mItemActivityImage = findViewById(R.id.item_activity_image);
        mCategoryGoodlist = findViewById(R.id.category_recyclerview_goodlist);
        swipeRefreshLayout = findViewById(R.id.category_swiperefreshlayout);

        titleModule.initTitle(getIntent().getStringExtra("activityName"), true);
        GlideUtile.bindImageView(this, getIntent().getStringExtra("activityIcon"), mItemActivityImage);

        reInitData(1);

        initAdapter();

        swipeRefreshLayout.setColorSchemeColors(colors);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                index=-1;
                reInitData(1);
            }
        });
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
        swipeRefreshLayout.setRefreshing(false);
        isshow = false;
        if(index==-1){
            page=1;
            listCategoryGoodsBean = list;
            mCategoryGoods.getAdapterlist().clear();
            mCategoryGoods.setNewData(list);
            return;
        }

        if(list==null){
            ToastUtil2.showToast(context, "没有更多");
            return;
        }
        for (int i = 0;i < list.size(); i++){
            if(listCategoryGoodsBean.get(index).getCnname().equals(list.get(i).getCnname())){
                mCategoryGoods.getAdapterlist().get(index).addData(list.get(i).getGoods());
                isshow = true;
                return;
            }
        }

        if(isshow == false){
            ToastUtil2.showToast(context, "没有更多");
        }


//        mCategoryGoods.addData(list);
    }

    @Override
    public void getGoodsSpecSuccess(GoodsSpecInfoBean infoBean) {
        if (infoBean != null && infoBean.getGoodsItemList() != null && infoBean.getGoodsItemList().size() > 0) {
            // 有规格
            goodsItemDialog(infoBean);
        } else {
            if (goodsInfoBean.getStock() <= 0) {
                SnackbarUtil.showToast(rootView, "暂无库存");
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

    @Override
    public void addGoodsToShoppingCartSuccess() {
        SnackbarUtil.showToast(rootView, "添加成功");
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
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        int ids = view.getId();
        switch (ids) {
            case R.id.category_footview:
                if(index!=-1&&index!=position){
                    page=1;
                }
                index = position;
                page++;
                reInitData(page);
                break;
            case R.id.item_shop_cart:
                goodsInfoBean= (GoodsInfoBean) adapter.getData().get(position);
                getPresenter().getGoodsItem(goodsInfoBean.getId());
                break;
        }
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
