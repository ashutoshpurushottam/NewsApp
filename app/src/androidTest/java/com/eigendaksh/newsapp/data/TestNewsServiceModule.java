package com.eigendaksh.newsapp.data;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TestNewsServiceModule {
    @Binds
    abstract NewsService bindNewsService(TestNewsService newsService);
}