package com.xzte;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.trjx.tlibs.uils.Logger;

public class MyScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {

    public MyScrollingViewBehavior() {
    }

    public MyScrollingViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Logger.t("----------in");
        boolean b = super.onDependentViewChanged(parent, child, dependency);

//        androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior behavior = ((androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams)dependency.getLayoutParams()).getBehavior();
//
//        dependency.getBottom() - child.getTop();
//        if (behavior instanceof AppBarLayout.BaseBehavior) {
//            AppBarLayout.BaseBehavior ablBehavior = (AppBarLayout.BaseBehavior)behavior;
//            ViewCompat.offsetTopAndBottom(child, dependency.getBottom() - child.getTop() + ablBehavior.offsetDelta + this.getVerticalLayoutGap() - this.getOverlapPixelsForOffset(dependency));
//        }



        if (dependency instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout)dependency;
            View view = appBarLayout.getChildAt(0);

            if(view instanceof CollapsingToolbarLayout){
                View appbarView = ((CollapsingToolbarLayout) view).getChildAt(1);
                if(appbarView instanceof Toolbar){
                    int bfb = (int) (Math.abs(dependency.getY() / appBarLayout.getTotalScrollRange()) * 255);
                    appbarView.setBackgroundColor(Color.argb(bfb, 10, 100, 100));
                }
            }


//            if(view instanceof CollapsingToolbarLayout){
//                Logger.t("-------------in_in");
//                Logger.t("totalScroll = " + appBarLayout.getTotalScrollRange());
//                Logger.t("getY = " + dependency.getY());
//                int bfb = (int) (Math.abs(dependency.getY() / appBarLayout.getTotalScrollRange()) * 255);
//                Logger.t("bfb = " + bfb);
////                ((CollapsingToolbarLayout) view).setContentScrimColor(Color.argb(bfb, 100, 200, 100));
//                View view2 = parent.getChildAt(1);
//                if (view2 instanceof RecyclerView) {
//                    view2.setBackgroundColor(Color.argb(bfb, 100, 200, 100));
//                }
//            }
//            Logger.t("leftOnScroll = "+ appBarLayout.isLiftOnScroll());
//            Logger.t("totalScroll = " + appBarLayout.getTotalScrollRange());
//            Logger.t("getTop = " + child.getTop());
//            Logger.t("getBottom = " + dependency.getBottom());
//            Logger.t("getY = " + dependency.getY());
//
//            if (appBarLayout.isLiftOnScroll()) {
//                float ff = child.getY();
//                int height = child.getHeight();
//                Logger.t("----------ff = " + ff);
//                Logger.t("----------height = " + height);
//            }
        }
        return b;
    }
}
