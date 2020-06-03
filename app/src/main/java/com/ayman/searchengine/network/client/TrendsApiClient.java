package com.ayman.searchengine.network.client;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ayman.searchengine.model.Trend;
import com.ayman.searchengine.network.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendsApiClient {

    private static TrendsApiClient instance;
    private MutableLiveData<List<Trend>> mTrends = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLoading = new MutableLiveData<>();
    private Call<List<Trend>> mCall;
    private Callback<List<Trend>> mCallBack;

    private TrendsApiClient() {
        mCallBack = new Callback<List<Trend>>() {
            @Override
            public void onResponse(@NonNull Call<List<Trend>> call, @NonNull Response<List<Trend>> response) {
                mLoading.postValue(false);
                if (!response.isSuccessful()) return;
                mTrends.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Trend>> call, @NonNull Throwable t) {
                mLoading.postValue(false);
            }
        };
    }

    public static TrendsApiClient getInstance() {
        if (instance != null) return instance;
        instance = new TrendsApiClient();
        return instance;
    }

    public LiveData<Boolean> isLoading() {
        return mLoading;
    }

    public LiveData<List<Trend>> getTrends() {
        return mTrends;
    }

    public void loadTrends(String country) {
        if (mCall != null) mCall.cancel();
        mLoading.setValue(true);
        mCall = ServiceGenerator.getSearchApi().getTrends(country);
        mCall.enqueue(mCallBack);
    }


}













