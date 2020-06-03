package com.ayman.searchengine.ui.trends;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ayman.searchengine.R;
import com.ayman.searchengine.adapter.TrendsAdapter;
import com.ayman.searchengine.databinding.FragmentTrendsBinding;
import com.ayman.searchengine.model.Trend;
import com.ayman.searchengine.ui.home.Countries;

import java.util.List;

public class TrendsFragment extends Fragment {
    private TrendsViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initViewModel();
        FragmentTrendsBinding binding = FragmentTrendsBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setModel(mViewModel);
        View root = binding.getRoot();
        Toolbar toolbar = root.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_trends);
        setupRecyclerView(root);
        setupRefreshLayout(root);
        loadTrends();
        return root;
    }

    private void setupRecyclerView(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.trends_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        final TrendsAdapter adapter = new TrendsAdapter();
        recyclerView.setAdapter(adapter);
        mViewModel.getTrends().observe(getViewLifecycleOwner(), new Observer<List<Trend>>() {
            @Override
            public void onChanged(List<Trend> trends) {
                adapter.setTrends(trends);
            }
        });
    }

    private void setupRefreshLayout(View root) {
        final SwipeRefreshLayout refreshLayout = root.findViewById(R.id.refresh_layout);
        refreshLayout.setColorSchemeResources(R.color.colorAccent);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadTrends(true);
            }
        });
        mViewModel.isLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean refreshing) {
                if (refreshLayout.isRefreshing() != refreshing)
                    refreshLayout.setRefreshing(refreshing);
            }
        });
    }

    private void initViewModel() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        NavBackStackEntry mBackStackEntry = navController.getBackStackEntry(R.id.mobile_navigation);
        mViewModel = new ViewModelProvider(mBackStackEntry).get(TrendsViewModel.class);
    }

    private void loadTrends() {
        loadTrends(false);
    }

    private void loadTrends(boolean force) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String country = Countries.getSavedCountryCode(preferences);
        if (force || !mViewModel.getLoadedCountry().equals(country))
            mViewModel.loadTrends(country);
    }


}
