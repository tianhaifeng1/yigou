package com.work.doctor.fruits.activity.order;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.t.databaselib.BigDecimalUtil;
import com.t.databaselib.DatabaseGoodsInfo;
import com.t.databaselib.DatabaseShopInfo;
import com.t.databaselib.GreenDaoAssist;
import com.t.httplib.yigou.bean.WechetPayInfo;
import com.t.httplib.yigou.bean.req.ReqInvoice2Info;
import com.t.httplib.yigou.bean.req.ReqInvoiceInfo;
import com.t.httplib.yigou.bean.req.ReqOrderSubmitInfo;
import com.t.httplib.yigou.bean.resp.AddressInfoBean;
import com.t.httplib.yigou.bean.resp.DistributionInfoBean;
import com.t.httplib.yigou.bean.resp.GoodsAffirmBean;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.SnackbarUtil;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.MainNavActivity;
import com.work.doctor.fruits.activity.adapter.ShopCartAdapter2;
import com.work.doctor.fruits.activity.coupon.CouponUseActivity;
import com.work.doctor.fruits.activity.vip.MVipActivity;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.assist.DemoUtils;
import com.work.doctor.fruits.base.DemoApplication;
import com.work.doctor.fruits.base.DemoMVPActivity;
import com.work.doctor.fruits.dialog.AddressSelectDialog;
import com.work.doctor.fruits.dialog.RemindDialog;
import com.work.doctor.fruits.wxapi.WXPayEntryActivity;
import com.xzte.maplib.baidu.SearchAddressActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * 确认订单页面
 */
