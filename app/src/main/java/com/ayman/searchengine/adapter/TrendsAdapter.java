package com.ayman.searchengine.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayman.searchengine.databinding.TrendItemBinding;
import com.ayman.searchengine.model.Trend;

import java.util.ArrayList;
import java.util.List;

public class TrendsAdapter extends RecyclerView.Adapter<TrendsAdapter.TrendViewHolder> {

    private List<Trend> mTrends = new ArrayList<>();

    @NonNull
    @Override
    public TrendsAdapter.TrendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TrendItemBinding binding = TrendItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TrendViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendsAdapter.TrendViewHolder holder, int position) {
        holder.bind(mTrends.get(position));
    }

    @Override
    public int getItemCount() {
        return mTrends == null ? 0 : mTrends.size();
    }


    public void setTrends(List<Trend> trends) {
        mTrends = trends;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface TrendClickListener {
        void onTrendClick(String title);
    }

    static class TrendViewHolder extends RecyclerView.ViewHolder {
        private TrendItemBinding mBinding;

        TrendViewHolder(TrendItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Trend trend) {
            mBinding.setItem(trend);
            mBinding.executePendingBindings();
        }
    }
}
