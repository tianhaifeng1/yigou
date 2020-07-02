package com.work.doctor.fruits.dialog;

import android.content.Context;
import android.content.Intent;
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

import com.trjx.tbase.tdialog.BaseDialog;
import com.trjx.tlibs.uils.SharedPreferencesUtils;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoWebActivity;

public class GuideDialog extends BaseDialog implements View.OnClickListener {

    private ViewHolder viewHolder;
    private Builder builder;
    private View contentView;


    public interface GuideDialogClickListener {
        void GuideDialogChangeClick(View view);
    }


    public GuideDialog() {
        super();
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.agreement_yonghu) {
            Intent intent = new Intent(builder.context, DemoWebActivity.class);
            intent.putExtra("code", 3);
            builder.context.startActivity(intent);
        } else if (ids == R.id.agreement_yinsi) {
            Intent intent = new Intent(builder.context, DemoWebActivity.class);
            intent.putExtra("code", 5);
            builder.context.startActivity(intent);
        } else if (ids == R.id.agreement_yes) {
            SharedPreferencesUtils.setParam(builder.context, DemoConstant.user_first, false);
            dismiss();
        } else if (ids == R.id.agreement_no) {
            if(builder.listener != null) {
                builder.listener.GuideDialogChangeClick(v);
            }
//            Toast.makeText(builder.context,"您未同意用户协议，将无法使用本软件",Toast.LENGTH_SHORT);
//            builder.context
//            System.exit(0);
        }
    }

    public static class Builder {
        private Context context;
        @FloatRange(from = 0.0, to = 1.0)
        private float alpha;
        private boolean cancelable;// 默认可以取消

        private LayoutInflater inflater;

        private GuideDialogClickListener listener;


        public Builder setListener(GuideDialogClickListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            cancelable = false;
            alpha = 0.4f;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setAlpha(float alpha) {
            this.alpha = alpha;
            return this;
        }

        public GuideDialog create() {
            GuideDialog dialog = new GuideDialog();
            dialog.builder = this;
            dialog.contentView = inflater.inflate(R.layout.dialog_agreement, null);
            dialog.viewHolder = new ViewHolder(dialog.contentView);

            return dialog;
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
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


    //一般用于创建复杂内容弹窗或全屏展示效果的场景，UI 复杂，功能复杂，一般有网络请求等异步操作。
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewHolder.agreementYonghu.setOnClickListener(this);
        viewHolder.agreementYinsi.setOnClickListener(this);
        viewHolder.agreementYes.setOnClickListener(this);
        viewHolder.agreementNo.setOnClickListener(this);
        return contentView;
    }



    static class ViewHolder {
        View view;
        TextView agreementYonghu;
        TextView agreementYinsi;
        TextView agreementYes;
        TextView agreementNo;

        ViewHolder(View view) {
            this.view = view;

            agreementYonghu = view.findViewById(R.id.agreement_yonghu);
            agreementYinsi = view.findViewById(R.id.agreement_yinsi);
            agreementYes = view.findViewById(R.id.agreement_yes);
            agreementNo =view.findViewById(R.id.agreement_no);

        }
    }
}
