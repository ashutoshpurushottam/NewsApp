package com.eigendaksh.newsapp.search.screens.searcharticles;

import com.eigendaksh.newsapp.di.ScreenScope;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface SearchArticlesControllerComponent extends AndroidInjector<SearchArticlesController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SearchArticlesController> {

        @BindsInstance
        public abstract void bindQueryString(@Named("query") String query);

        @BindsInstance
        public abstract void bindCategoriesString(@Named("categories") String categories);

        @Override
        public void seedInstance(SearchArticlesController instance) {
            bindQueryString(instance.getArgs().getString(SearchArticlesController.QUERY_KEY));
            bindCategoriesString(instance.getArgs().getString(SearchArticlesController.CATEGORIES_KEY));
        }
    }
}
