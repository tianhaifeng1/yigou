package com.work.doctor.fruits.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.bean.DemoRespBean;
import com.trjx.tbase.activity.InitTitleActivity;
import com.trjx.tbase.http.HttpBase;
import com.trjx.tlibs.uils.GlideUtile;
import com.trjx.tlibs.uils.Logger;
import com.work.doctor.fruits.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ShareFaceToFacePageActivity extends InitTitleActivity {
    private ImageView ewm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_face_to_face_page);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("面对面邀请", true);
        ewm = findViewById(R.id.ewm);

        new DemoModel().requestUserCreatWxaqrcode(new Observer<DemoRespBean<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DemoRespBean<String> ewmInfoBeanDemoRespBean) {
                try {
                    int state = ewmInfoBeanDemoRespBean.getResultState();
                    if (state == HttpBase.POST_SUCCESS) {
                        String infoBean = ewmInfoBeanDemoRespBean.getData();
                        if (infoBean != null) {
                            GlideUtile.bindImageView(context, infoBean, ewm);
                        }
                    } else {
                        tRemind(ewmInfoBeanDemoRespBean.getRemindMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                Logger.t("获取失败：" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }

}
