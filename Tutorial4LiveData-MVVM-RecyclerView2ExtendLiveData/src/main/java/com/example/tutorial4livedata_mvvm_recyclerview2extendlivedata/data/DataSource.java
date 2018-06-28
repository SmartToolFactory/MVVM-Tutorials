package com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.data;


import android.arch.lifecycle.LiveData;

import com.example.tutorial4livedata_mvvm_recyclerview2extendlivedata.model.Marker;

import java.util.List;

public interface DataSource {
     LiveData<List<Marker>> getAll();
     long addMarker(Marker marker);
     int update(Marker marker);
}
