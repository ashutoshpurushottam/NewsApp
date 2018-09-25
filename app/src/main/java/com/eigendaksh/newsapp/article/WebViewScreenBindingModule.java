package com.eigendaksh.newsapp.article;

import com.bluelinelabs.conductor.Controller;
import com.eigendaksh.newsapp.di.ControllerKey;
import com.eigendaksh.newsapp.home.screens.article.ArticleController;
import com.eigendaksh.newsapp.home.screens.article.ArticleControllerComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        ArticleControllerComponent.class,
})
public abstract class WebViewScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(ArticleController.class)
    abstract AndroidInjector.Factory<? extends Controller>
    bindArticleControllerInjector(ArticleControllerComponent.Builder builder);
}
