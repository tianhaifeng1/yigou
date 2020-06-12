package com.xzte.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.trjx.tlibs.uils.Logger;

public class MylifeCycleObserver implements LifecycleObserver {

    public MylifeCycleObserver() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onActivityResume(){
        Logger.t("-------onActivityResume----");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onActivityPause(){
        Logger.t("-------onActivityPause----");
    }

}
