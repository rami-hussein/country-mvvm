package com.ramihussien.countryweathermvvm.data.repositoy;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.ramihussien.countryweathermvvm.data.local.db.dao.CountryDao;
import com.ramihussien.countryweathermvvm.data.model.ApiError;
import com.ramihussien.countryweathermvvm.data.model.Response;
import com.ramihussien.countryweathermvvm.data.model.Country;
import com.ramihussien.countryweathermvvm.data.remote.ApiCallback;
import com.ramihussien.countryweathermvvm.data.remote.CountryApiExecutor;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CountryRepository {

    private CountryApiExecutor mCountryApiExecutor;
    private CountryDao mCountryDao;
    private Executor mExecutor;

    private MediatorLiveData<Response<List<Country>, ApiError>> mMediatorLiveData = new MediatorLiveData<>();

    @Inject
    public CountryRepository(CountryApiExecutor countryApiExecutor, CountryDao countryDao, Executor executor) {
        mCountryApiExecutor = countryApiExecutor;
        mCountryDao = countryDao;
        mExecutor = executor;
    }

    public LiveData<Response<List<Country>, ApiError>> getCountries() {
        LiveData<List<Country>> data = mCountryDao.loadAll();
        mMediatorLiveData.addSource(data, countries -> {
            if(shouldFetch(countries)) {
                fetchFromNetwork();
            } else {
                mMediatorLiveData.removeSource(data);
                mMediatorLiveData.setValue(new Response<>(countries, null));
            }
        });
        return mMediatorLiveData;
    }

    private boolean shouldFetch(List<Country> countries) {
        return countries == null || countries.isEmpty();
    }

    private void fetchFromNetwork() {
        Response<List<Country>, ApiError> apiResponse = new Response<>();
        mCountryApiExecutor.getCountries(new ApiCallback<List<Country>, Void>(){
            @Override
            public void onSuccess(List<Country> response) {
                mExecutor.execute(() -> mCountryDao.insertAll(response));

                apiResponse.setData(response);
                mMediatorLiveData.setValue(apiResponse);
            }

            @Override
            public void onError(Void aVoid) {
                apiResponse.setError(new ApiError("There was an error getting the data!"));
                mMediatorLiveData.setValue(apiResponse);
            }
        });
    }

}
