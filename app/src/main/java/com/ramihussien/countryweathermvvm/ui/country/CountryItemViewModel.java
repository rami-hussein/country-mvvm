package com.ramihussien.countryweathermvvm.ui.country;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.ramihussien.countryweathermvvm.data.model.Country;
import com.ramihussien.countryweathermvvm.ui.base.BaseViewModel;
import com.ramihussien.countryweathermvvm.util.Selectable;

public class CountryItemViewModel extends BaseViewModel {

    private ObservableBoolean mIsChecked = new ObservableBoolean(false);
    private ObservableField<String> mFlagUrl;
    private ObservableField<String> mCountryName;

    private CountryClickListener mListener;
    private Country mCountry;

    CountryItemViewModel(@NonNull Selectable<Country> countrySelectable, @NonNull CountryClickListener listener) {
        mCountry = countrySelectable.getValue();
        mListener = listener;
        mFlagUrl = new ObservableField<>(mCountry.getFlagUrl());
        mCountryName = new ObservableField<>(mCountry.getName());
        setIsChecked(countrySelectable.isSelected());
    }

    public void onCountryClick() {
        setIsChecked(!getIsChecked().get());
        mListener.onCountryClick(mCountry, mIsChecked.get());
    }

    public ObservableBoolean getIsChecked() {
        return mIsChecked;
    }

    private void setIsChecked(boolean isChecked) {
        mIsChecked.set(isChecked);
    }

    public ObservableField<String> getFlagUrl() {
        return mFlagUrl;
    }

    public ObservableField<String> getCountryName() {
        return mCountryName;
    }

    public interface CountryClickListener {
        void onCountryClick(Country country, boolean selected);
    }
}
