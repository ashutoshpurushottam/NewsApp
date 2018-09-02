package com.eigendaksh.newsapp.base;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application app) {
        this.application = app;
    }

    @Provides
    Context provideApplicationContext() {
        return application;
    }

}
