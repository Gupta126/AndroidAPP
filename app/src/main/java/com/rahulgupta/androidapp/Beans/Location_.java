
package com.rahulgupta.androidapp.Beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Location_ implements Parcelable {

    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;

    protected Location_(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    /**
     * 
     * @return
     *     The latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * 
     * @return
     *     The longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * 
     * @param longitude
     *     The longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Location_> CREATOR = new Parcelable.Creator<Location_>() {
        @Override
        public Location_ createFromParcel(Parcel in) {
            return new Location_(in);
        }

        @Override
        public Location_[] newArray(int size) {
            return new Location_[size];
        }
    };
}
