package com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.data;


import com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.model.Marker;
import com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.model.MarkerLiveData;

import java.util.List;

public interface DataSource {
    List<MarkerLiveData> getAll();

    long addMarker(Marker marker);

    int update(Marker marker);
}
