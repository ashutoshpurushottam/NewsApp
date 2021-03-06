package com.eigendaksh.newsapp.utils;

import com.eigendaksh.newsapp.model.others.Story;
import com.eigendaksh.newsapp.model.popular.PopularStory;
import com.eigendaksh.newsapp.model.search.SearchDocument;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.Objects;

import timber.log.Timber;


public class Utilities {

    public static String getDateFromStory(Story story) {
        return DateTimeFormatter.ofPattern("dd MMM").format(story.updatedDate());
    }

    public static String getDateFromSearchStory(SearchDocument searchDocument) {
        return searchDocument.publishedDate();
    }

    public static String getDateFromSearchDocument(SearchDocument searchDocument) {
        try {
            return searchDocument.publishedDate().split("T")[0];
        } catch (Exception e) {
            return "NA";
        }
    }

    public static String getSectionFromDocument(SearchDocument searchDocument) {
        try {
            return searchDocument.sectionName();
        } catch (Exception e) {
            return "NA";
        }
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

    public static String getImageFromUrlFromSearchDocument(SearchDocument searchDocument) {
        try {
            return searchDocument.multimediaObjectList().get(0).imageUrl();
        } catch (NullPointerException e) {
            Timber.d(e.getLocalizedMessage());
            return "";
        }

    }

}
