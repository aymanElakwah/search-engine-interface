package com.ayman.searchengine.network.client;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ayman.searchengine.network.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestIdClient {

    private static RequestIdClient instance;
    private MutableLiveData<String> mUserID;
    private Call<String> mRequest;
    private Callback<String> mCallBack;

    public RequestIdClient() {
        mUserID = new MutableLiveData<>();
        mCallBack = new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (!response.isSuccessful()) return;
                mUserID.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.e("USER_ID", t.toString());
            }
        };
    }

    public void requestUserID() {
        if (mRequest != null) mRequest.cancel();
        mRequest = ServiceGenerator.getSearchApi().requestID();
        mRequest.enqueue(mCallBack);
    }

    public MutableLiveData<String> getUserID() {
        return mUserID;
    }

}













