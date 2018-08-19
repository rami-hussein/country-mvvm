package com.ramihussien.countryweathermvvm.ui.country;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ramihussien.countryweathermvvm.BR;
import com.ramihussien.countryweathermvvm.R;
import com.ramihussien.countryweathermvvm.data.model.ApiError;
import com.ramihussien.countryweathermvvm.data.model.Country;
import com.ramihussien.countryweathermvvm.databinding.ActivityCountryBinding;
import com.ramihussien.countryweathermvvm.ui.base.BaseActivity;

import java.util.ArrayList;

public class CountryActivity extends BaseActivity<ActivityCountryBinding, CountryViewModel> implements CountryItemViewModel.CountryClickListener {

    private CountryAdapter mCountryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.showLoading(true);
        subscribeToCountries();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_country;
    }

    @Override
    public CountryViewModel getViewModel() {
        return ViewModelProviders.of(this, getViewModelFactory()).get(CountryViewModel.class);
    }

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void initViews() {
        mViewModel.init();
        mCountryAdapter = new CountryAdapter(new ArrayList<>(), this);

        RecyclerView countryRecycler = findViewById(R.id.countries_rv);
        countryRecycler.setLayoutManager(new LinearLayoutManager(this));
        countryRecycler.setAdapter(mCountryAdapter);

        mBinding.countriesSwipeToRefresh.setOnRefreshListener(() -> mViewModel.retry());
    }

    @Override
    public void onCountryClick(Country country, boolean selected) {
        mViewModel.addSelectedCountry(country, selected);
    }

    private void subscribeToCountries() {
        mViewModel.getCountries().observe(this, res -> {
            mViewModel.showLoading(false);
            if (mBinding.countriesSwipeToRefresh.isRefreshing()) {
                mBinding.countriesSwipeToRefresh.setRefreshing(false);
            }
            if (res == null) {
                showError(getString(R.string.smth_went_wrong_error_msg), v -> mViewModel.retry());
                return;
            }

            if (res.getData() != null) {
                mCountryAdapter.setSelectedCountries(mViewModel.getSelectedCountries());
                mCountryAdapter.setCountries(res.getData());
                mCountryAdapter.notifyDataSetChanged();
            } else {
                ApiError apiError = res.getError();
                showError(apiError == null ? getString(R.string.smth_went_wrong_error_msg) : apiError.getErrorMessage(), v -> mViewModel.retry());
            }
        });
    }
}
