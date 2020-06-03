package com.ayman.searchengine.ui.trends;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ayman.searchengine.model.Trend;
import com.ayman.searchengine.network.client.TrendsApiClient;

import java.util.List;

public class TrendsViewModel extends ViewModel {

    private String mCountry = "";
    private TrendsApiClient mApi = TrendsApiClient.getInstance();


    void loadTrends(String country) {
        mCountry = country;
        mApi.loadTrends(country);
    }

    public LiveData<List<Trend>> getTrends() {
        return mApi.getTrends();
    }

    public LiveData<Boolean> isLoading() {
        return mApi.isLoading();
    }

    String getLoadedCountry() {
        return mCountry;
    }

}