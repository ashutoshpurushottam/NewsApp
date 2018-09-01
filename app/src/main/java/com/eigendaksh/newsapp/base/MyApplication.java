package com.eigendaksh.newsapp.base;

import android.app.Application;

import com.eigendaksh.newsapp.BuildConfig;
import com.eigendaksh.newsapp.di.ActivityInjector;
import com.eigendaksh.newsapp.logging.MyDebugTree;

import javax.inject.Inject;

import timber.log.Timber;

public class MyApplication extends Application {

    private AppComponent appComponent;
    @Inject ActivityInjector activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        // Build the component (application level)
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        appComponent.inject(this);

        setUpLogging();

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
