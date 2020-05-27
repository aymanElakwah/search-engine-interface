package com.ayman.searchengine.network.response;

import java.util.ArrayList;

public class AutoCompleteResponse {
    private ArrayList<String> suggestions;

    public AutoCompleteResponse(ArrayList<String> suggestions) {
        this.suggestions = suggestions;
    }

    public ArrayList<String> getSuggestions() {
        return suggestions;
    }
}
