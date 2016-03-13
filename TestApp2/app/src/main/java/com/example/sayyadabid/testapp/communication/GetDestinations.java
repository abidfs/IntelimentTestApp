package com.example.sayyadabid.testapp.communication;

import com.example.sayyadabid.testapp.datamodels.Destination;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Webservice class to get the destination details.
 * Extends the abstract base class WebServiceBase and provides implementation for its abstract method.
 * Once the response is available, parses it using Gson APIs and notifies it to calling class using WebServiceResponseListener interface.
 * Created by Sayyad.abid on 13-Mar-16.
 */
public class GetDestinations extends WebServiceBase {
    private final String url = "http://express-it.optusnet.com.au/sample.json";

    private final WebServiceResponseListener listener;

    /**
     * Constructor
     *
     * @param listener the WebServiceResponseListener to notify success/failure events.
     */
    public GetDestinations(WebServiceResponseListener listener) {
        this.listener = listener;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void parseResponse(String response) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Destination>>() {
        }.getType();
        ArrayList<Destination> destinations = gson.fromJson(response, type);
        listener.onResponseReceived(destinations);
    }

    @Override
    public void notifyError(CommunicationErrors errorCode, String errorMessage) {
        listener.onRequestFailed(errorCode, errorMessage);
    }
}
