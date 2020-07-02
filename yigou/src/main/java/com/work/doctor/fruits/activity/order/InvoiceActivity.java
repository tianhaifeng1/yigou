package com.work.doctor.fruits.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.t.httplib.yigou.bean.req.ReqInvoice2Info;
import com.t.httplib.yigou.bean.req.ReqInvoiceInfo;
import com.trjx.tlibs.uils.TUtils;
import com.trjx.tlibs.uils.ToastUtil2;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.assist.DemoUtils;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvoiceActivity extends DemoMVPActivity<InvoiceView, InvoicePresenter>
        implements InvoiceView, View.OnClickListener {


    private Button mEInvoice;
    private Button mZInvoice;
    private Button mNoInvoice;
    private TextView mInvoiceMessage;
    private Button mCompanyInstance;
    private Button mPersonInstance;
    private EditText mEdPersonName;
    private EditText mEdPersonPhone;
    private LinearLayout mPersonLl;
    private EditText mEdCompanyInvoiceRise;
    private EditText mEdCompanyInvoiceNumber;
    private EditText mEdCompanyInvoiceAddress;
    private EditText mEdCompanyInvoicePhone;
    private EditText mEdCompanyInvoiceBank;
    private EditText mEdCompanyInvoiceAccountNumber;
    private LinearLayout mCompanyLl;
    private LinearLayout mInstanceLl;
    private View mEdCompanyInvoiceEmailLine;
    private EditText mEdCompanyInvoiceEmail;
    private LinearLayout mEdCompanyInvoiceEmailLl;
    private TextView mTvInstanceBtn;


    private String invoiceType, invoiceMessageType, emailStr = "";
    private String InvoiceRise, InvoiceNumber, InvoiceAddress, InvoicePhone, InvoiceBank, InvoiceAccountNumber;
    private String PersonInvoiceName, PersonInvoicePhone;
    private String invoiceId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instance);
        invoiceId = getIntent().getStringExtra("invoice");
        mEInvoice.performClick();
        mCompanyInstance.performClick();
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("发票信息", true);

        mEInvoice = findViewById(R.id.e_invoice);
        mZInvoice = findViewById(R.id.z_invoice);
        mNoInvoice = findViewById(R.id.no_invoice);
        mInvoiceMessage = findViewById(R.id.invoice_message);
        mCompanyInstance = findViewById(R.id.company_instance);
        mPersonInstance = findViewById(R.id.person_instance);
        mEdPersonName = findViewById(R.id.ed_person_name);
        mEdPersonPhone = findViewById(R.id.ed_person_phone);
        mPersonLl = findViewById(R.id.person_ll);
        mEdCompanyInvoiceRise = findViewById(R.id.ed_company_invoice_rise);
        mEdCompanyInvoiceNumber = findViewById(R.id.ed_company_invoice_number);
        mEdCompanyInvoiceAddress = findViewById(R.id.ed_company_invoice_address);
        mEdCompanyInvoicePhone = findViewById(R.id.ed_company_invoice_phone);
        mEdCompanyInvoiceBank = findViewById(R.id.ed_company_invoice_bank);
        mEdCompanyInvoiceAccountNumber = findViewById(R.id.ed_company_invoice_account_number);
        mCompanyLl = findViewById(R.id.company_ll);
        mInstanceLl = findViewById(R.id.instance_ll);
        mEdCompanyInvoiceEmailLine = findViewById(R.id.ed_company_invoice_email_line);
        mEdCompanyInvoiceEmail = findViewById(R.id.ed_company_invoice_email);
        mEdCompanyInvoiceEmailLl = findViewById(R.id.ed_company_invoice_email_ll);
        mTvInstanceBtn = findViewById(R.id.tv_instance_btn);

        mEInvoice.setOnClickListener(this);
        mZInvoice.setOnClickListener(this);
        mNoInvoice.setOnClickListener(this);
        mCompanyInstance.setOnClickListener(this);
        mPersonInstance.setOnClickListener(this);
        mTvInstanceBtn.setOnClickListener(this);

