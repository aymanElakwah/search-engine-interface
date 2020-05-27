package com.ayman.searchengine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AutoCompleteAdapter extends RecyclerView.Adapter<AutoCompleteAdapter.AutoCompleteViewHolder> {

    private ArrayList<String> mSuggestions = new ArrayList<>();

    @NonNull
    @Override
    public AutoCompleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.select_dialog_item, parent, false);
        return new AutoCompleteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AutoCompleteViewHolder holder, int position) {
        holder.bind(mSuggestions.get(position));
    }

    @Override
    public int getItemCount() {
        return mSuggestions.size();
    }

    static class AutoCompleteViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        AutoCompleteViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(android.R.id.text1);
        }

        void bind(String text) {
            mTextView.setText(text);
        }

    }
}
