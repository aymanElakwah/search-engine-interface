package com.ayman.searchengine.network;

import com.ayman.searchengine.network.response.AutoCompleteResponse;
import com.ayman.searchengine.network.response.ImageSearchResultsResponse;
import com.ayman.searchengine.network.response.TextSearchResultsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {

    // SEARCH
    @GET("text.php")
    Call<TextSearchResultsResponse> searchText(
            @Query("q") String query,
            @Query("last_id") String lastID,
            @Query("per_page") int perPage

    );

    @GET("images.php")
    Call<ImageSearchResultsResponse> searchImage(
            @Query("q") String query,
            @Query("last_id") String lastID,
            @Query("per_page") int perPage

    );

    @GET("complete.php")
    Call<List<String>> complete(
            @Query("q") String query
    );
}
