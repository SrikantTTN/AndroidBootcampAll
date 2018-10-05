package com.example.srikant.day2;

import com.google.gson.annotations.SerializedName;

public class NewsModel {
        @SerializedName("title")
        private String title;
        @SerializedName("publishedAt")
        private String publishedAt;
        @SerializedName("urlToImage")
        private String mainImageUrl;
        @SerializedName("description")
        private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatePosted() {
        return publishedAt;
    }

    public void setDatePosted(String datePosted) {
        this.publishedAt = datePosted;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

