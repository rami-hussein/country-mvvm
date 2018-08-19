package com.ramihussien.countryweathermvvm.di;

import com.ramihussien.countryweathermvvm.ui.country.CountryActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract CountryActivity bindCountryActivity();
}
