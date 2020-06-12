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

import com.trjx.tbase.tdialog.BaseDialog;
import com.work.doctor.fruits.R;

/**
 * 提示语句
 */
public class RemindDialog extends BaseDialog implements View.OnClickListener {

    private Param param;

    public RemindDialog() {
    }

    public RemindDialog(Param param) {
        this.param = param;
    }

    public interface OnRemindClickListener {
        void onRemindClickAffirm(View view);
        void onRemindClickCancle(View view);
    }


    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.dialog_remind_affirm) {
            if (param.listener != null) {
                param.listener.onRemindClickAffirm(v);
            }
            dismiss();
        } else if (ids == R.id.dialog_remind_cancle) {
            if (param.listener != null) {
                param.listener.onRemindClickCancle(v);
            }
            dismiss();
        }

    }

    public static class Builder {

        private Param mparam;

        public Builder(Context context) {
            mparam = new Param();
            mparam.mContext = context;
            mparam.cancelable = false;
            mparam.alpha = 0.4f;
            mparam.affirmText = "确认";
            mparam.cancleText = "";
            mparam.messageGravity = -1;
            mparam.titleStr = "";
        }

        public Builder setCancelable(boolean cancelable) {
            mparam.cancelable = cancelable;
            return this;
        }
        public Builder setTitle(String titleStr) {
            mparam.titleStr = titleStr;
            return this;
        }
        public Builder setMessage(String message) {
            mparam.message = message;
            return this;
        }

        /**
         *
         * @param messageGravity   Gravity.CENTER_VERTICAL ,Gravity.CENTER 等
         * @return
         */
        public Builder setMessageGravity(int messageGravity) {
            mparam.messageGravity = messageGravity;
            return this;
        }

        public Builder setAffirmText(String affirmText) {
            mparam.affirmText = affirmText;
            return this;
        }
        public Builder setCancleText(String cancleText) {
            mparam.cancleText = cancleText;
            return this;
        }

        public Builder setAlpha(float alpha) {
            mparam.alpha = alpha;
            return this;
        }

        public Builder setOnRemindClickListener(OnRemindClickListener listener) {
            mparam.listener = listener;
            return this;
        }

        public RemindDialog create() {
            return new RemindDialog(mparam);
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

        View contentView = inflater.inflate(R.layout.dialog_remind, null);
        ViewHolder viewHolder = new ViewHolder(contentView);
        if (param.titleStr != null && !param.titleStr.equals("")) {
            viewHolder.mDialogRemindTitle.setText(param.titleStr);
            viewHolder.mDialogRemindTitle.setVisibility(View.VISIBLE);
        }

        viewHolder.mDialogRemindMessage.setText(param.message);
        if (param.messageGravity >= 0) {
            viewHolder.mDialogRemindMessage.setGravity(param.messageGravity);
        }
        if(!param.cancleText.equals("")){
            viewHolder.mDialogRemindCancle.setText(param.cancleText);
            viewHolder.mDialogRemindCancle.setVisibility(View.VISIBLE);
            viewHolder.mDialogRemindAffirm.setBackgroundResource(R.drawable.shape_dialog_remind_affirm_half);
            viewHolder.mDialogRemindAffirm.setTextColor(param.mContext.getResources().getColor(R.color.color_white));
        }
        if(param.affirmText.equals("")){
            param.affirmText = "确认";
        }
        viewHolder.mDialogRemindAffirm.setText(param.affirmText);

        viewHolder.mDialogRemindCancle.setOnClickListener(this);
        viewHolder.mDialogRemindAffirm.setOnClickListener(this);

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

        lp.width = getResources().getDisplayMetrics().widthPixels - 100;
//        lp.height = getResources().getDisplayMetrics().heightPixels;
        lp.dimAmount = param.alpha;
        lp.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lp.windowAnimations = R.style.MyDialogAnimation;
        window.setAttributes(lp);
    }

    static class ViewHolder {
        View view;
        TextView mDialogRemindTitle;
        TextView mDialogRemindMessage;
        TextView mDialogRemindCancle;
        TextView mDialogRemindAffirm;

        ViewHolder(View view) {
            this.view = view;

            mDialogRemindTitle = view.findViewById(R.id.dialog_remind_title);
            mDialogRemindMessage = view.findViewById(R.id.dialog_remind_message);
            mDialogRemindCancle = view.findViewById(R.id.dialog_remind_cancle);
            mDialogRemindAffirm = view.findViewById(R.id.dialog_remind_affirm);

        }
    }

    static class Param{
        private Context mContext;
        @FloatRange(from = 0.0, to = 1.0)
        private float alpha;
        private boolean cancelable;// 默认可以取消

        private String titleStr;
        private String message;
        private int messageGravity;

        private String affirmText;
        private String cancleText;

        private OnRemindClickListener listener;
    }

}
