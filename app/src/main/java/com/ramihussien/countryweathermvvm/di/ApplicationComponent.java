package com.ramihussien.countryweathermvvm.di;

import android.app.Application;

import com.ramihussien.countryweathermvvm.CountryApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, ActivityModule.class, ApplicationModulesProvider.class, ViewModelsModule.class})
public interface ApplicationComponent {

    void inject(CountryApplication application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}
