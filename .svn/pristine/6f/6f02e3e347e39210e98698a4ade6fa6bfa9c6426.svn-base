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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.t.httplib.yigou.bean.resp.GoodsDetailInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsSpecInfoBean;
import com.trjx.tbase.tdialog.BaseDialog;
import com.trjx.tlibs.uils.GlideUtile;
import com.trjx.tlibs.uils.ToastUtil2;
import com.work.doctor.fruits.R;

import java.util.List;

/**
 * 商品详情页面使用
 */
@Deprecated
public class GoodsSpecInfoDialog extends BaseDialog implements View.OnClickListener {
    private ViewHolder viewHolder;
    private Builder builder;
    private View contentView;

    public interface OnGoodsSpecInfoListener {
        /**
         * 点击回调的方法
         *
         * @param view
         */
        void onOnGoodsAffirmClick(View view, int specIndex, int number);

        void onOnGoodsAddcartClick(View view, int specIndex, int number);
    }


    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.dialog_goodspecinfo_affirm) {

            if (builder.specIndex < 0 && builder.specListBeanList != null &&  builder.specListBeanList.size() > 0) {
                ToastUtil2.showToast(builder.context, "请选择规格");
                return;
            }

            if (builder.listener != null) {
                if (builder.dialogState == 1) {
                    builder.listener.onOnGoodsAddcartClick(viewHolder.mDialogGoodspecinfoAddcart,
                            builder.specIndex,
                            builder.number
                    );
                } else {
                    builder.listener.onOnGoodsAffirmClick(viewHolder.mDialogGoodspecinfoAffirm,
                            builder.specIndex,
                            builder.number
                    );
                }
            }
            dismiss();
        } else if (ids == R.id.dialog_goodspecinfo_addcart) {

            if (builder.specListBeanList.size() > 0 && builder.specIndex < 0) {
                ToastUtil2.showToast(builder.context, "请选择规格");
                return;
            }

            if (builder.listener != null) {
                builder.listener.onOnGoodsAddcartClick(viewHolder.mDialogGoodspecinfoAddcart,
                        builder.specIndex,
                        builder.number
                );
            }
            dismiss();

        } else if (ids == R.id.dialog_goodspecinfo_close) {
            dismiss();
        } else if (ids == R.id.dialog_goodspecinfo_add) {

            viewHolder.mDialogGoodspecinfoNumber.setText((++builder.number) + "");
//
//            if (builder.goodsStock > -1) {
//
//            }
//
//            if (builder.number >= builder.goodsStock) {
//                //不能点击
//                viewHolder.mDialogGoodspecinfoAdd.setEnabled(false);
//                if(){
//
//                }
//            }


        } else if (ids == R.id.dialog_goodspecinfo_minus) {
            //最少一个
            if (--builder.number < 1) {
                builder.number = 1;
            }
            viewHolder.mDialogGoodspecinfoNumber.setText(builder.number + "");
        }

    }

    public static class Builder {
        private Context context;
        @FloatRange(from = 0.0, to = 1.0)
        private float alpha;
        private boolean cancelable;// 默认可以取消

        private int dialogState;

        private OnGoodsSpecInfoListener listener;

        private LayoutInflater inflater;

        private List<GoodsSpecInfoBean.GoodsItemListBean.SpecListBean> specListBeanList;

        private GoodsDetailInfoBean goodsDetailInfoBean;

        private int number;

        //商品库存 ： -1代表库存不限
//        private int goodsStock;

        //        规格选择下标
        private int specIndex;

        public Builder(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            cancelable = true;
            alpha = 0.4f;
            number = 1;
//            affirmText = "";
            specIndex = -1;
//            goodsStock = -1;
        }


        // 设置商品库存
//        public Builder setGoodsStock(int goodsStock) {
//            this.goodsStock = goodsStock;
//            return this;
//        }

        // 设置规格数据集合
        public Builder setDialogState(int dialogState) {
            this.dialogState = dialogState;
            return this;
        }


        // 设置规格数据集合
        public Builder setGoodsItemList(List<GoodsSpecInfoBean.GoodsItemListBean.SpecListBean> specListBeanList) {
            this.specListBeanList = specListBeanList;
            return this;
        }

        // 设置默认规格选择项
        public Builder setGoodsItemIndex(int specIndex) {
            this.specIndex = specIndex;
            return this;
        }

        // 设置商品信息
        public Builder setGoodsInfo(GoodsDetailInfoBean goodsInfo) {
            this.goodsDetailInfoBean = goodsInfo;
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

        public Builder setOnGoodsSpecInfoListener(OnGoodsSpecInfoListener listener) {
            this.listener = listener;
            return this;
        }

        public GoodsSpecInfoDialog create() {
            GoodsSpecInfoDialog dialog = new GoodsSpecInfoDialog();
            dialog.builder = this;
            dialog.contentView = inflater.inflate(R.layout.dialog_goods_specinfo, null);
            dialog.viewHolder = new ViewHolder(dialog.contentView);

            dialog.viewHolder.mDialogGoodspecinfoName.setText(goodsDetailInfoBean.getGoodsName());
            dialog.viewHolder.mDialogGoodspecinfoNumber.setText(number + "");
            GlideUtile.bindImageView(context, goodsDetailInfoBean.getGoodsImage(), dialog.viewHolder.mDialogGoodspecinfoIcon);

            dialog.viewHolder.mDialogGoodspecinfoPrice.setText("￥ " + goodsDetailInfoBean.getSellPriceDiscount());

            String affirmText;
            if (dialogState == 0) {
                affirmText = "立即购买";
                dialog.viewHolder.mDialogGoodspecinfoAddcart.setVisibility(View.VISIBLE);
                dialog.viewHolder.mDialogGoodspecinfoAffirm.setBackgroundResource(R.drawable.selector_buy_now);
            } else {
                affirmText = "确认";
            }
            dialog.viewHolder.mDialogGoodspecinfoAffirm.setText(affirmText);

            if (specListBeanList != null && specListBeanList.size() > 0) {
                dialog.viewHolder.mDialogGoodspecinfoGuige.setVisibility(View.VISIBLE);
                dialog.viewHolder.mDialogGoodspecinfoRecyclerview.setVisibility(View.VISIBLE);

                //初始化选中项
                if (specIndex < specListBeanList.size() && specIndex > -1) {
                    specListBeanList.get(specIndex).setSelect(true);
                }

                GoodsSpecInfoAdapter infoAdapter = new GoodsSpecInfoAdapter(specListBeanList);
//            dialog.viewHolder.mDialogGoodspecinfoRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL){
                dialog.viewHolder.mDialogGoodspecinfoRecyclerview.setLayoutManager(new GridLayoutManager(context, 3) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
                dialog.viewHolder.mDialogGoodspecinfoRecyclerview.setAdapter(infoAdapter);
                infoAdapter.setOnItemClickListener((adapter, view, position) -> {

                    if (specIndex != position) {
                        GoodsSpecInfoBean.GoodsItemListBean.SpecListBean specListBeanPos = specListBeanList.get(position);
                        specListBeanPos.setSelect(true);
                        infoAdapter.notifyItemChanged(position);

                        if (specIndex < specListBeanList.size() && specIndex > -1) {
                            GoodsSpecInfoBean.GoodsItemListBean.SpecListBean specListBeanIndex = specListBeanList.get(specIndex);
                            specListBeanIndex.setSelect(false);
                            infoAdapter.notifyItemChanged(specIndex);
                        }

                        specIndex = position;
                    }


                });

            }

            return dialog;
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
        TextView mDialogGoodspecinfoGuige;
        RecyclerView mDialogGoodspecinfoRecyclerview;
        TextView mDialogGoodspecinfoMinus;
        TextView mDialogGoodspecinfoNumber;
        TextView mDialogGoodspecinfoAdd;
        TextView mDialogGoodspecinfoAddcart;
        TextView mDialogGoodspecinfoAffirm;
        ImageView mDialogGoodspecinfoIcon;
        ImageView mDialogGoodspecinfoClose;

        ViewHolder(View view) {
            this.view = view;

            mDialogGoodspecinfoName = view.findViewById(R.id.dialog_goodspecinfo_name);
            mDialogGoodspecinfoPrice = view.findViewById(R.id.dialog_goodspecinfo_price);
            mDialogGoodspecinfoGuige = view.findViewById(R.id.dialog_goodspecinfo_guige);
            mDialogGoodspecinfoRecyclerview = view.findViewById(R.id.dialog_goodspecinfo_recyclerview);
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
