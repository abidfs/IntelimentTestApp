package com.example.sayyadabid.testapp.communication;

/**
 * Interface to notify web service related events.
 * The class which calls the webservice must implement this interface and listen for onResponseReceived and onRequestFailed events.
 * Created by Sayyad.abid on 13-Mar-16.
 */
public interface WebServiceResponseListener {
    void onResponseReceived(Object response);

    void onRequestFailed(CommunicationErrors errorCode, String errorMessage);
}
