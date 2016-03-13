package com.example.sayyadabid.testapp.ui.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * This class is used to display toast.
 * Created by Sayyad.abid on 12-Mar-16.
 */
public class CustomToast {
    public static void show(final Activity activity, final String message, final int length) {
        try {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Toast.makeText(activity, message, length).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
