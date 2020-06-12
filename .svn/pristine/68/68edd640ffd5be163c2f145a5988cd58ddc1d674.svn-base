package com.work.doctor.fruits.base;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.JsPromptResult;

import com.t.httplib.yigou.DemoModel;
import com.t.httplib.yigou.bean.DemoRespBean;
import com.t.httplib.yigou.bean.req.ReqRuleInfo;
import com.t.httplib.yigou.bean.resp.RuleInfoBean;
import com.trjx.tbase.activity.TWeb2Activity;
import com.trjx.tbase.http.HttpBase;
import com.work.doctor.fruits.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * code 区别码
 * <p>
 * JFGZ:积分规则
 * TXGZ:提现协议 （4）
 * YHXY:用户协议 （3）
 * GYWM:关于我们 （2）
 * CZGZ:会员充值规则 （1）
 * YQGZ:邀请活动规则
 * 其它：（0）；path：显示路径
 * <p>
 *
 */
public class DemoWebActivity extends TWeb2Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("", true);

        int code = getIntent().getIntExtra("code", -1);

        if(code == 0){
            String path = getIntent().getStringExtra("path");
            String title = getIntent().getStringExtra("title");
            titleModule.setTitleText(title == null ? "" : title);
            initHtmlPath(path, "");
            return;
        }

        String catype = "";
        String titleStr = "";
        switch (code) {
            case 1:
                titleStr = "会员充值规则";
                catype = "CZGZ";
                break;
            case 2:
                titleStr = "关于我们";
                catype = "GYWM";
                break;
            case 3:
                titleStr = "用户协议";
                catype = "YHXY";
                break;
            case 4:
                titleStr = "提现协议";
                catype = "TXGZ";
                break;
        }

        titleModule.setTitleText(titleStr);
        if (catype.equals("")) {
            return;
        }
        ReqRuleInfo info = new ReqRuleInfo();
        info.setCatype(catype);
        new DemoModel().requestRule(info, new Observer<DemoRespBean<RuleInfoBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DemoRespBean<RuleInfoBean> bean) {
                if (bean.getResultState() == HttpBase.POST_SUCCESS) {
                    try {
                        //加载网页内容


                        if(code == 4){
                            String content = bean.getData().getSingleContent();
                            String url = "https://rulongegou.oss-cn-beijing.aliyuncs.com/bank/bank_"+1001+".png";
                            content = "<img src=\""+url+"\"></img>" + content;
                            initHtmlContent(content);
                        }else{
                            initHtmlContent(bean.getData().getSingleContent());
                        }

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

    @Override
    public void getMethodName(String methodName, Uri uri, JsPromptResult result) {

    }
}
