package com.example.bctn.check_internet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Common {
    public static boolean isConnectInternet(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo infos = connectivityManager.getActiveNetworkInfo();
            return infos != null;
        }
        return false;

    }
}
