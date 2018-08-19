package com.ramihussien.countryweathermvvm.ui.country;

import android.arch.lifecycle.LiveData;
import android.databinding.ObservableBoolean;

import com.ramihussien.countryweathermvvm.data.model.ApiError;
import com.ramihussien.countryweathermvvm.data.model.Country;
import com.ramihussien.countryweathermvvm.data.model.Response;
import com.ramihussien.countryweathermvvm.data.repositoy.CountryRepository;
import com.ramihussien.countryweathermvvm.ui.base.BaseViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class CountryViewModel extends BaseViewModel {

    private CountryRepository mRepository;

    private ObservableBoolean mIsLoading = new ObservableBoolean(false);

    private LiveData<Response<List<Country>, ApiError>> mCountriesLiveData;

    private Map<String, Country> mSelectedCountries;

    @Inject
    CountryViewModel(CountryRepository countryRepository) {
        mRepository = countryRepository;
        mSelectedCountries = new HashMap<>();
    }

    public void init() {
        fetchCountries();
    }

    public LiveData<Response<List<Country>, ApiError>> getCountries() {
        return mCountriesLiveData;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void showLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public void addSelectedCountry(Country country, boolean selected) {
        if(selected) {
            mSelectedCountries.put(country.getAlpha2Code(), country);
        } else {
            if(mSelectedCountries.containsKey(country.getAlpha2Code())) {
                mSelectedCountries.remove(country.getAlpha2Code());
            }
        }
    }

    public Map<String, Country> getSelectedCountries() {
        return mSelectedCountries;
    }

    public void retry() {
        fetchCountries();
    }

    private void fetchCountries() {
        mCountriesLiveData = mRepository.getCountries();
    }
}
