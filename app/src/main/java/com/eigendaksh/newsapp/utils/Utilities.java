package com.eigendaksh.newsapp.utils;

import com.eigendaksh.newsapp.model.popular.PopularStory;
import com.eigendaksh.newsapp.model.others.Story;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.Objects;

import timber.log.Timber;


public class Utilities {

    public static String getDateFromStory(Story story) {
        return DateTimeFormatter.ofPattern("dd MMM").format(story.updatedDate());
    }


    public static String getDateFromPopularStory(PopularStory popularStory) {
        return popularStory.publishedAt();
    }

    public static String getImageUrl(Story story) {
        if (story.associatedImages().size() > 0) {
            return story.associatedImages().get(0).photoUrl();
        }

        return "";
    }

    public static String getImageUrlFromPopularStory(PopularStory popularStory) {
        try {
            Timber.d(popularStory.associatedMediaList().get(0).toString());
            return Objects.requireNonNull(popularStory.associatedMediaList()).get(0).metaDataList().get(0).photoUrl();
        } catch (NullPointerException e) {
            Timber.d(e.getLocalizedMessage());
            return "";
        }
    }

}
