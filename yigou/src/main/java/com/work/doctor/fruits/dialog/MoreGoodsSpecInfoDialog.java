package com.work.doctor.fruits.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.t.databaselib.BigDecimalUtil;
import com.t.httplib.yigou.bean.resp.GoodsDetailInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tbase.tdialog.BaseDialog;
import com.trjx.tlibs.uils.GlideUtile;
import com.trjx.tlibs.uils.SnackbarUtil;
import com.trjx.tlibs.uils.ToastUtil2;
import com.work.doctor.fruits.R;

import java.util.List;

/**
 * 商品详情页面使用
 */
public class MoreGoodsSpecInfoDialog extends BaseDialog implements View.OnClickListener {
    private ViewHolder viewHolder;
    private Builder builder;
    private static View contentView;

    public interface OnMoreGoodsSpecInfoListener {
        /**
         * 点击回调的方法
         *
         * @param view
         */
        void onOnMoreGoodsAffirmClick(View view, String specIds, String specName, float goodsPrice, float goodsPriceVip, int number);

        void onOnMoreGoodsAddcartClick(View view, String specIds, String specName, float goodsPrice, float goodsPriceVip, int number);
    }


    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.dialog_goodspecinfo_affirm) {
            if (builder.goodsSpecInfoBean != null) {
                if (builder.specId.equals("") || builder.specName.equals("")) {
                    ToastUtil2.showToast(builder.context, "请选择规格");
                    return;
                }
            }

            if (builder.goodsStock == 0) {
                ToastUtil2.showToast(builder.context, "此规格暂无库存");
                return;
            }

            if (builder.goodsLimitStock == 0) {
                ToastUtil2.showToast(builder.context, "限购数异常");
                return;
            }

            if (builder.number <= 0) {
                ToastUtil2.showToast(builder.context, "请选择数量");
                return;
            }

            if (builder.listener != null) {
                if (builder.dialogState == 1) {
                    builder.listener.onOnMoreGoodsAddcartClick(viewHolder.mDialogGoodspecinfoAddcart,
                            builder.specId,
                            builder.specName,
                            builder.goodsPrice,
                            builder.goodsPriceVip,
                            builder.number
                    );
                } else {
                    builder.listener.onOnMoreGoodsAffirmClick(viewHolder.mDialogGoodspecinfoAffirm,
                            builder.specId,
                            builder.specName,
                            builder.goodsPrice,
                            builder.goodsPriceVip,
                            builder.number
                    );
                }
            }
            dismiss();
        } else if (ids == R.id.dialog_goodspecinfo_addcart) {
            if (builder.goodsSpecInfoBean != null) {
                if (builder.specId.equals("") || builder.specName.equals("")) {
                    ToastUtil2.showToast(builder.context, "请选择规格");
                    return;
                }
            }
            if (builder.goodsStock == 0) {
                ToastUtil2.showToast(builder.context, "此规格暂无库存");
                return;
            }

            if (builder.goodsLimitStock == 0) {
                ToastUtil2.showToast(builder.context, "限购数异常");
                return;
            }

            if (builder.number <= 0) {
                ToastUtil2.showToast(builder.context, "请选择数量");
                return;
            }
            if (builder.listener != null) {
                builder.listener.onOnMoreGoodsAddcartClick(viewHolder.mDialogGoodspecinfoAddcart,
                        builder.specId,
                        builder.specName,
                        builder.goodsPrice,
                        builder.goodsPriceVip,
                        builder.number
                );
            }
            dismiss();

        } else if (ids == R.id.dialog_goodspecinfo_close) {
            dismiss();
        } else if (ids == R.id.dialog_goodspecinfo_add) {

            if (builder.number < builder.goodsLimitStock) {
                viewHolder.mDialogGoodspecinfoNumber.setText((++builder.number) + "");
            }

        } else if (ids == R.id.dialog_goodspecinfo_minus) {
            //最少一个
            if (builder.number > 1) {
                viewHolder.mDialogGoodspecinfoNumber.setText(--builder.number + "");
            }
        }

    }

    public static class Builder {
        private Context context;
        @FloatRange(from = 0.0, to = 1.0)
        private float alpha;
        private boolean cancelable;// 默认可以取消

        private int dialogState;

        private OnMoreGoodsSpecInfoListener listener;

        private LayoutInflater inflater;

        private GoodsSpecInfoBean goodsSpecInfoBean;

        private GoodsDetailInfoBean goodsDetailInfoBean;

        private int number;

        //商品库存 ：
        private int goodsStock;
        private int goodsLimitStock;

        //商品规格id
        private String specId;
        //商品规格名字
        private String specName;

        private float goodsPrice;
        private float goodsPriceVip;

        public Builder(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            cancelable = true;
            alpha = 0.4f;
            number = 1;
//            affirmText = "";
            goodsStock = 0;
            goodsLimitStock = 0;

            specId = "";
            specName = "";
            goodsPrice = 0.0f;
            goodsPriceVip = 0.0f;
        }


        // 设置规格数据集合
        public Builder setDialogState(int dialogState) {
            this.dialogState = dialogState;
            return this;
        }

//        // 设置库存
//        public Builder setGoodsStock(int goodsStock) {
//            this.goodsStock = goodsStock;
//            return this;
//        }


        // 设置规格数据集合
        public Builder setGoodsItemList(GoodsSpecInfoBean goodsSpecInfoBean) {
            this.goodsSpecInfoBean = goodsSpecInfoBean;
            return this;
        }

        // 设置商品信息
        public Builder setGoodsInfo(GoodsDetailInfoBean goodsInfo) {
            this.goodsDetailInfoBean = goodsInfo;
            goodsPrice = goodsInfo.getSellPrice().floatValue();
            goodsPriceVip = goodsInfo.getSellPriceDiscount().floatValue();
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

        public Builder setOnMoreGoodsSpecInfoListener(OnMoreGoodsSpecInfoListener listener) {
            this.listener = listener;
            return this;
        }

        public MoreGoodsSpecInfoDialog create() {
            MoreGoodsSpecInfoDialog dialog = new MoreGoodsSpecInfoDialog();
            dialog.builder = this;
            dialog.contentView = inflater.inflate(R.layout.dialog_goods_specinfo, null);
            dialog.viewHolder = new ViewHolder(dialog.contentView);

            dialog.viewHolder.mDialogGoodspecinfoName.setText(goodsDetailInfoBean.getGoodsName());
            dialog.viewHolder.mDialogGoodspecinfoNumber.setText(number + "");


            GlideUtile.bindImageView(context, goodsDetailInfoBean.getGoodsImage(), dialog.viewHolder.mDialogGoodspecinfoIcon);

            dialog.viewHolder.mDialogGoodspecinfoPrice.setText("￥ " + BigDecimalUtil.roundOffString(goodsPriceVip, 2));

            String affirmText = "确认";
            if (dialogState == 0) {
                affirmText = "立即购买";
                dialog.viewHolder.mDialogGoodspecinfoAddcart.setVisibility(View.VISIBLE);
                dialog.viewHolder.mDialogGoodspecinfoAffirm.setBackgroundResource(R.drawable.selector_buy_now);
            } else if (dialogState == 1) {
                affirmText = "加入购物车";
            } else if (dialogState == 2) {
                affirmText = "立即购买";
            }
            dialog.viewHolder.mDialogGoodspecinfoAffirm.setText(affirmText);

            if (goodsSpecInfoBean != null) {
                List<GoodsSpecInfoBean.GoodsItemListBean> specListBeanList = goodsSpecInfoBean.getGoodsItemList();

                if (specListBeanList != null && specListBeanList.size() > 0) {
                    dialog.viewHolder.mDialogGoodspecinfoRecyclerview.setVisibility(View.VISIBLE);

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
            } else {
                if (goodsDetailInfoBean.getGoodsType() == 3 && goodsDetailInfoBean.getPresell() != null) {
                    //预售商品
                    goodsStock = goodsDetailInfoBean.getStock();
                    goodsLimitStock = goodsDetailInfoBean.getPresell().getLimitNum();
                    //判断库存
                    if (goodsStock < goodsLimitStock) {
                        goodsLimitStock = goodsStock;
                    }
                    dialog.viewHolder.mDialogGoodspecinfoStock.setText("库存：" + goodsStock);
                } else {
                    goodsStock = goodsDetailInfoBean.getStock();
                    goodsLimitStock = goodsStock;
                    dialog.viewHolder.mDialogGoodspecinfoStock.setText("库存：" + goodsStock);
                }
                //没有库存的情况：
                if(goodsLimitStock==0){

                    dialog.viewHolder.mDialogGoodspecinfoNumber.setEnabled(false);
                    dialog.viewHolder.mDialogGoodspecinfoAffirm.setEnabled(false);
                    if (dialogState == 0) {
                        dialog.viewHolder.mDialogGoodspecinfoAddcart.setEnabled(false);
                        dialog.viewHolder.mDialogGoodspecinfoAddcart.setBackgroundResource(R.drawable.dialog_goods_specinfo_textview_addcart_no);
                        dialog.viewHolder.mDialogGoodspecinfoAffirm.setBackgroundResource(R.drawable.dialog_goods_specinfo_textview_buy_no);
                    } else {
                        dialog.viewHolder.mDialogGoodspecinfoAffirm.setBackgroundResource(R.drawable.dialog_goods_specinfo_textview_no);
                    }

                }

            }
            dialog.viewHolder.mDialogGoodspecinfoNumber.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (goodsLimitStock > 0) {
                        String string = s.toString().trim();
                        if (string == null || string.equals("")) {
//                        number = 0;
                            return;
                        }
                        int numberC = Integer.parseInt(string);
                        if (numberC > goodsLimitStock) {
                            if(goodsStock == goodsLimitStock){
                                SnackbarUtil.showToast(contentView, "当前库存仅有 " + goodsLimitStock + " 请重新输入");
                            }else{
                                SnackbarUtil.showToast(contentView, "当前限购 " + goodsLimitStock + " 请重新输入");
                            }
                            dialog.viewHolder.mDialogGoodspecinfoNumber.setText("1");
                            dialog.viewHolder.mDialogGoodspecinfoNumber.setSelection(1);
                            number = 1;
                        } else {
                            number = numberC;
                        }
                    }
                }
            });


            return dialog;
        }

        private void changeData(List<GoodsSpecInfoBean.GoodsItemListBean> specListBeanList, ViewHolder viewHolder) {
            goodsStock = 0;
            goodsLimitStock = 0;
            specId = "";
            specName = "";
            number = 1;
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
            e:
            for (int i = 0; i < itemSkuBeanList.size(); i++) {
                GoodsSpecInfoBean.ItemSkuBean itemSkuBean = itemSkuBeanList.get(i);
                if (itemSkuBean.getAttrStrId().equals(specId)) {
                    goodsPrice = itemSkuBean.getPrice();
                    goodsPriceVip = itemSkuBean.getPriceDiscount();
                    goodsStock = itemSkuBean.getStock();
                    goodsLimitStock = goodsStock;
                    viewHolder.mDialogGoodspecinfoStock.setText("库存：" + goodsStock);
                    viewHolder.mDialogGoodspecinfoPrice.setText("￥ " + BigDecimalUtil.roundOffString(goodsPriceVip, 2));
                    break e;
                }
            }

            viewHolder.mDialogGoodspecinfoNumber.setText("" + number);

            if (goodsLimitStock > 0) {
                viewHolder.mDialogGoodspecinfoNumber.setEnabled(true);
                viewHolder.mDialogGoodspecinfoAffirm.setEnabled(true);
                if (dialogState == 0) {
                    viewHolder.mDialogGoodspecinfoAddcart.setEnabled(true);
                    viewHolder.mDialogGoodspecinfoAddcart.setBackgroundResource(R.drawable.selector_shopping_cart);
                    viewHolder.mDialogGoodspecinfoAffirm.setBackgroundResource(R.drawable.selector_buy_now);
                } else {
                    viewHolder.mDialogGoodspecinfoAffirm.setBackgroundResource(R.drawable.dialog_goods_specinfo_textview);
                }

            } else {
                viewHolder.mDialogGoodspecinfoNumber.setEnabled(false);
                viewHolder.mDialogGoodspecinfoAffirm.setEnabled(false);
                if (dialogState == 0) {
                    viewHolder.mDialogGoodspecinfoAddcart.setEnabled(false);
                    viewHolder.mDialogGoodspecinfoAddcart.setBackgroundResource(R.drawable.dialog_goods_specinfo_textview_addcart_no);
                    viewHolder.mDialogGoodspecinfoAffirm.setBackgroundResource(R.drawable.dialog_goods_specinfo_textview_buy_no);
                } else {
                    viewHolder.mDialogGoodspecinfoAffirm.setBackgroundResource(R.drawable.dialog_goods_specinfo_textview_no);
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
        viewHolder.mDialogGoodspecinfoMinus.setOnClickListener(this);
        viewHolder.mDialogGoodspecinfoAdd.setOnClickListener(this);
        viewHolder.mDialogGoodspecinfoClose.setOnClickListener(this);
        viewHolder.mDialogGoodspecinfoAffirm.setOnClickListener(this);
        viewHolder.mDialogGoodspecinfoAddcart.setOnClickListener(this);
        getDialog().setOnKeyListener((dialog, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dismiss();
                return true;
            }
            return false;
        });
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
        lp.gravity = Gravity.BOTTOM;
        //让宽度充满屏幕
//        lp.width = getResources().getDisplayMetrics().widthPixels;
        lp.width = getResources().getDisplayMetrics().widthPixels;
//        lp.height = getResources().getDisplayMetrics().heightPixels;
        lp.dimAmount = builder.alpha;
        lp.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lp.windowAnimations = R.style.DialogBottomAnimation;
        window.setAttributes(lp);
    }


    static class ViewHolder {
        View view;
        TextView mDialogGoodspecinfoName;
        TextView mDialogGoodspecinfoPrice;
        //        TextView mDialogGoodspecinfoGuige;
        RecyclerView mDialogGoodspecinfoRecyclerview;
        TextView mDialogGoodspecinfoStock;
        ImageView mDialogGoodspecinfoMinus;
        EditText mDialogGoodspecinfoNumber;
        ImageView mDialogGoodspecinfoAdd;
        TextView mDialogGoodspecinfoAddcart;
        TextView mDialogGoodspecinfoAffirm;
        ImageView mDialogGoodspecinfoIcon;
        ImageView mDialogGoodspecinfoClose;

        ViewHolder(View view) {
            this.view = view;

            mDialogGoodspecinfoName = view.findViewById(R.id.dialog_goodspecinfo_name);
            mDialogGoodspecinfoPrice = view.findViewById(R.id.dialog_goodspecinfo_price);
//            mDialogGoodspecinfoGuige = view.findViewById(R.id.dialog_goodspecinfo_guige);
            mDialogGoodspecinfoRecyclerview = view.findViewById(R.id.dialog_goodspecinfo_recyclerview);
            mDialogGoodspecinfoStock = view.findViewById(R.id.dialog_goodspecinfo_stock);
            mDialogGoodspecinfoMinus = view.findViewById(R.id.dialog_goodspecinfo_minus);
            mDialogGoodspecinfoNumber = view.findViewById(R.id.dialog_goodspecinfo_number);
            mDialogGoodspecinfoAdd = view.findViewById(R.id.dialog_goodspecinfo_add);
            mDialogGoodspecinfoAddcart = view.findViewById(R.id.dialog_goodspecinfo_addcart);
            mDialogGoodspecinfoAffirm = view.findViewById(R.id.dialog_goodspecinfo_affirm);
            mDialogGoodspecinfoIcon = view.findViewById(R.id.dialog_goodspecinfo_icon);
            mDialogGoodspecinfoClose = view.findViewById(R.id.dialog_goodspecinfo_close);

        }
    }


}
