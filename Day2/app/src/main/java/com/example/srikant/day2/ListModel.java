package com.example.srikant.day2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListModel {
    @SerializedName("articles")
    List<NewsModel> articlesList;
}
