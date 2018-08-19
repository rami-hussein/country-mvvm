package com.ramihussien.countryweathermvvm.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.ramihussien.countryweathermvvm.ui.base.ViewModelFactory;
import com.ramihussien.countryweathermvvm.ui.country.CountryViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@SuppressWarnings("unused")
@Module
abstract class ViewModelsModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(CountryViewModel.class)
    abstract ViewModel countryViewModel(CountryViewModel countryViewModel);
}
