package com.eigendaksh.newsapp.model;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import org.threeten.bp.ZonedDateTime;

import io.reactivex.annotations.NonNull;

public class ZonedDateTimeAdapter {

    @FromJson
    ZonedDateTime fromJson(String json) {
        return ZonedDateTime.parse(json);
    }

    @ToJson
    String toJson(@NonNull ZonedDateTime value) {
        return value != null ? value.toString() : null;
    }

}
