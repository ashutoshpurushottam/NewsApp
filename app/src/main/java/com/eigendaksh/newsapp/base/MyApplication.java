package com.eigendaksh.newsapp.base;

import android.app.Application;


public class MyApplication extends Application {


    protected AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = initComponent();
        appComponent.inject(this);
    }

    protected AppComponent initComponent() {
        // Build the component (application level)
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


}
