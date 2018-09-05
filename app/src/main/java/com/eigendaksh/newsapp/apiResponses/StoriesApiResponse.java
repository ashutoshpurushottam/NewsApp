package com.eigendaksh.newsapp.apiResponses;

import com.eigendaksh.newsapp.model.others.Story;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class StoriesApiResponse {
    @Json(name = "results")
    public abstract List<Story> stories();

    public static JsonAdapter<StoriesApiResponse> jsonAdapter(Moshi moshi) {
        return new AutoValue_StoriesApiResponse.MoshiJsonAdapter(moshi);
    }
}
