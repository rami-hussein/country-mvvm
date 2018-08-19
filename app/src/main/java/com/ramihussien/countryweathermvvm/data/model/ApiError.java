package com.ramihussien.countryweathermvvm.data.model;

public class ApiError {

    private String mErrorMessage;

    public ApiError(String errorMessage) {
        mErrorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    @SuppressWarnings("unused")
    public void setErrorMessage(String errorMessage) {
        this.mErrorMessage = errorMessage;
    }
}
