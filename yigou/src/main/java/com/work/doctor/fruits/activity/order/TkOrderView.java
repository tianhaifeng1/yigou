package com.work.doctor.fruits.activity.order;

import com.t.httplib.yigou.bean.resp.TkOrderInfoBean;
import com.trjx.tbase.mvp.TView;

import java.util.List;

public interface TkOrderView extends TView {

    void getTkListDataSuccess(List<TkOrderInfoBean> list);

}
