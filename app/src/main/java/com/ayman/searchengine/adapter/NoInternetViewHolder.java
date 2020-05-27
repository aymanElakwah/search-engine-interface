package com.ayman.searchengine.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayman.searchengine.R;

public class NoInternetViewHolder extends RecyclerView.ViewHolder {

    public NoInternetViewHolder(@NonNull View itemView, final SearchResultsAdapter.RetryListener listener) {
        super(itemView);
        itemView.findViewById(R.id.retry_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.retry();
            }
        });
    }

}
