package com.work.doctor.fruits.activity.Goods;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.t.databaselib.BigDecimalUtil;
import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.GreenDaoAssist;
import com.t.httplib.yigou.YigouConstant;
import com.t.httplib.yigou.bean.req.ReqCartAddInfo;
import com.t.httplib.yigou.bean.resp.EvaluateInfoBean;
import com.t.httplib.yigou.bean.resp.GmrOutInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsDetailInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tlibs.uils.GlideUtile;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.SnackbarUtil;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.MainNavActivity;
import com.work.doctor.fruits.activity.adapter.EvaluateAdapter;
import com.work.doctor.fruits.activity.order.GoodsAffirmActivity;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.JgPurchaseBean;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.MoreGoodsSpecInfoDialog;

import java.util.ArrayList;
import java.util.List;

import cn.jiguang.analytics.android.api.Currency;

public class GoodsDetial2Activity extends DemoMVPActivity<GoodsDetialView, GoodsDetialPresenter>
        implements View.OnClickListener, GoodsDetialView, MoreGoodsSpecInfoDialog.OnMoreGoodsSpecInfoListener {

    private RelativeLayout mSdHomepage;
    private RelativeLayout mSdServer;
    private RelativeLayout mSdShopcart;
    private TextView mSdShopcartNum;
    private TextView mSdAdd;
    private TextView mSdBuy;

    private TextView tab1, tab2;
    private WebView webView;
    private RecyclerView recyclerView;
    private TextView defaultTextview;

    private ImageView mFmShopDetialIcon;
    private ConvenientBanner banner;
    private TextView mFmShopDetialName;
    private TextView mFmShopDetialPrice;
    private TextView mFmShopDetialPriceVip;
    private TextView mFmShopDetialGuigetext;
    private LinearLayout mFmShopDetialGuige;

    private RelativeLayout relativeLayout;


    private EvaluateAdapter evaluateAdapter;
    private LinearLayoutManager linearLayoutManager;

    private int goodsId;

    private GreenDaoAssist greenDaoAssist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detial2);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("商品详情", true);

        goodsId = getIntent().getIntExtra("id", -1);

        tab1 = findViewById(R.id.fm_shop_detial_tab1);
        tab2 = findViewById(R.id.fm_shop_detial_tab2);
        webView = findViewById(R.id.webview);
        recyclerView = findViewById(R.id.recyclerview);
        defaultTextview = findViewById(R.id.default_textview);

        mSdHomepage = findViewById(R.id.sd_homepage);
        mSdServer = findViewById(R.id.sd_server);
        mSdShopcart = findViewById(R.id.sd_shopcart);
        mSdShopcartNum = findViewById(R.id.sd_shopcart_num);
        mSdAdd = findViewById(R.id.sd_add);
        mSdBuy = findViewById(R.id.sd_buy);

        relativeLayout = findViewById(R.id.fm_shop_detial_rl);

//        tab1
        mFmShopDetialIcon = findViewById(R.id.fm_shop_detial_icon);
        banner = findViewById(R.id.fm_shop_detial_banner);
        mFmShopDetialName = findViewById(R.id.fm_shop_detial_name);
        mFmShopDetialPrice = findViewById(R.id.fm_shop_detial_price);
        mFmShopDetialPriceVip = findViewById(R.id.fm_shop_detial_price_vip);
        mFmShopDetialGuigetext = findViewById(R.id.fm_shop_detial_guigetext);
        mFmShopDetialGuige = findViewById(R.id.fm_shop_detial_guige);

        mFmShopDetialGuige.setOnClickListener(this);


        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        mSdAdd.setOnClickListener(this);
        mSdBuy.setOnClickListener(this);
        mSdHomepage.setOnClickListener(this);
        mSdServer.setOnClickListener(this);
        mSdShopcart.setOnClickListener(this);

        initWebView();

        evaluateAdapter = new EvaluateAdapter(null);
        linearLayoutManager = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(evaluateAdapter);


        greenDaoAssist = new GreenDaoAssist(((DemoApplication) getApplication()).databaseAssist);

        setLaber();

    }

    @Override
    protected void onResume() {
        super.onResume();
//        Logger.t("------------------- 重新加载");
        //可以保证多端数据及时同步
        if (goodsId > -1) {
            getPresenter().getGoodsDetail(goodsId);
        }
    }

    private void initWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setDefaultTextEncodingName("utf-8");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        } else {
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }

        webSettings.setJavaScriptEnabled(true);//支持js
//        webSettings.setBuiltInZoomControls(true); // 显示放大缩小
//        webSettings.setSupportZoom(true); // 可以缩放

