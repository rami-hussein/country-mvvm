package com.ramihussien.countryweathermvvm.util;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.view.View;

import com.androidadvance.topsnackbar.TSnackbar;
import com.ramihussien.countryweathermvvm.R;

public class SnackBarUtils {

    private SnackBarUtils() {}


    public enum Action {
        WARNING,
        ERROR,
        SUCCESS
    }

    public static void showWithAction(Context context, View parentLayout, String msg,
                                      int duration, Action action, String actionName,
                                      View.OnClickListener callback) {
        TSnackbar tSnackbar = TSnackbar.make(parentLayout, msg, duration);
        tSnackbar.setActionTextColor(context.getResources().getColor(android.R.color.white));
        tSnackbar.setMaxWidth(parentLayout.getWidth());
        tSnackbar.setAction(actionName, callback);

        View view = tSnackbar.getView();
        view.setBackgroundColor(context.getResources().getColor(getColor(action)));
        tSnackbar.show();
    }

    public static void show(Context context, View root, String msg, int duration, Action action) {
        TSnackbar tSnackbar = TSnackbar.make(root, msg, duration);
        tSnackbar.setActionTextColor(context.getResources().getColor(android.R.color.white));

        View view = tSnackbar.getView();
        view.setBackgroundColor(context.getResources().getColor(getColor(action)));
        tSnackbar.show();
    }


    @ColorRes
    private static int getColor(Action type) {
        switch (type) {
            case WARNING:
                return R.color.warning;
            case ERROR:
                return R.color.error;
            case SUCCESS:
            default:
                return R.color.colorPrimaryDark;
        }
    }

}
