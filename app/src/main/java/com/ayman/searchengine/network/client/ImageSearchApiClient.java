package com.ayman.searchengine.network.client;


import androidx.annotation.NonNull;

import com.ayman.searchengine.model.ImageSearchResult;
import com.ayman.searchengine.model.SearchResult;
import com.ayman.searchengine.network.ServiceGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageSearchApiClient extends SearchApiClient {

    private static final int RESULTS_PER_PAGE = 10;
    private static ImageSearchApiClient instance;
    private Call<List<ImageSearchResult>> mImageSearchCall;
    private Callback<List<ImageSearchResult>> mImageSearchCallBack;

    private ImageSearchApiClient() {
        mImageSearchCallBack = new Callback<List<ImageSearchResult>>() {
            @Override
            public void onResponse(@NonNull Call<List<ImageSearchResult>> call, @NonNull Response<List<ImageSearchResult>> response) {
                if (!response.isSuccessful()) {
                    mNoInternet.postValue(true);
                    if (mPageNumber == 1) mSearchResults.postValue(null);
                    return;
                }
                List<SearchResult> list = new ArrayList<SearchResult>(response.body());
                filterResults(list);
                if (mPageNumber == 1) {
                    mSearchResults.postValue(list);
                } else {
                    List<SearchResult> searchResults = mSearchResults.getValue();
                    searchResults.addAll(list);
                    mSearchResults.postValue(searchResults);
                }
                if (list.size() == 0 || list.size() % RESULTS_PER_PAGE != 0)
                    mIsQueryExhausted.postValue(true);
            }

            @Override
            public void onFailure(@NonNull Call<List<ImageSearchResult>> call, @NonNull Throwable t) {
                if (call.isCanceled()) return;
                mNoInternet.postValue(true);
                if (mPageNumber == 1) mSearchResults.postValue(null);
            }
        };
    }

    public static ImageSearchApiClient getInstance() {
        if (instance == null) {
            instance = new ImageSearchApiClient();
        }
        return instance;
    }

    private void filterResults(List<SearchResult> results) {
        Iterator<SearchResult> it = results.iterator();
        while (it.hasNext()) {
            ImageSearchResult result = (ImageSearchResult) it.next();
            String imageUrl = result.getImageUrl();
            if (!imageUrl.startsWith("http") || imageUrl.substring(imageUrl.lastIndexOf('.')).startsWith(".svg"))
                it.remove();
        }
    }

    @Override
    protected void search() {
        if (mImageSearchCall != null) mImageSearchCall.cancel();
        mIsQueryExhausted.setValue(false);
        mNoInternet.setValue(false);
        mImageSearchCall = getImageSearchResults(mQuery, mPageNumber, mCountry, mUser);
        mImageSearchCall.enqueue(mImageSearchCallBack);
    }

    private Call<List<ImageSearchResult>> getImageSearchResults(String query, int pageNumber, String country, String user) {
        return ServiceGenerator.getSearchApi().searchImage(
                query,
                String.valueOf(pageNumber),
                RESULTS_PER_PAGE,
                country,
                user
        );
    }
}













