package com.work.doctor.fruits.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.GoodsInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tbase.tdialog.BaseDialog;
import com.trjx.tlibs.uils.ToastUtil2;
import com.work.doctor.fruits.R;

import java.util.List;

/**
 * 商品列表页面使用
 */
public class MoreGoodsSpecInfoDialog2 extends BaseDialog implements View.OnClickListener {

    private ViewHolder viewHolder;
    private Builder builder;
    private View contentView;

    public interface OnMoreGoodsListSpecInfoListener {
        /**
         * 点击回调的方法
         *
         * @param view
         */
        void onOnMoreGoodsListAddcartClick(View view,
                                           String specIds,
                                           String specName,
                                           float goodsPrice,
                                           int number);
    }


    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.dialog_goodspecinfo_addcart) {
            if (builder.goodsSpecInfoBean != null) {
                if (builder.specId.equals("") || builder.specName.equals("")) {
                    ToastUtil2.showToast(builder.context, "请选择规格");
                    return;
                }
            }
            //每次只能添加一个商品
            if (builder.goodsStock == 0) {
                ToastUtil2.showToast(builder.context, "此规格暂无库存");
                return;
            }
            if (builder.listener != null) {
                builder.listener.onOnMoreGoodsListAddcartClick(viewHolder.mDialogGoodspecinfoAddcart,
                        builder.specId,
                        builder.specName,
                        builder.goodsPrice,
                        1
                );
            }
            dismiss();

        } else if (ids == R.id.dialog_goodspecinfo_close) {
            dismiss();
        }

    }

    public static class Builder {
        private Context context;
        @FloatRange(from = 0.0, to = 1.0)
        private float alpha;
        private boolean cancelable;// 默认可以取消

        private OnMoreGoodsListSpecInfoListener listener;

        private LayoutInflater inflater;

        private GoodsSpecInfoBean goodsSpecInfoBean;

        private GoodsInfoBean goodsInfoBean;

//        private int number;

        //商品库存 ：
        private int goodsStock;

        //商品规格id
        private String specId;
        //商品规格名字
        private String specName;

        private float goodsPrice;

        public Builder(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            cancelable = true;
            alpha = 0.4f;
//            number = 1;
//            affirmText = "";

            goodsStock = 0;

            specId = "";
            specName = "";

            goodsPrice = 0.0f;

        }


        // 设置规格数据集合
        public Builder setGoodsItemList(GoodsSpecInfoBean goodsSpecInfoBean) {
            this.goodsSpecInfoBean = goodsSpecInfoBean;
            return this;
        }

        // 设置商品信息
        public Builder setGoodsInfo(GoodsInfoBean goodsInfo) {
            this.goodsInfoBean = goodsInfo;
            return this;
        }


        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setAlpha(float alpha) {
            this.alpha = alpha;
            return this;
        }

        public Builder setOnMoreGoodsListSpecInfoListener(OnMoreGoodsListSpecInfoListener listener) {
            this.listener = listener;
            return this;
        }

        public MoreGoodsSpecInfoDialog2 create() {
            MoreGoodsSpecInfoDialog2 dialog = new MoreGoodsSpecInfoDialog2();
            dialog.builder = this;
            dialog.contentView = inflater.inflate(R.layout.dialog_goods_specinfo2, null);
            dialog.viewHolder = new ViewHolder(dialog.contentView);

            dialog.viewHolder.mDialogGoodspecinfoPrice.setText("￥ " + BigDecimalUtil.roundOffString(goodsInfoBean.getSellPriceDiscount(),2));
            if (goodsSpecInfoBean != null) {
                List<GoodsSpecInfoBean.GoodsItemListBean> specListBeanList = goodsSpecInfoBean.getGoodsItemList();
                if (specListBeanList != null && specListBeanList.size() > 0) {

                    GoodsSpecInfoTitleAdapter infoTitleAdapter = new GoodsSpecInfoTitleAdapter(specListBeanList) {

                        @Override
                        public void changeDataEvent() {
                            //改变数据

                            changeData(specListBeanList, dialog.viewHolder);
                        }
                    };
                    dialog.viewHolder.mDialogGoodspecinfoRecyclerview.setLayoutManager(new LinearLayoutManager(context) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    });
                    dialog.viewHolder.mDialogGoodspecinfoRecyclerview.setAdapter(infoTitleAdapter);

                    //初始化
                    changeData(specListBeanList, dialog.viewHolder);

                }
            }

            dialog.viewHolder.mDialogGoodspecinfoClose.setOnClickListener(dialog);
            dialog.viewHolder.mDialogGoodspecinfoAddcart.setOnClickListener(dialog);

            return dialog;
        }

        private void changeData(List<GoodsSpecInfoBean.GoodsItemListBean> specListBeanList, ViewHolder viewHolder) {
            goodsStock = 0;
            specId = "";
            specName = "";
            for (int i = 0; i < specListBeanList.size(); i++) {
                GoodsSpecInfoBean.GoodsItemListBean listBean = specListBeanList.get(i);
                specId = specId + listBean.getSpecList().get(listBean.getSelectIndex()).getId() + ",";
                specName = specName + listBean.getItemName() + "：" + listBean.getSpecList().get(listBean.getSelectIndex()).getAttrValue() + "  ";
            }
            if (specId.endsWith(",")) {
                specId = specId.substring(0, specId.length() - 1);
            }
            //查询价格，切改变
            List<GoodsSpecInfoBean.ItemSkuBean> itemSkuBeanList = goodsSpecInfoBean.getItemSku();
            for (int i = 0; i < itemSkuBeanList.size(); i++) {
                GoodsSpecInfoBean.ItemSkuBean itemSkuBean = itemSkuBeanList.get(i);
                if (itemSkuBean.getAttrStrId().equals(specId)) {
                    goodsPrice = itemSkuBean.getPriceDiscount();
                    goodsStock = itemSkuBean.getStock();
                    viewHolder.mDialogGoodspecinfoPrice.setText("￥ " + BigDecimalUtil.roundOffString(goodsPrice, 2));
                    return;
                }
            }

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
    }

    //一般用于创建复杂内容弹窗或全屏展示效果的场景，UI 复杂，功能复杂，一般有网络请求等异步操作。
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        viewHolder.mDialogGoodspecinfoMinus.setOnClickListener(this);
//        viewHolder.mDialogGoodspecinfoAdd.setOnClickListener(this);

