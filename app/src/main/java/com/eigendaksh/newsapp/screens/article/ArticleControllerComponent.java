package com.eigendaksh.newsapp.screens.article;

import com.eigendaksh.newsapp.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface ArticleControllerComponent extends AndroidInjector<ArticleController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ArticleController> {

        @Override
        public void seedInstance(ArticleController instance) {

        }
    }
}
