package com.ramihussien.countryweathermvvm.data.model;

/**
 * Generic Response wrapper
 *
 * @param <DATA>  the response data type
 * @param <ERROR> the error response
 */
public class Response<DATA, ERROR> {

    private DATA mData;

    private ERROR mError;

    public Response() {
    }

    public Response(DATA data, ERROR error) {
        mData = data;
        mError = error;
    }

    public void setData(DATA data) {
        mData = data;
    }

    public void setError(ERROR error) {
        mError = error;
    }

    public DATA getData() {
        return mData;
    }

    public ERROR getError() {
        return mError;
    }
}
