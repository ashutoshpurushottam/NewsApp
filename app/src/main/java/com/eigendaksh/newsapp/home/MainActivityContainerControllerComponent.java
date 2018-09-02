package com.eigendaksh.newsapp.home;

import com.eigendaksh.newsapp.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface MainActivityContainerControllerComponent extends
        AndroidInjector<MainActivityContainerController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivityContainerController> {

        @Override
        public void seedInstance(MainActivityContainerController instance) {

        }
    }
}
