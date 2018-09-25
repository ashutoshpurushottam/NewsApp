package com.eigendaksh.newsapp.home;

import com.bluelinelabs.conductor.Controller;
import com.eigendaksh.newsapp.di.ControllerKey;
import com.eigendaksh.newsapp.home.screens.business.BusinessStoriesController;
import com.eigendaksh.newsapp.home.screens.business.BusinessStoriesControllerComponent;
import com.eigendaksh.newsapp.home.screens.popular.PopularStoriesController;
import com.eigendaksh.newsapp.home.screens.popular.PopularStoriesControllerComponent;
import com.eigendaksh.newsapp.home.screens.sports.SportsStoriesController;
import com.eigendaksh.newsapp.home.screens.sports.SportsStoriesControllerComponent;
import com.eigendaksh.newsapp.home.screens.topstories.TopStoriesController;
import com.eigendaksh.newsapp.home.screens.topstories.TopStoriesControllerComponent;

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
public abstract class MainScreenBindingModule {

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
