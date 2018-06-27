package com.example.tutorial4livedatamvvmrecyclerview.data;



import com.example.tutorial4livedatamvvmrecyclerview.model.Marker;

import java.util.ArrayList;
import java.util.List;

public class Repository implements DataSource {

    private static Repository sInstance;
    private List<Marker> markerList;

    private Repository() {
        markerList = new ArrayList<>();
        Marker marker = new Marker();
        marker.setTitle("Title");
        marker.setLatitude("10.100203050");
        marker.setLongitude("44.100203050");
        marker.setAddress("Edirne");
        marker.setDate(System.currentTimeMillis());
        markerList.add(marker);
    }

    public static Repository getsInstance() {

        if (sInstance == null) {
            sInstance = new Repository();

        }

        return sInstance;
    }

    @Override
    public List<Marker> getAll() {

        return markerList;
    }

    @Override
    public long addMarker(Marker marker) {
        markerList.add(marker);
        return 1;
    }

    @Override
    public int update(Marker marker) {
        if (markerList.contains(marker)) {
            markerList.set(markerList.indexOf(marker), marker);
        }
        return markerList.indexOf(marker);
    }

    @Override
    public int deleteMarker(Marker marker) {
        if (markerList.contains(marker)) {
            markerList.remove(marker);
        }
        return markerList.indexOf(marker);
    }

    @Override
    public void deleteAll() {
        markerList.clear();
    }
}
