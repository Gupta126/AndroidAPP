
package com.rahulgupta.androidapp.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Location {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("fromcentral")
    @Expose
    private Fromcentral fromcentral;
    @SerializedName("location")
    @Expose
    private Location_ location;

    /**
     * 
     * @return
     *     The id
     */
    public long getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The fromcentral
     */
    public Fromcentral getFromcentral() {
        return fromcentral;
    }

    /**
     * 
     * @param fromcentral
     *     The fromcentral
     */
    public void setFromcentral(Fromcentral fromcentral) {
        this.fromcentral = fromcentral;
    }

    /**
     * 
     * @return
     *     The location
     */
    public Location_ getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    public void setLocation(Location_ location) {
        this.location = location;
    }

}
