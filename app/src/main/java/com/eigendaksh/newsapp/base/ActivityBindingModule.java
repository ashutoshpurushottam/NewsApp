package com.eigendaksh.newsapp.base;

import android.app.Activity;

import com.eigendaksh.newsapp.article.WebViewActivity;
import com.eigendaksh.newsapp.article.WebViewActivityComponent;
import com.eigendaksh.newsapp.home.MainActivity;
import com.eigendaksh.newsapp.home.MainActivityComponent;
import com.eigendaksh.newsapp.search.SearchActivity;
import com.eigendaksh.newsapp.search.SearchActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        MainActivityComponent.class,
        WebViewActivityComponent.class,
        SearchActivityComponent.class,
})
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideMainActivityInjector
            (MainActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(WebViewActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideWebViewActivityInjector
            (WebViewActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(SearchActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideSearchActivityInjector
            (SearchActivityComponent.Builder builder);


}
