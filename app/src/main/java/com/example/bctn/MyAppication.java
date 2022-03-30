package com.example.bctn;

import android.app.Application;

public class MyAppication extends Application {

    public static DAO mDao;
    @Override
    public void onCreate() {
        super.onCreate();

        mDao = new DAO(getApplicationContext());
        DataLocalManager.init(getApplicationContext());
    }
}
