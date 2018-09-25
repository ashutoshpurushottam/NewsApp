package com.eigendaksh.newsapp.search;

import com.bluelinelabs.conductor.Controller;
import com.eigendaksh.newsapp.di.ControllerKey;
import com.eigendaksh.newsapp.search.screens.search.SearchScreenController;
import com.eigendaksh.newsapp.search.screens.search.SearchScreenControllerComponent;
import com.eigendaksh.newsapp.search.screens.searcharticles.SearchArticlesController;
import com.eigendaksh.newsapp.search.screens.searcharticles.SearchArticlesControllerComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        SearchScreenControllerComponent.class,
        SearchArticlesControllerComponent.class,
})
public abstract class SearchScreenBindingModule {
    @Binds
    @IntoMap
    @ControllerKey(SearchScreenController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindSearchScreenInjector
            (SearchScreenControllerComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(SearchArticlesController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindArticleScreensInjector
            (SearchArticlesControllerComponent.Builder builder);
}
