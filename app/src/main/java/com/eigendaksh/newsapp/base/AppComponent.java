package com.eigendaksh.newsapp.base;

import com.eigendaksh.newsapp.home.businessnews.BusinessNewsFragment;
import com.eigendaksh.newsapp.home.sportsnews.SportsNewsFragment;
import com.eigendaksh.newsapp.home.trendingnews.TrendingNewsFragment;
import com.eigendaksh.newsapp.home.worldnews.WorldNewsFragment;
import com.eigendaksh.newsapp.networking.NetworkModule;
import com.eigendaksh.newsapp.searchresults.SearchResultsActivity;
import com.eigendaksh.newsapp.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        NetworkModule.class,
        ViewModelModule.class,
})
public interface AppComponent {
    void inject(SearchResultsActivity searchResultsActivity);

    void inject(WorldNewsFragment worldNewsFragment);

    void inject(TrendingNewsFragment trendingNewsFragment);

    void inject(SportsNewsFragment sportsNewsFragment);

    void inject(BusinessNewsFragment businessNewsFragment);
}
