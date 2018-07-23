package com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.model;

import android.arch.lifecycle.MutableLiveData;

public class MarkerLiveData extends MutableLiveData<Marker> {

    private String title;
    private String location;
    private String address;

    public MarkerLiveData() {
        Marker marker = new Marker();
        marker.setTitle("Unknown");
        marker.setLatitude("Unknown");
        marker.setAddress("Unknown");
        setValue(marker);
    }

    public String getTitle() {
        if (getValue() != null) {
            title = getValue().getTitle();
        }
        return title;
    }

    public String getLocation() {
        if (getValue() != null) {
            location = getValue().getLatitude();
        }
        return location;

    }

    public String getAddress() {
        if (getValue() != null) {
            address = getValue().getAddress();
        }
        return address;
    }

}
