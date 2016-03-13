package com.example.sayyadabid.testapp.datamodels;

import java.io.Serializable;

/**
 * Datamodel class to hold destination details
 * Created by Sayyad.abid on 13-Mar-16.
 */
public class Destination implements Serializable {
    private int id;
    private String name;
    private FromCentral fromcentral;
    private Location location;

    /**
     * Parameterized constructor
     *
     * @param id          the destination id
     * @param name        the destination name
     * @param fromcentral the FromCentral details
     * @param location    the Location details
     */
    public Destination(int id, String name, FromCentral fromcentral, Location location) {
        this.id = id;
        this.name = name;
        this.fromcentral = fromcentral;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FromCentral getFromcentral() {
        return fromcentral;
    }

    public void setFromcentral(FromCentral fromcentral) {
        this.fromcentral = fromcentral;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
