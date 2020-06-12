package com.work.doctor.fruits.activity.vip;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqRuleInfo;
import com.t.httplib.yigou.bean.resp.RuleInfoBean;
import com.trjx.tbase.activity.InitTitleActivity;
import com.trjx.tbase.http.HttpBase;
import com.work.doctor.fruits.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyEarningGzActivity extends InitTitleActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_earning_gz);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("提现协议", true);

        textView = findViewById(R.id.amegz_textview);

        ReqRuleInfo info = new ReqRuleInfo();
        info.setCatype("TXGZ");
        new DemoModel().requestRule(info, new Observer<DemoRespBean<RuleInfoBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DemoRespBean<RuleInfoBean> bean) {
                if (bean.getResultState() == HttpBase.POST_SUCCESS) {
                    try {
                        //加载网页内容
                        textView.setText(Html.fromHtml(bean.getData().getSingleContent()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                tPostError(e.getMessage());
            }

            @Override
            public void onComplete() {
                tPostFinish(-1);
            }
        });


    }
}
