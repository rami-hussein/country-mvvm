package com.ramihussien.countryweathermvvm.data.remote;



import com.ramihussien.countryweathermvvm.data.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryApisService {

    @GET("rest/v1/all/")
    Call<List<Country>> getAllCountries();
}
