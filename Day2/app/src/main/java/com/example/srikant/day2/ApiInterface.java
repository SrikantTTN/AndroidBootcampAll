package com.example.srikant.day2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/v2/top-headlines")
    Call<ListModel> getNewsList(@Query("sources") String source, @Query("apiKey") String key);
}
