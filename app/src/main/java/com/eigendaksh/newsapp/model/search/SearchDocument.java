package com.eigendaksh.newsapp.model.search;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

import javax.annotation.Nullable;


@AutoValue
public abstract class SearchDocument {

    @Json(name = "_id")
    public abstract String docId();
    @Json(name = "web_url")
    public abstract String webUrl();
    @Json(name = "multimedia")
    public abstract List<SearchMultimediaObject> multimediaObjectList();
    @Json(name = "headline")
    public abstract SearchHeadline searchHeadline();
    @Nullable
    @Json(name = "pub_date")
    public abstract String publishedDate();
    @Nullable
    @Json(name = "section_name")
    public abstract String sectionName();

    public static JsonAdapter<SearchDocument> jsonAdapter(Moshi moshi) {
        return new AutoValue_SearchDocument.MoshiJsonAdapter(moshi);
    }
}
