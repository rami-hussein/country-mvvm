package com.ramihussien.countryweathermvvm.util;

public class Selectable<T> {

    private T mValue;

    private boolean mSelected;

    public T getValue() {
        return mValue;
    }

    public void setValue(T value) {
        mValue = value;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }

    @Override
    public String toString() {
        return "[selected: " + mSelected + "]";
    }
}
