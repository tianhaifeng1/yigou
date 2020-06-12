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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.trjx.tbase.tdialog.BaseDialog;
import com.work.doctor.fruits.R;

import java.lang.reflect.Field;

/**
 * 客服弹框
 */
public class KefuDialog extends BaseDialog implements View.OnClickListener {

    private Param param;

    public KefuDialog() {
    }

    public KefuDialog(Param param) {
        this.param = param;
    }

    public interface OnKefuDialogClickListener {
        void onKefuDialogAffirmClick(View view);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.dialog_kefu_cancle) {
            //取消
            dismiss();
        } else if (ids == R.id.dialog_kefu_affirm) {
            if (param.listener != null) {
                param.listener.onKefuDialogAffirmClick(v);
            }
            dismiss();
        }

    }

    public static class Builder {

        Param P;

        public Builder(Context context) {
            P = new Param();
            P.context = context;
            P.cancelable = true;
            P.alpha = 0.4f;
        }

        public Builder setCancelable(boolean cancelable) {
            P.cancelable = cancelable;
            return this;
        }

        public Builder setAlpha(float alpha) {
            P.alpha = alpha;
            return this;
        }

        public Builder setOnKefuDialogClickListener(OnKefuDialogClickListener listener) {
            P.listener = listener;
            return this;
        }

        public KefuDialog create() {
            return new KefuDialog(P);
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
        View contentView = inflater.inflate(R.layout.dialog_kefu, null);
        ViewHolder viewHolder = new ViewHolder(contentView);

        viewHolder.mDialogKefuAffirm.setOnClickListener(this);
        viewHolder.mDialogKefuCancle.setOnClickListener(this);
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
        lp.gravity = Gravity.CENTER;
        //让宽度充满屏幕
//        lp.width = getResources().getDisplayMetrics().widthPixels;

        lp.width = getResources().getDisplayMetrics().widthPixels - 200;
//        lp.height = getResources().getDisplayMetrics().heightPixels;
        lp.dimAmount = param.alpha;
        lp.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lp.windowAnimations = R.style.MyDialogAnimation;
        window.setAttributes(lp);
    }


    //DialogFragment 使用时出现IllegalStateException: Can not perform this action after onSaveInstanceState
    //解决以上问题，利用反射重写show方法
    public void show(FragmentManager manager, String tag) {
        try {
            Field mDismissed = this.getClass().getSuperclass().getDeclaredField("mDismissed");
            Field mShownByMe = this.getClass().getSuperclass().getDeclaredField("mShownByMe");
            mDismissed.setAccessible(true);
            mShownByMe.setAccessible(true);
            mDismissed.setBoolean(this, false);
            mShownByMe.setBoolean(this, true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }


    static class ViewHolder {
        View view;
        ImageView mDialogKefuCancle;
        TextView mDialogKefuAffirm;

        ViewHolder(View view) {
            this.view = view;
            mDialogKefuCancle = view.findViewById(R.id.dialog_kefu_cancle);
            mDialogKefuAffirm = view.findViewById(R.id.dialog_kefu_affirm);
        }
    }

    static class Param {

        private Context context;
        @FloatRange(from = 0.0, to = 1.0)
        private float alpha;
        private boolean cancelable;// 默认可以取消

        private OnKefuDialogClickListener listener;

    }


}
