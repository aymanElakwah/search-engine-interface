package com.ayman.searchengine.ui.trends;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.ayman.searchengine.model.Trend;
import com.ayman.searchengine.network.client.TrendsApiClient;

import java.util.List;

public class TrendsViewModel extends ViewModel {

    private String mCountry = "";
    private TrendsApiClient mApi = TrendsApiClient.getInstance();
    private LiveData<List<Trend>> mTrends = mApi.getTrends();
    private LiveData<Boolean> mLoading = mApi.isLoading();
    private MediatorLiveData<Boolean> showNoTrends = new MediatorLiveData<>();

    public TrendsViewModel() {
        showNoTrends.addSource(mTrends, new Observer<List<Trend>>() {
            @Override
            public void onChanged(List<Trend> trends) {
                boolean loading = mLoading.getValue() != null && mLoading.getValue();
                showNoTrends.postValue(!loading && trends.isEmpty());
            }
        });
        showNoTrends.addSource(mLoading, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loading) {
                boolean emptyTrends = mTrends.getValue() == null || mTrends.getValue().isEmpty();
                showNoTrends.postValue(!loading && emptyTrends);
            }
        });
    }


    void loadTrends(String country) {
        mCountry = country;
        mApi.loadTrends(country);
    }

    LiveData<List<Trend>> getTrends() {
        return mTrends;
    }

    LiveData<Boolean> isLoading() {
        return mLoading;
    }

    String getLoadedCountry() {
        return mCountry;
    }

    public LiveData<Boolean> showNoTrends() {
        return showNoTrends;
    }

}