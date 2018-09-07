package com.eigendaksh.newsapp.base;


import android.support.test.InstrumentationRegistry;

public class TestApplication extends MyApplication {

    @Override
    protected AppComponent initComponent() {
        return DaggerTestAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static TestAppComponent getComponent() {
        return ((TestAppComponent) ((TestApplication) InstrumentationRegistry.getTargetContext
                ().getApplicationContext()).appComponent);
    }
}