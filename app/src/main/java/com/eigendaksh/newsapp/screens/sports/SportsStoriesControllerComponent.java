package com.eigendaksh.newsapp.screens.sports;

import com.eigendaksh.newsapp.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface SportsStoriesControllerComponent extends AndroidInjector<SportsStoriesController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SportsStoriesController> {
        @Override
        public void seedInstance(SportsStoriesController instance) {

        }
    }

}
