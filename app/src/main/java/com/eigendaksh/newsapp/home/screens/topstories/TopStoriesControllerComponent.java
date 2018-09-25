package com.eigendaksh.newsapp.home.screens.topstories;

import com.eigendaksh.newsapp.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface TopStoriesControllerComponent extends AndroidInjector<TopStoriesController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TopStoriesController> {
        @Override
        public void seedInstance(TopStoriesController instance) {

        }
    }
}
