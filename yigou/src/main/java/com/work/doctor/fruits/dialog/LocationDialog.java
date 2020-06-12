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
import com.trjx.tlibs.uils.SharedPreferencesUtils;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.assist.DemoConstant;

import java.lang.reflect.Field;

/**
 * 定位地址提示
 */
public class LocationDialog extends BaseDialog implements View.OnClickListener {
    private ViewHolder viewHolder;
    private Builder builder;
    private View contentView;

    public interface OnLocationDialogClickListener {
        void onLocationDialogChangeClick(View view);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        if (ids == R.id.dialog_location_remind) {

            if(builder.isRemind){
                viewHolder.mDialogLocationRemind.setImageResource(R.mipmap.round_n);
                builder.isRemind = false;
            }else{
                viewHolder.mDialogLocationRemind.setImageResource(R.mipmap.goodscart_select);
                builder.isRemind = true;
            }
        } else if (ids == R.id.dialog_location_affirm) {
            if(builder.isRemind){
                SharedPreferencesUtils.setParam(builder.context, DemoConstant.user_onepage_dialog_remind, false);
            }else{
                SharedPreferencesUtils.setParam(builder.context, DemoConstant.user_onepage_dialog_remind, true);
            }
            dismiss();
        } else if (ids == R.id.dialog_location_change) {
            if(builder.isRemind){
                SharedPreferencesUtils.setParam(builder.context, DemoConstant.user_onepage_dialog_remind, false);
            }else{
                SharedPreferencesUtils.setParam(builder.context, DemoConstant.user_onepage_dialog_remind, true);
            }
            if (builder.listener != null) {
                builder.listener.onLocationDialogChangeClick(v);
            }
            dismiss();
        }

    }

    public static class Builder {
        private Context context;
        @FloatRange(from = 0.0, to = 1.0)
        private float alpha;
        private boolean cancelable;// 默认可以取消

        private LayoutInflater inflater;

        private String address;
        private String addressDetail;

        private boolean isRemind;

        private OnLocationDialogClickListener listener;

        public Builder(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            cancelable = true;
            alpha = 0.4f;
            isRemind = false;
            address = "";
            addressDetail = "";
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }
        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }
        public Builder setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
            return this;
        }

        public Builder setAlpha(float alpha) {
            this.alpha = alpha;
            return this;
        }

        public Builder setOnLocationDialogClickListener(OnLocationDialogClickListener listener) {
            this.listener = listener;
            return this;
        }

        public LocationDialog create() {
            LocationDialog dialog = new LocationDialog();
            dialog.builder = this;
            dialog.contentView = inflater.inflate(R.layout.dialog_location, null);
            dialog.viewHolder = new ViewHolder(dialog.contentView);
            dialog.viewHolder.mDialogLocationAddress.setText(address);
            dialog.viewHolder.mDialogLocationAddressdetail.setText(addressDetail);

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
        viewHolder.mDialogLocationRemind.setOnClickListener(this);
        viewHolder.mDialogLocationChange.setOnClickListener(this);
        viewHolder.mDialogLocationAffirm.setOnClickListener(this);
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
         TextView mDialogLocationAddress;
         TextView mDialogLocationAddressdetail;
         ImageView mDialogLocationRemind;
         TextView mDialogLocationChange;
         TextView mDialogLocationAffirm;

        ViewHolder(View view) {
            this.view = view;

            mDialogLocationAddress = view.findViewById(R.id.dialog_location_address);
            mDialogLocationAddressdetail = view.findViewById(R.id.dialog_location_addressdetail);
            mDialogLocationRemind = view.findViewById(R.id.dialog_location_remind);
            mDialogLocationChange = view.findViewById(R.id.dialog_location_change);
            mDialogLocationAffirm = view.findViewById(R.id.dialog_location_affirm);


        }
    }


}