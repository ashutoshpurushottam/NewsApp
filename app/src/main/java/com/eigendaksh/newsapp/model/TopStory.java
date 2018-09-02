package com.eigendaksh.newsapp.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.threeten.bp.ZonedDateTime;

import java.util.List;

@AutoValue
public abstract class TopStory {

    @Json(name="url")
    public abstract String articleUrl();
    public abstract String section();
    public abstract String subsection();
    public abstract String title();
    @Json(name = "created_date")
    public abstract ZonedDateTime createdDate();
    @Json(name = "updated_date")
    public abstract ZonedDateTime updatedDate();
    @Json(name = "multimedia")
    public abstract List<Multimedia> associatedImages();

    public static JsonAdapter<TopStory> jsonAdapter(Moshi moshi) {
        return new AutoValue_TopStory.MoshiJsonAdapter(moshi);
    }

}
