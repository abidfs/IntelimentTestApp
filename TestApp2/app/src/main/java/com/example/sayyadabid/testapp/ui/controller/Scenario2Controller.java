package com.example.sayyadabid.testapp.ui.controller;

import android.widget.Toast;

import com.example.sayyadabid.testapp.R;
import com.example.sayyadabid.testapp.communication.CommunicationErrors;
import com.example.sayyadabid.testapp.communication.GetDestinations;
import com.example.sayyadabid.testapp.communication.WebServiceResponseListener;
import com.example.sayyadabid.testapp.datamodels.Destination;
import com.example.sayyadabid.testapp.ui.Scenario2Activity;
import com.example.sayyadabid.testapp.ui.utils.CustomDialog;
import com.example.sayyadabid.testapp.ui.utils.CustomProgressDialog;
import com.example.sayyadabid.testapp.ui.utils.CustomToast;

import java.util.ArrayList;

/**
 * Controller class for Scenario2Activity.
 * Implements the WebServiceResponseListener and handles its onResponseReceived and onRequestFailed events.
 * Created by Sayyad.abid on 13-Mar-16.
 */
public class Scenario2Controller implements WebServiceResponseListener {
    private Scenario2Activity activity;
    private ArrayList<Destination> destinations;

    /**
     * Parameterized constructor
     *
     * @param activity the Scenario2Activity reference
     */
    public Scenario2Controller(Scenario2Activity activity) {
        this.activity = activity;
    }

    public void getDestinationsFromServer() {
        CustomProgressDialog.show(activity, activity.getString(R.string.msg_getting_destinations), false);
        GetDestinations getDestinations = new GetDestinations(this);
        getDestinations.send();
    }

    @Override
    public void onResponseReceived(Object response) {
        CustomProgressDialog.dismiss(activity);
        destinations = (ArrayList<Destination>) response;
        String[] destinationNames = getDestinationNames();
        if (destinationNames != null) {
            activity.populateSpinner(destinationNames);
        } else {
            CustomToast.show(activity, activity.getString(R.string.err_no_destinations), Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onRequestFailed(CommunicationErrors errorCode, String errorMessage) {
        CustomProgressDialog.dismiss(activity);
        switch (errorCode) {
            case ERROR_IN_COMMUNICATION:
            case NO_NETWORK:
            case INVALID_RESPONSE:
                CustomDialog.showDialog(activity, R.string.dlg_title_error, errorMessage, R.string.dlg_btn_ok, -1, -1);
                break;
        }
    }

    /**
     * Method to get the destination names. Called to set the adapter to location spinner.
     *
     * @return the names of destinations if available, null otherwise
     */
    public String[] getDestinationNames() {
        if (destinations == null) {
            return null;
        }

        String[] names = new String[destinations.size()];
        int i = 0;
        for (Destination destination : destinations) {
            names[i++] = destination.getName();
        }
        return names;
    }

    /**
     * Method to get the Destination object at input position.
     * Called when a particular item from location spinner is selected.
     *
     * @param position the position.
     * @return the Destination object at input position.
     */
    public Destination getDestinationAt(int position) {
        return destinations.get(position);
    }
}
