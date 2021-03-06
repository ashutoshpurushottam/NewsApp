package com.eigendaksh.newsapp.data;

import com.eigendaksh.newsapp.model.AdapterFactory;
import com.eigendaksh.newsapp.model.ZonedDateTimeAdapter;
import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class NewsApi {

    private static final String BASE_URL = "http://api.nytimes.com/svc/";
    private static Retrofit retrofit;
    private static NewsService newsService;
    private static Moshi moshi;


    public static NewsService getInstance() {
        if(newsService != null) {
            return newsService;
        }
        if(retrofit == null) {
            initRetrofitAndMoshi();
        }

        newsService = retrofit.create(NewsService.class);

        return newsService;
    }

    private static void initRetrofitAndMoshi() {
        moshi = new Moshi.Builder()
                .add(AdapterFactory.create())
                .add(new ZonedDateTimeAdapter())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }
}
