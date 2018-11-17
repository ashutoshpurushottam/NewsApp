package com.eigendaksh.newsapp.networking;

import com.eigendaksh.newsapp.data.NewsService;
import com.eigendaksh.newsapp.model.AdapterFactory;
import com.eigendaksh.newsapp.model.ZonedDateTimeAdapter;
import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

@Module
public abstract class NetworkModule {

    private static final String BASE_URL = "http://api.nytimes.com/svc/";


    @Provides
    @Singleton
    static Moshi provideMoshi() {
        return new Moshi.Builder()
                .add(AdapterFactory.create())
                .add(new ZonedDateTimeAdapter())
                .build();
    }

    @Provides
    @Singleton
    static Call.Factory provideOkHttp() {
        return new OkHttpClient
                .Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Response response = chain.proceed(request);
                    if(response.code() != 200) {
                        Timber.e(response.message());
                    }

                    return response;
                }).build();
    }


    @Provides
    @Singleton
    static Retrofit provideRetrofit(Moshi moshi, Call.Factory factory) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .callFactory(factory)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    static NewsService provideNewsService(Retrofit retrofit) {
        return retrofit.create(NewsService.class);
    }

}
