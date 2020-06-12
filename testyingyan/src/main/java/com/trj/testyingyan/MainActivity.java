package com.trj.testyingyan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//    TestYyApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        app = (TestYyApp) getApplication();
//        app.applicationAssist.setScanSpan(1);
//        app.applicationAssist.setTMapInfoListener(this);
//        app.applicationAssist.startLoction();
    }

    //创建entity
    public void onClickCreate(View view) {
//        if (aMapLocation != null) {
            startActivity(new Intent(this, YingyActivity.class));
//        }else{
//            SnackbarUtil.showToast(findViewById(android.R.id.content),"定位中");
//
//        }
    }

    public void onClickLook(View view) {


    }
//
//    BDLocation aMapLocation;
//
//    @Override
//    public void bdSuccess(BDLocation aMapLocation) {
//        Logger.t("-----" + aMapLocation.getCity());
//        Logger.t("-----" + aMapLocation.getLatitude() + "--" + aMapLocation.getLongitude());
//        this.aMapLocation = aMapLocation;
//
//    }
//
//    @Override
//    public void bdFail(String failStr) {
//        Logger.t("-----" + failStr);
//        app.applicationAssist.startLoction();
//    }
}
