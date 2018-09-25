package com.eigendaksh.newsapp.search.screens.search;

import com.eigendaksh.newsapp.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface SearchScreenControllerComponent extends AndroidInjector<SearchScreenController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SearchScreenController> {
        @Override
        public void seedInstance(SearchScreenController instance) {

        }
    }
}
