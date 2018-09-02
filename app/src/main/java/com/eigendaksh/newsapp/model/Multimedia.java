package com.eigendaksh.newsapp.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Multimedia {
    @Json(name = "url")
    public abstract String photoUrl();
    public abstract String caption();
    public abstract String copyright();

    public static JsonAdapter<Multimedia> jsonAdapter(Moshi moshi) {
        return new AutoValue_Multimedia.MoshiJsonAdapter(moshi);
    }



}
