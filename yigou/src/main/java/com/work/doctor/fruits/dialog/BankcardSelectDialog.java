package com.work.doctor.fruits.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.contrarywind.view.WheelView;
import com.t.httplib.yigou.bean.BandcardInterface;
import com.trjx.tbase.tdialog.BaseDialog;
import com.trjx.tlibs.uils.ToastUtil2;
import com.work.doctor.fruits.R;

import java.util.List;

/**
 * 银行卡选择的dialog
 */
public class BankcardSelectDialog
        extends BaseDialog
        implements View.OnClickListener {

    private Param param;

    public BankcardSelectDialog() {
    }

    public BankcardSelectDialog(Param param) {
        this.param = param;
    }

    public interface OnBankcardSelectListener {
        /**
         * 点击回调的方法
         *
         * @param view
         */
        void onBankcardSelect(View view, int position);

    }


    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.dialog_bankcard_select_affirm) {
            if (param.index < 0) {
                ToastUtil2.showToast(param.context, "您还未选择地址");
                return;
            }
            if (param.listener != null) {
                param.listener.onBankcardSelect(v, param.index);
            }
            dismiss();
        } else if (ids == R.id.dialog_bankcard_select_cancle) {
            dismiss();
        }

    }

    public static class Builder<T extends BandcardInterface> {
        private Param<T> P;

        public Builder(Context context) {
            P = new Param();
            P.context = context;
            P.cancelable = true;
            P.alpha = 0.4f;
            P.index = 0;
            P.title = "";
        }


        // 设置商品信息
        public Builder setTitle(String title) {
            P.title = title;
            return this;
        }

        // 设置商品信息
        public Builder setBankcardList(List<T> bankcardList) {
            P.bankcardList = bankcardList;
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
            if (postion < 0) {
                P.index = 0;
            }else{
                P.index = postion;
            }
            return this;
        }

        public Builder setOnBankcardSelectListener(OnBankcardSelectListener listener) {
            P.listener = listener;
            return this;
        }

        public BankcardSelectDialog create() {
            return new BankcardSelectDialog(P);
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

        View contentView = inflater.inflate(R.layout.dialog_bankcard_select, null);
        ViewHolder viewHolder = new ViewHolder(contentView);
        viewHolder.mDialogBankcardSelectTitle.setText(param.title);
        if (param.bankcardList != null) {
            TWheelAdapter adapter = new TWheelAdapter(param.bankcardList);
            viewHolder.wheelView.setAdapter(adapter);
            viewHolder.wheelView.setCurrentItem(param.index);
            viewHolder.wheelView.setOnItemSelectedListener(index ->param.index = index);
        }

        viewHolder.mDialogBankcardSelectCancle.setOnClickListener(this);
        viewHolder.mDialogBankcardSelectAffirm.setOnClickListener(this);
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
        TextView mDialogBankcardSelectCancle;
        TextView mDialogBankcardSelectAffirm;
        TextView mDialogBankcardSelectTitle;
        WheelView wheelView;

        ViewHolder(View view) {
            this.view = view;
            mDialogBankcardSelectCancle = view.findViewById(R.id.dialog_bankcard_select_cancle);
            mDialogBankcardSelectAffirm = view.findViewById(R.id.dialog_bankcard_select_affirm);
            mDialogBankcardSelectTitle = view.findViewById(R.id.dialog_bankcard_select_title);
            wheelView = view.findViewById(R.id.wheelview);
            wheelView.setCyclic(false);
        }
    }

    static class Param<T extends BandcardInterface>{

        private Context context;

        @FloatRange(from = 0.0, to = 1.0)
        private float alpha;
        private boolean cancelable;// 默认可以取消

        //地址集合
        private List<T> bankcardList;

        private OnBankcardSelectListener listener;

        private int index;

        private String title;
    }

}
