package com.example.sayyadabid.testapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Sayyad.abid on 12-Mar-16.
 */
public class MainApplication extends Application {
    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