//        invoiceType = "电子发票";
//        invoiceMessageType = "公司";
    }



    @Override
    protected InvoicePresenter initPersenter() {
        return new InvoicePresenter(this);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.e_invoice:
                //电子发票
                mEInvoice.setBackgroundResource(R.drawable.selector_btn_orange);
                mEInvoice.setTextColor(context.getResources().getColor(R.color.color_white));
                mZInvoice.setTextColor(context.getResources().getColor(R.color.color_black));
                mZInvoice.setBackgroundResource(R.drawable.selector_btn_black);
                mNoInvoice.setTextColor(context.getResources().getColor(R.color.color_black));
                mNoInvoice.setBackgroundResource(R.drawable.selector_btn_black);
                mInstanceLl.setVisibility(View.VISIBLE);
                invoiceType = "电子发票";

                mEdCompanyInvoiceEmailLine.setVisibility(View.VISIBLE);
                mEdCompanyInvoiceEmailLl.setVisibility(View.VISIBLE);

                break;
            case R.id.z_invoice:
                //纸质发票
                mZInvoice.setBackgroundResource(R.drawable.selector_btn_orange);
                mZInvoice.setTextColor(context.getResources().getColor(R.color.color_white));
                mEInvoice.setTextColor(context.getResources().getColor(R.color.color_black));
                mEInvoice.setBackgroundResource(R.drawable.selector_btn_black);
                mNoInvoice.setTextColor(context.getResources().getColor(R.color.color_black));
                mNoInvoice.setBackgroundResource(R.drawable.selector_btn_black);
                mInstanceLl.setVisibility(View.VISIBLE);
                invoiceType = "纸质发票";

                mEdCompanyInvoiceEmailLine.setVisibility(View.GONE);
                mEdCompanyInvoiceEmailLl.setVisibility(View.GONE);

                break;
            case R.id.no_invoice:
                //不开发票
                mEInvoice.setBackgroundResource(R.drawable.selector_btn_black);
                mEInvoice.setTextColor(context.getResources().getColor(R.color.color_black));
                mZInvoice.setBackgroundResource(R.drawable.selector_btn_black);
                mZInvoice.setTextColor(context.getResources().getColor(R.color.color_black));
                mNoInvoice.setTextColor(context.getResources().getColor(R.color.color_white));
                mNoInvoice.setBackgroundResource(R.drawable.selector_btn_orange);
                mInstanceLl.setVisibility(View.GONE);
                invoiceType = "不开发票";

                mEdCompanyInvoiceEmailLine.setVisibility(View.GONE);
                mEdCompanyInvoiceEmailLl.setVisibility(View.GONE);
                break;
            case R.id.company_instance:
                //公司
                mCompanyInstance.setBackgroundResource(R.drawable.selector_btn_orange);
                mCompanyInstance.setTextColor(context.getResources().getColor(R.color.color_white));
                mPersonInstance.setBackgroundResource(R.drawable.selector_btn_black);
                mPersonInstance.setTextColor(context.getResources().getColor(R.color.color_black));
                mCompanyLl.setVisibility(View.VISIBLE);
                mPersonLl.setVisibility(View.GONE);
                invoiceMessageType = "公司";

                break;
            case R.id.person_instance:
                //个人
                mPersonInstance.setBackgroundResource(R.drawable.selector_btn_orange);
                mPersonInstance.setTextColor(context.getResources().getColor(R.color.color_white));
                mCompanyInstance.setBackgroundResource(R.drawable.selector_btn_black);
                mCompanyInstance.setTextColor(context.getResources().getColor(R.color.color_black));
                mPersonLl.setVisibility(View.VISIBLE);
                mCompanyLl.setVisibility(View.GONE);
                invoiceMessageType = "个人";
                break;
            case R.id.tv_instance_btn:
                //确认

                affirmBtn();
                break;

        }

    }

    private void affirmBtn() {
        Intent data = new Intent();//只是回传数据就不用写跳转对象
        try {
            PersonInvoiceName = mEdPersonName.getText().toString();
            PersonInvoicePhone = mEdPersonPhone.getText().toString();
            InvoiceRise = mEdCompanyInvoiceRise.getText().toString();
            InvoiceNumber = mEdCompanyInvoiceNumber.getText().toString();
            InvoiceAddress = mEdCompanyInvoiceAddress.getText().toString();

            InvoicePhone = mEdCompanyInvoicePhone.getText().toString();
            InvoiceBank = mEdCompanyInvoiceBank.getText().toString();

            Pattern p = Pattern.compile("[0-9]*");
            Matcher m = p.matcher(mEdCompanyInvoiceAccountNumber.getText().toString());
            if(!m.matches()){
                tRemind("请输入正确的账号");
                return;
            }
            InvoiceAccountNumber = mEdCompanyInvoiceAccountNumber.getText().toString();
            if (("不开发票").equals(invoiceType)){
                data.putExtra("invoiceType", invoiceType);
                setResult(2, data);//返回data，2为result，data为intent对象
                finish();//页面销毁
            }else{
                if (!DemoUtils.isStringEmpty(invoiceMessageType)) {
                    if (invoiceMessageType.equals("个人"))
                    {
                        if (TextUtils.isEmpty(PersonInvoicePhone)) {
                            tRemind("请输入手机号码");
                            return;
                        } else if (PersonInvoicePhone.length() != 11) {
                            tRemind("手机号码位数不正确");
                            return;
                        } else {
                            String num = "[1][358]\\d{9}";
                            if (!PersonInvoicePhone.matches(num)){
                                tRemind("请输入正确的手机号码");
                                return;
                            }
                        }


                        if (DemoUtils.isStringEmpty(PersonInvoiceName)) {
                            tRemind("请输入姓名");
                            return;
                        }

                        if(("电子发票").equals(invoiceType)){
                            emailStr = mEdCompanyInvoiceEmail.getText().toString().trim();
                            if (emailStr == null || emailStr.equals("") || !emailStr.contains("@")) {
                                tRemind("请输入正确的邮箱地址");
                                return;
                            }else{
                                data.putExtra("email", emailStr);
                            }
                        }

                        if ("null".equals(invoiceId)) {
                            data.putExtra("invoiceType", invoiceType);
                            data.putExtra("invoiceMessageType", invoiceMessageType);
                            data.putExtra("personName", PersonInvoiceName);
                            data.putExtra("personPhone", PersonInvoicePhone);
                            setResult(2, data);//返回data，2为result，data为intent对象
                            finish();//页面销毁
                        }else {
                            initInvoice(invoiceId);
                        }

                    }else if (invoiceMessageType.equals("公司"))
                    {
                        if (TextUtils.isEmpty(InvoicePhone)) {
                            tRemind("请输入手机号码");
                            return;
                        } else if (InvoicePhone.length() != 11) {
                            tRemind("手机号码位数不正确");
                            return;
                        } else {
                            String num = "[1][358]\\d{9}";
                            if (!InvoicePhone.matches(num)){
                                tRemind("请输入正确的手机号码");
                                return;
                            }
                        }


                        if (DemoUtils.isStringEmpty(InvoiceRise)){
                            tRemind("请输入发票抬头");
                            return;
                        }
                        if (DemoUtils.isStringEmpty(InvoiceNumber)){
                            tRemind("请输入纳税人识别号或统一社会代码");
                            return;
                        }
                        if (DemoUtils.isStringEmpty(InvoiceAddress)){
                            tRemind("请输入地址");
                            return;
                        }
                        if (DemoUtils.isStringEmpty(InvoiceBank)){
                            tRemind("请输入开户行");
                            return;
                        }
                        if (DemoUtils.isStringEmpty(InvoiceAccountNumber)){
                            tRemind("请输入账号");
                            return;
                        }

                        if(("电子发票").equals(invoiceType)){
                            emailStr = mEdCompanyInvoiceEmail.getText().toString().trim();
                            if (emailStr == null || emailStr.equals("") || !emailStr.contains("@")) {
                                tRemind("请输入正确的邮箱地址");
                                return;
                            }else{
                                data.putExtra("email", emailStr);
                            }
                        }

                        if ("null".equals(invoiceId)) {
                            data.putExtra("invoiceType", invoiceType);
                            data.putExtra("invoiceMessageType", invoiceMessageType);
                            data.putExtra("InvoiceRise", InvoiceRise);
                            data.putExtra("InvoiceNumber", InvoiceNumber);
                            data.putExtra("InvoiceAddress", InvoiceAddress);
                            data.putExtra("InvoicePhone", InvoicePhone);
                            data.putExtra("InvoiceBank", InvoiceBank);
                            data.putExtra("InvoiceAccountNumber", InvoiceAccountNumber);

                            setResult(2, data);//返回data，2为result，data为intent对象
                            finish();//页面销毁
                        }else {
                            initInvoice(invoiceId);
                        }
                    }
                }else {
                    ToastUtil2.showToast(context,"请选择发票信息");
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 申请开票
     */
    private void initInvoice(String systemOrderNo) {
        showDialog("申请中...");
        String types = "";
        if (invoiceType.equals("电子发票")) {
            types = "0";
        } else if (invoiceType.equals("纸质发票")) {
            types = "1";
        }

        if (invoiceMessageType.equals("个人")) {
            ReqInvoice2Info invoice2Info = new ReqInvoice2Info();
            invoice2Info.setTypes(types);
            invoice2Info.setEmail(emailStr);
            invoice2Info.setPhone(PersonInvoicePhone);
            invoice2Info.setRaised(PersonInvoiceName);
            invoice2Info.setSystemOrderNo(systemOrderNo);
            if (!TUtils.isMobileNO(PersonInvoicePhone)) {
               tRemind("手机号未输入或格式错误");
               return;
            }else if(types.equals("0")){
                if (emailStr == null || emailStr.equals("") || !emailStr.contains("@")) {
                    tRemind("请输入正确的邮箱地址");
                    return;
                }
            }
            getPresenter().applyInvoice2(invoice2Info);
        }else if (invoiceMessageType.equals("公司")) {
            ReqInvoiceInfo invoiceInfo = new ReqInvoiceInfo();
            invoiceInfo.setTypes(types);
            invoiceInfo.setEmail(emailStr);
            invoiceInfo.setPhone(InvoicePhone);
            invoiceInfo.setRaised(InvoiceRise);
            invoiceInfo.setSystemOrderNo(systemOrderNo);
            invoiceInfo.setAccountNumber(InvoiceAccountNumber);
            invoiceInfo.setOpeningBank(InvoiceBank);
            invoiceInfo.setAddress(InvoiceAddress);
            invoiceInfo.setIdentifyNo(InvoiceNumber);
            if (!TUtils.isMobileNO(InvoicePhone)) {
                tRemind("手机号未输入或格式错误");
                return;
            }else if(types.equals("0")){
                if (emailStr == null || emailStr.equals("") || !emailStr.contains("@")) {
                    tRemind("请输入正确的邮箱地址");
                    return;
                }
            }
            getPresenter().applyInvoice(invoiceInfo);
        }
    }

    @Override
    public void applyInvoiceSuccess() {

        ToastUtil2.showToast(context, "开票成功");
        setResult(200);//返回data，2为result，data为intent对象
        finish();//页面销毁
    }
}
