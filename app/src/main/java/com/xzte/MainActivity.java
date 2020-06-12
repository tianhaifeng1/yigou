package com.xzte;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.trjx.tlibs.uils.GlideUtile;

public class MainActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image);
        String path = "http://rulongegou.oss-cn-beijing.aliyuncs.com/qrcode/0f28b5d49b3020afeecd95b4009adf4c_141.png";

        GlideUtile.bindImageView(this, path, image);



    }
}