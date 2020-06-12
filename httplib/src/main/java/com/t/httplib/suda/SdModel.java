package com.t.httplib.suda;

import com.google.gson.Gson;
import com.trjx.tbase.mvp.TModel;

public class SdModel extends TModel {

    private SdHttpRetrofit httpRetrofit;

    private Gson gson;

    public SdModel() {
        httpRetrofit = SdHttpRetrofit.getInstance();
        gson = new Gson();
    }



}
