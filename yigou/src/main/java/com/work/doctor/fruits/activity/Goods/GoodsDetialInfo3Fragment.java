package com.work.doctor.fruits.activity.Goods;

import android.graphics.Paint;
import android.os.Build;
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
import com.t.databaselib.bean.PresellInfoBean;
import com.t.httplib.yigou.bean.resp.EvaluateInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsDetailInfoBean;
import com.trjx.tbase.mvp.TView;
import com.trjx.tlibs.uils.GlideUtile;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.TextStyleHelper;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.EvaluateAdapter;
import com.work.doctor.fruits.base.DemoMVPFragment;
import com.work.doctor.fruits.base.DemoPresenter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class GoodsDetialInfo3Fragment extends DemoMVPFragment<TView, DemoPresenter<TView>>
        implements View.OnClickListener {

    private ImageView mFmShopDetialIcon;
    private ConvenientBanner banner;
    private TextView mFmShopDetialName;
    private TextView mFmShopDetialPrice;
    private TextView mFmShopDetialPriceVip;
    private TextView mFmShopDetialGuigetext;
    private LinearLayout mFmShopDetialGuige;

    private TextView tab1, tab2;
    private WebView webView;
    private RecyclerView recyclerView;
    private TextView defaultTextview;
    private RelativeLayout relativeLayout;

    private TextView textRemark;
    private TextView textTime;
    private TextView textDjsText;
    private TextView textDjsTime;
    private ImageView gwzn;

    private EvaluateAdapter evaluateAdapter;
    private LinearLayoutManager linearLayoutManager;

    private GoodsDetial3Activity goodsDetialActivity;
    private int goodsId;

    private SimpleDateFormat dateFormat;

    public GoodsDetialInfo3Fragment(int goodsId) {
        this.goodsId = goodsId;
        dateFormat = new SimpleDateFormat("MM月dd日 HH:mm:ss");
    }

    @Override
    protected int initLayout() {
        return R.layout.fm_shop_detial3;
    }

    @Override
    protected void initFragmentView(View view) {

        goodsDetialActivity = (GoodsDetial3Activity) getActivity();

        tab1 = view.findViewById(R.id.fm_shop_detial_tab1);
        tab2 = view.findViewById(R.id.fm_shop_detial_tab2);
        webView = view.findViewById(R.id.webview);
        recyclerView = view.findViewById(R.id.recyclerview);
        defaultTextview = view.findViewById(R.id.default_textview);

        textRemark = view.findViewById(R.id.fm_shop_detial_remark);
        textTime = view.findViewById(R.id.fm_shop_detial_time);
        textDjsText = view.findViewById(R.id.jstext);
        textDjsTime = view.findViewById(R.id.fm_shop_detial_djs_time);
        gwzn = view.findViewById(R.id.gwzn);


        mFmShopDetialIcon = view.findViewById(R.id.fm_shop_detial_icon);
        banner = view.findViewById(R.id.fm_shop_detial_banner);
        mFmShopDetialName = view.findViewById(R.id.fm_shop_detial_name);
        mFmShopDetialPrice = view.findViewById(R.id.fm_shop_detial_price);
        mFmShopDetialPriceVip = view.findViewById(R.id.fm_shop_detial_price_vip);
        mFmShopDetialGuigetext = view.findViewById(R.id.fm_shop_detial_guigetext);
        mFmShopDetialGuige = view.findViewById(R.id.fm_shop_detial_guige);

        relativeLayout = view.findViewById(R.id.fm_shop_detial_rl);

        mFmShopDetialGuige.setOnClickListener(this);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);

        initWebView();

        evaluateAdapter = new EvaluateAdapter(null);
        linearLayoutManager = new LinearLayoutManager(activity) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(evaluateAdapter);


//        GlideUtile.bindImageView(getContext(), R.mipmap.ic_launcher, gwzn);

    }

    @Override
    public void initData() {
        super.initData();
        GoodsDetailInfoBean infoBean = goodsDetialActivity.goodsDetailInfoBean;
        //商品信息
        if (infoBean != null) {
            if (infoBean.getImageList() != null && infoBean.getImageList().size() > 0) {
                mFmShopDetialIcon.setVisibility(View.GONE);
                banner.setVisibility(View.VISIBLE);
                initBannerData(infoBean.getImageList());
            } else {
                mFmShopDetialIcon.setVisibility(View.VISIBLE);
                banner.setVisibility(View.GONE);
                GlideUtile.bindImageView(activity, infoBean.getGoodsImage(), mFmShopDetialIcon);
            }

            if (infoBean.getIsTemai() == 0) {
                relativeLayout.setVisibility(View.VISIBLE);
                //更改库存
                if (infoBean.getIsTemai() == 0) {
                    if (infoBean.getSpecialSale() == 0 && infoBean.getStock() > 0) {
                        infoBean.setStock(1);
                    } else {
                        infoBean.setStock(0);
                    }
                }
            }

            PresellInfoBean presellInfoBean = infoBean.getPresell();

//            textRemark.setText("已售" + presellInfoBean.getSellTotalNum() + "份/限量"
//                    + presellInfoBean.getStock() + "份(每人限购" + presellInfoBean.getLimitNum() + "份)");

            String ygouStr = "(每人限购" + presellInfoBean.getLimitNum() + "份)";
            String string = "已售" + presellInfoBean.getSellTotalNum() + "份/限量" + presellInfoBean.getStock() + "份" + ygouStr;
            textRemark.setText(new TextStyleHelper(string)
                    .addForeColorSpan(activity.getResources().getColor(R.color.ys_color_two),
                            string.length() - ygouStr.length(),
                            string.length()
                    ).show());

            textTime.setText("预售时间：" + dateFormat.format(new Date(presellInfoBean.getStartTime()))
                    + "\n送货时间：" + dateFormat.format(new Date(presellInfoBean.getEndTime())));


            mFmShopDetialName.setText(infoBean.getGoodsName());
            mFmShopDetialPriceVip.setText("￥" + BigDecimalUtil.roundOffString(presellInfoBean.getPresellPrice(), 2));
            mFmShopDetialPrice.setText("￥" + BigDecimalUtil.roundOffString(infoBean.getSellPrice(), 2));
            mFmShopDetialPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);


            showGoodsDetail();
            String url = "https://rulongegou.oss-cn-beijing.aliyuncs.com/background_icon/ShoppingDirectory.png";
            GlideUtile.bindImageView(getContext(), url, gwzn);
        }

    }

    public void setTextDjsText(String textStr) {
        if (textDjsText != null) {
            textDjsText.setText(textStr);
        }
    }

    public void setTextDjsTime(String textStr) {
        if (textDjsTime != null) {
            textDjsTime.setText(textStr);
        }
    }


    @Override
    protected DemoPresenter initPersenter() {
        return new DemoPresenter(this);
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

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.fm_shop_detial_guige:
                goodsDetialActivity.getGoodsItemData(0);
                break;
            case R.id.fm_shop_detial_tab1:
                showGoodsDetail();
                break;
            case R.id.fm_shop_detial_tab2:
                showEvaluateList();
                break;
        }
    }

    public void getListDataEvaluateSuccess(List<EvaluateInfoBean> list) {
        if (list != null && list.size() > 0) {
            evaluateAdapter.setNewData(list);
            evaluateAdapter.loadMoreEnd(true);
            defaultTextview.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            defaultTextview.setText("暂无评价");
        }
    }

    private void showGoodsDetail() {

        tab1.setBackgroundResource(R.color.yigou_color_red);
        tab1.setTextColor(getResources().getColor(R.color.color_white));
        tab2.setBackgroundResource(R.color.transparent);
        tab2.setTextColor(getResources().getColor(R.color.color_content));

        recyclerView.setVisibility(View.GONE);
        defaultTextview.setText("商品详情加载中...");
        webView.setVisibility(View.GONE);
        defaultTextview.setVisibility(View.VISIBLE);
        if (goodsDetialActivity.goodsDetailInfoBean != null) {
            String goodsDetail = goodsDetialActivity.goodsDetailInfoBean.getGoodsDetail();
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
        tab1.setTextColor(getResources().getColor(R.color.color_content));
        tab2.setBackgroundResource(R.color.yigou_color_red);
        tab2.setTextColor(getResources().getColor(R.color.color_white));

        webView.setVisibility(View.GONE);
        defaultTextview.setText("评价列表加载中...");
        defaultTextview.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        if (goodsDetialActivity.goodsDetailInfoBean != null) {
            if (goodsDetialActivity.evaluateInfoBeanList != null && goodsDetialActivity.evaluateInfoBeanList.size() > 0) {
                getListDataEvaluateSuccess(goodsDetialActivity.evaluateInfoBeanList);
            } else {
                goodsDetialActivity.getPresenter().getListDataEvaluate(defaultTextview, goodsDetialActivity.goodsDetailInfoBean.getId(), 1, 100);
            }
        } else {
            defaultTextview.setText("暂无评价");
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
            GlideUtile.bindImageView(activity, data.getGoodsImage(), imageView);
        }

    }


}
