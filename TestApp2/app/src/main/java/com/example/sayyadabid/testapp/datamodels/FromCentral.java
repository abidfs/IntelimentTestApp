package com.example.sayyadabid.testapp.datamodels;

import java.io.Serializable;

/**
 * Datamodel class to store travel mode related details
 * Created by Sayyad.abid on 13-Mar-16.
 */
public class FromCentral implements Serializable {
    private String car;
    private String train;

    /**
     * Parameterized constructor
     *
     * @param car   the distance by car
     * @param train the distance by train
     */
    public FromCentral(String car, String train) {
        this.car = car;
        this.train = train;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }
}
