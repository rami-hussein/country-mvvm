package com.ramihussien.countryweathermvvm.ui.country;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ramihussien.countryweathermvvm.data.model.Country;
import com.ramihussien.countryweathermvvm.databinding.CountryListItemBinding;
import com.ramihussien.countryweathermvvm.util.Selectable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountryAdapter extends RecyclerView.Adapter<CountryViewHolder> {

    private Map<String, Country> mSelectedCountries;
    private List<Selectable<Country>> mSelectableCountries;

    private CountryItemViewModel.CountryClickListener mListener;

    CountryAdapter(List<Country> countries, CountryItemViewModel.CountryClickListener listener) {
        mListener = listener;
        mSelectableCountries = new ArrayList<>();
        setCountries(countries);
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new CountryViewHolder(CountryListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder countryViewHolder, int position) {
        countryViewHolder.bind(mSelectableCountries.get(position));
    }

    @Override
    public int getItemCount() {
        return mSelectableCountries.size();
    }

    public void setCountries(List<Country> countries) {
        mSelectableCountries.clear();
        for(Country country: countries) {
            Selectable<Country> selectable = new Selectable<>();
            selectable.setValue(country);
            selectable.setSelected(mSelectedCountries != null && mSelectedCountries.containsKey(country.getAlpha2Code()));
            mSelectableCountries.add(selectable);
        }
    }

    public void setSelectedCountries(Map<String, Country> selectedCountries) {
        mSelectedCountries = selectedCountries;
    }
}
