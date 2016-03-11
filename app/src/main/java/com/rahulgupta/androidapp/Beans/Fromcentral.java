
package com.rahulgupta.androidapp.Beans;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Fromcentral {

    @SerializedName("car")
    @Expose
    private String car;
    @SerializedName("train")
    @Expose
    private String train;

    /**
     * 
     * @return
     *     The car
     */
    public String getCar() {
        return car;
    }

    /**
     * 
     * @param car
     *     The car
     */
    public void setCar(String car) {
        this.car = car;
    }

    /**
     * 
     * @return
     *     The train
     */
    public String getTrain() {
        return train;
    }

    /**
     * 
     * @param train
     *     The train
     */
    public void setTrain(String train) {
        this.train = train;
    }

}
