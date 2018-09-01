package com.eigendaksh.newsapp.base;

import android.app.Activity;

import com.eigendaksh.newsapp.home.MainActivity;
import com.eigendaksh.newsapp.home.MainActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        MainActivityComponent.class,
})
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideMainActivityInjector
            (MainActivityComponent.Builder builder);
}
