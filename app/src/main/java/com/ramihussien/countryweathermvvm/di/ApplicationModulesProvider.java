package com.ramihussien.countryweathermvvm.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.ramihussien.countryweathermvvm.data.local.db.AppDatabase;
import com.ramihussien.countryweathermvvm.data.local.db.dao.CountryDao;
import com.ramihussien.countryweathermvvm.data.remote.CountryApiExecutor;
import com.ramihussien.countryweathermvvm.data.repositoy.CountryRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModulesProvider {

    @Provides
    @Singleton
    public CountryApiExecutor provideCountryApiExecutor() {
        return new CountryApiExecutor();
    }

    @Provides
    @Singleton
    public CountryRepository provideCountryRepository(CountryApiExecutor countryApiExecutor, CountryDao countryDao, Executor executor) {
        return new CountryRepository(countryApiExecutor, countryDao, executor);
    }

    @Provides
    @Singleton
    public AppDatabase provideDatabase(Application application){
        return Room.databaseBuilder(application, AppDatabase.class, "CountryDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public CountryDao provideCountryDao(AppDatabase appDatabase){
        return appDatabase.countryDao();
    }

    @Provides
    @Singleton
    public Executor provideExecutor(){
        return Executors.newSingleThreadExecutor();
    }
}
