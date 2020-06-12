package com.work.doctor.fruits.activity.order;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.baidu.mapapi.map.UiSettings;
import com.t.httplib.yigou.DObserver;
import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.bean.WlList;
import com.t.httplib.yigou.bean.req.ReqHorsemanInfo;
import com.t.httplib.yigou.bean.req.ReqOrderInfo;
import com.t.httplib.yigou.bean.resp.ExpressPsInfoBean;
import com.t.httplib.yigou.bean.resp.HorsemanLocationInfoBean;
import com.trjx.tbase.module.titlemodule.TitleModule;
import com.trjx.tlibs.uils.GlideUtile;
import com.trjx.tlibs.uils.ToastUtil2;
import com.trjx.tlibs.views.TListView;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.WlAdapter;
import com.xzte.maplib.baidu.BaseBdMapActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * 作者：小童
 * 创建时间：2019/5/30 17:01
 * <p>
 * 描述：物流信息页面
 */
@RuntimePermissions
public class DdDeliveryDetailActivity extends BaseBdMapActivity {

//    private LinearLayout linearLayout;

    private TListView ddddTlistview;
    private TextView ddddTime;
    private ImageView ddddImg;
    private TextView ddddName;
    private TextView ddddPhone;

    private DemoModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dd_delivery_detail);
    }

    @Override
    protected void initView() {
        super.initView();
        model = new DemoModel();
        TitleModule titleModule = new TitleModule(context, rootView);
        titleModule.setListenter(this);
        titleModule.initTitle("物流信息", true);

//        linearLayout = findViewById(R.id.layout_default_all);
//        if(DemoUtils.isPfUser()){
//            return;
//        }
//        linearLayout.setVisibility(View.GONE);


//        31.227, 121.481
//        double la = 31.227;
//        double lo = 121.481;

//        userId = (String) PreferenceHelper.getData(Constant.USER_ID, "");


        ddddTlistview = findViewById(R.id.dddd_tlistview);
        ddddTime = findViewById(R.id.dddd_time);
        ddddImg = findViewById(R.id.dddd_img);
        ddddName = findViewById(R.id.dddd_name);
        ddddPhone = findViewById(R.id.dddd_phone);

        application.applicationAssist.startLoction();

        initData();

    }


    @Override
    protected void mapUiSetting() {
        super.mapUiSetting();
        UiSettings settings = baiduMap.getUiSettings();
        //选择是否显示指南针:默认显示
        settings.setCompassEnabled(false);
        //比例尺：默认显示
        mMapView.showScaleControl(false);
        //缩放按钮
        mMapView.showZoomControls(false);

//        控制是否一并禁止所有手势，默认关闭。
        settings.setAllGesturesEnabled(true);
    }

    protected void initData() {
//
        String orderNo = getIntent().getStringExtra("orderno");
        orderShopId = getIntent().getIntExtra("shopId", -1);
        if (orderNo == null || orderNo.equals("")) {
            ToastUtil2.showToast(context, "订单异常");
            return;
        }
        showDialog("请求中...");
        ReqOrderInfo info = new ReqOrderInfo();
        info.setSystemOrderNo(orderNo);
        model.requestOrderExpress(info, new DObserver<ExpressPsInfoBean>(this) {
            @Override
            public void onTPostSuccess(ExpressPsInfoBean expressPsInfoBean) {
                getInfoSuccess(expressPsInfoBean);
                List<WlList> wlLists = initWlList(expressPsInfoBean);

                WlAdapter adapter = new WlAdapter(context, wlLists);
                ddddTlistview.setAdapter(adapter);

                GlideUtile.bindImageViewRound(context, expressPsInfoBean.getPhotoUrl(), R.mipmap.t_head, ddddImg);

                ddddPhone.setOnClickListener(v -> {
                    if (expressPsInfoBean.getStatus() > 0) { //有骑手接单
//                                        ToastUtil2.showToast(context, "打电话" + takeoutBean.getReceiverPhone());
                        showPremission_call_phone(expressPsInfoBean.getHorsemanNum());
                    } else {
                        ToastUtil2.showToast(context, "暂无骑手接单");
                    }
                });
                ddddName.setText("| " + expressPsInfoBean.getName());
            }
        });


//        String time = TimeUtils.getCurrentTime();
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("userId", Constant.userId);
//        map.put("systemOrderNo", orderNo);
//        map.put("timestamp", time);
//        String json = "{\"extend\":{\"version\":\"" + Constant.VERSION + "\",\"terminal\":\"1\",\"timestamp\":\"" + time +
//                "\",\"sign\":\"" + UiUtils.createSignString(map) + "\"}" + ",\"main\":{\"userId\":\"" + Constant.userId + "\",\"systemOrderNo\":\"" + orderNo + "\"}}";
//        OkGo.<String>post(Urls.URL_EXPRESSPS)//
//                .tag(this)//
//                .upJson(json)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        dismissLoading();
//                        try {
//                            Log.e("New", "物流详情 成功：" + response.body());
//                            WlBean wlBean = gson.fromJson(response.body(), WlBean.class);
//                            if (wlBean.getStatus() == 200) {
//                                WlBean.BodyBean.TakeoutBean takeoutBean = wlBean.getBody().getTakeout();
//
//                                int status = takeoutBean.getStatus();
//                                if (status > 0 && status < 4) {
//                                    horsemanId = takeoutBean.getHorsemanId();
//                                    latitude = takeoutBean.getShopLatitude();
//                                    longitude = takeoutBean.getShopLongitude();
//
//                                    //获取骑手位置
//                                    getLocation2();
//                                }
//
//
//                                List<WlList> wlLists = initWlList(takeoutBean);
//
//                                WlAdapter adapter = new WlAdapter(context, wlLists);
//                                ddddTlistview.setAdapter(adapter);
//
//                                Glide.with(context).load(takeoutBean.getPhotoUrl())
//                                        .apply(RequestOptions.bitmapTransform(new CircleCrop())
//                                                .error(R.mipmap.t_head))
//                                        .into(ddddImg);
//
//                                ddddPhone.setOnClickListener(v -> {
//                                    if (takeoutBean.getStatus() > 0) { //有骑手接单
////                                        ToastUtil2.showToast(context, "打电话" + takeoutBean.getReceiverPhone());
//                                        call(takeoutBean.getHorsemanNum());
//                                    }else{
//                                        ToastUtil2.showToast(context,"暂无骑手接单");
//                                    }
//                                });
//                                ddddName.setText("| " + takeoutBean.getName());
//
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        dismissLoading();
//                        Log.e("New", "订单详情 失败：" + response.body());
//                        LogUtils.d(getResources().getString(R.string.for_failure));
//                    }
//                });


    }


    private int orderShopId = -1;

    public void getInfoSuccess(ExpressPsInfoBean infoBean) {
        int status = infoBean.getStatus();
        if (status > 0 && status < 4) {
            horsemanId = infoBean.getHorsemanId();
            latitude = Double.parseDouble(infoBean.getShopLatitude());
            longitude = Double.parseDouble(infoBean.getShopLongitude());
            //获取骑手位置
            getLocation2();
        }
    }

    /**
     * 打电话
     */
    private void callPhone(String phoneNum) {
        //我们需要告诉系统，我们的动作：我要打电话
        //创建意图对象
        Intent intent = new Intent();
        //把打电话的动作ACTION_CALL封装至意图对象当中
        intent.setAction(Intent.ACTION_CALL);
        //设置打给谁
        intent.setData(Uri.parse("tel:" + phoneNum));//这个tel：必须要加上，表示我要打电话。否则不会有打电话功能，由于在打电话清单文件里设置了这个“协议”
        //把动作告诉系统,启动系统打电话功能。
        startActivity(intent);
    }


    private String horsemanId;
    private double latitude;
    private double longitude;
    private Timer timer;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getLocation();
        }
    };

    private void getLocation2() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 0, 5 * 1000);
    }

    private void getLocation() {
        if (horsemanId == null || horsemanId.equals("") || orderShopId < 0) {
            return;
        }
        ReqHorsemanInfo info = new ReqHorsemanInfo();
        info.setHorsemanId(horsemanId);
        info.setShopId(orderShopId);
        model.requestLocation(info, new DObserver<HorsemanLocationInfoBean>(this) {
            @Override
            public void onTPostSuccess(HorsemanLocationInfoBean infoBean) {
                double la = infoBean.getLatitude();
                double lo = infoBean.getLongitude();
                if (la <= 0 || lo <= 0) {
                    la = latitude;
                    lo = longitude;
                }
                if (la > 0 && lo > 0) {
                    removeMarker();
                    addMarker(la, lo, R.mipmap.t_wuliu);
                    movePosition(la, lo);
                } else {
                    Log.i("tong", "位置信息异常，请确认");
                }
            }
        });

    }

    private List<WlList> initWlList(ExpressPsInfoBean takeoutBean) {

        List<WlList> wlLists = new ArrayList<>();
        //                                var arr=[
//                                { txt: '骑手已经送达', time: logistics.accomplishTime},
//                                { txt: '骑手正在送货', time: logistics.deliveryTime},
//                                { txt: '骑手已经接单', time: logistics.grabTime},
//                                { txt: '等待骑手接单', time: logistics.createTime}
//]


        Long createTime = takeoutBean.getCreateTime();
        if (createTime == null || createTime <= 0) {
            return wlLists;
        } else {
            WlList info = new WlList();
            info.setName("等待骑手接单");
            info.setTime(createTime);
            wlLists.add(info);
        }

        Long grabTime = takeoutBean.getGrabTime();
        if (grabTime == null || grabTime <= 0) {
            return wlLists;
        } else {
            WlList info2 = new WlList();
            info2.setName("骑手已经接单");
            info2.setTime(grabTime);
            wlLists.add(info2);
        }

        Long deliveryTime = takeoutBean.getDeliveryTime();
        if (deliveryTime == null || deliveryTime <= 0) {
            return wlLists;
        } else {
            WlList info3 = new WlList();
            info3.setName("骑手正在送货");
            info3.setTime(deliveryTime);
            wlLists.add(info3);
        }
        Long finishTime = takeoutBean.getAccomplishTime();
        if (finishTime == null || finishTime <= 0) {
            return wlLists;
        } else {
            WlList info4 = new WlList();
            info4.setName("骑手已经送达");
            info4.setTime(finishTime);
            wlLists.add(info4);
        }
        return wlLists;
    }

    private String phoneNumber = "0891-6373803";

    public void showPremission_call_phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        DdDeliveryDetailActivityPermissionsDispatcher.getPremission_call_phoneWithPermissionCheck(this);
    }

    @NeedsPermission({Manifest.permission.CALL_PHONE})
    void getPremission_call_phone() {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("是否联系骑手？")
                .setPositiveButton("拨打", (dialog, which) -> {
                    callPhone(phoneNumber);
                })
                .setNegativeButton("取消", null)
                .create();
        alertDialog.show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        DdDeliveryDetailActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isFinishing()){
            if (timer != null) {
                timer.cancel();
                timer = null;
                System.gc();
            }
        }
    }

}