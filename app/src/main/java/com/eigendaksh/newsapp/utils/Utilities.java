package com.eigendaksh.newsapp.utils;

import com.eigendaksh.newsapp.model.TopStory;

import org.threeten.bp.format.DateTimeFormatter;


public class Utilities {

    public static String getDateFromStory(TopStory topStory) {
        return DateTimeFormatter.ofPattern("dd MMM").format(topStory.updatedDate());
    }

    public static String getImageUrl(TopStory topStory) {
        if(topStory.associatedImages().size() > 0) {
            return topStory.associatedImages().get(0).photoUrl();
        }

        return "";
    }
}
