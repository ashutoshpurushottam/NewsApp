package com.eigendaksh.newsapp.apiResponses;

import com.eigendaksh.newsapp.model.popular.PopularStory;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class PopularStoriesApiResponse {

    @Json(name = "results")
    public abstract List<PopularStory> popularStories();

    public static JsonAdapter<PopularStoriesApiResponse> jsonAdapter(Moshi moshi) {
        return new AutoValue_PopularStoriesApiResponse.MoshiJsonAdapter(moshi);
    }

}
