package com.eigendaksh.newsapp.networking;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

@Module
public abstract class NetworkModule {

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
    @Named("nytimes_base_url")
    static String provideBaseUrl() {
        return " http://api.nytimes.com/svc/";
    }


}
