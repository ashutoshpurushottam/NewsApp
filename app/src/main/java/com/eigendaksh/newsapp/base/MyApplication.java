package com.eigendaksh.newsapp.base;

import android.app.Application;
import android.content.Context;


public class MyApplication extends Application {


    protected AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }

    public static AppComponent getAppComponent(Context context) {
        return ((MyApplication) context.getApplicationContext()).appComponent;
    }


}
