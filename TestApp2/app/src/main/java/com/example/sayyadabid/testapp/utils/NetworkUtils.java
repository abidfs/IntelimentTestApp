package com.example.sayyadabid.testapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.example.sayyadabid.testapp.MainApplication;

/**
 * Utility class to handle network APIs.
 * The main responsibility of this class is to check whether the network is available.
 * Created by Sayyad.abid on 13-Mar-16.
 */
public class NetworkUtils {
    /**
     * Method to check if network is available.
     * First checks if wifi is available, it it is available, returns true else checks if mobile network is available.
     *
     * @return true if either wifi or mobile network is available, false otherwise.
     */
    public boolean isNetworkAvailable() {
        WifiManager manager = (WifiManager) MainApplication.appContext.getSystemService(Context.WIFI_SERVICE);
        if (manager.isWifiEnabled()) {
            WifiInfo wifiInfo = manager.getConnectionInfo();
            if (wifiInfo != null) {
                SupplicantState supplicantState = wifiInfo.getSupplicantState();
                if (supplicantState == SupplicantState.COMPLETED) {
                    return true;
                }
            }
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) MainApplication.appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            NetworkInfo.State state = networkInfo.getState();
            if (state == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }

        return false;
    }
}
