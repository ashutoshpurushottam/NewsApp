package com.eigendaksh.newsapp.base;

import android.app.Activity;

import com.eigendaksh.newsapp.article.TestWebViewActivityComponent;
import com.eigendaksh.newsapp.article.WebViewActivity;
import com.eigendaksh.newsapp.home.MainActivity;
import com.eigendaksh.newsapp.home.MainActivityComponent;
import com.eigendaksh.newsapp.home.TestMainActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;


@Module(subcomponents = {
        TestMainActivityComponent.class,
        TestWebViewActivityComponent.class,
})
public abstract class TestActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivityInjector
            (TestMainActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(WebViewActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindWebViewActivityInjector
            (TestWebViewActivityComponent.Builder builder);
}