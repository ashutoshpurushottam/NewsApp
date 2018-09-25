package com.eigendaksh.newsapp.apiResponses;


import com.eigendaksh.newsapp.model.search.SearchResponseWrapper;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class SearchApiResponse {

    @Json(name = "response")
    public abstract SearchResponseWrapper searchResponseWrapper();

    public static JsonAdapter<SearchApiResponse> jsonAdapter(Moshi moshi) {
        return new AutoValue_SearchApiResponse.MoshiJsonAdapter(moshi);
    }
}
