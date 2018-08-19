package com.ramihussien.countryweathermvvm.ui.country;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.support.annotation.NonNull;

import com.ramihussien.countryweathermvvm.data.repositoy.CountryRepository;

@SuppressWarnings("unused")
public class CountryViewModelFactory implements Factory {

    private CountryRepository mCountryRepository;

    public CountryViewModelFactory(@NonNull CountryRepository countryRepository) {
        mCountryRepository = countryRepository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CountryViewModel(mCountryRepository);
    }
}