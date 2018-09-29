package com.example.srikant.day2;

public class NewsModel {
        private String title;
        private String publishedAt;

        private String mainImageUrl;

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
    private String description;
}

