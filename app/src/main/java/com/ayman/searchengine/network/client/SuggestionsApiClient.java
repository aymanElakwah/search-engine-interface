package com.ayman.searchengine.network.client;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ayman.searchengine.network.ServiceGenerator;
import com.ayman.searchengine.network.response.AutoCompleteResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuggestionsApiClient {

    private MutableLiveData<List<String>> mSuggestions;
    private Call<List<String>> mRequest;
    private Callback<List<String>> mCallBack;
    private static SuggestionsApiClient instance;

    private SuggestionsApiClient() {
        mSuggestions = new MutableLiveData<>();
        mCallBack = new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (!response.isSuccessful()) return;
                mSuggestions.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        };
    }

    public void complete(String query) {
        if (mRequest != null) mRequest.cancel();
        mRequest = ServiceGenerator.getSearchApi().complete(query);
        mRequest.enqueue(mCallBack);
    }

    public static SuggestionsApiClient getInstance() {
        if (instance != null) return instance;
        instance = new SuggestionsApiClient();
        return instance;
    }


    public LiveData<List<String>> getSuggestions() {
        return mSuggestions;
    }

}













