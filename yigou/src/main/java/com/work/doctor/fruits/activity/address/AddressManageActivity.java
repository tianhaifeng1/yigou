package com.work.doctor.fruits.activity.address;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.t.httplib.yigou.bean.resp.AddressInfoBean;
import com.trjx.tbase.module.recyclermodule.TRecyclerModule;
import com.trjx.tbase.module.recyclermodule.TRecyclerViewListenter;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.ToastUtil2;
import com.work.doctor.fruits.R;
import com.work.doctor.fruits.activity.adapter.AddressAdapter;
import com.work.doctor.fruits.assist.DemoConstant;
import com.work.doctor.fruits.base.DemoMVPActivity;

import java.util.List;

/**
 * 地址管理页面
 */
public class AddressManageActivity extends DemoMVPActivity<AddressView, AddressPresenter>
        implements TRecyclerViewListenter, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemLongClickListener, AddressView, BaseQuickAdapter.OnItemClickListener, View.OnClickListener {

    private TextView mAddressAdd;
    private RelativeLayout mAddressBottomRl;

    private TRecyclerModule recyclerModule;

    private AddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manage2);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("收货地址", true);

        mAddressAdd = findViewById(R.id.address_manage_add);
        mAddressBottomRl = findViewById(R.id.address_manage_bottom_rl);

        addressAdapter = new AddressAdapter(null);

        recyclerModule = new TRecyclerModule.Builder(context)
                .setLayoutManager(new LinearLayoutManager(context))
                .createAdapter(addressAdapter)
                .setTRecyclerViewListenter(this)
                .creat(rootView);
        recyclerModule.setRefreshing(true);
        recyclerModule.setDefImg(R.mipmap.default_orderlist);
        recyclerModule.setDefText("一个地址也没有");
        recyclerModule.getOView().setPadding(0, 0, 0, context.getResources().getDimensionPixelOffset(R.dimen.dp300));

        View view = LayoutInflater.from(context).inflate(R.layout.item_address_default_bottom_view, null);
        TextView textView1 = view.findViewById(R.id.address_manage_add_default);
        textView1.setOnClickListener(this);
        recyclerModule.addOtherView(view);

        recyclerModule.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                itemEventLayout(index, false);
            }
        });

        addressAdapter.setOnItemChildClickListener(this);

        mAddressAdd.setOnClickListener(this);
        addressAdapter.setOnItemClickListener(this);
        addressAdapter.setOnItemLongClickListener(this);

        getRecyclerListData();

    }

    @Override
    protected AddressPresenter initPersenter() {
        return new AddressPresenter(this);
    }

    private int position;

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        this.position = position;
        AddressInfoBean infoBean = (AddressInfoBean) adapter.getData().get(position);
        int ids = view.getId();
        switch (ids) {
            case R.id.item_address_editor:
                Logger.t("地址 ： 编辑");
                Intent intent = new Intent(context, AddressUpdateActivity.class);
                intent.putExtra("code", 1);
                intent.putExtra("info", ((AddressInfoBean) adapter.getData().get(position)));
                skipActivity(intent, 100);
                break;

            case R.id.item_address_event_copy:
                Logger.t("地址复制");
                // 剪切板管理器
                ClipboardManager cbm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                cbm.setText(infoBean.getProvince()
                        + infoBean.getCity()
                        + infoBean.getCounty()
                        + infoBean.getAddress()
                        + infoBean.getDetailAddress());
                ToastUtil2.showToast(context, "已复制到剪切板");
                index = -1;
                infoBean.setShowEventView(false);
                adapter.notifyItemChanged(position);
                break;

            case R.id.item_address_event_default:
                Logger.t("设置默认地址");
                index = -1;
                infoBean.setShowEventView(false);
                getPresenter().setDefaultAddress(infoBean.getId());
                break;

            case R.id.item_address_event_delete:
                Logger.t("删除地址");
                index = -1;
                infoBean.setShowEventView(false);
                getPresenter().deleteAddress(infoBean.getId());
                break;

            case R.id.item_address_event:
                index = -1;
                infoBean.setShowEventView(false);
                adapter.notifyItemChanged(position);
                break;
        }


    }

    @Override
    public void onClickRecyclerExceptionPageEvent() {

    }

    @Override
    public void getRecyclerListData() {
        if (DemoConstant.shopInfoBean == null) {
            Logger.t("店铺信息不能为空");
            return;
        }
        if (recyclerModule.getPage() == 1) {
            isDefaultIndex = -1;
        }
        getPresenter().getListDataAddress(DemoConstant.shopInfoBean.getShopId() + "", recyclerModule.getPage(), recyclerModule.getPageSize());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == 200) {
                //刷新数据
                recyclerModule.setRefreshing(true);
                recyclerModule.setPage(1);
                getRecyclerListData();
            }
        }
    }

    private int index = -1;

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        Logger.t("产时间点击" + position);

        itemEventLayout(position,true);
        itemEventLayout(index, false);
        index = position;

        return true;
    }

    private void itemEventLayout(int index,boolean isShow){
        if (index > -1) {
            AddressInfoBean infoBean2 = addressAdapter.getData().get(index);
            infoBean2.setShowEventView(isShow);
            addressAdapter.notifyItemChanged(index);
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (index > -1) {
            AddressInfoBean infoBean2 = (AddressInfoBean) adapter.getData().get(index);
            infoBean2.setShowEventView(false);
            adapter.notifyItemChanged(index);
        }
        index = -1;
    }

    private int isDefaultIndex = -1;

    @Override
    public void getListDataSuccess(List<AddressInfoBean> list) {
        if (recyclerModule.getPage() == 1 && (list == null || list.size() == 0)) {
            mAddressBottomRl.setVisibility(View.GONE);
        } else {
            mAddressBottomRl.setVisibility(View.VISIBLE);
        }
        recyclerModule.setRefreshing(false);
        recyclerModule.bindListData(list);

        //找出默认地址的位置
        if (isDefaultIndex == -1) {
            List<AddressInfoBean> infoBeanList = addressAdapter.getData();
            if (infoBeanList != null && infoBeanList.size() > 0) {
                for (int i = 0; i < infoBeanList.size(); i++) {
                    AddressInfoBean infoBean = infoBeanList.get(i);
                    if (infoBean.getIsDefault() == 1) {
                        isDefaultIndex = i;
                        return;
                    }
                }
            }
        }

    }

    @Override
    public void updateAddressSuccess(AddressInfoBean infoBean) {

    }

    @Override
    public void deleteAddressSuccess() {
        if (isDefaultIndex == position) {//删除默认地址列表项：将默认地址项参数设置为-1
            isDefaultIndex = -1;
        }
        recyclerModule.deleteListData(position);
        if (addressAdapter.getData() == null || addressAdapter.getData().size() == 0) {
            mAddressBottomRl.setVisibility(View.GONE);
        }
    }

    @Override
    public void setDefaultAddressSuccess() {

        AddressInfoBean infoBean = addressAdapter.getData().get(position);
        infoBean.setIsDefault(1);
        addressAdapter.notifyItemChanged(position);

        if (isDefaultIndex > -1) {
            AddressInfoBean infoBean2 = addressAdapter.getData().get(isDefaultIndex);
            infoBean2.setIsDefault(0);
            addressAdapter.notifyItemChanged(isDefaultIndex);
        }
        isDefaultIndex = position;
    }


    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.address_manage_add:
            case R.id.address_manage_add_default:
                Logger.t("地址 ： 新增");
                Intent intent = new Intent(context, AddressUpdateActivity.class);
                intent.putExtra("code", 0);
                skipActivity(intent, 100);
                break;
        }
    }

}