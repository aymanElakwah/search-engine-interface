package com.ayman.searchengine.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ayman.searchengine.model.SearchResult;
import com.ayman.searchengine.network.client.SearchApiClient;
import com.ayman.searchengine.network.client.SuggestionsApiClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public abstract class BaseViewModel extends ViewModel {
    protected SearchApiClient mApi;
    protected LiveData<List<SearchResult>> mSearchResults;
    private SuggestionsApiClient mSuggestionsApi;
    private String searchQuery;
    private LiveData<Boolean> isQueryExhausted;
    private LiveData<Boolean> isNoInternet;
    private LiveData<Boolean> isInternalServerError;
    private LiveData<List<String>> suggestions;
    private String mSearchedCountry;

    protected BaseViewModel() {
        mApi = getApi();
        mSuggestionsApi = SuggestionsApiClient.getInstance();
        isQueryExhausted = mApi.isQueryExhausted();
        isNoInternet = mApi.isNoInternet();
        isInternalServerError = mApi.isInternalServerError();
        suggestions = mSuggestionsApi.getSuggestions();
        searchQuery = "";
        mSearchedCountry = "";
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public LiveData<Boolean> isQueryExhausted() {
        return isQueryExhausted;
    }

    public LiveData<Boolean> isNoInternet() {
        return isNoInternet;
    }

    public LiveData<Boolean> isInternalServerError() {
        return isInternalServerError;
    }

    LiveData<List<String>> getSuggestions() {
        return suggestions;
    }

    void complete(String query) {
        mSuggestionsApi.complete(query);
    }

    public void search(String query, String country, String user) {
        searchQuery = query;
        mSearchedCountry = country;
        mApi.search(query, country, user);
    }

    void searchNext(int pageNumber) {
        mApi.searchNext(pageNumber);
    }


    public LiveData<List<SearchResult>> getSearchResults() {
        return mSearchResults;
    }

    String getSearchedCountry() {
        return mSearchedCountry;
    }

    protected abstract SearchApiClient getApi();

    void click(String url) {
        try {
            mApi.click(new URL(url).getHost());
        } catch (MalformedURLException ignore) {
        }
    }
}
