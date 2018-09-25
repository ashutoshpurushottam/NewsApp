package com.eigendaksh.newsapp.home.screens.business;

import com.eigendaksh.newsapp.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface BusinessStoriesControllerComponent extends AndroidInjector<BusinessStoriesController>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BusinessStoriesController> {
        @Override
        public void seedInstance(BusinessStoriesController instance) {

        }
    }
}
