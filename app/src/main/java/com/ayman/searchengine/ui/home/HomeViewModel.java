package com.ayman.searchengine.ui.home;

import android.app.Application;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import com.ayman.searchengine.network.client.RequestIdClient;

public class HomeViewModel extends AndroidViewModel {

    public static final String USER_ID = "USER_ID";
    private MutableLiveData<String> userID;
    private MutableLiveData<Integer> selectedCountry = new MutableLiveData<>();
    private SharedPreferences preferences;


    public HomeViewModel(@NonNull Application application) {
        super(application);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        selectedCountry.setValue(Countries.getSavedCountryIndex(preferences));
        if (preferences.contains(USER_ID))
            userID = new MutableLiveData<>(preferences.getString(USER_ID, ""));
        else {
            Toast.makeText(application, "Requesting User ID", Toast.LENGTH_SHORT).show();
            RequestIdClient api = new RequestIdClient();
            api.requestUserID();
            userID = api.getUserID();
        }

    }

    public LiveData<Integer> getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(int i) {
        selectedCountry.setValue(i);
        Countries.saveCountryIndex(preferences, i);
    }

    public LiveData<String> getUserID() {
        return userID;
    }

    public void saveUserID(String id) {
        if (!preferences.contains(USER_ID))
            preferences.edit().putString(USER_ID, id).apply();
    }

}