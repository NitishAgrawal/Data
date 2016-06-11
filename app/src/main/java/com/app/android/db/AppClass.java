package com.app.android.db;

import android.app.Application;
import android.content.Context;

import com.app.android.db.dbhelper.DatabaseHandler;

public class AppClass extends Application {

    public static Context _instance;

    //public static DatabaseHandler dbHandlerObj;
    @Override
    public void onCreate() {
        super.onCreate();
        AppClass._instance = getApplicationContext();
        DatabaseHandler.initDB();
    }

    public static Context getAppContext() {
        return AppClass._instance;
    }

}