//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setDatabaseEnabled(true);
//        WebView.setWebContentsDebuggingEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //适配手机宽度
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                //重置webview中img标签的图片大小
//                webView.loadUrl("javascript:(function(){" +
//                        "var objs = document.getElementsByTagName('img'); " +
//                        "for(var i=0;i<objs.length;i++)  " +
//                        "{"
//                        + "var img = objs[i];   " +
//                        "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
//                        "}" +
//                        "})()");
//            }

        });

    }

    private void setLaber() {
        long number = greenDaoAssist.queryGoodsTypeNumber();
        if (number > 0) {
            mSdShopcartNum.setText(number > 99 ? "99" : number + "");
            mSdShopcartNum.setVisibility(View.VISIBLE);
        } else {
            mSdShopcartNum.setVisibility(View.GONE);
        }
    }

    @Override
    protected GoodsDetialPresenter initPersenter() {
        return new GoodsDetialPresenter(this);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.fm_shop_detial_guige:
                // 点击规格
                getGoodsItemData(0);
                break;
            case R.id.fm_shop_detial_tab1:
                showGoodsDetail();
                break;
            case R.id.fm_shop_detial_tab2:
                showEvaluateList();
                break;
            case R.id.sd_add:
                getGoodsItemData(1);
                break;
            case R.id.sd_buy:
                getGoodsItemData(2);
                break;
            case R.id.sd_homepage:
                Intent intent_home = new Intent(context, MainNavActivity.class);
                intent_home.putExtra("position", 0);
                startActivity(intent_home);
                break;
            case R.id.sd_server:
                showPremission_call_phone("0891-6373803");
                break;
            case R.id.sd_shopcart:
                Intent intent_shopcart = new Intent(context, MainNavActivity.class);
                intent_shopcart.putExtra("position", 2);
                startActivity(intent_shopcart);
                break;
        }
    }


    public GoodsDetailInfoBean goodsDetailInfoBean;

    @Override
    public void getGoodsDetailSuccess(GoodsDetailInfoBean infoBean) {
        this.goodsDetailInfoBean = infoBean;
        //商品信息
        if (infoBean != null) {
            if (infoBean.getImageList() != null && infoBean.getImageList().size() > 0) {
                mFmShopDetialIcon.setVisibility(View.GONE);
                banner.setVisibility(View.VISIBLE);
                initBannerData(infoBean.getImageList());
            } else {
                mFmShopDetialIcon.setVisibility(View.VISIBLE);
                banner.setVisibility(View.GONE);
                GlideUtile.bindImageView(context, infoBean.getGoodsImage(), mFmShopDetialIcon);
            }

            if (infoBean.getIsTemai() == 0) {
                relativeLayout.setVisibility(View.VISIBLE);

                //更改库存
                if(infoBean.getIsTemai()==0){
                    if(infoBean.getSpecialSale()==0&& infoBean.getStock() > 0){
                        infoBean.setStock(1);
                    }else{
                        infoBean.setStock(0);
                    }
                }
            }

            mFmShopDetialName.setText(infoBean.getGoodsName());
            mFmShopDetialPriceVip.setText("￥" + BigDecimalUtil.roundOffString(infoBean.getSellPriceDiscount(),2));
            mFmShopDetialPrice.setText(BigDecimalUtil.roundOffString(infoBean.getSellPrice(),2));
            mFmShopDetialPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            showGoodsDetail();

        }

    }

    private List<EvaluateInfoBean> evaluateInfoBeanList;

    @Override
    public void getListDataEvaluateSuccess(List<EvaluateInfoBean> list) {
        evaluateInfoBeanList = list;
        if (list != null && list.size() > 0) {

            evaluateAdapter.setNewData(list);
            evaluateAdapter.loadMoreEnd(true);
            defaultTextview.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            defaultTextview.setText("暂无评价");
        }
    }

    @Override
    public void getGmrDataSuccess(GmrOutInfoBean infoBean) {

    }

    private void showGoodsDetail() {

        tab1.setBackgroundResource(R.color.yigou_color_red);
        tab1.setTextColor(context.getResources().getColor(R.color.color_white));
        tab2.setBackgroundResource(R.color.transparent);
        tab2.setTextColor(context.getResources().getColor(R.color.color_content));

        recyclerView.setVisibility(View.GONE);
        defaultTextview.setText("商品详情加载中...");
        webView.setVisibility(View.GONE);
        defaultTextview.setVisibility(View.VISIBLE);
        if (goodsDetailInfoBean != null) {
            String goodsDetail = goodsDetailInfoBean.getGoodsDetail();
            if (goodsDetail != null && !goodsDetail.equals("")) {
                webView.loadDataWithBaseURL(null, translation(goodsDetail), "text/html", "utf-8", null);
                Logger.t("height = " + webView.getHeight() + "---contentHeight = " + webView.getContentHeight());
                defaultTextview.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        }
        defaultTextview.setText("暂无商品详情");
    }


    private String translation(String content) {
        String replace = content.replace("&lt;", "<");
        String replace1 = replace.replace("&gt;", ">");
        String replace2 = replace1.replace("&quot;", "\"");
        String replace3 = replace2.replace("<img", "<img style=\"width:100%;height:auto;\"");
        Logger.t("html = " + replace3);
        return replace3;
    }

    private void showEvaluateList() {

        tab1.setBackgroundResource(R.color.transparent);
        tab1.setTextColor(context.getResources().getColor(R.color.color_content));
        tab2.setBackgroundResource(R.color.yigou_color_red);
        tab2.setTextColor(context.getResources().getColor(R.color.color_white));

        webView.setVisibility(View.GONE);
        defaultTextview.setText("评价列表加载中...");
        defaultTextview.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        if (goodsDetailInfoBean != null) {
            if (evaluateInfoBeanList != null && evaluateInfoBeanList.size() > 0) {
                getListDataEvaluateSuccess(evaluateInfoBeanList);
            } else {
                getPresenter().getListDataEvaluate(defaultTextview, goodsDetailInfoBean.getId(), 1, 100);
            }
        } else {
            defaultTextview.setText("暂无评价");
        }

    }


    @Override
    public void getGoodsItemSuccess(GoodsSpecInfoBean infoBean, int dialogState) {
//        if (infoBean != null && infoBean.getGoodsItemList() != null && infoBean.getGoodsItemList().size() > 0) {
        // 有规格
        goodsItemDialog(dialogState, infoBean);
//        } else {
//            //没有规格
//            //直接添加到购物车
//            DatabaseGoodsInfo goodsInfo = new DatabaseGoodsInfo();
////        商品信息
//            goodsInfo.setGoodsId(goodsDetailInfoBean.getId()+"");
//            goodsInfo.setGoodsName(goodsDetailInfoBean.getGoodsName());
//            goodsInfo.setGoodsPrice(goodsDetailInfoBean.getSellPriceDiscount().floatValue());
//            goodsInfo.setGoodsNumber(1);
//            goodsInfo.setGoodsUrl(goodsDetailInfoBean.getGoodsImage());
//            goodsInfo.setGoodsTotal(100);
//            //规格
//            goodsInfo.setSpecId("");
//            goodsInfo.setSpecName("");
//
//            insertGoods(goodsInfo);
//        }
    }

    @Override
    public void addGoodsToShoppingCartSuccess() {
        SnackbarUtil.showToast(rootView, "添加成功");
    }

    //获取规格数据
    public void getGoodsItemData(int dialogState) {

        if (goodsDetailInfoBean != null && goodsDetailInfoBean.getSpecialSale() == 1) {
            SnackbarUtil.showToast(rootView, "此商品每天限购一件");
            return;
        }

        if (!ispost) {
            if (goodsDetailInfoBean != null) {
                getPresenter().getGoodsItem(goodsDetailInfoBean.getId(), dialogState);
            }
        } else {
            goodsItemDialog(dialogState, goodsSpecInfoBean);
        }
    }


    private boolean ispost = false;
    private GoodsSpecInfoBean goodsSpecInfoBean;

    //弹框
    public void goodsItemDialog(int dialogState, GoodsSpecInfoBean goodsSpecInfoBean) {
        ispost = true;
        this.goodsSpecInfoBean = goodsSpecInfoBean;
        MoreGoodsSpecInfoDialog infoDialog = new MoreGoodsSpecInfoDialog.Builder(context)
                .setCancelable(false)
                .setDialogState(dialogState)
                .setGoodsInfo(goodsDetailInfoBean)
                .setGoodsItemList(goodsSpecInfoBean)
                .setOnMoreGoodsSpecInfoListener(this)
                .create();
        infoDialog.show(getSupportFragmentManager(), "tishi_itemspec");
    }

    @Override
    public void onOnMoreGoodsAffirmClick(View view, String specIds, String specName, float goodsPrice,float goodsPriceVip,  int number) {

        //判断是否登陆，跳转登陆页面登陆
        if (YigouConstant.token.equals("")) {
            relogin();
            return;
        }

        //立即购买
        if (goodsDetailInfoBean != null) {
            DatabaseGoodsInfo goodsInfo = new DatabaseGoodsInfo();
            goodsInfo.setId(null);
            goodsInfo.setGoodsId(goodsDetailInfoBean.getId() + "");
            goodsInfo.setGoodsName(goodsDetailInfoBean.getGoodsName());
            goodsInfo.setGoodsPrice(goodsPrice);
            goodsInfo.setGoodsPriceVip(goodsPriceVip);
            goodsInfo.setGoodsTotal(goodsDetailInfoBean.getStock());
            goodsInfo.setGoodsAddTime(0);
            goodsInfo.setGoodsNumber(number);
            goodsInfo.setGoodsUrl(goodsDetailInfoBean.getGoodsImage());
            goodsInfo.setShopId(goodsDetailInfoBean.getShopId() + "");
            goodsInfo.setSpecId(specIds);
            goodsInfo.setSpecName(specName);
            //            商品类型
            int goodsType = goodsDetailInfoBean.getGoodsType();
            goodsInfo.setGoodsType(goodsType);
            if (goodsType == 3 && goodsDetailInfoBean.getPresell() != null) {
                goodsInfo.setStartTime(goodsDetailInfoBean.getPresell().getStartTime());
                goodsInfo.setEndTime(goodsDetailInfoBean.getPresell().getEndTime());
                goodsInfo.setGoodsTotal(goodsDetailInfoBean.getPresell().getLimitNum());
            } else {
                goodsInfo.setGoodsTotal(goodsDetailInfoBean.getStock());
            }

            //统计数据
            DemoConstant.jgPurchaseBeanList.clear();
            JgPurchaseBean bean = new JgPurchaseBean();
            bean.setPurchaseGoodsid(goodsDetailInfoBean.getId() + "");
            bean.setPurchaseGoodsName(goodsDetailInfoBean.getGoodsName());
            bean.setPurchasePrice(goodsPriceVip);
            bean.setPurchaseSuccess(true);
            bean.setPurchaseGoodsCount(number);
            bean.setPurchaseGoodsType(goodsDetailInfoBean.getCnname());
            bean.setPurchaseCurrency(Currency.CNY);
            DemoConstant.jgPurchaseBeanList.add(bean);

            ArrayList<DatabaseGoodsInfo> list = new ArrayList<DatabaseGoodsInfo>();
            list.add(goodsInfo);

            Intent intent = new Intent(context, GoodsAffirmActivity.class);
            intent.putExtra("code", 1);
            intent.putExtra("list", list);
            intent.putExtra("shopid", goodsDetailInfoBean.getShopId() + "");
            skipActivity(intent);
        }


    }

    @Override
    public void onOnMoreGoodsAddcartClick(View view, String specIds, String specName, float goodsPrice,float goodsPriceVip,  int number) {


        //添加购物车
        DatabaseGoodsInfo goodsInfo = new DatabaseGoodsInfo();
//        商品信息
        goodsInfo.setGoodsId(goodsDetailInfoBean.getId() + "");
        goodsInfo.setGoodsName(goodsDetailInfoBean.getGoodsName());
        goodsInfo.setGoodsPrice(goodsPrice);
        goodsInfo.setGoodsPriceVip(goodsPriceVip);
        goodsInfo.setGoodsNumber(number);
        goodsInfo.setGoodsUrl(goodsDetailInfoBean.getGoodsImage());
        //规格
        goodsInfo.setSpecId(specIds);
        goodsInfo.setSpecName(specName);
//        店铺信息
        goodsInfo.setShopId(goodsDetailInfoBean.getShopId() + "");

        //            商品类型
        int goodsType = goodsDetailInfoBean.getGoodsType();
        goodsInfo.setGoodsType(goodsType);
        if (goodsType == 3 && goodsDetailInfoBean.getPresell() != null) {
            goodsInfo.setStartTime(goodsDetailInfoBean.getPresell().getStartTime());
            goodsInfo.setEndTime(goodsDetailInfoBean.getPresell().getEndTime());
            goodsInfo.setGoodsTotal(goodsDetailInfoBean.getPresell().getLimitNum());
        } else {
            goodsInfo.setGoodsTotal(goodsDetailInfoBean.getStock());
        }

        greenDaoAssist.insertGoods(goodsDetailInfoBean.getShopId() + "", goodsInfo, isChange -> DemoConstant.isChangeDatabase = isChange);

        setLaber();

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

    private void initBannerData(List<GoodsDetailInfoBean.Images> list) {
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public NetworkImageHolderView createHolder(View itemView) {
                return new NetworkImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_banner;
            }
        }, list)
                .setCanLoop(false)//默认循环
                .setFirstItemPos(0)
//                .setCurrentItem(0,false)
                .setPageIndicator(new int[]{R.mipmap.dian_, R.mipmap.dian})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
    }

    public class NetworkImageHolderView extends Holder<GoodsDetailInfoBean.Images> {

        private ImageView imageView;

        public NetworkImageHolderView(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View itemView) {
            imageView = itemView.findViewById(R.id.imageview);
        }

        @Override
        public void updateUI(GoodsDetailInfoBean.Images data) {
            GlideUtile.bindImageView(context, data.getGoodsImage(), imageView);
        }

    }


}
