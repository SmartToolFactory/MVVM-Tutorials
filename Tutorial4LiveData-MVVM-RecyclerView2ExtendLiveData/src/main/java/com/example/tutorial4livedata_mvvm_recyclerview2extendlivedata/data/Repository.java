package com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.data;


import com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.model.Marker;
import com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.model.MarkerLiveData;

import java.util.ArrayList;
import java.util.List;

public class Repository implements DataSource {

    private static Repository sInstance;
    private List<MarkerLiveData> listMutableLiveData;

    private Repository() {
        listMutableLiveData = new ArrayList<>();

        Marker marker = new Marker();
        marker.setTitle("Title1");
        marker.setLatitude("10.5234124");
        marker.setAddress("Berlin");
        MarkerLiveData markerLiveData = new MarkerLiveData();
        markerLiveData.setValue(marker);
        listMutableLiveData.add(markerLiveData);


        marker = new Marker();
        marker.setTitle("Title2");
        marker.setLatitude("40.7534546");
        marker.setAddress("Moskow");
        markerLiveData = new MarkerLiveData();
        markerLiveData.setValue(marker);
        listMutableLiveData.add(markerLiveData);

        marker = new Marker();
        marker.setTitle("Title3");
        marker.setLatitude("30.7534546");
        marker.setAddress("New York");
        markerLiveData = new MarkerLiveData();
        markerLiveData.setValue(marker);
        listMutableLiveData.add(markerLiveData);

    }

    public static Repository getsInstance() {

        if (sInstance == null) {
            sInstance = new Repository();
        }

        return sInstance;
    }

    @Override
    public List<MarkerLiveData> getAll() {
        return listMutableLiveData;
    }

    @Override
    public long addMarker(Marker marker) {
        MarkerLiveData markerLiveData = new MarkerLiveData();
        markerLiveData.setValue(marker);
        return 1;
    }

    @Override
    public int update(Marker marker) {

        return listMutableLiveData.indexOf(marker);
    }

}
