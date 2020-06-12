package com.work.doctor.fruits.activity.address;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.t.httplib.yigou.bean.resp.AddressInfoBean;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.assist.DemoUtils;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.RemindDialog;
import com.xzte.maplib.baidu.SearchAddressActivity;

import java.util.List;

/**
 * 地址添加和修改
 * <p>
 * code ： 0 添加
 * 1  修改
 */
public class AddressUpdateActivity extends DemoMVPActivity<AddressView, AddressPresenter>
        implements AddressView, View.OnClickListener {

    private int code;
    private EditText mEtName;
    private EditText mEtPhoneNumber;
    private TextView mTvSelectArea;
    private ImageView mTvSelect;
    private LinearLayout mLlSelectArea;
    private TextView mEtDetailedAddress;
    private EditText mEtDetailedAddressdetail;
    private TextView mTvEditaddressBz;
    private TextView mTvSave;
    private TextView mTvCancel;

    //    地址信息
    private AddressInfoBean infoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_update);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("", true);

        mEtName = findViewById(R.id.et_name);
        mEtPhoneNumber = findViewById(R.id.et_phone_number);
        mTvSelectArea = findViewById(R.id.tv_select_area);
        mTvSelect = findViewById(R.id.tv_select_);
        mLlSelectArea = findViewById(R.id.ll_select_area);
        mEtDetailedAddress = findViewById(R.id.et_detailed_address);
        mEtDetailedAddressdetail = findViewById(R.id.et_detailed_addressdetail);
        mTvEditaddressBz = findViewById(R.id.tv_editaddress_bz);
        mTvSave = findViewById(R.id.tv_save);
        mTvCancel = findViewById(R.id.tv_cancel);

        mLlSelectArea.setOnClickListener(this);
        mTvSelect.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);

        code = getIntent().getIntExtra("code", 0);

        if (code == 0) {
            titleModule.setTitleText("新增地址");
            infoBean = new AddressInfoBean();
        } else if (code == 1) {
            titleModule.setTitleText("编辑地址");

            mTvCancel.setVisibility(View.VISIBLE);

            infoBean = (AddressInfoBean) getIntent().getSerializableExtra("info");

            mEtName.setText(infoBean.getName());
            mEtPhoneNumber.setText(infoBean.getPhone());
            mTvSelectArea.setText(infoBean.getProvince() + "  " + infoBean.getCity() + "  " + infoBean.getCounty());
            mEtDetailedAddress.setText(infoBean.getAddress());
            mEtDetailedAddressdetail.setText(infoBean.getDetailAddress());
            mEtPhoneNumber.setText(infoBean.getPhone());
        }

    }


    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.ll_select_area:
            case R.id.tv_select_:
                if(DemoUtils.isFastClick()){
                    Intent intent = new Intent(context, SearchAddressActivity.class);
                    intent.putExtra("isShowMapView", true);
                    intent.putExtra("title", "地址选择");
                    skipActivity(intent, 100);
                }

                break;
            case R.id.tv_save:

                String name = mEtName.getText().toString().trim();
                String phoneNumber = mEtPhoneNumber.getText().toString().trim();
                String addressdetail = mEtDetailedAddressdetail.getText().toString().trim();

                infoBean.setName(name);
                infoBean.setPhone(phoneNumber);
                infoBean.setDetailAddress(addressdetail);

                getPresenter().updateAddress(infoBean);

                break;
            case R.id.tv_cancel:
                if (infoBean != null) {
                    RemindDialog remindDialog = new RemindDialog.Builder(context)
                            .setMessage("确定要删除这条地址？")
                            .setCancleText("取消")
                            .setAffirmText("删除")
                            .setCancelable(false)
                            .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                                @Override
                                public void onRemindClickAffirm(View view) {
                                    getPresenter().deleteAddress(infoBean.getId());
                                }

                                @Override
                                public void onRemindClickCancle(View view) {

                                }
                            }).create();
                    remindDialog.show(getSupportFragmentManager(), "dialog_delete_address");
                }

                break;
        }
    }

    @Override
    protected AddressPresenter initPersenter() {
        return new AddressPresenter(this);
    }

    @Override
    public void getListDataSuccess(List<AddressInfoBean> list) {

    }

    @Override
    public void updateAddressSuccess(AddressInfoBean infoBean) {
        setResult(200);
        finish();
    }

    @Override
    public void deleteAddressSuccess() {
        setResult(200);
        finish();
    }

    @Override
    public void setDefaultAddressSuccess() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == 601) {

                String name = data.getStringExtra("name");
                String getprovince = data.getStringExtra("sheng");
                String getcity = data.getStringExtra("shi");
                String getcounty = data.getStringExtra("qu");
                double la = data.getDoubleExtra("la", 0);
                double lo = data.getDoubleExtra("lo", 0);

                mEtDetailedAddress.setText(name);
                mTvSelectArea.setText(getprovince + "  " + getcity + "  " + getcounty);

                if (infoBean != null) {
                    infoBean.setAddress(name);
                    infoBean.setProvince(getprovince);
                    infoBean.setCity(getcity);
                    infoBean.setCounty(getcounty);
                    infoBean.setLatitude(la);
                    infoBean.setLongitude(lo);
                }

            }
        }
    }

}
