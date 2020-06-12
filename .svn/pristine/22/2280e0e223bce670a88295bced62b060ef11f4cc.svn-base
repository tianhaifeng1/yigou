package com.t.databaselib;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

public class DatabaseApplicationAssist {

    private Context applicationContext;

    public DatabaseApplicationAssist(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    public void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(applicationContext, "goodscart.db");
        Database db = helper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;
    public DaoSession getDaoSession() {
        return daoSession;
    }

}
