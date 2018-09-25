package com.eigendaksh.newsapp.model.search;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class SearchResponseWrapper {
    @Json(name = "docs")
    public abstract List<SearchDocument> searchDocumentList();

    public static JsonAdapter<SearchResponseWrapper> jsonAdapter(Moshi moshi) {
        return new AutoValue_SearchResponseWrapper.MoshiJsonAdapter(moshi);
    }

}
