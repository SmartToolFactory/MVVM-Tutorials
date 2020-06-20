package com.example.tutorial4livedatamvvmrecyclerview.data;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tutorial4livedatamvvmrecyclerview.model.Marker;

import java.util.ArrayList;
import java.util.List;

public class Repository implements DataSource {

    private static Repository sInstance;
    private MutableLiveData<List<Marker>> listMutableLiveData;

    private Repository() {
        listMutableLiveData = new MutableLiveData<>();
        List<Marker> markerList = new ArrayList<>();
        Marker marker = new Marker();
        marker.setTitle("Title1");
        marker.setLatitude("10.5234124");
        marker.setAddress("Berlin");
        markerList.add(marker);

        marker = new Marker();
        marker.setTitle("Title2");
        marker.setLatitude("40.7534546");
        marker.setAddress("Moskow");
        markerList.add(marker);

        marker = new Marker();
        marker.setTitle("Title3");
        marker.setLatitude("30.7534546");
        marker.setAddress("New York");
        markerList.add(marker);

        listMutableLiveData.setValue(markerList);
    }

    public static Repository getsInstance() {

        if (sInstance == null) {
            sInstance = new Repository();
        }

        return sInstance;
    }

    @Override
    public LiveData<List<Marker>> getAll() {
        return listMutableLiveData;
    }

    @Override
    public long addMarker(Marker marker) {
        listMutableLiveData.getValue().add(marker);
        return 1;
    }

    @Override
    public int update(Marker marker) {
        List<Marker> markerList = listMutableLiveData.getValue();
        if (markerList != null && markerList.contains(marker)) {
            markerList.set(markerList.indexOf(marker), marker);
        }

        listMutableLiveData.setValue(markerList);
        return markerList.indexOf(marker);
    }

}
