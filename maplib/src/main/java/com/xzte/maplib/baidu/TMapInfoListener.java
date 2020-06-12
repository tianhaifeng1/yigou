package com.xzte.maplib.baidu;

import com.baidu.location.BDLocation;

/**
 * Created by Administrator on 2017/10/17.
 */

public interface TMapInfoListener {

    void bdSuccess(BDLocation aMapLocation);
    void bdFail(String failStr);

}
