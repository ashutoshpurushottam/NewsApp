package com.eigendaksh.newsapp.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

import javax.annotation.Nullable;

@AutoValue
public abstract class PopularStory {
    public abstract long id();
    public abstract String section();
    public abstract String title();
    @Json(name="url")
    public abstract String articleUrl();
    public abstract String type();
    @Nullable
    @Json(name = "media")
    public abstract List<Media> associatedMediaList();

    public static JsonAdapter<PopularStory> jsonAdapter(Moshi moshi) {
        return new AutoValue_PopularStory.MoshiJsonAdapter(moshi);
    }


}
