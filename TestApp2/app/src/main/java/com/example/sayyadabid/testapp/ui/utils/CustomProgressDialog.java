package com.example.sayyadabid.testapp.ui.utils;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * This class is used to display progress dialog.
 * Created by SAYYAD on 12-03-2016.
 */
public class CustomProgressDialog {
    private static ProgressDialog mProgressDialog;

    public static void show(final Activity activity, final String message, final boolean cancellable) {
        try {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mProgressDialog = new ProgressDialog(activity);
                        mProgressDialog.setMessage(message);
                        mProgressDialog.setCancelable(cancellable);
                        mProgressDialog.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dismiss(final Activity activity) {
        if (mProgressDialog != null) {
            try {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mProgressDialog.dismiss();
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
}
