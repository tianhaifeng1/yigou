package com.t.httplib.suda;

import com.trjx.tbase.http.HttpBase;

import retrofit2.Retrofit;

public class SdHttpRetrofit extends HttpBase {

    private volatile static SdHttpRetrofit mInstance;

    public SdAPI httpAPI;

    private SdHttpRetrofit() {
        super(1);
    }

    @Override
    public void init() {
        //本地服务器
//        baseUrl = "http://192.168.1.4:8080/egou.war.app/";
        //测试服务器
        baseUrl = "http://delivery.rlhys.com:8090/egou_android/";
        // 服务器
//        baseUrl = "https://delivery.rlhys.com:8444/fruits_android/";

    }

    @Override
    public void setAPI(Retrofit retrofit) {
        httpAPI = retrofit.create(SdAPI.class);
    }

    /**
     *
     * 获取单例对象
     *
     * @return
     */
    public static SdHttpRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (SdHttpRetrofit.class) {
                if (mInstance == null){
                    mInstance = new SdHttpRetrofit();
                }
            }
        }
        return mInstance;
    }

}
