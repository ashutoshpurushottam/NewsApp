package com.eigendaksh.newsapp.home;

import com.bluelinelabs.conductor.Controller;
import com.eigendaksh.newsapp.di.ControllerKey;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        MainActivityContainerControllerComponent.class,
})
public abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(MainActivityContainerController.class)
    abstract AndroidInjector.Factory<? extends Controller>
    bindMainActivityContainerControllerInjector(MainActivityContainerControllerComponent.Builder
                                                        builder);
}