public class GoodsAffirmActivity extends DemoMVPActivity<GoodsAffirmView, GoodsAffirmPresenter>
        implements GoodsAffirmView, View.OnClickListener, AddressSelectDialog.OnAddressSelectListener {

    private TextView mOaSelectAddressText;
    private RelativeLayout mOaSelectAddressRl;
    private TextView mOaShowAddressName;
    private TextView mOaShowAddressPhone;
    private ImageView mOaShowAddressEdit;
    private RelativeLayout mOaShowAddressRl;
    private EditText mOaEditName;
    private EditText mOaEditPhoneNumber;
    private TextView mOaEditSelectArea;
    private TextView mOaEditDetailedAddress;
    private EditText mOaEditDetailedAddressdetail;
    private LinearLayout mOaEditLl;
    private TextView mOaEditSave;
    private TextView mOaShopName;
    private TextView mOaShopAddress;
    private RecyclerView mOaGoodslistRecyclerview;
    private TextView mOaGoodslistTotalprice;
    private RelativeLayout mOaGoodslistYhj;
    private TextView mOaGoodslistYhjText;
    private TextView mOaPssmText;
    private RelativeLayout mOaPssm;
    private TextView mOaYfTextname;
    private TextView mOaYfText;
    private RelativeLayout mOaYf;
    private TextView mOaFpxxText;
    private RelativeLayout mOaFpxx;
    private EditText mOaBeizhu;
    private TextView mOaReallyprice;
    private TextView mOaPaywx;
    private TextView mOaPayye;
    private TextView mOaPaTextDate;
    private TextView mOaEditNo;
    private TextView mOaDistributionBtn;
    private TextView nOaTostoreBtn;
    private TextView mOaZitiShopPhone;
    private TextView mOaZitiShopName;
    private TextView mOaZitiShopAddress;
    private TextView mOaShowDate;
    private TextView mOaPayhdfk;
    private EditText mOaZitiName;
    private EditText mOaZitiPhoneNumber;

    private LinearLayout mOaFenleiRel;
    private LinearLayout mOaDistributionRel;
    private LinearLayout mOaZitiRel;

    private ShopCartAdapter2 shopCartAdapter;
    private ArrayList<DatabaseShopInfo> shopInfoArrayList;
    private int index;


    private int mjjindex = -1;
    private int psjindex = -1;

    private int code;

    private String shopId;

    //    存放地址
    private AddressInfoBean addressInfoBeanEdit;

    private String PersonEmail;
    private String invoiceType, invoiceMessageType;
    private String PersonInvoiceName, PersonInvoicePhone;
    private String InvoiceRise, InvoiceNumber, InvoiceAddress, InvoicePhone, InvoiceBank, InvoiceAccountNumber;

    private GreenDaoAssist greenDaoAssist;

    private int optionsDay;
    private int optionshh;
    private int optionsshowDay;
    private int distribId;
    private int distype = 0;

    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_affirm);
    }

    @Override
    protected void initView() {
        super.initView();

        greenDaoAssist = new GreenDaoAssist(((DemoApplication)getApplication()).databaseAssist);

        titleModule.initTitle("提交订单", true);
        titleModule.setBackImage(R.mipmap.ic_back_white);
        titleModule.setTitleTextColor(R.color.color_white);
        titleModule.setTitleBackground(R.color.transparent);
        titleModule.setTitleBottomLineShow(false);

        mOaSelectAddressText = findViewById(R.id.oa_select_address_text);
        mOaSelectAddressRl = findViewById(R.id.oa_select_address_rl);
        mOaShowAddressName = findViewById(R.id.oa_show_address_name);
        mOaShowAddressPhone = findViewById(R.id.oa_show_address_phone);
        mOaShowAddressEdit = findViewById(R.id.oa_show_address_edit);
        mOaShowAddressRl = findViewById(R.id.oa_show_address_rl);
        mOaEditName = findViewById(R.id.oa_edit_name);
        mOaEditPhoneNumber = findViewById(R.id.oa_edit_phone_number);
        mOaEditSelectArea = findViewById(R.id.oa_edit_select_area);
        mOaEditDetailedAddress = findViewById(R.id.oa_edit_detailed_address);
        mOaEditDetailedAddressdetail = findViewById(R.id.oa_edit_detailed_addressdetail);
        mOaEditLl = findViewById(R.id.oa_edit_ll);
        mOaEditSave = findViewById(R.id.oa_edit_save);
        mOaShopName = findViewById(R.id.oa_shop_name);
        mOaShopAddress = findViewById(R.id.oa_shop_address);
        mOaGoodslistRecyclerview = findViewById(R.id.oa_goodslist_recyclerview);
        mOaGoodslistTotalprice = findViewById(R.id.oa_goodslist_totalprice);
        mOaGoodslistYhj = findViewById(R.id.oa_goodslist_yhj);
        mOaGoodslistYhjText = findViewById(R.id.oa_goodslist_yhjtext);
        mOaPssmText = findViewById(R.id.oa_pssm_text);
        mOaPssm = findViewById(R.id.oa_pssm);
        mOaYfTextname = findViewById(R.id.oa_yf_textname);
        mOaYfText = findViewById(R.id.oa_yf_text);
        mOaYf = findViewById(R.id.oa_yf);
        mOaFpxxText = findViewById(R.id.oa_fpxx_text);
        mOaFpxx = findViewById(R.id.oa_fpxx);
        mOaBeizhu = findViewById(R.id.oa_beizhu);
        mOaReallyprice = findViewById(R.id.oa_reallyprice);
        mOaPaywx = findViewById(R.id.oa_paywx);
        mOaPayye = findViewById(R.id.oa_payye);
        mOaPaTextDate = findViewById(R.id.oa_text_date);
        mOaEditNo = findViewById(R.id.oa_edit_no);
        mOaDistributionBtn = findViewById(R.id.oa_distribution_btn);
        nOaTostoreBtn = findViewById(R.id.oa_tostore_btn);
        mOaFenleiRel = findViewById(R.id.oa_fenlei_rel);
        mOaDistributionRel = findViewById(R.id.oa_distribution_rel);
        mOaZitiRel = findViewById(R.id.oa_ziti_rel);
        mOaZitiShopName = findViewById(R.id.oa_ziti_shop_name);
        mOaZitiShopPhone = findViewById(R.id.oa_ziti_shop_phone);
        mOaZitiShopAddress = findViewById(R.id.oa_ziti_shop_address);
        mOaShowDate = findViewById(R.id.oa_show_date);
        mOaZitiName = findViewById(R.id.oa_ziti_name);
        mOaZitiPhoneNumber = findViewById(R.id.oa_ziti_phone_number);
        mOaPayhdfk = findViewById(R.id.oa_payhdfk);

//        mOaPssmText.setText("满50元包邮");
        mOaFpxxText.setText("不开发票");

        mOaSelectAddressRl.setOnClickListener(this);
        mOaShowAddressEdit.setOnClickListener(this);
        mOaEditSelectArea.setOnClickListener(this);
        mOaEditSave.setOnClickListener(this);
        mOaGoodslistYhj.setOnClickListener(this);
        mOaFpxxText.setOnClickListener(this);
        mOaPaywx.setOnClickListener(this);
        mOaPayye.setOnClickListener(this);
        mOaPaTextDate.setOnClickListener(this);
        mOaDistributionBtn.setOnClickListener(this);
        nOaTostoreBtn.setOnClickListener(this);
        mOaPayhdfk.setOnClickListener(this);

        //判断批发用户则显示货到付款按钮
        if(DemoConstant.userStatus==3){
            mOaPayhdfk.setVisibility(View.VISIBLE);
        }else{
            mOaPayhdfk.setVisibility(View.GONE);
        }


        //添加事件
        mOaEditName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addressInfoBeanEdit.setName(s.toString());
            }
        });
        mOaEditPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addressInfoBeanEdit.setPhone(s.toString());
            }
        });
        mOaEditDetailedAddressdetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addressInfoBeanEdit.setDetailAddress(s.toString());
            }
        });

        Intent intent = getIntent();
        code = intent.getIntExtra("code", 0);

        shopId = intent.getStringExtra("shopid");
        if (shopId != null && shopId.equals(DemoConstant.shopInfoBean.getShopId() + "")) {
            //当前店铺
            if (DemoConstant.addressInfoBean == null) {
                //未选择收货地址
                //显示编辑地址
            } else {
                addressInfoBeanEdit = DemoConstant.addressInfoBean;

//              一选择收货地址

                if(DemoConstant.addressInfoBean.getRange() == 0) {

                    String address = DemoUtils.changeAddressDetail(MainNavActivity.getprovince,
                            MainNavActivity.getcity,
                            MainNavActivity.getcounty,
                            MainNavActivity.name);

                    mOaEditName.setText(DemoConstant.addressInfoBean.getName());
                    mOaEditPhoneNumber.setText(DemoConstant.addressInfoBean.getPhone());
                    mOaEditSelectArea.setText(address);
                    mOaEditDetailedAddress.setText(DemoConstant.addressInfoBean.getAddress());
                    mOaEditDetailedAddressdetail.setText(DemoConstant.addressInfoBean.getDetailAddress());

                    mOaEditSave.setVisibility(View.GONE);
                    mOaEditNo.setVisibility(View.VISIBLE);

                } else if(DemoConstant.addressInfoBean.getRange() == 1){
                    mOaShowAddressRl.setVisibility(View.VISIBLE);
                    mOaEditLl.setVisibility(View.GONE);
                    mOaShowAddressName.setText(DemoConstant.addressInfoBean.getAddress() + DemoConstant.addressInfoBean.getDetailAddress());
                    mOaShowAddressPhone.setText(DemoConstant.addressInfoBean.getName() + "  " + DemoConstant.addressInfoBean.getPhone());
                }




            }
        } else {
            //其它店铺
            //显示编辑地址
        }

        //初始化
        if (addressInfoBeanEdit == null) {
            addressInfoBeanEdit = new AddressInfoBean();
        }
        initAddressInfo();

        getPresenter().getListDataAddress(shopId, 1, 1000);

        //初始化商品列表
        ArrayList<DatabaseGoodsInfo> arrayList = null;

        if (code == 1) {
//            商品详情数据
            arrayList = (ArrayList<DatabaseGoodsInfo>) intent.getSerializableExtra("list");
            mOaShopName.setText(DemoConstant.shopInfoBean.getShopName());
            mOaShopAddress.setText(DemoConstant.shopInfoBean.getShopIntro());
        } else if (code == 2) {
            //购物车数据
            shopInfoArrayList = (ArrayList<DatabaseShopInfo>) intent.getSerializableExtra("shop_list");
            index = intent.getIntExtra("shop_index", -1);
            DatabaseShopInfo shopInfo = shopInfoArrayList.get(index);

            arrayList = (ArrayList<DatabaseGoodsInfo>) intent.getSerializableExtra("goods_list");

            mOaShopName.setText(shopInfo.getShopName());
            mOaShopAddress.setText(shopInfo.getShopIntro());
        }
        shopCartAdapter = new ShopCartAdapter2(null);
        //计算高度
