package com.xzte.test2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.xzte.R;

public class TestToBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_to_back);
    }

    public void onClickBtn(View view) {
        setResult(200);
        finish();
    }
}
