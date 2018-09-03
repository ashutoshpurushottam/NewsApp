package com.eigendaksh.newsapp.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class MediaMetaData {
    @Json(name = "url")
    public abstract String photoUrl();

    public static JsonAdapter<MediaMetaData> jsonAdapter(Moshi moshi) {
        return new AutoValue_MediaMetaData.MoshiJsonAdapter(moshi);
    }

}
