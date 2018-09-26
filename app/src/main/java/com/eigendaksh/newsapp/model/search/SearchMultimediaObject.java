package com.eigendaksh.newsapp.model.search;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class SearchMultimediaObject {
    @Json(name = "url")
    public abstract String imageUrl();

    public static JsonAdapter<SearchMultimediaObject> jsonAdapter(Moshi moshi) {
        return new AutoValue_SearchMultimediaObject.MoshiJsonAdapter(moshi);
    }
}