//        viewHolder.mDialogGoodspecinfoClose.setOnClickListener(this);
//        viewHolder.mDialogGoodspecinfoAddcart.setOnClickListener(this);
        return contentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置外部点击不取消
        if (!builder.cancelable) {
            getDialog().setCancelable(false);
            getDialog().setCanceledOnTouchOutside(false);
        }
        //经测试，在这里设置背景色才起作用
        Window window = getDialog().getWindow();
//        window.getDecorView().setPadding(0,0,0,0);
//        window.setBackgroundDrawableResource(R.color.colorAccent);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        //让宽度充满屏幕
//        lp.width = getResources().getDisplayMetrics().widthPixels;
        lp.width = getResources().getDisplayMetrics().widthPixels - 100;
//        lp.height = getResources().getDisplayMetrics().heightPixels;
        lp.dimAmount = builder.alpha;
        lp.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lp.windowAnimations = R.style.MyDialogAnimation;
        window.setAttributes(lp);
    }

    static class ViewHolder {
        View view;
        TextView mDialogGoodspecinfoPrice;
        //        TextView mDialogGoodspecinfoGuige;
        RecyclerView mDialogGoodspecinfoRecyclerview;
        //        TextView mDialogGoodspecinfoMinus;
//        TextView mDialogGoodspecinfoNumber;
//        TextView mDialogGoodspecinfoAdd;
        TextView mDialogGoodspecinfoAddcart;
        ImageView mDialogGoodspecinfoClose;

        ViewHolder(View view) {
            this.view = view;

            mDialogGoodspecinfoPrice = view.findViewById(R.id.dialog_goodspecinfo_price);
//            mDialogGoodspecinfoGuige = view.findViewById(R.id.dialog_goodspecinfo_guige);
            mDialogGoodspecinfoRecyclerview = view.findViewById(R.id.dialog_goodspecinfo_recyclerview);
//            mDialogGoodspecinfoMinus = view.findViewById(R.id.dialog_goodspecinfo_minus);
//            mDialogGoodspecinfoNumber = view.findViewById(R.id.dialog_goodspecinfo_number);
//            mDialogGoodspecinfoAdd = view.findViewById(R.id.dialog_goodspecinfo_add);
            mDialogGoodspecinfoAddcart = view.findViewById(R.id.dialog_goodspecinfo_addcart);
            mDialogGoodspecinfoClose = view.findViewById(R.id.dialog_goodspecinfo_close);

        }
    }


}