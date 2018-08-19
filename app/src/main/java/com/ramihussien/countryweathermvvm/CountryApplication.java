package com.ramihussien.countryweathermvvm;

import android.app.Activity;
import android.app.Application;

import com.ramihussien.countryweathermvvm.di.ApplicationComponent;
import com.ramihussien.countryweathermvvm.di.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


public class CountryApplication extends Application implements HasActivityInjector {

    private ApplicationComponent mApplicationComponent;

    @Inject
    DispatchingAndroidInjector<Activity> mActivityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeDaggerComponent();

        mApplicationComponent.inject(this);
    }

    private void initializeDaggerComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .application(this)
                .build();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityDispatchingAndroidInjector;
    }
}
