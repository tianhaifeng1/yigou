package com.work.doctor.fruits.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.t.httplib.yigou.bean.resp.AddressInfoBean;
import com.trjx.tbase.tdialog.BaseDialog;
import com.trjx.tlibs.uils.ToastUtil2;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.myrecycler.TLinearLayoutManager;

import java.util.List;

/**
 * 商品详情页面使用
 */
public class AddressSelectDialog extends BaseDialog implements View.OnClickListener {

    private Context context;
    private Param param;

    public AddressSelectDialog() {
    }

    public AddressSelectDialog(Context context, Param param) {
        this.context = context;
        this.param = param;
    }


    public interface OnAddressSelectListener {
        /**
         * 点击回调的方法
         *
         * @param view
         */
        void onAddressSelect(View view, int position);

    }


    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.dialog_address_select_affirm) {
            if (param.index < 0) {
                ToastUtil2.showToast(context, "您还未选择地址");
                return;
            }
            if (param.listener != null) {
                param.listener.onAddressSelect(v, param.index);
            }
            dismiss();
        } else if (ids == R.id.dialog_address_select_cancle) {
            dismiss();
        }

    }

    public static class Builder {
        private Context context;

        private Param P;

        public Builder(Context context) {
            this.context = context;
            P = new Param();
            P.cancelable = true;
            P.alpha = 0.4f;
            P.index = -1;
        }


        // 设置商品信息
        public Builder setAddressList(List<AddressInfoBean> useAddressList) {
            P.useAddressList = useAddressList;
            return this;
        }


        public Builder setCancelable(boolean cancelable) {
            P.cancelable = cancelable;
            return this;
        }

        public Builder setAlpha(float alpha) {
            P.alpha = alpha;
            return this;
        }

        public Builder setPostion(int postion) {
            P.index = postion;
            return this;
        }

        public Builder setOnAddressSelectListener(OnAddressSelectListener listener) {
            P.listener = listener;
            return this;
        }

        public AddressSelectDialog create() {
            return new AddressSelectDialog(context,P);
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
        View contentView = inflater.inflate(R.layout.dialog_address_select, null);
        ViewHolder viewHolder = new ViewHolder(contentView);

        if (param.useAddressList != null) {

            DialogAddressSelectAdapter infoTitleAdapter = new DialogAddressSelectAdapter(param.useAddressList);
            TLinearLayoutManager tLinearLayoutManager = new TLinearLayoutManager(context);
            viewHolder.mDialogAddressSelectRecyclerview.setLayoutManager(tLinearLayoutManager);
            viewHolder.mDialogAddressSelectRecyclerview.setAdapter(infoTitleAdapter);

            infoTitleAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                int ids = view.getId();
                switch (ids) {
                    case R.id.dialog_address_select_item_select:
                        if (param.index != position) {
                            param.useAddressList.get(position).setSelect(true);
                            adapter.notifyItemChanged(position);
                            if (param.index > -1) {
                                param.useAddressList.get(param.index).setSelect(false);
                                adapter.notifyItemChanged(param.index);
                            }
                            param.index = position;
                        }
                        break;
                }
            });

            int line = param.useAddressList.size();
            if (line > 5) {
                line = 5;
                tLinearLayoutManager.setCanScroll(true);
            }else{
                tLinearLayoutManager.setCanScroll(false);
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewHolder.mDialogAddressSelectRecyclerview.getLayoutParams();
            layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
            layoutParams.height = context.getResources().getDimensionPixelOffset(R.dimen.dp80) * line;
            viewHolder.mDialogAddressSelectRecyclerview.setLayoutParams(layoutParams);

        }

        viewHolder.mDialogAddressSelectCancle.setOnClickListener(this);
        viewHolder.mDialogAddressSelectAffirm.setOnClickListener(this);
        return contentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置外部点击不取消
        if (!param.cancelable) {
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
        lp.dimAmount = param.alpha;
        lp.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lp.windowAnimations = R.style.DialogBottomAnimation;
        window.setAttributes(lp);
    }

    static class ViewHolder {
        View view;
        TextView mDialogAddressSelectCancle;
        TextView mDialogAddressSelectAffirm;
        TextView mDialogAddressSelectTitle;
        RecyclerView mDialogAddressSelectRecyclerview;

        ViewHolder(View view) {
            this.view = view;
            mDialogAddressSelectCancle = view.findViewById(R.id.dialog_address_select_cancle);
            mDialogAddressSelectAffirm = view.findViewById(R.id.dialog_address_select_affirm);
            mDialogAddressSelectTitle = view.findViewById(R.id.dialog_address_select_title);
            mDialogAddressSelectRecyclerview = view.findViewById(R.id.dialog_address_select_recyclerview);
        }
    }

    static class Param{

        @FloatRange(from = 0.0, to = 1.0)
        private float alpha;
        private boolean cancelable;// 默认可以取消

        //地址集合
        private List<AddressInfoBean> useAddressList;

        private OnAddressSelectListener listener;

        private int index;


    }


}
