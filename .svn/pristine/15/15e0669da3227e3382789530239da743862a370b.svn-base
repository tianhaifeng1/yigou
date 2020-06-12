package com.xzte.greendao;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.trjx.tbase.activity.InitTitleActivity;
import com.xzte.R;
import com.xzte.TestApplication;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class TestGreenDaoActivity extends InitTitleActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_green_dao);
    }

    private ShopInfoDao shopInfoDao;

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("测试GreenDao");

        textView = findViewById(R.id.message);

        DaoSession daoSession = ((TestApplication) getApplication()).getDaoSession();
        shopInfoDao = daoSession.getShopInfoDao();

    }

    public void onClickInsert(View view) {
        ShopInfo shopInfo = shopInfoDao.queryBuilder().where(ShopInfoDao.Properties.ShopId.eq("001")).unique();
        if (shopInfo == null) {
            ShopInfo info = new ShopInfo();
            info.setShopId("001");
            info.setShopName("儒龙好医生");
            info.setShopAddress("西藏拉萨市某某区某某街多少号");
            long mm = shopInfoDao.insert(info);

            textView.setText("插入" + (mm == 0 ? "成功" : "失败"));
        }else{
            textView.setText("此条数据已经存在");
        }

    }

    public void onClickLook(View view) {
        QueryBuilder<ShopInfo> queryBuilder = shopInfoDao.queryBuilder();
        List<ShopInfo> list = queryBuilder.list();
        textView.setText("查询" + list.toString());

    }
}
