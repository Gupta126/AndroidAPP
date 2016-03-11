package com.rahulgupta.androidapp.Helper;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by rahul.gupta on 3/11/2016.
 */
public class Utils {

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
