package com.ayman.searchengine.network.client;


import com.ayman.searchengine.model.SearchResult;
import com.ayman.searchengine.network.ServiceGenerator;
import com.ayman.searchengine.network.response.ImageSearchResultsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageSearchApiClient extends SearchApiClient {

    private static final int RESULTS_PER_PAGE = 10;
    private static ImageSearchApiClient instance;
    private Call<ImageSearchResultsResponse> mImageSearchCall;
    private Callback<ImageSearchResultsResponse> mImageSearchCallBack;

    private ImageSearchApiClient() {
        mImageSearchCallBack = new Callback<ImageSearchResultsResponse>() {
            @Override
            public void onResponse(Call<ImageSearchResultsResponse> call, Response<ImageSearchResultsResponse> response) {
                if (!response.isSuccessful()) {
                    mNoInternet.postValue(true);
                    if (mLastID == -1) mSearchResults.postValue(null);
                    return;
                }
                ImageSearchResultsResponse body = response.body();
                List<SearchResult> list = new ArrayList<SearchResult>(body.getData());
                if (mLastID == -1) {
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
            public void onFailure(Call<ImageSearchResultsResponse> call, Throwable t) {
                if(call.isCanceled()) return;
                mNoInternet.postValue(true);
                if (mLastID == -1) mSearchResults.postValue(null);
            }
        };
    }

    public static ImageSearchApiClient getInstance() {
        if (instance == null) {
            instance = new ImageSearchApiClient();
        }
        return instance;
    }

    @Override
    protected void search() {
        if (mImageSearchCall != null) mImageSearchCall.cancel();
        mIsQueryExhausted.setValue(false);
        mNoInternet.setValue(false);
        mImageSearchCall = getImageSearchResults(mQuery, mLastID);
        mImageSearchCall.enqueue(mImageSearchCallBack);
    }

    private Call<ImageSearchResultsResponse> getImageSearchResults(String query, int lastID) {
        return ServiceGenerator.getSearchApi().searchImage(
                query,
                String.valueOf(lastID),
                RESULTS_PER_PAGE
        );
    }
}













