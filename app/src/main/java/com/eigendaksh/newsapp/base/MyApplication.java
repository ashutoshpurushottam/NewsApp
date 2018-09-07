package com.eigendaksh.newsapp.base;

import android.app.Application;

import com.eigendaksh.newsapp.BuildConfig;
import com.eigendaksh.newsapp.di.ActivityInjector;
import com.eigendaksh.newsapp.logging.MyDebugTree;

import javax.inject.Inject;

import timber.log.Timber;

public class MyApplication extends Application {

    @Inject ActivityInjector activityInjector;

    protected AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = initComponent();
        appComponent.inject(this);

        setUpLogging();
    }

    protected AppComponent initComponent() {
        // Build the component (application level)
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void setUpLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new MyDebugTree());
        }
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
