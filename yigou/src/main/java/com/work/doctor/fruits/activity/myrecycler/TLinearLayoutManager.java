package com.work.doctor.fruits.activity.myrecycler;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;

public class TLinearLayoutManager extends LinearLayoutManager {

    private boolean canScroll = false;

    public TLinearLayoutManager(Context context) {
        super(context);
    }

    public TLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public TLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean canScrollVertically() {
        return canScroll&&super.canScrollVertically();
    }

    @Override
    public boolean canScrollHorizontally() {
        return canScroll&&super.canScrollHorizontally();
    }

    /**
     * 设置是否可以滑动
     * @param canScroll
     */
    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }

}
