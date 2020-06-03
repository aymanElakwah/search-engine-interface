package com.ayman.searchengine.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ayman.searchengine.R;
import com.ayman.searchengine.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private HomeViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initViewModel();
        com.ayman.searchengine.databinding.FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setModel(mViewModel);
        View root = binding.getRoot();
        Spinner spinner = root.findViewById(R.id.country_spinner);
        spinner.setAdapter(new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, Countries.getCountryNames()));
        Integer selectedCountryIndex = mViewModel.getSelectedCountry().getValue();
        spinner.setSelection(selectedCountryIndex == null ? 0 : selectedCountryIndex);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mViewModel.setSelectedCountry(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mViewModel.getUserID().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mViewModel.getUserID().removeObserver(this);
                mViewModel.saveUserID(s);
            }
        });
        return root;
    }

    private void initViewModel() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        NavBackStackEntry mBackStackEntry = navController.getBackStackEntry(R.id.mobile_navigation);
        mViewModel = new ViewModelProvider(mBackStackEntry).get(HomeViewModel.class);
    }


}
