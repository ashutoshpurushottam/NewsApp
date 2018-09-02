package com.eigendaksh.newsapp.apiResponses;

import com.eigendaksh.newsapp.model.TopStory;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class TopStoriesApiResponse {
    @Json(name = "results")
    public abstract List<TopStory> topStories();

    public static JsonAdapter<TopStoriesApiResponse> jsonAdapter(Moshi moshi) {
        return new AutoValue_TopStoriesApiResponse.MoshiJsonAdapter(moshi);
    }
}
