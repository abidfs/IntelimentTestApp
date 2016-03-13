package com.example.sayyadabid.testapp.communication;

import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is responsible for HTTP communication Using Android's HttpURLConnection APIs.
 * The only purpose of this class is used to create the connection, send request and get the response.
 * The received response is then pass on to the Webservice classes for further processing.
 * Created by Sayyad.abid on 13-Mar-16.
 */
public class HttpCommunication {
    /**
     * Method to make http requests and get the response from server.
     *
     * @param strUrl the Url
     * @return the response received from server, null in case of failure
     * @throws IOException
     * @throws InvalidResponseException
     */
    public String makeHttpRequest(String strUrl) throws IOException, InvalidResponseException {
        URL url = new URL(strUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        InputStream inputStream = connection.getInputStream();
        byte[] buffer = new byte[1024];
        int read;
        StringBuilder builder = new StringBuilder();
        while ((read = inputStream.read(buffer)) != -1) {
            builder.append(new String(buffer, 0, read));
        }
        if (!TextUtils.isEmpty(builder)) {
            return builder.toString();
        } else {
            throw new InvalidResponseException("Invalid Response");
        }
    }
}
