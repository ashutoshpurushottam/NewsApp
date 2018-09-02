package com.eigendaksh.newsapp.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class NewsServiceModule {

    @Provides
    @Singleton
    static NewsService provideRepoService(Retrofit retrofit) {
        return retrofit.create(NewsService.class);
    }

}
