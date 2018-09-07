package com.eigendaksh.newsapp.home;

import com.bluelinelabs.conductor.Controller;
import com.eigendaksh.newsapp.di.ControllerKey;
import com.eigendaksh.newsapp.screens.business.BusinessStoriesController;
import com.eigendaksh.newsapp.screens.business.BusinessStoriesControllerComponent;
import com.eigendaksh.newsapp.screens.popular.PopularStoriesController;
import com.eigendaksh.newsapp.screens.popular.PopularStoriesControllerComponent;
import com.eigendaksh.newsapp.screens.sports.SportsStoriesController;
import com.eigendaksh.newsapp.screens.sports.SportsStoriesControllerComponent;
import com.eigendaksh.newsapp.screens.topstories.TopStoriesController;
import com.eigendaksh.newsapp.screens.topstories.TopStoriesControllerComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        MainActivityContainerControllerComponent.class,
        BusinessStoriesControllerComponent.class,
        PopularStoriesControllerComponent.class,
        SportsStoriesControllerComponent.class,
        TopStoriesControllerComponent.class,
})
public abstract class TestMainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(MainActivityContainerController.class)
    abstract AndroidInjector.Factory<? extends Controller>
    bindMainActivityContainerInjector(MainActivityContainerControllerComponent.Builder
                                              builder);

    @Binds
    @IntoMap
    @ControllerKey(BusinessStoriesController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindBusinessStoriesInjector
            (BusinessStoriesControllerComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(PopularStoriesController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindsPopularStoriesInjector
            (PopularStoriesControllerComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(SportsStoriesController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindsSportsStoriesInjector
            (SportsStoriesControllerComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(TopStoriesController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindsTopStoriesInjector
            (TopStoriesControllerComponent.Builder builder);


}