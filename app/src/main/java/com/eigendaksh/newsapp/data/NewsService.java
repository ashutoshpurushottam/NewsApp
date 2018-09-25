package com.eigendaksh.newsapp.data;

import com.eigendaksh.newsapp.apiResponses.PopularStoriesApiResponse;
import com.eigendaksh.newsapp.apiResponses.SearchApiResponse;
import com.eigendaksh.newsapp.apiResponses.StoriesApiResponse;

import dagger.Provides;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

// Base: http://api.nytimes.com/svc/
// Top Stories: http://api.nytimes.com/svc/topstories/v2/world.json?api-key=a27a66145d4542d28a719cecee6de859
// Most popular: http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=a27a66145d4542d28a719cecee6de859
// Business: http://api.nytimes.com/svc/topstories/v2/business.json?api-key=a27a66145d4542d28a719cecee6de859
// Sports: http://api.nytimes.com/svc/topstories/v2/sports.json?api-key=a27a66145d4542d28a719cecee6de859
// http://api.nytimes.com/svc/search/v2/articlesearch.json?q=trump&fq=politics+sports&begin_date=20180701&end_date=20180925&sort=newest&page=1&api-key=a27a66145d4542d28a719cecee6de859
public interface NewsService {

    @GET("topstories/v2/world.json?api-key=a27a66145d4542d28a719cecee6de859")
    Single<StoriesApiResponse> getTopStories();

    @GET("mostpopular/v2/mostviewed/all-sections/7.json?api-key=a27a66145d4542d28a719cecee6de859")
    Single<PopularStoriesApiResponse> getPopularStories();

    @GET("topstories/v2/business.json?api-key=a27a66145d4542d28a719cecee6de859")
    Single<StoriesApiResponse> getBusinessStories();

    @GET("topstories/v2/sports.json?api-key=a27a66145d4542d28a719cecee6de859")
    Single<StoriesApiResponse> getSportsStories();

    @GET("search/v2/articlesearch.json?&begin_date=20180701&end_date=20180925&sort=newest&page=1&api-key=a27a66145d4542d28a719cecee6de859")
    Single<SearchApiResponse> getSearchStories(@Query("q") String query, @Query("fq") String categories);

}
