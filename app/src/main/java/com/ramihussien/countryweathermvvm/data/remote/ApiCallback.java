package com.ramihussien.countryweathermvvm.data.remote;

public interface ApiCallback<Success, Error> {

    void onSuccess(Success response);

    void onError(Error error);
}
