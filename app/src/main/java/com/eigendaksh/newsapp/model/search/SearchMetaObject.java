package com.eigendaksh.newsapp.model.search;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import javax.annotation.Nullable;

@AutoValue
public abstract class SearchMetaObject {
    @Nullable
    public abstract Long hits();
    @Nullable
    public abstract Long offset();
    @Nullable
    public abstract Long time();

    public static JsonAdapter<SearchMetaObject> jsonAdapter(Moshi moshi) {
        return new AutoValue_SearchMetaObject.MoshiJsonAdapter(moshi);
    }
}
