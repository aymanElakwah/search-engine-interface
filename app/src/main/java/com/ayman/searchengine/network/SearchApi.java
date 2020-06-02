package com.ayman.searchengine.network;

import com.ayman.searchengine.model.ImageSearchResult;
import com.ayman.searchengine.model.TextSearchResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {

    // SEARCH
    @GET("search")
    Call<List<TextSearchResult>> searchText(
            @Query("query") String query,
            @Query("page_number") int pageNumber,
            @Query("per_page") int perPage,
            @Query("country") String country,
            @Query("user") String user
    );

    @GET("images")
    Call<List<ImageSearchResult>> searchImage(
            @Query("query") String query,
            @Query("page_number") String lastID,
            @Query("per_page") int perPage,
            @Query("country") String country,
            @Query("user") String user

    );

    @GET("complete")
    Call<List<String>> complete(
            @Query("query") String query
    );

    @GET("request_id")
    Call<String> requestID();
}
