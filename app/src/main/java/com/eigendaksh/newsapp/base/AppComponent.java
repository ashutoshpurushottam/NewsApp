package com.eigendaksh.newsapp.base;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        ActivityBindingModule.class,
})
public interface AppComponent {
    void inject(MyApplication myApplication);
}
