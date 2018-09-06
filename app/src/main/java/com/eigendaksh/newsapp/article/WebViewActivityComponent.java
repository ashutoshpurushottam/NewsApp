package com.eigendaksh.newsapp.article;

import com.eigendaksh.newsapp.di.ActivityScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = {
        WebViewScreenBindingModule.class,
})
public interface WebViewActivityComponent extends AndroidInjector<WebViewActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<WebViewActivity> {
        @Override
        public void seedInstance(WebViewActivity instance) {

        }
    }

}
