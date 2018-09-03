package com.eigendaksh.newsapp.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

import javax.annotation.Nullable;

@AutoValue
public abstract class Media {
    public abstract String type();
    public abstract String subtype();
    @Nullable
    public abstract String caption();
    @Nullable
    public abstract String copyright();
    @Nullable
    @Json(name = "media_metadata")
    public abstract List<MediaMetaData> metaDataList();

    public static JsonAdapter<Media> jsonAdapter(Moshi moshi) {
        return new AutoValue_Media.MoshiJsonAdapter(moshi);
    }


}
