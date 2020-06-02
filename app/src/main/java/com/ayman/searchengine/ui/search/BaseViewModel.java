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
    private LiveData<Boolean> isRequestTimedOut;
    private LiveData<List<String>> suggestions;
    private String mSearchedCountry;

    protected BaseViewModel() {
        mApi = getApi();
        mSuggestionsApi = SuggestionsApiClient.getInstance();
        isQueryExhausted = mApi.isQueryExhausted();
        isRequestTimedOut = mApi.isNoInternet();
        suggestions = mSuggestionsApi.getSuggestions();
        searchQuery = "";
        mSearchedCountry = "";
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    LiveData<Boolean> isQueryExhausted() {
        return isQueryExhausted;
    }

    LiveData<Boolean> isRequestTimedOut() {
        return isRequestTimedOut;
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


    LiveData<List<SearchResult>> getSearchResults() {
        return mSearchResults;
    }

    String getSearchedCountry() {
        return mSearchedCountry;
    }

    protected abstract SearchApiClient getApi();

    void click(String url) {
        try {
            mApi.click(new URL(url).getAuthority());
        } catch (MalformedURLException ignore) {
        }
    }
}
