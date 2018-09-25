package com.eigendaksh.newsapp.home.screens.popular;

import com.eigendaksh.newsapp.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface PopularStoriesControllerComponent extends AndroidInjector<PopularStoriesController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PopularStoriesController> {
        @Override
        public void seedInstance(PopularStoriesController instance) {

        }
    }
}
