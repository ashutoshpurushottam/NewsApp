package com.eigendaksh.newsapp.search;

import com.eigendaksh.newsapp.di.ActivityScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = {
        SearchScreenBindingModule.class,
})
public interface SearchActivityComponent extends AndroidInjector<SearchActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SearchActivity> {
        @Override
        public void seedInstance(SearchActivity instance) {

        }
    }
}
