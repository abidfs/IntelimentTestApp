package com.example.sayyadabid.testapp.communication;

/**
 * This class extends Exception and is used to notify InvalidResponseException in case response is not received or is invalid.
 * Created by Sayyad.abid on 13-Mar-16.
 */
public class InvalidResponseException extends Exception {
    public InvalidResponseException(String message) {
        super(message);
    }
}
