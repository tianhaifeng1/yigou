package com.work.doctor.fruits.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;

import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.GreenDaoAssist;
import com.t.httplib.yigou.DObserver;
import com.t.httplib.yigou.DObserver2;
import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.req.ReqGoodsInfo;
import com.t.httplib.yigou.bean.req.ReqYgSearchInfo;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tbase.activity.SearchActivity;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.SnackbarUtil;
import com.trjx.tlibs.uils.ToastUtil;
import com.trjx.tlibs.uils.ToastUtil2;
import com.trjx.tlibs.views.TListView;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.Goods.GoodsDetial2Activity;
import com.work.doctor.fruits.activity.adapter.YgSearchAdapter;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.dialog.MoreGoodsSpecInfoDialog2;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class YgSearchActivity extends SearchActivity<GoodsInfoBean, YgSearchAdapter> implements MoreGoodsSpecInfoDialog2.OnMoreGoodsListSpecInfoListener {

    private DemoModel model;

    private GreenDaoAssist greenDaoAssist;

    private TListView listview_hot;

    private ScrollView history_scrollview;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_search_yg);
    }

    @Override
    protected void initView() {
        isShowSearchBtn = true;
        super.initView();

        history_scrollview = findViewById(R.id.history_scrollview);

        editText.setHint("搜索");

        listview_hot = findViewById(R.id.listview_hot);

        model = new DemoModel();
        greenDaoAssist = new GreenDaoAssist(((DemoApplication)getApplication()).databaseAssist);


        editText.setOnClickListener(v -> {
            history_scrollview.setVisibility(View.VISIBLE);
            historyLL.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            empty.setVisibility(View.GONE);
            getSearchHistoryData();
        });

        model.requestSearchPopularGoodsList(new DObserver<List<String>>(this) {
            @Override
            public void onTPostSuccess(List<String> strings) {
                try {
                    if (strings != null && strings.size() > 0) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, android.R.id.text1, strings);
                        listview_hot.setAdapter(arrayAdapter);
                        listview_hot.setOnItemClickListener((parent, view, position, id) -> {
                            String str = strings.get(position);
                            editText.setText(str);
                            editText.setSelection(str.length());
                            searchData(str);

//                        try {
//                            Class clazz = Class.forName("com.trjx.tbase.activity.SearchActivity");
//                            Method method = clazz.getDeclaredMethod("searchData", String.class);
//                            method.toGenericString();
//                            method.invoke(clazz.newInstance(), str);
//                        } catch (ClassNotFoundException e) {
//                            e.printStackTrace();
//                        } catch (NoSuchMethodException e) {
//                            e.printStackTrace();
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        } catch (InvocationTargetException e) {
//                            e.printStackTrace();
//                        } catch (InstantiationException e) {
//                            e.printStackTrace();
//                        }
                        });

                    }else{
                        SnackbarUtil.showToast(rootView,"很抱歉，没有对应商品");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void searchData(String searchStr) {
        history_scrollview.setVisibility(View.GONE);
        super.searchData(searchStr);
    }

    @Override
    protected void searchPost(String searchStr) {

        if (DemoConstant.shopInfoBean == null) {
            Logger.t("店铺信息不能为空");
            return;
        }
        ReqYgSearchInfo info = new ReqYgSearchInfo();
        info.setGoodsName(searchStr);
        info.setShopId(DemoConstant.shopInfoBean.getShopId() + "");
        model.requestSearchInfo(info, new DObserver<List<GoodsInfoBean>>(this) {
            @Override
            public void onTPostSuccess(List<GoodsInfoBean> ygSearchInfoBeans) {
                try {
                    getSearchDataList(ygSearchInfoBeans);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private GoodsInfoBean goodsInfoBean;
    private int index = -1;
    private GoodsSpecInfoBean goodsSpecInfoBean;

    @Override
    protected YgSearchAdapter initAdapter(List<GoodsInfoBean> infoList) {
        return new YgSearchAdapter(context, infoList) {

            @Override
            public void onClickCart(GoodsInfoBean infoBean, int position) {
                goodsInfoBean = infoBean;
                if (index == position) {
                    bymay(position);
                } else {
//获取规格
                    ReqGoodsInfo info = new ReqGoodsInfo();
                    info.setGoodsId(infoBean.getId());
                    model.requestGoodsItem(info, new DObserver<GoodsSpecInfoBean>(YgSearchActivity.this) {
                        @Override
                        public void onTPostSuccess(GoodsSpecInfoBean specInfoBean) {
                            try {
                                goodsSpecInfoBean = specInfoBean;
                                bymay(position);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                }


            }
        };
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
            greenDaoAssist.insertGoods(DemoConstant.shopInfoBean.getShopId()+"", goodsInfo, isChange -> DemoConstant.isChangeDatabase = isChange);

            ReqCartAddInfo addInfo = new ReqCartAddInfo();
            ReqCartAddInfo.ReqCartAddInfoBean infoBean2 = new ReqCartAddInfo.ReqCartAddInfoBean();
            infoBean2.setGoodsId(goodsInfo.getGoodsId());
            infoBean2.setAttrStrId(goodsInfo.getSpecId());
            infoBean2.setTotalNum(goodsInfo.getGoodsNumber());
            List<ReqCartAddInfo.ReqCartAddInfoBean> goods = new ArrayList<>();
            goods.add(infoBean2);
            addInfo.setGoods(goods);

            if (YigouConstant.token.equals("")) {
                ToastUtil.showToast(context, "添加成功");
            } else {
                showDialog("添加中...");
                model.requestCartAdd(addInfo, new DObserver2(YgSearchActivity.this) {
                    @Override
                    public void onTPostSuccess() {
                        try {
                            ToastUtil2.showToast(context, "添加成功");
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(context, GoodsDetial2Activity.class);
        intent.putExtra("id", infoList.get(position).getId());
        skipActivity(intent);

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
        greenDaoAssist.insertGoods(DemoConstant.shopInfoBean.getShopId()+"", goodsInfo, isChange -> DemoConstant.isChangeDatabase = isChange);

        ReqCartAddInfo addInfo = new ReqCartAddInfo();
        ReqCartAddInfo.ReqCartAddInfoBean infoBean = new ReqCartAddInfo.ReqCartAddInfoBean();
        infoBean.setGoodsId(goodsInfo.getGoodsId());
        infoBean.setAttrStrId(goodsInfo.getSpecId());
        infoBean.setTotalNum(goodsInfo.getGoodsNumber());
        List<ReqCartAddInfo.ReqCartAddInfoBean> goods = new ArrayList<>();
        goods.add(infoBean);
        addInfo.setGoods(goods);
        if (YigouConstant.token.equals("")) {
            ToastUtil.showToast(context, "添加成功");
        } else {
            showDialog("添加中...");
            model.requestCartAdd(addInfo, new DObserver2(YgSearchActivity.this) {
                @Override
                public void onTPostSuccess() {
                    try {
                        ToastUtil2.showToast(context, "添加成功");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}