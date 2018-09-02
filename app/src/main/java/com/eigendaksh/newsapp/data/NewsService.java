package com.eigendaksh.newsapp.data;

import com.eigendaksh.newsapp.apiResponses.TopStoriesApiResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

// Top Stories: http://api.nytimes.com/svc/topstories/v2/world.json?api-key=a27a66145d4542d28a719cecee6de859

public interface NewsService {

    @GET("topstories/v2/world.json?api-key=a27a66145d4542d28a719cecee6de859")
    Single<TopStoriesApiResponse> getTopStories();

}