//        if (arrayList != null && arrayList.size() > 0) {
//            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mOaGoodslistRecyclerview.getLayoutParams();
//            params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
//            params.height = context.getResources().getDimensionPixelOffset(R.dimen.dp110) * arrayList.size();
//            mOaGoodslistRecyclerview.setLayoutParams(params);
//        }

        mOaGoodslistRecyclerview.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mOaGoodslistRecyclerview.setAdapter(shopCartAdapter);

        getTotalPrice(arrayList);
        mOaGoodslistTotalprice.setText("￥" + BigDecimalUtil.roundOffString(totalPrice, 2));

        mOaZitiShopName.setText(DemoConstant.shopInfoBean.getShopName());
        mOaZitiShopPhone.setText(DemoConstant.shopInfoBean.getShopPhone());
        mOaZitiShopAddress.setText(DemoConstant.shopInfoBean.getShopAddress());

        showDistribution();

        //获取优惠卷
        //获取运费
        //计算实际支付金额
        getPresenter().getInfo(shopId, arrayList);
    }

    @Override
    protected GoodsAffirmPresenter initPersenter() {
        return new GoodsAffirmPresenter(this);
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.oa_show_address_edit:
                //地址编辑
                if (addressInfoBeanEdit != null) {
                    mOaEditLl.setVisibility(View.VISIBLE);
                    mOaShowAddressRl.setVisibility(View.GONE);
                }
                break;
            case R.id.oa_edit_save:
                //地址保存
                if (addressInfoBeanEdit != null) {
                    //保存并使用
                    getPresenter().updateAddress(addressInfoBeanEdit);
                }
                break;
            case R.id.oa_goodslist_yhj:
                if (orderDetailInfoBean != null && orderDetailInfoBean.getUseNum() > 0) {
                    Intent i = new Intent(context, CouponUseActivity.class);
                    i.putExtra("nolist", orderDetailInfoBean.getUnuseList());
                    i.putExtra("list", orderDetailInfoBean.getUseList());
                    i.putExtra("mjjindex", mjjindex);
                    i.putExtra("psjindex", psjindex);
                    skipActivity(i, 100);
                }
                break;
            case R.id.oa_select_address_rl:
                //点击选择地址
                if (useAddressList.size() > 0) {
                    AddressSelectDialog addressSelectDialog = new AddressSelectDialog.Builder(context)
                            .setAddressList(useAddressList)
                            .setPostion(addressIndex)
                            .setOnAddressSelectListener(this)
                            .create();
                    addressSelectDialog.show(getSupportFragmentManager(), "dialog_address_select");
                }
                break;
            case R.id.oa_edit_select_area:
                Intent intent = new Intent(context, SearchAddressActivity.class);
                intent.putExtra("isShowMapView", false);
                intent.putExtra("title", "选择收货地址");
                skipActivity(intent, 101);
                break;
            case R.id.oa_fpxx_text:
                //发票
                if(!openInvoice){//未开发票的情况，避免重复开发票
                    Intent i = new Intent(context, InvoiceActivity.class);
                    i.putExtra("invoice", "null");
                    skipActivity(i, 102);
                }
                break;
            case R.id.oa_paywx:

                //微信支付
                payType = 2;
                pay();
                break;
            case R.id.oa_payye:
                RemindDialog remindDialog1 = new RemindDialog.Builder(context)
                        .setMessage("确认使用 余额支付 吗？")
                        .setCancleText("考虑一下")
                        .setAffirmText("确认")
                        .setCancelable(true)
                        .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                            @Override
                            public void onRemindClickAffirm(View view) {
                                //余额支付
                                payType = 4;
                                pay();
                            }

                            @Override
                            public void onRemindClickCancle(View view) {

                            }
                        }).create();
                remindDialog1.show(getSupportFragmentManager(),"dialog_remind_buy_goods");
                break;
            case R.id.oa_payhdfk:
                RemindDialog remindDialog2 = new RemindDialog.Builder(context)
                        .setMessage("确认使用 货到付款 吗？")
                        .setCancleText("考虑一下")
                        .setAffirmText("确认")
                        .setCancelable(true)
                        .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                            @Override
                            public void onRemindClickAffirm(View view) {
                                //货到付款
                                payType = 5;
                                pay();
                            }

                            @Override
                            public void onRemindClickCancle(View view) {

                            }
                        }).create();
                remindDialog2.show(getSupportFragmentManager(),"dialog_remind_buy_goods");
                break;
            case R.id.oa_text_date:
                showDate();
                break;
            case R.id.oa_distribution_btn:
                distype = 0;
                showDistribution();
                break;
            case R.id.oa_tostore_btn:
                distype = 1;
                mOaDistributionBtn.setTextColor(Color.BLACK);
                nOaTostoreBtn.setTextColor(Color.RED);
                mOaFenleiRel.setEnabled(false);
                mOaDistributionRel.setVisibility(View.GONE);
                mOaZitiRel.setVisibility(View.VISIBLE);
                getPresenter().getDataDistribution(distype);
                mOaShowDate.setText("预计到店时间");
                break;
        }
    }

    private void showDistribution() {
        mOaDistributionBtn.setTextColor(Color.RED);
        nOaTostoreBtn.setTextColor(Color.BLACK);
        mOaFenleiRel.setEnabled(true);
        mOaDistributionRel.setVisibility(View.VISIBLE);
        mOaZitiRel.setVisibility(View.GONE);
        getPresenter().getDataDistribution(distype);
        mOaShowDate.setText("期望送达时间");
    }

    //配送时间段显示
    private void showDate() {
        if(distributionInfoBeanList==null){
            return;
        }
        OptionsPickerView pvOptions = new OptionsPickerBuilder(GoodsAffirmActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 , View v) {
                //返回的分别是三个级别的选中位置
                String tx = "";
                optionsDay=options1;
                optionshh=option2;
                if(distributionInfoBeanList.getList_now()!=null && distributionInfoBeanList.getList_now().size()>0){
                    if(options1==0){
                        distribId = distributionInfoBeanList.getList_now().get(option2).getId();
                    }else if(options1==1){
                        distribId = distributionInfoBeanList.getList_other().get(option2).getId();
                    }

                }else{
                    distribId = distributionInfoBeanList.getList_other().get(option2).getId();
                }


                if(options1==0&&option2==0&&distributionInfoBeanList.getList_now()!=null&& distributionInfoBeanList.getList_now().size()>0){
                    tx = options2Items.get(options1).get(option2);
                }else{
                    tx = options1Items.get(options1)+" | "
                            + options2Items.get(options1).get(option2);
                }
                mOaPaTextDate.setText(tx);
            }
        }).setTitleText("请选择配送时间")
                .setContentTextSize(16)//滚轮文字大小
                .setSelectOptions(optionsDay, optionshh)  //设置默认选中项
                .setLineSpacingMultiplier((float) 2.0)
                .setOutSideCancelable(false)
                .build();
        pvOptions.setPicker(options1Items, options2Items);
        pvOptions.show();
    }


    private int payType = -1;

    private void pay() {
        if (systemOrderNo != null && !systemOrderNo.equals("")) {
            getSubmitOrderSuccess(systemOrderNo);
            return;
        }

        String noteStr = mOaBeizhu.getText().toString().trim();

        ReqOrderSubmitInfo submitInfo = new ReqOrderSubmitInfo();

        submitInfo.setIsCart(code == 2 ? 1 : 0);
        if(psjindex > -1){
            submitInfo.setdCouponId(orderDetailInfoBean.getUseList().get(psjindex).getId() + "");
        }
        if(mjjindex > -1){
            submitInfo.setmCouponId(orderDetailInfoBean.getUseList().get(mjjindex).getId() + "");
        }

//        if (distributionInfoBeanList == null || distributionInfoBeanList.size() == 0) {
//        if (distributionInfoBeanList == null ) {
//            submitInfo.setDistribId("");
//        } else {
//            submitInfo.setDistribId(distributionInfoBeanList.getList_other().get(0).getId() + "");
//        }

        if (noteStr != null && !noteStr.equals("")) {
            submitInfo.setNote(noteStr);
        }
        submitInfo.setShopId(shopId);

        String priceStr = mOaReallyprice.getText().toString().trim();
        if (priceStr.equals("")) {
            SnackbarUtil.showToast(rootView, "价格异常，请重新选择商品");
            return;
        } else {
            try {
                priceStr = priceStr.substring(priceStr.indexOf("实付金额：￥") + 6);
                submitInfo.setTotalOrderFee(new BigDecimal(priceStr));
//                余额支付：当余额不足的情况
                if (payType == 4 && DemoConstant.balance.floatValue() - submitInfo.getTotalOrderFee().floatValue() < 0) {
                    SnackbarUtil.showToast(rootView, "当前余额不足");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(distributionInfoBeanList.getList_now()!=null && distributionInfoBeanList.getList_now().size()>0){
            submitInfo.setDistribDay(optionsDay+"");
        }else{
            submitInfo.setDistribDay(optionsDay+1+"");
        }
        submitInfo.setDistribId(distribId+"");
        if (distype==0) {
            if(addressInfoBeanEdit.getId()==null||addressInfoBeanEdit.getId().equals("")){
                tRemind("请选择地址");
                return;
            }
            submitInfo.setAddressId(addressInfoBeanEdit.getId());
            submitInfo.setReceiverName("");
            submitInfo.setReceiverPhone("");
        } else if(distype==1) {
            if(TextUtils.isEmpty(mOaZitiName.getText().toString().trim())){
                tRemind("请输入提货人姓名");
                return;
            }
            if (TextUtils.isEmpty(mOaZitiPhoneNumber.getText().toString().trim())) {
                tRemind("请输入提货人号码");
                return;
            } else if (mOaZitiPhoneNumber.getText().toString().trim().length() != 11) {
                tRemind("提货人号码位数不正确");
                return;
            } else {
                String phone_number = mOaZitiPhoneNumber.getText().toString().trim();
                String num = "[1][358]\\d{9}";
                if (!phone_number.matches(num)){
                    tRemind("请输入正确的手机号码");
                    return;
                }
            }
            submitInfo.setAddressId("");
            submitInfo.setReceiverName(mOaZitiName.getText().toString().trim());
            submitInfo.setReceiverPhone(mOaZitiPhoneNumber.getText().toString().trim());
        }

        //提交
        getPresenter().submitOrder(submitInfo);
    }



    private float totalPrice = 0.0f;

    //总价格价格
    private void getTotalPrice(List<DatabaseGoodsInfo> goodsInfoList) {
        if (goodsInfoList != null && goodsInfoList.size() > 0) {
            for (int i = 0; i < goodsInfoList.size(); i++) {
                DatabaseGoodsInfo goodsInfoIn = goodsInfoList.get(i);
                totalPrice = BigDecimalUtil.add(totalPrice, goodsInfoIn.getGoodsNumber() * goodsInfoIn.getGoodsPriceVip()).floatValue();
            }
        }
    }

    /**
     * 申请开票
     */
    private void initInvoice(String systemOrderNo) {

        if (invoiceMessageType.equals("个人")) {
            ReqInvoice2Info invoice2Info = new ReqInvoice2Info();
            invoice2Info.setTypes(invoiceType);
            invoice2Info.setEmail(PersonEmail);
            invoice2Info.setPhone(PersonInvoicePhone);
            invoice2Info.setRaised(PersonInvoiceName);
            invoice2Info.setSystemOrderNo(systemOrderNo);

            getPresenter().applyInvoice2(invoice2Info);
        } else if (invoiceMessageType.equals("公司")) {
            ReqInvoiceInfo invoiceInfo = new ReqInvoiceInfo();
            invoiceInfo.setTypes(invoiceType);
            invoiceInfo.setEmail(PersonEmail);
            invoiceInfo.setPhone(InvoicePhone);
            invoiceInfo.setRaised(InvoiceRise);
            invoiceInfo.setSystemOrderNo(systemOrderNo);
            invoiceInfo.setAccountNumber(InvoiceAccountNumber);
            invoiceInfo.setOpeningBank(InvoiceBank);
            invoiceInfo.setAddress(InvoiceAddress);
            invoiceInfo.setIdentifyNo(InvoiceNumber);

            getPresenter().applyInvoice(invoiceInfo);
        }
    }


    private GoodsAffirmBean orderDetailInfoBean;



    @Override
    public void getInfoSuccess(GoodsAffirmBean infoBean) {
        this.orderDetailInfoBean = infoBean;
        int useNum = infoBean.getUseNum();
        shopCartAdapter.setNewData(infoBean.getOrder().getDetails());
        if (useNum == 0) {
            mOaGoodslistYhjText.setText("暂无可用的优惠券");
            mOaGoodslistYhjText.setEnabled(false);
        } else {
            mOaGoodslistYhjText.setText("有 " + useNum + " 张优惠券可以使用");
            mOaGoodslistYhjText.setEnabled(true);
        }
        try {
            BigDecimal distribFee = BigDecimal.valueOf(infoBean.getOrder().getDistribFee());
            if(infoBean.getOrder().getDistribFee()==0){
                mOaYfText.setText("免邮");
            }else{
                mOaYfText.setText("￥" + BigDecimalUtil.roundOffString(distribFee.floatValue(),2));
            }
            mOaReallyprice.setText("实付金额：￥" + BigDecimalUtil.roundOffString(BigDecimalUtil.add(totalPrice, distribFee.floatValue()), 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getInfoFail(String msg) {
        RemindDialog remindDialog = new RemindDialog.Builder(context)
                .setCancelable(false)
                .setMessage(msg)
                .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                    @Override
                    public void onRemindClickAffirm(View view) {
                        finish();
                    }

                    @Override
                    public void onRemindClickCancle(View view) {
                        finish();
                    }
                })
                .create();
        remindDialog.show(getSupportFragmentManager(), "dialog_remind_fail");
    }

    //    可选择地址的集合
    private List<AddressInfoBean> useAddressList = new ArrayList<>();
    //记录选择的地址位置
    private int addressIndex = -1;

    @Override
    public void getListDataSuccess(List<AddressInfoBean> beanList) {
        useAddressList.clear();
        if (beanList != null && beanList.size() > 0) {
            for (int i = 0; i < beanList.size(); i++) {
                AddressInfoBean infoBean = beanList.get(i);
                if (infoBean.getRange() == 1) {
                    // 范围内的地址
                    useAddressList.add(infoBean);
                }
            }
            if (useAddressList.size() > 0) {
                mOaSelectAddressRl.setVisibility(View.VISIBLE);
                mOaSelectAddressText.setText("共" + useAddressList.size() + "个");
            }
        }

    }

    @Override
    public void updateAddressSuccess(AddressInfoBean infoBean) {
        addressInfoBeanEdit = infoBean;
        initAddressInfo();
        mOaEditLl.setVisibility(View.GONE);
        mOaShowAddressRl.setVisibility(View.VISIBLE);
    }

    private DistributionInfoBean distributionInfoBeanList;

    private static String ToDaymWay;
    private static String ToMowmWay;
    private static String ToToMowWay;

    @Override
    public void getDistributionInfoSuccess(DistributionInfoBean list) {
        distributionInfoBeanList = list;
        if (list != null) {

//          切换选项卡初始化数据
            options1Items.clear();
            options2Items.clear();
            optionsDay=0;
            optionshh=0;

            DistributionInfoBean infoBean = list;

            if(distype == 0){
                mOaPssm.setVisibility(View.VISIBLE);
                mOaYf.setVisibility(View.VISIBLE);
//            配送费与起始配送金额
                mOaPssmText.setText("满" + BigDecimalUtil.roundOffString(infoBean.getList_other().get(0).getOriginFee(),2) + "元包邮");
                mOaYfTextname.setText("运费(满" + BigDecimalUtil.roundOffString(infoBean.getList_other().get(0).getOriginFee(),2) + "元包邮)");
//                mOaYfText.setText("￥" + BigDecimalUtil.roundOffString(infoBean.getList_other().get(0).getDistribFee(),2));
//                mOaReallyprice.setText("实付金额：￥" + BigDecimalUtil.roundOffString(BigDecimalUtil.add(totalPrice, infoBean.getList_other().get(0).getDistribFee()), 2));
                if(orderDetailInfoBean!=null){
                    BigDecimal distribFee = BigDecimal.valueOf(orderDetailInfoBean.getOrder().getDistribFee());
                    if(orderDetailInfoBean.getOrder().getDistribFee()==0){
                        mOaYfText.setText("免邮");
                    }else{
                        mOaYfText.setText("￥" + BigDecimalUtil.roundOffString(distribFee.floatValue(),2));
                    }
                    mOaReallyprice.setText("实付金额：￥" + BigDecimalUtil.roundOffString(BigDecimalUtil.add(totalPrice, distribFee.floatValue()), 2));
                }
            } else {
                mOaReallyprice.setText("实付金额：￥" + BigDecimalUtil.roundOffString(totalPrice, 2));
                mOaPssm.setVisibility(View.GONE);
                mOaYf.setVisibility(View.GONE);
            }

 //         获取当前时间判断周几
            final Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            ToDaymWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
            c.add(c.DATE,1);
            ToMowmWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
            c.add(c.DATE,1);
            ToToMowWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));

            if(infoBean.getList_now()!=null && infoBean.getList_now().size()>0){
                options1Items.add("今天"+"("+getDateString(ToDaymWay)+")");
            }
            options1Items.add("明天"+"("+getDateString(ToMowmWay)+")");

            if(infoBean.getList_now()==null || infoBean.getList_now().size()==0){
                options1Items.add("后天"+"("+getDateString(ToToMowWay)+")");
            }

            ArrayList<String> options2Items_01 = new ArrayList<>();
            if(infoBean.getList_now()!=null && infoBean.getList_now().size()>0){
                for(int i = 0;i<infoBean.getList_now().size();i++){
                    options2Items_01.add(infoBean.getList_now().get(i).getDistribTimeSort());
                }
            }else{
                for(int i = 0;i<infoBean.getList_other().size();i++){
                    options2Items_01.add(infoBean.getList_other().get(i).getDistribTimeSort());
                }
            }
            ArrayList<String> options2Items_02 = new ArrayList<>();
            for(int i = 0;i<infoBean.getList_other().size();i++){
                options2Items_02.add(infoBean.getList_other().get(i).getDistribTimeSort());
            }
            options2Items.add(options2Items_01);
            options2Items.add(options2Items_02);
            optionsDay = 0;
            String tx = "";
            if(infoBean.getList_now()!=null && infoBean.getList_now().size()>0){
                tx = infoBean.getList_now().get(0).getDistribTimeSort();
                mOaPaTextDate.setText(tx);
                distribId = infoBean.getList_now().get(0).getId();
            }else{
                tx = "明天"+"("+getDateString(ToMowmWay)+")"+infoBean.getList_other().get(0).getDistribTimeSort();
                mOaPaTextDate.setText(tx);
                distribId = infoBean.getList_other().get(0).getId();
            }
        }
    }

    private String getDateString(String date) {
        if ("1".equals(date)) {
            date = "天";
        } else if ("2".equals(date)) {
            date = "一";
        } else if ("3".equals(date)) {
            date = "二";
        } else if ("4".equals(date)) {
            date = "三";
        } else if ("5".equals(date)) {
            date = "四";
        } else if ("6".equals(date)) {
            date = "五";
        } else if ("7".equals(date)) {
            date = "六";
        }
        return "周"+date;
    }

    private String systemOrderNo;

    @Override
    public void getSubmitOrderSuccess(String systemOrderNo) {
        if (this.systemOrderNo != null && !this.systemOrderNo.equals("")) {
            showDialog("支付中...");
        }else{
            setDialogText("支付中...");
        }
        this.systemOrderNo = systemOrderNo;
        if (!openInvoice && invoiceType != null && !invoiceType.equals("")) {
            initInvoice(systemOrderNo);
        }

        // 删除购物车
        if (code == 2) {
            if (shopInfoArrayList != null && shopInfoArrayList.size() > 0) {
                greenDaoAssist.deleteGoods(shopInfoArrayList, index, isChange ->DemoConstant.isChangeDatabase = isChange);
                DemoConstant.isBuyShoppingCartGoods = true;
            }
        }
//        支付
        getPresenter().payMent(systemOrderNo, payType);
    }

    @Override
    public void getPayMentSuccess(WechetPayInfo infoBean) {
        if (payType == 4) {
            tRemind("支付成功");
            DemoConstant.balance = infoBean.getBalance();
            Intent intent = new Intent(context, WXPayEntryActivity.class);
            intent.putExtra("code", 1);//成功
            skipActivity(intent);

        } else if (payType == 2) {
            //微信支付
            getPresenter().getWxPayInfo(context, infoBean);
        } else if (payType == 5){
            tRemind("支付成功");
            Intent intent = new Intent(context, WXPayEntryActivity.class);
            intent.putExtra("code", 1);//成功
            skipActivity(intent);
        }
        finish();
    }

    @Override
    public void getPayMentFaile203() {
        RemindDialog remindDialog = new RemindDialog.Builder(context)
                .setMessage("当前余额不足")
                .setCancleText("去充值")
                .setAffirmText("微信支付")
                .setOnRemindClickListener(new RemindDialog.OnRemindClickListener() {
                    @Override
                    public void onRemindClickAffirm(View view) {
                        payType = 2;
                        getPresenter().payMent(systemOrderNo, payType);
                    }

                    @Override
                    public void onRemindClickCancle(View view) {
                        //会员中心
                        skipActivity(MVipActivity.class);
                    }
                })
                .create();
        remindDialog.show(getSupportFragmentManager(),"dialog_remind_yue");
    }

    private boolean openInvoice = false;

    @Override
    public void applyInvoiceSuccess() {
        openInvoice = true;

//        ToastUtil2.showToast(context, "开票成功");
//        setResult(200);//返回data，2为result，data为intent对象
//        finish();//页面销毁


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            //优惠卷
            if (data != null) {
                mjjindex = data.getIntExtra("mjjindex", -1);
                psjindex = data.getIntExtra("psjindex", -1);

                try {
                    BigDecimal moneyYhj = initYhjMoney();
                    BigDecimal distribFee = BigDecimal.valueOf(orderDetailInfoBean.getOrder().getDistribFee());
                    mOaReallyprice.setText("实付金额：￥" + BigDecimalUtil.roundOffString(new BigDecimal(totalPrice).add(distribFee.subtract(moneyYhj)),2));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else if (requestCode == 101) {
            if (data != null) {
                //地址选择

                String name = data.getStringExtra("name");
                String getprovince = data.getStringExtra("sheng");
                String getcity = data.getStringExtra("shi");
                String getcounty = data.getStringExtra("qu");
                double la = data.getDoubleExtra("la", 0);
                double lo = data.getDoubleExtra("lo", 0);
                mOaEditSelectArea.setText(getprovince + "  " + getcity + "  " + getcounty);
                mOaEditDetailedAddress.setText(name);

                LatLng p1 = new LatLng(Double.valueOf(DemoConstant.shopInfoBean.getShopLatitude()),Double.valueOf(DemoConstant.shopInfoBean.getShopLongitude()));
                LatLng p2 = new LatLng(la, lo);


                if(DistanceUtil. getDistance(p1, p2)<DemoConstant.shopInfoBean.getDeliveryScope()){
                    mOaEditSave.setVisibility(View.VISIBLE);
                    mOaEditNo.setVisibility(View.GONE);
                }else{
                    mOaEditSave.setVisibility(View.GONE);
                    mOaEditNo.setVisibility(View.VISIBLE);
                }


                if (addressInfoBeanEdit != null) {
                    addressInfoBeanEdit.setAddress(name);
                    addressInfoBeanEdit.setProvince(getprovince);
                    addressInfoBeanEdit.setCity(getcity);
                    addressInfoBeanEdit.setCounty(getcounty);
                    addressInfoBeanEdit.setLatitude(la);
                    addressInfoBeanEdit.setLongitude(lo);
                }
            }

        } else if (requestCode == 102) {
            if (data != null) {
                PersonEmail = "";
                String type = data.getStringExtra("invoiceType");
//                invoiceMessageType = data.getStringExtra("invoiceMessageType");
                if (!DemoUtils.isStringEmpty(type)) {
                    if (type.equals("不开发票")) {
                        mOaFpxxText.setText(type);
                        invoiceType = "";
                    } else {
                        invoiceMessageType = data.getStringExtra("invoiceMessageType");
                        if (!DemoUtils.isStringEmpty(invoiceMessageType)) {
                            mOaFpxxText.setText(type + "(" + invoiceMessageType + ")");
                            if (invoiceMessageType.equals("个人")) {
                                PersonInvoiceName = data.getStringExtra("personName");
                                PersonInvoicePhone = data.getStringExtra("personPhone");
                                Logger.t("个人" + PersonInvoiceName + PersonInvoicePhone);
                            } else if (invoiceMessageType.equals("公司")) {
                                InvoiceRise = data.getStringExtra("InvoiceRise");
                                InvoiceNumber = data.getStringExtra("InvoiceNumber");
                                InvoiceAddress = data.getStringExtra("InvoiceAddress");
                                InvoicePhone = data.getStringExtra("InvoicePhone");
                                InvoiceBank = data.getStringExtra("InvoiceBank");
                                InvoiceAccountNumber = data.getStringExtra("InvoiceAccountNumber");
                                Logger.t("公司" + InvoiceRise + InvoiceNumber + InvoiceAddress + InvoicePhone + InvoiceBank + InvoiceAccountNumber);
                            }
                            if (("电子发票").equals(type)) {
                                PersonEmail = data.getStringExtra("email");
                                invoiceType = "0";
                            } else {
                                invoiceType = "1";
                            }
                        }
                    }
                }
            }
        }
    }

    private BigDecimal initYhjMoney() {

        if (orderDetailInfoBean != null && orderDetailInfoBean.getUseNum() > 0) {

            BigDecimal mjjvalue;
            BigDecimal psjvalue;

            if (mjjindex > -1) {
                mjjvalue = orderDetailInfoBean.getUseList().get(mjjindex).getCouponValue();
            } else {
                mjjvalue = BigDecimal.valueOf(0);
            }
            if (psjindex > -1) {
                psjvalue = orderDetailInfoBean.getUseList().get(psjindex).getCouponValue();
            } else {
                psjvalue = BigDecimal.valueOf(0);
            }

            BigDecimal add = mjjvalue.add(psjvalue);
            if (add.floatValue() > 0) {
                mOaGoodslistYhjText.setText("可优惠 " + add.setScale(2, BigDecimal.ROUND_HALF_UP) + " 元");
            } else {
                int useNum = orderDetailInfoBean.getUseNum();
                if (useNum == 0) {
                    mOaGoodslistYhjText.setText("暂无可用的优惠券");
                    mOaGoodslistYhjText.setEnabled(false);
                } else {
                    mOaGoodslistYhjText.setText("有 " + useNum + " 张优惠券可以使用");
                    mOaGoodslistYhjText.setEnabled(true);
                }
            }
            return add;
        }
        return BigDecimal.ZERO;

    }


    @Override
    public void onAddressSelect(View view, int position) {
        addressIndex = position;
        mOaEditLl.setVisibility(View.GONE);
        mOaShowAddressRl.setVisibility(View.VISIBLE);
        addressInfoBeanEdit = useAddressList.get(position);
        initAddressInfo();
    }

    //初始化地址信息
    private void initAddressInfo() {
        mOaShowAddressName.setText(addressInfoBeanEdit.getAddress() + addressInfoBeanEdit.getDetailAddress());
        mOaShowAddressPhone.setText(addressInfoBeanEdit.getName() + "  " + addressInfoBeanEdit.getPhone());

        mOaEditName.setText(addressInfoBeanEdit.getName());
        mOaEditPhoneNumber.setText(addressInfoBeanEdit.getPhone());
        mOaEditSelectArea.setText(addressInfoBeanEdit.getProvince() + "  " + addressInfoBeanEdit.getCity() + "  " + addressInfoBeanEdit.getCounty());
        mOaEditDetailedAddress.setText(addressInfoBeanEdit.getAddress());
        mOaEditDetailedAddressdetail.setText(addressInfoBeanEdit.getDetailAddress());

    }

}
