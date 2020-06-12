package com.t.httplib.yigou;

import com.trjx.tbase.http.HttpBase;

import retrofit2.Retrofit;

public class DemoHttpRetrofit extends HttpBase {

    private volatile static DemoHttpRetrofit mInstance;

    public DemoAPI httpAPI;

    private DemoHttpRetrofit() {
        super(1);
    }

    @Override
    public void init() {
        //本地服务器
//        baseUrl = "http://192.168.1.9:8080/egou.war.app/";
        //测试服务器
       baseUrl = "https://delivery.rlhys.com:8090/egou_android/";
        // 服务器
//        baseUrl = "https://delivery.rlhys.com:8444/fruits_android/";
    }

    @Override
    public void setAPI(Retrofit retrofit) {
        httpAPI = retrofit.create(DemoAPI.class);
    }

    /**
     *
     * 获取单例对象
     *
     * @return
     */
    public static DemoHttpRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (DemoHttpRetrofit.class) {
                if (mInstance == null){
                    mInstance = new DemoHttpRetrofit();
                }
            }
        }
        return mInstance;
    }

}