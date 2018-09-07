package com.eigendaksh.newsapp.base;


import com.eigendaksh.newsapp.data.TestNewsServiceModule;
import com.eigendaksh.newsapp.networking.ServiceModule;
import com.eigendaksh.newsapp.screens.topstories.TopStoriesControllerTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        TestActivityBindingModule.class,
        TestNewsServiceModule.class,
        ServiceModule.class,
})
public interface TestAppComponent extends AppComponent {

    void inject(TopStoriesControllerTest topStoriesControllerTest);
}