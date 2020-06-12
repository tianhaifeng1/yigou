package com.xzte;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.trjx.tbase.activity.InitTitleActivity;
import com.trjx.tbase.module.titlemodule.TitleModule;
import com.trjx.tlibs.uils.Logger;
import com.trjx.tlibs.uils.TUtils;

public class TextActivity extends InitTitleActivity {

    private EditText mMapSearchContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("测试");
        titleModule.initTitleMenu(TitleModule.MENU_TEXT,"");
//        mMapSearchContent = findViewById(R.id.map_search_content);
//        mMapSearchContent.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    mMapSearchContent.setCursorVisible(true);
//                    TUtils.showSystemKeyboard(context,view);
//                    titleModule.setMenuText("取消");
//                }
//                return false;
//            }
//        });

    }

    @Override
    public void onClickRightText(View view) {
        super.onClickRightText(view);
        Logger.t("-----------");
        TUtils.hideSystemKeyboard(context,view);
//        mMapSearchContent.setCursorVisible(false);
        titleModule.setMenuText("");
    }
}
