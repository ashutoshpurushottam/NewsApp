package com.eigendaksh.newsapp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.eigendaksh.newsapp.home.businessnews.BusinessNewsViewModel;
import com.eigendaksh.newsapp.home.sportsnews.SportsNewsViewModel;
import com.eigendaksh.newsapp.home.trendingnews.TrendingNewsViewModel;
import com.eigendaksh.newsapp.home.worldnews.WorldNewsViewModel;
import com.eigendaksh.newsapp.searchresults.SearchResultsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BusinessNewsViewModel.class)
    abstract ViewModel bindBusinessNewsViewModel(BusinessNewsViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SportsNewsViewModel.class)
    abstract ViewModel bindSportsNewsViewModel(SportsNewsViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TrendingNewsViewModel.class)
    abstract ViewModel bindTrendingNewsViewModel(TrendingNewsViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WorldNewsViewModel.class)
    abstract ViewModel bindWorldNewsViewModel(WorldNewsViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SearchResultsViewModel.class)
    abstract ViewModel bindSearchResultsViewModel(SearchResultsViewModel viewModel);


}
