package com.ramihussien.countryweathermvvm.ui.country;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.ramihussien.countryweathermvvm.data.model.Country;
import com.ramihussien.countryweathermvvm.databinding.CountryListItemBinding;
import com.ramihussien.countryweathermvvm.util.Selectable;

public class CountryViewHolder extends RecyclerView.ViewHolder {

    private CountryListItemBinding mBinding;
    private CountryItemViewModel.CountryClickListener mListener;

    CountryViewHolder(@NonNull CountryListItemBinding binding, @NonNull CountryItemViewModel.CountryClickListener listener) {
        super(binding.getRoot());
        mBinding = binding;
        mListener = listener;
    }

    public void bind(Selectable<Country> selectable) {
        mBinding.setViewModel(new CountryItemViewModel(selectable, mListener));
        mBinding.executePendingBindings();
    }
}
