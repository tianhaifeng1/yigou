package com.work.doctor.fruits.activity.Goods;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.t.httplib.yigou.bean.resp.GoodsDetailInfoBean;
import com.trjx.tlibs.uils.GlideUtile;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.fragment.TWeb2Fragment;

import java.util.List;

public class GoodsDetialInfoFragment extends TWeb2Fragment
        implements View.OnClickListener{

    private ImageView mFmShopDetialIcon;
    private ConvenientBanner banner;
    private TextView mFmShopDetialName;
    private TextView mFmShopDetialPrice;
    private TextView mFmShopDetialPriceVip;
    private TextView mFmShopDetialGuigetext;
    private LinearLayout mFmShopDetialGuige;

    private GoodsDetialActivity goodsDetialActivity;

    public GoodsDetialInfoFragment(int code) {
        super(code);
    }

    @Override
    protected int initLayout() {
        return R.layout.fm_shop_detial;
    }

    @Override
    protected void initFragmentView(View view) {
        super.initFragmentView(view);

        goodsDetialActivity = (GoodsDetialActivity) getActivity();

        mFmShopDetialIcon = view.findViewById(R.id.fm_shop_detial_icon);
        banner = view.findViewById(R.id.fm_shop_detial_banner);
        mFmShopDetialName = view.findViewById(R.id.fm_shop_detial_name);
        mFmShopDetialPrice = view.findViewById(R.id.fm_shop_detial_price);
        mFmShopDetialPriceVip = view.findViewById(R.id.fm_shop_detial_price_vip);
        mFmShopDetialGuigetext = view.findViewById(R.id.fm_shop_detial_guigetext);
        mFmShopDetialGuige = view.findViewById(R.id.fm_shop_detial_guige);

        mFmShopDetialGuige.setOnClickListener(this);

    }

    @Override
    public void initData() {
        super.initData();
        GoodsDetailInfoBean infoBean = goodsDetialActivity.goodsDetailInfoBean;
        if (infoBean != null) {
            if (infoBean.getImageList() != null && infoBean.getImageList().size() > 0) {
                mFmShopDetialIcon.setVisibility(View.GONE);
                banner.setVisibility(View.VISIBLE);
                initBannerData(infoBean.getImageList());
            }else{
                mFmShopDetialIcon.setVisibility(View.VISIBLE);
                banner.setVisibility(View.GONE);
                GlideUtile.bindImageView(activity.context,infoBean.getGoodsImage(),mFmShopDetialIcon);
            }

            mFmShopDetialName.setText(infoBean.getGoodsName());
            mFmShopDetialPriceVip.setText("￥"+infoBean.getSellPriceDiscount());

            initHtmlContent(infoBean.getGoodsDetail());
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
                .setCanLoop(true)//默认循环
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
            GlideUtile.bindImageView(activity, data.getGoodsImage(), R.mipmap.banne_default, imageView);
        }

    }



    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids){
            case R.id.fm_shop_detial_guige:
                goodsDetialActivity.getGoodsItemData(0);
                break;
        }
    }

}
