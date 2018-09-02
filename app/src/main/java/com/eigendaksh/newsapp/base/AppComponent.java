package com.eigendaksh.newsapp.base;

import com.eigendaksh.newsapp.data.NewsServiceModule;
import com.eigendaksh.newsapp.networking.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        NewsServiceModule.class,

})
public interface AppComponent {
    void inject(MyApplication myApplication);
}
