package com.example.sayyadabid.testapp.communication;

import android.os.AsyncTask;

import com.example.sayyadabid.testapp.utils.NetworkUtils;

/**
 * This is an Abstract base class. Every webservice class must extend this class and provide implementation for its abstract methods.
 * The send method of this class is concrete and its used to send the http request.
 * Before making any request, it first checks if network is available, if network is unavailable, it simply returns error.
 * If network is available, it sends the request on AsyncTask.
 * Created by Sayyad.abid on 13-Mar-16.
 */
public abstract class WebServiceBase {
    /**
     * Method to get the communication endpoint
     *
     * @return the communication endpoint
     */
    public abstract String getUrl();

    /**
     * Method to parse the received response. The derived classed must take the responsibility of parsing the response.
     *
     * @param response the received response string
     */
    public abstract void parseResponse(String response);

    /**
     * Method to notify error in case of communication
     *
     * @param errorCode    the CommunicationErrors
     * @param errorMessage the error message
     */
    public abstract void notifyError(CommunicationErrors errorCode, String errorMessage);

    /**
     * This method is used to send the http request.
     * Before making any request, it first checks if network is available, if network is unavailable, it simply returns error.
     * If network is available, it sends the request on AsyncTask.
     */
    public void send() {
        NetworkUtils networkUtils = new NetworkUtils();
        if (networkUtils.isNetworkAvailable()) {
            SendHttpRequestTask sendHttpRequestTask = new SendHttpRequestTask();
            sendHttpRequestTask.execute();
        } else {
            notifyError(CommunicationErrors.NO_NETWORK, "Network Unavailable. Please check your internet connection.");
        }
    }

    /**
     * Background task to send http requests
     */
    class SendHttpRequestTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {

            String url = getUrl();
            try {
                HttpCommunication communication = new HttpCommunication();
                String response = communication.makeHttpRequest(url);
                parseResponse(response);
            } catch (InvalidResponseException e) {
                e.printStackTrace();
                notifyError(CommunicationErrors.INVALID_RESPONSE, "Invalid Response");
            } catch (Exception e) {
                e.printStackTrace();
                notifyError(CommunicationErrors.ERROR_IN_COMMUNICATION, "Error in communication, please try again after some time.");
            }
            return 1;
        }
    }
}
