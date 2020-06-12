package com.xzte.lifecycle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.trjx.tlibs.uils.Logger;
import com.xzte.R;

public class TestLifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lifecycle);
//        Activity 于 LifecycleObserver 建立联系
        getLifecycle().addObserver(new MylifeCycleObserver());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.t("------onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.t("------onPause");
    }
}
