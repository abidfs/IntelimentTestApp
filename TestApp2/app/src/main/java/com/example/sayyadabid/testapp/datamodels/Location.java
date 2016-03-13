package com.example.sayyadabid.testapp.datamodels;

import java.io.Serializable;

/**
 * Datamodel class to store location related details
 * Created by Sayyad.abid on 13-Mar-16.
 */
public class Location implements Serializable {
    private double latitude;
    private double longitude;

    /**
     * Parameterized constructor
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     */
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
