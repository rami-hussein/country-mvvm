package com.ramihussien.countryweathermvvm.data.remote;

import android.support.annotation.NonNull;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ramihussien.countryweathermvvm.data.model.Country;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryApiExecutor {

    private static final String BASE_URL = "https://restcountries.eu/";

    private CountryApisService mApiService;

    public CountryApiExecutor() {
        mApiService = initApiService(BASE_URL, CountryApisService.class);
    }


    public void getCountries(ApiCallback<List<Country>, Void> callback) {
        mApiService.getAllCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(@NonNull Call<List<Country>> call, @NonNull retrofit2.Response<List<Country>> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Country>> call, @NonNull Throwable t) {
                callback.onError(null);
            }
        });
    }


    /**
     * Initializes retrofit APIs instance
     * @param baseUrl base url for the micro-service
     * @param serviceClass the apis service interface
     */
    private <S> S initApiService(String baseUrl, Class<S> serviceClass) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        long timeOut = 30 * 1000;

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(provideHeadersInterceptor())
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS);


        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT, Modifier.VOLATILE)
                .setExclusionStrategies(new ExclusionStrategy() {

                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaredType() == Void.TYPE;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return clazz.getName().equalsIgnoreCase(Void.class.getName());
                    }
                })
                .create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(okHttpClientBuilder.build())
//                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
//                .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(serviceClass);
    }

    private Interceptor provideHeadersInterceptor() {
        return chain -> {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Connection", "close");

            request = builder.build();

            return chain.proceed(request);
        };
    }
}
