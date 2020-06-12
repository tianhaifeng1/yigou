package com.xzte;


import com.trjx.tbase.InitApplication;
import com.xzte.greendao.DaoMaster;
import com.xzte.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

public class TestApplication extends InitApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "goodscart.db");
        Database db = helper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;
    public DaoSession getDaoSession() {
        return daoSession;
    }

}
