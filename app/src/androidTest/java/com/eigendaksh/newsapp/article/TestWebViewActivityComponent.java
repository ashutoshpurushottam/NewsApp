package com.eigendaksh.newsapp.article;

import com.eigendaksh.newsapp.di.ActivityScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

import static org.junit.Assert.*;

@ActivityScope
@Subcomponent(modules = {
    TestWebViewScreenBindingModule.class,
})
public interface TestWebViewActivityComponent extends AndroidInjector<WebViewActivity>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<WebViewActivity> {

    }

}